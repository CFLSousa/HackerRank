'use strict';

const fs = require('fs');

process.stdin.resume();
process.stdin.setEncoding('utf-8');

let inputString = '';
let currentLine = 0;

process.stdin.on('data', function(inputStdin) {
    inputString += inputStdin;
});

process.stdin.on('end', function() {
    inputString = inputString.split('\n');

    main();
});

function readLine() {
    return inputString[currentLine++];
}

function compareTriplets(a, b) {
    const results=[];
    let aliceScore=0;
    let bobScore=0;
    let aliceCategory=0;
    let bobCategory=0;
    let numberOfCategories=a.length;

    for(let i=0;i<numberOfCategories;i++){
        aliceCategory=a[i];
        bobCategory=b[i];

        if(aliceCategory>bobCategory)
            aliceScore++;
        else if(aliceCategory<bobCategory)
            bobScore++;
    }

    results.push(aliceScore,bobScore);
    return results;
}

function main() {
    const ws = fs.createWriteStream(process.env.OUTPUT_PATH);

    const a = readLine().replace(/\s+$/g, '').split(' ').map(aTemp => parseInt(aTemp, 10));

    const b = readLine().replace(/\s+$/g, '').split(' ').map(bTemp => parseInt(bTemp, 10));

    const result = compareTriplets(a, b);

    ws.write(result.join(' ') + '\n');

    ws.end();
}

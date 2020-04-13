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

function migratoryBirds(arr) {
    let countersLen=5;
    let maxCounter=0;
    let maxCounterIndex=0;
    let counters=Array(countersLen).fill(0);

    for(let birdType of arr){
        counters[birdType-1]++;
    }

    for(let [index,counterVal] of counters.entries()) {
        if(counterVal>maxCounter){
            maxCounter=counterVal;
            maxCounterIndex=index;
        }
    }

    return maxCounterIndex+1;
}

function main() {
    const ws = fs.createWriteStream(process.env.OUTPUT_PATH);

    const arrCount = parseInt(readLine().trim(), 10);

    const arr = readLine().replace(/\s+$/g, '').split(' ').map(arrTemp => parseInt(arrTemp, 10));

    const result = migratoryBirds(arr);

    ws.write(result + '\n');

    ws.end();
}

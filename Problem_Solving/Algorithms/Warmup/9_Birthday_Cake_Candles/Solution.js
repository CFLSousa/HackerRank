'use strict';

const fs = require('fs');

process.stdin.resume();
process.stdin.setEncoding('utf-8');

let inputString = '';
let currentLine = 0;

process.stdin.on('data', inputStdin => {
    inputString += inputStdin;
});

process.stdin.on('end', _ => {
    inputString = inputString.replace(/\s*$/, '')
        .split('\n')
        .map(str => str.replace(/\s*$/, ''));

    main();
});

function readLine() {
    return inputString[currentLine++];
}

function birthdayCakeCandles(ar) {
    let maxVal=0;
    let candlesQty=0;
    const arLen=ar.length;
    for(let i=0;i<arLen;i++){
        if(ar[i]>maxVal){
            maxVal=ar[i];
            candlesQty=1;
        }
        else if(ar[i]===maxVal)
            candlesQty++;
    }
    return candlesQty;
}

function main() {
    const ws = fs.createWriteStream(process.env.OUTPUT_PATH);

    const arCount = parseInt(readLine(), 10);

    const ar = readLine().split(' ').map(arTemp => parseInt(arTemp, 10));

    let result = birthdayCakeCandles(ar);

    ws.write(result + "\n");

    ws.end();
}

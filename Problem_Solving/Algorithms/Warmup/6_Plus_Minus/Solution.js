'use strict';

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

function plusMinus(arr) {
    let posNums=0.0;
    let negNums=0.0;
    let zeroNums=0.0;
    let posFraction=0.0;
    let negFraction=0.0;
    let zeroFraction=0.0;
    let arrLen=arr.length;

    for(let i=0;i<arrLen;i++) {
        if(arr[i]===0)
            zeroNums++;
        else if(arr[i]>0)
            posNums++;
        else
            negNums++;
    }

    posFraction=posNums/arrLen;
    negFraction=negNums/arrLen;
    zeroFraction=zeroNums/arrLen;

    process.stdout.write(`${posFraction.toFixed(6)}\n`+
        `${negFraction.toFixed(6)}\n`+
        `${zeroFraction.toFixed(6)}`);
}

function main() {
    const n = parseInt(readLine(), 10);

    const arr = readLine().split(' ').map(arrTemp => parseInt(arrTemp, 10));

    plusMinus(arr);
}

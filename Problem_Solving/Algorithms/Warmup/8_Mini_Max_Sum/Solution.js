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

function miniMaxSum(arr) {
    const arrLen=arr.length;
    let sum=0;
    let minVal=Number.MAX_SAFE_INTEGER;
    let maxVal=Number.MIN_SAFE_INTEGER;

    for(let i=0;i<arrLen;i++) {
        sum+=arr[i];
        if(arr[i]>maxVal)
            maxVal=arr[i];
        if(arr[i]<minVal)
            minVal=arr[i];
    }

    console.log(`${sum-maxVal} ${sum-minVal}`);
}

function main() {
    const arr = readLine().split(' ').map(arrTemp => parseInt(arrTemp, 10));

    miniMaxSum(arr);
}

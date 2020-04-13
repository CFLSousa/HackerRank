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

function staircase(n){
    for(let i=1;i<=n;i++){
        process.stdout.write(`${" ".repeat(n-i)}${"#".repeat(i)}`);
        if(i<n)
            process.stdout.write(`\n`);
    }
}

function main() {
    const n = parseInt(readLine(), 10);

    staircase(n);
}

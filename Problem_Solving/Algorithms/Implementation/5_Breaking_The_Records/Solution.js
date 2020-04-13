'use strict';

const fs = require('fs');

process.stdin.resume();
process.stdin.setEncoding('utf-8');

let inputString = '';
let currentLine = 0;

process.stdin.on('data', inputStdin => {
    inputString += inputStdin;
});

process.stdin.on('end', function() {
    inputString = inputString.replace(/\s*$/, '')
        .split('\n')
        .map(str => str.replace(/\s*$/, ''));

    main();
});

function readLine() {
    return inputString[currentLine++];
}

function breakingRecords(scores) {
    
        let sLen=scores.length;
        let maxPoints = scores[0];
        let minPoints = maxPoints;
        let recordTimes = [0,0];

        for(let i = 1; i < sLen; i++){
            if(scores[i] > maxPoints){
                maxPoints = scores[i];
                recordTimes[0]++;
            }
            else if(scores[i] < minPoints){
                minPoints = scores[i];
                recordTimes[1]++;
            }
        }
        return recordTimes;
}

function main() {
    const ws = fs.createWriteStream(process.env.OUTPUT_PATH);

    const n = parseInt(readLine(), 10);

    const scores = readLine().split(' ').map(scoresTemp => parseInt(scoresTemp, 10));

    const result = breakingRecords(scores);

    ws.write(result.join(' ') + '\n');

    ws.end();
}

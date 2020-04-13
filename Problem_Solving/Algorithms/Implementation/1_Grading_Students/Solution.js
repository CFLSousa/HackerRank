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

function gradingStudents(grades) {
    const minimumGrade=38;
    let gradesLen=grades.length;
    let roundedGrades=[];

    for(let i=0;i<gradesLen;i++){
        //round if distance<=2 from ith grade to next multiple of 5
        if(grades[i]>=minimumGrade && grades[i]%5>=3)
            roundedGrades.push(grades[i]-(grades[i]%5)+5);
        else
            roundedGrades.push(grades[i]);
    }

    return roundedGrades;
}

function main() {
    const ws = fs.createWriteStream(process.env.OUTPUT_PATH);

    const gradesCount = parseInt(readLine().trim(), 10);

    let grades = [];

    for (let i = 0; i < gradesCount; i++) {
        const gradesItem = parseInt(readLine().trim(), 10);
        grades.push(gradesItem);
    }

    const result = gradingStudents(grades);

    ws.write(result.join('\n'));

    ws.end();
}

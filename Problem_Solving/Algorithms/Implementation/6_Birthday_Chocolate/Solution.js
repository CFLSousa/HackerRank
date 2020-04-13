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

function birthday(s, d, m) {
    let solutions = 0;
    let sLen = s.length;

    if(m <= sLen){
        //Sum progression in each list position
        for(let i=1;i<sLen;i++)
            s[i]=s[i-1]+s[i];
            
        //If month m is equal to list s size
        //and the sum of the m elements in s is equal to day d,
        //there is only one solution.
        //
        //If the sum of the m elements in list s is different from day d,
        //there is no solution
        if(m==sLen && s[m-1]==d)
            solutions++;
        else if(sLen>m){
            for(let i=m;i<=sLen;i++){
                //Each possible combination of m squares is verified,
                //Each combination equalizing day d is a solution
                if((i>m && s[i-1]-s[i-1-m]==d) || 
                        (i==m && s[i-1]==d))
                    solutions++;
            }
        }
    }
    return solutions;
}

function main() {
    const ws = fs.createWriteStream(process.env.OUTPUT_PATH);

    const n = parseInt(readLine().trim(), 10);

    const s = readLine().replace(/\s+$/g, '').split(' ').map(sTemp => parseInt(sTemp, 10));

    const dm = readLine().replace(/\s+$/g, '').split(' ');

    const d = parseInt(dm[0], 10);

    const m = parseInt(dm[1], 10);

    const result = birthday(s, d, m);

    ws.write(result + '\n');

    ws.end();
}

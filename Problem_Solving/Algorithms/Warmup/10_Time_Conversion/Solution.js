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
    inputString = inputString.trim().split('\n').map(str => str.trim());

    main();
});

function readLine() {
    return inputString[currentLine++];
}

function timeConversion(s) {
    const regExpr=`^(([0][1-9])|([1][0-2])):[0-5][0-9]:[0-5][0-9][AP][M]$`;
    const regExpr12=new RegExp(regExpr);
    let sb24="";

    if(regExpr12.test(s)){
        /*
        character in index 8 (is A or P) and identifies
        12 or 24 hour mode
        */
        let mode=s.charAt(8);
        let hour=s.substring(0,2);

        if(mode==='P' && (hour.localeCompare("12")<0))
            sb24+=(parseInt(hour,10)+12);
        else if(mode==='A' && hour==="12")
            sb24+="00";
        else
            sb24+=hour;

        sb24+=s.substring(2,8);
        return sb24;
    }
    else
        return "";
}

function main() {
    const ws = fs.createWriteStream(process.env.OUTPUT_PATH);

    const s = readLine();

    let result = timeConversion(s);

    ws.write(result + "\n");

    ws.end();
}

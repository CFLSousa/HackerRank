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

function getTotalX(a, b) {
    //Calculate greatest common divisor of b
    let bgcd=0;
    for(let v of b)
        bgcd=gcd(bgcd,v);
    console.log(`Multiple: ` + bgcd);

    //Calculate least common multiple of a
    let alcm=1;
    for(let v of a){
        alcm=lcm(alcm,v);

    /*
    If factor becomes greater than multiple, 
    stop calculating getTotalX function,
    because there is not a solution.
    Multiple will never be divisible by factor
    */
        if(alcm>bgcd)
            return 0;
    }

    /*
    If multiple is not divisible by factor, 
    there is not an integer between the lists
    */
    if(bgcd%alcm !== 0)
        return 0;

    console.log(`Factor: ` + alcm);

    let total=0;

    for(let i=alcm;i<=bgcd;i++){
        if(bgcd%i===0 && i%alcm===0)
            total++;
    }

    return total;
}

//Euclid's algorithm
function gcd(n1, n2){
    if(n1===0 && n2>0)
        return n2;
    else if(n1>0 && n2===0)
        return n1;
    else if(n1>=n2)
        return gcd(n2,n1%n2);
    else
        return gcd(n1,n2%n1);
}

/*
lcm(a,b)=(Math.abs(a)/(gcd(a,b)))*Math.abs(b)
or
lcm(a,b)=(Math.abs(b)/(gcd(a,b)))*Math.abs(a)
*/
function lcm(n1, n2){
    if(n1===0 && n2===0)
        return 0;
    else
        return (Math.abs(n1)/(gcd(n1,n2)))*(Math.abs(n2));
}

function main() {
    const ws = fs.createWriteStream(process.env.OUTPUT_PATH);

    const firstMultipleInput = readLine().replace(/\s+$/g, '').split(' ');

    const n = parseInt(firstMultipleInput[0], 10);

    const m = parseInt(firstMultipleInput[1], 10);

    const arr = readLine().replace(/\s+$/g, '').split(' ').map(arrTemp => parseInt(arrTemp, 10));

    const brr = readLine().replace(/\s+$/g, '').split(' ').map(brrTemp => parseInt(brrTemp, 10));

    const total = getTotalX(arr, brr);

    ws.write(total + '\n');

    ws.end();
}

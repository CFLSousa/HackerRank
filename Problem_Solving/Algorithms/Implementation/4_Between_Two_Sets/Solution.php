<?php

function getTotalX($a, $b) {
    //Calculate greatest common divisor of b
    $bgcd=0;
    foreach($b as $v)
        $bgcd=gcd($bgcd,$v);
    print("Multiple: ".$bgcd."\n");

    //Calculate least common multiple of a
    $alcm=1;
    foreach($a as $v){
        $alcm=lcm($alcm,$v);

    /*
    If factor becomes greater than multiple, 
    stop calculating getTotalX function,
    because there is not a solution.
    Multiple will never be divisible by factor
    */
        if($alcm>$bgcd)
            return 0;
    }

    /*
    If multiple is not divisible by factor, 
    there is not an integer between the lists
    */
    if($bgcd%$alcm != 0)
        return 0;

    print("Factor: ".$alcm."\n");

    $total=0;

    for($i=$alcm;$i<=$bgcd;$i++){
        if($bgcd%$i==0 && $i%$alcm==0)
            $total++;
    }

    return $total;
}


//Euclid's algorithm
function gcd($n1, $n2){
    if($n1==0 && $n2>0)
        return $n2;
    else if($n1>0 && $n2==0)
        return $n1;
    else if($n1>=$n2)
        return gcd($n2,$n1%$n2);
    else
        return gcd($n1,$n2%$n1);
}

/*
lcm(a,b)=(abs(a)/(gcd(a,b)))*abs(b)
or
lcm(a,b)=(abs(b)/(gcd(a,b)))*abs(a)
*/
function lcm($n1, $n2){
    if($n1==0 && $n2==0)
        return 0;
    else
        return ((($n1<0)?($n1*(-1)):$n1)/(gcd($n1,$n2)))*(($n2<0)?($n2*(-1)):$n2);
}

$fptr = fopen(getenv("OUTPUT_PATH"), "w");

$first_multiple_input = explode(' ', rtrim(fgets(STDIN)));

$n = intval($first_multiple_input[0]);

$m = intval($first_multiple_input[1]);

$arr_temp = rtrim(fgets(STDIN));

$arr = array_map('intval', preg_split('/ /', $arr_temp, -1, PREG_SPLIT_NO_EMPTY));

$brr_temp = rtrim(fgets(STDIN));

$brr = array_map('intval', preg_split('/ /', $brr_temp, -1, PREG_SPLIT_NO_EMPTY));

$total = getTotalX($arr, $brr);

fwrite($fptr, $total . "\n");

fclose($fptr);

?>

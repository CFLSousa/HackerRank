<?php

function birthday($s, $d, $m) {
    $solutions = 0;
    $sLen = count($s);

    if($m <= $sLen){
        //Sum progression in each list position
        for($i=1;$i<$sLen;$i++)
            $s[$i]=$s[$i-1]+$s[$i];
            
        //If month m is equal to list s size
        //and the sum of the m elements in s is equal to day d,
        //there is only one solution.
        //
        //If the sum of the m elements in list s is different from day d,
        //there is no solution
        if($m==$sLen && $s[$m-1]==$d)
            $solutions++;
        else if($sLen>$m){
            for($i=$m;$i<=$sLen;$i++){
                //Each possible combination of m squares is verified,
                //Each combination equalizing day d is a solution
                if(($i>$m && $s[$i-1]-$s[$i-1-$m]==$d) || 
                        ($i==$m && $s[$i-1]==$d))
                    $solutions++;
            }
        }
    }
    return $solutions;
}

$fptr = fopen(getenv("OUTPUT_PATH"), "w");

$n = intval(trim(fgets(STDIN)));

$s_temp = rtrim(fgets(STDIN));

$s = array_map('intval', preg_split('/ /', $s_temp, -1, PREG_SPLIT_NO_EMPTY));

$dm = explode(' ', rtrim(fgets(STDIN)));

$d = intval($dm[0]);

$m = intval($dm[1]);

$result = birthday($s, $d, $m);

fwrite($fptr, $result . "\n");

fclose($fptr);

?>

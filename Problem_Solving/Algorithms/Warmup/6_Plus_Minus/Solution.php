<?php

function plusMinus($arr) {
    $posNums=0.0;
    $negNums=0.0;
    $zeroNums=0.0;
    $posFraction=0.0;
    $negFraction=0.0;
    $zeroFraction=0.0;
    $arrLen=count($arr);

    for($i=0;$i<$arrLen;$i++){
        if($arr[$i]===0)
            $zeroNums++;
        else if($arr[$i]>0)
            $posNums++;
        else
            $negNums++;
    }

    $posFraction=$posNums/$arrLen;
    $negFraction=$negNums/$arrLen;
    $zeroFraction=$zeroNums/$arrLen;
    
    printf("%.6f\n%.6f\n%.6f",$posFraction,$negFraction,$zeroFraction); 
}

$stdin = fopen("php://stdin", "r");

fscanf($stdin, "%d\n", $n);

fscanf($stdin, "%[^\n]", $arr_temp);

$arr = array_map('intval', preg_split('/ /', $arr_temp, -1, PREG_SPLIT_NO_EMPTY));

plusMinus($arr);

fclose($stdin);

?>

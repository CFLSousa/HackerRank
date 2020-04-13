<?php

function miniMaxSum($arr) {
    $arrLen=count($arr);
    $sum=0;
    $minVal=PHP_INT_MAX;
    $maxVal=PHP_INT_MIN;

    for($i=0;$i<$arrLen;$i++){
        $sum+=$arr[$i];
        if($arr[$i]>$maxVal)
            $maxVal=$arr[$i];
        if($arr[$i]<$minVal)
            $minVal=$arr[$i];
    }
        
    print(($sum-$maxVal) . " " . ($sum-$minVal));
}

$stdin = fopen("php://stdin", "r");

fscanf($stdin, "%[^\n]", $arr_temp);

$arr = array_map('intval', preg_split('/ /', $arr_temp, -1, PREG_SPLIT_NO_EMPTY));

miniMaxSum($arr);

fclose($stdin);

?>

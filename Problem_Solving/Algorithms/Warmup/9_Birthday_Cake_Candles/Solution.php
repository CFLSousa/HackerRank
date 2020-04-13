<?php

function birthdayCakeCandles($ar) {
    $maxVal=0;
    $candlesQty=0;
    $arLen=count($ar);
    for($i=0;$i<$arLen;$i++){
        if($ar[$i]>$maxVal){
            $maxVal=$ar[$i];
            $candlesQty=1;
        }
        else if($ar[$i]===$maxVal)
            $candlesQty++;
    }
    return $candlesQty;
}

$fptr = fopen(getenv("OUTPUT_PATH"), "w");

$stdin = fopen("php://stdin", "r");

fscanf($stdin, "%d\n", $ar_count);

fscanf($stdin, "%[^\n]", $ar_temp);

$ar = array_map('intval', preg_split('/ /', $ar_temp, -1, PREG_SPLIT_NO_EMPTY));

$result = birthdayCakeCandles($ar);

fwrite($fptr, $result . "\n");

fclose($stdin);
fclose($fptr);

?>

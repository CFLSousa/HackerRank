<?php

function diagonalDifference($arr) {
    $squareMatrixLen=count($arr);
    $leftRightDiagSum=0;
    $rightLeftDiagSum=0;

    for($i=0;$i<$squareMatrixLen;$i++){
        $leftRightDiagSum+=$arr[$i][$i];
        $rightLeftDiagSum+=$arr[$i][$squareMatrixLen-$i-1];
    }

    return abs($leftRightDiagSum-$rightLeftDiagSum);
}

$fptr = fopen(getenv("OUTPUT_PATH"), "w");

$n = intval(trim(fgets(STDIN)));

$arr = array();

for ($i = 0; $i < $n; $i++) {
    $arr_temp = rtrim(fgets(STDIN));

    $arr[] = array_map('intval', preg_split('/ /', $arr_temp, -1, PREG_SPLIT_NO_EMPTY));
}

$result = diagonalDifference($arr);

fwrite($fptr, $result . "\n");

fclose($fptr);

?>

<?php

function migratoryBirds($arr) {
    $countersLen=5;
    $counters=array_fill(0,$countersLen,0);

    foreach($arr as $birdType){
        $counters[$birdType-1]++;
    }

    return array_search(max($counters),$counters)+1;
}

$fptr = fopen(getenv("OUTPUT_PATH"), "w");

$arr_count = intval(trim(fgets(STDIN)));

$arr_temp = rtrim(fgets(STDIN));

$arr = array_map('intval', preg_split('/ /', $arr_temp, -1, PREG_SPLIT_NO_EMPTY));

$result = migratoryBirds($arr);

fwrite($fptr, $result . "\n");

fclose($fptr);

?>

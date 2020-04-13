<?php

function breakingRecords($scores) {
    
    $sLen=count($scores);
    $maxPoints = $scores[0];
    $minPoints = $maxPoints;
    $recordTimes = array(0,0);

    for($i = 1; $i < $sLen; $i++){
        if($scores[$i] > $maxPoints){
            $maxPoints = $scores[$i];
            $recordTimes[0]++;
        }
        else if($scores[$i] < $minPoints){
            $minPoints = $scores[$i];
            $recordTimes[1]++;
        }
    }
    return $recordTimes;
}

$fptr = fopen(getenv("OUTPUT_PATH"), "w");

$stdin = fopen("php://stdin", "r");

fscanf($stdin, "%d\n", $n);

fscanf($stdin, "%[^\n]", $scores_temp);

$scores = array_map('intval', preg_split('/ /', $scores_temp, -1, PREG_SPLIT_NO_EMPTY));

$result = breakingRecords($scores);

fwrite($fptr, implode(" ", $result) . "\n");

fclose($stdin);
fclose($fptr);

?>

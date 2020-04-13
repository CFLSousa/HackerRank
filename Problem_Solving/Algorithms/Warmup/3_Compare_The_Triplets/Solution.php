<?php

function compareTriplets($a, $b) {
    $aliceScore=0;
    $bobScore=0;
    $numberOfCategories=count($a);

    for($i=0;$i<$numberOfCategories;$i++){
        $aliceCategory=$a[$i];
        $bobCategory=$b[$i];

        if($aliceCategory>$bobCategory)
            $aliceScore++;
        else if ($aliceCategory<$bobCategory)
            $bobScore++;
    }

    $results=array($aliceScore,$bobScore);
    return $results;
}

$fptr = fopen(getenv("OUTPUT_PATH"), "w");

$a_temp = rtrim(fgets(STDIN));

$a = array_map('intval', preg_split('/ /', $a_temp, -1, PREG_SPLIT_NO_EMPTY));

$b_temp = rtrim(fgets(STDIN));

$b = array_map('intval', preg_split('/ /', $b_temp, -1, PREG_SPLIT_NO_EMPTY));

$result = compareTriplets($a, $b);

fwrite($fptr, implode(" ", $result) . "\n");

fclose($fptr);

?>

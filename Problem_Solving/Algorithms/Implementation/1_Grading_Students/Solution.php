<?php

function gradingStudents($grades) {
    $minimumGrade=38;
    $gradesLen=count($grades);
    $roundedGrades=array();

    for($i=0;$i<$gradesLen;$i++){
        //round if distance<=2 from ith grade to next multiple of 5
        if($grades[$i]>=$minimumGrade && $grades[$i]%5>=3)
            $roundedGrades[]=($grades[$i]-($grades[$i]%5)+5);
        else
            $roundedGrades[]=$grades[$i];
    }

    return $roundedGrades;
}

$fptr = fopen(getenv("OUTPUT_PATH"), "w");

$grades_count = intval(trim(fgets(STDIN)));

$grades = array();

for ($i = 0; $i < $grades_count; $i++) {
    $grades_item = intval(trim(fgets(STDIN)));
    $grades[] = $grades_item;
}

$result = gradingStudents($grades);

fwrite($fptr, implode("\n", $result));

fclose($fptr);

?>

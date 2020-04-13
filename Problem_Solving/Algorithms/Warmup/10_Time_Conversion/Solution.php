<?php

function timeConversion($s) {
    $regExpr12="/^(([0][1-9])|([1][0-2])):[0-5][0-9]:[0-5][0-9][AP][M]$/";
    $sb24="";

    if(preg_match($regExpr12,$s)){
        /*
        character in index 8 (is A or P) and identifies
        12 or 24 hour mode
        */
        $mode=$s[8];
        $hour=substr($s,0,2);

        if($mode==='P' && (strcmp($hour,"12")<0))
            $sb24.=(intval($hour,10)+12);
        else if($mode==='A' && $hour==="12")
            $sb24.="00";
        else
            $sb24.=$hour;

        $sb24.=substr($s,2,6);
        return $sb24;
    }
    else
        return "";
}

$fptr = fopen(getenv("OUTPUT_PATH"), "w");

$__fp = fopen("php://stdin", "r");

fscanf($__fp, "%[^\n]", $s);

$result = timeConversion($s);

fwrite($fptr, $result . "\n");

fclose($__fp);
fclose($fptr);

?>

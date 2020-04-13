<?php

function staircase($n) {
    for($i=1;$i<=$n;$i++){
        print(str_repeat(" ",$n-$i).str_repeat("#",$i));
        if($i<$n)
            print("\n");
    }
}

$stdin = fopen("php://stdin", "r");

fscanf($stdin, "%d\n", $n);

staircase($n);

fclose($stdin);

?>

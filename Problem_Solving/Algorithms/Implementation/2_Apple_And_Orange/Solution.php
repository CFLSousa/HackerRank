<?php

function countApplesAndOranges($s, $t, $a, $b, $apples, $oranges) {
    $applesOnRoof=count(
        array_filter($apples,
            array(new FruitOnRoof($s,$t,$a),"onRoof")
        )
    );
    
    $orangesOnRoof=count(
        array_filter($oranges,
            array(new FruitOnRoof($s,$t,$b),"onRoof")
        )
    );
    
    print($applesOnRoof."\n".$orangesOnRoof);
}

class FruitOnRoof {
    private $roofStart;
    private $roofEnd;
    private $fruitTreeLocation;
 
    function __construct($roofStart,$roofEnd,$fruitTreeLocation) {
        $this->roofStart=$roofStart;
        $this->roofEnd=$roofEnd;
        $this->fruitTreeLocation=$fruitTreeLocation;
    }

    function getRoofStart(){
        return $this->roofStart;
    }

    function getRoofEnd(){
        return $this->roofEnd;
    }

    function getFruitTreeLocation(){
        return $this->fruitTreeLocation;
    }

    function onRoof($fruit){
        return ($this->fruitTreeLocation+$fruit)>=$this->roofStart && 
                ($this->fruitTreeLocation+$fruit)<=$this->roofEnd;
    }
}

$stdin = fopen("php://stdin", "r");

fscanf($stdin, "%[^\n]", $st_temp);
$st = explode(' ', $st_temp);

$s = intval($st[0]);

$t = intval($st[1]);

fscanf($stdin, "%[^\n]", $ab_temp);
$ab = explode(' ', $ab_temp);

$a = intval($ab[0]);

$b = intval($ab[1]);

fscanf($stdin, "%[^\n]", $mn_temp);
$mn = explode(' ', $mn_temp);

$m = intval($mn[0]);

$n = intval($mn[1]);

fscanf($stdin, "%[^\n]", $apples_temp);

$apples = array_map('intval', preg_split('/ /', $apples_temp, -1, PREG_SPLIT_NO_EMPTY));

fscanf($stdin, "%[^\n]", $oranges_temp);

$oranges = array_map('intval', preg_split('/ /', $oranges_temp, -1, PREG_SPLIT_NO_EMPTY));

countApplesAndOranges($s, $t, $a, $b, $apples, $oranges);

fclose($stdin);

?>

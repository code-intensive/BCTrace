<?php

// $localhost = "localhost";
// $dbusername = "id16535397_batchcodetrace";
// $dbpassword = "8z1L5e@wYb??&[aH";
// $dbname = "id16535397_bctrace";

$localhost = "localhost";
$dbusername = "root";
$dbpassword = "";
$dbname = "bctrace";


try{

    mysqli_report(MYSQLI_REPORT_ERROR | MYSQLI_REPORT_STRICT);

    $conn = new mysqli($localhost,$dbusername,$dbpassword,$dbname);

    $conn->set_charset('utf8');

    if($conn->connect_error){

        echo "$conn->connect_error";    

        // die connection if server not found
        die("Connection Failed : ". $conn->connect_error);

    } 
    else {
        $select = "SELECT * FROM find";
        $res = mysqli_query($conn,$select);
        $result = array();
        
        while($row = mysqli_fetch_array($res)){
            array_push($result,array(
            'batch'=>$row[1],
            'mixer'=>$row[2],
            'sku'=>$row[3],
            'kegs'=>$row[4],
            'date'=>$row[5],
            'time'=>$row[6],
            'operator'=>$row[7],
            'bin'=>$row[8],
            // 'keg_1'=>$row[9],
            // 'keg_2'=>$row[10],
            // 'keg_3'=>$row[11],
            // 'keg_4'=>$row[12],
            // 'keg_5'=>$row[13],
            // 'keg_6'=>$row[14],
            // 'keg_7'=>$row[15],
            // 'keg_8'=>$row[16],
            // 'keg_9'=>$row[17],
            // 'keg_10'=>$row[18],
            // 'keg_11'=>$row[19],
            // 'keg_12'=>$row[20]
        ));
        }
        
        echo json_encode(array("result"=>$result));
        
        mysqli_close($conn);

    }

}  
catch (\mysqli_sql_exception $e)
{
    ?>
    <pre>
        <?php throw new mysqli_sql_exception($e->getMessage(), $e->getCode()); ?>
    </pre>
    <?php
}
?>
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
        $select = "SELECT * FROM consumption";
        $res = mysqli_query($conn,$select);

 
        $result = array();
        
        while($row = mysqli_fetch_array($res)){
            array_push($result,array(
            'batch'=>$row[1],
            'mixer'=>$row[2],
            'sku'=>$row[3],
            'kegs'=>$row[4],
            'c_date'=>$row[5],
            'c_time'=>$row[6],
            'operator'=>$row[7],
            'bin'=>$row[8],
            'dateResult'=>$row[9],
            'timeResult'=>$row[10],
            'usages'=>$row[11],
            'machine'=>$row[12],
            'consumed_keg'=>$row[13]
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
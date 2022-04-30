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

        die("Connection Failed : ". $conn->connect_error);
    } 
    else {
        
        $minor_sql = mysqli_query($conn,"SELECT minorNo, mixer FROM minor");
        
        $batch_sql = mysqli_query($conn, "SELECT batch, mixer FROM generate");

        while ($minor_data = mysqli_fetch_assoc($minor_sql)){
            $batch_data = mysqli_fetch_assoc($batch_sql);
            $batch_datas =  $batch_data["batch"];
            mysqli_query($conn, "DELETE FROM minor WHERE `minorNo` = '".$batch_datas."'");
        }
    }
    mysqli_close($conn);
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

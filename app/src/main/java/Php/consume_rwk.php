<?php

// $localhost = "localhost";
// $dbusername = "id16535397_batchcodetrace";
// $dbpassword = "8z1L5e@wYb??&[aH";
// $dbname = "id16535397_bctrace";

$localhost = "localhost";
$dbusername = "root";
$dbpassword = "";
$dbname = "bctrace";


$batchNo = $_POST['batchNo'];
$mixer = $_POST['mixer'];
$sku = $_POST['sku'];
$kegNo = $_POST['kegNo'];
$c_date = $_POST['c_date'];
$c_time = $_POST['c_time'];
$operator = $_POST['operator'];
$bin = $_POST['binNo'];
$dateResult = $_POST['dateResult'];
$timeResult = $_POST['timeResult'];
$usageType = $_POST['usageType'];
$percentage = $_POST['percentage'];
$consumed_keg = $_POST['consumed_keg'];

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
        
        $stmt = $conn->prepare("insert into consume_rwk(batch, mixer, sku, keg, c_date, c_time, operator, bin, dateResult, timeResult, usages, percentage, consumed_keg) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

        $stmt->bind_param("sssssssssssss", $batchNo, $mixer, $sku, $kegNo, $c_date, $c_time, $operator, $bin, $dateResult, $timeResult, $usageType, $percentage, $consumed_keg);

        $execval = $stmt->execute();
        if (!($conn->affected_rows === 0)){
            echo "Data Created Successfully";
        } else {
            echo "Error: " .$stmt. "<br>" . $conn->error;
        }

        $stmt->close();

        $conn->close();

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

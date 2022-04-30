<?php

// $localhost = "localhost";
// $dbusername = "id16535397_batchcodetrace";
// $dbpassword = "8z1L5e@wYb??&[aH";
// $dbname = "id16535397_bctrace";

$localhost = "localhost";
$dbusername = "root";
$dbpassword = "";
$dbname = "bctrace";

$batch = $_POST['batchNo'];
$mixer = $_POST['mixer'];
$sku = $_POST['sku'];
$keg = $_POST['kegNo'];
$date = $_POST['edate'];
$time = $_POST['etime'];
$operator = $_POST['operator'];
$bin = $_POST['binNo'];

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
        $checker = mysqli_query($conn,"SELECT * FROM find WHERE batch = '".$_POST['batchNo']."' AND date = '".$_POST['edate']."'");
        

        if(mysqli_fetch_row($checker) > 0){
            $response['success'] = "found";
            echo json_encode($response);
        }
        else{
        
            $insertdate = date("Y-m-d", strtotime($date));
            $stmt = $conn->prepare("insert into find(batch, mixer, sku, keg, date, time, operator, bin) values(?, ?, ?, ?, ?, ?, ?, ?)");
            $stmt->bind_param("ssssssss", $batch, $mixer, $sku, $keg, $insertdate, $time, $operator, $bin);
    
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

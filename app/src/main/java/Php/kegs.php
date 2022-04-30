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
$kegsArray_1 = $_POST["kegsArray_0"];
$kegsArray_2 = $_POST["kegsArray_1"];
$kegsArray_3 = $_POST["kegsArray_2"];
$kegsArray_4 = $_POST["kegsArray_3"];
$kegsArray_5 = $_POST["kegsArray_4"];
$kegsArray_6 = $_POST["kegsArray_5"];
$kegsArray_7 = $_POST["kegsArray_6"];
$kegsArray_8 = $_POST["kegsArray_7"];
$kegsArray_9 = $_POST["kegsArray_8"];
$kegsArray_10 = $_POST["kegsArray_9"];
$kegsArray_11 = $_POST["kegsArray_10"];
$kegsArray_12 = $_POST["kegsArray_11"];


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
        $checker = mysqli_query($conn,"SELECT * FROM generate WHERE batch = '".$_POST['batchNo']."' AND date = '".$_POST['edate']."'");
        

        if(mysqli_fetch_row($checker) > 0){
            $response['success'] = "found";
            echo json_encode($response);
        }
        else{
        
            $stmt = $conn->prepare("insert into kegs(batch, keg_1, keg_2, keg_3, keg_4, keg_5, keg_6, keg_7, keg_8, keg_9, keg_10, keg_11, keg_12) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            $stmt->bind_param("sssssssssssss", $batch, $kegsArray_1, $kegsArray_2, $kegsArray_3, $kegsArray_4, $kegsArray_5, $kegsArray_6, $kegsArray_7, $kegsArray_8, $kegsArray_9, $kegsArray_10, $kegsArray_11, $kegsArray_12);
    
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

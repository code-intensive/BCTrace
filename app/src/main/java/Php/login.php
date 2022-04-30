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

        $checker = mysqli_query($conn,"SELECT * FROM register WHERE username ='".$_POST['username']."' AND password = '".$_POST['password']."'");
        
        if (!($conn->affected_rows === 0)){
            $row = mysqli_fetch_row($checker);
            $result = array();
            array_push($result, array('operatorName'=>$row[3]));
            echo json_encode(array("result"=>$result));
            
        } else {
            echo "Failed";
        }
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

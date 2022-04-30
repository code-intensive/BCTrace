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

        $checker = mysqli_query($conn,"SELECT * FROM generate WHERE batch ='".$_POST['search']."' or keg_1 = '".$_POST['search']."' or keg_2 = '".$_POST['search']."' or keg_3 = '".$_POST['search']."' or keg_4 = '".$_POST['search']."' or keg_5 = '".$_POST['search']."' or keg_6 = '".$_POST['search']."' or keg_7 = '".$_POST['search']."' or keg_8 = '".$_POST['search']."' or keg_9 = '".$_POST['search']."' or keg_10 = '".$_POST['search']."' or keg_11 = '".$_POST['search']."' or keg_12 = '".$_POST['search']."'");
           
        $result = array();
        $row = mysqli_fetch_row($checker);
         array_push($result, array(
            'batch'=>$row[1],
            'mixer'=>$row[2],
            'sku'=>$row[3],
            'kegs'=>$row[4],
            'date'=>$row[5],
            'time'=>$row[6],
            'operator'=>$row[7],
            'bin'=>$row[8]
        ));
        
        echo json_encode(array("result"=>$result));

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

<?php

$localhost = "localhost";
$dbusername = "root";
$dbpassword = "";
$dbname = "bctrace";

$request = $_POST['request'];

try{

    mysqli_report(MYSQLI_REPORT_ERROR | MYSQLI_REPORT_STRICT);

    $conn = new mysqli($localhost,$dbusername,$dbpassword,$dbname);

    $conn->set_charset('utf8');

    if($conn->connect_error){

        echo "$conn->connect_error";    

        die("Connection Failed : ". $conn->connect_error);

    } 
    else {
        if ($request == 'amixon'){
            $sql = mysqli_query($conn,"SELECT * FROM amixon ORDER BY id DESC LIMIT 1");
            $result = array();
            
            $minor_data = mysqli_fetch_row($sql);
            array_push($result,array(
                'batch'=>$minor_data[1],
                'mixer'=>$minor_data[2]
                ));

                echo json_encode(array("result"=>$result));
            
        }
        if ($request == "lodige"){
            $sql = mysqli_query($conn,"SELECT * FROM lodige ORDER BY id DESC LIMIT 1");
            $result = array();
            
            $minor_data = mysqli_fetch_row($sql);
            array_push($result,array(
                'batch'=>$minor_data[1],
                'mixer'=>$minor_data[2]
                ));

                echo json_encode(array("result"=>$result));
            
        }
        if($request == "morton"){
            $sql = mysqli_query($conn,"SELECT * FROM morton ORDER BY id DESC LIMIT 1");
            $result = array();
            
            $minor_data = mysqli_fetch_row($sql);
            array_push($result,array(
                'batch'=>$minor_data[1],
                'mixer'=>$minor_data[2]
                ));

                echo json_encode(array("result"=>$result));
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
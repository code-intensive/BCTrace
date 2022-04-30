<?php

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

            $minor_sql = mysqli_query($conn,"SELECT minorNo, mixer FROM minor ORDER BY id DESC LIMIT 1");
            $result = array();
            
            $minor_data = mysqli_fetch_row($minor_sql);
            
            $batch_sql = mysqli_query($conn, "SELECT batch, mixer FROM find ORDER BY id DESC LIMIT 1");
            $batch_data = mysqli_fetch_row($batch_sql);


            if ( $minor_data == $batch_data){
                $response['success'] = "found";
                echo json_encode($response);

            }else{
                $response['success'] = "not found";
                echo json_encode($response);
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

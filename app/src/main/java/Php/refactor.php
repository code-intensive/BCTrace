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
            $result = "barricade";

            $refactor = mysqli_query($conn,"SELECT * FROM refactor");
        
            $row =  mysqli_fetch_array($refactor);

            if($row[1] == $result){
                echo "successful";
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

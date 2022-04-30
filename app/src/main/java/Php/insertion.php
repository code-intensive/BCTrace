<?php

// $localhost = "localhost";
// $dbusername = "id16535397_batchcodetrace";
// $dbpassword = "8z1L5e@wYb??&[aH";
// $dbname = "id16535397_bctrace";

$localhost = "localhost";
$dbusername = "root";
$dbpassword = "";
$dbname = "bctrace";


$insert = $_POST['entry'];

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
            $command = $_POST['insert'];
            if ($command == "insert"){

                $stmt = $conn->prepare("insert into refactor(command) values(?)");

                $stmt->bind_param("s", $insert);
        
                $execval = $stmt->execute();
                if (!($conn->affected_rows === 0)){
                    echo "Data Created Successfully";
                } else {
                    echo "Error: " .$stmt. "<br>" . $conn->error;
                }
        
                $stmt->close();
        
                $conn->close();
                
            }
            elseif($command == "delete"){

                $stmt = mysqli_query($conn, "DELETE from refactor WHERE command='". $insert ."';");
                if (!($conn->affected_rows === 0)){
                    echo "Successful";
                } else {
                    echo "Failed";
                }
        
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

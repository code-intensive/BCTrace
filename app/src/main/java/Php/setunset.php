<?php

// $localhost = "localhost";
// $dbusername = "id16535397_batchcodetrace";
// $dbpassword = "8z1L5e@wYb??&[aH";
// $dbname = "id16535397_bctrace";

$localhost = "localhost";
$dbusername = "root";
$dbpassword = "";
$dbname = "bctrace";


$set = $_POST["set"];
$setRefactor = $_POST["cmd"];

try{

    mysqli_report(MYSQLI_REPORT_ERROR | MYSQLI_REPORT_STRICT);

    $conn = new mysqli($localhost, $dbusername, $dbpassword, $dbname );

    $conn->set_charset('utf8');

    if($conn->connect_error){

        echo "$conn->connect_error";

        die("Connection Failed : ". $conn->connect_error);

    }
    else {

        if ($setRefactor == "setRefactor"){
            $stmt = $conn->prepare("insert into refactor(command) values(?)");

            $stmt->bind_param("s", $set);

            $execval = $stmt->execute();
            if (!($conn->affected_rows === 0)){
                echo "Data Created Successfully";
            } else {
                echo "Error: " .$stmt. "<br>" . $conn->error;
            }
             $stmt->close();

            $conn->close();

        }

        if ($setRefactor == "unsetRefactor"){
        
            mysqli_query($conn, "DELETE FROM refactor WHERE `command`= 'barricade' ");
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
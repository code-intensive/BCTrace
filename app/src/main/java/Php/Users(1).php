<?php

$localhost = "localhost";
$dbusername = "id16535397_batchcodetrace";
$dbpassword = "8z1L5e@wYb??&[aH";
$dbname = "id16535397_bctrace";


try{

    mysqli_report(MYSQLI_REPORT_ERROR | MYSQLI_REPORT_STRICT);

    $conn = new mysqli($localhost, $dbusername, $dbpassword, $dbname );

    $conn->set_charset('utf8');

    if($conn->connect_error){

        echo "$conn->connect_error";

        // die connection if server not found
        die("Connection Failed : ". $conn->connect_error);

    }
    else {
        $sql_command = $_POST['command'];
         if ($sql_command == "create"){
            $stmt = $conn->prepare("insert into users(pk, username, password, is_admin) values(?, ?, ?, ?)");
        $user_pk = $_POST['user_pk'];
        $username = $_POST['username'];
        $password = $_POST['password'];
        $is_admin = $_POST['is_admin'];
           $stmt->bind_param("ssss", $user_pk, $username, $password, $is_admin);
            if (!($conn->affected_rows === 0)){
                echo "Successful";
            } else {
                echo "Failed";
            }
        }else if ($sql_command == "retrieve"){
        $username = $_POST['username'];
        $password = $_POST['password'];

            $query = mysqli_query($conn, "SELECT username, password FROM  users WHERE username='". $username  ."' AND password='". $password."';");

            $user_details = $query -> fetch_array(MYSQLI_ASSOC);

            if ($user_details){
                echo $user_details["username"] .", ". $user_details["password"];
            } else {
                echo "Failed";
            }                
        }else if ($sql_command == "update"){
            $username = $_POST['username'];
            $password = $_POST['password'];
            $is_admin = $_POST['is_admin'];            
            $stmt = mysqli_query($conn, "UPDATE users SET password='". $password ."', is_admin='" .$is_admin ."' WHERE username='". $username ."';");
            if (!($conn->affected_rows === 0)){
                echo "Successful";
            } else {
                echo "Failed";
            }
        }else if ($sql_command == "delete"){
            $username = $_POST['username'];
            $stmt = mysqli_query($conn, "DELETE from users WHERE username='". $username ."';");
            if (!($conn->affected_rows === 0)){
                echo "Successful";
            } else {
                echo "Failed";
            }
        }else{
            echo "Invalid sql-command: Command should be one of-- 'create', 'retrieve', 'update', 'delete'";
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

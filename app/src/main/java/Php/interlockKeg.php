<?php
error_reporting(E_ALL & ~E_WARNING & ~E_NOTICE);

$localhost = "localhost";
$dbusername = "root";
$dbpassword = "";
$dbname = "bctrace";

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
        $sql_for_keg_1 = mysqli_query($conn, "SELECT keg_1 From generate WHERE `keg_1` = '$kegsArray_1'");                
        $sql_for_keg_2 = mysqli_query($conn, "SELECT keg_2 From generate WHERE `keg_2` = '$kegsArray_2'");                
        $sql_for_keg_3 = mysqli_query($conn, "SELECT keg_3 From generate WHERE `keg_3` = '$kegsArray_3'");                
        $sql_for_keg_4 = mysqli_query($conn, "SELECT keg_4 From generate WHERE `keg_4` = '$kegsArray_4'");                
        $sql_for_keg_5 = mysqli_query($conn, "SELECT keg_5 From generate WHERE `keg_5` = '$kegsArray_5'");                
        $sql_for_keg_6 = mysqli_query($conn, "SELECT keg_6 From generate WHERE `keg_6` = '$kegsArray_6'");                
        $sql_for_keg_7 = mysqli_query($conn, "SELECT keg_7 From generate WHERE `keg_7` = '$kegsArray_7'");                
        $sql_for_keg_8 = mysqli_query($conn, "SELECT keg_8 From generate WHERE `keg_8` = '$kegsArray_8'");                
        $sql_for_keg_9 = mysqli_query($conn, "SELECT keg_9 From generate WHERE `keg_9` = '$kegsArray_9'");                
        $sql_for_keg_10 = mysqli_query($conn, "SELECT keg_11 From generate WHERE `keg_10` = '$kegsArray_10'");                
        $sql_for_keg_11 = mysqli_query($conn, "SELECT keg_11 From generate WHERE `keg_11` = '$kegsArray_11'");                
        $sql_for_keg_12 = mysqli_query($conn, "SELECT keg_12 From generate WHERE `keg_12` = '$kegsArray_12'");

        if($kegsArray_1 != "Empty"){
            while ($keg_1 = mysqli_fetch_assoc($sql_for_keg_1)){
                foreach($keg_1 as $keg_of_keg_1){
                    if ($keg_of_keg_1 == $kegsArray_1){
                        $response['success'] = "found_1";
                        echo json_encode($response);
                    }
                }
            }
            if($keg_of_keg_1 != $kegsArray_1){
                $response['success'] = "pass";
                echo json_encode($response);
            }
            
        }
        if($kegsArray_2 != "Empty"){
            while ($keg_2 = mysqli_fetch_assoc($sql_for_keg_2)){
                foreach($keg_2 as $keg_of_keg_2){
                    if ($keg_of_keg_2 == $kegsArray_2){
                        $response['success'] = "found_2";
                        echo json_encode($response);
                    }
                }
            }
             if($keg_of_keg_2 != $kegsArray_2){
                $response['success'] = "pass";
                echo json_encode($response);
            }
        }
        if($kegsArray_3 != "Empty"){
            while ($keg_3 = mysqli_fetch_assoc($sql_for_keg_3)){
                foreach($keg_3 as $keg_of_keg_3){
                    if ($keg_of_keg_3 == $kegsArray_3){
                        $response['success'] = "found_3";
                        echo json_encode($response);
                    }
                }
            }
             if($keg_of_keg_3 != $kegsArray_3){
                $response['success'] = "pass";
                echo json_encode($response);
            }
        }
        if($kegsArray_4 != "Empty"){
            while ($keg_4 = mysqli_fetch_assoc($sql_for_keg_4)){
                foreach($keg_4 as $keg_of_keg_4){
                    if ($keg_of_keg_4 == $kegsArray_4){
                        $response['success'] = "found_4";
                        echo json_encode($response);
                    }
                }
            }
             if($keg_of_keg_4 != $kegsArray_4){
                $response['success'] = "pass";
                echo json_encode($response);
            }
        }
        if($kegsArray_5 != "Empty"){
            while ($keg_5 = mysqli_fetch_assoc($sql_for_keg_5)){
                foreach($keg_5 as $keg_of_keg_5){
                    if ($keg_of_keg_5 == $kegsArray_5){
                        $response['success'] = "found_5";
                        echo json_encode($response);
                    }
                }
            }
             if($keg_of_keg_5 != $kegsArray_5){
                $response['success'] = "pass";
                echo json_encode($response);
            }
        }
        if($kegsArray_6 != "Empty"){
            while ($keg_6 = mysqli_fetch_assoc($sql_for_keg_6)){
                foreach($keg_6 as $keg_of_keg_6){
                    if ($keg_of_keg_6 == $kegsArray_6){
                        $response['success'] = "found_6";
                        echo json_encode($response);
                    }
                }
            }
             if($keg_of_keg_6 != $kegsArray_6){
                $response['success'] = "pass";
                echo json_encode($response);
            }
        }
        if($kegsArray_7 != "Empty"){
            while ($keg_7 = mysqli_fetch_assoc($sql_for_keg_7)){
                foreach($keg_7 as $keg_of_keg_7){
                    if ($keg_of_keg_7 == $kegsArray_7){
                        $response['success'] = "found_7";
                        echo json_encode($response);
                    }
                }
            }
             if($keg_of_keg_7 != $kegsArray_7){
                $response['success'] = "pass";
                echo json_encode($response);
            }
        }
        if($kegsArray_8 != "Empty"){
            while ($keg_8 = mysqli_fetch_assoc($sql_for_keg_8)){
                foreach($keg_8 as $keg_of_keg_8){
                    if ($keg_of_keg_8 == $kegsArray_8){
                        $response['success'] = "found_8";
                        echo json_encode($response);
                    }
                }
            }
             if($keg_of_keg_9 != $kegsArray_8){
                $response['success'] = "pass";
                echo json_encode($response);
            }
    
        }
        if($kegsArray_9 != "Empty"){
            while ($keg_9 = mysqli_fetch_assoc($sql_for_keg_9)){
                foreach($keg_9 as $keg_of_keg_9){
                    if ($keg_of_keg_9 == $kegsArray_9){
                        $response['success'] = "found_9";
                        echo json_encode($response);
                    }
                }
            }
             if($keg_of_keg_9 != $kegsArray_9){
                $response['success'] = "pass";
                echo json_encode($response);
            }
        }
        if($kegsArray_10 != "Empty"){
            while ($keg_10 = mysqli_fetch_assoc($sql_for_keg_10)){
                foreach($keg_10 as $keg_of_keg_10){
                    if ($keg_of_keg_10 == $kegsArray_10){
                        $response['success'] = "found_10";
                         echo json_encode($response);
                    }
                }
            }
             if($keg_of_keg_10 != $kegsArray_10){
                $response['success'] = "pass";
                echo json_encode($response);
            }
        }
        if($kegsArray_11 != "Empty"){
            while ($keg_11 = mysqli_fetch_assoc($sql_for_keg_11)){
                foreach($keg_11 as $keg_of_keg_11){
                    if ($keg_of_keg_11 == $kegsArray_11){
                        $response['success'] = "found_11";
                        echo json_encode($response);
                    }
                }
            }
             if($keg_of_keg_11 != $kegsArray_11){
                $response['success'] = "pass";
                echo json_encode($response);
            }
        }
        if($kegsArray_12 != "Empty"){
            while ($keg_12 = mysqli_fetch_assoc($sql_for_keg_12)){
                foreach($keg_12 as $keg_of_keg_12){
                    if ($keg_of_keg_12 == $kegsArray_12){
                        $response['success'] = "found_12";
                        echo json_encode($response);
                    }
                }
            }
             if($keg_of_keg_12 != $kegsArray_12){
                $response['success'] = "pass";
                echo json_encode($response);
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

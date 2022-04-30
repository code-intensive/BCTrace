<?php
error_reporting(E_ALL & ~E_WARNING & ~E_NOTICE);

$localhost = "localhost";
$dbusername = "root";
$dbpassword = "";
$dbname = "bctrace";

$command = $_POST["command"];
$Empty = "Empty";
try{

    mysqli_report(MYSQLI_REPORT_ERROR | MYSQLI_REPORT_STRICT);

    $conn = new mysqli($localhost,$dbusername,$dbpassword,$dbname);

    $conn->set_charset('utf8');

    if($conn->connect_error){

        echo "$conn->connect_error";    

        die("Connection Failed : ". $conn->connect_error);
    } 
    else {
        if($command == "refresh"){
            $generate_sql = mysqli_query($conn,"SELECT keg_1, keg_2, keg_3, keg_4, keg_5, keg_6, keg_7, keg_8, keg_9, keg_10, keg_11, keg_12 FROM generate");
        
            $consume_sql = mysqli_query($conn, "SELECT consumed_keg FROM consume");

            mysqli_query($conn, "DELETE FROM generate WHERE `keg_1` = '$Empty'and `keg_2` ='$Empty' and `keg_3` = '$Empty' and `keg_4` = '$Empty' and `keg_5` = '$Empty'and `keg_6` = '$Empty'and `keg_7` = '$Empty'and `keg_8` = '$Empty'and `keg_9` = '$Empty'and `keg_10` = '$Empty'and `keg_11` = '$Empty'and `keg_12` = '$Empty'");

            while ($generate_data = mysqli_fetch_assoc($generate_sql)){
                $consume_data = mysqli_fetch_assoc($consume_sql);

                $consumed = $consume_data['consumed_keg'];

                $keg_1 =  $generate_data["keg_1"];
                $keg_2 =  $generate_data["keg_2"];
                $keg_3 =  $generate_data["keg_3"];
                $keg_4 =  $generate_data["keg_4"];
                $keg_5 =  $generate_data["keg_5"];
                $keg_6 =  $generate_data["keg_6"];
                $keg_7 =  $generate_data["keg_7"];
                $keg_8 =  $generate_data["keg_8"];
                $keg_9 =  $generate_data["keg_9"];
                $keg_10 =  $generate_data["keg_10"];
                $keg_11 =  $generate_data["keg_11"];
                $keg_12 =  $generate_data["keg_12"];

                foreach($consume_data as $kegs_in_consumed){
                    
                    if(isset($keg_1) == $kegs_in_consumed){
                        mysqli_query($conn, "UPDATE generate SET keg_1 = 'Empty' WHERE keg_1='". $kegs_in_consumed."'");
                    }

                    if(isset($keg_2) == $kegs_in_consumed){
                        mysqli_query($conn, "UPDATE generate SET keg_2 = 'Empty' WHERE keg_2='". $kegs_in_consumed."'"); 
                    }

                    if(isset($keg_3)== $kegs_in_consumed){
                        mysqli_query($conn, "UPDATE generate SET keg_3 = 'Empty' WHERE keg_3='". $kegs_in_consumed."'");
                    }

                    if(isset($keg_4)== $kegs_in_consumed){
                        mysqli_query($conn, "UPDATE generate SET keg_4 = 'Empty' WHERE keg_4='". $kegs_in_consumed."'");
                    }

                    if(isset($keg_5)== $kegs_in_consumed){
                        mysqli_query($conn, "UPDATE generate SET keg_5 = 'Empty' WHERE keg_5='". $kegs_in_consumed."'");
                    }

                    if(isset($keg_6)== $kegs_in_consumed){
                        mysqli_query($conn, "UPDATE generate SET keg_6 = 'Empty' WHERE keg_6='". $kegs_in_consumed."'");
                    }

                    if(isset($keg_7)== $kegs_in_consumed){
                        mysqli_query($conn, "UPDATE generate SET keg_7 = 'Empty' WHERE keg_7='". $kegs_in_consumed."'"); 
                    }

                    if(isset($keg_8)== $kegs_in_consumed){
                        mysqli_query($conn, "UPDATE generate SET keg_8 = 'Empty' WHERE keg_8='". $kegs_in_consumed."'");
                    }

                    if(isset($keg_9)== $kegs_in_consumed){
                        mysqli_query($conn, "UPDATE generate SET keg_9 = 'Empty' WHERE keg_9='". $kegs_in_consumed."'");
                    }

                    if(isset($keg_10) == $kegs_in_consumed){
                        mysqli_query($conn, "UPDATE generate SET keg_10 = 'Empty' WHERE keg_10='". $kegs_in_consumed."'"); 
                    }

                    if(isset($keg_11) == $kegs_in_consumed){
                        mysqli_query($conn, "UPDATE generate SET keg_11 = 'Empty' WHERE keg_11='". $kegs_in_consumed."'"); 
                    }

                    if(isset($keg_12) == $kegs_in_consumed){
                        mysqli_query($conn, "UPDATE generate SET keg_12 = 'Empty' WHERE keg_12='". $kegs_in_consumed."'");
                    }

                    if($kegs_in_consumed != $generate_data){
                        $result=mysqli_query($conn,"SELECT * FROM consume");
                        $row=mysqli_fetch_array($result);
                        $id=$row[0];
                        mysqli_query($conn,"DELETE from consume WHERE id='$id'");

                    }
                }
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

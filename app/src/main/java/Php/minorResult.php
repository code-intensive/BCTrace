<?php
error_reporting(E_ALL & ~E_WARNING & ~E_NOTICE);

$localhost = "localhost";
$dbusername = "root";
$dbpassword = "";
$dbname = "bctrace";

$minorNo = $_POST['minorNo'];
$mixer = $_POST['mixer'];
$morton = $_POST['morton'];
$amixon = $_POST['amixon'];
$lodige = $_POST['lodige'];

try{

    mysqli_report(MYSQLI_REPORT_ERROR | MYSQLI_REPORT_STRICT);

    $conn = new mysqli($localhost,$dbusername,$dbpassword,$dbname);

    $conn->set_charset('utf8');

    if($conn->connect_error){

        echo "$conn->connect_error";    

        die("Connection Failed : ". $conn->connect_error);

    } 
    else {
        $sql = mysqli_query($conn, "select * from minor where `minorNo` = '$minorNo'");
        if(mysqli_num_rows($sql)){
            echo "Data Found...";
        }
        else{
            $stmt = $conn->prepare("insert into minor(minorNo, mixer) values(?,?)");

            $stmt->bind_param("ss", $minorNo,$mixer);

            $execval = $stmt->execute();
            if (!($conn->affected_rows === 0)){
                echo "Data Created Successfully";
            } else {
                echo "Error: " .$stmt. "<br>" . $conn->error;
            }
        }

        if ($amixon == "amixon"){
            $sql = mysqli_query($conn, "select * from amixon where `minorNo` = '$minorNo'");
            if(mysqli_num_rows($sql)){
                echo "Data Found...";
            }
            else{
                $stmt = $conn->prepare("insert into amixon(minorNo, mixer) values(?,?)");

                $stmt->bind_param("ss", $minorNo,$mixer);

                $execval = $stmt->execute();
                if (!($conn->affected_rows === 0)){
                    echo "Data Created Successfully";
                } else {
                    echo "Error: " .$stmt. "<br>" . $conn->error;
                }
            }
        }

        if ($lodige == "lodige"){
            $sql = mysqli_query($conn, "select * from lodige where `minorNo` = '$minorNo'");
            if(mysqli_num_rows($sql)){
                echo "Data Found...";
            }
            else{
                $stmt = $conn->prepare("insert into lodige(minorNo, mixer) values(?,?)");

                $stmt->bind_param("ss", $minorNo,$mixer);

                $execval = $stmt->execute();
                if (!($conn->affected_rows === 0)){
                    echo "Data Created Successfully";
                } else {
                    echo "Error: " .$stmt. "<br>" . $conn->error;
                } 
            }
        }

        if ($morton == "morton"){
            $sql = mysqli_query($conn, "select * from morton where `minorNo` = '$minorNo'");
            if(mysqli_num_rows($sql)){
                echo "Data Found...";
            }
            else{
                $stmt = $conn->prepare("insert into morton(minorNo, mixer) values(?,?)");

                $stmt->bind_param("ss", $minorNo,$mixer);

                $execval = $stmt->execute();
                if (!($conn->affected_rows === 0)){
                    echo "Data Created Successfully";
                } else {
                    echo "Error: " .$stmt. "<br>" . $conn->error;
                }
            }

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

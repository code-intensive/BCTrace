<?php
	$batch = $_POST['batch'];
	$salt = $_POST['salt'];
	$msg = $_POST['msg'];
	$starch = $_POST['starch'];
	$fat = $_POST['fat'];
	$paprika = $_POST['paprika'];
	

	// Database connection
	$conn = new mysqli('localhost','root','','bctrace');
	if($conn->connect_error){
		echo "$conn->connect_error";
		die("Connection Failed : ". $conn->connect_error);
	} else {
		$sql = mysqli_query($conn, "select * from material where `batch` = '$batch'");
		if(mysqli_num_rows($sql)){
			echo "Data Found...";
		}
		else{
			$stmt = $conn->prepare("insert into material(batch, salt, msg, starch, fat, paprika) values(?, ?, ?, ?, ?, ?)");
			$stmt->bind_param("ssssss", $batch, $salt, $msg, $starch, $fat, $paprika);
			$execval = $stmt->execute();
			echo "Sent successfully...";
			$stmt->close();
			$conn->close();
		}
	}
?>
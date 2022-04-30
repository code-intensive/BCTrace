<?php
require 'ExportToExcel.php';
?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Data Management System</title>
    <!-- <link href="css-js/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous"> -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
    <style>
        body {
            background-image: url('knorrReal.jpg');
            background-repeat: no-repeat;
            background-attachment: fixed;  
            background-size: cover;
        }
    </style>
</head>
<body class="bg-light">
<section class="py-5">
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-md-12">
                <div class="card">
                    <div class="card-header" align="center">
                        <h5><strong>DATA MANAGEMENT SYSTEM</strong></h5>
                    </div>
                    <div class="card-body">
                        <form action="" method="GET">
                            <div class="row">
                                <div class="col-md-4">
                                    <div class="form-group">
                                        <label for="">Start Date</label>
                                        <input type="date" name="start_date" value="<?php if(isset($_GET['start_date'])){echo $_GET['start_date'];}else{}?>" class="form-control" placeholder="mm-dd-yyyy">
                                    </div>
                                </div>
                                <div class="col-md-4">
                                    <div class="form-group">
                                        <label for="">End Date</label>
                                        <input type="date" name="end_date" value="<?php if(isset($_GET['end_date'])){echo $_GET['end_date'];}else{}?>" class="form-control" placeholder="mm-dd-yyyy">
                                    </div>
                                </div>
                                <div class="col-md-4">
                                    <div class="form-group">
                                    <br>
                                        <button type="submit" class="btn btn-success">Search</button>
                                    </div>
                                </div>
                            </div>
                        </form>
                        <div class="col-md-4">
                            <div class="form-group">
                            <br>
                                <form action="kegDatabase.php">
                                    <button type="submit" class="btn btn-success">View Keg</button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="card mt-3">
                    <div class="card-body">
                        <h5>Data List</h5>
                        <hr>
                        <table class="table table-bordered table-striped">
                            <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>Batch</th>
                                    <th>Mixer</th>
                                    <th>Sku</th>
                                    <th>Keg</th>
                                    <th>Date</th>
                                    <th>Time</th>
                                    <th>Operator</th>
                                    <th>Bin</th>
                                </tr>
                            </thead>
                            <tbody>
                                 <?php
                                 error_reporting(E_ALL & ~E_WARNING & ~E_NOTICE);
                                 if(isset($_GET['start_date']) && isset($_GET["end_date"]))
                                 {
                                    if(strtotime($_GET['start_date']) < strtotime($_GET['end_date']))
                                    {
                                   
                                        $start_date = $_GET['start_date'];
                                        $end_date = $_GET["end_date"]; 

                                        $conn = mysqli_connect("localhost", "root", "", "bctrace");
                                        $query = "SELECT * FROM find WHERE date BETWEEN '$start_date' AND '$end_date' ";
                                        $query_run = mysqli_query($conn, $query);

                                        if(mysqli_fetch_assoc($query_run) > 0)
                                        {
                                            foreach($query_run as $row)
                                            { 
                                                ?>
                                                    <tr>
                                                        <td><?php echo $row['id']; ?></td>
                                                        <td><?php echo $row['batch'];?></td>
                                                        <td><?php echo $row['mixer']; ?></td>
                                                        <td><?php echo $row['sku']; ?></td>
                                                        <td><?php echo $row['keg']; ?></td>
                                                        <td><?php echo $row['date']; ?></td>
                                                        <td><?php echo $row['time']; ?></td>
                                                        <td><?php echo $row['operator']; ?></td>
                                                        <td><?php echo $row['bin']; ?></td>
                                                    </tr>
                                                <?php 
                                            }
                                        }
                                        else{
                                            ?>
                                                <tr>
                                                    <td colspan="4"><h5>No Record Found</h5></td>
                                                </tr>
                                            <?php
                                        }
                                    }
                                    else
                                    {
                                        ?>
                                            <tr>
                                                <td colspan="4"><h5>Start Date Is Greater Than End Date. Please Change</h5></td>
                                            </tr>
                                        <?php
                                    }
                                 }
                                 ?> 
                            </tbody>
                        </table>
                        <br />
                        <form method="POST">
                            <input type="submit" name="export" class="btn btn-success" value="Export" />
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
    
<!-- <script src="css-js/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script> -->
<!-- <script src="css-js/bootstrap.min.js" integrity="sha384-Atwg2Pkwv9vp0ygtn1JAojH0nYbwNJLPhwyoVbhoPwBhjQPR5VtM2+xf0Uwh9KtT" crossorigin="anonymous"></script> -->
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.min.js" integrity="sha384-Atwg2Pkwv9vp0ygtn1JAojH0nYbwNJLPhwyoVbhoPwBhjQPR5VtM2+xf0Uwh9KtT" crossorigin="anonymous"></script>
</body>
</html>
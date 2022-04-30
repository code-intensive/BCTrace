<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Data Management System</title>
    <link href="css-js/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
    <!-- <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous"> -->
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
                                        <label for="">Batch Number</label>
                                        <input type="text" name="batch" value="<?php if(isset($_GET['batch'])){echo $_GET['batch'];}else{}?>" class="form-control" placeholder="Batch Number">
                                    </div>
                                </div>
                                <div class="col-md-4">
                                    <div class="form-group">
                                    <br>
                                        <button type="submit" class="btn btn-primary">Fetch Kegs</button>
                                    </div>
                                </div>
                            </div>
                        </form>
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
                                    <th>keg 1</th>
                                    <th>keg 2</th>
                                    <th>Keg 3</th>
                                    <th>keg 4</th>
                                    <th>keg 5</th>
                                    <th>keg 6</th>
                                    <th>keg 7</th>
                                    <th>keg 8</th>
                                    <th>keg 9</th>
                                    <th>keg 10</th>
                                    <th>keg 11</th>
                                    <th>keg 12</th>
                                </tr>
                            </thead>
                            <tbody>
                                 <?php
                                 error_reporting(E_ALL & ~E_WARNING & ~E_NOTICE);
                                 if(isset($_GET['batch']))
                                 {
                                    $batch = $_GET['batch'];

                                    $conn = mysqli_connect("localhost", "root", "", "bctrace");
                                    $query = "SELECT * FROM kegs WHERE batch = '$batch'";
                                    $query_run = mysqli_query($conn, $query);

                                    if(mysqli_num_rows($query_run) > 0)
                                    {
                                        foreach($query_run as $row)
                                        {
                                            ?>
                                                <tr>
                                                    <td><?php echo $row['id']; ?></td>
                                                    <td><?php echo $row['batch']; ?></td>
                                                    <td><?php echo $row['keg_1']; ?></td>
                                                    <td><?php echo $row['keg_2']; ?></td>
                                                    <td><?php echo $row['keg_3']; ?></td>
                                                    <td><?php echo $row['keg_4']; ?></td>
                                                    <td><?php echo $row['keg_5']; ?></td>
                                                    <td><?php echo $row['keg_6']; ?></td>
                                                    <td><?php echo $row['keg_7']; ?></td>
                                                    <td><?php echo $row['keg_8']; ?></td>
                                                    <td><?php echo $row['keg_9']; ?></td>
                                                    <td><?php echo $row['keg_10']; ?></td>
                                                    <td><?php echo $row['keg_11']; ?></td>
                                                    <td><?php echo $row['keg_12']; ?></td>
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
                                 ?> 
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
    
<script src="css-js/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
<script src="css-js/bootstrap.min.js" integrity="sha384-Atwg2Pkwv9vp0ygtn1JAojH0nYbwNJLPhwyoVbhoPwBhjQPR5VtM2+xf0Uwh9KtT" crossorigin="anonymous"></script>
</body>
</html>
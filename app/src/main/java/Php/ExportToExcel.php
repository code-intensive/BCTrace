<?php 
$conn = mysqli_connect("localhost", "root", "", "bctrace");
$output = '';
if(isset($_POST["export"]))
{
    $start_date = $_GET['start_date'];
    $end_date = $_GET["end_date"];
    $query = "SELECT * FROM find WHERE date BETWEEN '$start_date' AND '$end_date' ";
    $query_run = mysqli_query($conn, $query);
    echo mysqli_num_rows($query_run);
    if(mysqli_num_rows($query_run) > 0)
    {
        echo "export";

        $output .= '
        <table class="table" bordered="1">
            <tr>
                <th>ID</th>
                <th>BATCH</th>
                <th>MIXER</th>
                <th>SKU</th>
                <th>KEG</th>
                <th>DATE</th>
                <th>TIME</th>
                <th>OPERATOR</th>
                <th>BIN</th>
            </tr>
        ';
        while($row = mysqli_fetch_array($query_run))
        {
            $output .='
            <tr>
                <td>'.$row["id"].'</td>
                <td>'.$row["batch"].'</td>
                <td>'.$row["mixer"].'</td>
                <td>'.$row["sku"].'</td>
                <td>'.$row["keg"].'</td>
                <td>'.$row["date"].'</td>
                <td>'.$row["time"].'</td>
                <td>'.$row["operator"].'</td>
                <td>'.$row["bin"].'</td>
            </tr>
            ';
        }
        $output .= '</table>';
        $fileName = "Bctrace-" . date('Ymd') . ".xls"; 
        header("Content-Type: application/vnd.ms-excel"); 
        header("Content-Disposition: attachment; filename=\"$fileName\""); 
        header("Pragma: no-cache"); 
        header("Expires: 0");
        echo $output;
    }
}
?>
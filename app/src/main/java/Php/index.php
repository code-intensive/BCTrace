<!DOCTYPE html>
<html>
  <head>
    <title>Material Page</title>
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
  <body>
    <div class="container">
    <section class="py-5">
         <div class="row justify-content-center">
            <div class="col-md-8">
                <div class="card">
                  <div class="card-header" align="center">
                        <h5><strong>Material Form</strong></h5>
                  </div>
                  <div class="card-body">
                    <div class="panel-body">
                      <form action="materialDB.php" method="post">
                      <div class="form-group">
                          <label for="batch">Batch</label>
                          <input
                            type="text"
                            class="form-control"
                            id="batch"
                            name="batch"
                            placeholder="Batch Number"
                            required
                          />
                        </div>
                        <div class="form-group">
                          <label for="Salt">Salt</label>
                          <input
                            type="text"
                            class="form-control"
                            id="salt"
                            name="salt"
                            placeholder="0.00kg"
                            required
                          />
                        </div>
                        <div class="form-group">
                          <label for="msg">msg</label>
                          <input
                            type="text"
                            class="form-control"
                            id="msg"
                            name="msg"
                            placeholder="0.00kg"
                            required
                          />
                        </div>
                        <div class="form-group">
                          <label for="starch">Starch</label>
                          <input
                            type="text"
                            class="form-control"
                            id="starch"
                            name="starch"
                            placeholder="0.00kg"
                            required
                          />
                        </div>
                        <div class="form-group">
                          <label for="fat">Fat</label>
                          <input
                            type="text"
                            class="form-control"
                            id="fat"
                            name="fat"
                            placeholder="0.00kg"
                            required
                          />
                        </div>
                        <div class="form-group">
                          <label for="paprika">Paprika</label>
                          <input
                            type="text"
                            class="form-control"
                            id="paprika"
                            name="paprika"
                            placeholder="0.00kg"
                          />
                        </div>
                        <br>
                        <div align="center">
                          <input type="submit" class="btn btn-primary" value="Submit"/>
                        </div>
                      </form>
                    </div>
                  </div>
                </div>
            </div>
         </div>
        </div>
      </div>
    </div>
  </div>
</div>
  </body>
</html>

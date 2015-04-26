<!DOCTYPE html>
<html>
  <head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="css/bootstrap.css">
  </head>

  <body>
  <nav class="navbar navbar-inverse navbar-fixed-top">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="#">SGC</a>
    </div>
    <div>
      <ul class="nav navbar-nav navbar-right">
        <li><a href="#"><span class="glyphicon glyphicon-user"></span>Sign-in</a></li>
      </ul>
    </div>
  </div>
</nav>
  <div class="container_fluid">
  <div class="jumbotron">
  <h1 align="center">Camara de Comercio de Tulua-SGC</h1>
  </div>
  </div>
    <div class="container">
       <form class="form-horizontal" role="form" action="LoginServlet" method="post">
       <div class="form-group">
       <div class="col-md-6">
     <h2>Login</h2>
    </div>
  </div>
  <div class="form-group">
    <label for="ejemplo_email_3" class="col-md-3 control-label">Email</label>
    <div class="col-md-3">
      <input type="email" class="form-control" id="user" name="user"
             placeholder="Email">
    </div>
  </div>
  <div class="form-group">
    <label for="ejemplo_password_3" class="col-md-3 control-label">Contraseña</label>
    <div class="col-lg-3">
      <input type="password" class="form-control" id="password" name="password"
             placeholder="Contraseña">
    </div>
  </div>
  <div class="form-group">
    <div class="col-lg-offset-2 col-lg-10">
      <button type="submit" class="btn btn-primary">Entrar</button>
    </div>
  </div>
</form>
    </div>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
  </body>
</html>
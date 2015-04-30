<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="generator" content="Bootply" />
<meta charset="UTF-8">
<link rel="stylesheet" href="css/bootstrap.css">
<link href="css/styles.css" rel="stylesheet">
<link href="http://hayageek.github.io/jQuery-Upload-File/uploadfile.min.css" rel="stylesheet">
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<script src="https://rawgithub.com/hayageek/jquery-upload-file/master/js/jquery.uploadfile.min.js"></script>
<script src="js/loadFormats.js"></script>
<script src="js/notification.js"></script>
<script src="js/loadTasks.js"></script>

</head>
<body>
<nav class="navbar navbar-inverse navbar-fixed-top">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="#">SGC</a>
    </div>
    <div>
      <ul class="nav navbar-nav navbar-right">
      <li> <a class="navbar-brand" href="#">Bienvenido <%
    out.println(session.getAttribute("msg"));
    %></a></li>
        <li><a href="#"><span class="glyphicon glyphicon-log-in"></span> Logout</a></li>
      </ul>
    </div>
  </div>
</nav>
  <div class="container_fluid">
  <div class="jumbotron">
  <h1 align="center">Administraci&oacute;n de Contenidos</h1>
  </div>
  </div>

    <div class="container-fluid">
      
      <div class="row row-offcanvas row-offcanvas-left">
        
         <div class="col-sm-3 col-md-2 sidebar-offcanvas" id="sidebar" role="navigation">
           
            <ul class="nav nav-sidebar">
             <li> <a href="#" id="formats"><h4>Formatos</h4></a></li>
               <%
             if("N".equals(session.getAttribute("leadProcess"))) {
                 %> <li><a href="#" id="notifications"><h4>Notificaciones</h4></a></li>
                 <%
                }
              %>
              <li><a href="#"><h4>Tareas</h4></a></li>
              <li><a href="#"><h4>Cuenta</h4></a></li>
            </ul>
        </div>
        <div class="col-sm-4 col-md-10 main" id="content">
      <div class="container">
       <form class="form-horizontal" role="form" action="FortmatsServlet" method="post">
       <div class="form-group">
       <div class="col-md-4">   
   	   <h2>Modificar Formato2</h2>
    </div>
  </div>
  <div class="form-group">
    <label class="col-md-2 control-label">Nombre Formato</label>
    <div class="col-md-2">
      <input type="text" class="form-control" id="nombreFormato" name="nombreFormato"
             placeholder="Nombre Formato">
    </div>
  <div class="form-group">
    <label class="col-md-2 control-label">Versi&oacute;n</label>
    <div class="col-lg-2">
      <input type="text" class="form-control" id="version" name="version"
             placeholder="version del formato">
    </div>
  </div>
  <div class="form-group">
    <div class="col-lg-offset-2 col-lg-10">
      <button type="submit" class="btn btn-primary">Guardar</button>
    </div>
  </div>
</form>
    </div>
</div>
</div>
</div>
</body>
</html>       
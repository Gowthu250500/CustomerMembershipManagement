<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Admin Login Page</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</head>
<body>

<header style="color:white">
	
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
  <div class="container-fluid">
    <a class="navbar-brand" href="Customer_list">Customer Management</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
    </div>
  </div>
</nav>
	
</header>

<br><br><br>

<form action="list" method="post">

<h1 align="center">Admin Login</h1>
<hr>
<div class="container" style="width:50%">

	<div class="input-group input-group-sm mb-3">
  		<span class="input-group-text" id="inputGroup-sizing-sm">USERNAME</span>
  		<input type="text" class="form-control" placeholder="Enter Username"  aria-label="Sizing example input" aria-describedby="inputGroup-sizing-sm" name="tbUserName" required>
	</div>

	<div class="input-group input-group-sm mb-3">
  		<span class="input-group-text" id="inputGroup-sizing-sm">PASSWORD</span>
  		<input type="password" class="form-control" placeholder="Enter Your Password"  aria-label="Sizing example input" aria-describedby="inputGroup-sizing-sm" name="tbPass" required>
	</div>
	
	<div>
		<button class="btn btn-outline-primary">Login</button>
	</div>

</div>

</form>
</body>
</html>
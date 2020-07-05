<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   <%@ page isErrorPage="true"%>

<!DOCTYPE html>
<html>
<head>
<style>
p {
  margin-left: 120px;
}
</style>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<p><img src="img/original.gif" class="rounded mx-auto d-block" width="420" height="350" align="left">
<br><br><br><h1><strong>404 - PAGE NOT FOUND</strong></h1><br>
The page you are looking for might have been removed had its name changed<br> or is temporarily unavailable.
<br>
<%=exception %>
</p>
<a href="index.jsp"><button type="button" class="btn btn-outline-dark">HOME PAGE</button></a>
&nbsp;
<a href="LogoutPage"><button type="button" class="btn btn-outline-dark">Log Out</button></a>

</body>
</html>
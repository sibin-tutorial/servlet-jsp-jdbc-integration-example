<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<h1>Register User</h1>

<form action="ListOfUserServlet" method="post">

Name: <input type = "text" name = "name"/>
Email: <input type = "text" name = "email"/>

<input type="submit" value = "Add User"/>
</form>

<form action="ListOfUserServlet" method="post">

<input type="submit" value = "List all User"/>
</form>


</body>
</html>
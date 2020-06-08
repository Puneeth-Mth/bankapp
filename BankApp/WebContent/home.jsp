<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>


<%
out.println("Welcome "+session.getAttribute("name"));
%>
<br>
<br>

<a href="CheckBalance">CHECK BALANCE</a>
<br>
<br>

<a href="changePwd.html">CHANGE PASSWORD</a>
<br>
<br>

<a href="transferMoney.html">TRANSFER MONEY</a>
<br>
<br>

<a href="applyLoan.html">APPLY LOAN</a>
<br>
<br>

<a href="GetStatement">TRANSACTION  DETAILS</a><br>
<br>
<br>

<a href="Logout"> LOGOUT</a>


</body>
</html>
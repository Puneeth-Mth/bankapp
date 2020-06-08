<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<p>GetStatement details..!!</p>
<%
out.println("RAcc_no="+session.getAttribute("al1"));
%>
<br>
<br>
<%
out.println("Amount="+session.getAttribute("al2"));
%><br>



</body>
</html>
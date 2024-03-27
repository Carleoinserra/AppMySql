<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
String[] lista = (String[])request.getAttribute("lista");
out.print("Hai ordinato");
out.print("<hr>");
for (String prod: lista){
	out.print(prod);
	out.print("<hr>");
}

%>
Prodotti ordinati con successo
</body>
</html>
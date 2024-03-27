<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%@ page import = "java.util.ArrayList"%>
<%@ page import = "app.Prodotto"%>
<%
ArrayList<Prodotto> lista= (ArrayList)request.getAttribute("lista");
String actionUrl = "ordina";

if (lista == null){
	out.print("E' null");
}
else {
	out.print("<form action='" + actionUrl + "' method='post'>");
for (int i = 0; i < lista.size(); i++){
	out.print(lista.get(i).getId());
	out.print(" prodotto: ");
	out.print(lista.get(i).getNome());
	out.print(" prezzo: ");
	out.print(lista.get(i).getPrezzo());
	
	out.print("<input type='checkbox' id='ordini' name='ordini' value='" + lista.get(i).getNome() + "'>");
	
	out.print("<hr>");
	
	
}
out.print("<input type='submit' value='Invia'>");
out.print("</form>");

}

%>
</body>
</html>
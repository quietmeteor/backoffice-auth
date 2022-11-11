<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Home</title>
</head>
<body>
	
		<form action="/Backoffice-auth/" method="POST">
		
		<label for="par1">Parametro 1</label>
		<input type="text" id="par1" name="parametro1"><br>
		
		<label for="par2">Parametro 2</label>
		<input type="text" id="par2" name="parametro2"><br>
		
		<input type="submit" value="Invia richiesta">
		</form>
		
</body>
</html>
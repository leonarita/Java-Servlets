<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>

<!DOCTYPE html>

<html lang="pt-br">

	<head>
		<meta charset="utf-8">
		<title>Olá Mundo em JSP</title>
	</head>

	<body>
		<h1>Olá Mundo em JSP</h1>
		
		<p><% out.println("LP2A4 - Linguagem de Programação 2"); %></p>
		
		<%! Date data = new Date(); %>
		<%! SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm"); %>
		<%! int contador = 0; %>
		
		<p>Data: <%= df.format(data) %></p>
		
		<p>Número de acessos: <%= contador++ %></p>
	</body>
	
</html>
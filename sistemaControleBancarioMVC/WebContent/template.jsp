<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<%@page import="model.Pessoa"%>

<!-- Verificação do status de autenticação do usuário. -->
<%
boolean auth = false;
Pessoa p = new Pessoa();
String idPessoa = "#", visibilidadeAuth = "elemOculto", visibilidadeUnauth = "";

if ("OK".equals(session.getAttribute("usuarioAutenticado")))
{
	auth = true;
	p = (Pessoa)session.getAttribute("pessoa");
	idPessoa = Integer.toString(p.getIdPessoa());
	visibilidadeAuth = "";
	visibilidadeUnauth = "elemOculto";
}
%>

<!-- Template de Página HTML com Bootstrap 4.6 -->
<!-- https://getbootstrap.com/docs/4.6/examples/starter-template/ -->

<!DOCTYPE html>
<html lang="pt-br">
<head>

<!-- Required meta tags for Bootstrap -->
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

<title><%=(String) request.getAttribute("tituloPagina")%></title>

<!-- Bootstrap CSS 4.6 -->
<!-- Tutorial Inicial (em inglês) -->
<!-- https://getbootstrap.com/docs/4.6/getting-started/introduction/ -->

<link rel="stylesheet" type="text/css"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css"
	integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l"
	crossorigin="anonymous">

<!-- DataTables  -->
<!-- https://datatables.net/download/ -->

<link rel="stylesheet" type="text/css"
	href="https://cdn.datatables.net/v/bs4/dt-1.10.23/datatables.min.css" />

<!-- Bootstrap Icons -->

<link rel="stylesheet" type="text/css"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.0/font/bootstrap-icons.css">

<!-- Folhas de estilo de customização -->

<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/custom.css" />

</head>
<body>
	<nav class="navbar navbar-expand-md navbar-dark bg-dark fixed-top">
		<a class="navbar-brand" href="#">Sistema de Controle Bancário</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarsExampleDefault"
			aria-controls="navbarsExampleDefault" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>

		<div class="collapse navbar-collapse" id="navbarsExampleDefault">
			<ul class="navbar-nav mr-auto">
				<li class="<%= visibilidadeAuth %> nav-item">
					<a class="nav-link"
						href="${pageContext.request.contextPath}/pessoa/ver?idpessoa=<%= idPessoa %>">
						Bem-vindo <%= p.getNomePessoa() %>!
						<span class="sr-only">(current)</span>
					</a>
				</li>
				<li class="nav-item active">
					<a class="nav-link"
						href="${pageContext.request.contextPath}/index">Início
						<span class="sr-only">(current)</span>
					</a>
				</li>
				<li class="<%= visibilidadeAuth %> nav-item">
					<a class="nav-link"
						href="${pageContext.request.contextPath}/pessoa/listar">Pessoas</a>
				</li>
				<li class="<%= visibilidadeAuth %> nav-item">
					<a class="nav-link"
						href="${pageContext.request.contextPath}/contacomum/listar">Contas</a>
				</li>
				<li class="<%= visibilidadeAuth %> nav-item">
					<a class="nav-link disabled" href="#"
						tabindex="-1" aria-disabled="true">Movimentos</a>
				</li>
				<li class="<%= visibilidadeUnauth %> nav-item">
					<a class="nav-link"
						href="${pageContext.request.contextPath}/login">Entrar</a>
				</li>
				<li class="<%= visibilidadeAuth %> nav-item">
					<a class="nav-link"
						href="${pageContext.request.contextPath}/logout">Sair</a>
				</li>
			</ul>
			<form class="form-inline my-2 my-lg-0">
				<input class="form-control mr-sm-2" type="text" placeholder="Search"
					aria-label="Search">
				<button class="btn btn-secondary my-2 my-sm-0" type="submit">Search</button>
			</form>
		</div>
	</nav>
	
	<main role="main" class="container">
		<div class="container-template">
			<h1><%=(String) request.getAttribute("tituloPagina")%></h1>
			<%
			if ((String) request.getAttribute("subtituloPagina") != null)
			{
				out.print("<p class=\"lead\">");
				out.print((String) request.getAttribute("subtituloPagina"));
				out.println("</p>");
			}
			%>
		</div>
		
		<%
		String pathPagina = (String) request.getAttribute("pathPagina");
		%>
		
		<%-- Carregamento do conteúdo da view. --%>
		<jsp:include page="${pathPagina}" />
	</main>

	<footer class="footer mt-auto py-3">
		<div class="container">
			<span class="text-muted">Copyright (C) 2021.</span>
		</div>
	</footer>

	<!-- Scripts do jQuery -->

	<script type="text/javascript"
		src="http://code.jquery.com/jquery-3.5.1.min.js"
		integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0="
		crossorigin="anonymous"></script>

	<!-- Scripts do Bootstrap -->

	<script type="text/javascript"
		src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-Piv4xVNRyMGpqkS2by6br4gNJ7DXjqk09RmUpJ8jgGtD7zP9yug3goQfGII0yAns"
		crossorigin="anonymous"></script>

	<!-- Scripts do DataTables -->

	<script type="text/javascript"
		src="https://cdn.datatables.net/v/bs4/dt-1.10.23/datatables.min.js"></script>

	<!-- Scripts de Customização -->

	<script type="text/javascript"
		src="${pageContext.request.contextPath}/js/custom.js"></script>
</body>
</html>

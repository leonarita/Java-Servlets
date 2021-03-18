<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<!-- Controle simples para evitar acesso direto à página JSP pela URL. -->
<%
Boolean doServidor = (Boolean)request.getAttribute("doServidor");

if(doServidor == null || !doServidor)
	response.sendRedirect(request.getContextPath() + "/index");
%>
	
<!-- Bootstrap Alerts -->
<!-- https://getbootstrap.com/docs/4.6/components/alerts/ -->
<div class="alert alert-danger alert-dismissible fade show" role="alert">
	<h4 class="alert-heading">Atenção!</h4>
	Você não está autorizado a acessar esse recurso.
	<button type="button" class="close" data-dismiss="alert" aria-label="Close">
		<span aria-hidden="true">&times;</span>
	</button>
</div>

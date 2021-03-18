<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<!-- Controle simples para evitar acesso direto à página JSP pela URL. -->
<%
Boolean doServidor = (Boolean)request.getAttribute("doServidor");

if(doServidor == null || !doServidor)
	response.sendRedirect(request.getContextPath() + "/index");
%>

<!-- Atribuições e validações iniciais -->
<%
String mensagemAlerta, classeDivAlerta = "";
mensagemAlerta = (String) request.getAttribute("mensagemAlerta");

if(mensagemAlerta == null) {
	mensagemAlerta = "";
	classeDivAlerta = "div-oculta";
}
%>

<!-- Bootstrap Alerts -->
<!-- https://getbootstrap.com/docs/4.6/components/alerts/ -->
<div class="alert alert-primary alert-dismissible fade show <%= classeDivAlerta %>" role="alert">
	<h4 class="alert-heading">Feito!</h4>
	<%= mensagemAlerta %>
	<button type="button" class="close" data-dismiss="alert" aria-label="Close">
		<span aria-hidden="true">&times;</span>
	</button>
</div>

<!-- Grid System com Bootstrap 4.6 -->
<!-- https://getbootstrap.com/docs/4.6/layout/grid/ -->

<div class="container">
	<div class="row">
		<div class="col-xl-12">
			<p>Sistema em construção.</p>
			<p>Utilize o menu superior para navegar pelas opções do sistema.</p>
		</div>
	</div>
</div>

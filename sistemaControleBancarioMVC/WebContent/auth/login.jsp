<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>

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

if(mensagemAlerta == null)
{
	mensagemAlerta = "";
	classeDivAlerta = "div-oculta";
}
%>

<!-- Bootstrap Alerts -->
<!-- https://getbootstrap.com/docs/4.6/components/alerts/ -->
<div class="alert alert-danger alert-dismissible fade show <%= classeDivAlerta %>" role="alert">
	<h4 class="alert-heading">Atenção!</h4>
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
			<!-- Bootstrap Form Components -->
			<!-- https://getbootstrap.com/docs/4.6/components/forms/ -->
			<form action="${pageContext.request.contextPath}/login" method="POST">
				<div class="form-group">
					<label for="txtEmail">E-mail</label>
					<input type="email" class="form-control" name="txtEmail" id="txtEmail" required="required">
				</div>
				<div class="form-group">
					<label for="txtSenha">Senha</label>
					<input type="password" class="form-control" name="txtSenha" id="txtSenha" required="required">
				</div>
				<div class="form-group">
					<input type="submit" class="btn btn-primary" name="btnEnviar" value="Entrar">
					<input class="btn btn-secondary" type="button" name="btnCancelar" value="Voltar"
						onclick='document.location.href="${pageContext.request.contextPath}/index"'>
				</div>
			</form>
		</div>
	</div>
</div>

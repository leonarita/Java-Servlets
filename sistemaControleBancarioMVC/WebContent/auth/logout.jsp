<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<!-- Controle simples para evitar acesso direto à página JSP pela URL. -->
<%
Boolean doServidor = (Boolean)request.getAttribute("doServidor");

if(doServidor == null || !doServidor)
	response.sendRedirect(request.getContextPath() + "/index");
%>

<div class="container">
	<div class="row">
		<div class="col-xl-12 d-flex justify-content-center">
			<p>Tem certeza que deseja sair?</p>
		</div>
	</div>
	<div class="row">
		<div class="col-xl-12 d-flex justify-content-center">
			<!-- Bootstrap Form Components -->
			<!-- https://getbootstrap.com/docs/4.6/components/forms/ -->
			<form action="${pageContext.request.contextPath}/logout" method="POST">
				<div class="form-group">
					<input type="submit" class="btn btn-danger" name="btnEnviar" value="Sair">
					<input class="btn btn-secondary" type="button" name="btnCancelar" value="Cancelar"
						onclick='document.location.href="${pageContext.request.contextPath}/index"'>
				</div>
			</form>
		</div>
	</div>
</div>

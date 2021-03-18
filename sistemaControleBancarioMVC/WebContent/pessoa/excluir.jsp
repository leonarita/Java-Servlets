<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<%@page import="model.Pessoa"%>

<!-- Controle simples para evitar acesso direto à página JSP pela URL. -->
<%
Boolean doServidor = (Boolean)request.getAttribute("doServidor");

if(doServidor == null || !doServidor)
	response.sendRedirect(request.getContextPath() + "/index");
%>

<!-- Atribuições e validações iniciais. -->
<%
Pessoa p = (Pessoa) request.getAttribute("pessoa");
int idPessoa = 0;
String nomePessoa = "";
String classDivExclusao = "div-oculta";
String classDivMensagem = "";

if (p != null)
{
	classDivExclusao = "";
	classDivMensagem = "div-oculta";
	idPessoa = p.getIdPessoa();
	nomePessoa = p.getNomePessoa();
}
%>

<div class="container <%= classDivMensagem %> ">
	<div class="row">
		<div class="col-xl-12">
			<p>Nada a exibir!</p>
		</div>
	</div>
</div>

<div class="container  <%= classDivExclusao %> ">
	<div class="row">
		<div class="col-xl-12">
			<p>Tem certeza que deseja excluir o cadastro de <%= nomePessoa %>?</p>
		</div>
	</div>
	<div class="row">
		<div class="col-xl-12">
			<!-- Bootstrap Form Components -->
			<!-- https://getbootstrap.com/docs/4.6/components/forms/ -->
			<form action="${pageContext.request.contextPath}/pessoa/excluir" method="POST">
				<div class="form-group">
					<input type="hidden" name="numIdPessoa" id="numIdPessoa"
						value="<%= idPessoa %>">
				</div>
				<div class="form-group">
					<input type="submit" class="btn btn-danger" name="btnEnviar"
						value="Excluir">
					<input class="btn btn-secondary" type="button" name="btnCancelar"
						value="Cancelar"
						onclick='document.location.href="${pageContext.request.contextPath}/pessoa"'>
				</div>
			</form>
		</div>
	</div>
</div>

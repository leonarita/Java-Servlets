<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<!-- Controle simples para evitar acesso direto à página JSP pela URL. -->
<%
Boolean doServidor = (Boolean)request.getAttribute("doServidor");

if(doServidor == null || !doServidor)
	response.sendRedirect(request.getContextPath() + "/index");
%>

<div class="container">
	<div class="row">
		<div class="col-xl-12">
			<!-- Bootstrap Form Components -->
			<!-- https://getbootstrap.com/docs/4.6/components/forms/ -->
			<form action="${pageContext.request.contextPath}/pessoa/cadastrar" method="POST"
				 enctype="multipart/form-data">
				<div class="form-group">
					<label for="txtNome">Nome</label>
					<input type="text" class="form-control" name="txtNome"
						id="txtNome" required="required">
				</div>
				<div class="form-group">
					<label for="fileFotoPerfil">Foto de Perfil</label>
					<input type="file" class="form-control" name="fileFotoPerfil"
						id="fileFotoPerfil" required="required">
				</div>
				<div class="form-group">
					<label for="txtEndereco">Endereço</label>
					<input type="text" class="form-control" name="txtEndereco"
						id="txtEndereco" multiple="multiple">
				</div>
				<div class="form-group">
					<label for="numCep">CEP</label>
					<input type="number" class="form-control" name="numCep"
						id="numCep" maxlength="8">
				</div>
				<div class="form-group">
					<label for="txtTelefone">Telefone</label>
					<input type="tel" class="form-control" name="txtTelefone"
						id="txtTelefone" maxlength="20">
				</div>
				<div class="form-group">
					<label for="txtEmail">E-mail</label>
					<input type="email" class="form-control" name="txtEmail"
						id="txtEmail" required="required" aria-describedby="emailHelp">
					<small id="emailHelp" class="form-text text-muted">Nós nunca compartilharemos o seu e-mail com ninguém mais.</small>
				</div>
				<div class="form-group">
					<label for="numRenda">Renda</label>
					<input type="number" class="form-control" name="numRenda"
						id="numRenda" step=".01">
				</div>
				<div class="form-group">
					<label for="txtSenha">Senha</label>
					<input type="password" class="form-control" name="txtSenha"
						id="txtSenha">
				</div>
				<div class="form-group  form-check">
					<input type="checkbox" class="form-check-input" name="chkAtivo"
						id="chkAtivo" checked="checked">
					<label class="form-check-label" for="chkAtivo">Ativo</label>
				</div>
				<div class="form-group">
					<input type="submit" class="btn btn-primary" name="btnEnviar"
						value="Cadastrar">
					<input class="btn btn-secondary"
						type="button" name="btnCancelar" value="Cancelar"
						onclick='document.location.href="${pageContext.request.contextPath}/pessoa"'>
				</div>
			</form>
		</div>
	</div>
</div>

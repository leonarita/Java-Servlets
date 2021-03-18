<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<%@page import="model.Pessoa"%>
<%@page import="java.util.HashSet"%>
<%@page import="java.util.Set"%>

<!-- Controle simples para evitar acesso direto à página JSP pela URL. -->
<%
Boolean doServidor = (Boolean) request.getAttribute("doServidor");

if (doServidor == null || !doServidor)
	response.sendRedirect(request.getContextPath() + "/index");
%>

<!-- Atribuições e validações iniciais. -->
<%
Set<Pessoa> pessoas = new HashSet<Pessoa>();

if(request.getAttribute("pessoasCadastradas") != null)
	pessoas = (Set<Pessoa>) request.getAttribute("pessoasCadastradas");
%>

<div class="container">
	<div class="row">
		<div class="col-xl-12">
			<!-- Bootstrap Form Components -->
			<!-- https://getbootstrap.com/docs/4.6/components/forms/ -->
			<form action="${pageContext.request.contextPath}/contacomum/cadastrar" method="POST">
				<div class="form-group">
					<label for="ddlTitulares">Titulares</label>
					<select class="form-control" name="ddlTitulares" id="ddlTitulares"
						required multiple>
						<%
						for (Pessoa p : pessoas)
						{
							out.print("<option value=\"");
							out.print(p.getIdPessoa());
							out.print("\">");
							out.print(p.getIdPessoa() + " - " + p.getNomePessoa());
							out.println("</option>");
						}
						%>
					</select>
				</div>
				<div class="form-group">
					<label for="dtmAbertura">Abertura</label>
					<input type="datetime-local" class="form-control datepicker" name="dtmAbertura"
						id="dtmAbertura" placeholder="Selecione uma data/hora" required>
				</div>
				<div class="form-group">
					<label for="dtmFechamento">Fechamento</label>
					<input type="datetime-local" class="form-control datepicker" name="dtmFechamento"
						id="dtmFechamento" placeholder="Selecione uma data/hora">
				</div>
				<div class="form-group">
					<label for="ddlSituacao">Situação</label>
					<select class="form-control" name="ddlSituacao" id="ddlSituacao" required>
						<option value="1" selected>Aberta</option>
						<option value="0">Fechada</option>
					</select>
				</div>
				<div class="form-group">
					<label for="txtSenha">Senha</label>
					<input type="password" class="form-control" name="txtSenha" id="txtSenha"
						pattern="[0-9]*" inputmode="numeric" required>
				</div>
				<div class="form-group">
					<label for="numSaldoInicial">Saldo Inicial</label>
					<input type="number" class="form-control" name="numSaldoInicial"
						id="numSaldoInicial" step=".01" required>
				</div>
				<div class="form-group">
					<input type="submit" class="btn btn-primary" name="btnEnviar" value="Cadastrar">
					<input class="btn btn-secondary" type="button" name="btnCancelar" value="Cancelar"
						onclick='document.location.href="${pageContext.request.contextPath}/contacomum"'>
				</div>
			</form>
		</div>
	</div>
</div>

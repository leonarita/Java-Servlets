<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<%@page import="java.util.HashSet"%>
<%@page import="java.util.Set"%>

<%@page import="model.Pessoa"%>

<!-- Controle simples para evitar acesso direto à página JSP pela URL. -->
<%
Boolean doServidor = (Boolean)request.getAttribute("doServidor");

if(doServidor == null || !doServidor)
	response.sendRedirect(request.getContextPath() + "/index");
%>

<!-- Atribuições e validações iniciais. -->
<%
Set<Pessoa> pessoas = new HashSet<Pessoa>();

if(request.getAttribute("pessoasCadastradas") != null)
	pessoas = (Set<Pessoa>) request.getAttribute("pessoasCadastradas");

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

<div class="container">
	<!-- Bootstrap Flex -->
	<!-- https://getbootstrap.com/docs/4.6/utilities/flex/ -->
	<div class="row d-flex justify-content-between">
		<div class="col-xl-3">
			<!-- Botões com Bootstrap -->
			<!-- https://getbootstrap.com.br/docs/4.1/components/buttons/ -->
			<a class="btn btn-secondary"
				href="${pageContext.request.contextPath}/pessoa/listar?view=2"
				role="button">Grid</a>
			<a class="btn btn-secondary"
				href="${pageContext.request.contextPath}/pessoa/listar?view=1"
				role="button">Tabela</a>
		</div>
		<div class="col-xl-3 d-flex justify-content-end">
			<!-- Botões com Bootstrap -->
			<!-- https://getbootstrap.com.br/docs/4.1/components/buttons/ -->
			<a class="btn btn-primary"
				href="${pageContext.request.contextPath}/pessoa/cadastrar"
				role="button">Cadastrar Nova Pessoa</a>
		</div>
	</div>
	
	<!-- Bootstrap Spacing -->
	<!-- https://getbootstrap.com/docs/4.6/utilities/spacing/ -->
	<div class="row mt-3">
		<div class="col-xl-12">
			<!-- https://mdbootstrap.com/docs/b4/jquery/tables/search/ -->
			<table id="dtPessoas"
				class="table table-striped table-bordered table-sm" cellspacing="0"
				width="100%">
				<thead>
					<tr>
						<th class="th-sm">Id</th>
						<th class="th-sm">Nome</th>
						<th class="th-sm">CEP</th>
						<th class="th-sm">Telefone</th>
						<th class="th-sm">E-mail</th>
						<th class="th-sm">Situação</th>
						<th class="th-sm"></th>
					</tr>
				</thead>
				<tbody>
					<%
					for (Pessoa p : pessoas)
					{
						out.println("<tr>"); // Linha
						
						out.println("<td>" + p.getIdPessoa() + "</td>");
						out.println("<td>" + p.getNomePessoa() + "</td>");
						out.println("<td>" + p.getCepPessoa() + "</td>");
						out.println("<td>" + p.getTelefonePessoa() + "</td>");
						out.println("<td>" + p.getEmail() + "</td>");
						out.println("<td>" + (p.getSituacaoPessoa() == 1 ? "Ativo" : "Inativo") + "</td>");
						
						out.println("<td>");
						out.println("<a role=\"button\" class=\"btn btn-secondary\" ");
						out.print("href=\"");
						out.print(request.getContextPath());
						out.print("/pessoa/ver?idpessoa=");
						out.print(p.getIdPessoa());
						out.print("\" >");
						out.println("<i class=\"bi bi-eye\"></i>");
						out.println("</a>");
						
						out.println("<a role=\"button\" class=\"btn btn-secondary\" ");
						out.print("href=\"");
						out.print(request.getContextPath());
						out.print("/pessoa/editar?idpessoa=");
						out.print(p.getIdPessoa());
						out.print("\" >");
						out.println("<i class=\"bi bi-pencil-square\"></i>");
						out.println("</a>");
						
						out.println("<a role=\"button\" class=\"btn btn-secondary\" ");
						out.print("href=\"");
						out.print(request.getContextPath());
						out.print("/pessoa/excluir?idpessoa=");
						out.print(p.getIdPessoa());
						out.print("\" >");
						out.println("<i class=\"bi bi-x-square\"></i>");
						out.println("</a>");
						out.println("</td>");
						
						out.println("</tr>"); // Fim Linha
					}
					%>
				</tbody>
				<tfoot>
					<tr>
						<th>Id</th>
						<th>Nome</th>
						<th>CEP</th>
						<th>Telefone</th>
						<th>E-mail</th>
						<th>Situação</th>
						<th></th>
					</tr>
				</tfoot>
			</table>
		</div>
	</div>
</div>

<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<%@page import="model.ContaComum"%>
<%@page import="model.Pessoa"%>

<%@page import="java.text.NumberFormat"%>

<%@ page import="java.util.Date"%>
<%@page import="java.util.HashSet"%>
<%@page import="java.util.Locale"%>
<%@page import="java.util.Set"%>
<%@ page import="java.text.SimpleDateFormat"%>

<!-- Controle simples para evitar acesso direto à página JSP pela URL. -->
<%
Boolean doServidor = (Boolean) request.getAttribute("doServidor");

if (doServidor == null || !doServidor)
	response.sendRedirect(request.getContextPath() + "/index");
%>

<!-- Atribuições e validações iniciais. -->
<%
Set<ContaComum> contas = new HashSet<ContaComum>();
String nomesTitulares = "";

Locale local = new Locale("pt", "BR");
SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm");
NumberFormat nf = NumberFormat.getCurrencyInstance(local);

if (request.getAttribute("contasCadastradas") != null)
	contas = (Set<ContaComum>) request.getAttribute("contasCadastradas");

String mensagemAlerta, classeDivAlerta = "";
mensagemAlerta = (String) request.getAttribute("mensagemAlerta");

if (mensagemAlerta == null)
{
	mensagemAlerta = "";
	classeDivAlerta = "div-oculta";
}
%>

<!-- Bootstrap Alerts -->
<!-- https://getbootstrap.com/docs/4.6/components/alerts/ -->
<div class="alert alert-primary alert-dismissible fade show <%=classeDivAlerta%>"
	role="alert">
	<h4 class="alert-heading">Feito!</h4>
	<%=mensagemAlerta%>
	<button type="button" class="close" data-dismiss="alert" aria-label="Close">
		<span aria-hidden="true">&times;</span>
	</button>
</div>

<div class="container">
	<!-- Bootstrap Flex -->
	<!-- https://getbootstrap.com/docs/4.6/utilities/flex/ -->
	<div class="row d-flex justify-content-end">
		<div class="col-xl-3 d-flex justify-content-end">
			<!-- Botões com Bootstrap -->
			<!-- https://getbootstrap.com.br/docs/4.1/components/buttons/ -->
			<a class="btn btn-primary"
				href="${pageContext.request.contextPath}/contacomum/cadastrar"
				role="button">Cadastrar Nova Conta</a>
		</div>
	</div>

	<!-- Bootstrap Spacing -->
	<!-- https://getbootstrap.com/docs/4.6/utilities/spacing/ -->
	<div class="row mt-3">
		<div class="col-xl-12">
			<!-- https://mdbootstrap.com/docs/b4/jquery/tables/search/ -->
			<table id="dtContasComuns"
				class="table table-striped table-bordered table-sm" cellspacing="0"
				width="100%">
				<thead>
					<tr>
						<th class="th-sm">Número</th>
						<th class="th-sm">Titular</th>
						<th class="th-sm">Abertura</th>
						<th class="th-sm">Encerramento</th>
						<th class="th-sm">Situação</th>
						<th class="th-sm">Saldo</th>
						<th class="th-sm"></th>
					</tr>
				</thead>
				<tbody>
					<%
					for (ContaComum c : contas)
					{
						out.println("<tr>"); // Linha
						out.println("<td>" + c.getNumeroConta() + "</td>");
						
						nomesTitulares = "";
						
						if(c.getPessoas() != null)
						{
							for(Pessoa p: c.getPessoas())
							{
								nomesTitulares += " / ";
								nomesTitulares += p.getNomePessoa();
							}
						}
						
						if(nomesTitulares.length() > 3)
							nomesTitulares = nomesTitulares.substring(3, nomesTitulares.length());
						
						out.println("<td>" + nomesTitulares + "</td>");
						out.println("<td>" + df.format(c.getAberturaConta().getTime()) + "</td>");
						out.println("<td>" + (c.getFechamentoConta() != null ? df.format(c.getFechamentoConta().getTime()) : "")  + "</td>");
						out.println("<td>" + (c.getSituacaoConta() == 1 ? "Aberta" : "Fechada") + "</td>");
						out.println("<td>" + nf.format(c.getSaldoConta()) + "</td>");
						
						out.println("<td>");
						out.println("<a role=\"button\" class=\"btn btn-secondary\" ");
						out.print("href=\"");
						out.print(request.getContextPath());
						out.print("/contacomum/ver?idconta=");
						out.print(c.getNumeroConta());
						out.print("\" >");
						out.println("<i class=\"bi bi-eye\"></i>");
						out.println("</a>");
						
						out.println("<a role=\"button\" class=\"btn btn-secondary\" ");
						out.print("href=\"");
						out.print(request.getContextPath());
						out.print("/contacomum/editar?idconta=");
						out.print(c.getNumeroConta());
						out.print("\" >");
						out.println("<i class=\"bi bi-pencil-square\"></i>");
						out.println("</a>");
						
						out.println("<a role=\"button\" class=\"btn btn-secondary\" ");
						out.print("href=\"");
						out.print(request.getContextPath());
						out.print("/contacomum/excluir?idconta=");
						out.print(c.getNumeroConta());
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
						<th>Número</th>
						<th>Titular</th>
						<th>Abertura</th>
						<th>Encerramento</th>
						<th>Situação</th>
						<th>Saldo</th>
						<th></th>
					</tr>
				</tfoot>
			</table>
		</div>
	</div>
</div>

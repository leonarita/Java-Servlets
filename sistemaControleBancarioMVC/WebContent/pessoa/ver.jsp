<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<%@ page import="java.text.NumberFormat"%>
<%@page import="java.text.SimpleDateFormat"%>

<%@page import="java.util.Base64"%>
<%@ page import="java.util.Date"%>
<%@page import="java.util.HashSet"%>
<%@page import="java.util.Locale"%>
<%@page import="java.util.Set"%>

<%@page import="model.ContaComum"%>
<%@page import="model.Pessoa"%>

<!-- Controle simples para evitar acesso direto à página JSP pela URL. -->
<%
Boolean doServidor = (Boolean) request.getAttribute("doServidor");

if (doServidor == null || !doServidor)
	response.sendRedirect(request.getContextPath() + "/index");
%>

<!-- Atribuições e validações iniciais. -->
<%
Pessoa p = (Pessoa) request.getAttribute("pessoa");

long idFotoPerfil = 0;
String fotoPerfilBase64 = "", fotoPerfilContentType = "";

if(p.getFotoPerfil() != null && p.getFotoPerfil().getArquivo() != null)
{
	idFotoPerfil = p.getFotoPerfil().getId();
	fotoPerfilBase64 = Base64.getEncoder().encodeToString(p.getFotoPerfil().getArquivo());
	fotoPerfilContentType = p.getFotoPerfil().getContentType();
}

Set<ContaComum> contasRelacionadas = new HashSet<ContaComum>();

if(p.getContas() != null)
	contasRelacionadas = p.getContas();

Locale local = new Locale("pt", "BR");
NumberFormat nf = NumberFormat.getCurrencyInstance(local);

String classDivVisualizacao = "div-oculta", classDivMensagem = "",
	classFotoPerfil = "", classDivAlerta = "";
String chkAtivoStatus = "";
String mensagemAlerta = (String) request.getAttribute("mensagemAlerta");

if (mensagemAlerta == null)
{
	mensagemAlerta = "";
	classDivAlerta = "div-oculta";
}

if (p != null)
{
	classDivVisualizacao = "";
	classDivMensagem = "div-oculta";
	chkAtivoStatus = (p.getSituacaoPessoa() == 1) ? "Ativo" : "Inativo";
	
	if(fotoPerfilBase64 == null || "".equals(fotoPerfilBase64.trim()))
		classFotoPerfil = "div-oculta";
}
else
{
	p = new Pessoa("", "", 0l, "", "", 0.0, 0, "");
}
%>

<!-- Bootstrap Alerts -->
<!-- https://getbootstrap.com/docs/4.6/components/alerts/ -->
<div
	class="alert alert-primary alert-dismissible fade show <%=classDivAlerta%>"
	role="alert">
	<h4 class="alert-heading">Feito!</h4>
	<%=mensagemAlerta%>
	<button type="button" class="close" data-dismiss="alert"
		aria-label="Close">
		<span aria-hidden="true">&times;</span>
	</button>
</div>

<div class="container <%=classDivMensagem%> ">
	<div class="row">
		<div class="col-xl-12">
			<p>Nada a exibir!</p>
		</div>
	</div>
</div>

<div class="container  <%=classDivVisualizacao%> ">
	<div class="row mt-3 d-flex justify-content-start">
		<div class="col-xl-6">
			<div class="container">
				<div class="row mt-3 d-flex justify-content-between">
					<div class="col-xl-6">
						<a class="btn btn-secondary"
							href="${pageContext.request.contextPath}/index"
							role="button">Início</a>
						<a class="btn btn-secondary"
							href="${pageContext.request.contextPath}/pessoa/listar?view=2"
							role="button">Grid</a>
						<a class="btn btn-secondary"
							href="${pageContext.request.contextPath}/pessoa/listar?view=1"
							role="button">Tabela</a>
					</div>
					<div class="col-xl-6 d-flex justify-content-end">
							<%
							out.print("<input type=\"button\" class=\"btn btn-primary mr-3 \" value=\"Atualizar\" ");
							out.print("onclick='document.location.href=\"");
							out.print(request.getContextPath());
							out.print("/pessoa/editar?idpessoa=");
							out.print(p.getIdPessoa());
							out.print("\"' >");
							%>
					</div>
				</div>
				<div class="row mt-3 d-flex justify-content-center <%= classFotoPerfil %>">
					<div class="col-xl-4">
						<img alt="Foto de Perfil" class="img-fluid rounded"
							src="data:<%= fotoPerfilContentType %>;base64,<%= fotoPerfilBase64 %>">
					</div>
				</div>
				<div class="row mt-3 d-flex justify-content-center <%= classFotoPerfil %>">
					<div class="col-xl-4">
						<a href="<%= request.getContextPath() %>/fotoperfil/download?idfotoperfil=<%= idFotoPerfil %>"
							target="_blank">Baixar Foto do Perfil</a>
					</div>
				</div>
				<div class="row mt-3 d-flex justify-content-center">
					<div class="col-xl-4 mr-3 d-flex justify-content-end">
						<label class="font-weight-bold">Nome</label>
					</div>
					<div class="col-xl-6 d-flex justify-content-start">
						<label class="font-weight-bold"><%=p.getNomePessoa()%></label>
					</div>
				</div>
				<div class="row mt-3 d-flex justify-content-center">
					<div class="col-xl-4 mr-3 d-flex justify-content-end">
						<label class="font-weight-bold">Endereço</label>
					</div>
					<div class="col-xl-6 d-flex justify-content-start">
						<label><%=p.getEnderecoPessoa()%></label>
					</div>
				</div>
				<div class="row mt-3 d-flex justify-content-center">
					<div class="col-xl-4 mr-3 d-flex justify-content-end">
						<label class="font-weight-bold">CEP</label>
					</div>
					<div class="col-xl-6 d-flex justify-content-start">
						<label><%=p.getCepPessoa()%></label>
					</div>
				</div>
				<div class="row mt-3 d-flex justify-content-center">
					<div class="col-xl-4 mr-3 d-flex justify-content-end">
						<label class="font-weight-bold">Telefone</label>
					</div>
					<div class="col-xl-6 d-flex justify-content-start">
						<label><%=p.getTelefonePessoa()%></label>
					</div>
				</div>
				<div class="row mt-3 d-flex justify-content-center">
					<div class="col-xl-4 mr-3 d-flex justify-content-end">
						<label class="font-weight-bold">E-mail</label>
					</div>
					<div class="col-xl-6 d-flex justify-content-start">
						<label><%=p.getEmail()%></label>
					</div>
				</div>
				<%
				String rendaString = nf.format(p.getRendaPessoa());
				%>
				<div class="row mt-3 d-flex justify-content-center">
					<div class="col-xl-4 mr-3 d-flex justify-content-end">
						<label class="font-weight-bold">Renda</label>
					</div>
					<div class="col-xl-6 d-flex justify-content-start">
						<label><%=rendaString%></label>
					</div>
				</div>
				<div class="row mt-3 d-flex justify-content-center">
					<div class="col-xl-4 mr-3 d-flex justify-content-end">
						<label class="font-weight-bold">Status</label>
					</div>
					<div class="col-xl-6 d-flex justify-content-start">
						<label><%=chkAtivoStatus%></label>
					</div>
				</div>
			</div>
		</div>
		<div class="col-xl-6">
			<div class="container">
				<div class="row mt-3 d-flex justify-content-between">
					<div class="col-xl-12">
						<h4>Contas Relacionadas</h4>
					</div>
				</div>
				<div class="row mt-3 d-flex justify-content-between">
					<div class="col-xl-12">
						<!-- Tabelas Bootstrap -->
						<!-- https://getbootstrap.com/docs/4.6/content/tables/ -->
						<table class="table table-dark table-striped">
							<thead>
								<tr>
									<th scope="col">Número</th>
									<th scope="col">Status</th>
									<th scope="col">Saldo</th>
								</tr>
							</thead>
							<tbody>
								<%
								for(ContaComum cc : contasRelacionadas)
								{
									out.println("<tr>");
									out.println("<th  scope=\"row\">" + cc.getNumeroConta() + "</th>");
									out.println("<td>" + (cc.getSituacaoConta() == 1 ? "Aberta" : "Fechada") + "</td>");
									out.println("<td>" + nf.format(cc.getSaldoConta()) + "</td>");
									out.println("</tr>");
								}
								%>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

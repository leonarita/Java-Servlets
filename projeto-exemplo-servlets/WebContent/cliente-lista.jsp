<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>IFSP Admin</title>
        <link href="/projeto-exemplo-servlets/css/styles.css" rel="stylesheet" />
        <link href="https://cdn.datatables.net/1.10.20/css/dataTables.bootstrap4.min.css" rel="stylesheet" crossorigin="anonymous" />
        <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.11.2/js/all.min.js" crossorigin="anonymous"></script>
        <link href="//maxcdn.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css" rel="stylesheet">
    </head>
    <body class="sb-nav-fixed">
        <nav class="sb-topnav navbar navbar-expand navbar-dark bg-dark">
            <a class="navbar-brand" href="index.html">IFSP</a>
        </nav>
        <div id="layoutSidenav">
            <div id="layoutSidenav_nav">
                <nav class="sb-sidenav accordion sb-sidenav-light" id="sidenavAccordion">
                    <div class="sb-sidenav-menu">
                        <div class="nav">
                            <div class="sb-sidenav-menu-heading">Menu Principal</div>
                            <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseLayouts" aria-expanded="false" aria-controls="collapseLayouts"
                                ><div class="sb-nav-link-icon"><i class="fas fa-columns"></i></div>
                                Cliente
                                <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div
                            ></a>
                            <div class="collapse" id="collapseLayouts" aria-labelledby="headingOne" data-parent="#sidenavAccordion">
                                <nav class="sb-sidenav-menu-nested nav">
									<a class="nav-link" href="#">Lista</a>
									<a class="nav-link" href="#">Novo</a></nav>
                            </div>
                            <!--
							<a class="nav-link" href="#"
                                ><div class="sb-nav-link-icon"><i class="fas fa-table"></i></div>
                                Novo link
							</a> -->
                        </div>
                    </div>
                    <div class="sb-sidenav-footer">
                        <div class="small">Disciplina:</div>
                        LP2A5
                    </div>
                </nav>
            </div>
            <div id="layoutSidenav_content">
                <main>
                    <div class="container-fluid">
                        <h1 class="mt-4">Cliente</h1>
                        <ol class="breadcrumb mb-4">
                            <li class="breadcrumb-item"><a href="#">Home</a></li>
                            <li class="breadcrumb-item active">Cliente</li>
                        </ol>
                        <div class="card mb-4">
                            <div class="card-body">
								<button type="button" class="btn btn-outline-primary">+ Cliente</button>
							</div>
                        </div>
                        <div class="card mb-4">
                            <div class="card-header"><i class="fas fa-table mr-1"></i>Lista de Clientes</div>
                            <div class="card-body">
                                <div class="table-responsive">
                                    <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                                        <thead>
                                            <tr>
                                                <th>Código</th>
                                                <th>Nome</th>
                                                <th>E-mail</th>
                                                <th>Tipo</th>
                                                <th>CPF/CNPJ</th>
                                                <th>Telefone</th>
                                                <th>Ações</th>
                                            </tr>
                                        </thead>
                                        <tfoot>
                                            <tr>
                                                <th>Código</th>
                                                <th>Nome</th>
                                                <th>E-mail</th>
                                                <th>Tipo</th>
                                                <th>CPF/CNPJ</th>
                                                <th>Telefone</th>
												<th>Ações</th>
                                            </tr>
                                        </tfoot>
                                        <tbody>
                                            <c:forEach items="${clientes}" var="cli" varStatus="loopStatus"> 
                                            	<tr> 
                                            		<td>${cli.id}</td> 
                                            		<td>${cli.nome}</td> 
                                            		<td>${cli.email}</td> 
                                            		<td>${cli.tipo}</td> 
                                            		<td>${cli.cpf_cnpj}</td>
                                            		<td>${cli.telefone}</td> 
                                            		<td>
                                            			<a href="/projeto-exemplo-servlets/cliente/editar?id=${cli.id}">
                                            				<i class="fa fa-pencil-square-o" aria-hidden="true"></i>
                                            			</a>
                                            			<a href="#" class="deleta" id="${cli.id}">
                                            				<i class="fa fa-trash" aria-hidden="true"></i>
                                            			</a>
                                            		</td>
                                            	</tr> 
                                        	</c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                </main>
                <footer class="py-4 bg-light mt-auto">
                    <div class="container-fluid">
                        <div class="d-flex align-items-center justify-content-between small">
                            <div class="text-muted">Prof. Aldo Paim</div>
                            <div>
                                Versão 1.0
                            </div>
                        </div>
                    </div>
                </footer>
            </div>
        </div>
        <script src="https://code.jquery.com/jquery-3.4.1.min.js" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
        <script src="https://cdn.datatables.net/1.10.20/js/jquery.dataTables.min.js" crossorigin="anonymous"></script>
        <script src="https://cdn.datatables.net/1.10.20/js/dataTables.bootstrap4.min.js" crossorigin="anonymous"></script>
        <script src="/projeto-exemplo-servlets/assets/demo/datatables-demo.js"></script>
        
        <script>
        	$('.deleta').bind('click', function() {
        		var id = this.id;
        		
        		if(confirm("Deseja deletar o cliente de código " + id)) {
        			$.ajax({
        				url: "/projeto-exemplo-servlets/cliente/deletar",
        				type: "post",
        				data: {
        					id: id
        				},
        				success: function(data) {
        						alert('Cliente deletado com sucesso!');
        						location.reload();
        				}
        			})
        		}
        	})
        </script>
    </body>
</html>

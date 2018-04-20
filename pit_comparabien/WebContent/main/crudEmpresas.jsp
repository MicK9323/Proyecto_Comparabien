<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html">
<html lang="es">
<jsp:include page="../layout/head.jsp" />

<body class="fix-header card-no-border fix-sidebar">
	<!-- Preloader -->
	<jsp:include page="../layout/preloader.jsp" />

	<div id="main-wrapper">
		<jsp:include page="../layout/topbar.jsp" />
		<jsp:include page="../layout/sidebar.jsp" />
		<!-- ============================================================== -->
		<!-- Contenido de la pagina  -->
		<!-- ============================================================== -->
		<div class="page-wrapper">
			<div class="container-fluid">
				<div class="container-fluid">
					<div class="row page-titles">
						<div class="col-md-5 align-self-center">
							<h3 class="text-themecolor">Empresas y Entidades Registradas</h3>
						</div>
					</div>
					<div class="row">
						<div class="col-12">
							<div class="card">
								<!-- .left-right-aside-column-->
								<div class="contact-page-aside">
									<!-- .left-aside-column-->
									<div class="left-aside bg-light-part">
										<div class="row">
											<div class="col-lg-12">
												<div class="card bg-info text-white">
													<div class="card-body">
														<div class="d-flex">
															<div class="stats">
																<h1 class="text-white">
																	<s:property value="response.cantidad" />
																</h1>
																<h6 class="text-white">Empresas Registradas</h6>
																<s:a action="regEmpresa"
																	cssClass="btn btn-rounded btn-outline btn-light m-t-10 font-14">
																	<i class="fa fa-plus"></i> Agregar</s:a>
															</div>
															<div class="stats-icon text-right ml-auto">
																<i class="fa fa-briefcase display-5 op-3 text-dark"></i>
															</div>
														</div>
													</div>
												</div>
											</div>
										</div>
									</div>
									<!-- /.left-aside-column-->
									<div class="right-aside ">
										<div class="right-page-header">
											<div class="d-flex">
												<div class="align-self-center">
													<h4 class="card-title m-t-10">Registro de Empresas</h4>
												</div>
												<div class="ml-auto">
													<input type="text" id="demo-input-search2"
														placeholder="Buscar Empresa" class="form-control">
												</div>
											</div>
										</div>
										<s:if test="response.ver">
											<div class="alert alert-info">
												<button type="button" class="close" data-dismiss="alert"
													aria-label="Close">
													<span aria-hidden="true">×</span>
												</button>
												<h3 class="text-info">
													<i class="fa fa-exclamation-circle"></i> Información
												</h3>
												Aun no se ha registrado ninguna empresa pulse en el boton <strong>Agregar</strong>
												para realizar el registro de una empresa.
											</div>
										</s:if>
										<div class="table-responsive">
											<table id="demo-foo-addrow"
												class="table m-t-30 table-hover no-wrap contact-list"
												data-page-size="10">
												<thead>
													<tr>
														<th>Logo</th>
														<th>Razon Social</th>
														<th>Opciones</th>
													</tr>
												</thead>
												<tbody>
													<s:iterator value="response.empresas">
														<s:url id="idBuscar" action="verEmpresa">
								 							<s:param name="empresa.id_emp" value="id_emp"/>
														 </s:url>
														<tr>
															<td> 
																	<img class="img-responsive img-rounded"  
																	alt="<s:property value="nom_empresa" />" 
																	src="../assets/images/bancos/company.png">
															</td>
															<td><s:property value="nom_empresa" /></td>
															<td><s:a href="%{idBuscar}" class="btn btn-outline-primary">Ver Empresa</s:a></td>
														</tr>
													</s:iterator>
												</tbody>
												<tfoot>
													<tr>
														<td colspan="12">
															<div class="text-right">
																<ul class="pagination">
																</ul>
															</div>
														</td>
													</tr>
												</tfoot>
											</table>
										</div>
										<!-- .left-aside-column-->
									</div>
									<!-- /.left-right-aside-column-->
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<jsp:include page="../layout/footer.jsp" />
		</div>
	</div>
	<jsp:include page="../layout/scripts.jsp" />
	<script src="js/empresa.js"></script>
</body>

</html>
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

				<div class="row">
					<div class="col-lg-4 col-xlg-3 col-md-5">
						<div class="card">
							<div class="card-body">
								<div class="m-t-30">
									<img src="../assets/images/bancos/company.png"
										class="img-responsive img-rounded" width="150" />
									<h4 class="card-title m-t-10"><s:property value="empresa.nom_empresa" /></h4>
									<!-- <h6 class="card-subtitle">Accoubts Manager Amix corp</h6> -->
								</div>
							</div>
							<div>
								<hr>
							</div>
							<div class="card-body">
								<small class="text-muted">Correo</small>
								<h6><s:property value="empresa.email_empresa" /></h6>
								<small class="text-muted p-t-30 db">Teléfono</small>
								<h6><s:property value="empresa.telf_empresa" /></h6>
								<small class="text-muted p-t-30 db">Dirección</small>
								<h6><s:property value="empresa.dir_empresa" /></h6>
								<br />
							</div>
						</div>
					</div>
					<div class="col-lg-8 col-xlg-9 col-md-7">
						<div class="card">
							<div class="card-header bg-info">
								<h4 class="m-b-0 text-white">Datos de la empresa</h4>
							</div>
							<div class="card-body">
								<form action="#">
									<div class="form-body">
										<div class="row">
											<div class="col-md-6">
												<div class="form-group">
													<label>Ruc</label> 
													<s:textfield readonly="true" id="ruc" 
                                                    	name="empresa.ruc_empresa" cssClass="form-control" />
													<s:hidden name="empresa.id_emp" id="cod"/>
												</div>
											</div>
											<!--/span-->
											<div class="col-md-6">
												<div class="form-group">
													<label>Teléfono</label>
													<s:textfield maxlength="10" id="telf" 
                                                    	name="empresa.telf_empresa" cssClass="form-control" />
												</div>
											</div>
											<!--/span-->
										</div>
										<div class="row">
											<div class="col-md-12 ">
												<div class="form-group">
													<label>Razón Social</label> 
													<s:textfield type="tel" readonly="true" maxlength="10" id="telf" 
														cssClass="form-control required" name="empresa.nom_empresa" />
												</div>
											</div>
										</div>
										<div class="row">
											<div class="col-md-12 ">
												<div class="form-group">
													<label>Dirección</label>
													<s:textfield maxlength="100" id="dir" cssClass="form-control required" 
														name="empresa.dir_empresa" />
												</div>
											</div>
										</div>
										<!--/row-->
										<div class="row">
											<div class="col-md-12 ">
												<div class="form-group">
													<label>Correo</label>
													<s:textfield type="email" maxlength="50" id="email" 
														cssClass="form-control required" name="empresa.email_empresa" />
												</div>
											</div>
										</div>
									</div>
									<div class="form-actions">
										<div id="btnUpdate" class="btn btn-success">
											<i class="fa fa-check"></i> Guardar
										</div>
										<s:a action="listaEmpresas" class="btn btn-warning">Volver</s:a>
									</div>
								</form>
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
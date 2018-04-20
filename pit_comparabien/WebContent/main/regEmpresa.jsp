<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html">
<html lang="es">
<jsp:include page="../layout/head.jsp" />
<style>
.state-icon {
	left: -5px;
}

.list-group-item-primary {
	color: rgb(255, 255, 255);
	background-color: rgb(66, 139, 202);
}
</style>
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
			
				<div class="row page-titles">
                    <div class="col-md-5 align-self-center">
                        <h3 class="text-themecolor">Registrar Empresa</h3>
                    </div>
                </div>
                
                <div class="row" id="validation">
                    <div class="col-12">
                        <div class="card wizard-content">
                            <div class="card-body">
                                <form class="validation-wizard wizard-circle">
                                    <!-- Step 1 -->
                                    <h6>Datos de la Empresa</h6>
                                    <section>
                                        <div class="row">
                                            <div class="col-md-6">
                                                <div class="form-group">
                                                    <label for="ruc"> RUC :
                                                        <span class="danger">*</span>
                                                    </label>
                                                    <s:textfield maxlength="11" id="ruc" 
                                                    	name="empresa.ruc_empresa" cssClass="form-control required" />
                                                </div>
                                            </div>
                                            <div class="col-md-3">
                                                <div class="form-group">
                                                    <label for="ruc"> Consultar en linea </label>
                                                    <div id="btnConsulta" class="btn btn-outline-primary form-control">
                                                        <i class="fa fa-search"></i>Consultar Ruc
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-md-6">
                                                <div class="form-group">
                                                    <label for="razonsoc"> Razón Social :
                                                        <span class="danger">*</span>
                                                    </label>
                                                    <s:textfield id="razonsoc" maxlength="100" name="empresa.nom_empresa" 
                                                    cssClass="form-control required" />
                                                </div>
                                            </div>
                                            <div class="col-md-6">
                                                <div class="form-group">
                                                    <label for="telf">Teléfono :
                                                        <span class="danger">*</span>
                                                    </label>
                                                    <s:textfield type="tel" maxlength="10" id="telf" cssClass="form-control required" name="empresa.telf_empresa" />
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-md-6">
                                                <div class="form-group">
                                                    <label for="direccion"> Dirección :
                                                        <span class="danger">*</span>
                                                    </label>
                                                    <s:textfield maxlength="100" id="dir" cssClass="form-control required" name="empresa.dir_empresa" />
                                                </div>
                                            </div>
                                            <div class="col-md-6">
                                                <div class="form-group">
                                                    <label for="correo">Correo :
                                                        <span class="danger">*</span>
                                                    </label>
                                                    <s:textfield type="email" maxlength="50" id="email" cssClass="form-control required" name="empresa.email_empresa" />
                                                </div>
                                            </div>
                                        </div>
                                    </section>
                                    <!-- Step 2 -->
                                    <h6>Cobertura Nacional</h6>
                                    <section>
                                        <div class="row">
                                            <div class="col-md-4">
                                                <h3 class="text-center">Departamentos</h3>
                                                <s:hidden name="empresa.cobertura_dep" id="dep" />
                                                <div class="well" style="max-height: 300px;overflow: auto;">
                                                    <ul class="list-group checked-list-box">
                                                    <s:iterator value="response.departamentos">
                                                    	<li class="list-group-item" data-style="button" data-color="success">
                                                           <span class="d-none" id="id"><s:property value="id" /></span>
                                                           <span id="nombre"><s:property value="nombre" /></span>
                                                         </li>
                                                    </s:iterator>
                                                    </ul>
                                                </div>
                                            </div>
                                            <div class="col-md-8 text-left">
                                                <button class="btn btn-outline-info col-xs-12 d-none" id="get-checked-data">Selecccionar Todo</button>
                                                <br>
                                                <div id="departamentos">

                                                </div>
                                            </div>
                                            <!-- <div class="col-md-12 text-right">
                                            	<div id="btnGuardar" class="btn btn-primary">Guardar</div>
                                            </div> -->
                                        </div>
                                    </section>
                                    <!-- Step 3 -->
                                    <h6>Logo</h6>
                                    <section>
                                        <div class="row">
                                            <div class="col-md-12">
                                                <div class="card">
                                                    <div class="card-body">
                                                        <h4 class="card-title">Suba el logo de la empresa</h4>
                                                        <!-- <label for="input-file-now">Your so fresh input file — Default version</label> -->
                                                        <s:file accept="image/*" id="input-file-now" cssClass="dropify" name="empresa.logo" />
                                                        <!-- <input type="file" id="input-file-now" class="dropify"> -->
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-md-12 text-right">
                                            	<div id="btnGuardar" class="btn btn-primary">Guardar</div>
                                            </div>
                                        </div>
                                    </section>
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
	<script src="js/reniec-sunat-js.min.js"></script>
	<script src="js/empresa.js"></script>

</html>
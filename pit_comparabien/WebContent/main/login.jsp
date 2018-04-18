<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<meta charset="utf-8">
	<!-- Tell the browser to be responsive to screen width -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- Favicon icon -->
    <link rel="icon" type="image/png" sizes="16x16" href="../assets/images/favicon.png">
    <title><s:property value="response.titulo" /></title>
    <!-- Hojas de Estilo -->
    <!-- Bootstrap Core CSS -->
    <link href="../assets/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <!-- page css -->
    <link href="../main/css/pages/login-register-lock.css" rel="stylesheet">    
    <!-- Custom CSS -->
    <link href="../main/css/style.css" rel="stylesheet">    
    <!-- You can change the theme colors from here -->
    <link href="../main/css/colors/blue.css" id="theme" rel="stylesheet">
<body>

    <jsp:include page="../layout/preloader.jsp"/>

    <section id="wrapper" class="login-register login-sidebar" style="background-image:url(../assets/images/background/bg-login.jpg);">
        <div class="login-box card">
            <div class="card-body">
                <form class="form-horizontal form-material" id="loginform" action="login" method="post">
                    <a href="javascript:void(0)" class="text-center db"><img src="../assets/images/logo-icon.png" alt="Home" /><br/><img src="../assets/images/logo-text.png" alt="Home" /></a>
                    <div class="form-group m-t-40">
                        <div class="col-xs-12">
                        	<s:textfield id="usuario" maxlength="8" name="usuario" cssClass="form-control" placeholder="ID Usuario" required="true" />
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-xs-12">
                        	<s:password id="clave" name="clave" maxlength="8" cssClass="form-control" placeholder="Contraseña" required="true" />
                        </div>
                    </div>
                    <div class="form-group text-center m-t-20">
                        <div class="col-xs-12">
                            <button class="btn btn-info btn-lg btn-block text-uppercase btn-rounded" type="submit">Ingresar</button>
                        </div>
                    </div>
                    <div class="row">
                    	<div class="col-xs-12 col-sm-12 col-md-12 m-t-10 text-center">
                    		<s:if test="response.mostrar == true">
 								<div class="alert alert-danger"> 
	                    			<i class="ti-user"></i> <s:property value="response.msg" /> 
	                               	<button type="button" class="close" data-dismiss="alert" aria-label="Close"> <span aria-hidden="true">×</span> </button>
                            	</div>
							</s:if>                    		
                    	</div>
                    </div>
                </form>
                
            </div>
        </div>
    </section>
    <!-- All Jquery -->
	<!-- ============================================================== -->
	<script src="../assets/plugins/jquery/jquery.min.js"></script>
	<!-- Bootstrap tether Core JavaScript -->
	<script src="../assets/plugins/bootstrap/js/popper.min.js"></script>
	<script src="../assets/plugins/bootstrap/js/bootstrap.min.js"></script>
    <!--Custom JavaScript -->
    <script type="text/javascript">
        $(function() {
            $(".preloader").fadeOut();
        });
        $(function() {
            $('[data-toggle="tooltip"]').tooltip()
        });
        // ============================================================== 
        // Login and Recover Password 
        // ============================================================== 
        $('#to-recover').on("click", function() {
            $("#loginform").slideUp();
            $("#recoverform").fadeIn();
        });
    </script>
    
</body>

</html>
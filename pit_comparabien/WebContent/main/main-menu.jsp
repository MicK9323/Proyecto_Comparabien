<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html">
<html lang="es">
<jsp:include page="../layout/head.jsp" />

<body class="fix-header card-no-border fix-sidebar">
    <!-- Preloader -->
    <jsp:include page="../layout/preloader.jsp"/>
    
    <div id="main-wrapper">
        <jsp:include page="../layout/topbar.jsp"/>
        <jsp:include page="../layout/sidebar.jsp"/>
        <!-- ============================================================== -->
        <!-- Contenido de la pagina  -->
        <!-- ============================================================== -->
        <div class="page-wrapper">
            <div class="container-fluid">
                <div class="row page-titles">
                    <div class="col-md-5 align-self-center">
                        <h3 class="text-themecolor">Bienvenido: <s:property value="#session.usuario.nom_user"/> </h3>
                    </div>
                </div>
                <div class="jumbotron - fluid">
                    <h1 class="display-3">Hello, world!</h1>
                    <p class="lead">This is a simple hero unit, a simple jumbotron-style component for calling extra attention to featured
                        content or information.</p>
                    <hr class="my-4">
                    <p>It uses utility classes for typography and spacing to space content out within the larger container.</p>
                    <p class="lead">
                        <a class="btn btn-info btn-lg" href="#" role="button">Learn more</a>
                    </p>
                </div>
            </div>
            <jsp:include page="../layout/footer.jsp"/>
        </div>
    </div>
    <jsp:include page="../layout/scripts.jsp"/>
</body>

</html>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<aside class="left-sidebar">
	<!-- Sidebar scroll-->
	<div class="scroll-sidebar">
		<!-- Sidebar navigation-->
		<nav class="sidebar-nav">
			<ul id="sidebarnav">
				<li class="user-profile"><a class="waves-effect waves-dark"
					href="#" aria-expanded="false"> <span class="hide-menu"><s:property
								value="#session.usuario.nom_rol" /></span>
				</a></li>
				<li class="nav-devider"></li>
				<li><a class="has-arrow waves-effect waves-dark" href="#"
					aria-expanded="false"> <i class="mdi mdi-briefcase-check"></i>
						<span class="hide-menu">Empresas</span>
				</a>
					<ul aria-expanded="false" class="collapse">
						<li class=""><s:a action="listaEmpresas">Empresas</s:a></li>
					</ul></li>
				<li><a class="has-arrow waves-effect waves-dark" href="#"
					aria-expanded="false"> <i class="mdi mdi-credit-card"></i> <span
						class="hide-menu">Tarjetas de Crédito</span>
				</a>
					<ul aria-expanded="false" class="collapse">
						<s:iterator value="#session.opcionesTarjetas">
							<li class=""><a
								href="${pageContext.request.contextPath }/main/<s:property value="ruta" />"><s:property
										value="nom_enlace" /></a></li>
						</s:iterator>
					</ul></li>
				<li><a class="has-arrow waves-effect waves-dark" href="#"
					aria-expanded="false"> <i class="mdi mdi-pig"></i> <span
						class="hide-menu">Cuentas de Ahorro</span>
				</a>
					<ul aria-expanded="false" class="collapse">
						<s:iterator value="#session.opcionesAhorros">
							<li class=""><a
								href="${pageContext.request.contextPath }/main/<s:property value="ruta" />"><s:property
										value="nom_enlace" /></a></li>
						</s:iterator>
					</ul></li>
				<li><a class="has-arrow waves-effect waves-dark" href="#"
					aria-expanded="false"> <i class="mdi mdi-cash-usd"></i> <span
						class="hide-menu">Créditos y Préstamos</span>
				</a>
					<ul aria-expanded="false" class="collapse">
						<s:iterator value="#session.opcionesCreditos">
							<li class=""><a
								href="${pageContext.request.contextPath }/main/<s:property value="ruta" />"><s:property
										value="nom_enlace" /></a></li>
						</s:iterator>
					</ul></li>
				<li><a class="has-arrow waves-effect waves-dark" href="#"
					aria-expanded="false"> <i class="fa fa-ambulance"></i> <span
						class="hide-menu">Seguros</span>
				</a>
					<ul aria-expanded="false" class="collapse">
						<s:iterator value="#session.opcionesSeguros">
							<li class=""><a
								href="${pageContext.request.contextPath }/main/<s:property value="ruta" />"><s:property
										value="nom_enlace" /></a></li>
						</s:iterator>
					</ul></li>
				<li><a class="has-arrow waves-effect waves-dark" href="#"
					aria-expanded="false"> <i class="fa fa-rss-square"></i> <span
						class="hide-menu">Comunicaciones</span>
				</a>
					<ul aria-expanded="false" class="collapse">
						<s:iterator value="#session.opcionesComu">
							<li class=""><a
								href="${pageContext.request.contextPath }/main/<s:property value="ruta" />"><s:property
										value="nom_enlace" /></a></li>
						</s:iterator>
					</ul></li>
				<s:if test="#session.usuario.id_rol == 1">
					<li><a class="has-arrow waves-effect waves-dark" href="#"
						aria-expanded="false"> <i class="fa fa-gears"></i> <span
							class="hide-menu">Usuarios y Parámetros</span>
					</a>
						<ul aria-expanded="false" class="collapse">
							<s:iterator value="#session.opcionesAdmin">
								<li class=""><a
									href="${pageContext.request.contextPath }/main/<s:property value="ruta" />"><s:property
											value="nom_enlace" /></a></li>
							</s:iterator>
						</ul></li>
				</s:if>

			</ul>
		</nav>
	</div>
</aside>
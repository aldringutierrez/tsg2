<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Aplicacion de Trajetas de Credito</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
</head>
<body>

	<header>
		<nav class="navbar navbar-expand-md navbar-dark"
			style="background-color: tomato">
			<div>
				<a class="navbar-brand"> Tarjetas de Credito </a>
			</div>

			<ul class="navbar-nav">
				<li><a href="<%=request.getContextPath()%>/list"
					class="nav-link">Clientes</a></li>
			</ul>
		</nav>
	</header>
	<br>
	<div class="container col-md-5">
		<div class="card">
			<div class="card-body">
				<c:if test="${cliente != null}">
					<form action="update" method="post">
				</c:if>
				<c:if test="${cliente == null}">
					<form action="insert" method="post">
				</c:if>

				<caption>
					<h2>
						<c:if test="${cliente != null}">
            			Editar Cliente
            		</c:if>
						<c:if test="${cliente == null}">
            			Nuevo Cliente
            		</c:if>
					</h2>
				</caption>

				<c:if test="${cliente != null}">
					<input type="hidden" name="idCliente" value="<c:out value='${cliente.idCliente}' />" />
				</c:if>

				<fieldset class="form-group">
					<label>Cedula</label> <input type="text"
						value="<c:out value='${cliente.cedula}' />" class="form-control"
						name="cedula" required="required">
				</fieldset>

				<fieldset class="form-group">
					<label>Nombres</label> <input type="text"
						value="<c:out value='${cliente.nombres}' />" class="form-control"
						name="nombres" required="required">
				</fieldset>

				<fieldset class="form-group">
					<label>Apellidos</label> <input type="text"
						value="<c:out value='${cliente.apellidos}' />" class="form-control"
						name="apellidos">
				</fieldset>

				<fieldset class="form-group">
					<label>Direccion</label> <input type="text"
						value="<c:out value='${cliente.direccion}' />" class="form-control"
						name="direccion">
				</fieldset>

				<fieldset class="form-group">
					<label>Telefono</label> <input type="text"
						value="<c:out value='${cliente.telefono}' />" class="form-control"
						name="telefono">
				</fieldset>

				<button type="submit" class="btn btn-success">Save</button>
				</form>
			</div>
		</div>
	</div>
</body>
</html>

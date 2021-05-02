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
				<a  class="navbar-brand"> Administracion de Tarjetas</a>
			</div>
			<ul class="navbar-nav">
				<li>
				</li>
			</ul>
		</nav>
	</header>
	<br>

	<div class="row">
		<div class="container">
			<h3 class="text-center">Lista de Clientes</h3>
			<hr>
			<div class="container text-left">

				<a href="<%=request.getContextPath()%>/new" class="btn btn-success">Nuevo Cliente</a>
			</div>
			<br>
			<table class="table table-bordered">
				<thead>
					<tr>
						<th>ID</th>
						<th>Cedula</th>
						<th>Nombres</th>
						<th>Apellidos</th>
						<th>Direccion</th>
						<th>Telefono</th>
						<th>Operaciones</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="cliente" items="${listCliente}">
						<tr>
							<td><c:out value="${cliente.idCliente}" /></td>
							<td><c:out value="${cliente.cedula}" /></td>
							<td><c:out value="${cliente.nombres}" /></td>
							<td><c:out value="${cliente.apellidos}" /></td>
							<td><c:out value="${cliente.direccion}" /></td>
							<td><c:out value="${cliente.telefono}" /></td>
							<td>
							<a href="edit?id=<c:out value='${cliente.idCliente}' />">Edit</a>
								&nbsp;&nbsp;&nbsp;&nbsp; 
							<a href="delete?idCliente=<c:out value='${cliente.idCliente}' />">Delete</a>
								&nbsp;&nbsp;&nbsp;&nbsp; 
							<a href="tarjetas?id=<c:out value='${cliente.idCliente}' />">Tarjetas</a>
							</td>
						</tr>
					</c:forEach>
					<!-- } -->
				</tbody>

			</table>
		</div>
	</div>
</body>
</html>

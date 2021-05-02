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
				<li><a href="<%=request.getContextPath()%>/list"
					class="nav-link">Clientes</a></li>
			</ul>
		</nav>
	</header>
	<br>
	<div class="row">
		<div class="container">
			<h1 class="text-center">Lista de Tarjetas </h1>
			<h3 class="text-center"> ${existingClient.getNombres()}&nbsp;${existingClient.getApellidos()} </h3>
			<hr>
			<div class="container text-left">
				<a href="<%=request.getContextPath()%>/nuevaTarjeta" class="btn btn-success">Nueva Tarjeta</a>
			</div>
			<br>
			<table class="table table-bordered">
				<thead>
					<tr>
						<th>ID</th>
						<th>Banco</th>
						<th>Numero</th>
						<th>Vencimiento</th>
						<th>Predeterminada</th>
						<th>Actions</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="tarjeta" items="${listTarjeta}">
						<tr>
							<td><c:out value="${tarjeta.idTarjeta}" /></td>
							<td><c:out value="${tarjeta.banco}" /></td>
							<td><c:out value="${tarjeta.numero}" /></td>
							<td><c:out value="${tarjeta.fecVence}" /></td>
							<td><input type="checkbox" name="selected" value="${tarjeta.predeterminada}" 
								<c:if test="${tarjeta.predeterminada=='S'}">checked=checked</c:if>
							></td>
							
							<td>
							<a href="marcarTarjeta?idTarjeta=<c:out value='${tarjeta.idTarjeta}' />">Marcar</a>
								&nbsp;&nbsp;&nbsp;&nbsp; 
							<a href="deleteTarjeta?idTarjeta=<c:out value='${tarjeta.idTarjeta}' />">Delete</a>
							</td>
						</tr>
					</c:forEach>
				</tbody>

			</table>
		</div>
	</div>
</body>
</html>

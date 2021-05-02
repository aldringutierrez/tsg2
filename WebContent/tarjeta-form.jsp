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
					class="nav-link">Tarjetas</a></li>
			</ul>
		</nav>
	</header>
	<br>
	<div class="container col-md-5">
		<div class="card">
			<div class="card-body">
				<c:if test="${tarjeta != null}">
					<form action="updateTarjeta" method="post">
				</c:if>
				<c:if test="${tarjeta == null}">
					<form action="insertTarjeta" method="post">
				</c:if>

				<caption>
					<h2>
						<c:if test="${tarjeta != null}">
            			Editar Tarjeta
            		</c:if>
						<c:if test="${tarjeta == null}">
            			Nuevo Tarjeta
            		</c:if>
					</h2>
				</caption>

				<c:if test="${tarjeta != null}">
					<input type="hidden" name="idTarjeta" value="<c:out value='${tarjeta.idTarjeta}' />" />
				</c:if>

				<fieldset class="form-group">
					<label>Banco</label> <input type="text"
						value="<c:out value='${tarjeta.banco}' />" class="form-control"
						name="banco" required="required">
				</fieldset>

				<fieldset class="form-group">
					<label>Numero</label> <input type="text"
						value="<c:out value='${tarjeta.numero}' />" class="form-control"
						name="numero" required="required">
				</fieldset>

				<fieldset class="form-group">
					<label>Vencimiento</label> <input type="Date"
						value="<c:out value='${tarjeta.fecVence}' />" class="form-control"
						name="vencimiento">
				</fieldset>

				<button type="submit" class="btn btn-success">Save</button>
				</form>
			</div>
		</div>
	</div>
</body>
</html>

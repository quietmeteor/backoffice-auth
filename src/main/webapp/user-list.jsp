<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<!-- saved from url=(0051)http://localhost:8080/prenotazioni-web/list/risorse -->
<html lang="en">
<head>
<meta http-equiv="Content-Type"
	content="text/html; charset=windows-1252">

<title>Lista Utenti</title>
<!--<base href="/">-->
<base href=".">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="icon" type="image/x-icon"
	href="http://localhost:8080/favicon.ico">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css">
<link rel='stylesheet'
	href='https://use.fontawesome.com/releases/v5.3.1/css/all.css'>
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>

<style>
.bd-placeholder-img {
	font-size: 1.125rem;
	text-anchor: middle;
	-webkit-user-select: none;
	-moz-user-select: none;
	user-select: none;
}

@media ( min-width : 768px) {
	.bd-placeholder-img-lg {
		font-size: 3.5rem;
	}
}

.b-example-divider {
	height: 3rem;
	background-color: rgba(0, 0, 0, .1);
	border: solid rgba(0, 0, 0, .15);
	border-width: 1px 0;
	box-shadow: inset 0 .5em 1.5em rgba(0, 0, 0, .1), inset 0 .125em .5em
		rgba(0, 0, 0, .15);
}

.b-example-vr {
	flex-shrink: 0;
	width: 1.5rem;
	height: 100vh;
}

.bi {
	vertical-align: -.125em;
	fill: currentColor;
}

.nav-scroller {
	position: relative;
	z-index: 2;
	height: 2.75rem;
	overflow-y: hidden;
}

.nav-scroller .nav {
	display: flex;
	flex-wrap: nowrap;
	padding-bottom: 1rem;
	margin-top: -1px;
	overflow-x: auto;
	text-align: center;
	white-space: nowrap;
	-webkit-overflow-scrolling: touch;
}
</style>


</head>
<body>

	<nav class="navbar navbar-dark sticky-top bg-dark flex-md-nowrap p-0">
		<a href="http://localhost:8080/prenotazioni-web/home"
			class="navbar-brand col-sm-3 col-md-2 mr-0">App Prenotazione
			Risorse</a>
		<ul class="navbar-nav px-3">
			<li class="nav-item text-nowrap"><a
				href="http://localhost:8080/#" class="nav-link">Nome Utente
					Collegato</a></li>
		</ul>
	</nav>
	<div class="container-fluid">
		<div class="row">
			<nav class="col-md-2 d-none d-md-block bg-light sidebar">
				<div class="sidebar-sticky">
					<ul class="nav flex-column">
						<li class="nav-item"><a
							href="http://localhost:8080/prenotazioni-web/home"
							class="nav-link "> <span></span> Prenotazioni
						</a></li>
						<li class="nav-item"><a
							href="http://localhost:8080/prenotazioni-web/list/risorse"
							class="nav-link active"> <span></span> Lista utenti
						</a></li>
						<li class="nav-item"><a
							href="http://localhost:8080/prenotazioni-web/list/slot-prenotazioni"
							class="nav-link "> <span></span> Lista gruppi
						</a></li>

					</ul>
				</div>
			</nav>
			<main role="main" class="col-md-9 ml-sm-auto col-lg-10 pt-3 px-4">



				<form id="form1"
					action="http://localhost:8080/prenotazioni-web/delete/risorsa"
					method="POST">
					<input type="hidden" id="id" name="id">
				</form>
				<div class="container">
					<div class="row">
						<div class="col-11">
							<button type="button" class="btn btn-success" onclick="create()">
								<i class="fas fa-plus"></i>
							</button>
							<table class="table table-bordered">
								<thead>
									<tr>
										<th scope="col">ID</th>

										<th scope="col">EMAIL</th>

										<th scope="col">NOME</th>

										<th scope="col">COGNOME</th>

										<th scope="col">USERNAME</th>

										<th scope="col">PASSWORD</th>

										<th scope="col">GROUP</th>

										<th scope="col">LAST LOGIN</th>

										<th scope="col">DATE MIDIFIED PASSWORD</th>

										<th scope="col">VERIFIED?</th>

										<th scope="col">DELETED?</th>

										<td>&nbsp;</td>
									</tr>
								</thead>
								<tbody>


									<c:forEach items="${userList}" var="user">
										<tr>
											<td><c:out value="${user.id}"></c:out></td>
											<td><c:out value="${user.email}"></c:out></td>
											<td><c:out value="${user.name}"></c:out></td>
											<td><c:out value="${user.lastName}"></c:out></td>
											<td><c:out value="${user.username}"></c:out></td>
											<td><c:out value="${user.password}"></c:out></td>
											<td><c:out value="${user.userGroup}"></c:out></td>
											<td><c:out value="${user.lastLogin}"></c:out></td>
											<td><c:out value="${user.dateModifiedPass}"></c:out></td>
											<!--  <td><c:out value="${user.isVerified}"></c:out></td>
											<td><c:out value="${user.isDeleted}"></c:out></td>-->

											<td>
												<button type="button" class="btn btn-primary"
													onclick="detail(4)">
													<i class="far fa-eye"></i>
												</button>
												<button type="button" class="btn btn-success"
													onclick="edit(4)">
													<i class="fas fa-edit"></i>
												</button>
												<button type="button" class="btn btn-danger"
													onclick="deleteEntity(4)">
													<i class="far fa-trash-alt"></i>
												</button>
											</td>
										</tr>
									</c:forEach>

								</tbody>
							</table>

						</div>
					</div>
				</div>

				<script>
					function detail(id) {

						window.location.href = "http://localhost:8080/prenotazioni-web/detail/risorsa/"
								+ id;
					}
					function edit(id) {

						window.location.href = "http://localhost:8080/prenotazioni-web/edit/risorsa/"
								+ id;
					}
					function create() {
						window.location.href = "http://localhost:8080/prenotazioni-web/edit/risorsa/0";
					}

					function deleteEntity(id) {

						$('#id').val(id);
						$('#form1').submit();
					}
				</script>



			</main>
		</div>
	</div>


</body>
</html>
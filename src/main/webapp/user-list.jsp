<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<!-- saved from url=(0051)http://localhost:8080/prenotazioni-web/list/risorse -->
<html lang="en">
<head>
<meta http-equiv="Content-Type"
	content="text/html; charset=windows-1252">

<title>Prenotazione risorse</title>
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
body {
	font-size: .875rem;
}

.feather {
	width: 16px;
	height: 16px;
	vertical-align: text-bottom;
}

.sidebar {
	position: fixed;
	top: 0;
	bottom: 0;
	left: 0;
	z-index: 100;
	padding: 0;
	box-shadow: inset -1px 0 0 rgba(0, 0, 0, .1);
}

.sidebar-sticky {
	position: sticky;
	top: 48px;
	height: calc(100vh - 48px);
	padding-top: .5rem;
	overflow-x: hidden;
	overflow-y: auto;
}

.sidebar   .nav-link {
	font-weight: 500;
	color: #333;
}

.sidebar   .nav-link   .feather {
	margin-right: 4px;
	color: #999;
}

.sidebar   .nav-link.active {
	color: #007bff;
}

.sidebar   .nav-link:hover   .feather, .sidebar   .nav-link.active   .feather
	{
	color: inherit;
}

.sidebar-heading {
	font-size: .75rem;
	text-transform: uppercase;
}

.navbar-brand {
	padding-top: .75rem;
	padding-bottom: .75rem;
	font-size: 1rem;
	background-color: rgba(0, 0, 0, .25);
	box-shadow: inset -1px 0 0 rgba(0, 0, 0, .25);
}

.navbar   .form-control {
	padding: .75rem 1rem;
	border-width: 0;
	border-radius: 0;
}

.form-control-dark {
	color: #fff;
	background-color: rgba(255, 255, 255, .1);
	border-color: rgba(255, 255, 255, .1);
}

.form-control-dark:focus {
	border-color: transparent;
	box-shadow: 0 0 0 3px rgba(255, 255, 255, .25);
}

.border-top {
	border-top: 1px solid #e5e5e5;
}

.border-bottom {
	border-bottom: 1px solid #e5e5e5;
}
</style>
<style>
.day-container {
	border: 0.5px solid gray;
	border-collapse: collapse;
	padding: 3px;
	height: 150px;
	font-size: 12px;
}

.day-container   span {
	border-bottom: 1px solid gray;
}

.day-container-h {
	border: 0.5px solid gray;
	padding: 3px;
}

.prenotazione {
	background-color: orange;
	margin-top: 2px;
	margin-bottom: 3px;
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
						<div class="col-12">
							<button type="button" class="btn btn-success" onclick="create()">
								<i class="fas fa-plus"></i>
							</button>
							<table class="table table-bordered">
								<thead>
									<tr>

										<th scope="col">ID USER</th>

										<th scope="col">EMAIL</th>

										<th scope="col">NOME</th>

										<th scope="col">COGNOME</th>

										<th scope="col">USERNAME</th>

										<th scope="col">PASSWORD</th>

										<th scope="col">GROUP</th>

										<th scope="col">LAST LOGIN</th>

										<th scope="col">DATE MIDIFIED PASSWORD</th>

										<th scope="col">IF_VERIFIED</th>

										<td>&nbsp;</td>
									</tr>
								</thead>
								<tbody>

									<tr>

										<td><c:out value=""></c:out></td>



										<td></td>



										<td></td>



										<td></td>



										<td></td>



										<td></td>



										<td></td>



										<td>Si</td>



										<td>No</td>


										<td></td>
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

									<tr>




										<td>Risorsa 2</td>



										<td></td>



										<td></td>



										<td></td>



										<td></td>



										<td></td>



										<td></td>



										<td>Si</td>



										<td>No</td>


										<td>
											<button type="button" class="btn btn-primary"
												onclick="detail(5)">
												<i class="far fa-eye"></i>
											</button>
											<button type="button" class="btn btn-success"
												onclick="edit(5)">
												<i class="fas fa-edit"></i>
											</button>
											<button type="button" class="btn btn-danger"
												onclick="deleteEntity(5)">
												<i class="far fa-trash-alt"></i>
											</button>
										</td>
									</tr>

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
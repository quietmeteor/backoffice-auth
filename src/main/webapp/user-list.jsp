<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
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
						<div class="col-4 text-center">
							<button type="button" class="btn btn-success" onclick="create()">
								<i class="fas fa-plus"></i>
							</button>
							<table class="table table-bordered table-hover">
								<thead>
									<tr>
										<th scope="col" class="align-text-top">ID</th>

										<th scope="col" class="align-text-top">EMAIL</th>

										<th scope="col" class="align-text-top">NOME</th>

										<th scope="col" class="align-text-top">COGNOME</th>

										<th scope="col" class="align-text-top">USERNAME</th>

										<th scope="col" class="align-text-top">PASSWORD</th>

										<th scope="col" class="align-text-top">GROUP</th>

										<th scope="col" class="align-text-top">LAST LOGIN</th>

										<th scope="col" class="align-text-top">DATE MIDIFIED
											PASSWORD</th>

										<th scope="col" class="align-text-top">VERIFIED?</th>

										<th scope="col" class="align-text-top">DELETED?</th>

										<td>&nbsp;</td>
									</tr>
								</thead>
								<tbody>


									<c:forEach items="${userList}" var="user">
										
											<tr>
												<td><c:out value="${fn:trim(user.id)}"></c:out></td>
												<td><c:out value="${fn:trim(user.email)}"></c:out></td>
												<td><c:out value="${fn:trim(user.name)}"></c:out></td>
												<td><c:out value="${fn:trim(user.lastName)}"></c:out></td>
												<td><c:out value="${fn:trim(user.username)}"></c:out></td>
												<td><c:out value="${fn:trim(user.password)}"></c:out></td>
												<td><c:out value="${fn:trim(user.groupName)}"></c:out></td>
												<td><c:out value="${fn:trim(user.lastLogin)}"></c:out></td>
												<td><c:out value="${fn:trim(user.dateModifiedPass)}"></c:out></td>
												<td><c:out value="${fn:trim(user.verified)}"></c:out></td>
												<td><c:out value="${fn:trim(user.deleted)}"></c:out></td>

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
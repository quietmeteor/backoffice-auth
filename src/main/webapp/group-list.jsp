<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<!-- saved from url=(0051)http://localhost:8080/prenotazioni-web/list/risorse -->
<html lang="en">
<head>
<meta http-equiv="Content-Type"
	content="text/html; charset=windows-1252">

<title>Lista Gruppi</title>
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
							href="http://localhost:8080/Backoffice/user-list"
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
	
	


	
				<div class="container">
					<div class="row">
						<div class="col-12">
							<button type="button" class="btn btn-success" onclick="create()">
								<i class="fas fa-plus"></i>
							</button>
							<table class="table table-bordered">
								<thead>
									<tr>

										<th scope="col">Id</th>

										<th scope="col">Group Name</th>

										<th scope="col">Roles</th>

										<th scope="col">Permissions</th>

										<th scope="col">Enabled</th>

										<th scope="col">Creation User</</th>

										<th scope="col">Creation Time</th>

										<th scope="col">Update User</th>

										<th scope="col">Update Time</th>

										<td>&nbsp;</td>
									</tr>
								</thead>
								<tbody>

									<c:forEach items="${groupList}" var="group">
										<tr>
											<td><c:out value="${group.id}" /></td>
											<td><c:out value="${group.groupName}" /></td>
											<td><c:out value="${group.roles}" /></td>
											<td><c:out value="${group.permissions}" /></td>
											<td><c:out value="${group.enabled}" /></td>
											<td><c:out value="${group.creationUser}" /></td>
											<td><c:out value="${group.creationTime}" /></td>
											<td><c:out value="${group.updateUser}" /></td>
											<td><c:out value="${group.updateTime}" /></td>

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

				



			</main>
		</div>
	</div>


</body>
</html>
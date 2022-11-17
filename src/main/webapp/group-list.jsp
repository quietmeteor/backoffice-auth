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

<title>Lista Gruppi</title>
<!--<base href="/">-->
<base href=".">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="icon" type="image/x-icon"
	href="http://localhost:8080/favicon.ico">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">
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
							href="http://localhost:8080//Backoffice/group-list"
							class="nav-link "> <span></span> Lista gruppi
						</a></li>

					</ul>
				</div>
			</nav>
			<main role="main" class="col-md-9 ml-sm-auto col-lg-10 pt-3 px-4">





				<div class="container">
								<button type="button" class="btn btn-success" onclick="create()">Create
					Group</button>
					<div class="row">

						<div class="col-4 text-center">
							<table class="table table-bordered table-hover table-striped table-sm">
								<thead>
									<tr>

										<th scope="col" class="align-text-top">Id</th>

										<th scope="col" class="align-text-top">Group Name</th>

										<th scope="col" class="align-text-top">Roles</th>

										<th scope="col" class="align-text-top">Permissions</th>

										<th scope="col" class="align-text-top">Enabled</th>

										<th scope="col" class="align-text-top">Creation User</</th>

										<th scope="col" class="align-text-top">Creation Time</th>

										<th scope="col" class="align-text-top">Update User</th>

										<th scope="col" class="align-text-top">Update Time</th>

										<td>&nbsp;</td>
									</tr>
								</thead>
								<tbody>

									<c:forEach items="${groupList}" var="group">
										<tr>
											<td><c:out value="${fn:trim(group.id)}" /></td>
											<td><c:out value="${fn:trim(group.groupName)}" /></td>
											<td><c:out value="${fn:trim(group.roles)}" /></td>
											<td><c:out value="${fn:trim(group.permissions)}" /></td>
											<td><c:out value="${fn:trim(group.enabled)}" /></td>
											<td><c:out value="${fn:trim(group.creationUser)}" /></td>
											<td><c:out value="${fn:trim(group.creationTime)}" /></td>
											<td><c:out value="${fn:trim(group.updateUser)}" /></td>
											<td><c:out value="${fn:trim(group.updateTime)}" /></td>

											<td>
												<button type="button" class="btn btn-primary"
													onclick="detail(${group.id})">
													<i class="far fa-eye"></i>
												</button>
												<button type="button" class="btn btn-success"
													onclick="edit(${group.id})">
													<i class="fas fa-edit"></i>
												</button>
												<button type="button" class="btn btn-danger"
													onclick="deleteEntity(${group.id})">
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


	<script type="text/javascript">
		function detail(id){
			window.location.href="/Backoffice/group-detail?id="+id;
		}
	
		function edit(id) {

		}

		function create() {

		}

		function deleteEntity() {

		}
	</script>

</body>
</html>
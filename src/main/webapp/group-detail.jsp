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

<title>Gruppo dettagio</title>
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
							href="http://localhost:8080/Backoffice/home" class="nav-link ">
								<span></span> Prenotazioni
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


				<form action="group-detail" method="POST">

					<div class="text-danger">
						<c:out value="${error_message}" />
					</div>
					<div class="form-group row">
						<label for="groupName" class="col-sm-2 col-form-label">Group
							Name</label>
						<div class="col-sm-10">
							<input type="text" id="groupName" name="groupName"
								value="${group.groupName}"></br>
						</div>
					</div>

					<div class="form-group row">
						<label for="roles" class="col-sm-2 col-form-label">Roles</label>
						<div class="col-sm-10">
							<input type="text" id="roles" name="roles"
								class="col-sm-2 col-form-label" value="${group.roles}"></br>
						</div>
					</div>

					<div class="form-group row">
						<label for="permissions" class="col-sm-2 col-form-label">Permissions</label>
						<div class="col-sm-10">
							<input type="text" id="permission" name="permissions"
								value="${group.permissions}"></br>
						</div>
					</div>

					<div class="form-group row">
						<label for="enabled" class="col-sm-2 col-form-label">Enabled</label>
						<div class="col-sm-10">
							<input type="text" id="enabled" name="enabled"
								value="${group.enabled}"></br>
						</div>
					</div>

					<div class="form-group row">
						<label for="creationTime" class="col-sm-2 col-form-label">Creation Time</label>
						<div class="col-sm-10">
							<input type="datetime-local" id="creationTime"
								name="creationTime" value="${group.creationTime}"></br>
						</div>
					</div>
					<div class="form-group row">
						<label for="creationUser" class="col-sm-2 col-form-label">Creation User</label>
						<div class="col-sm-10">
							<input type="text" id="creationUser" name="creationUser"
								value="${group.creationUser}"></br>
						</div>
					</div>
					<div class="form-group row">
						<label for="updateTime" class="col-sm-2 col-form-label">Update Time</label>
						<div class="col-sm-10">
							<input type="datetime-local" id="updateTime" name="updateTime"
								value="${group.updateTime}"></br>
						</div>
					</div>
					<div class="form-group row">
						<label for="updateUser" class="col-sm-2 col-form-label">Update User</label>
						<div class="col-sm-10">
							<input type="text" id="updateUser" name="updateUser"
								value="${group.updateUser}"></br>
						</div>
					</div>
					<input class="btn btn-primary" type="submit" value="modifica"></br>
				</form>




			</main>
		</div>
	</div>


	<script type="text/javascript">
		function view(id) {
			window.location.href = "/Backoffice/group-detail?id=" + id;
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
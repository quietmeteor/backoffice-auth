<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>
<%@taglib prefix="pt" uri="/WEB-INF/tlds/pagetemplate.tld"%>
<head>
<meta http-equiv="Content-Type"
	content="text/html; charset=windows-1252">

<title>Dettaglio del gruppo</title>
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


<pt:page>
	<div class="overflow-scroll" style="height: 100vh">



				<div class="container-fluid">



							<form action="group-detail" method="GET">

								<div class="text-danger">
									<c:out value="${errorMessage}" />
								</div>
								<div class="form-group row">
									<label for="groupName" class="col-sm-2 col-form-label">Nome
										Gruppo</label>
									<div class="col-sm-10">
										<input type="text" id="groupName" name="groupName"
											value="${group.groupName}" disabled readonly></br>
									</div>
								</div>

								<div class="form-group row">
									<label for="roles" class="col-sm-2 col-form-label">Ruoli</label>
									<div class="col-sm-10">
										<textarea type="text" id="roles" name="roles"
											value="${group.roles}" disabled readonly>"${group.roles}"</textarea>
										</br>
									</div>
								</div>

								<div class="form-group row">
									<label for="permissions" class="col-sm-2 col-form-label">Permessi</label>
									<div class="col-sm-10">
										<textarea type="text" id="permission" name="permissions"
											value="${group.permissions}" disabled readonly>"${group.permissions}"</textarea>
										</br>
									</div>
								</div>

								<div class="form-group row">
									<label for="enabled" class="col-sm-2 col-form-label">Attivo</label>
									<div class="col-sm-10">
										<input type="text" id="enabled" name="enabled"
											value="${group.enabled}" disabled readonly></br>
									</div>
								</div>

								<div class="form-group row">
									<label for="creationTime" class="col-sm-2 col-form-label">Data
										Creazione </label>
									<div class="col-sm-10">
										<input type="datetime-local" id="creationTime"
											name="creationTime" value="${group.creationTime}" disabled
											readonly></br>
									</div>
								</div>
								<div class="form-group row">
									<label for="creationUser" class="col-sm-2 col-form-label">User
										Creazione </label>
									<div class="col-sm-10">
										<input type="text" id="creationUser" name="creationUser"
											value="${group.creationUser}" disabled readonly></br>
									</div>
								</div>
								<div class="form-group row">
									<label for="updateTime" class="col-sm-2 col-form-label">Data
										Aggiornamento </label>
									<div class="col-sm-10">
										<input type="datetime-local" id="updateTime" name="updateTime"
											value="${group.updateTime}" disabled readonly></br>
									</div>
								</div>
								<div class="form-group row">
									<label for="updateUser" class="col-sm-2 col-form-label">User
										Aggiornamento </label>
									<div class="col-sm-10">
										<input type="text" id="updateUser" name="updateUser"
											value="${group.updateUser}" disabled readonly></br>
									</div>
								</div>


							</form>


					</div>
				</div>


				<script type="text/javascript">
					
				</script>



</pt:page>
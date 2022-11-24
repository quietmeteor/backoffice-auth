<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>
<%@taglib prefix="pt" uri="/WEB-INF/tlds/pagetemplate.tld"%>

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi"
	crossorigin="anonymous" />

<link rel='stylesheet'
	href='https://use.fontawesome.com/releases/v5.3.1/css/all.css'>
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>

</head>


<pt:page>
	<div class="row mb-3">
		<div class="col-1 d-flex flex-row">

			<body>

				<div class="container-fluid">
					<div class="row">

						<main role="main" class="col-md-9 ml-sm-auto col-lg-10 pt-3 px-4">


							<form action="group-detail" method="POST">

								<div class="text-danger">
									<c:out value="${error_message}" />
								</div>
								<div class="form-group row">
									<label for="groupName"  class="col-sm-2 col-form-label" >Nome Gruppo</label>
									<div class="col-sm-10">
										<input type="text"  id="groupName" name="groupName" 
											value="${group.groupName}" disabled readonly></br>
									</div>
								</div>

								<div class="form-group row">
									<label for="roles" class="col-sm-2 col-form-label">Ruoli</label>
									<div class="col-sm-10">
										<textarea type="text" id="roles" name="roles"
											value="${group.roles}" disabled readonly>"${group.roles}"</textarea> </br>
									</div>
								</div>

								<div class="form-group row">
									<label for="permissions" class="col-sm-2 col-form-label">Permessi</label>
									<div class="col-sm-10">
										<textarea type="text" id="permission" name="permissions"
											value="${group.permissions}" disabled readonly>"${group.permissions}"</textarea></br>
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
											name="creationTime" value="${group.creationTime}" disabled readonly></br>
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
								<input class="btn btn-primary" type="submit" value="modifica"></br>
								
								
							</form>




						</main>
					</div>
				</div>


				<script type="text/javascript">

				</script>

			</body>


		</div>
	</div>
</pt:page>
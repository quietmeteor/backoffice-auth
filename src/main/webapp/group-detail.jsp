<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>
<%@taglib prefix="pt" uri="/WEB-INF/tlds/pagetemplate.tld"%>
<pt:page>
	<div class="row mb-3">
		<div class="col-1 d-flex flex-row">

			<body>

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
									<label for="groupName" class="col-sm-2 col-form-label">Nome
										Gruppo</label>
									<div class="col-sm-10">
										<input type="text" id="groupName" name="groupName"
											value="${group.groupName}"></br>
									</div>
								</div>

								<div class="form-group row">
									<label for="roles" class="col-sm-2 col-form-label">Ruoli</label>
									<div class="col-sm-10">
										<input type="text" id="roles" name="roles"
											value="${group.roles}"></br>
									</div>
								</div>

								<div class="form-group row">
									<label for="permissions" class="col-sm-2 col-form-label">Permessi</label>
									<div class="col-sm-10">
										<input type="text" id="permission" name="permissions"
											value="${group.permissions}"></br>
									</div>
								</div>

								<div class="form-group row">
									<label for="enabled" class="col-sm-2 col-form-label">Attivo</label>
									<div class="col-sm-10">
										<input type="text" id="enabled" name="enabled"
											value="${group.enabled}"></br>
									</div>
								</div>

								<div class="form-group row">
									<label for="creationTime" class="col-sm-2 col-form-label">Data
										Creazione </label>
									<div class="col-sm-10">
										<input type="datetime-local" id="creationTime"
											name="creationTime" value="${group.creationTime}"></br>
									</div>
								</div>
								<div class="form-group row">
									<label for="creationUser" class="col-sm-2 col-form-label">User
										Creazione </label>
									<div class="col-sm-10">
										<input type="text" id="creationUser" name="creationUser"
											value="${group.creationUser}"></br>
									</div>
								</div>
								<div class="form-group row">
									<label for="updateTime" class="col-sm-2 col-form-label">Data
										Aggiornamento </label>
									<div class="col-sm-10">
										<input type="datetime-local" id="updateTime" name="updateTime"
											value="${group.updateTime}"></br>
									</div>
								</div>
								<div class="form-group row">
									<label for="updateUser" class="col-sm-2 col-form-label">User
										Aggiornamento </label>
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
						window.location.href = "/Backoffice/group-detail?id="
								+ id;
					}

					function edit(id) {

					}

					function create() {

					}

					function deleteEntity() {

					}
				</script>

			</body>


		</div>
	</div>
</pt:page>
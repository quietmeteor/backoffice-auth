<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>
<%@taglib prefix="pt" uri="/WEB-INF/tlds/pagetemplate.tld"%>
<pt:page>

	<div class="overflow-scroll" style="heigh: 100vh">

		<div class="row mb-3">
			<div class="col-1 d-flex flex-row">

				<body>

					<div class="container-fluid">
						<div class="row">

							<main role="main" class="col-md-9 ml-sm-auto col-lg-10 pt-3 px-4">


								<form action="group-list" method="POST">

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
										<label for="updateUser" class="col-sm-2 col-form-label">User
											Aggiornamento </label>
										<div class="col-sm-10">
											<input type="text" id="updateUser" name="updateUser"
												value="${group.updateUser}"></br>
										</div>
									</div>
									<input class="btn btn-success" type="submit" value="Modifica"></br>
								</form>




							</main>
						</div>
					</div>


					<script type="text/javascript">
						
					</script>

				</body>


			</div>
		</div>
	</div>
</pt:page>
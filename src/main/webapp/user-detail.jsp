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
	<div class="oveflow-scroll" style="height: 100vh">
		<div class="row mb-3">
			<div class="col-1 d-flex flex-row">

				<body>

					<div class="container-fluid">
						<div class="row">

							<main role="main" class="col-md-9 ml-sm-auto col-lg-10 pt-3 px-4">


								<form action="user-detail" method="POST">

									<div class="text-danger">
										<c:out value="${error_message}" />
									</div>
									<div class="form-user row">
										<label for="userName" class="col-sm-2 col-form-label">Nome
											Utente</label>
										<div class="col-sm-10">
											<input type="text" id="userName" name="userName"
												value="${user.name}" disabled readonly></br>
										</div>
									</div>

									<div class="form-user row">
										<label for="roles" class="col-sm-2 col-form-label">Cognome</label>
										<div class="col-sm-10">
											<input type="text" id="lastName" name="lastName"
												value="${user.lastName}" disabled readonly></input> </br>
										</div>
									</div>

									<div class="form-user row">
										<label for="permissions" class="col-sm-2 col-form-label">Email</label>
										<div class="col-sm-10">
											<input type="text" id="email" name="email"
												value="${user.email}" disabled readonly></input></br>
										</div>
									</div>

									<div class="form-user row">
										<label for="enabled" class="col-sm-2 col-form-label">Username</label>
										<div class="col-sm-10">
											<input type="text" id="username" name="username"
												value="${user.username}" disabled readonly></br>
										</div>
									</div>

									<div class="form-user row">
										<label for="enabled" class="col-sm-2 col-form-label">Password</label>
										<div class="col-sm-10">
											<input type="text" id="password" name="password"
												value="${user.password}" disabled readonly></br>
										</div>
									</div>

									<div class="form-user row">
										<label for="creationTime" class="col-sm-2 col-form-label">Ultimo
											accesso</label>
										<div class="col-sm-10">
											<input type="datetime-local" id="lastLogin" name="lastLogin"
												value="${user.lastLogin}" disabled readonly></br>
										</div>
									</div>

									<div class="form-user row">
										<label for="creationTime" class="col-sm-2 col-form-label">Data
											modifica password</label>
										<div class="col-sm-10">
											<input type="datetime-local" id="dateModifiedPass"
												name="dateModifiedPass" value="${user.dateModifiedPass}"
												disabled readonly></br>
										</div>
									</div>

									<div class="form-user row">
										<label for="creationTime" class="col-sm-2 col-form-label">Gruppo
											di appartenenza</label>
										<div class="col-sm-10">
											<input type="text" id="groupName" name="groupName"
												value="${user.groupName}" disabled readonly></br>
										</div>
									</div>

									<div class="form-user row">
										<label for="creationTime" class="col-sm-2 col-form-label">Utente
											verificato</label>
										<div class="col-sm-10">
											<input type="text" id="verified" name="verified"
												value="${user.verified}" disabled readonly></br>
										</div>
									</div>

									<div class="form-user row">
										<label for="creationTime" class="col-sm-2 col-form-label">Utente
											eliminato</label>
										<div class="col-sm-10">
											<input type="text" id="deleted" name="deleted"
												value="${user.deleted}" disabled readonly></br>
										</div>
									</div>
								</form>




							</main>
						</div>
					</div>


					<script type="text/javascript">
						function view(id) {

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
	</div>
</pt:page>
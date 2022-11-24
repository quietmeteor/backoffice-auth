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


							<form action="group-edit" method="POST">

								<div class="text-danger">
									<c:out value="${error_message}" />
								</div>
								<div class="form-group row">
									<label for="groupName" class="col-sm-2 col-form-label">Nome
										Gruppo</label>
									<div class="col-sm-10">
										<input type="text" id="groupName" name="groupName"
											placeholder="Inserire nome gruppo"></br>
									</div>
								</div>

								<div class="form-group row">
									<label for="roles" class="col-sm-2 col-form-label">Ruoli</label>
									<div class="col-sm-10">
										<textarea type="text" id="roles" name="roles"
											placeholder="Sciegliere ruoli"></textarea>
										</br>
									</div>
								</div>

								<div class="form-group row">
									<label for="permissions" class="col-sm-2 col-form-label">Permessi</label>
									<div class="col-sm-10">
										<textarea type="text" id="permission" name="permissions"
											placeholder="Sciegliere permessi"></textarea>
										</br>
									</div>
								</div>

								<div class="form-group row">
									<label for="enabled" class="col-sm-2 col-form-label">Attivo</label>
									<div class="col-sm-10">
										<input class="form-check-input" type="checkbox" id="enabled"
											name="enabled"></br>
									</div>
								</div>

								<div class="form-group row">
									<label for="creationUser" class="col-sm-2 col-form-label">User
										Creazione </label>
									<div class="col-sm-10">
										<input type="text" id="creationUser" name="creationUser"
											placeholder="User creazione"></br>
									</div>
								</div>

								<input class="btn btn-primary" type="submit" value="Crea"></br>
								
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
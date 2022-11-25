<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>
<%@taglib prefix="pt" uri="/WEB-INF/tlds/pagetemplate.tld"%>

<head>
<meta http-equiv="Content-Type"
	content="text/html; charset=windows-1252">

<title>Creazione Gruppo</title>
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


	<form action="group-list" method="POST">

		<div class="text-danger">
			<c:out value="${errorMessage}" />
		</div>
		<div class="invisible">
			<input type="text" id="action" name="action" value="create">
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
					name="enabled" value="${x}"></br>
				
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

		<input class="btn btn-success" type="submit" value="Crea"
			onclick="enabled()"></br>

	</form>


	<script type="text/javascript">
		function enabled() {

			var x = document.getElementById("enabled").checked;
			console.log(x);

		}
	</script>



</pt:page>
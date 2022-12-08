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

<!--- Select 2 links --->
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.4/css/select2.min.css"
	rel="stylesheet" />
<script src="//code.jquery.com/jquery-2.2.4.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.4/js/select2.min.js"></script>

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
		<div class="multi-selector">
			<label for="roles" class="col-sm-2 col-form-label">Ruoli </label> <select
				class="multiple-roles" name="roles" id="roles"
				multiple="multiple" style="width: 16%">
				<optgroup label="Shortcut roles">
					<option value="SUPER_USER">SUPER_USER</option>
					<option value="ADMIN">ADMIN</option>
					<option value="MODERATOR">MODERATOR</option>
					<option value="">USER</option>
				</optgroup>
				<optgroup label="Risorse">
					<option value="ROLE_CREATE_RISORSE">ROLE_CREATE_RISORSE</option>
					<option value="ROLE_READ_RISORSE">ROLE_READ_RISORSE</option>
					<option value="ROLE_UPDATE_RISORSE">ROLE_UPDATE_RISORSE</option>
					<option value="ROLE_DELETE_RISORSE">ROLE_DELETE_RISORSE</option>
				</optgroup>
				<optgroup label="Utente">
					<option value="ROLE_CREATE_USER">ROLE_CREATE_USER</option>
					<option value="ROLE_READ_USER">ROLE_READ_USER</option>
					<option value="ROLE_UPDATE_USER">ROLE_UPDATE_USER</option>
					<option value="ROLE_DELETE_USER">ROLE_DELETE_USER</option>
				</optgroup>
				<optgroup label="Slot">
					<option value="ROLE_CREATE_SLOT">ROLE_CREATE_SLOT</option>
					<option value="ROLE_READ_SLOT">ROLE_READ_SLOT</option>
					<option value="ROLE_UPDATE_SLOT">ROLE_UPDATE_SLOT</option>
					<option value="ROLE_DELETE_SLOT">ROLE_DELETE_SLOT</option>
				</optgroup>
				<optgroup label="Gruppo">
					<option value="ROLE_CREATE_GROUP">ROLE_CREATE_GROUP</option>
					<option value="ROLE_READ_GROUP">ROLE_READ_GROUP</option>
					<option value="ROLE_UPDATE_GROUP">ROLE_UPDATE_GROUP</option>
					<option value="ROLE_DELETE_GROUP">ROLE_DELETE_GROUP</option>
				</optgroup>
			</select>

		</div>
		

		<div class="multi-selector">

			<label for="permissions" class="col-sm-2 col-form-label">Permessi</label>
			<select class="add-permissions" multiple="multiple" id="permissions"
				name="permissions" style="width: 16%">
				<option></option>

			</select>


		</div>

		<div class="form-group row">
			<label for="enabled" class="col-sm-2 col-form-label">Attivo</label>
			<div class="col-sm-10">
				<input class="form-check-input" type="checkbox" id="enabled"
					name="enabled">

			</div>
		</div>

		<div class="form-group row">
			<label for="creationUser" class="col-sm-2 col-form-label">User
				Creazione </label>
			<div class="col-sm-10">
				<input type="text" id="creationUser" name="creationUser"
					placeholder="User creazione">
			</div>
		</div>

		<input class="btn btn-success" type="submit" value="Crea gruppo"
			onclick="enable()">

	</form>





	<script type="text/javascript">
		function enable() {

			var x = document.getElementById("enabled").checked;

			console.log(x);

		}

		$(".multiple-roles").select2({
			placeholder : "Seleziona i ruoli"
		});

		$(".add-permissions").select2({
			tags : true,
			tokenSeparators: [',', ' ', '.'],
			placeholder: "Seleziona i permessi"
		});
		
	</script>






</pt:page>
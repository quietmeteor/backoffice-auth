<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>
<%@taglib prefix="pt" uri="/WEB-INF/tlds/pagetemplate.tld"%>

<head>
<meta http-equiv="Content-Type"
	content="text/html; charset=windows-1252">

<title>Modifica del gruppo</title>
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

<body onload="enabledCheck(${group.enabled})">
	<pt:page>
		<div class="overflow-scroll" style="heigh: 100vh">

			<form action="group-list" method="POST">
				<div class="invisible">
					<input type="hidden" id="id" name="id" value="${group.id}">
					<input type="hidden" id="action" name="action" value="edit">
				</div>
				<div class="text-danger">
					<c:out value="${errorMessage}" />
				</div>
				<div class="form-group row">
					<label for="groupName" class="col-sm-2 col-form-label">Nome
						Gruppo</label>
					<div class="col-sm-10">
						<input type="text" id="groupName" name="groupName"
							value="${group.groupName}"></br>
					</div>
				</div>

				<div class="multi-select">
					<label for="roles" class="col-sm-2 col-form-label">Ruoli </label> <select
						class="update-roles" name="roles" id="roles" multiple="multiple"
						style="width: 16%">

						<c:forEach items="${rolesList}" var="i">
							<option selected="selected">"${i}"</option>
						</c:forEach>

					</select>

				</div>
				</br>

				<div class="multi-select">
					<label for="permissions" class="col-sm-2 col-form-label">Permessi
					</label> <select class="add-permissions" name="permissions"
						id="permissions" multiple="multiple" style="width: 16%">
						<c:forEach items="${permList}" var="i">

							<option selected="selected">"${i}"</option>

						</c:forEach>

					</select>
				</div>

				<div class="form-group row">
					<label for="enabled" class="col-sm-2 col-form-label">Attivo</label>
					<div class="col-sm-10">
						<input class="form-check-input" type="checkbox" id="enabled"
							name="enabled""></br>

					</div>
				</div>

				<div class="form-group row">
					<label for="creationTime" class="col-sm-2 col-form-label">Data
						Creazione </label>
					<div class="col-sm-10">
						<input type="datetime-local" id="creationTime" name="creationTime"
							value="${group.creationTime}"></br>
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
				<input class="btn btn-success" type="submit" value="Modifica"
					onclick="enable()"> </br>
			</form>





		</div>



		<script type="text/javascript">
		
		function enable() {

			var y = document.getElementById("enabled").checked;
			console.log(y);

		}
		
		function enabledCheck(enabled){
			var x=enabled
			console.log(x)
			if(x==true){
				 x = document.getElementById("enabled").checked=true;
				 
			}else{
				 x = document.getElementById("enabled").checked=false;
				
			}
			
		}
		
		$('.update-roles').select2({
			placeholder:"Modifica i ruoli"

		});;
	
		
		$(".add-permissions").select2({
			tags : true,
			tokenSeparators: [',', ' ', '.'],
			placeholder: "Modifica i permessi"
		});
		
		</script>





	</pt:page>

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

	<form action="user-list" method="POST">
		<div class="invisible">
			<input type="hidden" id="action" name="action" value="create"></input>
			
		</div>
		<div class="text-danger">
			<c:out value="${errorMessage}" />
		</div>
		<div class="form-user row">
			<label for="Nome Utente" class="col-sm-2 col-form-label">Nome
				Utente</label>
			<div class="col-sm-10">
				<input type="text" id="userName" name="userName" value="${userName}"></input> <br />
			</div>
		</div>

		<div class="form-user row">
			<label for="Cognome" class="col-sm-2 col-form-label">Cognome</label>
			<div class="col-sm-10">
				<input type="text" id="lastName" name="lastName" value="${lastName}"></input> <br />
			</div>
		</div>

		<div class="form-user row">
			<label for="Email" class="col-sm-2 col-form-label">Email</label>
			<div class="col-sm-10">
				<input type="text" id="email" name="email" value="${email}"></input><br />
			</div>
		</div>

		<div class="form-user row">
			<label for="Username" class="col-sm-2 col-form-label">Username</label>
			<div class="col-sm-10">
				<input type="text" id="username" name="username" value="${username}"></input><br />
			</div>
		</div>

		<div class="form-user row">
			<label for="Password" class="col-sm-2 col-form-label">Password</label>
			<div class="col-sm-10">
				<input type="text" id="password" name="password" value="${password}"></input><br />
			</div>
		</div>

		<div class="form-user row">
			<label for="Gruppo" class="col-sm-2 col-form-label">Gruppo</label>
			<div class="col-sm-10" style="width: 222.500px">
				<select id="groupName" name="groupName" class="form-select"
					aria-label="Default select example">
					<c:forEach items="${groupList}" var="group">
						<option value="${group.id}">${group.groupName}</option>
					</c:forEach>

				</select>
			</div>
		</div>


		<div>
			<button type="submit" class="btn btn-success">Aggiungi
				utente</button>
		</div>
	</form>







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



</pt:page>
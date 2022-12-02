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



		<form action="user-list" method="POST">
			<input type="hidden" id="action" name="action" value="edit"></input>
			<input type="hidden" id="id" name="id" value="${user.id}"></input>
			<div class="text-danger">
				<c:out value="${errorMessage}" />
			</div>
			<div class="form-user row">
				<label for="Nome Utente" class="col-sm-2 col-form-label">Nome
					Utente</label>
				<div class="col-sm-10">
					<input type="text" id="userName" name="userName"
						value="${user.name}"><br />
				</div>
			</div>

			<div class="form-user row">
				<label for="Cognome" class="col-sm-2 col-form-label">Cognome</label>
				<div class="col-sm-10">
					<input type="text" id="lastName" name="lastName"
						value="${user.lastName}"></input> <br />
				</div>
			</div>

			<div class="form-user row">
				<label for="Email" class="col-sm-2 col-form-label">Email</label>
				<div class="col-sm-10">
					<input type="text" id="email" name="email" value="${user.email}"></input><br />
				</div>
			</div>

			<div class="form-user row">
				<label for="Username" class="col-sm-2 col-form-label">Username</label>
				<div class="col-sm-10">
					<input type="text" id="username" name="username"
						value="${user.username}"><br />
				</div>
			</div>

			<div class="form-user row">
				<label for="Password" class="col-sm-2 col-form-label">Password</label>
				<div class="col-sm-10">
					<input type="text" id="password" name="password"
						value="${user.password}"><br />
				</div>
			</div>

			<div class="form-user row">
				<label for="Ultimo accesso" class="col-sm-2 col-form-label">Ultimo
					accesso</label>
				<div class="col-sm-10">
					<input type="datetime-local" id="lastLogin" name="lastLogin"
						value="${user.lastLogin}"><br />
				</div>
			</div>

			<div class="form-user row">
				<label for="Data modifica password" class="col-sm-2 col-form-label">Data
					modifica password</label>
				<div class="col-sm-10">
					<input type="datetime-local" id="dateModifiedPass"
						name="dateModifiedPass" value="${user.dateModifiedPass}"><br />
				</div>
			</div>

			<div class="form-user row">
				<label for="Gruppo" class="col-sm-2 col-form-label">Gruppo</label>
				<div class="col-sm-10" style="width: 222.500px">
					<select id="groupName" name="groupName" class="form-select"
						aria-label="${user.groupName }">
						<c:forEach items="${groupList}" var="group">
							<option value="${group.id}">${group.groupName}</option>
						</c:forEach>

					</select>
				</div>
			</div>

			<div class="form-user row">
				<label for="creationTime" class="col-sm-2 col-form-label">Utente
					verificato</label>
				<div class="col-sm-10">
					<input type="text" id="verified" name="verified"
						value="${user.verified}"><br />
				</div>
			</div>

			<div class="form-user row">
				<label for="creationTime" class="col-sm-2 col-form-label">Utente
					eliminato</label>
				<div class="col-sm-10">
					<input type="text" id="deleted" name="deleted"
						value="${user.deleted}"><br />
				</div>
			</div>

			<div>
				<button type="submit" class="btn btn-success">Aggiorna</button>
			</div>
		</form>





	</div>



	<script type="text/javascript">
		
	</script>

	</body>



</pt:page>
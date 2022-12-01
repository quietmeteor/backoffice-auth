<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>
<%@taglib prefix="pt" uri="/WEB-INF/tlds/pagetemplate.tld"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<head>
<meta http-equiv="Content-Type"
	content="text/html; charset=windows-1252">

<title>Lista Utenti</title>
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

<<<<<<< HEAD

=======
>>>>>>> master-web-userList
<pt:page>

	<div class="container-fluid">
		<div class="row">
<<<<<<< HEAD
			
			<main role="main" class="col-md-9 ml-sm-auto col-lg-10 pt-3 px-4">

				<div class="container">
					<button type="button" class="btn btn-success" onclick="create()">Create user
					</button>
				
					<div class="row">
						<div class="col-4 text-center">

							
=======


			<main role="main" class="col-md-9 ml-sm-auto col-lg-10 pt-3 px-4">



				<div class="container">
					<button type="button" class="btn btn-success" onclick="create()">Create new
						user</button>

					<div class="row">

						<div class="text-danger">
							<c:out value="${error_message}" />
						</div>

						<div class="col-4 text-center">
							<p></p>
>>>>>>> master-web-userList
							<table class="table table-bordered table-hover">
								<thead>
									<tr>
										<th scope="col" class="align-text-top">Id</th>

<<<<<<< HEAD
										<th scope="col" class="align-text-top">Email</th>
=======
										<th scope="col" class="align-text-top">ID</th>
>>>>>>> master-web-userList

										<th scope="col" class="align-text-top">Nome</th>

										<th scope="col" class="align-text-top">Cognome</th>

<<<<<<< HEAD
										<th scope="col" class="align-text-top">Username</th>

										<th scope="col" class="align-text-top">Password</th>

										<th scope="col" class="align-text-top">Group</th>

										<th scope="col" class="align-text-top">Last login</th>

										<th scope="col" class="align-text-top">Date modified password</th>

										<th scope="col" class="align-text-top">Verified?</th>

										<th scope="col" class="align-text-top">Deleted?</th>
=======
										<th scope="col" class="align-text-top">Email</th>

										<th scope="col" class="align-text-top">Username</th>

										<th scope="col" class="align-text-top">Password</</th>

										<th scope="col" class="align-text-top">Ultimo acesso</th>

										<th scope="col" class="align-text-top">Data modifica
											password</th>

										<th scope="col" class="align-text-top">Gruppo</th>

										<th scope="col" class="align-text-top">Utente verificato</th>

										<th scope="col" class="align-text-top">Utente eliminato</th>
>>>>>>> master-web-userList

										<td>&nbsp;</td>
									</tr>
								</thead>
								<tbody>

<<<<<<< HEAD
=======
									<c:forEach items="${userList}" var="user">
										<tr>
											<td><c:out value="${fn:trim(user.id)}" /></td>
											<td><c:out value="${fn:trim(user.name)}" /></td>
											<td><c:out value="${fn:trim(user.lastName)}" /></td>
											<td><c:out value="${fn:trim(user.email)}" /></td>
											<td><c:out value="${fn:trim(user.username)}" /></td>
											<td><c:out value="${fn:trim(user.password)}" /></td>
											<td><c:out value="${fn:trim(user.lastLogin)}" /></td>
											<td><c:out value="${fn:trim(user.dateModifiedPass)}" /></td>
											<td><c:out value="${fn:trim(user.groupName)}" /></td>
											<td><c:out value="${fn:trim(user.verified)}" /></td>
											<td><c:out value="${fn:trim(user.deleted)}" /></td>
											<td>
												<!-- 	<form action="user-list" method="POST">-->
												<button type="button" class="btn btn-primary"
													onclick="detail(${user.id})">
													<i class="far fa-eye"></i>
												</button>
												<button type="button" class="btn btn-success"
													onclick="edit(${user.id})">
													<i class="fas fa-edit"></i>
												</button>
												<button type="button" class="btn btn-danger"
													onclick="deleteEntity(${user.id})">
													<i class="far fa-trash-alt"></i>
												</button>
												</form>
											</td>
										</tr>
									</c:forEach>
>>>>>>> master-web-userList

									<c:forEach items="${userList}" var="user">
										
											<tr>
												<td><c:out value="${fn:trim(user.id)}"></c:out></td>
												<td><c:out value="${fn:trim(user.email)}"></c:out></td>
												<td><c:out value="${fn:trim(user.name)}"></c:out></td>
												<td><c:out value="${fn:trim(user.lastName)}"></c:out></td>
												<td><c:out value="${fn:trim(user.username)}"></c:out></td>
												<td><c:out value="${fn:trim(user.password)}"></c:out></td>
												<td><c:out value="${fn:trim(user.groupName)}"></c:out></td>
												<td><c:out value="${fn:trim(user.lastLogin)}"></c:out></td>
												<td><c:out value="${fn:trim(user.dateModifiedPass)}"></c:out></td>
												<td><c:out value="${fn:trim(user.verified)}"></c:out></td>
												<td><c:out value="${fn:trim(user.deleted)}"></c:out></td>

<<<<<<< HEAD
												<td>
													<button type="button" class="btn btn-primary"
														onclick="detail(4)">
														<i class="far fa-eye"></i>
													</button>
													<button type="button" class="btn btn-success"
														onclick="edit(4)">
														<i class="fas fa-edit"></i>
													</button>
													<button type="button" class="btn btn-danger"
														onclick="deleteEntity(4)">
														<i class="far fa-trash-alt"></i>
													</button>
												</td>
											</tr>
										</c:forEach>
									
=======
>>>>>>> master-web-userList

								</tbody>
							</table>

						</div>

					</div>

				</div>
<<<<<<< HEAD

				<script>
					function detail(id) {

						window.location.href="./user-list?action=detail&id="+id;
					}
					function edit(id) {

						window.location.href = "http://localhost:8080/prenotazioni-web/edit/risorsa/"
								+ id;
					}
					function create() {
						window.location.href = "http://localhost:8080/prenotazioni-web/edit/risorsa/0";
					}

					function deleteEntity(id) {

						$('#id').val(id);
						$('#form1').submit();
					}
				</script>



=======
>>>>>>> master-web-userList
			</main>
		</div>

	</div>


<<<<<<< HEAD
=======
	<script type="text/javascript">
		function detail(id){
			window.location.href="./user-list?action=detail&id="+id;
		}
	
		function edit(id) {
			window.location.href="./user-list?action=edit&id="+id;

		}

		function create() {
			window.location.href="./user-list?action=create";
		}


		
		function deleteEntity(id) {
			
			window.location.href="./user-list?action=delete&id="+id;
			
		}
	</script>


>>>>>>> master-web-userList
</pt:page>
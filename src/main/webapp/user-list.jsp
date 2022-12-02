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


<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.1/css/all.min.css"
	integrity="sha512-MV7K8+y+gLIBoVD59lQIYicR65iaqukzvf/nwasF0nqhPay5w/9lJmVM2hMDcnK1OnMGCdVK+iQrJ7lzPJQd1w=="
	crossorigin="anonymous" referrerpolicy="no-referrer" />
<script src="https://code.jquery.com/jquery-3.6.1.js"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>

<link rel="stylesheet" type="text/css"
	href="https://cdn.datatables.net/1.13.1/css/jquery.dataTables.css">
<script type="text/javascript" charset="utf8"
	src="https://cdn.datatables.net/1.13.1/js/jquery.dataTables.js"></script>

</head>

<pt:page>
	<div class="overflow-scroll" style="height: 96vh">
		<div class="container-fluid">
			<div class="row">


				<main role="main" class="col-md-9 ml-sm-auto col-lg-10 pt-3 px-4">

					<div class="container pb-3">
						<button type="button" class="btn btn-success" onclick="create()">Create
							new User</button>
					</div>

					<div class="container">


						<div class="row">

							<div class="text-danger">
								<c:out value="${error_message}" />
							</div>

							<table class="table table-bordered table-hover"
								id="user-datatable">
								<thead>
									<tr>

										<th scope="col" class="align-text-top">ID</th>

										<th scope="col" class="align-text-top">Nome</th>

										<th scope="col" class="align-text-top">Cognome</th>

										<th scope="col" class="align-text-top">Email</th>

										<th scope="col" class="align-text-top">Username</th>

										<th scope="col" class="align-text-top">Password</</th>

										<th scope="col" class="align-text-top">Ultimo acesso</th>

										<th scope="col" class="align-text-top">Data modifica
											password</th>

										<th scope="col" class="align-text-top">Gruppo</th>

										<th scope="col" class="align-text-top">Utente verificato</th>

										<th scope="col" class="align-text-top">Utente eliminato</th>

										<td>&nbsp;</td>
									</tr>
								</thead>
								<tbody>

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

												<button type="button" class="btn btn-primary"
													style="width: 44px" onclick="detail(${user.id})">
													<i class="far fa-eye"></i>
												</button>
												<button type="button" class="btn btn-success"
													style="width: 44px" onclick="edit(${user.id})">
													<i class="fas fa-edit"></i>
												</button>
												<button type="button" class="btn btn-danger"
													style="width: 44px" onclick="deleteEntity(${user.id})">
													<i class="far fa-trash-alt"></i>
												</button>

											</td>
										</tr>
									</c:forEach>



								</tbody>
							</table>
						</div>

					</div>
				</main>
			</div>

		</div>

	</div>


	<script type="text/javascript">
	
		(function($) {
			    $('#user-datatable').DataTable({
					"dom": '<lf<t>ip>',
					
					language: {
						search: "_INPUT_",
						searchPlaceholder: "Search records"
					},
					
					columnDefs: [
						    { orderable: false, targets: -1 }
						  ]
				});
		}(jQuery));
		
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

	</div>
</pt:page>
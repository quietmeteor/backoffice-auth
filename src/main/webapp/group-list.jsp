<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>
<%@taglib prefix="pt" uri="/WEB-INF/tlds/pagetemplate.tld"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<head>
<meta http-equiv="Content-Type"
	content="text/html; charset=windows-1252">

<title>Lista Gruppi</title>
<!--<base href="/">-->
<base href=".">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="icon" type="image/x-icon" href="http://localhost:8080/favicon.ico">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">
	

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.1/css/all.min.css" integrity="sha512-MV7K8+y+gLIBoVD59lQIYicR65iaqukzvf/nwasF0nqhPay5w/9lJmVM2hMDcnK1OnMGCdVK+iQrJ7lzPJQd1w==" crossorigin="anonymous" referrerpolicy="no-referrer" />
<script src="https://code.jquery.com/jquery-3.6.1.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
	
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.13.1/css/jquery.dataTables.css">
<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.13.1/js/jquery.dataTables.js"></script>

</head>

<pt:page>
<div class="overflow-scroll" style="height: 96vh">
	<div class="container-fluid">
		<div class="row">


<<<<<<< HEAD
			<main role="main" class=" col-lg-10 ">
			
			<div class="container pb-3">
				<button type="button" class="btn btn-success" onclick="create()">Create Group</button> 
			</div>
=======
			<main role="main" class="col-md-9 ml-sm-auto col-lg-10 pt-3 px-4">
>>>>>>> b5c1448251409b276c4010b1f4b9005b7cb2f229

				<div class="container">
					
			
					<div class="row">

						<div class="text-danger">
							<c:out value="${error_message}" />
						</div>

						

							<table class="table table-bordered table-hover" id="group-datatable">
								<thead>
									<tr>

										<th scope="col" class="align-text-top">Id</th>

										<th scope="col" class="align-text-top">Nome Gruppo</th>

										<th scope="col" class="align-text-top">Ruoli</th>

										<th scope="col" class="align-text-top">Permessi</th>

										<th scope="col" class="align-text-top">Attivo?</th>

										<th scope="col" class="align-text-top">User creazione</</th>

										<th scope="col" class="align-text-top">Data creazione</th>

										<th scope="col" class="align-text-top">Aggiornamento user</th>

										<th scope="col" class="align-text-top">Data aggiornamento</th>

										<td>&nbsp;</td>
									</tr>
								</thead>
								<tbody>

									<c:forEach items="${groupList}" var="group">
										<tr>
											<td><c:out value="${fn:trim(group.id)}" /></td>
											<td><c:out value="${fn:trim(group.groupName)}" /></td>
											<td><c:out value="${fn:trim(group.roles)}" /></td>
											<td><c:out value="${fn:trim(group.permissions)}" /></td>
											<td><c:out value="${fn:trim(group.enabled)}" /></td>
											<td><c:out value="${fn:trim(group.creationUser)}" /></td>
											<td><c:out value="${fn:trim(group.creationTime)}" /></td>
											<td><c:out value="${fn:trim(group.updateUser)}" /></td>
											<td><c:out value="${fn:trim(group.updateTime)}" /></td>

											<td>
											
												<button type="button" class="btn btn-primary" style="width: 44px"
													onclick="detail(${group.id})">
													<i class="far fa-eye"></i>
												</button>
												<button type="button" class="btn btn-success" style="width: 44px"
													onclick="edit(${group.id})">
													<i class="fas fa-edit"></i>
												</button>
												<button type="button" class="btn btn-danger" style="width: 44px"
													onclick="deleteEntity(${group.id})">
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
			    $('#group-datatable').DataTable({
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
			window.location.href="./group-list?action=detail&id="+id;
		}
	
		function edit(id) {
			window.location.href="./group-list?action=edit&id="+id;
		}

		function create() {
			window.location.href="./group-list?action=create";
		}
	
		function deleteEntity(id) {
			window.location.href="./group-list?action=deleteEntity&id="+id;
		}		

	</script>

</pt:page>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>
<%@taglib prefix="pt" uri="/WEB-INF/tlds/pagetemplate.tld"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<head>

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
	<div class="container-fluid">
		<div class="row">

			<main role="main" class="col-md-9 ml-sm-auto col-lg-10 pt-3 px-4">



				<div class="container">
					<button type="button" class="btn btn-success" onclick="create()">Create
						Group</button>
					<div class="row">

						<div class="col-4 text-center">
							<table class="table table-bordered table-hover table-striped">
								<thead>
									<tr>

										<th scope="col" class="align-text-top">Id</th>

										<th scope="col" class="align-text-top">Group Name</th>

										<th scope="col" class="align-text-top">Roles</th>

										<th scope="col" class="align-text-top">Permissions</th>

										<th scope="col" class="align-text-top">Enabled</th>

										<th scope="col" class="align-text-top">Creation User</</th>

										<th scope="col" class="align-text-top">Creation Time</th>

										<th scope="col" class="align-text-top">Update User</th>

										<th scope="col" class="align-text-top">Update Time</th>

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
												<button type="button" class="btn btn-primary"
													onclick="detail(${group.id})">
													<i class="far fa-eye"></i>
												</button>
												<button type="button" class="btn btn-success"
													onclick="edit(${group.id})">
													<i class="fas fa-edit"></i>
												</button>
												<button type="button" class="btn btn-danger"
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
				</div>





			</main>
		</div>
	</div>


	<script type="text/javascript">
		function detail(id){
			window.location.href="/Backoffice/group-detail?id="+id;
		}
	
		function edit(id) {

		}

		function create() {

		}

		function deleteEntity() {

		}
	</script>


</pt:page>
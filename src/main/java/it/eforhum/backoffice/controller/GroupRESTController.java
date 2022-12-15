package it.eforhum.backoffice.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import it.eforhum.backoffice.dto.GroupDTO;
import it.eforhum.backoffice.entity.UserGroups;
import it.eforhum.backoffice.enums.Roles;
import it.eforhum.backoffice.util.DaoFactory;
import it.eforhum.backoffice.util.ServiceFactory;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/groups")
public class GroupRESTController {
	private static Logger log = LogManager.getLogger(GroupRESTController.class);	

	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public List<GroupDTO> findAll() {

		log.info("Read all items");
		
		List<GroupDTO> list = ServiceFactory.getGroupService().getAllGroups();
		
		return list;
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public GroupDTO getGroup(@PathParam("id") int id) {

		log.info("Read item detail for id {}", id);
		
		GroupDTO entity = ServiceFactory.getGroupService().findGroupById(id);
		
		return entity;
	}

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public GroupDTO createGroup(@Valid GroupDTO dto) {

		log.info("Create item");
		
		UserGroups group = new UserGroups();
		group.setGroupName(dto.getGroupName());
		group.setEnabled(dto.isEnabled());
		group.setPermissions(dto.getPermissions());
		group.setCreationTime(dto.getCreationTime());
		group.setCreationUser(dto.getCreationUser());
		
		for(Roles r : dto.getRoles()) {
			group.setRoles(r.toString());
		}
		
		group.setUpdateTime(dto.getUpdateTime());
		group.setUpdateUser(dto.getUpdateUser());
		
		DaoFactory.getUserGroupDao().save(group);
		
		return ServiceFactory.getGroupService().findGroupById(group.getId());
	}	

	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public GroupDTO updateGroup(@PathParam("id") int id, GroupDTO dto) {

		log.info("Update item detail for id {}", id);
		
		ServiceFactory.getGroupService().updateGroup(id, dto);
		
		return ServiceFactory.getGroupService().findGroupById(id);
	}	
	
	@DELETE
	@Path("/{id}")
	public Response deleteGroup(@PathParam("id") int id) {
		int deletedId = id;
		
		ServiceFactory.getGroupService().deleteGroup(id);
		
		return Response.status(200)
				.entity("Group with id " + deletedId + " succesfully deleted.")
				.build();
	}
	
}

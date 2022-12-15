package it.eforhum.backoffice.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import it.eforhum.backoffice.dto.GroupDTO;
import it.eforhum.backoffice.dto.UserDTO;
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

@Path("/users")
public class UserRestController {

	private static Logger log = LogManager.getLogger(UserRestController.class);	

	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public List<UserDTO> findAll() {

		log.info("Read all Users");
		
		List<UserDTO> list = ServiceFactory.getUserService().getAllUsers();
		
		return list;
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public UserDTO getUser(@PathParam("id") long id) {

		log.info("Read user detail for id {}", id);
		
		UserDTO entity = ServiceFactory.getUserService().findById(id);
		
		return entity;
	}
	
	@GET
	@Path("/{username}")
	@Produces(MediaType.APPLICATION_JSON)
	public UserDTO getUserByUsername(@PathParam("username") String username) {

		log.info("Read user detail for id {}", username);
		
		UserDTO entity = ServiceFactory.getUserService().findByUsername(username);
		
		return entity;
	}

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public UserDTO createUser(@Valid UserDTO dto) {

		log.info("Create user");
//		List<UserGroups> groups = () DaoFactory.getUserGroupDao().findAll();
		UserDTO user = new UserDTO();

		user.setName(dto.getName());
		user.setLastName(dto.getLastName());
		user.setEmail(dto.getEmail());
		user.setUsername(dto.getUsername());
		user.setPassword(dto.getPassword());
		user.setLastLogin(LocalDateTime.now());
		user.setDateModifiedPass(LocalDateTime.now());
		user.setCreationTime(LocalDateTime.now());
		user.setCreationUser("admin");
		user.setVerified(dto.isVerified());
		user.setDeleted(dto.isDeleted());
		user.setGroupId(dto.getGroupId());
		
		ServiceFactory.getUserService().createUser(user);
		
		return ServiceFactory.getUserService().findByUsername(dto.getUsername());
	}	

	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public UserDTO updateUser(@PathParam("id") long id, UserDTO dto) {

		log.info("Update user detail for id {}", id);
		
		ServiceFactory.getUserService().updateUser(dto, id);
		
		return ServiceFactory.getUserService().findById(id);
	}
	
	@PUT
	@Path("/{idGroup}/{idUser}")
	@Produces(MediaType.APPLICATION_JSON)
	public UserDTO addUserToGroup(@PathParam("idGroup") long idGroup, @PathParam("idUser") long idUser) {

		log.info("User detail for id {}", ServiceFactory.getUserService().findById(idUser));
		log.info("Group detail for id {}", ServiceFactory.getGroupService().findGroupById(idGroup));
		
		GroupDTO groupDTO = ServiceFactory.getGroupService().findGroupById(idGroup);
		UserDTO userDTO = ServiceFactory.getUserService().findById(idUser);
		
		ServiceFactory.getUserService().addUserToGroup(groupDTO, userDTO);
		
		return ServiceFactory.getUserService().findById(idUser);
	}
	
	@DELETE
	@Path("/{id}")
	public Response deleteUser(@PathParam("id")long id) {
		log.info("Delete user for id {}", id);
		long deletedId = id;
		ServiceFactory.getUserService().deleteUserCompletely(id);
		
		return Response.status(200)
				.entity("User with id "+deletedId+" succesfuly deleted.")
				.build();
	}
	
	
}

package it.eforhum.backoffice.controller;

import java.time.LocalDateTime;
import java.util.List;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.eforhum.backoffice.dto.GroupDTO;
import it.eforhum.backoffice.dto.UserDTO;
import it.eforhum.backoffice.service.GroupService;
import it.eforhum.backoffice.service.UserService;

@RestController
@RequestMapping(value = "/api/users")
@CrossOrigin
public class UserRESTController {

	private static Logger log = LogManager.getLogger(UserRESTController.class);	
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private GroupService groupService;

	@GetMapping(value = "/")
	public List<UserDTO> findAll() {

		log.info("Read all Users");
		
		List<UserDTO> list = userService.getAllUsers();
		
		return list;
	}

	@GetMapping(value = "/{id}")
	public UserDTO getUser(@PathVariable("id") int id) {

		log.info("Read user detail for id {}", id);
		
		UserDTO entity = userService.findById(id);
		
		return entity;
	}
	
	@GetMapping(value = "")
	public ResponseEntity<UserDTO> findByUsername(@RequestParam String username) {

		log.info("Read user detail for username {}", username);
		
		UserDTO user = userService.findByUsername(username);
		
	    if (user == null) {
	        return ResponseEntity.notFound().build();
	    }
	    
	    return ResponseEntity.ok(user);
	    
	}

	@PostMapping(value = "/")
	public UserDTO createUser(@Valid UserDTO dto) {

		log.info("Create user");
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
		
		userService.createUser(user);
		
		return userService.findByUsername(dto.getUsername());
	}	

	@PutMapping(value = "/{id}")
	public UserDTO updateUser(@PathVariable("id") int id, @RequestBody UserDTO dto) {

		log.info("Update user detail for id {}", id);
		
		userService.updateUser(dto, id);
		
		return userService.findById(id);
	}
	
	
	@PutMapping(value = "/{idGroup}/{idUser}")
	public ResponseEntity<UserDTO> addUserToGroup(@PathVariable("idGroup") int idGroup, @PathVariable("idUser") int idUser) {
		
		UserDTO uDTO = userService.findById(idUser);
		GroupDTO gDTO = groupService.findGroupById(idGroup);
		userService.addUserToGroup(gDTO, uDTO);
		
		return new ResponseEntity<UserDTO>(userService.findById(idUser), HttpStatus.OK);
	}
	
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<String> deleteUser(@PathVariable("id") int id) {
		log.info("Delete user for id {}", id);
		long deletedId = id;
		userService.deleteUserCompletely(id);
		
		return new ResponseEntity<>("User with id " + deletedId + " succesfully deleted", HttpStatus.OK);
	}
	
	
}

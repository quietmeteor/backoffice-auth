package it.eforhum.backoffice.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.eforhum.backoffice.dto.GroupDTO;
import it.eforhum.backoffice.service.GroupService;

@RestController
@RequestMapping(value = "/api/groups")
@CrossOrigin
public class GroupRESTController {
	
	private static Logger log = LogManager.getLogger(GroupRESTController.class);

	@Autowired
	private GroupService groupService;

	@GetMapping(value = "/")
	public List<GroupDTO> findAll() {

		log.info("Read all items");

		return groupService.getAllGroups();

	}

	@GetMapping(value = "/{id}")
	public GroupDTO getGroup(@PathVariable("id") int id) {

		log.info("Read item detail for id {}", id);

		return groupService.findGroupById(id);

	}

	@PutMapping(value = "/")
	public GroupDTO createGroup(@RequestBody GroupDTO dto) {

		log.info("Creating a group");

		return groupService.createGroup(dto);
		
	}	

	@PutMapping(value = "/{id}")
	public GroupDTO updateGroup(@PathVariable("id") int id, @RequestBody GroupDTO dto) {

		log.info("Update item detail for id {}", id);

		groupService.updateGroup(id, dto);

		return groupService.findGroupById(id);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<String> deleteGroup(@PathVariable("id") int id) {
		int deletedId = id;

		groupService.deleteGroup(id);

		return new ResponseEntity<>("Group with id " + deletedId + " succesfully deleted", HttpStatus.OK);
	}

}

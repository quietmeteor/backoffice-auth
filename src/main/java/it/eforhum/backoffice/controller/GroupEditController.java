package it.eforhum.backoffice.controller;


import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import it.eforhum.backoffice.dto.GroupDTO;
import it.eforhum.backoffice.enums.Roles;
import it.eforhum.backoffice.util.ServiceFactory;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/group-edit")
public class GroupEditController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private Logger log = LogManager.getLogger(GroupEditController.class);

	public GroupEditController() {
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.getRequestDispatcher("group-create.jsp").forward(req, resp);
		
	}
	
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		log.info("Post request recieved");
//		resp.sendRedirect("/Backoffice/group-list");
		
//		String action=req.getParameter("action");
//		log.info("Action is {} ", action);
		
//		if(action.equalsIgnoreCase("create")) {
			
			
			
			GroupDTO newGroup=new GroupDTO();
			
			if(req.getParameter("groupName")==null) {
				log.warn("Nome gruppo nullo");
				req.setAttribute("errorMessage", "Nome deve essere valorizzato!");
				return;
			}
			
			newGroup.setGroupName(req.getParameter("groupName"));
			
			if(req.getParameter("roles")==null) {
				log.warn("Ruoli gruppo nulli");
				req.setAttribute("errorMessage", "Ruoli devono essere valorizzati");
			}
			
			List<Roles> roles=new ArrayList<>();
			roles.add(Roles.USER);
			newGroup.setRoles(roles);
			
			if(req.getParameter("permissions")==null) {
				log.warn("Permessi nulli");
				req.setAttribute("errorMessage", "Permessi devono essere valorizzati");
			}
			
			if(req.getParameter("creationUser")==null) {
				log.warn("User creazione nullo");
				req.setAttribute("errorMessage", "User creazione deve essere valorizzato");
			}
			
			newGroup.setCreationUser(req.getParameter("creationUser"));
			newGroup.setPermissions("BASE_PERMISSIONS");
			
			newGroup.setEnabled(true);
			
			newGroup.setCreationTime(LocalDateTime.now());
			
			ServiceFactory.getGroupService().createGroup(newGroup);
			log.info("Gruppo id {} e' stato creato ", newGroup);
			
			resp.sendRedirect("group-list");
			
//		}
		
		
		
		
	}
}

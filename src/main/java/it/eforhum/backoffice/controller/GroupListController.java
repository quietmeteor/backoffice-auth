//package it.eforhum.backoffice.controller;
//
//import java.io.IOException;
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
//import org.apache.commons.lang3.EnumUtils;
//import org.apache.commons.lang3.StringUtils;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//
//import it.eforhum.backoffice.dto.GroupDTO;
//import it.eforhum.backoffice.enums.Roles;
//import it.eforhum.backoffice.util.ServiceFactory;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//
//@WebServlet("/group-list")
//public class GroupListController extends HttpServlet {
//	private static final Logger log = LogManager.getLogger(GroupListController.class);
//
//	@Override
//	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//
//		List<GroupDTO> groupList = ServiceFactory.getGroupService().getAllGroups();
//		log.info("Ricerca dei gruppi effetuata");
//
//		req.setAttribute("groupList", groupList);
//
//		String action = req.getParameter("action");
//
//		if (action != null && action.equalsIgnoreCase("detail")) {
//			log.info("doGet Request per /group-detail ricevuta");
//
//			GroupDTO group = findGroup(req, resp);
//
//			List<String> rolesList = getRoles(group);
//			log.info("La lista dei ruoli e': {}", rolesList);
//
//			List<String> permList = Arrays.asList(group.getPermissions().split(","));
//			log.info("Lista permessi: {}", permList);
//
//			req.setAttribute("group", group);
//			req.setAttribute("rolesList", rolesList);
//			req.setAttribute("permList", permList);
//
//			req.getRequestDispatcher("group-detail.jsp").forward(req, resp);
//
//		}
//
//		else if (action != null && action.equalsIgnoreCase("deleteEntity")) {
//
//			log.info("");
//			log.info("Request recieved, action delete");
//
//			GroupDTO group = findGroup(req, resp);
//			ServiceFactory.getGroupService().deleteGroup(group.getId());
//
//			log.info("Gruppo e' stato eliminato");
//
//			resp.sendRedirect("group-list");
//
//		} else if (action != null && action.equalsIgnoreCase("create")) {
//			log.info("Request Recieved, action create");
//			req.setAttribute("action", action);
//			req.getRequestDispatcher("group-create.jsp").forward(req, resp);
//
//		} else if (action != null && action.equalsIgnoreCase("edit")) {
//			log.info("Request Recieved, action edit");
//			GroupDTO group = findGroup(req, resp);
//			req.setAttribute("group", group);
//			
//			List<String> rolesList = getRoles(group);
//			log.info("La lista dei ruoli e': {}", rolesList);
//
//			List<String> permList = Arrays.asList(group.getPermissions().split(","));
//			log.info("Lista permessi: {}", permList);
//
//			req.setAttribute("group", group);
//			req.setAttribute("rolesList", rolesList);
//			req.setAttribute("permList", permList);
//			
//			log.info("Richiesta modifica di gruppo id {} ", group.getId());
//			req.setAttribute("action", action);
//
//			req.getRequestDispatcher("group-edit.jsp").forward(req, resp);
//		}
//
//		else {
//			req.getRequestDispatcher("group-list.jsp").forward(req, resp);
//		}
//
//	}
//
//	@Override
//	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//
//		String action = req.getParameter("action");
//		log.info("Action is {} ", action);
//
//		if (action != null && action.equalsIgnoreCase("create")) {
//			log.info("Richeiesta crea ricevuta");
//			GroupDTO newGroup = new GroupDTO();
//
//			if (StringUtils.isEmpty(req.getParameter("groupName"))) {
//				log.warn("Nome gruppo nullo");
//				req.setAttribute("errorMessage", "Nome deve essere valorizzato!");
//				req.getRequestDispatcher("group-create.jsp").forward(req, resp);
//				return;
//			}
//
//			newGroup.setGroupName(req.getParameter("groupName"));
//
//			if (StringUtils.isEmpty(req.getParameter("roles"))) {
//				log.warn("Ruoli gruppo nulli");
//				req.setAttribute("errorMessage", "Ruoli devono essere valorizzati");
//				req.getRequestDispatcher("group-create.jsp").forward(req, resp);
//				return;
//			}
//
//			String[] rolesArr = req.getParameterValues("roles");
//			List<Roles> roles = new ArrayList<>();
//			String role = " ";
//
//			for (int i = 0; i < rolesArr.length; i++) {
//				log.info("Role is: {} ", rolesArr[i]);
//				role = rolesArr[i];
//        
//        if(!StringUtils.isEmpty(role)) {
//          roles.add(Roles.valueOf(role));
//        }
//
//			}
//			newGroup.setRoles(roles);
//			
//			if (StringUtils.isEmpty(req.getParameter("permissions"))) {
//				log.warn("Permessi nulli");
//				req.setAttribute("errorMessage", "Permessi devono essere valorizzati");
//				req.getRequestDispatcher("group-create.jsp").forward(req, resp);
//				return;
//			}
//
//			String[] permParams = req.getParameterValues("permissions");
//			List<String> permArr = new ArrayList<>();
//			for (int i = 0; i < permParams.length; i++) {
//				log.info("Il permesso e' {} ", permParams[i]);
//				permArr.add(permParams[i]);
//			}
//
//			log.info("Permessi sono: {} ", permArr);
//			newGroup.setPermissions(permArr.toString().replace("[", "").replace("]", ""));
//
//			if (StringUtils.isEmpty(req.getParameter("creationUser"))) {
//				log.warn("User creazione nullo");
//				req.setAttribute("errorMessage", "User creazione deve essere valorizzato");
//				req.getRequestDispatcher("group-create.jsp").forward(req, resp);
//				return;
//			}
//
//			newGroup.setCreationUser(req.getParameter("creationUser"));
//
//			if (req.getParameter("enabled") == null) {
//				newGroup.setEnabled(false);
//			} else if (req.getParameter("enabled").equalsIgnoreCase("on")) {
//				newGroup.setEnabled(true);
//			}
//
//			newGroup.setCreationTime(LocalDateTime.now());
//
//			ServiceFactory.getGroupService().createGroup(newGroup);
//			log.info("Gruppo id {} e' stato creato ", newGroup);
//
//			resp.sendRedirect("group-list");
//
//		} else if (action != null && action.equalsIgnoreCase("edit")) {
//
//			log.info("Richeiesta edit ricevuta");
//			GroupDTO groupUpd = new GroupDTO();
//			GroupDTO group = findGroup(req, resp);
//			req.setAttribute("group", group);
//			
//			List<String> rolesList = getRoles(group);
//			log.info("La lista dei ruoli e': {}", rolesList);
//
//			List<String> permList = Arrays.asList(group.getPermissions().split(","));
//			log.info("Lista permessi: {}", permList);
//
//			req.setAttribute("group", group);
//			req.setAttribute("rolesList", rolesList);
//			req.setAttribute("permList", permList);
//			
//			log.info("Richiesta modifica di gruppo id {} ", group.getId());
//			req.setAttribute("action", action);
//
//			String idPar = req.getParameter("id");
//			log.info("ID e' {} ", idPar);
//			if (idPar == null) {
//				log.warn("Id parameter is null");
//				req.setAttribute("errorMessage", "Id nullo");
//				resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
//
//			}
//
//			int id = -1;
//
//			try {
//				id = Integer.parseInt(idPar);
//			} catch (NumberFormatException e) {
//				log.error("Id parameter must be a number");
//				req.setAttribute("errorMessage", "Formato id sbagliato");
//
//				resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
//			}
//			groupUpd.setId(id);
//
//			log.info("Il nome e' {}", req.getParameter("groupName"));
//
//			if (StringUtils.isEmpty(req.getParameter("groupName"))) {
//				log.warn("Nome gruppo nullo");
//				req.setAttribute("errorMessage", "Nome deve essere valorizzato!");
//				req.getRequestDispatcher("group-edit.jsp").forward(req, resp);
//				return;
//			}
//
//			groupUpd.setGroupName(req.getParameter("groupName"));
//
//			if (StringUtils.isEmpty(req.getParameter("roles"))) {
//				log.warn("Ruoli gruppo nulli");
//				req.setAttribute("errorMessage", "Ruoli devono essere valorizzati");
//				req.getRequestDispatcher("group-edit.jsp").forward(req, resp);
//				return;
//			}
//
//			String[] rolesParams = req.getParameterValues("roles");
//			
//			List<Roles> roles = new ArrayList<>();
//			String role = "";
//
//			for (int i = 0; i < rolesParams.length; i++) {
//				log.info("Il ruolo: {} ", rolesParams[i]);
//				role = rolesParams[i];
//				if (EnumUtils.isValidEnum(Roles.class, role)) {
//					
//				roles.add(Roles.valueOf(role));
//				
//				}else {
//					log.warn("Il ruolo non e' valido!");
//					req.setAttribute("errorMessage", "Ruolo non valido!");
//					req.getRequestDispatcher("group-edit.jsp").forward(req, resp);
//					return;
//				}
//				
//			}
//
//			groupUpd.setRoles(roles);
//
//			if (StringUtils.isEmpty(req.getParameter("permissions"))) {
//				log.warn("Permessi nulli");
//				req.setAttribute("errorMessage", "Permessi devono essere valorizzati");
//				req.getRequestDispatcher("group-edit.jsp").forward(req, resp);
//				return;
//			}
//
//			String[] permParams = req.getParameterValues("permissions");
//			List<String> permArr = new ArrayList<>();
//			
//			for (int i = 0; i < permParams.length; i++) {
//				log.info("Il permesso e' {} ", permParams[i]);
//				permArr.add(permParams[i]);
//			}
//
//			log.info("Permessi sono: {} ", permArr);
//			
//			groupUpd.setPermissions(permArr.toString().replace("[", "").replace("]", ""));
//			log.info("User e' {} ", req.getParameter("creationUser"));
//
//			if (StringUtils.isEmpty(req.getParameter("creationUser"))) {
//				log.warn("User creazione nullo");
//				req.setAttribute("errorMessage", "User creazione deve essere valorizzato");
//				req.getRequestDispatcher("group-edit.jsp").forward(req, resp);
//				return;
//			}
//
//			groupUpd.setCreationUser(req.getParameter("creationUser"));
//
//			if (StringUtils.isEmpty(req.getParameter("updateUser"))) {
//				log.warn("User aggiornamento nullo");
//				req.setAttribute("errorMessage", "User aggiornamento deve essere valorizzato");
//
//				req.getRequestDispatcher("group-edit.jsp").forward(req, resp);
//				return;
//			}
//
//			groupUpd.setUpdateUser(req.getParameter("updateUser"));
//
//			if (req.getParameter("enabled") == null) {
//				groupUpd.setEnabled(false);
//			} else if (req.getParameter("enabled").equalsIgnoreCase("on")) {
//				groupUpd.setEnabled(true);
//			}
//			
//
//			if (StringUtils.isEmpty(req.getParameter("creationTime"))) {
//				log.warn("Creation time nullo");
//				req.setAttribute("errorMessage", "Data creazione deve essere valorizzata!");
//				req.getRequestDispatcher("group-edit.jsp").forward(req, resp);
//				return;
//			}
//			
//			groupUpd.setCreationTime(LocalDateTime.parse(req.getParameter("creationTime")));
//
//			groupUpd.setUpdateTime(LocalDateTime.now());
//
//			ServiceFactory.getGroupService().updateGroup(groupUpd.getId(), groupUpd);
//			resp.sendRedirect("group-list");
//
//		} else {
//			resp.sendRedirect("group-list");
//		}
//
//	}
//
//	public GroupDTO findGroup(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//
//		log.info("");
//		log.info("doGet Request for /group-detail recieved");
//
//		String idPar = req.getParameter("id");
//		if (idPar == null) {
//			log.warn("Id parameter is null");
//			req.setAttribute("errorMessage", "Id nullo");
//			resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
//		}
//
//		int id = -1;
//
//		try {
//			id = Integer.parseInt(idPar);
//		} catch (NumberFormatException e) {
//			log.error("Id parameter must be a number");
//			req.setAttribute("errorMessage", "Formato id sbagliato");
//			resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
//		}
//
//		GroupDTO group = ServiceFactory.getGroupService().findGroupById(id);
//
//		if (group == null) {
//			log.warn("The group with id {} is null", id);
//			req.setAttribute("errorMessage", "Gruppo inesistente");
//			resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
//		}
//		log.info("Group {} was succesfully found ", group);
//
//		return group;
//	}
//
//	public List<String> getRoles(GroupDTO group) {
//		List<String> rolesList;
//		rolesList = ServiceFactory.getRolesService().getInstance().getRolesList(group);
//		return rolesList;
//	}
//
//}

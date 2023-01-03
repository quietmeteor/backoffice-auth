//package it.eforhum.backoffice.controller;
//
//import java.io.IOException;
//import java.time.LocalDateTime;
//import java.util.Enumeration;
//import java.util.List;
//
//import org.apache.commons.lang3.StringUtils;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//
//import it.eforhum.backoffice.dto.GroupDTO;
//import it.eforhum.backoffice.dto.UserDTO;
//import it.eforhum.backoffice.util.ServiceFactory;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//
//@WebServlet("/user-list")
//public class UserListController extends HttpServlet {
//
//	private static final long serialVersionUID = 1L;
//
//	private Logger log = LogManager.getLogger(UserListController.class);
//
//	public UserListController() {
//	}
//
//	@Override
//	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		log.info("Processing a GET request");
//
//		String action = request.getParameter("action");
//
//		log.info("action => {}", action);
//		if (action != null && (action.equalsIgnoreCase("detail") || action.equalsIgnoreCase("delete"))) {
//			UserDTO user = findUser(request, response);
//			if (action.equalsIgnoreCase("detail")) {
//				request.setAttribute("user", user);
//				request.getRequestDispatcher("user-detail.jsp").forward(request, response);
//
//			}
//			if (action.equalsIgnoreCase("edit")) {
//				request.setAttribute("user", user);
//				request.getRequestDispatcher("user-edit.jsp").forward(request, response);
//
//			}
//			if (action.equalsIgnoreCase("delete")) {
//				log.info("Set user with id {}", user.getId() + " deleted => true");
//
//				ServiceFactory.getUserService().deleteUserCompletely(user.getId());
//				;
//				request.setAttribute("user", user);
//				response.sendRedirect("user-list");
//			}
//
//		} else if (action != null && action.equalsIgnoreCase("create")) {
//			List<GroupDTO> groupList = ServiceFactory.getGroupService().getAllGroups();
//
//			request.setAttribute("groupList", groupList);
//			request.getRequestDispatcher("user-create.jsp").forward(request, response);
//
//		} else if (action != null && action.equalsIgnoreCase("edit")) {
//			List<GroupDTO> groupList = ServiceFactory.getGroupService().getAllGroups();
//
//			request.setAttribute("groupList", groupList);
//			
//			UserDTO user = findUser(request, response);
//			ServiceFactory.getUserService().findById(user.getId());
//
//			request.setAttribute("user", user);
//			request.setAttribute("action", action);
//			request.getRequestDispatcher("user-edit.jsp").forward(request, response);
//
//		} else {
//			List<UserDTO> userList = ServiceFactory.getUserService().getAllUsers();
//
//			request.setAttribute("userList", userList);
//
//			request.getRequestDispatcher("user-list.jsp").forward(request, response);
//		}
//
//	}
//
//	@Override
//	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		log.info("Processing a POST request");
//
//		String action = request.getParameter("action");
//		log.info("action => {}", action);
//		if (action != null && action.equalsIgnoreCase("create")) {
//			UserDTO newUser = new UserDTO();
//			Enumeration<String> parameterNames = request.getParameterNames();
//			List<GroupDTO> groupList = ServiceFactory.getGroupService().getAllGroups();
//
//			request.setAttribute("groupList", groupList);
//			while(parameterNames.hasMoreElements()) {
//				String paramName = parameterNames.nextElement();
//				String parameterValue = request.getParameter(paramName);
//				request.setAttribute(paramName, parameterValue);
//				request.setAttribute("groupList", groupList);
//			}
//			
//			if (StringUtils.isEmpty(request.getParameter("userName"))) {
//				log.warn("Nome utente nullo");
//				fillRequestAndForward(request, response, "Nome Utente", "user-create.jsp");
//				return;
//			}
//			newUser.setName(request.getParameter("userName"));
//
//			if (StringUtils.isEmpty(request.getParameter("lastName"))) {
//				log.warn("Cognome nuovo user nullo");
//				fillRequestAndForward(request, response, "Cognome", "user-create.jsp");
//				return;
//			}
//			newUser.setLastName(request.getParameter("lastName"));
//
//			if (StringUtils.isEmpty(request.getParameter("email"))) {
//				log.warn("Email nullo");
//				fillRequestAndForward(request, response, "Email", "user-create.jsp");
//				return;
//			}
//			newUser.setEmail(request.getParameter("email"));
//
//			if (StringUtils.isEmpty(request.getParameter("username"))) {
//				log.warn("Username nullo");
//				fillRequestAndForward(request, response, "Username", "user-create.jsp");
//				return;
//			}
//			newUser.setUsername(request.getParameter("username"));
//
//			if (StringUtils.isEmpty(request.getParameter("password"))) {
//				log.warn("Password nullo");
//				fillRequestAndForward(request, response, "Password", "user-create.jsp");
//				return;
//			}
//			newUser.setPassword(request.getParameter("password"));
//
//			newUser.setLastLogin(null);
//			newUser.setDateModifiedPass(null);
//
//			if (StringUtils.isEmpty(request.getParameter("groupName"))) {
//				log.warn("Gruppo nullo");
//				fillRequestAndForward(request, response, "Gruppo", "user-create.jsp");
//				return;
//			}
//			newUser.setGroupId(Integer.parseInt(request.getParameter("groupName")));
//
//			if (request.getParameter("verified") == null) {
//				newUser.setVerified(false);
//			} else if (request.getParameter("verified").equalsIgnoreCase("on")) {
//				newUser.setVerified(true);
//			}
//
//
//			ServiceFactory.getUserService().createUser(newUser);
//			log.info("User id {} e' stato creato ", newUser);
//
//			response.sendRedirect("user-list");
//		}
////-------------------------------------------EDIT-----------------------------------------------------------------------
//		else if (action != null && action.equalsIgnoreCase("edit")) {
//			UserDTO user = findUser(request, response);
//			UserDTO userToUpdate = new UserDTO();
//			
//			Enumeration<String> parameterNames = request.getParameterNames();
//            List<GroupDTO> groupList = ServiceFactory.getGroupService().getAllGroups();
//
//            request.setAttribute("groupList", groupList);
//            while(parameterNames.hasMoreElements()) {
//                String paramName = parameterNames.nextElement();
//                String parameterValue = request.getParameter(paramName);
//                request.setAttribute(paramName, parameterValue);
//                request.setAttribute("groupList", groupList);
//                request.setAttribute("user", user);
//            }
//
//			String idPar = request.getParameter("id");
//			if (idPar == null) {
//				log.warn("Id parameter is null");
//				request.setAttribute("errorMessage", "Id nullo");
//				response.sendError(HttpServletResponse.SC_BAD_REQUEST);
//			}
//
//			int id = -1;
//			try {
//				id = Integer.parseInt(idPar);
//			} catch (NumberFormatException e) {
//				log.error("Id parameter must be a number");
//				request.setAttribute("errorMessage", "Formato id sbagliato");
//				response.sendError(HttpServletResponse.SC_BAD_REQUEST);
//			}
//			userToUpdate.setId(user.getId());
//
//			if (StringUtils.isEmpty(request.getParameter("userName"))) {
//				log.warn("Nome utente nullo");
//				fillRequestAndForward(request, response, "Nome", "user-edit.jsp");
//				return;
//
//			}
//			userToUpdate.setName(request.getParameter("userName"));
//
//			if (StringUtils.isEmpty(request.getParameter("lastName"))) {
//				log.warn("Cognome nuovo user nullo");
//				fillRequestAndForward(request, response, "Cognome", "user-edit.jsp");
//				return;
//			}
//			userToUpdate.setLastName(request.getParameter("lastName"));
//
//			if (StringUtils.isEmpty(request.getParameter("email"))) {
//				log.warn("Email nullo");
//				fillRequestAndForward(request, response, "Email", "user-edit.jsp");
//				return;
//			}
//			userToUpdate.setEmail(request.getParameter("email"));
//
//			if (StringUtils.isEmpty(request.getParameter("username"))) {
//				log.warn("Username nullo");
//				fillRequestAndForward(request, response, "Username", "user-edit.jsp");
//				return;
//			}
//			userToUpdate.setUsername(request.getParameter("username"));
//
//			if (StringUtils.isEmpty(request.getParameter("password"))) {
//				log.warn("Password nullo");
//				fillRequestAndForward(request, response, "Password", "user-edit.jsp");
//				return;
//			}
//			userToUpdate.setPassword(request.getParameter("password"));
//
//			if (StringUtils.isEmpty(request.getParameter("lastLogin"))) {
//				log.warn("Data ultimo login nulla");
//				fillRequestAndForward(request, response, "Ultimo accesso", "user-edit.jsp");
//				return;
//			}
//			userToUpdate.setLastLogin(LocalDateTime.parse(request.getParameter("lastLogin")));
//			userToUpdate.setDateModifiedPass(LocalDateTime.now());
//
//			if (StringUtils.isEmpty(request.getParameter("groupName"))) {
//				log.warn("Gruppo nullo");
//				fillRequestAndForward(request, response, "Gruppo", "user-edit.jsp");
//				return;
//			}
//			userToUpdate.setGroupId(Integer.parseInt(request.getParameter("groupName")));
//			
//			if (request.getParameter("verified") == null) {
//				userToUpdate.setVerified(false);
//			} else if (request.getParameter("verified").equalsIgnoreCase("on")) {
//				userToUpdate.setVerified(true);
//			}
//
//			if (request.getParameter("deleted") == null) {
//				userToUpdate.setDeleted(false);
//			} else if (request.getParameter("deleted").equalsIgnoreCase("on")) {
//				userToUpdate.setDeleted(true);
//			}
//
//			log.info("Utente con id {} e' stato aggiornato ", userToUpdate);
//			ServiceFactory.getUserService().updateUser(userToUpdate, user.getId());
//
//			response.sendRedirect("user-list");
//
//		}
//	}
//
//	private void fillRequestAndForward(HttpServletRequest request, HttpServletResponse response, String fieldName,
//			String jspPage) throws ServletException, IOException {
//		request.setAttribute("errorMessage", fieldName + " deve essere valorizzato");
//		request.getRequestDispatcher(jspPage).forward(request, response);
//
//	}
//
//	public UserDTO findUser(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		log.info("");
//		log.info("doGet Request for /user recieved");
//
//		String idPar = request.getParameter("id");
//		if (idPar == null) {
//			log.warn("Id parameter is null");
//			request.setAttribute("errorMessage", "Id nullo");
//			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
//		}
//
//		int id = -1;
//
//		try {
//			id = Integer.parseInt(idPar);
//		} catch (NumberFormatException e) {
//			log.error("Id parameter must be a number");
//			request.setAttribute("errorMessage", "Formato id sbagliato");
//			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
//		}
//
//		UserDTO user = ServiceFactory.getUserService().findById(id);
//
//		if (user == null) {
//			log.warn("The user with id {} is null", id);
//			request.setAttribute("errorMessage", "Gruppo inesistente");
//			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
//		}
//		log.info("user {} was succesfully found ", user);
//
//		return user;
//	}
//
//}
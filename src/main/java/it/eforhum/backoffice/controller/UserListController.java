package it.eforhum.backoffice.controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import it.eforhum.backoffice.dto.GroupDTO;
import it.eforhum.backoffice.dto.UserDTO;
import it.eforhum.backoffice.entity.User;
import it.eforhum.backoffice.service.UserService;
import it.eforhum.backoffice.util.ServiceFactory;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/user-list")
public class UserListController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private Logger log = LogManager.getLogger(UserListController.class);

	public UserListController() {
	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.info("Processing a GET request");

		String action = request.getParameter("action");
		log.info("action => {}", action);
		if (action != null && (action.equalsIgnoreCase("detail") || action.equalsIgnoreCase("edit")
				|| action.equalsIgnoreCase("delete"))) {

			UserDTO user = findUser(request, response);
			if (action.equalsIgnoreCase("detail")) {
				request.setAttribute("user", user);
				request.getRequestDispatcher("user-detail.jsp").forward(request, response);

			}
			if (action.equalsIgnoreCase("edit")) {
				request.setAttribute("user", user);
				request.getRequestDispatcher("user-edit.jsp").forward(request, response);

			}
			if (action.equalsIgnoreCase("delete")) {
				log.info("Set user with id {}", user.getId() + " deleted => true");
				
				ServiceFactory.getUserService().deleteUserCompletely(user.getId());;
				request.setAttribute("user", user);
				response.sendRedirect("user-list");
			}

		} else if (action != null && action.equalsIgnoreCase("create")) {
			List<GroupDTO> groupList = ServiceFactory.getGroupService().getAllGroups();

			request.setAttribute("groupList", groupList);
			request.getRequestDispatcher("user-create.jsp").forward(request, response);

		} else {
			List<UserDTO> userList = ServiceFactory.getUserService().getAllUsers();

			request.setAttribute("userList", userList);

			request.getRequestDispatcher("user-list.jsp").forward(request, response);
		}

	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.info("Processing a POST request");

		String action = request.getParameter("action");
		log.info("action => {}", action);
		if (action != null && action.equalsIgnoreCase("create")) {
			UserDTO newUser = new UserDTO();

			if (StringUtils.isEmpty(request.getParameter("userName"))) {
				log.warn("Nome utente nullo");
				request.setAttribute("errorMessage", "Nome deve essere valorizzato!");
				return;
			}
			newUser.setName(request.getParameter("userName"));

			if (StringUtils.isEmpty(request.getParameter("lastName"))) {
				log.warn("Cognome nuovo user nullo");
				request.setAttribute("errorMessage", "Cognome deve essere valorizzato");
			}
			newUser.setLastName(request.getParameter("lastName"));

			if (StringUtils.isEmpty(request.getParameter("email"))) {
				log.warn("Email nullo");
				request.setAttribute("errorMessage", "Email deve essere valorizzato");
			}
			newUser.setEmail(request.getParameter("email"));

			if (StringUtils.isEmpty(request.getParameter("username"))) {
				log.warn("Username nullo");
				request.setAttribute("errorMessage", "Username deve essere valorizzato");
			}
			newUser.setUsername(request.getParameter("username"));

			if (StringUtils.isEmpty(request.getParameter("password"))) {
				log.warn("Password nullo");
				request.setAttribute("errorMessage", "Passord deve essere valorizzato");
			}
			newUser.setPassword(request.getParameter("password"));

			newUser.setLastLogin(null);
			newUser.setDateModifiedPass(null);

			String nomeGruppo = request.getParameter("groupName");
			log.info("Nome gruppo => {}", nomeGruppo);

			if (StringUtils.isEmpty(request.getParameter("groupName"))) {
				log.warn("Gruppo nullo");
				request.setAttribute("errorMessage", "Nome gruppo deve essere valorizzato");
			}
			newUser.setGroupName(request.getParameter("groupName"));

			newUser.setVerified(false);

			ServiceFactory.getUserService().createUser(newUser);
			log.info("Gruppo id {} e' stato creato ", newUser);

			response.sendRedirect("user-list");
		}

		else if (action != null && action.equalsIgnoreCase("edit")) {
			UserDTO user = findUser(request, response);
			request.setAttribute("user", user);

			UserDTO userToUpdate = new UserDTO();

			if (StringUtils.isEmpty(request.getParameter("userName"))) {
				log.warn("Nome utente nullo");
				request.setAttribute("errorMessage", "Nome deve essere valorizzato!");
				return;
			}
			userToUpdate.setName(request.getParameter("userName"));

			if (StringUtils.isEmpty(request.getParameter("lastName"))) {
				log.warn("Cognome nuovo user nullo");
				request.setAttribute("errorMessage", "Cognome deve essere valorizzato");
			}
			userToUpdate.setLastName(request.getParameter("lastName"));

			if (StringUtils.isEmpty(request.getParameter("email"))) {
				log.warn("Email nullo");
				request.setAttribute("errorMessage", "Email deve essere valorizzato");
			}
			userToUpdate.setEmail(request.getParameter("email"));

			if (StringUtils.isEmpty(request.getParameter("username"))) {
				log.warn("Username nullo");
				request.setAttribute("errorMessage", "Username deve essere valorizzato");
			}
			userToUpdate.setUsername(request.getParameter("username"));

			if (StringUtils.isEmpty(request.getParameter("password"))) {
				log.warn("Password nullo");
				request.setAttribute("errorMessage", "Passord deve essere valorizzato");
			}
			userToUpdate.setPassword(request.getParameter("password"));

			if (StringUtils.isEmpty(request.getParameter("lastLogin"))) {
				log.warn("Data ultimo login nulla");
				request.setAttribute("errorMessage", "Ultimo accesso deve essere valorizzato");
			}
			userToUpdate.setLastLogin(LocalDateTime.parse(request.getParameter("lastLogin")));

			userToUpdate.setDateModifiedPass(LocalDateTime.now());

			if (StringUtils.isEmpty(request.getParameter("groupName"))) {
				log.warn("Nome gruppo nullo");
				request.setAttribute("errorMessage", "Nome gruppo deve essere valorizzato");
			}
			String nomeGruppo = request.getParameter("groupName");
			log.info("Nome gruppo => {}", nomeGruppo);
			userToUpdate.setGroupName(request.getParameter("groupName"));

			if (StringUtils.isEmpty(request.getParameter("verified"))) {
				log.warn("Utente verificato nullo");
				request.setAttribute("errorMessage", "Campo verificato deve essere valorizzato");
			}
			userToUpdate.setVerified(Boolean.parseBoolean(request.getParameter("verified")));

			if (StringUtils.isEmpty(request.getParameter("deleted"))) {
				log.warn("Eliminato nullo");
				request.setAttribute("errorMessage", "Campo eliminato deve essere valorizzato");
			}
			userToUpdate.setDeleted(Boolean.parseBoolean(request.getParameter("deleted")));

			ServiceFactory.getUserService().createUser(userToUpdate);
			log.info("Utente con id {} e' stato creato ", userToUpdate);

			response.sendRedirect("user-list");

		}
	}

	public UserDTO findUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.info("");
		log.info("doGet Request for /user-detail recieved");

		String idPar = request.getParameter("id");
		if (idPar == null) {
			log.warn("Id parameter is null");
			request.setAttribute("error_message", "Id nullo");
			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
		}

		int id = -1;

		try {
			id = Integer.parseInt(idPar);
		} catch (NumberFormatException e) {
			log.error("Id parameter must be a number");
			request.setAttribute("error_message", "Formato id sbagliato");
			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
		}

		UserDTO user = ServiceFactory.getUserService().findById(id);

		if (user == null) {
			log.warn("The user with id {} is null", id);
			request.setAttribute("error_message", "Gruppo inesistente");
			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
		}
		log.info("user {} was succesfully found ", user);

		return user;
	}

}

package it.eforhum.backoffice.controller;

import java.io.IOException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import it.eforhum.backoffice.dto.UserDTO;
import it.eforhum.backoffice.enums.Roles;
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
		log.info("action => {}",action);
		if (!action.isBlank() 
				&& (action.equalsIgnoreCase("detail") 
				|| action.equalsIgnoreCase("edit")
				|| action.equalsIgnoreCase("delete"))) {
			
			UserDTO user = findUser(request, response);
			if (action.equalsIgnoreCase("detail")) {
				request.setAttribute("user", user);
				request.getRequestDispatcher("user-detail.jsp").forward(request, response);
			} else if (action.equalsIgnoreCase("edit")) {
				// TODO
			} else if (action.equalsIgnoreCase("delete")) {
				log.info("Set user with id {}", user.getId() + "deleted => true");
				user.setDeleted(true);
				response.sendRedirect("user-list");
			} else if (action != null && action.equalsIgnoreCase("create")) {
				request.getRequestDispatcher("user-create.jsp").forward(request, response);

			}

		} else if (action.isBlank()){
			List<UserDTO> userList = ServiceFactory.getUserService().getAllUsers();

			request.setAttribute("userList", userList);

			request.getRequestDispatcher("user-list.jsp").forward(request, response);

		}

	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.info("Processing a POST request");

		String action = request.getParameter("action");

		if (action != null && action.equalsIgnoreCase("create")) {
			UserDTO newUser = new UserDTO();

			if (request.getParameter("userName") == null) {
				log.warn("Nome utente nullo");
				request.setAttribute("errorMessage", "Nome deve essere valorizzato!");
				return;
			}
			newUser.setName(request.getParameter("userName"));

			if (request.getParameter("lastName") == null) {
				log.warn("Cognome nuovo user nullo");
				request.setAttribute("errorMessage", "Cognome deve essere valorizzato");
			}
			newUser.setLastName(request.getParameter("lastName"));

			if (request.getParameter("email") == null) {
				log.warn("Email nullo");
				request.setAttribute("errorMessage", "Email deve essere valorizzato");
			}
			newUser.setEmail(request.getParameter("email"));

			if (request.getParameter("username") == null) {
				log.warn("Username nullo");
				request.setAttribute("errorMessage", "Username deve essere valorizzato");
			}
			newUser.setUsername(request.getParameter("username"));

			if (request.getParameter("password") == null) {
				log.warn("Password nullo");
				request.setAttribute("errorMessage", "Passord deve essere valorizzato");
			}
			newUser.setPassword(request.getParameter("password"));

			newUser.setLastLogin(null);
			newUser.setDateModifiedPass(null);
			newUser.setGroupName(Roles.USER.toString());
			newUser.setVerified(false);

			ServiceFactory.getUserService().createUser(newUser);
			log.info("Gruppo id {} e' stato creato ", newUser);

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

package it.eforhum.backoffice.controller;

import java.io.IOException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import it.eforhum.backoffice.entity.User;
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
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		List<User> userList = ServiceFactory.getUserService().findAll();
		log.info("Lista utenti: ", userList.toString());
		
		request.setAttribute("userList", userList);
		
		request.getRequestDispatcher("user-list.jsp").forward(request, response);
	}

}

package it.eforhum.backoffice.controller;

import java.io.IOException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import it.eforhum.backoffice.entity.UserGroups;
import it.eforhum.backoffice.util.ServiceFactory;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/group-list")
public class GroupListController extends HttpServlet {
	private static final Logger log = LogManager.getLogger(GroupListController.class);
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		List<UserGroups> groupList = ServiceFactory.getGroupService().getAllGroups();
		log.info("La lista di gruppi e': ", groupList);
		
		req.setAttribute("groupList", groupList);

		req.getRequestDispatcher("group-list.jsp").forward(req, resp);
	}
}

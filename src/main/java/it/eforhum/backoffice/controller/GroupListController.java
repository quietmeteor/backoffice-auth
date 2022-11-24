package it.eforhum.backoffice.controller;

import java.io.IOException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import it.eforhum.backoffice.dto.GroupDTO;
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

		List<GroupDTO> groupList = ServiceFactory.getGroupService().getAllGroups();
		log.info("Ricerca dei gruppi effetuata");

		req.setAttribute("groupList", groupList);

		String action = req.getParameter("action");

		if (action != null && action.equalsIgnoreCase("detail")) {
			log.info("doGet Request for /group-detail recieved");

			GroupDTO group = findGroup(req, resp);

			req.setAttribute("group", group);
			req.getRequestDispatcher("group-detail.jsp").forward(req, resp);

		}

		else if (action != null && action.equalsIgnoreCase("deleteEntity")) {

			log.info("");
			log.info("Request recieved, action delete");

			GroupDTO group = findGroup(req, resp);
			ServiceFactory.getGroupService().deleteGroup(group);

			log.info("Group was succesfully deleted");

			resp.sendRedirect("group-list");

		} 
			else if (action != null && action.equalsIgnoreCase("create")) {
			log.info("Request Recieved, action create");

			resp.sendRedirect("group-edit");

		}

		else {
			req.getRequestDispatcher("group-list.jsp").forward(req, resp);
		}

	}

//	@Override
//	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//
//
//
//	}
	
	
	
	
	

	public GroupDTO findGroup(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		log.info("");
		log.info("doGet Request for /group-detail recieved");

		String idPar = req.getParameter("id");
		if (idPar == null) {
			log.warn("Id parameter is null");
			req.setAttribute("error_message", "Id nullo");
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
		}

		int id = -1;

		try {
			id = Integer.parseInt(idPar);
		} catch (NumberFormatException e) {
			log.error("Id parameter must be a number");
			req.setAttribute("error_message", "Formato id sbagliato");
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
		}

		GroupDTO group = ServiceFactory.getGroupService().findGroupById(id);

		if (group == null) {
			log.warn("The group with id {} is null", id);
			req.setAttribute("error_message", "Gruppo inesistente");
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
		}
		log.info("Group {} was succesfully found ", group);

		return group;
	}

}

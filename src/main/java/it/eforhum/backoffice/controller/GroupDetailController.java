package it.eforhum.backoffice.controller;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import it.eforhum.backoffice.dto.GroupDTO;
import it.eforhum.backoffice.util.ServiceFactory;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/group-detail")
public class GroupDetailController extends HttpServlet {
	private static final Logger log = LogManager.getLogger(GroupDetailController.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		log.info("");
		log.info("doGet Request for /group-detail recieved");

		String idPar = req.getParameter("id");
		if (idPar == null) {
			log.warn("Id parameter is null");
			// to do add error
		}

		int id = -1;

		try {
			id = Integer.parseInt(idPar);
		} catch (NumberFormatException e) {
			log.error("Id parameter must be a number");
			// todo add error
		}

		GroupDTO group = ServiceFactory.getGroupService().findGroupById(id);

		if (group == null) {
			log.warn("The group with id {} is null", id);
			// todo add err
		}
		log.info("Group {} was succesfully found ", group);
		
		req.setAttribute("group", group);
		req.getRequestDispatcher("group-detail.jsp").forward(req, resp);

	}
}

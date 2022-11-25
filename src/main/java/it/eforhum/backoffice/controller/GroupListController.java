package it.eforhum.backoffice.controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import it.eforhum.backoffice.dto.GroupDTO;
import it.eforhum.backoffice.entity.UserGroups;
import it.eforhum.backoffice.enums.Roles;
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
			log.info("doGet Request per /group-detail ricevuta");

			GroupDTO group = findGroup(req, resp);

			req.setAttribute("group", group);
			req.getRequestDispatcher("group-detail.jsp").forward(req, resp);

		}

		else if (action != null && action.equalsIgnoreCase("deleteEntity")) {

			log.info("");
			log.info("Request recieved, action delete");

			GroupDTO group = findGroup(req, resp);
			ServiceFactory.getGroupService().deleteGroup(group.getId());

			log.info("Gruppo e' stato eliminato");

			resp.sendRedirect("group-list");

		} else if (action != null && action.equalsIgnoreCase("create")) {
			log.info("Request Recieved, action create");
			req.setAttribute("action", action);
			req.getRequestDispatcher("group-create.jsp").forward(req, resp);

		} else if (action != null && action.equalsIgnoreCase("edit")) {
			log.info("Request Recieved, action edit");
			GroupDTO group = findGroup(req, resp);
			req.setAttribute("group", group);
			log.info("Richiesta modifica di gruppo id {} ", group.getId());
			req.setAttribute("action", action);
			req.getRequestDispatcher("group-edit.jsp").forward(req, resp);
		}

		else {
			req.getRequestDispatcher("group-list.jsp").forward(req, resp);
		}

	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String action = req.getParameter("action");
		log.info("Action is {} ", action);

		if (action != null && action.equalsIgnoreCase("create")) {
			log.info("Richeiesta crea ricevuta");
			GroupDTO newGroup = new GroupDTO();

			if (StringUtils.isEmpty(req.getParameter("groupName"))) {
				log.warn("Nome gruppo nullo");
				req.setAttribute("errorMessage", "Nome deve essere valorizzato!");
				req.getRequestDispatcher("group-create.jsp").forward(req, resp);
				return;
			}

			newGroup.setGroupName(req.getParameter("groupName"));

			if (StringUtils.isEmpty(req.getParameter("roles"))) {
				log.warn("Ruoli gruppo nulli");
				req.setAttribute("errorMessage", "Ruoli devono essere valorizzati");
				req.getRequestDispatcher("group-create.jsp").forward(req, resp);
				return;
			}

			List<Roles> roles = new ArrayList<>();
			roles.add(Roles.USER);
			newGroup.setRoles(roles);

			if (StringUtils.isEmpty(req.getParameter("permissions"))) {
				log.warn("Permessi nulli");
				req.setAttribute("errorMessage", "Permessi devono essere valorizzati");
				req.getRequestDispatcher("group-create.jsp").forward(req, resp);
				return;
			}

			if (StringUtils.isEmpty(req.getParameter("creationUser"))) {
				log.warn("User creazione nullo");
				req.setAttribute("errorMessage", "User creazione deve essere valorizzato");
				req.getRequestDispatcher("group-create.jsp").forward(req, resp);
				return;
			}

			newGroup.setCreationUser(req.getParameter("creationUser"));
			newGroup.setPermissions("BASE_PERMISSIONS");

			newGroup.setEnabled(Boolean.parseBoolean(req.getParameter("enabled")));

			newGroup.setCreationTime(LocalDateTime.now());

			ServiceFactory.getGroupService().createGroup(newGroup);
			log.info("Gruppo id {} e' stato creato ", newGroup);

			resp.sendRedirect("group-list");

		} else if (action != null && action.equalsIgnoreCase("edit")) {

			log.info("Richeiesta edit ricevuta");
			GroupDTO groupUpd = new GroupDTO();
			GroupDTO group = findGroup(req, resp);
			req.setAttribute("group", group);

			String idPar = req.getParameter("id");
			log.info("ID e' {} ", idPar);
			if (idPar == null) {
				log.warn("Id parameter is null");
				req.setAttribute("errorMessage", "Id nullo");
				resp.sendError(HttpServletResponse.SC_BAD_REQUEST);

			}

			int id = -1;

			try {
				id = Integer.parseInt(idPar);
			} catch (NumberFormatException e) {
				log.error("Id parameter must be a number");
				req.setAttribute("errorMessage", "Formato id sbagliato");

				resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
			}
			groupUpd.setId(id);

			log.info("Il nome e' {}", req.getParameter("groupName"));

			if (StringUtils.isEmpty(req.getParameter("groupName"))) {
				log.warn("Nome gruppo nullo");
				req.setAttribute("errorMessage", "Nome deve essere valorizzato!");
				req.getRequestDispatcher("group-edit.jsp").forward(req, resp);
				return;
			}

			groupUpd.setGroupName(req.getParameter("groupName"));

			if (StringUtils.isEmpty(req.getParameter("roles"))) {
				log.warn("Ruoli gruppo nulli");
				req.setAttribute("errorMessage", "Ruoli devono essere valorizzati");
				req.getRequestDispatcher("group-edit.jsp").forward(req, resp);
				return;
			}

			List<Roles> roles = new ArrayList<>();
			roles.add(Roles.ROLE_CREATE_RISORSE);
			groupUpd.setRoles(roles);

			if (StringUtils.isEmpty(req.getParameter("permissions"))) {
				log.warn("Permessi nulli");
				req.setAttribute("errorMessage", "Permessi devono essere valorizzati");
				req.getRequestDispatcher("group-edit.jsp").forward(req, resp);
				return;
			}

			groupUpd.setPermissions("CREATE RISORSE");
			log.info("User e' {} ", req.getParameter("creationUser"));

			if (StringUtils.isEmpty(req.getParameter("creationUser"))) {
				log.warn("User creazione nullo");
				req.setAttribute("errorMessage", "User creazione deve essere valorizzato");
				req.getRequestDispatcher("group-edit.jsp").forward(req, resp);
				return;
			}

			groupUpd.setCreationUser(req.getParameter("creationUser"));

			if (StringUtils.isEmpty(req.getParameter("updateUser"))) {
				log.warn("User aggiornamento nullo");
				req.setAttribute("errorMessage", "User aggiornamento deve essere valorizzato");

				req.getRequestDispatcher("group-edit.jsp").forward(req, resp);
				return;
			}

			groupUpd.setUpdateUser(req.getParameter("updateUser"));

			groupUpd.setEnabled(Boolean.parseBoolean(req.getParameter("enabled")));

			groupUpd.setCreationTime(LocalDateTime.parse(req.getParameter("creationTime")));

			groupUpd.setUpdateTime(LocalDateTime.now());
//			
//			if(groupUpd==null) {
//				GroupDTO groupToUpdate=findGroup(req, resp);
//				req.setAttribute("group", groupToUpdate);
//			}

			ServiceFactory.getGroupService().updateGroup(groupUpd.getId(), groupUpd);

			resp.sendRedirect("group-list");

		} else {
			resp.sendRedirect("group-list");
		}

	}

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

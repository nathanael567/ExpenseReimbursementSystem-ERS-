package com.reimbursement.main;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.reimbursement.model.User;

public class ManagerController {
	final static Logger log = Logger.getAnonymousLogger();

	public static String Home(HttpServletRequest request, HttpServletResponse response) {

		User ManagerUser = (User) request.getSession().getAttribute("CurrentUser");
		try {
			response.getWriter().write(new ObjectMapper().writeValueAsString(ManagerUser));
			log.info("The manager is now logged in the manager home site! :) ");
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "manager.html";
	}

}
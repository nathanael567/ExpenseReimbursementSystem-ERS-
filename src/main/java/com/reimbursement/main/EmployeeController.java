package com.reimbursement.main;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.reimbursement.model.User;

public class EmployeeController {
	final static Logger log = Logger.getAnonymousLogger();
	
	public static String Home(HttpServletRequest request, HttpServletResponse response) {
		User EmployeeUser = (User)request.getSession().getAttribute("CurrentUser");
		try {
			response.getWriter().write(new ObjectMapper().writeValueAsString(EmployeeUser));
			log.info("Employee is now logged in the employee home site! :)");
		} catch (JsonProcessingException e) {
			log.info("JSON failed!");
			e.printStackTrace();
		} catch (IOException e) {
			log.info("Failed going to employee home");
			e.printStackTrace();
		}
		return "employee.html";
	}
	
	

}

package com.reimbursement.main;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.reimbursement.model.User;

public class EmployeeController {
	
	public static String Home(HttpServletRequest request, HttpServletResponse response) {
		User EmployeeUser = (User)request.getSession().getAttribute("CurrentUser");
		try {
			response.getWriter().write(new ObjectMapper().writeValueAsString(EmployeeUser));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "employee.html";
	}
	
	

}

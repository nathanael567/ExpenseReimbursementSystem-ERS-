package com.reimbursement.main;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RequestHelper {
	
	public static String process(HttpServletRequest request, HttpServletResponse response) {

		switch (request.getRequestURI()) {
		case "/project1/login.change":
			try {
				return LoginController.login(request);
			} catch (NullPointerException e) {
				return "index.html";
			}
		case "/project1/employee.change":
			return EmployeeController.Home(request, response);
		case "/project1/manager.change":
			return ManagerController.Home(request, response);
		case "/project1/submitTicket.change": 
			return TicketController.submitTicket(request);
		case "/project1/approveTicket.change":
			return TicketController.approveTicket(request);
		case "/project1/denyTicket.change":
			return TicketController.denyTicket(request);
		default:

			return "index.html";
		}

	}

}
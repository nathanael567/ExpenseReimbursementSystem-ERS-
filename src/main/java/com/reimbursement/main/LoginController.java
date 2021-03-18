package com.reimbursement.main;

import javax.servlet.http.HttpServletRequest;

import com.reimbursement.dao.UserDAO;
import com.reimbursement.model.User;

public class LoginController {

	
	public static String login(HttpServletRequest request){
		UserDAO user = new UserDAO();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		User newUser = new User();
		newUser = user.getUser(username,password);
        System.out.println(newUser);
			
			if(username.equals(newUser.getUsername()) && password.equals(newUser.getPassword())) {
				if(newUser.getRoleid() == 1) { 
					request.getSession().setAttribute("CurrentUser", newUser); 
					return "employee.change";
				}
				else if(newUser.getRoleid() == 2) {
					request.getSession().setAttribute("CurrentUser", newUser); 
					return "manager.change";
				}
           
            }
			
			    return "index.html";
        }
}

package com.reimbursement.main;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/logout")
public class LogoutController extends HttpServlet{
    private static final long serialVersionUID = 1L;

    @Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession(false);		
		if (session != null) {
            req.getSession().removeAttribute("CurrentUser");
			session.invalidate();
			resp.sendRedirect("index.html");
		}
	
	}

}

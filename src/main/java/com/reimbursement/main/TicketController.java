package com.reimbursement.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.reimbursement.dao.TicketDAO;
import com.reimbursement.model.Ticket;
import com.reimbursement.model.User;

public class TicketController {
    final static Logger log = Logger.getAnonymousLogger();
    
    public static String submitTicket(HttpServletRequest request) {
        User EmployeeUser = (User) request.getSession().getAttribute("CurrentUser");
        TicketDAO ticketDAO = new TicketDAO();

        int ticketid = 0;
        double amount = Double.parseDouble(request.getParameter("ticketAmount"));

        Date date = new Date();
        Timestamp submitted = new Timestamp(date.getTime());
        Timestamp resolved = null;
        String description = request.getParameter("ticketDesc");

        int author = EmployeeUser.getUserid();
        int resolver = 4;
        int statusid = 1;
        String ticketType = request.getParameter("ticketType");
        int typeid =0;
        switch (ticketType) {
        case "Lodging":
            typeid = 1;
            break;
        case "Travel":
            typeid = 2;
            break;
        case "Food":
            typeid = 3;
            break;
        case "Other":
            typeid = 4;
            break;
        }

        Ticket newTicket = new Ticket(ticketid, amount, submitted, resolved, description, author, resolver, statusid,
                typeid);

        ticketDAO.insertTicket(newTicket);
        log.info("Sucessfully submitted ticket! :)");
        request.getSession().setAttribute("CurrentUser", EmployeeUser);
        
        return "employee.html";

    }

    public static void displayTickets(HttpServletRequest request, HttpServletResponse response) {

        User EmployeeUser = (User) request.getSession().getAttribute("CurrentUser");
        TicketDAO tick = new TicketDAO();
        int id = EmployeeUser.getUserid();
        ArrayList<Ticket> ticketList = tick.getTicketById(id);
        try {
            response.getWriter().write(new ObjectMapper().writeValueAsString(ticketList)); 
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void displayAllTickets(HttpServletRequest request, HttpServletResponse response) {
        TicketDAO tick = new TicketDAO();
        ArrayList<Ticket> ticketList = tick.getAllTicket();
        try {
            response.getWriter().write(new ObjectMapper().writeValueAsString(ticketList));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static String approveTicket(HttpServletRequest request) {
        User EmployeeUser = (User) request.getSession().getAttribute("CurrentUser");
        TicketDAO tick = new TicketDAO();
		try {
            BufferedReader reader = request.getReader();
			String data = reader.readLine();
            tick.approveTicket(data);
		} catch (IOException e) {
			e.printStackTrace();
		}
        
        log.info("Successfully approved tickets! :)");
        request.getSession().setAttribute("CurrentUser", EmployeeUser);

        return "manager.html";

    }

    public static String denyTicket(HttpServletRequest request) {
        User EmployeeUser = (User) request.getSession().getAttribute("CurrentUser");
        TicketDAO tick = new TicketDAO();
		try {
            BufferedReader reader = request.getReader();
			String data = reader.readLine();
            tick.denyTicket(data);
		} catch (IOException e) {
			e.printStackTrace();
		}
        
        log.info("Successfully Denied");
        request.getSession().setAttribute("CurrentUser", EmployeeUser);

        return "manager.html";

    }

}

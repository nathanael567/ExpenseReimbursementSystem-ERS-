package com.reimbursement.main;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.fasterxml.jackson.core.JsonProcessingException;

public class JSONRequestHelper {
	public static void process(HttpServletRequest req, HttpServletResponse res)
			throws JsonProcessingException, IOException {
		System.out.println(req.getRequestURI());
		switch (req.getRequestURI()) {
		case "/project1/displayTickets.json":
			TicketController.displayTickets(req, res);
			break;
		case "/project1/displayAllTickets.json":
			TicketController.displayAllTickets(req, res);
			break;
		}

	}

}

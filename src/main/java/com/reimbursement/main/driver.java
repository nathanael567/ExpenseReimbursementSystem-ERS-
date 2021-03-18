package com.reimbursement.main;

import java.sql.Timestamp;

import com.reimbursement.dao.TicketDAO;
import com.reimbursement.model.Ticket;

public class driver {
    public static void main(String[] args) {
        Timestamp now =new Timestamp(System.currentTimeMillis()); 
        Ticket ticket = new Ticket(123,342,now,null,"Everthing was good",1,2,3,2);
        TicketDAO tickettt = new TicketDAO();

        tickettt.insertTicket(ticket);
    }
    
    
}

package com.reimbursement.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Logger;

import com.reimbursement.model.Ticket;
import com.reimbursement.util.ConnectionUtil;

/*
* Welcome to The Ticket DAO!
*
*/
public class TicketDAO {
    final static Logger log = Logger.getAnonymousLogger();

    
    public void insertTicket(Ticket x) {
        try {
            Connection conn = ConnectionUtil.getConnectionUtil().getConnection();
            PreparedStatement ps = conn
                    .prepareStatement("INSERT INTO REIMBURSEMENTS VALUES (nextval('rebursesequence'),?,?,?,?,?,?,?,?)");
            ps.setDouble(1, x.getAmount());
            ps.setTimestamp(2, x.getSubmitted());
            ps.setTimestamp(3, x.getResolved());
            ps.setString(4, x.getDescription());

            ps.setInt(5, x.getAuthor());
            ps.setInt(6, x.getResolver());
            ps.setInt(7, x.getStatusid());
            ps.setInt(8, x.gettypeid());
            ps.executeUpdate();

            log.info("Success while inserting ticket! :)");

        } catch (SQLException e) {
            log.info("Failed while inserting ticket!");
            e.printStackTrace();
        }

    }

    public ArrayList<Ticket> getTicket(int x) {
        ArrayList<Ticket> ticketList = new ArrayList<Ticket>();
        try {
            Connection conn = ConnectionUtil.getConnectionUtil().getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM REIMBURSEMENTS WHERE reburseid =?");
            ps.setInt(1, x);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ticketList.add(new Ticket(
                    rs.getInt(1),
                    rs.getDouble(2),
                    rs.getTimestamp(3), 
                    rs.getTimestamp(4),
                    rs.getString(5),
                    rs.getInt(6),
                    rs.getInt(7), 
                    rs.getInt(8),
                    rs.getInt(9)));
            }
            log.info("Success in getting ticket by reburseID! :)");
        } catch (SQLException e) {
            log.info("Failed to get ticket!");
            e.printStackTrace();
        }
       
        return ticketList;

    }
    public ArrayList<Ticket> getAllTicket() {
        ArrayList<Ticket> ticketList = new ArrayList<Ticket>();
        try {
            Connection conn = ConnectionUtil.getConnectionUtil().getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM REIMBURSEMENTS");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ticketList.add(new Ticket(
                    rs.getInt(1),
                    rs.getDouble(2),
                    rs.getTimestamp(3), 
                    rs.getTimestamp(4),
                    rs.getString(5),
                    rs.getInt(6),
                    rs.getInt(7), 
                    rs.getInt(8),
                    rs.getInt(9)));
            }
            log.info("Success with getting tickets! :)");
        } catch (SQLException e) {
            log.info("Failed getting all tickets!");
            e.printStackTrace();
        }
       
        return ticketList;

    }
    public ArrayList<Ticket> getTicketById(int x) {
        ArrayList<Ticket> ticketList = new ArrayList<Ticket>();
        try {
            Connection conn = ConnectionUtil.getConnectionUtil().getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM REIMBURSEMENTS WHERE reburseauthor = '" + x + "';");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ticketList.add(new Ticket(
                    rs.getInt(1),
                    rs.getDouble(2),
                    rs.getTimestamp(3), 
                    rs.getTimestamp(4),
                    rs.getString(5),
                    rs.getInt(6),
                    rs.getInt(7), 
                    rs.getInt(8),
                    rs.getInt(9)));
            }
            log.info("Success with getting ticket by ID! :)");
        } catch (SQLException e) {
            log.info("Failed with getting ticket by ID!");
            e.printStackTrace();
        }
       
        return ticketList;

    }


    
    public void approveTicket(String data) {
		Date date= new Date();
		Timestamp current_date = new Timestamp(date.getTime());
        String sql = "UPDATE REIMBURSEMENTS SET REBURSERESOLVED = ? " + ", REBURSESTATUSID = ? "
				+ "WHERE REBURSEID = '" + data + "';";

		try {
			Connection conn = ConnectionUtil.getConnectionUtil().getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
            int approved = 2;
			conn.setAutoCommit(false);
            ps.setTimestamp(1, current_date);
            ps.setInt(2, approved);
			ps.executeUpdate();
			conn.commit();
            log.info("Success with approving ticket! :)");
        }catch (SQLException e) {
            log.info("Failed to approve ticket!");
			e.printStackTrace();
		}
    }



    public void denyTicket(String data) {
		Date date= new Date();
		Timestamp current_date = new Timestamp(date.getTime());
        String sql = "UPDATE REIMBURSEMENTS SET REBURSERESOLVED = ? " + ", REBURSESTATUSID = ? "
				+ "WHERE REBURSEID = '" + data + "';";

		try {
			Connection conn = ConnectionUtil.getConnectionUtil().getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
            int denied = 3;
			conn.setAutoCommit(false);
            ps.setTimestamp(1, current_date);
            ps.setInt(2, denied);
			ps.executeUpdate();
			conn.commit();
            log.info("Success with denying ticket! :)");
        }catch (SQLException e) {
            log.info("Failed in the deny ticket!");
			e.printStackTrace();
		}
    }

}

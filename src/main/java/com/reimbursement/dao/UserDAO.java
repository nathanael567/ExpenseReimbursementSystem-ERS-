package com.reimbursement.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Logger;

import com.reimbursement.model.User;
import com.reimbursement.util.ConnectionUtil;

/**
 * Implementation of UserDAO that reads/writes to a relational database
 */
public class UserDAO {
    final static Logger log = Logger.getAnonymousLogger();
	
    /**
	 * Retrieves all users in the persistence layer
	 * @return a list of all users
	 */
	public ArrayList<User> getAllUsers() {
		ArrayList<User> userList = new ArrayList<User>();
		try {
			Connection conn = ConnectionUtil.getConnectionUtil().getConnection();
			String sql = "SELECT * FROM USERS ";
			Statement s = conn.createStatement();
			ResultSet rs = s.executeQuery(sql);
			while (rs.next()) {
                userList.add(new User(
						rs.getInt(1),
						rs.getString(2), 
						rs.getString(3),
						rs.getString(4),
						rs.getString(5),
						rs.getString(6),
						rs.getInt(7)));
			}
			log.info("Success getting all users! :)");
		} catch (SQLException e) {
			log.info("Failed getting all users!");
			e.printStackTrace();
		}
		return userList;
	}
    /**
	 * Retrieves a user by username and password
	 * 
	 * @param username username
	 * @param pass password
	 * @return the user object
	 */
    public User getUser(String username, String pass) {
		User user = new User();
		try {
			Connection conn = ConnectionUtil.getConnectionUtil().getConnection();
			String sql = "SELECT * FROM USERS WHERE username = '" + username + "' AND upassword = '" + pass + "';";
			Statement s = conn.createStatement();
			ResultSet rs = s.executeQuery(sql);
			while (rs.next()) {
				user.setUserid(rs.getInt("userid"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("upassword"));
				user.setFirstname(rs.getString("firstname"));
				user.setLastname(rs.getString("lastname"));
                user.setEmail(rs.getString("email"));
                user.setRoleid(rs.getInt("userroleid"));
			}
			log.info("Success getting user! :)");
			rs.close();
			s.close();
			conn.close();
		} catch (SQLException e) {
			log.info("Failed getting user!");
			e.printStackTrace();
		}
		return user;
	
	}

}

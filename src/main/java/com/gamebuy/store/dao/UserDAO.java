package com.gamebuy.store.dao;

import com.gamebuy.store.domain.Role;
import com.gamebuy.store.domain.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class UserDAO extends DAO {

	public User generateUserFromResultSet(ResultSet rs) {

		User user;

		try {
			int id = rs.getInt( "id" );
			String username = rs.getString( "username" );
			String password = rs.getString( "password" );
			Role role = Role.valueOf(rs.getString( "role" ));

			user = new User(id, username, password, role);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return user;
	}
	public User getUser(int id) {
			
		Connection conn = null;
		Statement statement;
		
		User user;
		String query;

		query = "SELECT * FROM user WHERE id = " + id;

    	ResultSet rs;
    	
		try {
			conn = getConnection();
			statement = conn.createStatement();
			rs = statement.executeQuery(query);
			user = generateUserFromResultSet(rs);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			closeConnection(conn);
		}
    	
		return user;
	}

	public User getUserByUsername(String username) {

		Connection conn = null;
		Statement statement;

		User user;
		String query;

		query = "SELECT * FROM user WHERE username = " + username;

		ResultSet rs;

		try {
			conn = getConnection();
			statement = conn.createStatement();
			rs = statement.executeQuery(query);
			user = generateUserFromResultSet(rs);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			closeConnection(conn);
		}

		return user;
	}
	
	public ArrayList<User> getAllUsers() {
		
		Connection conn = null;
		Statement statement;
		
		ArrayList<User> users = new ArrayList<>();
        String query = "SELECT * FROM user";
		
		try {
			conn = getConnection();
			statement = conn.createStatement();
			ResultSet rs = statement.executeQuery(query);
			while ( rs.next() ) {
				User user = generateUserFromResultSet(rs);
			    users.add(user);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			closeConnection(conn);
		}
    	
		return users;
	}
	
	public int addUser(User user) {

		Connection conn = null;
		Statement statement;

		String username = user.getUsername();
		String password = user.getPassword();
		String role = user.getRole().name();

		String query = "INSERT INTO user (username, password, role) VALUES ('" + username + "','" + password + "','" + role + "');";
		String returnMaxId = "SELECT MAX(id) from user;";
		int maxId = 0;
		try {
			conn = getConnection();
			statement = conn.createStatement();
			statement.executeUpdate(query);
			ResultSet rs = statement.executeQuery(returnMaxId);
			if (rs.next()) {
				maxId = rs.getInt("max(id)");
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			closeConnection(conn);
		}
		return maxId;
	}
	
	public void updateUser(int id, String username, String password, Role role) {
	
		Connection conn = null;
		Statement statement;
		
		String query;
		
		if (username.equals("")) {
			username = getUser(id).getUsername();
		}
		
		if (password.equals("")) {
			password = getUser(id).getPassword();
		}
		
		if (role.name().equals("")) {
			role = getUser(id).getRole();
		}
		
		query = "UPDATE user "
				+ "SET username = '" + username + "', "
				+ "password = '" + password + "', "
				+ "role = " + role.name() + " "
				+ "WHERE id = " + id + "";
		
		try {
			conn = getConnection();
			statement = conn.createStatement();
			statement.executeUpdate(query);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			closeConnection(conn);
		}
	}

    public void deleteUser(int id) {
		Connection conn = null;
		Statement statement;

		String query = "DELETE FROM user WHERE id = " + id;

		try {
			conn = getConnection();
			statement = conn.createStatement();
			statement.executeUpdate(query);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			closeConnection(conn);
		}
    }
}

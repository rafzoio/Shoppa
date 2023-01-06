package com.gamebuy.store.dao;

import com.gamebuy.store.domain.Address;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AddressDAO extends DAO {
	
public Address generateAddressFromResultSet(ResultSet rs) {
		
		Address address;
		
		try {
			int id = rs.getInt( "CUSTOMERID" );
			String house = rs.getString( "HOUSE" );
			String addressLine1 = rs.getString( "ADDRESSLINE1" );
			String addressLine2 = rs.getString( "ADDRESSLINE2" );
			String country = rs.getString( "COUNTRY" );
			String postcode = rs.getString( "POSTCODE" );
			
			address = new Address(id, house, addressLine1, addressLine2, country, postcode);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		return address;
	}
	
	public Address getAddress(int id) {
			
		Connection conn = null;
		Statement statement;
		
		Address address;
		String query;

		query = "SELECT * FROM address WHERE customerId = " + id;

    	ResultSet rs;
    	
		try {
			conn = getConnection();
			statement = conn.createStatement();
			rs = statement.executeQuery(query);
			address = generateAddressFromResultSet(rs);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			closeConnection(conn);
		}
    	
		return address;
	}
	/*
	public ArrayList<Customer> getAllCustomers() throws SQLException {
		
		Connection conn = null;
		Statement statement;
		
		ArrayList<Customer> customers = new ArrayList<Customer>();
        String query = "SELECT * FROM customer";

    	ResultSet rs = null;
		try {
			conn = getConnection();
			statement = conn.createStatement();
			rs = statement.executeQuery(query);
			while ( rs.next() ) {
				Customer customer = generateCustomerFromResultSet(rs);
			    customers.add(customer);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally { 
			if (conn != null) {
				conn.close();
			}
		}
    	
		return customers;
	}
*/
	public void addAddress(Address address) {
		
		Connection conn = null;
		Statement statement;
		
		int customerId = address.getCustomerId();
		String house = address.getHouse();
		String addressLine1 = address.getAddressLine1();
		String addressLine2 = address.getAddressLine2();
		String country = address.getCountry();
		String postcode = address.getPostcode();
		
		String query = "INSERT INTO address (customerId, house, addressLine1, addressLine2, country, postcode) VALUES (" + customerId + ",'" + house + "','" + addressLine1 + "','" + addressLine2 +"','" + country +"','" + postcode + "');";
		
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

	public void deleteAddress(int customerId) {

		Connection conn = null;
		Statement statement;

		String query = "DELETE FROM address WHERE customerId = " + customerId + ";";

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

	public void updateAddress(int customerId, String house, String addressLine1, String addressLine2, String country, String postcode) {

		Connection conn = null;
		Statement statement;

		String query = "UPDATE address "
				+ "SET house = '" + house + "', "
				+ "addressLine1 = '" + addressLine1 + "', "
				+ "addressLine2 = '" + addressLine2 + "', "
				+ "country = '" + country + "', "
				+ "postcode = '" + postcode + "' "
				+ "WHERE customerId = " + customerId + ";";

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

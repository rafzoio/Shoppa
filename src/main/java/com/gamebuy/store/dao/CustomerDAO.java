package com.gamebuy.store.dao;

import com.gamebuy.store.domain.Customer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class CustomerDAO extends DAO {

    /**
     * Generates and returns a customer object from a result set object.
     *
     * @param rs
     * @return Customer
     */
    public Customer generateCustomerFromResultSet(ResultSet rs) {

        Customer customer;

        try {
            int id = rs.getInt("ID");
            String firstName = rs.getString("FIRSTNAME");
            String secondName = rs.getString("SECONDNAME");
            String telephoneNumber = rs.getString("TELEPHONENUMBER");
            customer = new Customer(id, firstName, secondName, telephoneNumber);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return customer;
    }

    /**
     * Gets a customer from the database by id.
     *
     * @param id
     * @return Customer
     */
    public Customer getCustomer(int id) {

        Connection conn = null;
        Statement statement;

        Customer customer;
        String query;

        query = "SELECT * FROM customer WHERE id = " + id;

        ResultSet rs;

        try {
            conn = getConnection();
            statement = conn.createStatement();
            rs = statement.executeQuery(query);
            customer = generateCustomerFromResultSet(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeConnection(conn);
        }

        return customer;
    }

    /**
     * Returns an ArrayList of all customers stored in the database.
     *
     * @return ArrayList of customers
     */
    public ArrayList<Customer> getAllCustomers() {

        Connection conn = null;
        Statement statement;

        ArrayList<Customer> customers = new ArrayList<>();
        String query = "SELECT * FROM customer";

        try {
            conn = getConnection();
            statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                Customer customer = generateCustomerFromResultSet(rs);
                customers.add(customer);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeConnection(conn);
        }

        return customers;
    }

    /**
     * Adds a customer to the database.
     * Returns the auto-generated id value of the inserted customer using second SQL query.
     *
     * @param customer
     * @return int value of inserted customer id
     */
    public int addCustomer(Customer customer) {

        Connection conn = null;
        Statement statement;

        String firstName = customer.getFirstName();
        String secondName = customer.getSecondName();
        String telephoneNumber = customer.getTelephoneNumber();

        String query = "INSERT INTO customer (firstName, secondName, telephoneNumber) VALUES ('" + firstName + "','" + secondName + "'," + telephoneNumber + ");";
        String returnMaxId = "SELECT MAX(id) from customer;";
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

    /**
     * Updates all values of a customer in the database.
     *
     * @param id
     * @param firstName
     * @param secondName
     * @param telephoneNumber
     */
    public void updateCustomer(int id, String firstName, String secondName, String telephoneNumber) {

        Connection conn = null;
        Statement statement;

        String query;

        if (firstName.equals("")) {
            firstName = getCustomer(id).getFirstName();
        }

        if (secondName.equals("")) {
            secondName = getCustomer(id).getSecondName();
        }

        if (telephoneNumber.equals("")) {
            telephoneNumber = getCustomer(id).getTelephoneNumber();
        }

        query = "UPDATE customer "
                + "SET firstName = '" + firstName + "', "
                + "secondName = '" + secondName + "', "
                + "telephoneNumber = " + telephoneNumber + " "
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

    /**
     * Deletes a customer from the database by id.
     *
     * @param id
     */
    public void deleteCustomer(int id) {
        Connection conn = null;
        Statement statement;

        String query = "DELETE FROM customer WHERE id = " + id;

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

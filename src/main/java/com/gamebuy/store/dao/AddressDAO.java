package com.gamebuy.store.dao;

import com.gamebuy.store.domain.Address;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AddressDAO extends DAO {

    /**
     * Takes a result set and collects the attributes of an address into an address object.
     *
     * @param rs a result set containing address data.
     * @return Address
     */
    public Address generateAddressFromResultSet(ResultSet rs) {

        Address address;

        try {
            int id = rs.getInt("CUSTOMERID");
            String house = rs.getString("HOUSE");
            String addressLine1 = rs.getString("ADDRESSLINE1");
            String addressLine2 = rs.getString("ADDRESSLINE2");
            String country = rs.getString("COUNTRY");
            String postcode = rs.getString("POSTCODE");

            address = new Address(id, house, addressLine1, addressLine2, country, postcode);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return address;
    }

    /**
     * Gets an address from the database by id.
     *
     * @param id
     * @return Address
     */
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

    /**
     * Adds an address to the database.
     *
     * @param address
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

        String query =
                "INSERT INTO address (" +
                        "customerId, " +
                        "house, " +
                        "addressLine1, " +
                        "addressLine2, " +
                        "country, " +
                        "postcode) " +
                        "VALUES (" +
                        customerId + ",'" +
                        house + "','" +
                        addressLine1 + "','" +
                        addressLine2 + "','" +
                        country + "','" +
                        postcode + "');";

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
     * Deletes an address from the database by customerId.
     *
     * @param customerId
     */
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

    /**
     * Updates all values of an address stored in the database.
     *
     * @param customerId
     * @param house
     * @param addressLine1
     * @param addressLine2
     * @param country
     * @param postcode
     */
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

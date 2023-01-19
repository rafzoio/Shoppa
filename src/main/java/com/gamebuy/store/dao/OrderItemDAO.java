package com.gamebuy.store.dao;

import com.gamebuy.store.domain.OrderItem;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class OrderItemDAO extends DAO {

    /**
     * Returns an orderItem generated from an SQL result set.
     *
     * @param rs
     * @return orderItem
     */
    public OrderItem generateOrderItemFromResultSet(ResultSet rs) {
        OrderItem orderItem = null;
        try {
            int productId = rs.getInt("product_id");
            int basketId = rs.getInt("basket_id");
            int quantity = rs.getInt("quantity");
            orderItem = new OrderItem(basketId, productId, quantity);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderItem;
    }

    /**
     * Gets all orderItems by basketID.
     *
     * @param basketId
     * @return ArrayList of orderItems
     */
    public ArrayList<OrderItem> getOrderItemsInBasket(int basketId) {
        ArrayList<OrderItem> orderItems = new ArrayList<>();

        Connection conn = null;
        Statement statement;

        String query = "SELECT * FROM orderItem WHERE basket_id = " + basketId;

        try {
            conn = getConnection();
            statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                OrderItem orderItem = generateOrderItemFromResultSet(rs);
                orderItems.add(orderItem);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeConnection(conn);
        }
        return orderItems;
    }

    /**
     * Adds an orderItem to the database.
     *
     * @param basketId
     * @param productId
     * @param quantity
     */
    public void addOrderItem(int basketId, int productId, int quantity) {
        OrderItem orderItem = new OrderItem(basketId, productId, quantity);

        Connection conn = null;
        Statement statement;

        String query = "INSERT INTO orderItem (basket_id, product_id, quantity) VALUES (" + orderItem.getBasketID() + "," + orderItem.getProductId() + "," + orderItem.getQuantity() + ")";

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
     * Updates the quantity of an orderItem in the database by basketId and productId.
     *
     * @param basketId
     * @param productId
     * @param newQuantity
     */
    public void updateOrderItemQuantity(int basketId, int productId, int newQuantity) {

        Connection conn = null;
        Statement statement;

        String query;

        query = "UPDATE orderItem "
                + "SET quantity = " + newQuantity
                + " WHERE basket_id = " + basketId
                + " AND product_id = " + productId + ";";

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
     * Deletes an orderItem from the database by basketId and productId.
     *
     * @param basketId
     * @param productId
     */
    public void deleteOrderItem(int basketId, int productId) {

        Connection conn = null;
        Statement statement;

        String query = "DELETE FROM orderItem "
                + " WHERE basket_id = " + basketId
                + " AND product_id = " + productId + ";";

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

package com.gamebuy.store.dao;

import com.gamebuy.store.domain.Product;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ProductDAO extends DAO {

    /**
     * Generates a product from a SQL result set object.
     *
     * @param rs
     * @return
     */
    public Product generateProductFromResultSet(ResultSet rs) {

        Product product;

        try {
            int id = rs.getInt("id");
            String SKU = rs.getString("SKU");
            String description = rs.getString("DESCRIPTION");
            String category = rs.getString("CATEGORY");
            int available = rs.getInt("AVAILABLE");
            int price = rs.getInt("PRICE");

            product = new Product(id, SKU, description, category, available, price);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return product;
    }

    /**
     * Returns a product from the database by id.
     *
     * @param id
     * @return product
     */
    public Product getProduct(int id) {

        Connection conn = null;
        Statement statement;

        Product product;
        String query;

        query = "SELECT * FROM product WHERE id = " + id;

        ResultSet rs;

        try {
            conn = getConnection();
            statement = conn.createStatement();
            rs = statement.executeQuery(query);
            product = generateProductFromResultSet(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeConnection(conn);
        }

        return product;
    }

    /**
     * Returns an array of all products stored in the database.
     *
     * @return all products
     */
    public ArrayList<Product> getAllProducts() {

        Connection conn = null;
        Statement statement;

        ArrayList<Product> products = new ArrayList<>();
        String query = "SELECT * FROM product";

        ResultSet rs;
        try {
            conn = getConnection();
            statement = conn.createStatement();
            rs = statement.executeQuery(query);
            while (rs.next()) {
                Product product = generateProductFromResultSet(rs);
                products.add(product);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeConnection(conn);
        }

        return products;
    }

    /**
     * Adds a product to the database.
     *
     * @param product
     */
    public void addProduct(Product product) {

        Connection conn = null;
        Statement statement;

        String SKU = product.getSKU();
        String description = product.getDescription();
        String category = product.getCategory();
        int available = product.getAvailable();
        int price = product.getPrice();

        String query =
                "INSERT INTO product " +
                        "(SKU, " +
                        "description, " +
                        "category, " +
                        "available, " +
                        "price) " +
                        "VALUES ('" +
                        SKU + "','" +
                        description + "','" +
                        category + "'," +
                        available + "," +
                        price + ");";

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
     * Deletes a product from the database by id.
     *
     * @param id
     */
    public void deleteProduct(int id) {

        Connection conn = null;
        Statement statement;

        String query = "DELETE FROM product WHERE id = " + id;

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
     * Updates all values of a product in the database by id.
     *
     * @param id
     * @param SKU
     * @param description
     * @param category
     * @param available
     * @param price
     */
    public void updateProduct(int id, String SKU, String description, String category, int available, int price) {

        Connection conn = null;
        Statement statement;

        String query;

        Product existingProduct = getProduct(id);

        if (SKU.equals("")) {
            SKU = existingProduct.getSKU();
        }

        if (description.equals("")) {
            description = existingProduct.getDescription();
        }

        if (category.equals("")) {
            category = existingProduct.getCategory();
        }

        if (available == 0) {
            available = existingProduct.getAvailable();
        }

        if (price == 0) {
            price = existingProduct.getPrice();
        }

        query = "UPDATE product "
                + "SET sku = '" + SKU + "', "
                + "description = '" + description + "', "
                + "category = '" + category + "', "
                + "available = " + available + ", "
                + "price = " + price + " "
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
}
























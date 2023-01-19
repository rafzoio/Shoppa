package com.gamebuy.store.dao;

import org.sqlite.SQLiteDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public abstract class DAO {

    /**
     * Returns a new connection with the SQLite datasource.
     *
     * @return new connection
     */
    protected final Connection getConnection() {

        SQLiteDataSource ds;
        Connection conn;

        try {
            ds = new SQLiteDataSource();
            ds.setUrl("jdbc:sqlite:game-buy.db");
            conn = ds.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return conn;
    }

    /**
     * Closes a connection if not null.
     *
     * @param conn
     */
    protected final void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

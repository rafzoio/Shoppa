package com.gamebuy.store.dao;

import org.sqlite.SQLiteDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public abstract class DAO {

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

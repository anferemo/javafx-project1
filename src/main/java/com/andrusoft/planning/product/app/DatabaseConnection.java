/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.andrusoft.planning.product.app;

import com.andrusoft.planning.product.app.dao.ProductDAO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author andrusoft
 */
public final class DatabaseConnection {
    
    private static final String DB_URL = "jdbc:h2:./data/mydatabase";
    private static final String USER = "sa";
    private static final String PASSWORD = "";
    
    private static final Logger logger = Logger.getLogger(DatabaseConnection.class.getName());
    
    private static Connection connection;

    public DatabaseConnection() {
        try {
            connection = getDatabaseConnection();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    public static Connection getDatabaseConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASSWORD);
    }
    
    public boolean schemaExists() {
    logger.info("Checking for Schema existence");      
    try {
      Statement st = connection.createStatement();      
      st.executeQuery("select count(*) from product");
      logger.info("Schema exists");      
    } catch (SQLException ex) {
      logger.info("Existing DB not found will create a new one");
      return false;
    }

    return true;
  }

    public static Connection getConnection() throws SQLException {
        if (connection==null || connection.isClosed()) {
            connection = getDatabaseConnection();
        }
        return connection;
    }
    
    public void createSchema() throws SQLException {
        logger.info("Creating schema");
        ProductDAO daoProd = new ProductDAO();
        daoProd.createTable();        
        logger.info("Created schema");
    }
}

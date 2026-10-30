/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.andrusoft.planning.product.app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.h2.jdbcx.JdbcDataSource;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author andrusoft
 */
public class DatabaseConnection {
    
    private static final String DB_URL = "jdbc:h2:./data/mydatabase";
    private static final String USER = "sa";
    private static final String PASSWORD = "";
    
    private static final Logger logger = Logger.getLogger(DatabaseConnection.class.getName());
    
    private Connection connection;

    public DatabaseConnection() {
        try {
            connection = getDatabaseConnection();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    public Connection getDatabaseConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASSWORD);
    }
    
    public boolean schemaExists() {
    logger.info("Checking for Schema existence");      
    try {
      Statement st = connection.createStatement();      
      st.executeQuery("select count(*) from employee");
      logger.info("Schema exists");      
    } catch (SQLException ex) {
      logger.info("Existing DB not found will create a new one");
      return false;
    }

    return true;
  }

    public Connection getConnection() {
        return connection;
    }
    
    public void createSchema(Connection con) throws SQLException {
    logger.info("Creating schema");
    Statement st = con.createStatement();
    String table = "create table employee(id integer, name varchar(64))";
    st.executeUpdate(table);
    logger.info("Created schema");
  }
}

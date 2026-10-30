/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.andrusoft.planning.product.app.dao;

import com.andrusoft.planning.product.app.DatabaseConnection;
import com.andrusoft.planning.product.app.model.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author andrusoft
 */
public class ProductDAO {
    
    private static final Logger logger = Logger.getLogger(ProductDAO.class.getName());
    
    private static final String INSERT_SQL = "INSERT INTO product(name) values(?)";
    private static final String SELECT_SQL = "select * from product";
    private static final String CREATE_TABLE_SQL = "create table if not exists product(NAME varchar(64) primary key)";
    
    public ProductDAO(){
        
    }
    
    public List<Product> queryAllProducts() {
        List<Product> lista = new ArrayList<>();
        try(Connection conn = DatabaseConnection.getConnection()) {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(SELECT_SQL);
            while(rs.next()) {               
                String name = rs.getString("NAME");
                lista.add(new Product(name, ""));
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, null, ex);
        }
        
        return lista;
    }
    
    public void saveProduct(Product producto){
        try(Connection conn = DatabaseConnection.getConnection()) {
                PreparedStatement prepStmt = conn.prepareStatement(INSERT_SQL);
                prepStmt.setString(1, producto.getNombre());
                prepStmt.executeUpdate();
                
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, null, ex);
        }
    }
    
    public void createTable() {
        logger.info("Creating Product Table");
        try(Connection conn = DatabaseConnection.getConnection()) {
            Statement st = conn.createStatement();
            st.executeUpdate(CREATE_TABLE_SQL);
            logger.info("Product Table Created");
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, null, ex);
        }
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.andrusoft.planning.product.app.dao;

import com.andrusoft.planning.product.app.DatabaseConnection;
import com.andrusoft.planning.product.app.MyBatisConnection;
import com.andrusoft.planning.product.app.mapper.InventoryMapper;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.ibatis.session.SqlSession;

/**
 *
 * @author andrusoft
 */
public class InventoryDAO {
    
    private static final Logger logger = Logger.getLogger(InventoryDAO.class.getName());
    
    private static final String CREATE_TABLE_SQL = "inventory_prueba"; //"create table if not exists lote(codigo varchar(64) primary key, producto varchar(128) not null, cantidad_programada long, fecha_envase Date, fecha_aprobacion_mb Date, fecha_emision_op Date, cantidad_entregada long, observaciones varchar(128), estado_origen varchar(128))";

    public InventoryDAO() {
    }
    
    public void createTable(){
        try(SqlSession session= createSession()) {
            InventoryMapper invMapper = session.getMapper(InventoryMapper.class);
            invMapper.createTable();
            logger.log(Level.INFO, "Tabla inventario creada");
        }
    }
    
    private SqlSession createSession() {
        SqlSession session = null;
        session = MyBatisConnection.factory.openSession();
        return session;
    }        
}

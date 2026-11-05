/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.andrusoft.planning.product.app.dao;

import com.andrusoft.planning.product.app.DatabaseConnection;
import com.andrusoft.planning.product.app.MyBatisConnection;
import com.andrusoft.planning.product.app.mapper.LoteMapper;
import com.andrusoft.planning.product.app.model.Lote;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.ibatis.session.SqlSession;

/**
 *
 * @author andrusoft
 */
public class LoteDAO {
    
    private static final Logger logger = Logger.getLogger(LoteDAO.class.getName());
    
    private static final String INSERT_SQL = "INSERT INTO lote(codigo, producto, cantidad_programada, fecha_envase, fecha_aprobacion_mb, fecha_emision_op, cantidad_entregada, observaciones, estado_origen) values(?,?,?,?,?,?,?,?,?)";
    private static final String SELECT_ALL_SQL = "select * from lote order by fecha_envase desc";
    private static final String SELECT_LOTE_BY_PRODUCT = "select * from lote where producto like ? order by fecha_envase desc";
    private static final String CREATE_TABLE_SQL = "create table if not exists lote(codigo varchar(64) primary key, producto varchar(128) not null, cantidad_programada long, fecha_envase Date, fecha_aprobacion_mb Date, fecha_emision_op Date, cantidad_entregada long, observaciones varchar(128), estado_origen varchar(128))";
    private static final String ALTER_TABLE_FK_PROD = "alter table if exists lote add constraint LOTE_PROD_FK (producto) references product(NAME)";
    private static final String DELETE_ALL_SQL = "delete from lote";

    public LoteDAO() {
    }
    
    public List<Lote> queryAllLotes() {        
        try (SqlSession session = createSession()) {
            LoteMapper loteMapper = session.getMapper(LoteMapper.class);
            return loteMapper.getAllLotes();            
        } catch (Exception ex){
            return new ArrayList<>();
        }        
    }
    
    private SqlSession createSession() {
        SqlSession session = null;
        session = MyBatisConnection.factory.openSession();
        return session;
    }
    
    public void createTable() {
        logger.info("Creating Lote Table");
        try(Connection conn = DatabaseConnection.getConnection()) {
            Statement st = conn.createStatement();
            st.executeUpdate(CREATE_TABLE_SQL);
            logger.info("Table Lote Created");
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, null, ex);
        }
    }
    
    public void deleteAll() {
        logger.info("Deleting Lote Table");
        try(Connection conn = DatabaseConnection.getConnection()) {
            Statement st = conn.createStatement();
            int rows = st.executeUpdate(DELETE_ALL_SQL);
            logger.log(Level.INFO, "{0} Lote''s rows Deleted", String.valueOf(rows));
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, null, ex);
        }
    }
    
    public void saveLotes(List<Lote> lista) {
        try(Connection conn = DatabaseConnection.getConnection()) {            
            PreparedStatement prepStmt = conn.prepareStatement(INSERT_SQL);
            for (int i = 0 ; i<lista.size(); i++) {
                Lote lote = lista.get(i);
                prepStmt.setString(1, lote.getCodigo());
                prepStmt.setString(2, lote.getProducto());
                prepStmt.setLong(3, lote.getCantidadProgramada());
                if(lote.getFechaEnvase()!=null) {
                    prepStmt.setDate(4,Date.valueOf(lote.getFechaEnvase()));
                } else {
                    prepStmt.setNull(4,Types.DATE);
                }
                if(lote.getFechaAprobacionMB()!=null) {
                    prepStmt.setDate(5, Date.valueOf(lote.getFechaAprobacionMB()));
                } else {
                    prepStmt.setNull(5,Types.DATE);
                }
                if (lote.getFechaEmisionOP()!=null) {
                    prepStmt.setDate(6, Date.valueOf(lote.getFechaEmisionOP()));
                } else {
                    prepStmt.setNull(6,Types.DATE);
                }
                prepStmt.setLong(7, lote.getCantidadEntregada());
                if(lote.getObservaciones()!=null) {
                    prepStmt.setString(8, lote.getObservaciones());
                } else {
                    prepStmt.setNull(8,Types.VARCHAR);
                }
                if(lote.getEstadoOrigen()!=null) {
                    prepStmt.setString(9, lote.getEstadoOrigen());
                } else {
                    prepStmt.setNull(9,Types.VARCHAR);
                }
                prepStmt.addBatch();
            }
            prepStmt.executeBatch();
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, null, ex);
        }
    }

    public void addLote(Lote lote) {
        try(Connection conn = DatabaseConnection.getConnection()) {
                PreparedStatement prepStmt = conn.prepareStatement(INSERT_SQL);
                prepStmt.setString(1, lote.getCodigo());
                prepStmt.setString(2, lote.getProducto());
                prepStmt.setLong(3, lote.getCantidadProgramada());
                if(lote.getFechaEnvase()!=null) {
                    prepStmt.setDate(4,Date.valueOf(lote.getFechaEnvase()));
                } else {
                    prepStmt.setNull(4,Types.DATE);
                }
                if(lote.getFechaAprobacionMB()!=null) {
                    prepStmt.setDate(5, Date.valueOf(lote.getFechaAprobacionMB()));
                } else {
                    prepStmt.setNull(5,Types.DATE);
                }
                if (lote.getFechaEmisionOP()!=null) {
                    prepStmt.setDate(6, Date.valueOf(lote.getFechaEmisionOP()));
                } else {
                    prepStmt.setNull(6,Types.DATE);
                }
                prepStmt.setLong(7, lote.getCantidadEntregada());
                if(lote.getObservaciones()!=null) {
                    prepStmt.setString(8, lote.getObservaciones());
                } else {
                    prepStmt.setNull(8,Types.VARCHAR);
                }
                if(lote.getEstadoOrigen()!=null) {
                    prepStmt.setString(9, lote.getEstadoOrigen());
                } else {
                    prepStmt.setNull(9,Types.VARCHAR);
                }
                prepStmt.executeUpdate();
                
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, null, ex);
        }
    }
    
    public List<Lote> queryAllLotesByProductName(String productName) {        
        try (SqlSession session = createSession()) {
            LoteMapper loteMapper = session.getMapper(LoteMapper.class);
            return loteMapper.getAllLotesByProductName(productName);
        } catch (Exception ex) {
            return new ArrayList<>();
        }        
        
    }
}

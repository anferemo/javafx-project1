/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.andrusoft.planning.product.app.dao;

import com.andrusoft.planning.product.app.MyBatisConnection;
import com.andrusoft.planning.product.app.mapper.LoteMapper;
import com.andrusoft.planning.product.app.model.Lote;
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
    

    public LoteDAO() {
    }
    
    public List<Lote> queryAllLotes() {        
        try (SqlSession session = MyBatisConnection.createSession()) {
            LoteMapper loteMapper = session.getMapper(LoteMapper.class);
            return loteMapper.getAllLotes();            
        } catch (Exception ex){
            return new ArrayList<>();
        }        
    }
    
    public void createTable() {
        logger.info("Creating Lote Table");
        try (SqlSession session = MyBatisConnection.createSession()) {
            LoteMapper loteMapper = session.getMapper(LoteMapper.class);
            loteMapper.createTable();            
        }
        logger.info("Table Lote Created");        
    }
    
    public void deleteAll() {
        logger.info("Deleting Lote Table");
        try(SqlSession session = MyBatisConnection.createSession()) {
            LoteMapper loteMapper = session.getMapper(LoteMapper.class);            
            int rows = loteMapper.deleteAll();
            session.commit();
            logger.log(Level.INFO, "{0} Lote''s rows Deleted", String.valueOf(rows));
        } catch (Exception ex) {
            logger.log(Level.SEVERE, null, ex);
        }
    }
    
    public void saveLotes(List<Lote> lista) {
        lista.stream().forEach(lote-> {
            this.addLote(lote);
        });
        /*
        //Revisar el tema de insercion x batch ya que cuando hay error no hay commit,
        try(SqlSession session = createSession()){
            LoteMapper loteMapper = session.getMapper(LoteMapper.class);
            lista.stream().forEach(lote-> {                
                loteMapper.addLote(lote);                
            });            
            logger.log(Level.INFO, "{0} Lote''s rows Added", lista.size());            
        } catch (Exception ex) {
            logger.log(Level.SEVERE, null, ex);            
        }*/
    }

    public void addLote(Lote lote) {
        try(SqlSession session = MyBatisConnection.createSession()) {
            LoteMapper loteMapper = session.getMapper(LoteMapper.class);           
            int cant = loteMapper.addLote(lote);            
            logger.log(Level.INFO, "{0} Lote''s rows Added", cant);
            session.commit();
        } catch (Exception ex) {
            logger.log(Level.SEVERE, null, ex);            
        }
    }
    
    public List<Lote> queryAllLotesByProductName(String productName) {        
        try (SqlSession session = MyBatisConnection.createSession()) {
            LoteMapper loteMapper = session.getMapper(LoteMapper.class);
            return loteMapper.getAllLotesByProductName(productName);
        } catch (Exception ex) {
            return new ArrayList<>();
        }        
        
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.andrusoft.planning.product.app;

import com.andrusoft.planning.product.app.mapper.InventoryMapper;
import com.andrusoft.planning.product.app.mapper.LoteMapper;
import java.io.IOException;
import java.io.Reader;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 *
 * @author andrusoft
 */
public class MyBatisConnection {
    
    private static final Logger logger = Logger.getLogger(MyBatisConnection.class.getName());
    
    public static SqlSessionFactory factory = null;    
    
    public static void init() {
        String resource = "mybatis-configuration.xml";
        Reader reader;
        try {
            reader = Resources.getResourceAsReader(resource);
        } catch (IOException e) {
           logger.log(Level.SEVERE, e.getMessage());
            return;
        }
        factory = new SqlSessionFactoryBuilder().build(reader);
        factory.getConfiguration().addMapper(LoteMapper.class);
        factory.getConfiguration().addMapper(InventoryMapper.class);
    }
    
    public static SqlSessionFactory getSessionFactory() {
        if (factory==null) {
            init();
        }
        return factory;
    }
    
}

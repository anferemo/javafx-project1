/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.andrusoft.planning.product.app.controller;

import com.andrusoft.planning.product.app.dao.InventoryDAO;
import com.andrusoft.planning.product.app.file.SaldoInventarioReader;
import com.andrusoft.planning.product.app.model.Inventory;
import java.io.File;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Logger;

/**
 *
 * @author andrusoft
 */
public class InventoryController {
    
    private static final Logger logger = Logger.getLogger(InventoryController.class.getName());
    
    private SaldoInventarioReader reader;
    
    private InventoryDAO daoInventory;

    public InventoryController() {
        this.reader=new SaldoInventarioReader();
        this.daoInventory=new InventoryDAO();
    }
        
    public void loadInventoryFile(File f) {
        List<Inventory> listaInventario = this.reader.loadFile(f);
        daoInventory.deleteByFechaCargue(LocalDate.now());
        if (!listaInventario.isEmpty()) {
            listaInventario.stream().forEach(item -> {                
                daoInventory.insert(item);
            });
        }        
    }
    
    public void createTable() {
        this.daoInventory.createTable();
    }
    
    public void createPrimaryKey() {
        this.daoInventory.createPrimaryKey();
    }
    
    public List<Inventory> getInventoryByDate(LocalDate fecha) {
        return this.daoInventory.getInventoryByCargueDate(fecha);
    }
    
    public List<Inventory> getInventoryByProduct(String prodName) {
        return this.daoInventory.getInventoryByProduct(prodName);
    }
    
    public List<Inventory> getInventoryByProductZero() {
        return this.daoInventory.getInventoryProductInZero();
    }
    
    public List<Inventory> getAllInventory() {
        return this.daoInventory.getAllInventory();
    }
    
}

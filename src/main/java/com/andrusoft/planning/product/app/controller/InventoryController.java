/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.andrusoft.planning.product.app.controller;

import com.andrusoft.planning.product.app.file.SaldoInventarioReader;
import com.andrusoft.planning.product.app.model.Inventory;
import java.io.File;
import java.util.List;
import java.util.logging.Logger;

/**
 *
 * @author andrusoft
 */
public class InventoryController {
    
    private static final Logger logger = Logger.getLogger(InventoryController.class.getName());
    
    private SaldoInventarioReader reader;

    public InventoryController() {
        this.reader=new SaldoInventarioReader();
    }
        
    public List<Inventory> loadInventoryFile(File f) {
        return this.reader.loadFile(f);
    }
}

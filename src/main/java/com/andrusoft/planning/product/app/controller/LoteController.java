/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.andrusoft.planning.product.app.controller;

import com.andrusoft.planning.product.app.dao.LoteDAO;
import com.andrusoft.planning.product.app.file.SeguimientoProduccionReader;
import com.andrusoft.planning.product.app.model.Lote;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author andrusoft
 */
public class LoteController {
    
    private static final Logger logger = Logger.getLogger(LoteController.class.getName());
 
    private List<Lote> listaLotes;
    private LoteDAO loteDAO;
    private SeguimientoProduccionReader fileReader;    

    public LoteController() {
        listaLotes = new ArrayList<>();
        loteDAO = new LoteDAO();
        fileReader = new SeguimientoProduccionReader();
    }

    public List<Lote> getListaLotes() {
        return loteDAO.queryAllLotes();
    }

    public void setListaLotes(List<Lote> listaLotes) {
        this.listaLotes = listaLotes;
    }
    
    public void addLote(Lote lote) {
        this.loteDAO.addLote(lote);
        //this.listaLotes.add(lote);
    }
    
    public void loadLoteData() {
        logger.info("loadLoteData() started");
        loteDAO.createTable();
        loteDAO.deleteAll();
        logger.info("loadLoteData() finished");        
        try {
            List<Lote> lotesLeidos = new ArrayList<>();
            lotesLeidos.addAll(fileReader.loadFile());
            loteDAO.saveLotes(lotesLeidos);
        } catch (Exception ex) {
            logger.log(Level.WARNING, "Hubo un error al cargar el archivo: {0}", ex.getMessage());            
        }
        
    }
    
    
    
}

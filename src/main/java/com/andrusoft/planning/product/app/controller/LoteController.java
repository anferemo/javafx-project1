/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.andrusoft.planning.product.app.controller;

import com.andrusoft.planning.product.app.model.Lote;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author andrusoft
 */
public class LoteController {
 
    private List<Lote> listaLotes;

    public LoteController() {
        listaLotes = new ArrayList<>();
    }

    public List<Lote> getListaLotes() {
        return listaLotes;
    }

    public void setListaLotes(List<Lote> listaLotes) {
        this.listaLotes = listaLotes;
    }
    
    public void addLote(Lote lote) {
        this.listaLotes.add(lote);
    }
    
    
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.andrusoft.planning.product.app.model;

import java.time.LocalDate;

/**
 *
 * @author andrusoft
 */
public class Inventory {
    
    private int rowNum;
    private double referencia;
    private String nombreProducto;
    private double tamanoLote;
    private String vence;
    private double existencia;
    private double inventarioMinimo;
    private double expor;
    private double ace;
    private double programacion;
    private LocalDate fechaCarga;
    private double indicador1;
    private double mes;

    public Inventory() {
    }

    public int getRowNum() {
        return rowNum;
    }

    public void setRowNum(int rowNum) {
        this.rowNum = rowNum;
    }

    public double getReferencia() {
        return referencia;
    }

    public void setReferencia(double referencia) {
        this.referencia = referencia;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public double getTamanoLote() {
        return tamanoLote;
    }

    public void setTamanoLote(double tamanoLote) {
        this.tamanoLote = tamanoLote;
    }

    public String getVence() {
        return vence;
    }

    public void setVence(String vence) {
        this.vence = vence;
    }

    public double getExistencia() {
        return existencia;
    }

    public void setExistencia(double existencia) {
        this.existencia = existencia;
    }

    public double getInventarioMinimo() {
        return inventarioMinimo;
    }

    public void setInventarioMinimo(double inventarioMinimo) {
        this.inventarioMinimo = inventarioMinimo;
    }

    public double getExpor() {
        return expor;
    }

    public void setExpor(double expor) {
        this.expor = expor;
    }

    public double getAce() {
        return ace;
    }

    public void setAce(double ace) {
        this.ace = ace;
    }

    public double getProgramacion() {
        return programacion;
    }

    public void setProgramacion(double programacion) {
        this.programacion = programacion;
    }

    public LocalDate getFechaCarga() {
        return fechaCarga;
    }

    public void setFechaCarga(LocalDate fechaCarga) {
        this.fechaCarga = fechaCarga;
    }

    public double getIndicador1() {
        return indicador1;
    }

    public void setIndicador1(double indicador1) {
        this.indicador1 = indicador1;
    }

    public double getMes() {
        return mes;
    }

    public void setMes(double mes) {
        this.mes = mes;
    }    
    
    @Override
    public String toString() {
        return "Inventory{" + "rowNum=" + rowNum + ", referencia=" + referencia + ", nombreProducto=" + nombreProducto + ", tamanoLote=" + tamanoLote + ", vence=" + vence + ", existencia=" + existencia + ", inventarioMinimo=" + inventarioMinimo + ", expor=" + expor + ", ace=" + ace + ", programacion=" + programacion + ", fechaCarga=" + fechaCarga + ", indicador1=" + indicador1+ ", mes=" + mes + '}';
    }
    
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.andrusoft.planning.product.app.model;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.Locale;

/**
 *
 * @author andrusoft
 */
public class Lote {
    
    private String codigo;
    private String producto;
    private long cantidadProgramada;
    private LocalDate fechaEnvase;
    private LocalDate fechaAprobacionMB;
    private LocalDate fechaEmisionOP;
    private long cantidadEntregada;
    //private String porcentajeEntrega;
    private String observaciones;
    private String estadoOrigen;
    
    public Lote(String codigo) {
        this.codigo = codigo;
    }

    public Lote() {
        
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public long getCantidadProgramada() {
        return cantidadProgramada;
    }

    public void setCantidadProgramada(long cantidadProgramada) {
        this.cantidadProgramada = cantidadProgramada;
    }

    public LocalDate getFechaEnvase() {
        return fechaEnvase;
    }

    public void setFechaEnvase(LocalDate fechaEnvase) {
        this.fechaEnvase = fechaEnvase;
    }

    public long getCantidadEntregada() {
        return cantidadEntregada;
    }

    public void setCantidadEntregada(long cantidadEntregada) {
        this.cantidadEntregada = cantidadEntregada;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }    

    public LocalDate getFechaAprobacionMB() {
        return fechaAprobacionMB;
    }

    public void setFechaAprobacionMB(LocalDate fechaAprobacionMB) {
        this.fechaAprobacionMB = fechaAprobacionMB;
    }

    public LocalDate getFechaEmisionOP() {
        return fechaEmisionOP;
    }

    public void setFechaEmisionOP(LocalDate fechaEmisionOP) {
        this.fechaEmisionOP = fechaEmisionOP;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getEstadoOrigen() {
        return estadoOrigen;
    }

    public void setEstadoOrigen(String estadoOrigen) {
        this.estadoOrigen = estadoOrigen;
    }

    public String getPorcentaje() {
        NumberFormat percentFormat = NumberFormat.getPercentInstance(Locale.US);
        //percentFormat.setMaximumFractionDigits(3);
        float ratio = 0;
        try{
            ratio = this.cantidadEntregada * 1000 /this.cantidadProgramada;
        } catch (Exception ex) {
            ratio = 0;
        }
        percentFormat.setMaximumFractionDigits(3);
        return percentFormat.format(ratio);
        //return porcentaje;
    }
    
    @Override
    public String toString() {
        return "Lote{" + "codigo=" + codigo + ", producto=" + producto + ", cantidadProgramada=" + cantidadProgramada + ", fechaEnvase=" + fechaEnvase + ", fechaAprobacionMB=" + fechaAprobacionMB + ", fechaEmisionOP=" + fechaEmisionOP + ", cantidadEntregada=" + cantidadEntregada + ", observaciones=" + observaciones + ", estadoOrigen=" + estadoOrigen + ", porcentaje=" + getPorcentaje() +  '}';
    }
    
    
}

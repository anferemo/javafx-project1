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
public class Lote {
    
    private String codigo;
    private String producto;
    private long cantidadProgramada;
    private LocalDate fechaEnvase;
    private long cantidadEntregada;

    public Lote(String codigo) {
        this.codigo = codigo;
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
    
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.andrusoft.planning.product.app.controller;

/**
 *
 * @author andrusoft
 */
public class CompareDoubleValue {
      
    public static boolean isNegative(double d1) {        
        int valor = Double.compare(d1, 0d);        
        return (valor<0);
        
    }
}

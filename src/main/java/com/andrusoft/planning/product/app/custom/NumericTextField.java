/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.andrusoft.planning.product.app.custom;

import javafx.scene.control.TextField;

/**
 *
 * @author andrusoft
 */
public class NumericTextField extends TextField{
    
     // Allow: empty, "-", or digits (adjust regex for edge cases)
    private static final String VALID_PATTERN = "-?\\d*";
 
    @Override
    public void replaceText(int start, int end, String text) {
        String newText = getText().substring(0, start) + text + getText().substring(end);
        if (newText.matches(VALID_PATTERN)) {
            super.replaceText(start, end, text); // Allow valid input
        }
    }
 
    @Override
    public void replaceSelection(String text) {
        String newText = getText().substring(0, getSelection().getStart()) 
            + text + getText().substring(getSelection().getEnd());
        if (newText.matches(VALID_PATTERN)) {
            super.replaceSelection(text); // Allow valid input
        }
    }
 
    // Optional: Get value as Integer
    public Integer getValue() {
        String text = getText();
        return text.isEmpty() ? 0 : Integer.parseInt(text);
    }
}

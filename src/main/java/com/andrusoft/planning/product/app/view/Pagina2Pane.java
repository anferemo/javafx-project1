/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.andrusoft.planning.product.app.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

/**
 *
 * @author andrusoft
 */
public class Pagina2Pane extends GridPane{
    
     //protected Pane pane;
    
     public Pagina2Pane() {
        super();
        var label = new Label("Esta es la pagina 2");
        //super.getChildren().add(label);
        super.setMinSize(400, 200);
        super.setPadding(new Insets(10,10,10,10));
        super.setVgap(5);
        super.setHgap(5);
        super.setAlignment(Pos.CENTER);
        super.add(label, 0, 0);
        super.setVisible(false);
        super.setId("Pagina 2");
        
    }
    
}

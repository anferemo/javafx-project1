/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.andrusoft.planning.product.app.view;

import com.andrusoft.planning.product.app.model.Product;
import java.util.ArrayList;
import java.util.List;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Border;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 *
 * @author andrusoft
 */
public class ProductoWindow extends VBox{
        
    /*private GridPane createPane;
    private TableView<Product> contentPane; */
    
    public ProductoWindow() {
        super();
                
        var contentPane= new TableView<>();
        var label = new Label("Maestro de Productos");
        Text textoNombre = new Text("Nombre Producto: ");
        var txtNombre = new TextField();
        var boxProductCreate = new HBox(textoNombre, txtNombre);        
        var btnGuardar = new Button("Crear Producto");         
        super.setPadding(new Insets(5, 10, 10, 10));        
        super.setAlignment(Pos.TOP_LEFT);
        
        
        //hbox
        super.getChildren().add(label);        
        super.getChildren().add(boxProductCreate);
        super.getChildren().add(btnGuardar);
        super.getChildren().add(contentPane);
        super.setVisible(false);
        super.setId("ProductWindow");        
        
    }    
    
}

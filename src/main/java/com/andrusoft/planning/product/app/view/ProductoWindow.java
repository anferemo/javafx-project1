/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.andrusoft.planning.product.app.view;

import com.andrusoft.planning.product.app.controller.ProductController;
import com.andrusoft.planning.product.app.model.Product;
import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventType;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
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
    
    private ProductController controller;
    private Button btnGuardar;
    private TextField inputNombre;
    private TableView<Product> tabla;
    
    public ProductoWindow() {
        super();
        this.controller=new ProductController();
        var contentPane = createProductTable();
        var label = new Label("Maestro de Productos");
        Text textoNombre = new Text("Nombre Producto: ");
        this.inputNombre = new TextField();
        var boxProductCreate = new HBox(textoNombre, inputNombre);        
        createGuardarProdutoButton();
        
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

    private void createGuardarProdutoButton() {
        Dialog<String> dialog = new Dialog<>();        
        this.btnGuardar = new Button("Crear Producto");
        this.btnGuardar.setId("guardarProductoBtn");
        this.btnGuardar.setOnAction((ActionEvent action) -> {
            dialog.getDialogPane().getButtonTypes().removeAll();
            if (this.inputNombre.getText().isBlank()) {
                dialog.setContentText("No hay nombre para registrar");
                dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);                
            } else {
                controller.addProduct(new Product(this.inputNombre.getText(),""));                
                dialog.setContentText("Producto: " +this.inputNombre.getText() + " Creado");
                dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
                updateProductTable();
            }
            dialog.show();
        });
        
    }

    private void updateProductTable() {
        ObservableList<Product> products = FXCollections.observableArrayList(controller.getProductList());
        this.tabla.setItems(products);
    }

    private TableView<?> createProductTable() {
        
        TableColumn<Product,String> nombreColumn = new TableColumn<>("Nombre");
        nombreColumn.setCellValueFactory(cellData->new SimpleStringProperty(cellData.getValue().getNombre()));
        
        this.tabla = new TableView<>();
        this.tabla.getColumns().addAll(nombreColumn);
        updateProductTable();        
        return tabla;
    }
    
}

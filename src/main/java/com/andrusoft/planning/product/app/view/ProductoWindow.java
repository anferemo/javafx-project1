/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.andrusoft.planning.product.app.view;

import com.andrusoft.planning.product.app.controller.ProductController;
import com.andrusoft.planning.product.app.model.Product;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 *
 * @author andrusoft
 */
public class ProductoWindow extends VBox{    
    
    private ProductController controller;
    private Button btnGuardar;
    private Button btnCreateProductDialog;
    private TextField inputNombre;
    private TableView<Product> tabla;
    private Dialog productDialog;
    private Alert alert;
    
    public ProductoWindow() {
        super();
        this.controller=new ProductController();
        var contentPane = createProductTable();
        this.btnCreateProductDialog = new Button("Crear Producto");
        var label = new Label("Maestro de Productos");
        this.inputNombre = new TextField();
        var boxProductCreate = new VBox(label, btnCreateProductDialog);
        this.setId("ProductWindow");
        showGuardarProductoAction();
        createGuardarProductoDialog();
        
        super.setPadding(new Insets(5, 10, 10, 10));        
        super.setAlignment(Pos.TOP_LEFT);
        
        super.getChildren().add(boxProductCreate);
        super.getChildren().add(contentPane);
        super.setVisible(false);
        super.setId("ProductWindow");        
        
    }

    private void createGuardarProductoDialog() {
        this.productDialog = new Dialog<>();         
        this.btnGuardar = new Button("Guardar");
        this.btnGuardar.setId("guardarProductoBtn");
        this.productDialog.getDialogPane().setHeaderText("Crear Producto");        
        this.productDialog.getDialogPane().setContent(createProductForm());
        this.productDialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
        this.productDialog.getDialogPane().setCenterShape(true);        
        this.productDialog.setResultConverter(dialogBtn -> {
            if(dialogBtn==ButtonType.OK) {
                if (this.inputNombre.getText().isBlank()) {
                    alert = new Alert(AlertType.ERROR);
                    alert.setContentText("No hay nombre para registrar");                    
                } else {
                    controller.addProduct(new Product(this.inputNombre.getText(),""));                
                    alert = new Alert(AlertType.INFORMATION);
                    alert.setContentText("Producto: " +this.inputNombre.getText() + " Creado");                    
                    updateProductTable();
                }
                alert.showAndWait();
            } else  if (dialogBtn==ButtonType.CANCEL){
                this.productDialog.close();
            }
            return null;
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
    
    private Node createProductForm() {        
        HBox newProductBox = new HBox();                
        newProductBox.setAlignment(Pos.CENTER);
        newProductBox.getChildren().add(new Text("Nombre Producto: "));
        newProductBox.getChildren().add(this.inputNombre);
        newProductBox.setId("productPane");        
        return newProductBox;        
    }

    private void showGuardarProductoAction() {
        this.btnCreateProductDialog.setOnAction((ActionEvent action) -> {
            this.productDialog.show();
        });
    }
    
}

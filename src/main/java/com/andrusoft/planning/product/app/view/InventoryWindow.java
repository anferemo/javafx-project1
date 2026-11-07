/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.andrusoft.planning.product.app.view;

import com.andrusoft.planning.product.app.controller.InventoryController;
import com.andrusoft.planning.product.app.model.Inventory;
import java.io.File;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.stage.Window;

/**
 *
 * @author andrusoft
 */
public class InventoryWindow extends VBox{
    private Button btnCargar;
    private Label lblTitulo;
    private TableView<Inventory> tablaInventario;
    private FileChooser fileChooser;
    private File fileSelected;
    private InventoryController controller;

    public InventoryWindow() {
        super();
        this.setId("InventoryWindow");
        createBtnCargar();
        createTablaInventario();
        this.controller=new InventoryController();
        this.fileChooser=new FileChooser();
        this.fileChooser.setTitle("Seleccionar Archivo a cargar");
        this.fileChooser.getExtensionFilters().add(new ExtensionFilter("Excel FIles", "*.xls"));
        HBox boxButtons = createButtonBox();        
        super.setAlignment(Pos.TOP_LEFT);        
        this.lblTitulo=new Label("Inventario");
        ObservableList<Node> uiList = FXCollections.observableArrayList();
        uiList.add(this.lblTitulo);
        uiList.add(boxButtons);
        uiList.add(this.tablaInventario);
        super.setSpacing(10);
        super.setVisible(false);
        super.getChildren().setAll(uiList);
    }
    
    private void createBtnCargar() {
        this.btnCargar=new Button("Cargar Información Inventario");
        this.btnCargar.setOnAction((ActionEvent action) -> {
            Stage stageLoadFile= new Stage();
            stageLoadFile.setHeight(300);
            stageLoadFile.setWidth(150);
            InventoryWindow.this.fileSelected = InventoryWindow.this.fileChooser.showOpenDialog(stageLoadFile);
            this.controller.loadInventoryFile(this.fileSelected);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Info");
            alert.setContentText("El archivo seleccionado es: " + InventoryWindow.this.fileSelected.getAbsolutePath());
            alert.showAndWait();
        });
        
    }
    
    private void createTablaInventario() {
        this.tablaInventario=new TableView<>();
    }
    
    private HBox createButtonBox() {
        HBox boxButtons = new HBox();
        boxButtons.getChildren().add(this.btnCargar);
        boxButtons.setSpacing(15);
        return boxButtons;
    }
    
    
}

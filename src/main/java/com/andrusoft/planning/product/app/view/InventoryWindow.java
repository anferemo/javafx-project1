/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.andrusoft.planning.product.app.view;

import com.andrusoft.planning.product.app.controller.CompareDoubleValue;
import com.andrusoft.planning.product.app.controller.InventoryController;
import com.andrusoft.planning.product.app.model.Inventory;
import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

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
    private Label lblProductField;
    private TextField txtProductField;
    private Button btnSearchProduct;

    public InventoryWindow() {
        super();
        this.setId("InventoryWindow");
        this.lblProductField= new Label("Producto:");
        this.txtProductField=new TextField();
        createBtnCargar();
        createBtnSearch();
        this.controller=new InventoryController();
        createTablaInventario();
        this.fileChooser=new FileChooser();
        this.fileChooser.setTitle("Seleccionar Archivo a cargar");
        this.fileChooser.getExtensionFilters().add(new ExtensionFilter("Excel FIles", "*.xls"));
        HBox boxButtons = createButtonBox();
        HBox boxSearch = createSearchBox();
        super.setAlignment(Pos.TOP_LEFT);        
        this.lblTitulo=new Label("Inventario");
        ObservableList<Node> uiList = FXCollections.observableArrayList();
        uiList.add(this.lblTitulo);
        uiList.add(boxButtons);        
        uiList.add(this.tablaInventario);
        uiList.add(boxSearch);
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
            //updateInventoryTable();
            createTablaInventario();
            alert.showAndWait();            
        });
        
    }
    
    private void createTablaInventario() {
        this.tablaInventario=new TableView<>();        
        
        TableColumn<Inventory,String> fechaCargue = new TableColumn<>("Fecha Cargue");
        DateTimeFormatter dmy = DateTimeFormatter.ofPattern("yyyy-MM-dd");        
        fechaCargue.setCellValueFactory(cellData -> {            
            LocalDate fchCarga =  cellData.getValue().getFechaCarga();
            return new SimpleStringProperty(fchCarga!=null ? fchCarga.format(dmy): "");
        });        
        
        TableColumn<Inventory,String> referencia = new TableColumn<>("Referencia");
        referencia.setCellValueFactory(cellData-> {
            return new SimpleStringProperty(String.valueOf(cellData.getValue().getReferencia()));
        });
        
        TableColumn<Inventory,String> producto = new TableColumn<>("Producto");
        producto.setCellValueFactory(cellData-> {
            return new SimpleStringProperty(cellData.getValue().getNombreProducto());
        });
        
        TableColumn<Inventory,String> tamLote = new TableColumn<>("Tamaño Lote");
        tamLote.setCellValueFactory(cellData-> {
            return new SimpleStringProperty(String.valueOf(cellData.getValue().getTamanoLote()));
        });
                
                
        TableColumn<Inventory,String> existencia = new TableColumn<>("Existencia");
        existencia.setCellValueFactory(cellData-> {
            return new SimpleStringProperty(String.valueOf(cellData.getValue().getExistencia()));
        });
        
        TableColumn<Inventory,String> invMin = new TableColumn<>("Inventario Mínimo");
        invMin.setCellValueFactory(cellData-> {
            return new SimpleStringProperty(String.valueOf(cellData.getValue().getInventarioMinimo()));
        });
        
        TableColumn<Inventory,String> expor = new TableColumn<>("Expor");
        expor.setCellValueFactory(cellData-> {
            return new SimpleStringProperty(String.valueOf(cellData.getValue().getExpor()));
        });
        
        TableColumn<Inventory,String> ace = new TableColumn<>("Ace");
        ace.setCellValueFactory(cellData-> {
            return new SimpleStringProperty(String.valueOf(cellData.getValue().getAce()));
        });
        
        TableColumn<Inventory,String> programacion = new TableColumn<>("Programacion");
        programacion.setCellValueFactory(cellData-> {
            return new SimpleStringProperty(String.valueOf(cellData.getValue().getProgramacion()));
        });
        
        TableColumn<Inventory, String> indicador1 = new TableColumn<>("Indicador1");
        indicador1.setCellValueFactory(cellData-> {            
            return new SimpleStringProperty(String.valueOf(cellData.getValue().getIndicador1()));
        });        
        
        
        TableColumn<Inventory, String> mes = new TableColumn<>("Mes");
        mes.setCellValueFactory(cellData-> {            
            return new SimpleStringProperty(String.valueOf(cellData.getValue().getMes()));
        });        
         
        this.tablaInventario.getColumns().addAll(fechaCargue, referencia, producto, tamLote, existencia, invMin, indicador1, expor, ace, programacion, mes);        
        updateInventoryTable();
        //colorTableRow();
        /*this.tablaInventario.setRowFactory(tv-> new TableRow<Inventory>() {
            @Override
            protected void updateItem(Inventory inv, boolean empty) {
                if (inv!=null) {                    
                    if(CompareDoubleValue.isNegative(inv.getIndicador1())) {
                        setStyle("-fx-background-color: #ffcccc;");
                    }
                }
            }
        });*/
    }
    
    private HBox createButtonBox() {
        HBox boxButtons = new HBox();
        boxButtons.getChildren().add(this.btnCargar);
        boxButtons.setSpacing(15);
        return boxButtons;
    }
    
    private HBox createSearchBox() {
        HBox boxSearch = new HBox();
        boxSearch.getChildren().add(this.lblProductField);
        boxSearch.getChildren().add(this.txtProductField);
        boxSearch.getChildren().add(this.btnSearchProduct);
        boxSearch.setSpacing(15);
        return boxSearch;
    }
    
    private void createBtnSearch() {
        this.btnSearchProduct=new Button("Buscar x Producto");
        this.btnSearchProduct.setOnAction((ActionEvent action) -> {            
            if (this.txtProductField!=null && !this.txtProductField.getText().isBlank() && !this.txtProductField.getText().isEmpty()) {
                updateInventoryTableByProduct(this.txtProductField.getText());
            } else {
                updateInventoryTable();
            }
        });
        
    }

    private void updateInventoryTable() {
        ObservableList<Inventory> inventario = FXCollections.observableArrayList(controller.getInventoryByDate(LocalDate.now()));        
        this.tablaInventario.setItems(inventario);
        colorTableRow();
    }
    
    private void colorTableRow() {
        this.tablaInventario.setRowFactory(tv-> new TableRow<Inventory>() {
            @Override
            protected void updateItem(Inventory inv, boolean empty) {
                if (inv!=null) {                    
                    if(CompareDoubleValue.isNegative(inv.getIndicador1())) {
                        setStyle("-fx-background-color: #ffcccc;");
                    }
                }
            }
        });
    }

    private void updateInventoryTableByProduct(String productName) {        
        ObservableList<Inventory> inventario = FXCollections.observableArrayList(controller.getInventoryByProduct(productName+'%'));        
        this.tablaInventario.setItems(inventario);
        colorTableRow();
        
    }
    
    
}

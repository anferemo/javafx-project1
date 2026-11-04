/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.andrusoft.planning.product.app.view;

import com.andrusoft.planning.product.app.controller.LoteController;
import com.andrusoft.planning.product.app.controller.ProductController;
import com.andrusoft.planning.product.app.custom.NumericTextField;
import com.andrusoft.planning.product.app.dao.LoteDAO;
import com.andrusoft.planning.product.app.model.Lote;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 *
 * @author andrusoft
 */
public class LoteWindow extends VBox{
    
    private LoteController controller;
    private ProductController prodController;
    private Label lblTitle;
    private Dialog dialogNewLote;
    private Button showDialogButton;
    private TableView<Lote> tablaLotes;
    private TextField txtNewCodigo;
    private NumericTextField txtNewCantidad;
    private ChoiceBox<String> productSelect;
    private Alert alert;

    public LoteWindow() {
        super();
        this.controller=new LoteController();
        this.prodController=new ProductController();                
        this.lblTitle= new Label("Tabla de Lotes");
        createTablaLotes();
        //¿Crear contentPane para mostrar la tabla?
        
        createLoteDialog();
        createShowLoteDialogButton();
        
        this.setId("LoteWindow");
        var boxLoteWindow = new VBox(lblTitle, showDialogButton, tablaLotes);        
        super.setVisible(false);
        super.getChildren().add(boxLoteWindow);
        
    }

    private void createLoteDialog() {
        this.dialogNewLote=new Dialog<>();
        this.dialogNewLote.setHeaderText("Nuevo Lote");
        this.dialogNewLote.getDialogPane().setContent(createNewLoteForm());
        this.dialogNewLote.getDialogPane().getButtonTypes().addAll(ButtonType.CANCEL, ButtonType.OK);
        this.dialogNewLote.setResultConverter(dialogBtn-> {
            if(dialogBtn==ButtonType.OK) {
                
                Lote lote = new Lote();
                if(this.txtNewCodigo.getText().isEmpty() || this.productSelect.getValue()==null || this.txtNewCantidad.getValue() == 0) {
                    alert = new Alert(AlertType.ERROR);
                    alert.setContentText("Por favor diligenciar los campos requeridos!!");
                } else {
                    this.alert = new Alert(AlertType.INFORMATION);
                    lote.setCodigo(this.txtNewCodigo.getText());
                    lote.setProducto(this.productSelect.getValue());
                    lote.setCantidadProgramada(this.txtNewCantidad.getValue());
                    controller.addLote(lote);
                    this.txtNewCodigo.setText("");
                    this.productSelect.setValue(null);
                    this.txtNewCantidad.clear();
                    alert.setContentText("Lote almacenado correctamente");
                    updateLoteTable();
                    
                }
                alert.showAndWait();
            }
            return null;
        });
    }
    
    private void createShowLoteDialogButton() {
        this.showDialogButton=new Button("Crear Lote");
        this.showDialogButton.setOnAction((ActionEvent action)->{
            this.dialogNewLote.show();
        });
    }
    
    private Node createNewLoteForm() {
        GridPane gridPane = new GridPane();
        gridPane.setMinSize(400, 200);
        gridPane.setPadding(new Insets(10,10,10,10));
        gridPane.setVgap(5);
        gridPane.setHgap(5);
        gridPane.setAlignment(Pos.CENTER);
        Text lblCodigo = new Text("Codigo: ");
        this.txtNewCodigo = new TextField();
        Text lblCantidad = new Text("Cantidad: ");
        this.txtNewCantidad = new NumericTextField();
        Text lblProducto = new Text("Producto: ");
        
        fillProductSelect();
        //ChoiceBox<String> productSelect = new ChoiceBox<>();
        
        gridPane.add(lblCodigo, 0, 0);
        gridPane.add(this.txtNewCodigo, 1, 0);
        gridPane.add(lblProducto, 0, 1);
        gridPane.add(this.productSelect, 1, 1);
        gridPane.add(lblCantidad, 0, 2);
        gridPane.add(this.txtNewCantidad, 1, 2);
        
        return gridPane;
    }

    private void createTablaLotes() {
        
        TableColumn<Lote,String> codigoColumn = new TableColumn<>("Codigo");
        codigoColumn.setCellValueFactory(cellData->new SimpleStringProperty(cellData.getValue().getCodigo()));
        
        TableColumn<Lote,String> nombreProdColumn = new TableColumn<>("Producto");
        nombreProdColumn.setCellValueFactory(cellData->new SimpleStringProperty(cellData.getValue().getProducto()));
        
        DateTimeFormatter dmy = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        TableColumn<Lote,String> fechaEnvase = new TableColumn<>("Fecha De Envase");      
        fechaEnvase.setCellValueFactory(cellData-> {
            
            LocalDate celda = cellData.getValue().getFechaEnvase();
            return new SimpleStringProperty(celda!=null ? celda.format(dmy): "");
        });
        
        TableColumn<Lote,String> fechaAprobacion = new TableColumn<>("Fecha Aprobacion MB");      
        fechaAprobacion.setCellValueFactory(cellData-> {
            
            LocalDate celda = cellData.getValue().getFechaAprobacionMB();
            return new SimpleStringProperty(celda!=null ? celda.format(dmy): "");
        });
        
        this.tablaLotes = new TableView<>();
        this.tablaLotes.getColumns().addAll(codigoColumn, nombreProdColumn, fechaEnvase, fechaAprobacion);
        updateLoteTable();
    }
    
    
    private void updateLoteTable() {
        ObservableList<Lote> lotes= FXCollections.observableArrayList(controller.getListaLotes());
        this.tablaLotes.setItems(lotes);
    }

    private void fillProductSelect() {
        this.productSelect=new ChoiceBox<>();        
        ObservableList<String> prodNames= FXCollections.observableArrayList(prodController.getProductNames());
        this.productSelect.setItems(prodNames);
    }
    
    
    
    
}

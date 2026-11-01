/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.andrusoft.planning.product.app.view;

import com.andrusoft.planning.product.app.model.Lote;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
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
    
    private Label lblTitle;
    private Dialog dialogNewLote;
    private Button showDialogButton;
    private TableView<Lote> tablaLotes;

    public LoteWindow() {
        super();
        this.lblTitle= new Label("Tabla de Lotes");
        this.tablaLotes= new TableView<>();        
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
        TextField txtCodigo = new TextField();
        Text lblCantidad = new Text("Cantidad: ");
        TextField txtCantidad = new TextField();
        Text lblProducto = new Text("Producto: ");
        ChoiceBox<String> productSelect = new ChoiceBox<>();
        
        gridPane.add(lblCodigo, 0, 0);
        gridPane.add(txtCodigo, 1, 0);
        gridPane.add(lblProducto, 0, 1);
        gridPane.add(productSelect, 1, 1);
        gridPane.add(lblCantidad, 0, 2);
        gridPane.add(txtCantidad, 1, 2);
        
        return gridPane;
    }
    
    
    
    
}

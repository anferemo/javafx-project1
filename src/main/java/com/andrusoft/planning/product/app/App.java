package com.andrusoft.planning.product.app;

import com.andrusoft.planning.product.app.view.LoteWindow;
import com.andrusoft.planning.product.app.view.ProductoWindow;
import com.andrusoft.planning.product.app.view.Pagina2Pane;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


/**
 * JavaFX App
 */
public class App extends Application {
    
    private static final Logger logger = Logger.getLogger(App.class.getName());

    @Override
    public void start(Stage stage) {
        var javaVersion = SystemInfo.javaVersion();
        var javafxVersion = SystemInfo.javafxVersion();
        
        DatabaseConnection database= null;
        
        try {
            database = new DatabaseConnection();
            if(database.schemaExists()) {
                logger.log(Level.INFO, "Esquema existe");
            } else {                
                logger.log(Level.INFO, "Esquema NO existe");
                database.createSchema();
            }
        } catch (Exception ex) {
            logger.log(Level.SEVERE, ex.getMessage());
        }

        var label = new Label("Hello, JavaFX " + javafxVersion + ", running on Java " + javaVersion + ".");
        label.setId("Principal");
        
        //Se crean las vistas(paginas)
        var productoView = new ProductoWindow();
        var loteView = new LoteWindow();
        
        StackPane paginaApp = new StackPane(label, productoView, loteView);
        paginaApp.setId("contenido");
        
        MenuBar menuBar = createMenuUI(paginaApp);
        
        VBox menuBox = new VBox(menuBar);
        menuBox.setId("MenuBar");
        
        
        VBox principal = new VBox(menuBox, paginaApp);
        var scene = new Scene(principal, 640, 480);
        scene.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());
        
        stage.setScene(scene);
        stage.show();
    }
    
    
    private MenuBar createMenuUI(Pane contenido) {
        MenuBar menuBar = new MenuBar();
        Menu menu1 = new Menu("Producto");
        MenuItem itemVerProducto = new MenuItem("Ver");
        menu1.getItems().add(itemVerProducto);
        MenuItem itemVolver = new MenuItem("Volver"); 
        menu1.getItems().add(itemVolver);
        
        Menu menu2 = new Menu("Lotes");
        MenuItem item2 = new MenuItem("Ver"); 
        menu2.getItems().add(item2);        
        menuBar.getMenus().addAll(menu1, menu2);
        
         itemVerProducto.setOnAction((ActionEvent action) -> {            
            contenido.getChildren().forEach((var page) -> {
                if(!page.getId().equals("ProductWindow") && !page.getId().equals("MenuBar")) {
                    page.setVisible(false);
                } else {
                    page.setVisible(true);
                }
            });            
        });
        
        item2.setOnAction((ActionEvent action) -> {
            contenido.getChildren().forEach((var page) -> {
                if(!page.getId().equals("LoteWindow") && !page.getId().equals("MenuBar")) {
                    page.setVisible(false);
                } else {
                    page.setVisible(true);
                }
            });
        });
        
        itemVolver.setOnAction((ActionEvent action) -> {
            contenido.getChildren().forEach((var page) -> {
                if(!page.getId().equals("Principal") && !page.getId().equals("MenuBar")) {
                    page.setVisible(false);
                } else {
                    page.setVisible(true);
                }
            });            
        });
        
        return menuBar;
    }

    public static void main(String[] args) {
        launch();
    }

}
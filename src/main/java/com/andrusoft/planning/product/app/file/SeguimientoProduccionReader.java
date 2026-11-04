/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.andrusoft.planning.product.app.file;

import com.andrusoft.planning.product.app.model.Lote;
import java.io.File;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.scene.control.TableCell;
import org.odftoolkit.odfdom.changes.Table;
import org.odftoolkit.odfdom.doc.OdfDocument;
import org.odftoolkit.odfdom.doc.table.OdfTable;
import org.odftoolkit.odfdom.doc.table.OdfTableCell;
import org.odftoolkit.odfdom.doc.table.OdfTableRow;

/**
 *
 * @author andrusoft
 */
public class SeguimientoProduccionReader {

    private static final Logger logger = Logger.getLogger(SeguimientoProduccionReader.class.getName());
    
    public SeguimientoProduccionReader() {
    }
    
    public List<Lote> loadFile() throws Exception {
        List<Lote> listaData = new ArrayList<>();
        //SEGUIMIENTO PRODUCCION OPEN 2026.ods
        OdfDocument document = OdfDocument.loadDocument("/home/andrusoft/Documentos/planeacion-produccion/SEGUIMIENTO PRODUCCION OPEN 2026.ods");
        OdfTable  table = document.getTableByName("Hoja1");
        logger.log(Level.INFO, "Archivo cargado!!! {0}", table.getTableName());
        
        int nRows = 2029; //table.getRowCount();
        //int nRows = table.getRowByIndex(1).getCellCount();
        int nCols = 9;
        OdfTableCell cell = null;
        
        String prodName = table.getCellByPosition(1, 1).getDisplayText();
        
        for(int nRowIndex = 1; nRowIndex < nRows; nRowIndex++)
        {            
            Lote lote = loadLoteData(nRowIndex, table, prodName);
            if(lote!=null) {
                listaData.add(lote);
            }            
         }

         listaData.stream().forEach(elem->{
             logger.log(Level.INFO, "Lote: {0}", elem.toString());
         });
        return listaData;
    }
    
    
    private Lote loadLoteData(int rowIndex, OdfTable  table, String productName) {
        Lote lote = new Lote();        
        
        String pName = table.getCellByPosition(0, rowIndex).getDisplayText();
        String loteVal = table.getCellByPosition(1, rowIndex).getDisplayText();
        if((pName.isBlank() && loteVal.isBlank()) ||(pName.isEmpty() && pName.isEmpty())) {
            return null;
        }
        
        if (pName.isBlank()) {
            lote.setProducto(productName);
        } else {
            lote.setProducto(pName.trim());
            productName = pName;
        }
        
        lote.setCodigo(table.getCellByPosition(1, rowIndex).getDisplayText());
        lote.setCantidadProgramada(Long.parseLong(table.getCellByPosition(2, rowIndex).getDisplayText()));
        
        
        String cell = table.getCellByPosition(3, rowIndex).getDisplayText();
        try {        
            if (!cell.isBlank() && !cell.isEmpty()) {
                LocalDate dateEnv = LocalDate.parse(cell);
                lote.setFechaEnvase(dateEnv);
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage());
        }
        
        //Fecha de aprobacion
        try {
            cell = table.getCellByPosition(4, rowIndex).getDisplayText();
            if (!cell.isBlank() && !cell.isEmpty()) {
                LocalDate dateEnv = LocalDate.parse(cell);
                lote.setFechaAprobacionMB(dateEnv);
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage());
        }
        
        //Fecha de emision
        try {
            cell = table.getCellByPosition(5, rowIndex).getDisplayText();
            if (!cell.isBlank() && !cell.isEmpty()) {
                LocalDate dateEnv = LocalDate.parse(cell);
                lote.setFechaEmisionOP(dateEnv);
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage());
        }
        
        //cantidad entregada ACE
        cell = table.getCellByPosition(6, rowIndex).getDisplayText();
        if (!cell.isBlank() && !cell.isEmpty()) {
            lote.setCantidadEntregada(Long.parseLong(cell));
        }       
        
        //Observaciones
        cell = table.getCellByPosition(8, rowIndex).getDisplayText();
        if (!cell.isBlank() && !cell.isEmpty()) {
            lote.setObservaciones(cell);
        }
        
        //Estado Orden
        cell = table.getCellByPosition(9, rowIndex).getDisplayText();
        if (!cell.isBlank() && !cell.isEmpty()) {
            lote.setEstadoOrigen(cell);
        }
        
        return lote;
    }
}

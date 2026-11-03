/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.andrusoft.planning.product.app.file;

import com.andrusoft.planning.product.app.model.Lote;
import java.io.File;
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
        
        int nRows = 2089; //table.getRowCount();
        //int nRows = table.getRowByIndex(1).getCellCount();
        int nCols = 9;
        OdfTableCell cell = null;
        
        for(int nRowIndex = 0; nRowIndex < nRows; nRowIndex++)
        {
            int nColIndex = 0;
            for( ;nColIndex < nCols; nColIndex++)
            {
                cell =table.getCellByPosition(nColIndex, nRowIndex);
                String texto = cell.getDisplayText();
                if(texto.isBlank() && nColIndex==2) {
                    break;
                } else {
                    logger.log(Level.INFO, "Valor Celda: {0}", cell.getDisplayText());
                }
            }
         }        
        return listaData;
    }
}

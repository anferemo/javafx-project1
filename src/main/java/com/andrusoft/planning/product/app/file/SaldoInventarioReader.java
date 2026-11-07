/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.andrusoft.planning.product.app.file;

import com.andrusoft.planning.product.app.model.Inventory;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;

/**
 *
 * @author andrusoft
 */
public class SaldoInventarioReader {
    private static final Logger logger = Logger.getLogger(SeguimientoProduccionReader.class.getName());

    public SaldoInventarioReader() {
    }
    
    public List<Inventory> loadFile(File file) {
        List<Inventory> lista = new ArrayList<>();
        try(FileInputStream fis= new FileInputStream(file)) {
            HSSFWorkbook libro= new HSSFWorkbook(fis);
            HSSFSheet hoja = libro.getSheetAt(0);
            int rowNumber = 4;
            boolean endOfFile = false;
            while (!endOfFile) {                
                Row row = hoja.getRow(rowNumber);
                if(row.getCell(0)==null) {
                    endOfFile=true;
                }
                Inventory itemInventario = mapData(row);
                logger.log(Level.INFO, "El objeto cargado es: {0}",  itemInventario.toString());
                logger.log(Level.INFO, "*******************************");
                rowNumber++;
            }            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SaldoInventarioReader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(SaldoInventarioReader.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }
    
    
    private Inventory mapData(Row row) {
        Inventory itemInventario = new Inventory();
        Cell celda = row.getCell(1);
        if (celda!=null) {
            if(celda.getCellType().equals(CellType.NUMERIC)) {
                itemInventario.setReferencia(celda.getNumericCellValue());
            }        

            celda = row.getCell(2);
            if(celda.getCellType().equals(CellType.STRING)) {
                itemInventario.setNombreProducto(celda.getStringCellValue().trim());
            }

            celda = row.getCell(3);
            if(celda.getCellType().equals(CellType.NUMERIC)) {
                itemInventario.setTamanoLote(celda.getNumericCellValue());
            }

            celda = row.getCell(4);
            if(celda.getCellType().equals(CellType.STRING)) {
                itemInventario.setVence(celda.getStringCellValue().trim());
            }

            celda = row.getCell(5);
            if(celda.getCellType().equals(CellType.NUMERIC)) {
                itemInventario.setExistencia(celda.getNumericCellValue());
            }

            celda = row.getCell(6);
            if(celda.getCellType().equals(CellType.NUMERIC)) {
                itemInventario.setInventarioMinimo(celda.getNumericCellValue());
            }
            //Atributo calculdao (7)

            celda = row.getCell(8);
            if(celda.getCellType().equals(CellType.NUMERIC)) {
                itemInventario.setExpor(celda.getNumericCellValue());
            }

            celda = row.getCell(9);
            if(celda.getCellType().equals(CellType.NUMERIC)) {
                itemInventario.setAce(celda.getNumericCellValue());
            }

            celda = row.getCell(10);
            if(celda.getCellType().equals(CellType.NUMERIC)) {
                itemInventario.setProgramacion(celda.getNumericCellValue());
            }

            itemInventario.setFechaCarga(LocalDate.now());
        }
        return itemInventario;
    }
    
}

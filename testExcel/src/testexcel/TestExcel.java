/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testexcel;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.DateTime;
import jxl.write.WritableCell;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;


/**
 * Aplicación para la lectura de ficheros excel desde java
 * @author david.fernandez
 */
public class TestExcel {
    
    /**
     * Función que lee el número de hojas de un excel pasado cómo parámetros 
     * y muestra por pantalla el número de hojas y el nombre de estas
     * @param archivo , con la ruta del archivo Excel
     **/
    public static void leerExcel(File archivo){
        try {
            Workbook libro = Workbook.getWorkbook(archivo);
            //Mostramos información de libro
            int numHojas = libro.getNumberOfSheets();
            String[] hojas = libro.getSheetNames();
            System.out.println("Número de hojas: " + numHojas);
            for (String hoja : hojas) {
                System.out.println("Hoja: " + hoja); 
            }
            //Ahora vamos a crear un nuevo fichero xls y le vamos a copiar 
            //algunos elementos del original. 
            
            ///Creamos el libro con posibilidad de escritura
            WritableWorkbook salida = Workbook.createWorkbook(new File("E:\\salida.xls"));
            WritableSheet ws = salida.createSheet("salida", 0); 
            ws.setName("output1");
        
            
        } catch (IOException | BiffException ex) {
            Logger.getLogger(TestExcel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        String ruta="E:\\jtest.xls";
        File fich = new File(ruta);
        leerExcel(fich);
    }
    
}

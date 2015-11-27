/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testexcel;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import static testexcel.LecturaExcel.leerExcelJXL;
import static testexcel.LecturaExcel.leerExcelXls;

/**
 *
 * @author david.fernandez
 */
public class Main {

    /**
     * Función principal del programa
     *
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        List DatosExcel = new ArrayList();

//        String ruta = "E:\\jtest.xls";
//        File fich = new File(ruta);
//        leerExcelJXL(fich);
        String rutaxls = "E:\\test.xls";
        long time_start, time_end;
        time_start = System.currentTimeMillis();
        DatosExcel = leerExcelXls(rutaxls); // llamamos a la tarea
        time_end = System.currentTimeMillis();
        System.out.println("Tiempo:" + (time_end - time_start) + "milisegundos");
        
        
//       if(DatosExcel!=null){
//               System.out.println("Tengo datos:" + DatosExcel.toString());
//               for (int i = 0; i < DatosExcel.size(); i++) {
//                 System.out.println("DATO "+i+": " + DatosExcel.get(i));
//               }
//       }

    }

}

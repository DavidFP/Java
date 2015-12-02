package testexcel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import static testexcel.LecturaExcel.XLStoHASHSET;

/**
 * Función principal del programa
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
        String rutaxls = "E:\\SALIDAS\\BOM_MATERIALES\\opcion1.xls";
        long time_start, time_end;
        
        //DatosExcel = leerExcelXls(rutaxls); // llamamos a la tarea
        
       

       // System.out.println(DatosExcel.toString());

        //Recorrido a base de un iterador de la lista de elementos que tiene el
        //fichero de excel
//       for(int i=0; i < DatosExcel.size();i++){
//           System.out.println("ELEMENTO: " + i + " _ " + DatosExcel.get(i));
//       } 
        HashSet<Articulo> listadoArticulos;
        time_start = System.currentTimeMillis();
        listadoArticulos = XLStoHASHSET(rutaxls);
        time_end = System.currentTimeMillis();
        System.out.println("Tiempo:" + (time_end - time_start) + "milisegundos");
        ///Ya tenemos los articulos del excel capturados, ahora hay que procesarlos
        
        //Buscamos uno de los elementos
        Articulo art = new Articulo(1, "LeaderPin_E1000_1" ,"");
        for(Articulo a: listadoArticulos){
            if(a.getPartNumber() == null ? art.getPartNumber() == null : a.getPartNumber().equals(art.getPartNumber())){
                System.out.println("_"+ a.toString());
            }
            
//            if(a.getPartNumber() == null ? art.getPartNumber() == null : a.getPartNumber().equals(art.getPartNumber())){
//                //Artículo encontrado, se muestra por pantalla
//                System.out.println("->" + a.toString());
//            }
        }
        
//        if(listadoArticulos.){
//            System.out.println("TRUE");
//        }else{
//            System.out.println("FALSE");
//        };
        
        

        System.out.println("En el listado hay:" + listadoArticulos.size());

        //// Creamos la estructura que almacenará los items de la hoja excel 
//       if(DatosExcel!=null){
//               System.out.println("Tengo datos:" + DatosExcel.toString());
//               for (int i = 0; i < DatosExcel.size(); i++) {
//                 System.out.println("DATO "+i+": " + DatosExcel.get(i));
//               }
//       }
    }

}

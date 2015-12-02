package testexcel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import static testexcel.LecturaExcel.XLStoHASHSET;
import static testexcel.LecturaExcel.buscarArticuloPartNumber;
import static testexcel.LecturaExcel.procesaEspacios;

/**
 * Función principal del programa
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

        String rutaxls = "E:\\SALIDAS\\BOM_MATERIALES\\opcion1.xls";
        long time_start, time_end;

        HashSet<Articulo> listadoArticulos;
        time_start = System.currentTimeMillis();
        listadoArticulos = XLStoHASHSET(rutaxls, 87);
        time_end = System.currentTimeMillis();
        System.out.println("Tiempo:" + (time_end - time_start) + "milisegundos");
        ///Ya tenemos los articulos del excel capturados, ahora hay que procesarlos

        //Procesamos el listado de articulos para quitarle 
        //los espacios en el Part Number
        
        procesaEspacios(listadoArticulos);
        
        String palabra = "Eye";
        palabra = palabra.replace(" ", "");
        if(buscarArticuloPartNumber(palabra, listadoArticulos)==0)
            if(buscarArticuloPartNumber(palabra, listadoArticulos)==0)
                System.out.println(":-( No se ha encontrado nada...");
        
        //Buscamos uno de los elementos
        Articulo art = new Articulo(1, "LeaderPin_E1000_1", "");


}


}

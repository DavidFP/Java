package testexcel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import static testexcel.LecturaExcel.XLStoHASHSET;
import static testexcel.LecturaExcel.XLStoHASHSETCatalogo;
import static testexcel.LecturaExcel.buscarArticuloNomenclature;
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
        
        String palabra = "plate";
        palabra = palabra.replace(" ", "");
        List<Articulo> encontrados = buscarArticuloPartNumber(palabra, listadoArticulos);
        if(encontrados.isEmpty()){
            System.out.println(":-( No se ha encontrado nada...");
        }else{
            System.out.println("Se han encontrado " + encontrados.size() + " articulos");
            for(Articulo a: encontrados)
                System.out.println(a.toString());
        }
        HashSet<MeusburguerProduct> articulosMeus = new HashSet<>();
        String rutaMeus = "E:\\BD\\catalogo1.xls";
        time_start = System.currentTimeMillis();
        articulosMeus =XLStoHASHSETCatalogo(rutaMeus, 0);
        time_end = System.currentTimeMillis();
        System.out.println("Tiempo:" + (time_end - time_start) + "milisegundos en leer la BD de " + articulosMeus.size()+ "elementos");

        //Buscamos uno de los elementos
        //Articulo art = new Articulo(1, "LeaderPin_E1000_1", "");


}


}

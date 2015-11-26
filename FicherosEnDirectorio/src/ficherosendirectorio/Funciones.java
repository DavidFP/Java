/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ficherosendirectorio;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author David.Fernandez
 */
public class Funciones {

    public int numelem = 0;
    public ArrayList<String> arrFicheros = new ArrayList<>();

/**
 * Devuelve la cantidad de ficheros nuevos en array de ficheros pasado
 **/    
    
    public int nuevos(String[] ficheros) {
        int n = 0;
        for (int i = 0; i < arrFicheros.size(); i++) {
            if (arrFicheros.get(i) == null ? ficheros[i] == null : arrFicheros.get(i).equals(ficheros[i])) {
                //No hay nuevo fichero
            } else {
                //Hay fichero nuevo, incrementa el contador
                n++;
            }
        }
        //Devuelve el número de ficheros contados
        return n;
    };
/**
 * Comprueba el directorio y muestra los ficheros que tiene
 **/
    public void checkDir() throws IOException {
        while (true) {
            String ruta = "D:\\subidas";
            File dir = new File(ruta);
            String[] ficheros = dir.list();
//            int news = nuevos(ficheros);
//            if (news != 0) {
//                System.out.println("No hay nuevos elementos");
//            } else {
            if (arrFicheros == null) {
                arrFicheros.addAll(Arrays.asList(ficheros));
            } else {

            }

            System.out.println("#####################################");
            System.out.println("Lista de: " + ruta);
            for (String fichero : ficheros) {

                System.out.println("-" + fichero);
            }

            
            System.out.println("Tiene " + ficheros.length + " elementos");
            System.out.println("-------------------------------------------------");
//            }
            try {
                Thread.sleep(10000);
            } catch (InterruptedException ex) {
                Logger.getLogger(FicherosEnDirectorio.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void detector2() throws IOException {
//        
//        Funciones   f = new Funciones();
//        f.checkDir();
        int total = 0;

        //Definimos la ruta del directorio de subida
        String ruta = "D:\\subidas";
        File dir = new File(ruta);
        //Creamos un mapa de Entero-Nombre que contenga los ficheros que tiene el directorio
        Map<Integer, String> ficheros = new HashMap<>();
        
        //Ejecución contínua de la funcion.
        while (true) {
            //Primero tenemos que comprobar que no sea la primera ejecución
            //Por lo tanto el mapa estará vacío
            if (!ficheros.isEmpty()) { //Si el mapa tiene elementos
                if (total == 0) {
                    System.out.println("El directorio está vacío");
                } else {
                    //Creamos un iterador para recorrer el mapa
                    Iterator it = ficheros.keySet().iterator();
                    //Hacemos una búsqueda por valor para comprobar si hay nuevos
                    //Creamos un array de String temporal para almacenar los 
                    //Nuevos elementos del directorio
                    String[] temporal = dir.list();
                    //ficheros en el directorio
                    while (it.hasNext()) {
                        //Variable booleana para comprobar si hay nuevo
                        boolean encontrado;
                        int val = 1;
                        //Definimos el valor de encontrado para cada elemento
                        encontrado = ficheros.containsValue(temporal[val]);
                        val++;
                        System.out.println(encontrado);
                        
                        if (encontrado == false) {
                            //Se ha encontrado un elemento distinto
                            //  System.out.println("Elem distinto pos"+ i);
                        } else {
                            //   System.out.println("Todo iguales");
                        }

                    }
                }
            } else { 
                //En el caso de que el mapa esté vacío
                String[] temp;
                temp = dir.list();
                int cantidad = temp.length;
                //Copiamos los elementos del temporal al mapa
                for (int i = 0; i < cantidad; i++) {
                    String temp2 = temp[i];
                    ficheros.put(i + 1, temp2);
                }
                
                //Muestra los elementos del mapa
                Iterator it = ficheros.keySet().iterator();
                while (it.hasNext()) {
                    Integer clave = (Integer) it.next();
                    System.out.println("ID:" + clave + " nombre: " + ficheros.get(clave));
                }
                ///Una vez que ya los tenemos copiados decimos la cantida que hay
                total = cantidad;
                System.out.println("El directorio tiene " + total + " elem");

//                ///Buscamos uno de los elementos por nombre
//                boolean esta = ficheros.containsValue("17_08_15-09_38carrusel2.jpg");
//                System.out.println(esta);

//                //Buscamos por clave
//                boolean esta2 = ficheros.containsKey(1);
//                System.out.println(esta2);
                try {
                    //Metemos una interrupción de la ejecución de 10 segundos
                    //en la comprobacion de nuevos ficheros en el directorio
                    Thread.sleep(10000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(FicherosEnDirectorio.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }

    }
}

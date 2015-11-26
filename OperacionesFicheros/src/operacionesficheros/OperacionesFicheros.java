package operacionesficheros;

import java.io.File;

/**
 * Programa que realiza distintas operaciones con ficheros
 * @author David.Fernandez
 * 
 */
public class OperacionesFicheros {

    /**
     * Función que comprueba si un directorio existe,
     * en el caso de que no exista muestra el contenido
     * de este directorio
     * @param path
     **/
    public static void crearDir(String path) {
        File f = new File(path);
        //Si no es un directorio, entonces se crea
        if (!f.isDirectory()) {
            f.mkdir();
            System.out.println("El directorio no existe, se crea.");
        } //Si es un directorio se muestra el contenido
        else {
            System.out.println("Ya es un directorio y contiene los siguientes ficheros: ");
            String[] listaFicheros;
            listaFicheros = f.list();
            if (f.length() == 0) {
                System.out.println("El directorio no contiene ficheros.");
            } else {
                for (String listaFichero : listaFicheros) {
                    System.out.println("Fichero: \t" + listaFichero);
                }
            }
        }
    }
    
        
    /**
     * Función principal del programa
     * @param args
     */
    public static void main(String[] args) {
        String path = "C:\\PRUEBAS";
        crearDir(path);
    }
    
}

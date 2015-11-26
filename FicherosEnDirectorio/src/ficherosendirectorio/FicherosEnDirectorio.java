/**
 * APLICACIÓN DE MONITORIZACIÓN DE FICHEROS EN LOS DIRECTORIOS DE LA APLICACIÓN
 * @author david.fernandez 
 * @version 0.8
 **/
package ficherosendirectorio;

///Inclusión de las librerías oportunas
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.monitor.FileAlterationListener;
import org.apache.commons.io.monitor.FileAlterationListenerAdaptor;
import org.apache.commons.io.monitor.FileAlterationMonitor;
import org.apache.commons.io.monitor.FileAlterationObserver;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


/**
 *
 * @author David.Fernandez
 */
public class FicherosEnDirectorio {
    //Ruta del fichero de configuración del sistema, desde este se cargan las 
    // distintas rutas de los ficheros, cómo entradas - salidas etc. 
    // Para más información revisar el fichero configuracion.xml

    public static String ruta = "configuracion.xml";

    //Definición del directorio que se va a monitorizar
    public static String CARPETA = new String();
    public static LinkedList cola = new LinkedList();

    /**
     * Función que lee un fichero de configuración pasado por parámetro y fija atributos a
     * un objeto de configuración pasado por parámetro.
     * @param ruta , String con la ruta del fichero de configuración
     * @param conf, Objeto de tipo Configuración
     **/
    public static void LeerDatosDesdeXML(String ruta, Configuracion conf) throws ParserConfigurationException, SAXException, IOException {
        if (ruta == "") {
            System.out.println("Ruta del fichero de configuración incorrecta");
        } else {
            // System.out.println("Ruta ok");
            DocumentBuilderFactory factoria = DocumentBuilderFactory.newInstance();
            DocumentBuilder constructorDocumento = factoria.newDocumentBuilder();
            Document documento = constructorDocumento.parse(ruta);

            //Elemento Raiz
            Element raiz = documento.getDocumentElement();

            //Obtenemos la lista de nodos con la etiqueta PARAMETROS
            // NodeList listaParametros = raiz.getElementsByTagName("PARAMETRO");
            //Obtenemso el nombre de fichero
            NodeList nombreFich = raiz.getElementsByTagName("NOMBREFICHERO");
            conf.setNombreFichero(nombreFich.item(0).getFirstChild().getNodeValue());

            //Obtenemos el directorio de entrada
            NodeList directorioEntrada = raiz.getElementsByTagName("DIR_ENTRADA");
            conf.setDir_entrada(directorioEntrada.item(0).getFirstChild().getNodeValue());
            CARPETA = conf.getDir_entrada();
            //Obtenemos el directorio de salida
            NodeList directorioSalida = raiz.getElementsByTagName("DIR_SALIDA");
            conf.setDir_salida(directorioSalida.item(0).getFirstChild().getNodeValue());
            //Obtenemos el fichero de la cola. 
            NodeList cola_fich = raiz.getElementsByTagName("COLA");
            conf.setFichero_cola(cola_fich.item(0).getFirstChild().getNodeValue());
            
            //Obtenemos el comando de ejecución EXCEL
            NodeList comandexcel = raiz.getElementsByTagName("COMANDOEXCEL");
            conf.setComandoExcel(comandexcel.item(0).getFirstChild().getNodeValue());
            //Obtenemos el comando de ejecución STL
            NodeList comandstl = raiz.getElementsByTagName("COMANDOSTL");
            conf.setComandoExcel(comandstl.item(0).getFirstChild().getNodeValue());
        }
    }
    /**
     * Función que muestra los elementos que tiene una cola pasada por parámetro
     * @param cola, lista enlazada para la cola
     ***/
    public static void muestraCola(LinkedList cola) {
        System.out.println("FICHEROS EN LA COLA: ");
        System.out.println(cola);
    }
    /**
     * Función que escribe los elementos que tiene el objeto cola
     * en un fichero csv de configuración pasado en el objeto conf
     * @param cola, objeto de la cola
     * @param conf, objeto de la configuración
     */
    public static void vuelcaFicheroCola(LinkedList cola, Configuracion conf) {
        System.out.println("Volcado a fichero");
        String rutaCola = conf.getFichero_cola();
        File fich = new File(rutaCola);
///BLOQUE TRY
        try {
            FileWriter fw = new FileWriter(fich);
            //Bucle que se repite mientras que en la cola queden elementos
            for (Object elementoCola : cola) {
                //System.out.println(elementoCola);
                fw.write(elementoCola.toString());
                fw.write("\n");
            }
            //Cerramos el fichero para liberar el recurso
            fw.close();
            System.out.println("Se ha volcado al fichero correctamente");
////BLOQUE CATCH            
        } catch (IOException ex) {
            //Lanza excepción de tipo IOException
            System.out.println("No se ha podido volcar a fichero correctamente");
            Logger.getLogger(FicherosEnDirectorio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * Procesamiento que se realiza cuando se carga un fichero de tipo excel
     * @param file , con el descriptor de fichero del Excel
     * @param conf , objeto con la configuración de la aplicación
     */
    public static void procesaXLS(File file, Configuracion conf) {
        System.out.println("PROCESANDO EXCEL");
        try {
            //Antes de nada almacenamos el nombre del fichero en una cola, la 
            //cual vamos a usar para crear el Excel con los nombres de los 
            //ficheros para que luego tratarlo en catia.

            //Definimos el objeto con el nombre
            String nomObj = new String();
            nomObj = file.getCanonicalPath();
            //Añadimos a la cola el nombre del fichero modificado
            cola.addFirst(nomObj);
            //Mostramos por pantalla los elementos que tiene la cola
            //muestraCola(cola);
            vuelcaFicheroCola(cola, conf);
            //Ruta de catia                                                             comando    ruta de la macro         modulo a cargar
            // String comando = "\"E:\\Archivos de Programa\\Dassault Systemes\\B19\\win_b64\\code\\bin\\CNEXT.exe\" -macro \"E:\\macros\\MacroFinal.catvba " + "Module_initial";
            String comando = conf.getComandoExcel();
            Process procesoExcel = Runtime.getRuntime().exec(comando);
            
        } catch (IOException e) {
            
        }
    }
     /**
     * Procesamiento que se realiza cuando se carga un fichero de tipo STL
     * @param file , con el descriptor de fichero del Excel
     * @param conf , objeto con la configuración de la aplicación
     */   

    public static void procesaSTL(File file, Configuracion conf) {
        System.out.println("PROCESANDO STL");
        try {
            //Antes de nada almacenamos el nombre del fichero en una cola, la 
            //cual vamos a usar para crear el Excel con los nombres de los 
            //ficheros para que luego tratarlo en catia.
            
            //Definimos el objeto con el nombre
            String nomObj = new String();
            nomObj = file.getCanonicalPath();
            //Añadimos a la cola el nombre del fichero modificado
            cola.addFirst(nomObj);
            //Mostramos por pantalla los elementos que tiene la cola
            //muestraCola(cola);
            vuelcaFicheroCola(cola, conf);
            //Ruta de catia                                                             comando    ruta de la macro         modulo a cargar
            // String comando = "\"E:\\Archivos de Programa\\Dassault Systemes\\B19\\win_b64\\code\\bin\\CNEXT.exe\" -macro \"E:\\macros\\MacroFinal.catvba " + "Module_initial";
            String comandostl = conf.getComandoSTL();
            Process procesostl = Runtime.getRuntime().exec(comandostl);
            
        } catch (IOException e) {
            
        }
    }
    
    public static void procesaJson(File file, Configuracion conf){
        System.out.println("Procesamos el JSON");
        JSONParser parser = new JSONParser();
        try {
            try {
                Object obj = parser.parse(new FileReader(file.getCanonicalPath()));
             //   Object obj = parser.parse(file);
                JSONObject jobject = (JSONObject) obj;
                Material cs = new Material(jobject);
                System.out.println(cs.toString());
//                String nombre_usuario = (String) jobject.get("Nombre_usuario");
//                String nombre_proyecto = (String) jobject.get("Nombre_proyecto");
//                String material_plastico = (String) jobject.get("Material_plastico");
//                String fabricante = (String) jobject.get("Fabricante");
//                String coste = (String) jobject.get("Material");
//
//                System.out.println("DATOS:");
//                System.out.println("\n N_usuario:" + nombre_usuario + "\n N_proyecto:" + nombre_proyecto
//                        + "\n Mat_plastic:" + material_plastico + "\n Fabric:" + fabricante + "\n Material:" + coste);
            } catch (IOException ex) {
                Logger.getLogger(FicherosEnDirectorio.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (ParseException ex) {
            Logger.getLogger(FicherosEnDirectorio.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public static void main(String[] args) throws Exception {
        //Objeto configuración
        final Configuracion conf = new Configuracion();
        //Función para leer la configuración desde el fichero XML
        LeerDatosDesdeXML(ruta, conf);
//        ///Muestra la configuración que hay cargada en el XML
//        System.out.println("FICHEROS DE CONFIGURACION");
//        System.out.println(conf.toString());

        //final Writer escritor = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("salida-rutas.csv"),"utf-8"));
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
       
        //Definimos el intervalo de actualizacion de 5 segundos para la comprobación de nuevos ficheros////////////////
        final long intervalo = 5 * 1000;
        
        //Creamos el descriptor de fichero-directorio
        final File carpeta = new File(CARPETA);
        //Comprobamos que la carpeta existe de modo que si esta no existe lanza un RuntimeException
        if (!carpeta.exists()) {
            //La carpeta no existe
            throw new RuntimeException("No existe la carpeta: " + CARPETA);
        }
       ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        /**
         * Objetos asociados al uso de un monitor. 
         * Debe tener un Observador, un monitor y un Listener
         */
        final FileAlterationObserver observador = new FileAlterationObserver(carpeta); //Observador
        final FileAlterationMonitor monitor = new FileAlterationMonitor(intervalo); //Monitor
        final FileAlterationListener escuchador; //Listener, atiende las modificaciones de los ficheros en el directorio
        escuchador = new FileAlterationListenerAdaptor() {
            //Monitorizamos, con un disparador cuando se crea un fichero en la carpeta observada
            //Sobrecargamos los métodos de las funciones de la librería para que 
            //actuen conforme se quiere
            
            
            /**
             * Acción que salta cuando se crea un fichero en el directorio de entrada
             * @param   file, descriptor de fichero
             **/
            
            @Override
            public void onFileCreate(File file) {
               
                try {
                    ///Se Muestra por consola las modificaciones de los ficheros en el directorio monitorizado
                    System.out.println("#############################################################");
                    System.out.println("Fichero creado: " + file.getCanonicalPath());
                    System.out.println("-------------------------------------------------------------");
                    String extension = FilenameUtils.getExtension(file.getCanonicalPath());
                    System.out.println("Extensión: " + extension);
                    System.out.println("-------------------------------------------------------------");
                    //Dependiendo del tipo de extensión que tenga el fichero se actúa de una forma u otra. 

                    //Si el fichero es de tipo .xls se lanza una macro de catia para procesarla
                    if ("xls".equals(extension)|| "xlsx".equals(extension) ) {
                        procesaXLS(file, conf);
                    }

                    if ("json".equals(extension)){
                        procesaJson(file,conf);
                    }
                    if ("CATPart".equals(extension)) {
                        System.out.println("PENDIENTE DE IMPL procesar CATPART");
                    }
                    if ("CATProduct".equals(extension)) {
                        System.out.println("PENDIENTE DE IMPL procesar catProduct");
                    }
                    if ("stl".equals(extension)) {
                       // System.out.println("Lanzamos script para procesar stl");
                        procesaSTL(file, conf);
                    }
                    if ("stp".equals(extension)) {
                        System.out.println("PENDIENTE DE IMPL procesar stp");
                    }

                } catch (IOException e) {
                    e.printStackTrace(System.err);
                }
            
            }
           /**
            * Acción que se lanza a la hora de eliminar fichero
            * @param   file, descriptor de fichero
            **/
            @Override
            public void onFileDelete(File file) {
                try {
                    System.out.println("Fichero eliminado " + file.getCanonicalPath());
                    cola.remove(file.getCanonicalPath());
                    muestraCola(cola);
                    vuelcaFicheroCola(cola, conf);
                } catch (IOException ex) {
                    Logger.getLogger(FicherosEnDirectorio.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        };
        
        observador.addListener(escuchador);
        monitor.addObserver(observador);
        monitor.start();

    }

}

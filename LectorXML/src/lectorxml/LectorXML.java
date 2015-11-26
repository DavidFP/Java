/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lectorxml;

import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;
/**
 *
 * @author david.fernandez
 */

public class LectorXML {
public static String nombre_fichero = new String();
public static String dir_entrada = new String();
public static String dir_salida = new String();


    public static void LeerDatos(String ruta, Usuario usu) throws ParserConfigurationException, SAXException, IOException {

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
            NodeList listaParametros = raiz.getElementsByTagName("PARAMETRO");
            for (int i = 0; i < listaParametros.getLength(); i++) {
                Node parametro = listaParametros.item(i);
                System.out.println("####################################");
                System.out.println("PARAMETROS: " + i);
                System.out.println("####################################");
                //DENTRO DE CADA PARÁMETRO OBTENEMOS EL PARAMETRO CONCRETO
                NodeList datosParametro = parametro.getChildNodes();
                for (int j = 0; j < datosParametro.getLength(); j++) {
                    Node dato = datosParametro.item(j);
                    //Comprobamos si es un Element
                    if (dato.getNodeType() == Node.ELEMENT_NODE) {
                        //Mostramos el nombre del parametro
                        System.out.println(dato.getNodeName() + ":");
                        Node datoContenido = dato.getFirstChild();
                        //Mostramos el contenido
                        if (datoContenido != null) {
                            System.out.println(datoContenido.getNodeValue());
                        }
                    }
                }

            }
        }
    }

    public static void LeerDatosDesdeXML(String ruta, Usuario usu) throws ParserConfigurationException, SAXException, IOException {
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
            NodeList listaParametros = raiz.getElementsByTagName("PARAMETRO");
            NodeList nombreFich = raiz.getElementsByTagName("NOMBREFICHERO");
            System.out.println(nombreFich.item(0).getFirstChild().getNodeValue());
            nombre_fichero = nombreFich.item(0).getFirstChild().getNodeValue();
        }

    }
    
    public static void crearDir(String path){
        File f  = new File(path);
        if(!f.isDirectory())
            f.mkdir();
        else{
            System.out.println("Ya es un directorio y contiene los siguientes ficheros: ");
            String[] listaFicheros;
            listaFicheros = f.list();
            for (String listaFichero : listaFicheros) {
                System.out.println("Fichero: \t" + listaFichero);
            }
        }
    }   
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
        System.out.println("Lector de configuracion XML");
        Usuario usu = new Usuario();
        String ruta="configuracion.xml";
        LeerDatosDesdeXML(ruta, usu);
        
    }
    
}

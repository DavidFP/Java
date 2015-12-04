package testexcel;

//Inclusión de las librerías
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;

/**
 * Aplicación para la lectura de ficheros excel desde java
 *
 * @author david.fernandez
 */
public class LecturaExcel {

    /**
     * Función que lee el número de hojas de un excel pasado cómo parámetros y
     * muestra por pantalla el número de hojas y el nombre de estas
     *
     * @param archivo , con la ruta del archivo Excel
     */
    public static void leerExcelJXL(File archivo) {
        try {
            Workbook libro = Workbook.getWorkbook(archivo);
            //Mostramos información de libro
            int numHojas = libro.getNumberOfSheets();
            String[] hojas = libro.getSheetNames();
//            System.out.println("Número de hojas: " + numHojas);
//            for (String hoja : hojas) {
//                System.out.println("Hoja: " + hoja);
//            }
            //Ahora vamos a crear un nuevo fichero xls y le vamos a copiar 
            //algunos elementos del original. 

            ///Creamos el libro con posibilidad de escritura
            WritableWorkbook salida = Workbook.createWorkbook(new File("E:\\salida.xls"));
            WritableSheet ws = salida.createSheet("salida", 0);
            ws.setName("output2");

        } catch (IOException | BiffException ex) {
            Logger.getLogger(LecturaExcel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Función que le un excel y devuelve un hashset con el listado de artículos
     *
     * @param ruta , Path del fichero a leer
     * @param primeraCelda , celda desde la que se empieza a leer
     */
    public static HashSet XLStoHASHSET(String ruta, int primeraCelda) {
        //Estructura de datos donde se almacenan las filas
        HashSet<Articulo> hs = new HashSet<Articulo>();

        try {
            //creamos el file input stream del fichero de entrada de los datos
            InputStream fis = new FileInputStream(new File(ruta));
            // Crea un objeto excel desde el sistema de ficheros
            HSSFWorkbook workbook = new HSSFWorkbook(fis);
            
// Obtiene la primera hoja del libro 
            int numHojas = workbook.getNumberOfSheets();
            int i = 0;
//Exploramos todas las hojas del libro
            while (i < numHojas) {
                HSSFSheet sheet = workbook.getSheetAt(i);

                //Muestra la hoja actual en la que estamos
                System.out.println("HOJA " + (i + 1));
                //Obtiene el número total de celdas
                int totalRow = sheet.getPhysicalNumberOfRows();
                System.out.println("Num. Filas: " + totalRow);
                /*RECORRER DE FIN A INICIO LA HOJA*/
                int ultima = sheet.getLastRowNum();
                for (int u = ultima; u > primeraCelda; u--) {
                    HSSFRow row = sheet.getRow(u);
                    // System.out.println(row.getRowNum());
                    String quantity = (String) row.getCell(0).toString();
                    float q = Float.parseFloat(quantity);
                    String partNum = row.getCell(1).toString();
                    String nomenclature = row.getCell(2).toString();
                    Articulo art = new Articulo(q, partNum, nomenclature);
                    hs.add(art);
                }
                i++;
            }
        } catch (IOException e) {
        }
        return hs;
    }

    public static HashSet XLStoHASHSETCatalogo(String ruta, int primeraCelda) {
        //Estructura de datos donde se almacenan las filas
        HashSet<MeusburguerProduct> hs = new HashSet<>();
        
        try {
            //creamos el file input stream del fichero de entrada de los datos
            InputStream fis = new FileInputStream(new File(ruta));
            /**
             * Crea un objeto excel desde el sistema de ficheros
             */
            HSSFWorkbook workbook = new HSSFWorkbook(fis);

            /**
             * Obtiene la primera hoja del libro
             */
            int numHojas = workbook.getNumberOfSheets();
            int i = 0;
            //Exploramos todas las hojas del libro
            while (i < numHojas) {
                HSSFSheet sheet = workbook.getSheetAt(i);

                //Muestra la hoja actual en la que estamos
                System.out.println("HOJA " + (i + 1));

                //Obtiene el número total de celdas
                int totalRow = sheet.getPhysicalNumberOfRows();
                System.out.println("Num. Filas: " + totalRow);

                /**
                 * ALTERNATIVA DE RECORRER DE FIN A INICIO LA HOJA
                 */
                int ultima = sheet.getLastRowNum();
                //Se establece el límite a 88 ya que es la celda donde comienza
                //siempre el resumen del BOM de materiales
                for (int u = 1; u < ultima; u++) {
                    HSSFRow row = sheet.getRow(u);
                    String terminoBusqueda = (String) row.getCell(0).toString();
                    String SKU = (String) row.getCell(1).toString();
                    String referencia = (String) row.getCell(2).toString();
                    String descripcion = (String) row.getCell(3).toString();
                    String pe = (String) row.getCell(4).toString();
                    Float peso = Float.parseFloat(pe);
                    
                    String pre = (String) row.getCell(5).toString();
                    Float precio = Float.parseFloat(pre);
                    String grupo = (String) row.getCell(6).toString();
                    System.out.println("R: - " +terminoBusqueda+ " => " +  SKU+ " => "  + referencia + " => " + descripcion + " => " + peso + " => " + precio + " => " + grupo);
                    MeusburguerProduct mp = new MeusburguerProduct(terminoBusqueda, SKU, referencia, descripcion, peso, precio, grupo);
                    hs.add(mp);
                    System.out.println("ARTICULO: " + u);
                }
            i++;
            }
        } catch (IOException e) {
        }
        return hs;
    }

    public static void procesaEspacios(HashSet<Articulo> listado) {
        for (Articulo obj : listado) {
            obj.setPartNumber(obj.getPartNumber().replace(" ", ""));
            obj.setNomenclature(obj.getNomenclature().replace(" ", ""));
        }
    }

    /**
     * Función para buscar un artículo dentro de un listado de artículos por
     * Part Number
     *
     * @param art, un objeto de tipo artículo
     * @param listadoArticulos, el listado donde se van a buscar
     * @return List, de elementos que ha encontrado con el cirterio de busqueda
     */
    public static List<Articulo> buscarArticuloPartNumber(String art, HashSet<Articulo> listadoArticulos) {
        List<Articulo> encontrado = new ArrayList<Articulo>();
        //pasamos a minuscula el criterio de busqueda para encontrar el articulo
        art = art.toLowerCase();
        for (Articulo a : listadoArticulos) {
            if (a.getPartNumber().toLowerCase().contains(art)) {
                encontrado.add(a);
                //System.out.println("->" + a.toString());
            }
        }
        return encontrado;
    }

    /**
     * Función para buscar un artículo dentro de un listado de artículos por
     * Nomenclature
     *
     * @param art, un objeto de tipo artículo
     * @param listadoArticulos, el listado donde se van a buscar
     * @return valor entero con el número de ocurrencias de la cadena buscada
     */
    public static int buscarArticuloNomenclature(String art, HashSet<Articulo> listadoArticulos) {
        int encontrado = 0;
        for (Articulo a : listadoArticulos) {
            if (a.getNomenclature().contains(art)) {
                encontrado++;
                System.out.println("->" + a.toString());
            }
        }
        if (encontrado != 0) {
            System.out.println("Se han encontrado: " + encontrado);
        }
        return encontrado;
    }

    /**
     * Función que lee los elementos de un fichero xls
     *
     * @param rutaxls , path del fichero xls
     */
    public static List leerExcelXls(String rutaxls) {
        List sheetData = new ArrayList();
        InputStream is = null;
        try {
            InputStream fis = new FileInputStream(new File(rutaxls));
            /**
             * Crea un objeto excel desde el sistema de ficheros
             */
            HSSFWorkbook workbook = new HSSFWorkbook(fis);

            /**
             * Obtiene la primera hoja del libro
             */
            int numHojas = workbook.getNumberOfSheets();
            System.out.println("El Libro dispone de: " + numHojas + " hojas");
            for (int i = 0; i < numHojas; i++) {
                System.out.println("Hoja: " + workbook.getSheetName(i));
            }
            int i = 0;
            while (i < numHojas) {
                HSSFSheet sheet = workbook.getSheetAt(i);

                int totalRow = sheet.getPhysicalNumberOfRows();
                System.out.println("HOJA " + (i + 1));
                System.out.println("Num. Filas: " + totalRow);
                int ultimaFila = sheet.getLastRowNum();
                System.out.println("Ultima fila: " + ultimaFila);

                /**
                 * Cuando hay un objeto en la hoja, lo manejamos con un iterador
                 * para la hoja con rows y para cada row con sus Cells
                 * asociadas. Almacenamos los datos en un ArrayList que podemos
                 * manejar
                 */
                Iterator rows = sheet.rowIterator();
                while (rows.hasNext()) {
                    HSSFRow row = (HSSFRow) rows.next();
                    Iterator cells = row.cellIterator();
                    List data = new ArrayList();
                    while (cells.hasNext()) {
                        HSSFCell cell = (HSSFCell) cells.next();
                        //System.out.println("Añadiendo Celda: " + cell.toString());
                        data.add(cell);
                    }
                    sheetData.add(data);
                }
                i++;
            }
        } catch (IOException e) {
        }
        //showExelData(sheetData);
        return sheetData;
    }

    /**
     * Función que muestra una colección de valores almacenados de una celda.
     *
     * @param datosHoja , lista de datos de la hoja
     */
    private static void muestraDatosExcel(List datosHoja) {
        /**
         * Itera y muestra por consola los datos
         */
        for (Object elemento : datosHoja) {
            List filas = (List) elemento;
            for (int j = 0; j < filas.size(); j++) {
                Cell celda = (Cell) filas.get(j);
                //Tratamiento en caso de ser de tipo numérico
                if (celda.getCellType() == Cell.CELL_TYPE_NUMERIC) {
                    System.out.print(celda.getNumericCellValue() + " ");
                } else if (celda.getCellType() == Cell.CELL_TYPE_STRING) { //Tratamiento en caso de ser String
                    System.out.print(celda.getRichStringCellValue() + " ");
                } else if (celda.getCellType() == Cell.CELL_TYPE_BOOLEAN) { //Tratamiento en caso de ser Booleano
                    System.out.print(celda.getBooleanCellValue() + " ");
                }
                if (j < filas.size() - 1) {
                    System.out.print(" , ");
                }
            }
            System.out.println("");
        }
    }

}

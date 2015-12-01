package testexcel;

/**
 * Inclusión de las librerías
 */
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
     *
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

    public static HashSet XLStoHASHSET(String ruta) {
        //Estructura de datos donde se almacenan las filas
        HashSet<Articulo> hs = new HashSet<Articulo>();
//        Articulo articulo1 = new Articulo(1, "Clamping Plate", "E100/E2");
//        Articulo articulo2 = new Articulo(2, "sdf", "E220/al");
//        hs.add(articulo1);
//        hs.add(articulo2);

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
                for (int u = ultima; u > 88; u--) {
                    HSSFRow row = sheet.getRow(u);
                   // System.out.println(row.getRowNum());
                    String quantity = (String) row.getCell(0).toString();
                    float q = Float.parseFloat(quantity);
                    String partNum = row.getCell(1).toString();
                    String nomenclature = row.getCell(2).toString();
                    Articulo art = new Articulo(q, partNum, nomenclature);
                    hs.add(art);

                        //System.out.println("Elemento añadido " + u);
                    //System.out.println("->"+ art.toString() + "<-");
//                        System.out.println("COL A: " + row.getCell(0));
//                        System.out.println("COL B: " + row.getCell(1));
//                        System.out.println("COL C: " + row.getCell(2));
                }

                /**
                 * Cuando hay un objeto en la hoja, lo manejamos con un iterador
                 * para la hoja con rows y para cada row con sus Cells
                 * asociadas. Almacenamos los datos en un ArrayList que podemos
                 * manejar
                 */
//                    Iterator rows = sheet.rowIterator();
//                    while (rows.hasNext()) {
//                        HSSFRow row = (HSSFRow) rows.next();
//                        Iterator cells = row.cellIterator();
//                        List data = new ArrayList();
//                        while (cells.hasNext()) {
//                            HSSFCell cell = (HSSFCell) cells.next();
//                            //System.out.println("Añadiendo Celda: " + cell.toString());
//                            data.add(cell);
//                        }
//                     //   sheetData.add(data);
//                    }
                i++;
            }
        } catch (IOException e) {
        }

        return hs;
    }

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

                for (int r = ultimaFila; r > 88; r--) {

                }

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
    private static void showExelData(List datosHoja) {
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

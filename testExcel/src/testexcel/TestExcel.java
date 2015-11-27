package testexcel;

/**
 * Inclusión de las librerías
 */
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
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
public class TestExcel {

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
            System.out.println("Número de hojas: " + numHojas);
            for (String hoja : hojas) {
                System.out.println("Hoja: " + hoja);
            }
            //Ahora vamos a crear un nuevo fichero xls y le vamos a copiar 
            //algunos elementos del original. 

            ///Creamos el libro con posibilidad de escritura
            WritableWorkbook salida = Workbook.createWorkbook(new File("E:\\salida.xls"));
            WritableSheet ws = salida.createSheet("salida", 0);
            ws.setName("output2");

        } catch (IOException | BiffException ex) {
            Logger.getLogger(TestExcel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void leerExcelPOI(String rutaPoi) throws IOException {
        List sheetData = new ArrayList();
        try (FileInputStream fis = new FileInputStream(rutaPoi)) {
            /**
             * Crea un objeto excel desde el sistema de ficheros
             */
            HSSFWorkbook workbook = new HSSFWorkbook(fis);
            /**
             * Obtiene la primera hoja del libro
             */
            HSSFSheet sheet = workbook.getSheetAt(0);
            /**
             * Cuando hay un objeto en la hoja, lo manejamos con un
             * iterador para la hoja con rows y para cada row con sus
             * Cells asociadas. 
             * Almacenamos los datos en un ArrayList que podemos manejar
             */
            Iterator rows = sheet.rowIterator();
            while (rows.hasNext()) {
                HSSFRow row = (HSSFRow) rows.next();

                Iterator cells = row.cellIterator();
                List data = new ArrayList();
                while (cells.hasNext()) {
                    HSSFCell cell = (HSSFCell) cells.next();
                    System.out.println("Añadiendo Celda: " + cell.toString());
                    data.add(cell);
                }
                sheetData.add(data);
            }
        } catch (IOException e) {
        }
        showExelData(sheetData);
    }
    
    /**
     * Función que muestra una colección de valores almacenados de
     * una celda.
     * @param   sheetData , lista de datos de la hoja
     */
    private static void showExelData(List sheetData) {
        /**
         * Itera y muestra por consola los datos
         */
        for (Object sheetData1 : sheetData) {
            List list = (List) sheetData1;
            for (int j = 0; j < list.size(); j++) {
                Cell cell = (Cell) list.get(j);
                //Tratamiento en caso de ser de tipo numérico
                if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) 
                {
                    System.out.print(cell.getNumericCellValue());
                } else if (cell.getCellType() == Cell.CELL_TYPE_STRING) 
                { //Tratamiento en caso de ser String
                    System.out.print(cell.getRichStringCellValue());
                } else if (cell.getCellType() == Cell.CELL_TYPE_BOOLEAN)
                { //Tratamiento en caso de ser Booleano
                    System.out.print(cell.getBooleanCellValue());
                }
                if (j < list.size() - 1) {
                    System.out.print(", ");
                }
            }
            System.out.println("");
        }
    }

    /**
     * Función principal del programa
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here

        String ruta = "E:\\jtest.xls";
        File fich = new File(ruta);
        leerExcelJXL(fich);

        String rutaPoi = "test.xlsx";
        leerExcelPOI(rutaPoi);

    }

}

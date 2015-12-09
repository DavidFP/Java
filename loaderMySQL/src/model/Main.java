package model;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

/**
 * Clase principal del programa
 * @author David.Fernandez
 */
public class Main {


    /**
     * @param args the command line arguments
     * @throws java.sql.SQLException
     */
    public static void main(String[] args) throws SQLException {
        long t_inicio, t_fin;
        Conexion c = new Conexion();
        c.establecerConexion();
        c.getConexion();
        HashSet<MeusburguerProduct> hs = new HashSet<>();
        
        //Inicio de la medición de tiempo
        t_inicio = System.currentTimeMillis();
        hs = Utils.cargarElementosHashSet(c.getConexion());
        t_fin = System.currentTimeMillis();
        //Fin de la medición del tiempo 
        
        System.out.println("Tiempo de carga: " + (t_fin-t_inicio) + "milisegundos");
        
        List<MeusburguerProduct> result = Utils.buscarArticulo(c.getConexion(), "E116");
        if(result.isEmpty()){
            System.out.println("NO SE HA ENCONTRADO NADA");
        }else{
            System.out.println("ENCONTRADOS: ");
            for (MeusburguerProduct meusres : result) {
                System.out.println("->" + meusres.toString());
            }
        }
        
//        Iterator<MeusburguerProduct>  it  = hs.iterator();
//        while(it.hasNext()){
//            System.out.println(it.next().toString());
//        }
    }

}

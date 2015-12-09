package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase para las funciones auxiliares de la aplicación. 
 * @author david.fernandez
 */
public class Utils {

    public Utils() {
    }
    /**
     * Función que realiza una búsqueda de un artículo por ID
     * devuelve una lista con los resultados que ha obtenido de la 
     * busqueda de un determinado artículo.
     * @param c, conexión a la BD
     * @param id, string con el criterio de búsqueda
     **/
    
    /**
     * Función que realiza una búsqueda de un artículo por ID
 devuelve una lista con los resultados que ha obtenido de la 
 busqueda de un determinado artículo.
     * @param c , conexión a la BD
     * @param id , string con el criterio de búsqueda
     * @return 
     * @throws java.sql.SQLException
     */
    public static List buscarArticulo(Connection c, String id) throws SQLException{
        //Definición de la estructura de salida
        List<MeusburguerProduct> resultado = new ArrayList<>();
        Statement st = null;
        String query = "SELECT * FROM catalogo WHERE ID='" + id +"'";
        
        st= c.createStatement();
        ResultSet rs  = st.executeQuery(query);
        while(rs.next()){
            String SKU = rs.getString("SKU");
            String Ref = rs.getString("REFERENCIA");
            String descripcion = rs.getString("DESCRIPCION");

            String p = rs.getString("PESO");
            p = p.replace(",", ".");
            float pp = (Float) Float.parseFloat(p);
            String pr = rs.getString("PRECIO");
            pr = pr.replace(",", ".");
            float prpr = (Float) Float.parseFloat(pr);
            String grupo = rs.getString("GRUPO");
            MeusburguerProduct mp = new MeusburguerProduct(id,SKU,Ref,descripcion,pp,prpr,grupo);
            resultado.add(mp);
        }
        return resultado;
    }
    
    public static HashSet cargarElementosHashSet(Connection c) throws SQLException{
        HashSet<MeusburguerProduct> listado = new HashSet<>();
        Statement st = null;
        String query = "SELECT * FROM catalogo";
        try {
            st = c.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                String ID = rs.getString("ID");
                String SKU = rs.getString("SKU");
                String Ref = rs.getString("REFERENCIA");
                String descripcion = rs.getString("DESCRIPCION");
                
                String p = rs.getString("PESO");
                p = p.replace(",", ".");
                float pp = (Float)Float.parseFloat(p);
                String pr = rs.getString("PRECIO");
                pr = pr.replace(",",".");
                float prpr= (Float)Float.parseFloat(pr);
                String grupo = rs.getString("GRUPO");
                MeusburguerProduct mp = new MeusburguerProduct(ID, SKU, descripcion, descripcion, pp, prpr, grupo);
                listado.add(mp);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("DATOS CARGADOS ~OK~");
        return listado;
    }
}

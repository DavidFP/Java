package model;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase que implementa las conexiones a la base de datos usando el conector de
 * MySQL
 *
 * @author David.Fernandez
 */
public class Conexion {

    Connection conex = null;
    static String bd  = "celermold";
    static String user = "root";
    static String password = "";
    static String server = "jdbc:mysql://localhost:3306/" + bd;
    
    //Contructor por defecto, crea una instancia del objeto Conexión
    public Conexion() {
    }
 
    public void establecerConexion() throws SQLException {
        if(this.conex==null){
            try {
                Class.forName("com.mysql.jdbc.Driver");
                conex = DriverManager.getConnection(server,user,password);
                System.out.println("Conectado ok :-)");
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex){
                throw new SQLException(ex);
            }
        }
    }

    public Connection getConexion() {
        return conex;
    }
    
    
    
    public void eliminar() {

        if (conex != null) {

            try {
                conex.close();
            } catch (Exception e) {
            }
        }
    }
}


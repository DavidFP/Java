/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ficherosendirectorio;

/**
 *
 * @author david.fernandez
 */
public class Configuracion {
    String nombreFichero;
    String dir_entrada;
    String dir_salida;
    String fichero_cola;
    String comandoExcel;
    String comandoSTL;

    public Configuracion() {
    }

    public Configuracion(String nombreFichero, String dir_entrada, String dir_salida, String fichero_cola) {
        this.nombreFichero = nombreFichero;
        this.dir_entrada = dir_entrada;
        this.dir_salida = dir_salida;
        this.fichero_cola = fichero_cola;
    }

    public String getNombreFichero() {
        return nombreFichero;
    }

    public void setNombreFichero(String nombreFichero) {
        this.nombreFichero = nombreFichero;
    }

    public String getDir_entrada() {
        return dir_entrada;
    }

    public void setDir_entrada(String dir_entrada) {
        this.dir_entrada = dir_entrada;
    }

    public String getDir_salida() {
        return dir_salida;
    }

    public void setDir_salida(String dir_salida) {
        this.dir_salida = dir_salida;
    }

    public String getFichero_cola() {
        return fichero_cola;
    }

    public void setFichero_cola(String fichero_cola) {
        this.fichero_cola = fichero_cola;
    }

    public String getComandoExcel() {
        return comandoExcel;
    }

    public void setComandoExcel(String comando) {
        this.comandoExcel = comando;
    }

    public String getComandoSTL() {
        return comandoSTL;
    }

    public void setComandoSTL(String comandoSTL) {
        this.comandoSTL = comandoSTL;
    }

    
    
    
    
}

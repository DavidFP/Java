/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lectorxml;

/**
 *
 * @author david.fernandez
 */
public class Usuario {

    public String nombre;
    public String dir_salida;
    public String dir_entrada;
    //Default constructor
    public Usuario() {
        this.nombre = "";
        this.dir_entrada="";
        this.dir_salida="";
    }

    public Usuario(String nombre, String dir_salida, String dir_entrada) {
        this.nombre = nombre;
        this.dir_salida = dir_salida;
        this.dir_entrada = dir_entrada;
    }
    
    /**
     * Get the value of dir_salida
     *
     * @return the value of dir_salida
     */
    public String getDir_salida() {
        return dir_salida;
    }

    /**
     * Set the value of dir_salida
     *
     * @param dir_salida new value of dir_salida
     */
    public void setDir_salida(String dir_salida) {
        this.dir_salida = dir_salida;
    }

    /**
     * Get the value of dir_entrada
     *
     * @return the value of dir_entrada
     */
    public String getDir_entrada() {
        return dir_entrada;
    }

    /**
     * Set the value of dir_entrada
     *
     * @param dir_entrada new value of dir_entrada
     */
    public void setDir_entrada(String dir_entrada) {
        this.dir_entrada = dir_entrada;
    }

    /**
     * Get the value of nombre
     *
     * @return the value of nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Set the value of nombre
     *
     * @param nombre new value of nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}

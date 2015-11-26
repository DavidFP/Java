/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ficherosendirectorio;

import org.json.simple.JSONObject;

/**
 * Clase para obtener los parámetros de los ficheros JSON para poder 
 * realizar los cálculos de la estimación de costes. 
 * @author david.fernandez
 */

public class Material {
    String NUsuario; //Campo nombre de usuario
    String NProyecto; //Campo nombre del proyecto
    String MPlastico; //Campo nombre del material plástico
    String Fabricante; //Campo nombre del fabricante
    String Cost; //Campo coste del material
    
    /**
     * Contructor por defecto, crea una instancia del objeto en memoria
     */
    public Material() {
    }
    /**
     * Constructor parametrizado para la creación del nuevo objeto material
     * @param NUsuario
     * @param NProyecto 
     * @param MPlastico 
     * @param Fabricante 
     * @param Cost 
     */
    public Material(String NUsuario, String NProyecto, String MPlastico, String Fabricante, String Cost) {
        this.NUsuario = NUsuario;
        this.NProyecto = NProyecto;
        this.MPlastico = MPlastico;
        this.Fabricante = Fabricante;
        this.Cost = Cost;
    }
    /**
     * Constructor parametrizado a través de un JSONObject de la librería
     * json-simple-1.1.1.jar 
     * @param jsonObj 
     */
    public Material(JSONObject jsonObj) {
        this.NUsuario = (String) jsonObj.get("Nombre_usuario");
        this.NProyecto = (String) jsonObj.get("Nombre_proyecto");
        this.MPlastico = (String) jsonObj.get("Material_plastico");
        this.Fabricante = (String) jsonObj.get("Fabricante");
        this.Cost = (String) jsonObj.get("Coste");
    }

    
    /**
     * MÉTODOS GETTER & SETTER DE LA CLASE
     */
    
    public String getNUsuario() {
        return NUsuario;
    }

    public void setNUsuario(String NUsuario) {
        this.NUsuario = NUsuario;
    }

    public String getNProyecto() {
        return NProyecto;
    }

    public void setNProyecto(String NProyecto) {
        this.NProyecto = NProyecto;
    }

    public String getMPlastico() {
        return MPlastico;
    }

    public void setMPlastico(String MPlastico) {
        this.MPlastico = MPlastico;
    }

    public String getFabricante() {
        return Fabricante;
    }

    public void setFabricante(String Fabricante) {
        this.Fabricante = Fabricante;
    }

    public String getCost() {
        return Cost;
    }

    public void setCost(String cost) {
        this.Cost = cost;
    }
    
    /**
     * Sobrecarga del método toString para mostrar los datos por pantalla
     */
    @Override
    public String toString() {
        return "Coste{" + "NUsuario=" + NUsuario + ", NProyecto=" + NProyecto + ", MPlastico=" + MPlastico + ", Fabricante=" + Fabricante + ", cost=" + Cost + '}';
    }
    
}

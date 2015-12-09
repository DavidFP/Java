package model;

import java.util.UUID;

/**
 * Clase para el tratamiento de articulos obtenidos de la BD
 * Los atributos de la clase se corresponden con las columnas necesarias de la BD
 * @author David.Fernandez
 */
public class MeusburguerProduct {
    UUID id;
    String terminoBusqueda;
    String SKU;
    String referencia;
    String descripcion;
    Float peso;
    Float precio;
    String grupo;

    public MeusburguerProduct() {
        this.id = UUID.randomUUID();
        this.terminoBusqueda="";
        this.SKU = "";
        this.referencia = "";
        this.descripcion = "";
        this.peso = (float) 0.0;
        this.grupo = "";
    }

    public MeusburguerProduct(String terminoBusqueda, String SKU, String referencia, String descripcion, Float peso, Float precio, String grupo) {
        this.id = UUID.randomUUID();
        this.terminoBusqueda = terminoBusqueda;
        this.SKU = SKU;
        this.referencia = referencia;
        this.descripcion = descripcion;
        this.peso = peso;
        this.precio = precio;
        this.grupo = grupo;
    }

    public String getTerminoBusqueda() {
        return terminoBusqueda;
    }

    public void setTerminoBusqueda(String terminoBusqueda) {
        this.terminoBusqueda = terminoBusqueda;
    }

    public String getSKU() {
        return SKU;
    }

    public void setSKU(String SKU) {
        this.SKU = SKU;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Float getPeso() {
        return peso;
    }

    public void setPeso(Float peso) {
        this.peso = peso;
    }

    public Float getPrecio() {
        return precio;
    }

    public void setPrecio(Float precio) {
        this.precio = precio;
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
    
    @Override
    public String toString() {
        return "MeusburguerProduct{" + "terminoBusqueda=" + terminoBusqueda + ", SKU=" + SKU + ", referencia=" + referencia + ", descripcion=" + descripcion + ", peso=" + peso + ", precio=" + precio + ", grupo=" + grupo + '}';
    }
    
}

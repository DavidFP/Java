/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testexcel;

import java.util.UUID;

/**
 *
 * @author david.fernandez
 */
public class Articulo {

    UUID id;
    float quantity;
    String partNumber;
    String nomenclature;
    float price;

    public Articulo() {
        this.id = UUID.randomUUID();
        this.quantity = 0;
        this.partNumber = "";
        this.nomenclature = "";
        this.price = 0;
    }

    public Articulo(float quantity, String partNumber, String nomenclature) {
        this.id = UUID.randomUUID();
        this.quantity = quantity;
        this.partNumber = partNumber;
        this.nomenclature = nomenclature;
        this.price = 0;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public float getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getPartNumber() {
        return partNumber;
    }

    public void setPartNumber(String partNumber) {
        this.partNumber = partNumber;
    }

    public String getNomenclature() {
        return nomenclature;
    }

    public void setNomenclature(String nomenclature) {
        this.nomenclature = nomenclature;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    
    @Override
    public String toString() {
        return "Articulo{" + "id=" + id + ", quantity=" + quantity + ", partNumber=" + partNumber + ", nomenclature=" + nomenclature + ", price=" + price + '}';
    }

    
}

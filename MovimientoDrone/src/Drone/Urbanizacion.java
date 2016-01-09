package Drone;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author David Fernández Puentes
 */
public class Urbanizacion {
    int identificador;
    int rango;
    float x;
    float y;

    public Urbanizacion() {
    }

    public Urbanizacion(int identificador, int rango, float x, float y) {
        this.identificador = identificador;
        this.rango = rango;
        this.x = x;
        this.y = y;
    }

    public int getIdentificador() {
        return identificador;
    }

    public void setIdentificador(int identificador) {
        this.identificador = identificador;
    }

    public int getRango() {
        return rango;
    }

    public void setRango(int rango) {
        this.rango = rango;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "Urbanizacion{" + "identificador=" + identificador + ", rango=" + rango + ", x=" + x + ", y=" + y + '}';
    }
    /**
     * Recorrido de una matriz en espiral desde el centro
     * Funciona correctamente con tamaños impares.
     * @return devuelve una lista con los elementos que se visitan 
     * @param x coordenada en el eje x
     * @param y coordenada en el eje y
     * @param rangoEntrada es el rango desde el centro de la matriz. 
     *     2    2   2   2   2
     *     2    1   1   1   2
     *     2    1   0   1   2
     *     2    1   1   1   2
     *     2    2   2   2   2 
     * La figura anterior muestra el rango de la matriz y los valores
     * que tienen incluidos son los elementos ordenados de izquierda a derecha
     * y de arriba a abajo
     */
    public ArrayList<Urbanizacion> obtenerUrbanizaciones(float x, float y, int rangoEntrada){
     ArrayList<Urbanizacion> urbanizaciones = new ArrayList<>(); 
        //Defino la matriz de urbanizaciones en local
        //para recorrerla en forma de espiral 
        int mat[][] = new int[10][10]; 
        int tam = 5, origen, i, izquierda, derecha; 
        int identificadorUrbanizacion =1; //idUrbanizacion 
        //CREACION DE LA MATRIZ DE IDENTIFICADORES
        
        for (i = 0; i <= (tam - 1); i++) 
            for (izquierda = 0; izquierda <= (tam - 1); izquierda++)  
                mat[i][izquierda] = identificadorUrbanizacion++; 
        
        origen = (int) (tam / 2);
        int rango=0; // rango parcial hasta llegar al tope del rango de entrada     
        while (rango <= rangoEntrada) { 
            if (rango == 0) { 
                //En el caso del primer elemento u origen
                urbanizaciones.add(new Urbanizacion(mat[origen][origen], rango, origen, origen));
                rango++; 
            } else { 
                while (rango <= rangoEntrada) {
                    //Adyacente izquierda
                    for (izquierda = (origen + rango - 1); izquierda >= (origen - rango + 1); izquierda--)  
                       urbanizaciones.add(new Urbanizacion(mat[izquierda][origen - rango], rango, izquierda, (origen-rango)));
                    //Adyacente arriba 
                    for (i = (-rango); i <= (rango); i++)                             
                        urbanizaciones.add(new Urbanizacion(mat[origen - rango][origen + i], rango, (origen - rango), (origen + i)));
                     //Adyacente derecha
                    for (derecha = (origen - rango + 1); derecha <= (origen + rango - 1); derecha++)        
                        urbanizaciones.add(new Urbanizacion(mat[derecha][origen+rango],rango,derecha,(origen+rango)));
                    //Adyacente derecha
                    for (i = (origen + rango); i >= (origen - rango); i--)            
                        urbanizaciones.add(new Urbanizacion(mat[origen+rango][i],rango,(origen+rango),i));
                     
                    rango++; 
                } 
            } 
        }
        //Mostramos por pantalla los elementos que se han seleccionado
        Iterator<Urbanizacion> it = urbanizaciones.iterator();
            while(it.hasNext())
                System.out.println(it.next().toString());
        return urbanizaciones;
    }
    
}

package org.example;

import java.util.ArrayList;

public class Pronosticador {
    final int PUNTAJE_INICIAL = 0;
    final int CANTIDAD_DE_ACIERTOS_INICIAL = 0;

    private final String nombre;
    private ArrayList<Pronostico> pronosticos = new ArrayList<Pronostico>();
    private int puntaje;
    private int cantidad_de_aciertos;

    //Post: Crea un pronosticador con los datos recibidos
    public Pronosticador(String nombre) {
        this.nombre = nombre;
        this.puntaje = PUNTAJE_INICIAL;
        cantidad_de_aciertos = CANTIDAD_DE_ACIERTOS_INICIAL;
    }

    //Post: Agrega el pronóstico recibido al array de pronosticos
    public void agregar_pronostico(Pronostico pronostico) {
        pronosticos.add(pronostico);
    }

    //Post: Devuelve el array con todos los pronosticos del pronosticador
    public ArrayList<Pronostico> obtener_pronosticos(){
        return pronosticos;
    }

    //Post: Setea el puntaje del pronosticador
    public void set_puntaje(int puntaje){
        this.puntaje = puntaje;
    }

    //Post: Devuelve el puntaje del pronosticador
    public int get_puntaje(){
        return puntaje;
    }

    //Post: Devuelve el nombre del pronosticador
    public String get_nombre(){
        return nombre;
    }

    public void aumentar_aciertos() {
        cantidad_de_aciertos++;
    }

    public int get_cantidad_de_aciertos() {
        return cantidad_de_aciertos;
    }
}

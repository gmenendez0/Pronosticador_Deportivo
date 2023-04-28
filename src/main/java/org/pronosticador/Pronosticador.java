package org.pronosticador;

import java.util.ArrayList;

public class Pronosticador {
    final int PUNTAJE_INICIAL = 0;
    final int CANTIDAD_DE_ACIERTOS_INICIAL = 0;
    final int ERROR = -1;

    private final String nombre;
    private final ArrayList<Pronostico> pronosticos = new ArrayList<>();
    private int puntaje;
    private int cantidadDeAciertos;
    private final ArrayList<AciertoRonda> aciertosPorRonda = new ArrayList<>();

    //Post: Crea un pronosticador con los datos recibidos
    public Pronosticador(String nombre) {
        this.nombre = nombre;
        this.puntaje = PUNTAJE_INICIAL;
        cantidadDeAciertos = CANTIDAD_DE_ACIERTOS_INICIAL;
    }

    //Post: Agrega el pronóstico recibido al array de pronosticos
    public void agregarPronostico(Pronostico pronostico) {
        pronosticos.add(pronostico);
    }

    //Post: Devuelve el array con todos los pronosticos del pronosticador
    public ArrayList<Pronostico> obtenerPronosticos(){
        return pronosticos;
    }

    //Post: Setea el puntaje del pronosticador
    public void setPuntaje(int puntaje){
        this.puntaje = puntaje;
    }

    //Post: Devuelve el puntaje del pronosticador
    public int getPuntaje(){
        return puntaje;
    }

    //Post: Devuelve el nombre del pronosticador
    public String getNombre(){
        return nombre;
    }

    //Post: Aumenta la cantidad de aciertos del pronosticador.
    public void aumentarAciertos() {
        cantidadDeAciertos++;
    }

    //Post: Devuelve la cantidad de aciertos del pronosticador
    public int getCantidadDeAciertos() {
        return cantidadDeAciertos;
    }

    //Pre: No puede haber rondas de id <= 0.
    //Post: Inicializará en el vector de aciertos_por_ronda, las rondas que esten hayan sido cargadas al sistema.
    public void inicializarAciertosPorRonda(ArrayList<Ronda> rondas) {
        for (Ronda ronda : rondas) {
            var contadorAciertoRonda = new AciertoRonda(ronda.getIdRonda());
            aciertosPorRonda.add(contadorAciertoRonda);
        }
    }

    //Pre: Debe recibir un idRonda de id mayor a 0 y que este registrado en el sistema de rondas del sistema.
    //Post: Aumenta en 1 la cantidad de aciertos de la ronda recibida.
    public void aumentarAciertosDeRonda(int idRonda){
        for (AciertoRonda aciertoRonda : aciertosPorRonda) {
            if (aciertoRonda.getIdRonda() == idRonda) aciertoRonda.aumentarAciertos();
        }
    }

    //Pre: Debe recibir un número de ronda de id mayor a 0 y que este registrado en el sistema de rondas del sistema.
    //Post: Devuelve la cantidad de aciertos del pronosticador en la ronda recibida o -1 en caso de recibir ronda erronea.
    public int getAciertosDeRonda(int numeroDeRonda) {
        int cantidadDeAciertos = ERROR;

        for (AciertoRonda aciertoRonda : aciertosPorRonda) {
            if (aciertoRonda.getIdRonda() == numeroDeRonda) cantidadDeAciertos = aciertoRonda.getAciertos();
        }

        return cantidadDeAciertos;
    }
}

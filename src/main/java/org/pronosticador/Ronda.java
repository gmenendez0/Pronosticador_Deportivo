package org.pronosticador;

import java.util.ArrayList;

public class Ronda {
    private final int idRonda;
    private final ArrayList<Partido> partidos = new ArrayList<>();

    //Post: Crea una ronda con el id recibido
    public Ronda(int idRonda) {
        this.idRonda = idRonda;
    }

    //Post: Agrega el partido recibido al array de partidos
    public void agregarPartido(Partido partido) {
        partidos.add(partido);
    }

    //Pre: Debe recibir un id_partido mayor a 0.
    //Post: Devuelve el partido con el id recibido o null en caso de no encontrarlo.
    public Partido obtenerPartido(int idPartido) {
        boolean encontrado = false;
        Partido partidoBuscado = null;
        int iterador = 0;

        while (!encontrado && iterador < partidos.size()) {
            if (partidos.get(iterador).getIdPartido() == idPartido) {
                encontrado = true;
                partidoBuscado = partidos.get(iterador);
            }
            iterador++;
        }

        return partidoBuscado;
    }

    //Post: Devuelve el id de la ronda
    public int getIdRonda() {
        return idRonda;
    }

    //Post: Devuelve la cantidad de partidos que tiene la ronda
    public int getCantidadDePartidos() {
        return partidos.size();
    }
}

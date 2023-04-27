package org.pronosticador;

public class Pronostico {
    private final int idPartido;
    private final int idRonda;
    private final int pronostico; //0: empate, 1: equipo1, 2: equipo2

    //Post: Crea un pronostico con los datos recibidos
    public Pronostico(int idPartido, int idRonda, int pronostico){
        this.idPartido = idPartido;
        this.idRonda = idRonda;
        this.pronostico = pronostico;
    }

    //Post: Devuelve el ID del partido a la que pertenece el pronóstico
    public int getIdPartido(){
        return idPartido;
    }

    //Post: Devuelve el ID de la ronda del partido a la que pertenece el pronóstico
    public int getIdRonda(){
        return idRonda;
    }

    //Post: Devuelve el pronóstico hecho. 1 si gana el equipo 1, 2 si gana el equipo 2, 0 si es empate
    public int getPronostico(){
        return pronostico;
    }
}

package org.pronosticador;

public class Partido {
    final int EMPATE = 0;
    final int GANA_EQUIPO1 = 1;
    final int GANA_EQUIPO2 = 2;

    private final int idPartido;
    private final int idRonda;
    private int resultado; //0: empate, 1: equipo1, 2: equipo2

    //Post: Crea un partido con los datos recibidos.
    public Partido(int idPartido, int idRonda, int golesEquipo1, int golesEquipo2) {
        this.idPartido = idPartido;
        this.idRonda = idRonda;

        this.calcularResultadoPartido(golesEquipo1, golesEquipo2);
    }

    //Pre: Debe recibir los goles de cada equipo
    //Post: Calcula el resultado del partido y lo guarda en la variable resultado
    private void calcularResultadoPartido(int golesEquipo1, int golesEquipo2) {
        if(golesEquipo1 < golesEquipo2) {
            this.resultado = GANA_EQUIPO2;
        } else if(golesEquipo1 > golesEquipo2) {
            this.resultado = GANA_EQUIPO1;
        } else {
            this.resultado = EMPATE;
        }
    }

    //Post: Devuelve el ID del partido
    public int getIdPartido() {
        return this.idPartido;
    }

    //Post: Devuelve el ID de la ronda a la que pertenece el partido
    public int getIdRonda() {
        return this.idRonda;
    }

    //Post: Devuelve el resultado del partido
    public int getResultadoPartido() {
        return this.resultado;
    }
}

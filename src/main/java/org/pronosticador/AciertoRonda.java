package org.pronosticador;

public class AciertoRonda {
    final int CANTIDAD_DE_ACIERTOS_INICIAL = 0;

    private final int idRonda;
    private int aciertos;

    public AciertoRonda(int idRonda) {
        this.idRonda = idRonda;
        this.aciertos = CANTIDAD_DE_ACIERTOS_INICIAL;
    }

    //Post: Devuelve el id de la ronda
    public int getIdRonda() {
        return idRonda;
    }

    //Post: Devuelve la cantidad de aciertos de la ronda
    public int getAciertos() {
        return aciertos;
    }

    //Post: Aumenta en 1 la cantidad de aciertos de la ronda.
    public void aumentarAciertos() {
        aciertos++;
    }
}

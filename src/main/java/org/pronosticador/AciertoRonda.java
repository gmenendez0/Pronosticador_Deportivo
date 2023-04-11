package org.pronosticador;

public class AciertoRonda {
    final int CANTIDAD_DE_ACIERTOS_INICIAL = 0;

    private final int id_ronda;
    private int aciertos;

    public AciertoRonda(int id_ronda) {
        this.id_ronda = id_ronda;
        this.aciertos = CANTIDAD_DE_ACIERTOS_INICIAL;
    }

    //Post: Devuelve el id de la ronda
    public int get_id_ronda() {
        return id_ronda;
    }

    //Post: Devuelve la cantidad de aciertos de la ronda
    public int get_aciertos() {
        return aciertos;
    }

    //Post: Aumenta en 1 la cantidad de aciertos de la ronda.
    public void aumentar_aciertos() {
        aciertos++;
    }
}

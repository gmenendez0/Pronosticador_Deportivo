package org.pronosticador;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class PronosticadorTest {

    //Post: Testea el correcto funcionamiento de inicializarAciertosPorRonda
    @Test
    void inicializarAciertosPorRonda() {
       Pronosticador pronosticador = new Pronosticador("Marcos");
       ArrayList<Ronda> Rondas = new ArrayList<>();
       var ronda1 = new Ronda(1);
       var ronda2 = new Ronda(2);
       var ronda3 = new Ronda(3);
       Rondas.add(ronda1);
       Rondas.add(ronda2);
       Rondas.add(ronda3);

       pronosticador.inicializarAciertosPorRonda(Rondas);
       int aciertosDeRonda = pronosticador.getAciertosDeRonda(3);

       assertEquals(0, aciertosDeRonda);
    }

    //Post: Testea el correcto funcionamiento de aumentarAciertosDeRonda
    @Test
    void aumentarAciertosDeRonda() {
        Pronosticador pronosticador = new Pronosticador("Marcos");
        ArrayList<Ronda> Rondas = new ArrayList<>();
        var ronda1 = new Ronda(1);
        var ronda2 = new Ronda(2);
        var ronda3 = new Ronda(3);
        Rondas.add(ronda1);
        Rondas.add(ronda2);
        Rondas.add(ronda3);

        pronosticador.inicializarAciertosPorRonda(Rondas);
        pronosticador.aumentarAciertosDeRonda(2);
        int aciertosDeRonda = pronosticador.getAciertosDeRonda(2);

        assertEquals(1, aciertosDeRonda);
    }

    //Post: Testea el correcto funcionamiento de getAciertosDeRonda
    @Test
    void getAciertosDeRonda() {
        Pronosticador pronosticador = new Pronosticador("Marcos");
        ArrayList<Ronda> Rondas = new ArrayList<>();
        var ronda1 = new Ronda(1);
        var ronda2 = new Ronda(2);
        var ronda3 = new Ronda(3);
        Rondas.add(ronda1);
        Rondas.add(ronda2);
        Rondas.add(ronda3);

        pronosticador.inicializarAciertosPorRonda(Rondas);
        int aciertosDeRondaError = pronosticador.getAciertosDeRonda(15);

        pronosticador.inicializarAciertosPorRonda(Rondas);
        int aciertosDeSegundaRonda = pronosticador.getAciertosDeRonda(2);

        assertEquals(-1, aciertosDeRondaError);
        assertEquals(0, aciertosDeSegundaRonda);
    }
}
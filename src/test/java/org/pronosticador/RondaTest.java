package org.pronosticador;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RondaTest {

    //Post: Testea el correcto funcionamiento de obtenerPartido.
    @Test
    void obtenerPartido() {
        var ronda1 = new Ronda(1);
        var partido = new Partido(1, 1, "hola", "hola2", 2, 2);

        ronda1.agregarPartido(partido);
        var partidoEsperado = ronda1.obtenerPartido(1);

        ronda1.agregarPartido(partido);
        var partidoNull = ronda1.obtenerPartido(1234);

        assertNull(partidoNull);
        assertEquals(partido, partidoEsperado);
    }
}
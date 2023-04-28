package org.pronosticador;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PartidoTest {

    //Post: Testea el correcto funcionamiento del metodo calcularResultadoPartido.
    @Test
    void calcularResultadoPartido() {
        var partidoEmpatado = new Partido(1, 1, "hola", "hola2", 2, 2);
        var partidoGanaEquipoUno = new Partido(1, 1, "hola", "hola2", 3, 2);
        var partidoGanaEquipoDos = new Partido(1, 1, "hola", "hola2", 3, 4);


        int resultadoPartidoEmpatado = partidoEmpatado.getResultadoPartido();
        int resultadoPartidoGanaEquipoUno = partidoGanaEquipoUno.getResultadoPartido();
        int resultadoPartidoGanaEquipoDos = partidoGanaEquipoDos.getResultadoPartido();

        assertEquals(0, resultadoPartidoEmpatado);
        assertEquals(1, resultadoPartidoGanaEquipoUno);
        assertEquals(2, resultadoPartidoGanaEquipoDos);
    }
}
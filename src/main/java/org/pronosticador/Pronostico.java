package org.pronosticador;

/**
 * @param pronostico 0: empate, 1: equipo1, 2: equipo2
 */
public record Pronostico(int idPartido, int idRonda, int pronostico){
}

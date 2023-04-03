package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Scanner {
    static final int PRIMERA_POSICION = 0;
    static final int SEGUNDA_POSICION = 1;
    static final int TERCERA_POSICION = 2;
    static final int CUARTA_POSICION = 3;
    static final int QUINTA_POSICION = 4;
    static final int SEXTA_POSICION = 5;
    static final int GANA_EQUIPO1 = 1;
    static final int GANA_EQUIPO2 = 2;
    static final int EMPATE = 0;
    static final String SEPARADOR = ";";
    static final String INDICADOR = "X";

    private final Path partidos_path;
    private final Path pronosticos_path;

    //Post: Crea un objeto scanner
    public Scanner(Path partidos_path, Path pronosticos_path) {
        this.partidos_path = partidos_path;
        this.pronosticos_path = pronosticos_path;
    }

    //Pre; El path "partidos_path" debe estar inicialiado.
    //Post: Lee el csv del path "partidos_path" y carga lo leido en la competencia.
    public void cargar_partidos(Competencia competencia) throws IOException {
        BufferedReader lector = Files.newBufferedReader(this.partidos_path);
        String linea_leida;
        String[] caracteristicas_partido;
        Partido partido_leido;

        while((linea_leida = lector.readLine()) != null){
            caracteristicas_partido = linea_leida.split(SEPARADOR);

            partido_leido = new Partido(Integer.parseInt(caracteristicas_partido[PRIMERA_POSICION]), Integer.parseInt(caracteristicas_partido[SEGUNDA_POSICION]), caracteristicas_partido[TERCERA_POSICION], caracteristicas_partido[SEXTA_POSICION], Integer.parseInt(caracteristicas_partido[CUARTA_POSICION]), Integer.parseInt(caracteristicas_partido[QUINTA_POSICION]));
            competencia.agregar_partido(partido_leido);
        }
    }

    //Pre; El path "pronosticos_path" debe estar inicialiado.
    //Post: Lee el csv del path "pronosticos_path" y carga lo leido en la competencia.
    public void cargar_pronosticos(Competencia competencia) throws IOException {
        BufferedReader lector = Files.newBufferedReader(this.pronosticos_path);
        String linea_leida;
        String[] caracteristicas_pronostico;
        Pronostico pronostico_leido;
        int pronostico_realizado;

        while((linea_leida = lector.readLine()) != null){
            caracteristicas_pronostico = linea_leida.split(SEPARADOR);
            pronostico_realizado = determinar_pronostico_realizado(caracteristicas_pronostico);

            pronostico_leido = new Pronostico(Integer.parseInt(caracteristicas_pronostico[1]), Integer.parseInt(caracteristicas_pronostico[2]), pronostico_realizado);
            competencia.agregar_pronostico(pronostico_leido, caracteristicas_pronostico[0]);
        }
    }

    //Post: Determina el pronostico realizado. Devuelve 1 si gana el equipo 1, 2 si gana el equipo 2 y 0 si es empate.
    private int determinar_pronostico_realizado(String[] caracteristicas_pronostico){
        int pronostico_realizado;

        if(caracteristicas_pronostico[QUINTA_POSICION].equals(INDICADOR)) {
            pronostico_realizado = GANA_EQUIPO1;
        } else if(caracteristicas_pronostico[SEXTA_POSICION].equals(INDICADOR)){
            pronostico_realizado = EMPATE;
        } else {
            pronostico_realizado = GANA_EQUIPO2;
        }

        return pronostico_realizado;
    }
}

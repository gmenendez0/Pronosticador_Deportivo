package org.pronosticador;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) throws IOException {
        var inputs_usuario = new InputsUsuario();
        Competencia competencia = new Competencia(inputs_usuario.preguntar_puntos_por_acierto());

        Path partidos_path = Paths.get("src/main/resources/partidos.csv");
        if(!Files.exists(partidos_path)) Files.createFile(partidos_path);

        Path pronosticos_path = Paths.get("src/main/resources/pronosticos.csv");
        if(!Files.exists(pronosticos_path)) Files.createFile(pronosticos_path);

        CSV_Scanner CSV_Scanner = new CSV_Scanner(partidos_path, pronosticos_path);

        CSV_Scanner.cargar_partidos(competencia);
        CSV_Scanner.cargar_pronosticos(competencia);

        competencia.mostrar_puntajes();
    }
}
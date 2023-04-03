package org.example;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) throws IOException {
        Competencia competencia = new Competencia();

        Path partidos_path = Paths.get("src/main/resources/partidos.csv");
        if(!Files.exists(partidos_path)) Files.createFile(partidos_path);

        Path pronosticos_path = Paths.get("src/main/resources/pronosticos.csv");
        if(!Files.exists(pronosticos_path)) Files.createFile(pronosticos_path);

        Scanner scanner = new Scanner(partidos_path, pronosticos_path);

        scanner.cargar_partidos(competencia);
        scanner.cargar_pronosticos(competencia);

        competencia.mostrar_puntajes();
    }
}
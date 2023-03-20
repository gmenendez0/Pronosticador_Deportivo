import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
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

    public static void main(String[] args) throws IOException {
        Competencia competencia = new Competencia();

        Path partidos_path = Paths.get("src/partidos.csv");
        if(!Files.exists(partidos_path)) Files.createFile(partidos_path);

        Path pronosticos_path = Paths.get("src/pronosticos.csv");
        if(!Files.exists(pronosticos_path)) Files.createFile(pronosticos_path);

        cargar_partidos(competencia, partidos_path);
        cargar_pronosticos(competencia, pronosticos_path);
    }


    private static void cargar_pronosticos(Competencia competencia, Path pronosticos_path) throws IOException {
        BufferedReader lector = Files.newBufferedReader(pronosticos_path);
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

    private static int determinar_pronostico_realizado(String[] caracteristicas_pronostico){
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

    private static void cargar_partidos(Competencia competencia, Path partidos_path) throws IOException {
        BufferedReader lector = Files.newBufferedReader(partidos_path);
        String linea_leida;
        String[] caracteristicas_partido;
        Partido partido_leido;

        while((linea_leida = lector.readLine()) != null){
            caracteristicas_partido = linea_leida.split(SEPARADOR);

            partido_leido = new Partido(Integer.parseInt(caracteristicas_partido[PRIMERA_POSICION]), Integer.parseInt(caracteristicas_partido[SEGUNDA_POSICION]), caracteristicas_partido[TERCERA_POSICION], caracteristicas_partido[SEXTA_POSICION], Integer.parseInt(caracteristicas_partido[CUARTA_POSICION]), Integer.parseInt(caracteristicas_partido[QUINTA_POSICION]));
            competencia.agregar_partido(partido_leido);
        }
    }
}
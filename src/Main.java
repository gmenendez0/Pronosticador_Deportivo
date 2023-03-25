import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    static final int CANTIDAD_DE_ARGUMENTOS_ESPERADOS = 2;
    static final int ERROR = 1;
    static final int PRIMERA_POSICION = 0;
    static final int SEGUNDA_POSICION = 1;

    public static void main(String[] args) throws IOException {
       /* ? PARA PRESENTAR SEGUN LO PEDIDO:

       if (args.length != CANTIDAD_DE_ARGUMENTOS_ESPERADOS) {
            System.out.println("Error: cantidad de argumentos incorrecta. Debe proveer PRIMERO la ruta del archivo partidos.csv y SEGUNDO la ruta del archivo pronosticos.csv");
            System.exit(ERROR);
        }

        Competencia competencia = new Competencia();

        Path partidos_path = Paths.get(args[PRIMERA_POSICION]);
        if(!Files.exists(partidos_path)) Files.createFile(partidos_path);

        Path pronosticos_path = Paths.get(args[SEGUNDA_POSICION]);
        if(!Files.exists(pronosticos_path)) Files.createFile(pronosticos_path);

        Scanner scanner = new Scanner(partidos_path, pronosticos_path);

        scanner.cargar_partidos(competencia);
        scanner.cargar_pronosticos(competencia);

        competencia.mostrar_puntajes();*/

        /* ! PARA TESTING: */

        Competencia competencia = new Competencia();

        Path partidos_path = Paths.get("./resources/partidos.csv");
        if(!Files.exists(partidos_path)) Files.createFile(partidos_path);

        Path pronosticos_path = Paths.get("./resources/pronosticos.csv");
        if(!Files.exists(pronosticos_path)) Files.createFile(pronosticos_path);

        Scanner scanner = new Scanner(partidos_path, pronosticos_path);

        scanner.cargar_partidos(competencia);
        scanner.cargar_pronosticos(competencia);

        competencia.mostrar_puntajes();
    }
}
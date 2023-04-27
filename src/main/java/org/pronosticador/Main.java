package org.pronosticador;
import org.database.DatabaseConnector;
import org.user_inputs.InputsUsuario;
import java.io.FileNotFoundException;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException, FileNotFoundException {
        var inputsUsuario = new InputsUsuario();
        Competencia competencia = new Competencia(inputsUsuario.preguntarPuntosPorAcierto());
        DatabaseConnector databaseConnector = new DatabaseConnector();

        databaseConnector.cargarPartidos(competencia);
        databaseConnector.cargarPronosticos(competencia);

        competencia.mostrarPuntajes();
    }
}
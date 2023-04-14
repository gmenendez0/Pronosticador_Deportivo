package org.pronosticador;
import java.io.FileNotFoundException;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException, FileNotFoundException {
        var inputs_usuario = new InputsUsuario();
        Competencia competencia = new Competencia(inputs_usuario.preguntar_puntos_por_acierto());
        DatabaseConnector databaseConnector = new DatabaseConnector();

        databaseConnector.cargarPartidos(competencia);
        databaseConnector.cargarPronosticos(competencia);

        competencia.mostrar_puntajes();
    }
}
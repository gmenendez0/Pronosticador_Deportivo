package org.pronosticador;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class DatabaseConnector {
    static final int PRIMERA_POSICION = 1;
    static final int SEGUNDA_POSICION = 2;
    static final int TERCERA_POSICION = 3;
    static final int CUARTA_POSICION = 4;
    static final int QUINTA_POSICION = 5;
    static final int SEXTA_POSICION = 6;
    static final int OCTAVA_POSICION = 8;

    static final int PRIMER_INDEX = 0;
    static final int SEGUNDO_INDEX = 1;
    static final int TERCER_INDEX = 2;

    static final int GANA_EQUIPO1 = 1;
    static final int GANA_EQUIPO2 = 2;
    static final int EMPATE = 0;
    static final String INDICADOR = "X";

    static final String CONFIG_FILE_PATH = "src/main/resources/config.properties";

    //Post: Lee las credenciales de la base de datos del archivo .properties y las devuelve en un array de strings.
    private String[] recuperarCredencialesBaseDeDatos() throws FileNotFoundException {
        Properties propiedades = new Properties();
        String[] credencialesBaseDeDatos = {"","",""};

        try {
            FileInputStream configFile = new FileInputStream(CONFIG_FILE_PATH);
            propiedades.load(configFile);

            credencialesBaseDeDatos[PRIMER_INDEX] = propiedades.getProperty("DBurl");
            credencialesBaseDeDatos[SEGUNDO_INDEX] = propiedades.getProperty("DBuser");
            credencialesBaseDeDatos[TERCER_INDEX] = propiedades.getProperty("DBpassword");
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return credencialesBaseDeDatos;
    }

    //Post: Lee desde la base de dato la tabla de partidos y carga lo leido en la competencia.
    public void cargarPartidos(Competencia competencia) throws SQLException, FileNotFoundException {
        Partido partidoLeido;
        String[] credencialesBaseDeDatos = recuperarCredencialesBaseDeDatos();

        try{
            Connection con = DriverManager.getConnection(credencialesBaseDeDatos[PRIMER_INDEX], credencialesBaseDeDatos[SEGUNDO_INDEX], credencialesBaseDeDatos[TERCER_INDEX]);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Partidos");

            while(rs.next()) {
                partidoLeido = new Partido(rs.getInt(PRIMERA_POSICION), rs.getInt(SEGUNDA_POSICION), rs.getString(TERCERA_POSICION), rs.getString(SEXTA_POSICION), rs.getInt(CUARTA_POSICION), rs.getInt(QUINTA_POSICION));
                competencia.agregar_partido(partidoLeido);
            }

            con.close();
        } catch(Exception e){
            throw new SQLException(e);
        }
    }

    //Post: Lee desde la base de datos la tabla de pronosticos y carga lo leido en la competencia.
    public void cargarPronosticos(Competencia competencia) throws SQLException, FileNotFoundException {
        Pronostico pronosticoLeido;
        int pronosticoPartido;
        String[] credencialesBaseDeDatos = recuperarCredencialesBaseDeDatos();

        try {
            Connection con = DriverManager.getConnection(credencialesBaseDeDatos[PRIMER_INDEX], credencialesBaseDeDatos[SEGUNDO_INDEX], credencialesBaseDeDatos[TERCER_INDEX]);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Pronosticos");

            while (rs.next()) {
                String[] caracteristicasPronostico = {rs.getString(SEXTA_POSICION), rs.getString(OCTAVA_POSICION)};

                pronosticoPartido = determinarPronosticoRealizado(caracteristicasPronostico);
                pronosticoLeido = new Pronostico(rs.getInt(TERCERA_POSICION), rs.getInt(CUARTA_POSICION), pronosticoPartido);

                competencia.agregar_pronostico(pronosticoLeido, rs.getString(SEGUNDA_POSICION));
            }

            con.close();
        } catch (Exception e) {
            throw new SQLException(e);
        }
    }

    //Post: Determina el pronóstico realizado. Devuelve 1 si gana el equipo 1, 2 si gana el equipo 2 y 0 si es empatan.
    private int determinarPronosticoRealizado(String[] caracteristicasPronostico){
        int pronosticoRealizado;

        if(caracteristicasPronostico[PRIMER_INDEX].equals(INDICADOR)) {
            pronosticoRealizado = GANA_EQUIPO1;
        } else if(caracteristicasPronostico[SEGUNDO_INDEX].equals(INDICADOR)){
            pronosticoRealizado = GANA_EQUIPO2;
        } else {
            pronosticoRealizado = EMPATE;
        }

        return pronosticoRealizado;
    }
}

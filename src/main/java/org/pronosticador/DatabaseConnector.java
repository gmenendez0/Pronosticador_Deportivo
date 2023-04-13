package org.pronosticador;
import java.sql.*;

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
    static final int GANA_EQUIPO1 = 1;
    static final int GANA_EQUIPO2 = 2;
    static final int EMPATE = 0;
    static final String INDICADOR = "X";

    static final String urlDB = "jdbc:mysql://sql10.freemysqlhosting.net:3306/sql10612648";
    static final String CONTRASENIA = "Y4dezdRJMd"; //TODO COLOCAR CONTRASEÑA
    static final String USUARIO = "sql10612648";

    public void crearBaseDeDatos() throws SQLException {
        try {
            Connection con = DriverManager.getConnection(urlDB, USUARIO, CONTRASENIA);
            Statement stmt = con.createStatement();

            stmt.execute("CREATE TABLE Pronosticos(" +
                    " ID_PRONOSTICO int primary key NOT NULL AUTO_INCREMENT," +
                    " PARTICIPANTE Varchar(255) NOT NULL," +
                    " ID_PARTIDO int NOT NULL," +
                    " ID_RONDA INT NOT NULL," +
                    " EQUIPO1 Varchar(255) NOT NULL," +
                    " GANA1 Varchar(1)," +
                    " EMPATA Varchar(1)," +
                    " GANA2 Varchar(1)," +
                    " EQUIPO2 Varchar(255) NOT NULL" +
                    ");");

            stmt.execute("CREATE TABLE Partidos(" +
                    " ID_PARTIDO int primary key NOT NULL AUTO_INCREMENT," +
                    " ID_RONDA int  NOT NULL," +
                    " EQUIPO1 Varchar(255) NOT NULL," +
                    " GOLES_EQ1 int NOT NULL," +
                    " GOLES_EQ2 int NOT NULL," +
                    " EQUIPO2 Varchar(255) NOT NULL" +
                    ");");

            con.close();
        }catch(Exception e){
            throw e;
        }
    }

    //Post: Lee desde la base de dato la tabla de partidos y carga lo leido en la competencia.
    public void cargarPartidos(Competencia competencia) throws SQLException {
        Partido partidoLeido;

        try{
            Connection con = DriverManager.getConnection(urlDB, USUARIO, CONTRASENIA);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Partidos");

            while(rs.next()) {
                partidoLeido = new Partido(rs.getInt(PRIMERA_POSICION), rs.getInt(SEGUNDA_POSICION), rs.getString(TERCERA_POSICION), rs.getString(SEXTA_POSICION), rs.getInt(CUARTA_POSICION), rs.getInt(QUINTA_POSICION));
                competencia.agregar_partido(partidoLeido);
            }

            con.close();
        } catch(Exception e){
            throw e;
        }
    }

    //Post: Lee desde la base de datos la tabla de pronosticos y carga lo leido en la competencia.
    public void cargarPronosticos(Competencia competencia) throws SQLException {
        Pronostico pronosticoLeido;
        int pronosticoPartido;

        try {
            Connection con = DriverManager.getConnection(urlDB, USUARIO, CONTRASENIA);
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
            throw e;
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

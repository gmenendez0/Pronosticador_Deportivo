package org.pronosticador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class PartidosMundial {
    static final String urlDB = "jdbc:mysql://sql10.freemysqlhosting.net:3306/sql10612648";
    static final String contraseña="Y4dezdRJMd";//TODO COLOCAR CONTRASEÑA
    static final String usuario= "sql10612648";
    public static void traerPartidos(){
        try {
            Connection con = DriverManager.getConnection(urlDB, usuario, contraseña);
            Statement stmt = con.createStatement();

            stmt.execute("INSERT INTO Partidos(ID_RONDA, EQUIPO1, GOLES_EQ1, GOLES_EQ2, EQUIPO2)" +
                    "VALUES (1, 'QATAR', 0, 2, 'ECUADOR')");
            stmt.execute("INSERT INTO Partidos(ID_RONDA, EQUIPO1, GOLES_EQ1, GOLES_EQ2, EQUIPO2)" +
                    "VALUES (1, 'SENEGAL', 0, 2, 'PAISES BAJOS')");
            stmt.execute("INSERT INTO Partidos(ID_RONDA, EQUIPO1, GOLES_EQ1, GOLES_EQ2, EQUIPO2)" +
                    "VALUES (1, 'QATAR', 0, 2, 'SENEGAL')");
            stmt.execute("INSERT INTO Partidos(ID_RONDA, EQUIPO1, GOLES_EQ1, GOLES_EQ2, EQUIPO2)" +
                    "VALUES (1, 'PAISES BAJOS', 1, 1, 'ECUADOR')");
            stmt.execute("INSERT INTO Partidos(ID_RONDA, EQUIPO1, GOLES_EQ1, GOLES_EQ2, EQUIPO2)" +
                    "VALUES (1, 'PAISES BAJOS', 2, 0, 'QATAR')");
            stmt.execute("INSERT INTO Partidos(ID_RONDA, EQUIPO1, GOLES_EQ1, GOLES_EQ2, EQUIPO2)" +
                    "VALUES (1, 'ECUADOR', 1, 2, 'SENEGAL')");

            stmt.execute("INSERT INTO Partidos(ID_RONDA, EQUIPO1, GOLES_EQ1, GOLES_EQ2, EQUIPO2)" +
                    "VALUES (2,'INGLATERRA', 6, 2, 'IRAN')");
            stmt.execute("INSERT INTO Partidos(ID_RONDA, EQUIPO1, GOLES_EQ1, GOLES_EQ2, EQUIPO2)" +
                    "VALUES (2, 'EE.UU', 1, 1, 'GALES')");
            stmt.execute("INSERT INTO Partidos(ID_RONDA, EQUIPO1, GOLES_EQ1, GOLES_EQ2, EQUIPO2)" +
                    "VALUES (2, 'INGLATERRA', 0, 0, 'EE.UU')");
            stmt.execute("INSERT INTO Partidos(ID_RONDA, EQUIPO1, GOLES_EQ1, GOLES_EQ2, EQUIPO2)" +
                    "VALUES (2, 'GALES', 0, 2 , 'IRAN')");
            stmt.execute("INSERT INTO Partidos(ID_RONDA, EQUIPO1, GOLES_EQ1, GOLES_EQ2, EQUIPO2)" +
                    "VALUES (2, 'GALES', 0, 3, 'INGLATERRA')");
            stmt.execute("INSERT INTO Partidos(ID_RONDA, EQUIPO1, GOLES_EQ1, GOLES_EQ2, EQUIPO2)" +
                    "VALUES (2, 'IRAN', 0, 1, 'EE.UU')");

            stmt.execute("INSERT INTO Partidos(ID_RONDA, EQUIPO1, GOLES_EQ1, GOLES_EQ2, EQUIPO2)" +
                    "VALUES (3, 'ARGENTINA', 1, 2, 'ARABIA SAUDITA')");
            stmt.execute("INSERT INTO Partidos(ID_RONDA, EQUIPO1, GOLES_EQ1, GOLES_EQ2, EQUIPO2)" +
                    "VALUES (3, 'MEXICO', 0, 0, 'POLONIA')");
            stmt.execute("INSERT INTO Partidos(ID_RONDA, EQUIPO1, GOLES_EQ1, GOLES_EQ2, EQUIPO2)" +
                    "VALUES (3, 'ARGENTINA', 2, 0, 'MEXICO')");
            stmt.execute("INSERT INTO Partidos(ID_RONDA, EQUIPO1, GOLES_EQ1, GOLES_EQ2, EQUIPO2)" +
                    "VALUES (3, 'POLONIA', 0, 0, 'ARABIA SAUDITA')");
            stmt.execute("INSERT INTO Partidos(ID_RONDA, EQUIPO1, GOLES_EQ1, GOLES_EQ2, EQUIPO2)" +
                    "VALUES (3, 'POLONIA', 0, 2, 'ARGENTINA')");
            stmt.execute("INSERT INTO Partidos(ID_RONDA, EQUIPO1, GOLES_EQ1, GOLES_EQ2, EQUIPO2)" +
                    "VALUES (3, 'ARABIA SAUDITA', 1, 2, 'MEXICO')");

        }catch(Exception e){
            System.out.println("esta aca el problem partidosM");
            System.out.println(e);
        }
    }
}


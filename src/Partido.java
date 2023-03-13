public class Partido {
    final int EMPATE = 0;
    final int GANA_EQUIPO1 = 1;
    final int GANA_EQUIPO2 = 2;

    private int ID_Partido;
    private int ID_ronda;
    private String ID_equipo1;
    private String ID_equipo2;
    private int goles_equipo1;
    private int goles_equipo2;
    private int resultado; //0: empate, 1: equipo1, 2: equipo2

    //Post: Crea un partido con los datos recibidos.
    public Partido(int ID_Partido, int ID_ronda, String ID_equipo1, String ID_equipo2, int goles_equipo1, int goles_equipo2) {
        this.ID_Partido = ID_Partido;
        this.ID_ronda = ID_ronda;
        this.ID_equipo1 = ID_equipo1;
        this.ID_equipo2 = ID_equipo2;
        this.goles_equipo1 = goles_equipo1;
        this.goles_equipo2 = goles_equipo2;

        this.calcular_resultado_partido(goles_equipo1, goles_equipo2);
    }

    //Pre: Debe recibir los goles de cada equipo
    //Post: Calcula el resultado del partido y lo guarda en la variable resultado
    private void calcular_resultado_partido(int goles_equipo1, int goles_equipo2) {
        if(goles_equipo1 < goles_equipo2) {
            this.resultado = GANA_EQUIPO2;
        } else if(goles_equipo1 > goles_equipo2) {
            this.resultado = GANA_EQUIPO1;
        } else {
            this.resultado = EMPATE;
        }
    }

    //Post: Devuelve el ID del partido
    public int get_id_partido() {
        return this.ID_Partido;
    }

    //Post: Devuelve el ID de la ronda a la que pertenece el partido
    public int get_id_ronda() {
        return this.ID_ronda;
    }

    //Post: Devuelve el resultado del partido
    public int get_resultado_partido() {
        return this.resultado;
    }
}

public class Pronostico {
    private int ID_partido;
    private int ID_ronda;
    private int pronostico; //0: empate, 1: equipo1, 2: equipo2

    //Post: Crea un pronostico con los datos recibidos
    Pronostico(int ID_partido, int ID_ronda, int pronostico){
        this.ID_partido = ID_partido;
        this.ID_ronda = ID_ronda;
        this.pronostico = pronostico;
    }

    //Post: Devuelve el ID del partido a la que pertenece el pronóstico
    public int get_id_partido(){
        return ID_partido;
    }

    //Post: Devuelve el ID de la ronda del partido a la que pertenece el pronóstico
    public int get_id_ronda(){
        return ID_ronda;
    }

    //Post: Devuelve el pronóstico hecho. 1 si gana el equipo 1, 2 si gana el equipo 2, 0 si es empate
    public int get_pronostico(){
        return pronostico;
    }
}

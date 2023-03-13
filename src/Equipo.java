public class Equipo {
    private String ID_equipo;

    //Post: Crea un equipo con el ID especificado
    public Equipo(String ID_equipo){
        this.ID_equipo = ID_equipo;
    }

    //Post: Devuelve el ID del equipo
    public String get_id_equipo(){
        return ID_equipo;
    }
}

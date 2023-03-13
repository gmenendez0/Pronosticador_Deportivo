import java.util.ArrayList;

public class Ronda {
    private int ID_ronda;
    private ArrayList<Partido> partidos = new ArrayList<Partido>();

    //Post: Crea una ronda con el id recibido
    public Ronda(int ID_ronda) {
        this.ID_ronda = ID_ronda;
    }

    //Post: Agrega el partido recibido al array de partidos
    public void agregar_partido(Partido partido) {
        partidos.add(partido);
    }

    //Pre: Debe recibir un id_partido mayor a 0.
    //Post: Devuelve el partido con el id recibido o null en caso de no encontrarlo.
    public Partido obtener_partido(int id_partido) {
        boolean encontrado = false;
        Partido partido_buscado = null;
        int iterador = 0;

        while (!encontrado && iterador < partidos.size()) {
            if (partidos.get(iterador).get_id_partido() == id_partido) {
                encontrado = true;
                partido_buscado = partidos.get(iterador);
            }
            iterador++;
        }

        return partido_buscado;
    }

    //Post: Devuelve el id de la ronda
    public int get_id_ronda() {
        return ID_ronda;
    }
}

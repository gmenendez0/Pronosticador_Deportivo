import java.util.ArrayList;

public class Competencia {
    final int BONUS_ACIERTO = 1;

    private ArrayList<Ronda> rondas = new ArrayList<Ronda>();
    private ArrayList<Equipo> equipos = new ArrayList<Equipo>();
    private ArrayList<Pronosticador> pronosticadores = new ArrayList<Pronosticador>();

    //Post: Crea una competencia
    public Competencia() {}

    //Post: Agrega una ronda a la competencia
    public void agregar_ronda(Ronda ronda) {
        rondas.add(ronda);
    }

    //Post: Agrega un equipo a la competencia
    public void agregar_equipo(Equipo equipo) {
        equipos.add(equipo);
    }

    //Post: Agrega un pronosticador a la competencia
    public void agregar_pronosticador(Pronosticador pronosticador) {
        pronosticadores.add(pronosticador);
    }

    //Post: Devuelve la ronda buscada o null en caso de no encontrarla
    public Ronda obtener_ronda(int numero_ronda) {
        Ronda ronda_buscada = null;
        int iterador = 0;
        boolean encontrado = false;

        while (iterador < rondas.size() && !encontrado) {
            if (rondas.get(iterador).get_id_ronda() == numero_ronda) {
                ronda_buscada = rondas.get(iterador);
                encontrado = true;
            }

            iterador++;
        }

        return ronda_buscada;
    }

    //Post: Devuelve el equipo buscado o null en caso de no encontrarlo
    public Equipo obtener_equipo(String nombre_equipo_buscado) {
        Equipo equipo_buscado = null;
        int iterador = 0;
        boolean encontrado = false;

        while (iterador < equipos.size() && !encontrado) {
            if (equipos.get(iterador).get_id_equipo().equals(nombre_equipo_buscado)) {
                equipo_buscado = equipos.get(iterador);
                encontrado = true;
            }

            iterador++;
        }

        return equipo_buscado;
    }

    //Post: Devuelve el pronosticador buscado o null en caso de no encontrarlo
    public Pronosticador obtener_pronosticador(String nombre_pronosticador_buscado) {
        Pronosticador pronosticador_buscado = null;
        int iterador = 0;
        boolean encontrado = false;

        while (iterador < pronosticadores.size() && !encontrado) {
            if (pronosticadores.get(iterador).get_nombre().equals(nombre_pronosticador_buscado)) {
                pronosticador_buscado = pronosticadores.get(iterador);
                encontrado = true;
            }

            iterador++;
        }

        return pronosticador_buscado;
    }

    //Post: Calcula la cantidad de puntos de cada pronosticador
    private void calcular_puntos() {
        Pronosticador pronosticador_actual;

        for (int i = 0; i < pronosticadores.size(); i++) {
            pronosticador_actual = pronosticadores.get(i);

            for (int j = 0; j < pronosticador_actual.obtener_pronosticos().size(); j++) {
                Pronostico pronostico_actual = pronosticador_actual.obtener_pronosticos().get(j);
                Partido partido_pronosticado = rondas.get(pronostico_actual.get_id_ronda()).obtener_partido(pronostico_actual.get_id_partido());

                if(partido_pronosticado != null && pronostico_actual.get_pronostico() == partido_pronosticado.get_resultado_partido()) {
                    pronosticadores.get(i).set_puntaje(pronosticador_actual.puntaje + BONUS_ACIERTO);
                }
            }
        }
    }

    //Post: Imprime en pantalla los puntajes de todos los pronosticadores
    public void mostrar_puntajes() {
        calcular_puntos();

        for (int i = 0; i < pronosticadores.size(); i++) {
            System.out.println(pronosticadores.get(i).get_nombre() + ": " + pronosticadores.get(i).puntaje);
        }
    }

    //Post: Agregará el partido a la ronda correspondiente, si no existe la ronda, la crea
    public void agregar_partido(Partido partido){
        int id_ronda = partido.get_id_ronda();

        if(obtener_ronda(id_ronda) == null){
            Ronda nueva_ronda = new Ronda(id_ronda);
            nueva_ronda.agregar_partido(partido);
            agregar_ronda(nueva_ronda);
        } else {
            //! ACA NO SE SABE SI FUNCIONA.
            //! SI NO FUNCIONA, ES PORQUE EL METODO "OBTENER_RONDA" DEVUELVE UNA COPIA DE LA RONDA, NO UNA REFERENCIA.
            obtener_ronda(id_ronda).agregar_partido(partido);
        }
    }

    //Post: Agregará el pronóstico al pronosticador correspondiente, si no existe el pronosticador, lo crea
    public void agregar_pronostico(Pronostico pronostico, String nombre_pronosticador){
        if(obtener_pronosticador(nombre_pronosticador) == null){
            Pronosticador nuevo_pronosticador = new Pronosticador(nombre_pronosticador);
            nuevo_pronosticador.agregar_pronostico(pronostico);
            agregar_pronosticador(nuevo_pronosticador);
        } else {
            //! ACA NO SE SABE SI FUNCIONA.
            //! SI NO FUNCIONA, ES PORQUE EL METODO "OBTENER_PRONOSTICADOR" DEVUELVE UNA COPIA DEL PRONOSTICADOR, NO UNA REFERENCIA.
            obtener_pronosticador(nombre_pronosticador).agregar_pronostico(pronostico);
        }
    }
}

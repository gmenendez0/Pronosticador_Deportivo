package org.pronosticador;

import java.util.ArrayList;

public class Competencia {
    final int PUNTOS_POR_RONDA_ACERTADA = 5;


    private ArrayList<Ronda> rondas = new ArrayList<Ronda>();
    private ArrayList<Pronosticador> pronosticadores = new ArrayList<Pronosticador>();
    private final int puntos_por_acierto;

    //Post: Crea una competencia
    public Competencia(int puntos_por_acierto) {
        this.puntos_por_acierto = puntos_por_acierto;
    }

    //Post: Agrega una ronda a la competencia
    public void agregar_ronda(Ronda ronda) {
        rondas.add(ronda);
    }

    //Post: Agrega un pronosticador a la competencia
    public void agregar_pronosticador(Pronosticador pronosticador) {
        pronosticadores.add(pronosticador);
    }

    //Post: Devuelve la ronda buscada o null en caso de no encontrarla
    private Ronda obtener_ronda(int numero_ronda) {
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

    //Post: Devuelve el pronosticador buscado o null en caso de no encontrarlo
    private Pronosticador obtener_pronosticador(String nombre_pronosticador_buscado) {
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

    //Post: Devuelve true si el pronóstico coincide con el resultado del partido, false en caso contrario o en caso de que el partido pronosticado no exista.
    private boolean pronostico_acertado(Partido partido_pronosticado, Pronostico pronostico_actual){
        return ((partido_pronosticado != null) && (pronostico_actual.get_pronostico() == partido_pronosticado.get_resultado_partido()));
    }

    private void aumentar_puntos(Pronostico pronostico, Pronosticador pronosticador){
        pronosticador.set_puntaje(pronosticador.get_puntaje() + puntos_por_acierto);
        pronosticador.aumentar_aciertos();
        pronosticador.aumentar_aciertos_de_ronda(pronostico.get_id_ronda());
    }

    private void evaluar_pronosticos_pronosticador(Pronosticador pronosticador){
        for (int j = 0; j < pronosticador.obtener_pronosticos().size(); j++) {
            Pronostico pronostico_actual = pronosticador.obtener_pronosticos().get(j);
            Partido partido_pronosticado = this.obtener_ronda(pronostico_actual.get_id_ronda()).obtener_partido(pronostico_actual.get_id_partido());

            if(pronostico_acertado(partido_pronosticado, pronostico_actual)) aumentar_puntos(pronostico_actual, pronosticador);
        }
    }

    //Post: Actualiza la cantidad de puntos de cada pronosticador por pronosticos acertados.
    private void calcular_puntos_por_aciertos() {
        Pronosticador pronosticador_actual;

        for (int i = 0; i < pronosticadores.size(); i++) {
            pronosticador_actual = pronosticadores.get(i);
            pronosticador_actual.inicializar_aciertos_por_ronda(rondas);

            evaluar_pronosticos_pronosticador(pronosticador_actual);
        }
    }

    //Post: Actualiza la cantidad de puntos de cada pronosticador por rondas completas acertadas.
    private void calcular_puntos_por_rondas() {
        for (int i = 0; i < pronosticadores.size(); i++) {
            for (int j = 0; j < rondas.size(); j++) {
                if (pronosticadores.get(i).get_aciertos_de_ronda(rondas.get(j).get_id_ronda()) == rondas.get(j).get_cantidad_de_partidos()) {
                    pronosticadores.get(i).set_puntaje(pronosticadores.get(i).get_puntaje() + PUNTOS_POR_RONDA_ACERTADA);
                }
            }
        }
    }

    //Post: Imprime en pantalla los puntajes de todos los pronosticadores
    public void mostrar_puntajes() {
        calcular_puntos_por_aciertos();
        calcular_puntos_por_rondas();

        for (int i = 0; i < pronosticadores.size(); i++) {
            System.out.println(pronosticadores.get(i).get_nombre() + ": Puntos: " + pronosticadores.get(i).get_puntaje() + " Aciertos: " + pronosticadores.get(i).get_cantidad_de_aciertos());
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
            obtener_pronosticador(nombre_pronosticador).agregar_pronostico(pronostico);
        }
    }
}

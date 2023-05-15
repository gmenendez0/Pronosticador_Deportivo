package org.pronosticador;

import java.util.ArrayList;
import java.util.List;

public class Competencia {
    final int PUNTOS_POR_RONDA_ACERTADA = 5;

    private final List<Ronda> rondas = new ArrayList<>();
    private final List<Pronosticador> pronosticadores = new ArrayList<>();
    private final int puntosPorAcierto;

    //Post: Crea una competencia
    public Competencia(int puntosPorAcierto) {
        this.puntosPorAcierto = puntosPorAcierto;
    }

    //Post: Agrega una ronda a la competencia
    public void agregarRonda(Ronda ronda) {
        rondas.add(ronda);
    }

    //Post: Agrega un pronosticador a la competencia
    public void agregarPronosticador(Pronosticador pronosticador) {
        pronosticadores.add(pronosticador);
    }

    //Post: Devuelve la ronda buscada o null en caso de no encontrarla
    private Ronda obtenerRonda(int numeroRonda) {
        Ronda rondaBuscada = null;
        int iterador = 0;
        boolean encontrado = false;

        while (iterador < rondas.size() && !encontrado) {
            if (rondas.get(iterador).getIdRonda() == numeroRonda) {
                rondaBuscada = rondas.get(iterador);
                encontrado = true;
            }

            iterador++;
        }

        return rondaBuscada;
    }

    //Post: Devuelve el pronosticador buscado o null en caso de no encontrarlo
    private Pronosticador obtenerPronosticador(String nombrePronosticadorBuscado) {
        Pronosticador pronosticadorBuscado = null;
        int iterador = 0;
        boolean encontrado = false;

        while (iterador < pronosticadores.size() && !encontrado) {
            if (pronosticadores.get(iterador).getNombre().equals(nombrePronosticadorBuscado)) {
                pronosticadorBuscado = pronosticadores.get(iterador);
                encontrado = true;
            }

            iterador++;
        }

        return pronosticadorBuscado;
    }

    //Post: Devuelve true si el pronóstico coincide con el resultado del partido, false en caso contrario o en caso de que el partido pronosticado no exista.
    private boolean pronosticoAcertado(Partido partidoPronosticado, Pronostico pronosticoActual){
        return ((partidoPronosticado != null) && (pronosticoActual.pronostico() == partidoPronosticado.getResultadoPartido()));
    }

    //Post: Aumenta los puntos del pronosticador, junto con su cantidad de aciertos y los aciertos de la ronda correspondiente al pronostico recibido.
    private void aumentarPuntos(Pronostico pronostico, Pronosticador pronosticador){
        pronosticador.setPuntaje(pronosticador.getPuntaje() + puntosPorAcierto);
        pronosticador.aumentarAciertos();
        pronosticador.aumentarAciertosDeRonda(pronostico.idRonda());
    }

    //Post: Evalua si los pronosticos del pronosticador son acertados, en caso de serlos, aumenta sus puntos
    private void evaluarPronosticosPronosticador(Pronosticador pronosticador){
        for (int j = 0; j < pronosticador.obtenerPronosticos().size(); j++) {
            Pronostico pronosticoActual = pronosticador.obtenerPronosticos().get(j);
            Partido partidoPronosticado = this.obtenerRonda(pronosticoActual.idRonda()).obtenerPartido(pronosticoActual.idPartido());

            if(pronosticoAcertado(partidoPronosticado, pronosticoActual)) aumentarPuntos(pronosticoActual, pronosticador);
        }
    }

    //Post: Actualiza la cantidad de puntos de cada pronosticador por pronosticos acertados.
    private void calcularPuntosPorAciertos() {
        Pronosticador pronosticadorActual;

        for (Pronosticador pronosticador : pronosticadores) {
            pronosticadorActual = pronosticador;
            pronosticadorActual.inicializarAciertosPorRonda(rondas);

            evaluarPronosticosPronosticador(pronosticadorActual);
        }
    }

    //Post: Actualiza la cantidad de puntos de cada pronosticador por rondas completas acertadas.
    private void calcularPuntosPorRondas() {
        for (Pronosticador pronosticador : pronosticadores) {
            for (Ronda ronda : rondas) {
                if (pronosticador.getAciertosDeRonda(ronda.getIdRonda()) == ronda.getCantidadDePartidos()) {
                    pronosticador.setPuntaje(pronosticador.getPuntaje() + PUNTOS_POR_RONDA_ACERTADA);
                }
            }
        }
    }

    //Post: Imprime en pantalla los puntajes de todos los pronosticadores
    public void mostrarPuntajes() {
        calcularPuntosPorAciertos();
        calcularPuntosPorRondas();

        for (Pronosticador pronosticador : pronosticadores) {
            System.out.println(pronosticador.getNombre() + ": Puntos: " + pronosticador.getPuntaje() + " Aciertos: " + pronosticador.getCantidadDeAciertos());
        }
    }

    //Post: Agregará el partido a la ronda correspondiente, si no existe la ronda, la crea
    public void agregarPartido(Partido partido){
        int idRonda = partido.getIdRonda();

        if(obtenerRonda(idRonda) == null){
            Ronda nuevaRonda = new Ronda(idRonda);
            nuevaRonda.agregarPartido(partido);
            agregarRonda(nuevaRonda);
        } else {
            obtenerRonda(idRonda).agregarPartido(partido);
        }
    }

    //Pre: No puede haber dos pronosticadores con el mismo nombre. Debe haber algo para diferenciarlos en dicho nombre.
    //Post: Agregará el pronóstico al pronosticador correspondiente, si no existe el pronosticador, lo crea
    public void agregarPronostico(Pronostico pronostico, String nombrePronosticador){
        if(obtenerPronosticador(nombrePronosticador) == null){
            Pronosticador nuevoPronosticador = new Pronosticador(nombrePronosticador);
            nuevoPronosticador.agregarPronostico(pronostico);
            agregarPronosticador(nuevoPronosticador);
        } else {
            obtenerPronosticador(nombrePronosticador).agregarPronostico(pronostico);
        }
    }
}

package org.pronosticador;

import java.util.ArrayList;

public class Pronosticador {
    final int PUNTAJE_INICIAL = 0;
    final int CANTIDAD_DE_ACIERTOS_INICIAL = 0;
    final int ERROR = -1;

    private final String nombre;
    private ArrayList<Pronostico> pronosticos = new ArrayList<Pronostico>();
    private int puntaje;
    private int cantidad_de_aciertos;
    private ArrayList<AciertoRonda> aciertos_por_ronda = new ArrayList<AciertoRonda>();

    //Post: Crea un pronosticador con los datos recibidos
    public Pronosticador(String nombre) {
        this.nombre = nombre;
        this.puntaje = PUNTAJE_INICIAL;
        cantidad_de_aciertos = CANTIDAD_DE_ACIERTOS_INICIAL;
    }

    //Post: Agrega el pronóstico recibido al array de pronosticos
    public void agregar_pronostico(Pronostico pronostico) {
        pronosticos.add(pronostico);
    }

    //Post: Devuelve el array con todos los pronosticos del pronosticador
    public ArrayList<Pronostico> obtener_pronosticos(){
        return pronosticos;
    }

    //Post: Setea el puntaje del pronosticador
    public void set_puntaje(int puntaje){
        this.puntaje = puntaje;
    }

    //Post: Devuelve el puntaje del pronosticador
    public int get_puntaje(){
        return puntaje;
    }

    //Post: Devuelve el nombre del pronosticador
    public String get_nombre(){
        return nombre;
    }

    //Post: Aumenta la cantidad de aciertos del pronosticador.
    public void aumentar_aciertos() {
        cantidad_de_aciertos++;
    }

    //Post: Devuelve la cantidad de aciertos del pronosticador
    public int get_cantidad_de_aciertos() {
        return cantidad_de_aciertos;
    }

    //Pre: No puede haber rondas de id <= 0.
    //Post: Inicializará en el vector de aciertos_por_ronda, las rondas que esten hayan sido cargadas al sistema.
    public void inicializar_aciertos_por_ronda(ArrayList<Ronda> rondas) {
        for (int i = 0; i < rondas.size(); i++) {
            var contador_acierto_ronda = new AciertoRonda(rondas.get(i).get_id_ronda());
            aciertos_por_ronda.add(contador_acierto_ronda);
        }
    }

    //Pre: Debe recibir un número de ronda de id mayor a 0 y que este registrado en el sistema de rondas del sistema.
    //Post: Aumenta en 1 la cantidad de aciertos de la ronda recibida.
    public void aumentar_aciertos_de_ronda(int numero_de_ronda){
        for (int i = 0; i < aciertos_por_ronda.size(); i++) {
            if (aciertos_por_ronda.get(i).get_id_ronda() == numero_de_ronda) {
                aciertos_por_ronda.get(i).aumentar_aciertos();
            }
        }
    }

    //Pre: Debe recibir un número de ronda de id mayor a 0 y que este registrado en el sistema de rondas del sistema.
    //Post: Devuelve la cantidad de aciertos del pronosticador en la ronda recibida o -1 en caso de recibir ronda erronea.
    public int get_aciertos_de_ronda(int numero_de_ronda) {
        int cantidad_de_aciertos = ERROR;

        for (int i = 0; i < aciertos_por_ronda.size(); i++) {
            if (aciertos_por_ronda.get(i).get_id_ronda() == numero_de_ronda) {
                cantidad_de_aciertos = aciertos_por_ronda.get(i).get_aciertos();
            }
        }

        return cantidad_de_aciertos;
    }
}

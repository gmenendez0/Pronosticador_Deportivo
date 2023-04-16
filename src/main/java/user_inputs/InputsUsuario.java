package user_inputs;

import java.util.Scanner;

public class InputsUsuario {

    //Post: Pregunta al usuario la cantidad de puntos por acierto y devuelve el valor ingresado.
    public int preguntar_puntos_por_acierto(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese la cantidad de puntos dados por cada pronostico acertado: ");
        return scanner.nextInt();
    }
}

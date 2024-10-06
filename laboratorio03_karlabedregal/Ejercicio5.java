/* Autor: Karla Bedregal Coaguila 
Solucionar la Actividad 5 de la Práctica 1 pero usando arreglo de objetos */
package laboratorio03_karlabedregal;
import java.util.Random;
public class Ejercicio5 {
    public static void main(String[] args) {
        Random rand = new Random();
        // Número de soldados random para los equipos
        int numeroSoldadosA = rand.nextInt(5) + 1;
        int numeroSoldadosB = rand.nextInt(5) + 1;
        // Arreglos para los soldados
        Soldado1[] soldadosA = new Soldado1[numeroSoldadosA];
        Soldado1[] soldadosB = new Soldado1[numeroSoldadosB];
        // Asignar nombres a los soldados del equipo A
        for (int i = 0; i < numeroSoldadosA; i++) {
            soldadosA[i] = new Soldado1("SoldadoA" + (i + 1));
        }
        // Asignar nombres a los soldados del equipo B
        for (int j = 0; j < numeroSoldadosB; j++) {
            soldadosB[j] = new Soldado1("SoldadoB" + (j + 1));
        }
        // Métodos
        System.out.println("\nSoldados del ejército A: " + numeroSoldadosA);
        mostrarEjercito(soldadosA);
        System.out.println("\nSoldados del ejército B: " + numeroSoldadosB);
        mostrarEjercito(soldadosB);
        ejercitoGanador(numeroSoldadosA, numeroSoldadosB);
    }
    public static void mostrarEjercito(Soldado1[] array) {
        for (Soldado1 soldado : array) {
            System.out.println("Nombre de los soldados: " + soldado.getNombre());
        }
    }
    public static void ejercitoGanador(int a, int b) {
        if (a == b) {
            System.out.println("\nEMPATE - No hay ganador");
        } else if (a < b) {
            System.out.println("\nGanador - EJERCITO B con " + b + " soldados");
        } else {
            System.out.println("\nGanador - EJERCITO A con " + a + " soldados");
        }
    }
}
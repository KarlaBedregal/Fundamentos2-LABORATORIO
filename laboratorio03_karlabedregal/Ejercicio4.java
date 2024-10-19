/* Autor: Karla Bedregal Coaguila 
Solucionar la Actividad 4 de la Práctica 1 pero usando arreglo de objetos */
package laboratorio03_karlabedregal;
import java.util.Scanner;
public class Ejercicio4 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        // Arreglo de soldados
        Soldado[] soldados = new Soldado[5];

        // Ingresar nombre y nivel de vida
        for (int i = 0; i < soldados.length; i++) {
            System.out.println("Ingrese el nombre del soldado " + (i + 1) + ": ");
            String nombre = sc.nextLine();
            
            System.out.println("Ingrese el nivel de vida del soldado " + (i + 1) + ": ");
            int nivelVida = sc.nextInt();
            sc.nextLine(); // Limpiar el buffer

            // Crear el objeto Soldado y almacenarlo en el arreglo
            soldados[i] = new Soldado(nombre, nivelVida);
        }

        // Imprimir
        System.out.println("\nInformación de los soldados:");
        for (Soldado soldado : soldados) {
            System.out.println(soldado); // Llama al método toString
        }
    }
}
/* Autor: Karla Bedregal Coaguila 
 Analice, complete y pruebe el Código de la clase DemoBatalla */
package laboratorio03_karlabedregal;
import java.util.*;
public class DemoBatalla {
    public static void main(String [] args){
        Nave [] misNaves = new Nave[10];
        Scanner sc = new Scanner(System.in);
        String nomb, col;
        int fil, punt;
        boolean est;

        for (int i = 0; i < misNaves.length; i++) {
            System.out.println("Nave " + (i+1));
            System.out.print("Nombre: ");
            nomb = sc.next();
            System.out.println("Fila ");
            fil = sc.nextInt();
            System.out.print("Columna: ");
            col = sc.next();
            System.out.print("Estado: ");
            est = sc.nextBoolean();
            System.out.print("Puntos: ");
            punt = sc.nextInt();

            misNaves[i] = new Nave(); //Se crea un objeto Nave 

            misNaves[i].setNombre(nomb);
            misNaves[i].setFila(fil);
            misNaves[i].setColumna(col);
            misNaves[i].setEstado(est);
            misNaves[i].setPuntos(punt);
        }

        System.out.println("\nNaves creadas:");
        mostrarNaves(misNaves);
        mostrarPorNombre(misNaves);
        mostrarPorPuntos(misNaves);
        System.out.println("\nNave con mayor número de puntos: " + mostrarMayorPuntos(misNaves));
        Nave[] flotaDesordenada = desordenarNaves(misNaves);
        System.out.println("Arreglo desordenado:");
        mostrarNaves(flotaDesordenada);
        }
        //Método para mostrar todas las naves
    public static void mostrarNaves(Nave [] flota){
        System.out.println("Naves:");
        //Usamos un for-each para imprimir
        for (Nave c : flota) {
            System.out.println(c);
        }
    }

    //Método para mostrar todas las naves de un nombre que se pide por teclado
    public static void mostrarPorNombre(Nave [] flota){
        Scanner sc = new Scanner (System.in);
        System.out.println("Ingrese el nombre: ");
        String nombreIngresado = sc.next();
        //Un for para mostrar las filas, columnas, estado y puntos del nombre
        for (int i = 0; i < flota.length; i++) {
            if (nombreIngresado.equalsIgnoreCase(flota[i].getNombre())) {
                System.out.println(flota[i].getFila());
                System.out.println(flota[i].getColumna());
                System.out.println(flota[i].getEstado());
                System.out.println(flota[i].getPuntos());
            }
        }
    }
    //Método para mostrar todas las naves con un número de puntos inferior o igual
    //al número de puntos que se pide por teclado
    public static void mostrarPorPuntos(Nave [] flota){
        Scanner sc = new Scanner (System.in);
        System.out.println("Ingrese el número de puntos: ");
        int numPuntos = sc.nextInt();
        for (int i = 0; i < flota.length; i++) {
            if (flota[i].getPuntos() <= numPuntos) {
                System.out.println(flota[i].getNombre());
            }
        }
    
    }
    //Método que devuelve la Nave con mayor número de Puntos
    public static Nave mostrarMayorPuntos(Nave [] flota){
        Scanner sc = new Scanner (System.in);
        Nave mayor = flota[0]; 
        for (int i = 1; i < flota.length; i++) {
            if (flota[i].getPuntos() > mayor.getPuntos()) {
                mayor = flota[i]; // Si encontramos una nave con más puntos, actualizamos "mayor"
            }
        }
        return mayor; // Devolvemos la nave con más puntos
    }
    //Crear un método que devuelva un nuevo arreglo de objetos con todos los objetos previamente 
    // ingresados pero aleatoriamente desordenados
    public static Nave[] desordenarNaves(Nave[] flota) {
        Nave[] flotaDesordenada = Arrays.copyOf(flota, flota.length); // Copiamos el arreglo original 
        Random rand = new Random();

        for (int i = flotaDesordenada.length - 1; i > 0; i--) {
            int j = rand.nextInt(i + 1); // Generamos un random
        // Intercambiamos flotaDesordenada[i] con flotaDesordenada[j]
            Nave des = flotaDesordenada[i];
            flotaDesordenada[i] = flotaDesordenada[j];
            flotaDesordenada[j] = des;
        }
        return flotaDesordenada; // Arreglo desordenado
    }
}

/* Autor: Karla Miluska Bedregal Coaguila
Laboratorio 07 */
package laboratorio7_karlabedregal;
import java.util.*;
public class VideoJuego4 {
    public static void main(String[] args) {
        Scanner sc = new Scanner (System.in);
 
        int filas = 10;
        int columnas = 10;
        
        while (true) {
            // Inicializamos el tablero con un ArrayList bidimensional
            ArrayList<ArrayList<Soldado>> tablero = inicializarTablero(filas, columnas);

            // Creamos dos ejércitos (1 y 2)
            ArrayList<Soldado> ejercito1 = crearEjercito(1, tablero, filas, columnas);
            ArrayList<Soldado> ejercito2 = crearEjercito(2, tablero, filas, columnas);

            // Mostramos el tablero
            mostrarTablero(tablero, filas, columnas);

            // Mostramos vida y promedio del ejército 1
            System.out.println("\nVida y promedio del Ejército 1:");
            mostrarVida_Promedio_Ejercito(ejercito1, "Ejército 1");

            // Mostramos vida y promedio del ejército 2
            System.out.println("\nVida y promedio del Ejército 2:");
            mostrarVida_Promedio_Ejercito(ejercito2, "Ejército 2");   

            // Mostramos soldados en orden de creación:
            System.out.println("\nSoldados del Ejército 1 y en orden de creación:");
            mostrarSoldados(ejercito1);

            System.out.println("\nSoldados del Ejército 2 en orden de creación:");
            mostrarSoldados(ejercito2);

            // Ordenamos y mostramos el ranking Ejército1 por Burbuja
            System.out.println("\nRanking del Ejército 1 (Burbuja):");
            ordenarBurbuja(ejercito1);
            mostrarRanking(ejercito1);

            // Ordenamos y mostramos el ranking Ejército2 por Inserción
            System.out.println("\nRanking del Ejército 2 (Inserción):");
            ordenarInsercion(ejercito2);
            mostrarRanking(ejercito2);

            // Determinamos ejército ganador a través del promedio de sus puntos de Vida.
            determinarGanador(ejercito1, ejercito2);
            
            System.out.println("\n¿Desea salir? (s/n)");
            String resp = sc.next();
            if (resp.equalsIgnoreCase("s")) {
                break;
            }
        }    
    }
    // Inicializar el tablero bidimensional como ArrayList de ArrayLists
    public static ArrayList<ArrayList<Soldado>> inicializarTablero(int filas, int columnas) {
        ArrayList<ArrayList<Soldado>> tablero = new ArrayList<>();
        for (int i = 0; i < filas; i++) {
            tablero.add(new ArrayList<Soldado>());
            for (int j = 0; j < columnas; j++) {
                // Consideramos todas las posiciones como vacías
                tablero.get(i).add(null); 
            }
        }
        return tablero;
    }

    // Método para crear un ejército con soldados aleatorios en el tablero
    public static ArrayList<Soldado> crearEjercito(int equipoEjercito, 
            ArrayList<ArrayList<Soldado>> tablero, int filas, int columnas) {
        
        ArrayList<Soldado> ejercito = new ArrayList<Soldado>();
        Random random = new Random();
        int numSoldados = random.nextInt(10) + 1; 

        for (int i = 0; i < numSoldados; i++) {
            int fila, columna;
            
            // Verificamos si la posición ya está ocupada  
            do {
                fila = random.nextInt(filas);
                columna = random.nextInt(columnas);
            } while (tablero.get(fila).get(columna) != null); 

            // Creamos el soldado y lo agregamos al tablero
            String nombre = "Soldado" + i + "X" + equipoEjercito;
            // Generar random para puntos de vida
            int puntosVida = random.nextInt(5) + 1; 
            Soldado soldado = new Soldado(nombre, puntosVida, fila, columna, equipoEjercito); 
            tablero.get(fila).set(columna, soldado);
            ejercito.add(soldado);
        }

        return ejercito;
    }

    // Método para mostrar el tablero con los soldados creados
    public static void mostrarTablero(ArrayList<ArrayList<Soldado>> tablero, int filas, int columnas){
        System.out.println("Tablero:");
        // Imprimir las letras de las columnas (A-J)
        System.out.print("        "); // Espacio para la numeración de las filas
        for (int j = 0; j < columnas; j++) {
            char letraColumna = (char) ('A' + j); // Convertir índice en letra
            System.out.print(letraColumna + "          ");
        }
        System.out.println();

        for (int i = 0; i < filas; i++) {
        System.out.print(i + 1 + " ");
            for (int j = 0; j < columnas; j++) {
                Soldado soldado = tablero.get(i).get(j);
                if (soldado == null) {
                    System.out.print("|__________");  // Casilla vacía
                } else {
                    String nombre = soldado.getNombre();

                    while (nombre.length() < 7) {
                        nombre += "_";
                    }

                    System.out.print("|" + nombre);
                }
            }
        System.out.println();
        }
    }

    public static void mostrarSoldados(ArrayList<Soldado> soldados) {
        for (Soldado soldado : soldados) {
            System.out.println(soldado);
        }
    }
    public static int calcularTotalVida(ArrayList<Soldado> soldados) {
        int totalVida = 0;
        for (Soldado soldado : soldados) {
            totalVida += soldado.getPuntosVida();
        }
        return totalVida;
    }

    // Método para mostrar la vida y el promedio de ejército 
    public static void mostrarVida_Promedio_Ejercito(ArrayList<Soldado> ejercito, 
            String nombreEjercito) {
        Soldado maxVida = ejercito.get(0);
        int sumaVida = 0;

        for (Soldado soldado : ejercito) {
            sumaVida += soldado.getPuntosVida();
            if (soldado.getPuntosVida() > maxVida.getPuntosVida()) {
                maxVida = soldado;
            }
        }

        double promedioVida = (double) sumaVida / ejercito.size();

        System.out.println("Soldado con mayor vida en " + nombreEjercito + ": " + maxVida);
        System.out.println("Promedio de vida en " + nombreEjercito + ": " + promedioVida);
    }

    // Método para ordenar los soldados de un ejército usando Ordenamiento por Burbuja
    public static void ordenarBurbuja(ArrayList<Soldado> ejercito) {
        int n = ejercito.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (ejercito.get(j).getPuntosVida() < ejercito.get(j + 1).getPuntosVida()) {
                    // Intercambio manual
                    Soldado temp = ejercito.get(j);
                    ejercito.set(j, ejercito.get(j + 1));
                    ejercito.set(j + 1, temp);
                }
            }
        }
    }
    
    // Método para ordenar los soldados de un ejército usando Ordenamiento por Insercion 
    public static void ordenarInsercion(ArrayList<Soldado> soldados) {
        int n = soldados.size();
        for (int i = 1; i < n; i++) {
            Soldado key = soldados.get(i);
            int j = i - 1;

            // Mover los soldados que son menores que la clave
            while (j >= 0 && soldados.get(j).getPuntosVida() < key.getPuntosVida()) {
                soldados.set(j + 1, soldados.get(j));
                j--;
            }
            soldados.set(j + 1, key);
        }
    }

    // Método para mostrar el ranking de los soldados de un ejército
    public static void mostrarRanking(ArrayList<Soldado> ejercito) {
        for (Soldado soldado : ejercito) {
            System.out.println(soldado);
        }
    }

    // Método pata encontrar el ejército ganador (a través del total de vida de sus soldados)
    public static void determinarGanador(ArrayList<Soldado> ejercito1, ArrayList<Soldado> ejercito2) {
        int totalVidaEjercito1 = calcularTotalVida(ejercito1);
        int totalVidaEjercito2 = calcularTotalVida(ejercito2);
        System.out.println("\nEl ganador es aquel equipo que tiene más puntos de vida");
        
        if (totalVidaEjercito1 > totalVidaEjercito2) {
            System.out.println("El Ejército 1 gana con " + totalVidaEjercito1 + " puntos totales de vida");
        } else if (totalVidaEjercito2 > totalVidaEjercito1) {
            System.out.println("El Ejército 2 gana con " + totalVidaEjercito2 + " puntos totales de vida");
        } else {
            System.out.println("Es un empate con un total de puntos de vida de: " + totalVidaEjercito1);
        }
    }   
} 
/* Autor: Karla Miluska Bedregal Coaguila
Laboratorio 08 */
package laboratorio8;
import java.util.*;
public class VideoJuego5 {
    public static void main(String[] args) {
        Scanner sc = new Scanner (System.in);
 
        int filas = 10;
        int columnas = 10;
        
        while (true) {
            // Tablero inicializado 
            HashMap<String, Soldado> tablero = new HashMap<>();

            // Creamos dos ejércitos
            HashMap<String, Soldado> ejercito1 = crearEjercito(1, tablero, filas, columnas);
            HashMap<String, Soldado> ejercito2 = crearEjercito(2, tablero, filas, columnas);

            // Mostrar tablero
            mostrarTablero(tablero, filas, columnas);

            // Vida y promedio de cada ejército
            System.out.println("\nEjército 1:");
            mostrarVidaPromedio(ejercito1);

            System.out.println("\nEjército 2:");
            mostrarVidaPromedio(ejercito2);   

            // Mostramos soldados en orden de creación:
            System.out.println("\nSoldados del Ejército 1 y en orden de creación:");
            mostrarSoldados(ejercito1);

            System.out.println("\nSoldados del Ejército 2 en orden de creación:");
            mostrarSoldados(ejercito2);

            // Ordenamos y mostramos el ranking Ejército1 por Burbuja
            System.out.println("\nRanking del Ejército 1 (Burbuja):");
            mostrarRanking(ordenarBurbuja(ejercito1));

            // Ordenamos y mostramos el ranking Ejército2 por Inserción
            System.out.println("\nRanking del Ejército 2 (Inserción):");
            mostrarRanking(ordenarInsercion(ejercito2));

            // Determinamos ejército ganador a través del promedio de sus puntos de Vida.
            determinarGanador(ejercito1, ejercito2);
            
            System.out.println("\n¿Desea salir? (s/n)");
            String resp = sc.next();
            if (resp.equalsIgnoreCase("s")) {
                break;
            }
        }    
    }
    
    // Método para crear un ejército con soldados aleatorios en el tablero
    public static HashMap<String, Soldado> crearEjercito(int equipoEjercito, 
        HashMap<String, Soldado> tablero, int filas, int columnas) {
    
        HashMap<String, Soldado> ejercito = new HashMap<>();
        Random random = new Random();
        int numSoldados = random.nextInt(10) + 1; 

        for (int i = 0; i < numSoldados; i++) {
            int fila, columna;
            String posicion;

            // Verificamos si la posición ya está ocupada  
            do {
                fila = random.nextInt(filas);
                columna = random.nextInt(columnas);
                posicion = fila + "," + columna; 
            } while (tablero.containsKey(posicion)); 

            // Creamos el soldado
            String nombre = "Soldado" + i + "X" + equipoEjercito;
            int puntosVida = random.nextInt(5) + 1; 
            Soldado soldado = new Soldado(nombre, puntosVida, fila, columna, equipoEjercito); 

            // Agregamos al tablero y al ejército
            tablero.put(posicion, soldado);
            ejercito.put(posicion, soldado);
        }
        return ejercito;
    }
    
    // Método para mostrar el tablero con los soldados creados
    public static void mostrarTablero(HashMap<String, Soldado> tablero, int filas, int columnas){
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
                String posicion = i + "," + j;
                if (tablero.containsKey(posicion)) {
                    Soldado soldado = tablero.get(posicion);
                    String nombre = soldado.getNombre();
                    
                    while (nombre.length() < 7) {
                        nombre += "_";
                    }

                    System.out.print("|" + nombre);
                } else {
                    System.out.print("|__________");
                }
            }
            System.out.println();
        }
    }

    // Método para mostrar los soldados
    public static void mostrarSoldados(HashMap<String, Soldado> ejercito) {
        for (Soldado soldado : ejercito.values()) {
            System.out.println(soldado);
        }
    }
    
    // Método para mostrar la vida y el promedio de ejército 
    public static void mostrarVidaPromedio(HashMap<String, Soldado> ejercito) {
        int totalVida = 0;
        Soldado maxVida = null;

        for (Soldado soldado : ejercito.values()) {
            totalVida += soldado.getPuntosVida();
            if (maxVida == null || soldado.getPuntosVida() > maxVida.getPuntosVida()) {
                maxVida = soldado;
            }
        }

        double promedioVida = (double) totalVida / ejercito.size();
        System.out.println("Soldado con mayor vida: " + maxVida);
        System.out.println("Promedio de vida: " + promedioVida);
    }

    // Método para ordenar los soldados de un ejército usando Ordenamiento por Burbuja
    public static ArrayList<Soldado> ordenarBurbuja(HashMap<String, Soldado> ejercito) {
        ArrayList<Soldado> soldados = new ArrayList<>(ejercito.values());
        int n = soldados.size();

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {

                if (soldados.get(j).getPuntosVida() < soldados.get(j + 1).getPuntosVida()) {
                    Soldado temp = soldados.get(j);
                    soldados.set(j, soldados.get(j + 1));
                    soldados.set(j + 1, temp);
                }
            }
        }

        return soldados;
    }

    
    // Método para ordenar los soldados de un ejército usando Ordenamiento por Insercion 
    public static ArrayList<Soldado> ordenarInsercion(HashMap<String, Soldado> ejercito) {
        ArrayList<Soldado> soldados = new ArrayList<>(ejercito.values());
        int n = soldados.size();

        for (int i = 1; i < n; i++) {
            Soldado key = soldados.get(i);
            int j = i - 1;

            while (j >= 0 && soldados.get(j).getPuntosVida() < key.getPuntosVida()) {
                soldados.set(j + 1, soldados.get(j));
                j--;
            }
            soldados.set(j + 1, key);
        }

        return soldados;
    }


    // Método para mostrar el ranking de los soldados de un ejército
    public static void mostrarRanking(ArrayList<Soldado> soldados) {
        for (Soldado soldado : soldados) {
            System.out.println(soldado);
        }
    }

    // Método pata encontrar el ejército ganador (a través del total de vida de sus soldados)
    public static void determinarGanador(HashMap<String, Soldado> ejercito1, HashMap<String, Soldado> ejercito2) {
        int vidaEjercito1 = ejercito1.values().stream().mapToInt(Soldado::getPuntosVida).sum();
        int vidaEjercito2 = ejercito2.values().stream().mapToInt(Soldado::getPuntosVida).sum();

        System.out.println("\nEjército ganador:");
        if (vidaEjercito1 > vidaEjercito2) {
            System.out.println("Ejército 1 gana con " + vidaEjercito1 + " puntos de vida.");
        } else if (vidaEjercito2 > vidaEjercito1) {
            System.out.println("Ejército 2 gana con " + vidaEjercito2 + " puntos de vida.");
        } else {
            System.out.println("Empate con " + vidaEjercito1 + " puntos de vida.");
        }
    }
}
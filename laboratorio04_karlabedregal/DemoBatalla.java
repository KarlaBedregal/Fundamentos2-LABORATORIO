/* Autor: Karla Bedregal Coaguila 
Laboratorio 04 - Ejercicio 01 
Usted podrá reutilizar las dos clases Nave.java y DemoBatalla.java. creadas en 
Laboratorio 3.  Completar el código de la clase DemoBatalla*/
package laboratorio04_karlabedregal;
import java.util.*;
public class DemoBatalla {
    public static void main(String [] args){
        Nave [] misNaves = new Nave[8];
        Scanner sc = new Scanner(System.in);
        String nomb, col;
        int fil, punt;
        boolean est;

        for (int i = 0; i < misNaves.length; i++) {
            System.out.println("Nave " + (i+1));
            System.out.print("Nombre: ");
            nomb = sc.next();
            System.out.print("Fila: ");
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
        System.out.println("\nNave con mayor número de puntos: " + 
                mostrarMayorPuntos(misNaves));
        
        //leer un nombre y mostrar los datos de la nave con dicho nombre, 
        //mensaje de "no encontrado" en caso contrario
        System.out.println("\nIngrese el nombre de la nave a buscar: ");
        String nombre= sc.next();
        int pos = busquedaLinealNombre(misNaves, nombre);
        if (pos!= -1)
            System.out.println("Nave encontrada en la posición " + pos);
        else 
            System.out.println("Nave no encontrada");
        
        System.out.println("\nOrdenamiento burbuja por puntos: ");
        ordenarPorPuntosBurbuja(misNaves);
        mostrarNaves(misNaves);
        
        System.out.println("\nOrdenamiento burbuja por nombre: ");
        ordenarPorNombreBurbuja(misNaves);
        mostrarNaves(misNaves);
        
        //mostrar los datos de la nave con dicho nombre, mensaje de 
        //“no encontrado” en caso contrario
        pos=busquedaBinariaNombre(misNaves,nombre);
        if (pos!=-1) 
            System.out.println("Nave encontrada en la posición " + pos);
        else 
            System.out.println("Nave no enontrada");
        
        System.out.println("\nOrdenamiento selección por puntos: ");
        ordenarPorPuntosSeleccion(misNaves);
        mostrarNaves(misNaves);
        
        System.out.println("\nOrdenamiento selección por nombre: ");
        ordenarPorNombreSeleccion(misNaves);
        mostrarNaves(misNaves);
        
        System.out.println("\nOrdenamiento inserción por puntos: ");
        ordenarPorPuntosInsercion(misNaves);
        mostrarNaves(misNaves);
        
        System.out.println("\nOrdenamiento inserción por nombre: ");
        ordenarPorNombreInsercion(misNaves);
        mostrarNaves(misNaves);
    }
        
    //Método para mostrar todas las naves
    public static void mostrarNaves(Nave [] flota){
        //Usamos un for-each para imprimir
        for (Nave c : flota) {
            System.out.println(c);
        }
    }
    
    //Método para mostrar las naves de un nombre ingresado
    public static void mostrarPorNombre(Nave [] flota){
        Scanner sc = new Scanner (System.in);
        System.out.println("\nIngrese un nombre: ");
        String name = sc.next();
        for (Nave c: flota) {
            if (c.getNombre().equals(name))
                System.out.println(c);
        }
    }    
    
    //Método paara mostrar las naves con los puntos inferiores o iguales a 
    //los ingresados
    public static void mostrarPorPuntos(Nave [] flota){
        Scanner sc = new Scanner (System.in);
        System.out.println("\nIngrese los puntos de referencia: ");
        int point = sc.nextInt();
        for (Nave c: flota) {
            if (c.getPuntos() <= point)
                System.out.println(c); 
        }
    }
    
    //Método que devuelve la Nave con mayor número de Puntos
    public static Nave mostrarMayorPuntos(Nave [] flota){
        Nave mayor = flota[0]; 
        for (int i = 1; i < flota.length; i++) {
            if (flota[i].getPuntos() > mayor.getPuntos()) {
                mayor = flota[i]; // Si hay una nave con más puntos,actualizamos
            }
        }
        return mayor; // Nave con más puntos
    }
    
    //Método que busca la nave con un nombre ingresado (Búsqueda lineal)
    public static int busquedaLinealNombre(Nave[] flota, String nombre) {
        for (int i = 0; i < flota.length; i++) {
            if (flota[i].getNombre().equals(nombre))
                return i;    
        }
        return -1;
    }
    
    //Método que ordena los puntos de menor a mayor (Por burbuja)
    public static void ordenarPorPuntosBurbuja(Nave[] flota) {
        for (int i = 0; i < flota.length; i++) {
            for (int j = 0; j < flota.length - 1; j++) {
                if (flota[j].getPuntos() > flota[j + 1].getPuntos())
                    intercambiar(flota, j, j+1);
                }    
        } 
    }
    
    //Método que ordena los nombres de A a Z (Por burbuja)
    public static void ordenarPorNombreBurbuja(Nave[] flota) {
        for (int i = 0; i < flota.length - 1; i++) { 
            for (int j = 0; j < flota.length - 1 - i; j++) { 
                if (flota[j].getNombre().compareTo(flota[j + 1].getNombre()) > 0) {
                    intercambiar(flota, j, j + 1);
                }    
            } 
        } 
    }
    //Método que intercambia los nombres y  puntos de los anteriores métodos
    private static void intercambiar(Nave[] flota, int i, int j) {
        String temp = flota[i].getNombre(); 
        flota[i].setNombre(flota[j].getNombre()); 
        flota[j].setNombre(temp); 
    }
    
    //Método que busca la nave con un nombre ingresado (Búsqueda binaria)
    public static int busquedaBinariaNombre(Nave[] flota, String nombre) {
        int izquierda = 0;
        int derecha = flota.length - 1;
        while (izquierda <= derecha) {
            int medio = (izquierda + derecha) / 2;
            String nombreMedio = flota[medio].getNombre();
            if (nombreMedio.equals(nombre)) {
                return medio; 
            } else if (nombreMedio.compareTo(nombre) < 0) {
                izquierda = medio + 1; 
            } else {
                derecha = medio - 1; 
            }
        }
        return -1; 
    }
    
    //Método que ordena los puntos de menro a mayor (Por selección)
    public static void ordenarPorPuntosSeleccion(Nave[] flota) {
        for (int i = 0; i < flota.length - 1; i++) {
            int indiceMenor = i;
            for (int j = i + 1; j < flota.length; j++) {
                if (flota[j].getPuntos() < flota[indiceMenor].getPuntos()) {
                    indiceMenor = j; 
                }
            }
            if (indiceMenor != i) {
                Nave temp = flota[i];
                flota[i] = flota[indiceMenor];
                flota[indiceMenor] = temp;
            }
        }
    }
    
    //Método que ordena los nombres de A a Z (Por selección)
    public static void ordenarPorNombreSeleccion(Nave[] flota) {
        for (int i = 0; i < flota.length - 1; i++) {
            int indiceMenor = i;
            for (int j = i + 1; j < flota.length; j++) {
                if (flota[j].getNombre().compareTo(flota[indiceMenor].getNombre()) < 0) {
                    indiceMenor = j; 
                }
            }
            if (indiceMenor != i) {
                Nave temp = flota[i];
                flota[i] = flota[indiceMenor];
                flota[indiceMenor] = temp;
            }
        }
    }
    
    //Método que ordena los puntos de menor a mayor (Por inserción)
    public static void ordenarPorPuntosInsercion(Nave[] flota) {
        for (int i = 1; i < flota.length; i++) {
            Nave clave = flota[i]; 
            int j = i - 1;
            while (j >= 0 && flota[j].getPuntos() > clave.getPuntos()) {
                flota[j + 1] = flota[j]; 
                j--;
            }
            flota[j + 1] = clave;
        }
    }
    
    //Método que ordena los nombres de A a Z (Por inserción)
    public static void ordenarPorNombreInsercion(Nave[] flota) {
        for (int i = 1; i < flota.length; i++) {
            Nave clave = flota[i]; 
            int j = i - 1;
            while (j >= 0 && flota[j].getNombre().compareTo(clave.getNombre()) > 0) {
                flota[j + 1] = flota[j]; 
                j--;
            }
            flota[j + 1] = clave;
        }
    }
}

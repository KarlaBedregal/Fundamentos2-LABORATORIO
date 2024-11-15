/* Autor: Karla Miluska Bedregal Coaguila
Laboratorio 07 */
package laboratorio7_karlabedregal;
public class Soldado {
    private String nombre;
    private int puntosVida;
    private int fila;
    private int columna;
    private int ejercito; 

    // Constructor
    public Soldado(String nombre, int puntosVida, int fila, int columna, int ejercito) {
        this.nombre = nombre;
        this.puntosVida = puntosVida;
        this.fila = fila;
        this.columna = columna;
        this.ejercito = ejercito; 
    }
    
    // Getters
    public String getNombre() {
        return nombre;
    }
    public int getPuntosVida() {
        return puntosVida;
    }
    public int getFila() {
        return fila;
    }
    public int getColumna() {
        return columna;
    }
    public int getEjercito() {
        return ejercito;
    }
    public String toString() {
        char columnaLetra = (char) ('A' + columna); 
        return nombre + " -> Vida: " + puntosVida + ", Posición: (" + (fila + 1) 
                + ", " + columnaLetra + "), Ejército: " + ejercito;
    }
}

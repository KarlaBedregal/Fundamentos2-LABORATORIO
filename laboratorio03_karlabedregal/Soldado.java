/* Autor: Karla Bedregal Coaguila 
Solucionar la Actividad 4 de la Práctica 1 pero usando arreglo de objetos */
package laboratorio03_karlabedregal;
public class Soldado {
    private String nombre;
    private int nivelVida;
    // Constructor
    public Soldado(String nombre, int nivelVida) {
        this.nombre = nombre;
        this.nivelVida = nivelVida;
    }
    // Métodos accesores
    public String getNombre() {
        return nombre;
    }
    public int getNivelVida() {
        return nivelVida;
    }
    // Método para mostrar información del soldado
    public String toString() {
        return "Nombre: " + nombre + ", Nivel de vida: " + nivelVida;
    }
}

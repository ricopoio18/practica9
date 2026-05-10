package Modelo;

import Interfaces.Inventariable;

/**
 * La clase Arma representa un objeto utilizable en combate dentro del juego.
 *
 * Implementa la interfaz Inventariable, lo que permite que pueda ser
 * almacenada en el inventario del jugador.
 *
 * Un arma tiene nombre, daño y alcance, los cuales determinan su
 * comportamiento durante los ataques.
 */
public class Arma implements Inventariable {

    /** Nombre del arma */
    private String nombre;

    /** Cantidad de daño que puede causar el arma */
    private int daño;

    /** Distancia máxima a la que puede afectar el arma */
    private double alcance;

    /**
     * Constructor de la clase Arma.
     *
     * Inicializa todos los atributos del arma.
     *
     * @param nombre nombre del arma
     * @param daño cantidad de daño
     * @param alcance alcance del arma
     */
    public Arma(String nombre, int daño, double alcance){
        this.nombre = nombre;
        this.daño = daño;
        this.alcance = alcance;
    }

    /**
     * Obtiene el nombre del arma.
     *
     * @return nombre del arma
     */
    public String getNombre(){
        return nombre;
    }

    /**
     * Obtiene el daño del arma.
     *
     * @return daño del arma
     */
    public int getDaño(){
        return daño;
    }

    /**
     * Obtiene el alcance del arma.
     *
     * @return alcance del arma
     */
    public double getAlcance(){
        return alcance;
    }

    /**
     * Registra el arma en el sistema (por ejemplo, al agregarla al inventario).
     */
    @Override
    public void registrar() {
        System.out.println("Arma registrada: " + nombre);
    }

    /**
     * Elimina el arma del sistema (por ejemplo, al removerla del inventario).
     */
    @Override
    public void borrar() {
        System.out.println("Arma eliminada: " + nombre);
    }

    /**
     * Devuelve una representación en texto del arma.
     *
     * @return cadena con los atributos del arma
     */
    @Override
    public String toString(){
        return "\nArma: " + nombre +
                "\n Daño: " + daño +
                "\n Alcance: " + alcance;
    }
}

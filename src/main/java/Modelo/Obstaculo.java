package Modelo;

import Interfaces.Destruible;

/**
 * La clase Obstaculo representa un objeto dentro del juego que puede
 * causar daño a los personajes y ser destruido.
 *
 * Implementa la interfaz Destruible, lo que permite cambiar su estado
 * a destruido cuando sea necesario.
 *
 * Ejemplos de obstáculos:
 * - Trampas
 * - Barreras
 * - Objetos del entorno que afectan al jugador
 */
public class Obstaculo implements Destruible {

    /** Nombre del obstáculo */
    private String nombre;

    /** Cantidad de daño que provoca al jugador */
    private int daño;

    /** Posición en el eje X */
    private int posicionX;

    /** Posición en el eje Y */
    private int posicionY;

    /** Indica si el obstáculo ha sido destruido */
    private boolean destruido;

    /**
     * Constructor de la clase Obstaculo.
     *
     * Inicializa los atributos del objeto y establece su estado como no destruido.
     *
     * @param nombre Nombre del obstáculo
     * @param daño Cantidad de daño que causa
     * @param posicionX Posición inicial en X
     * @param posicionY Posición inicial en Y
     */
    public Obstaculo(String nombre, int daño, int posicionX, int posicionY){
        this.nombre = nombre;
        this.daño = daño;
        this.posicionX = posicionX;
        this.posicionY = posicionY;
        this.destruido = false;
    }

    /**
     * Obtiene el nombre del obstáculo.
     *
     * @return nombre del obstáculo
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Obtiene el daño que causa el obstáculo.
     *
     * @return valor de daño
     */
    public int getDaño(){
        return daño;
    }

    /**
     * Obtiene la posición en el eje X.
     *
     * @return posición X
     */
    public int getPosicionX(){
        return posicionX;
    }

    /**
     * Obtiene la posición en el eje Y.
     *
     * @return posición Y
     */
    public int getPosicionY(){
        return posicionY;
    }

    /**
     * Indica si el obstáculo ha sido destruido.
     *
     * @return true si está destruido, false en caso contrario
     */
    public boolean isDestruido(){
        return destruido;
    }

    /**
     * Destruye el obstáculo.
     *
     * Implementa el método de la interfaz Destruible.
     * Cambia su estado a destruido y muestra un mensaje en consola.
     */
    @Override
    public void destruye(){
        destruido = true;
        System.out.println(nombre + " ha sido destruido");
    }
}

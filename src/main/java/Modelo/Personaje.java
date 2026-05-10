package Modelo;

import Interfaces.Destruible;
import Interfaces.ElementoDinamico;

/**
 * La clase Personaje representa una entidad dentro del juego que
 * puede moverse, recibir daño y ser destruida.
 *
 * Implementa:
 * - Destruible: permite que el personaje pueda ser eliminado
 * - ElementoDinamico: permite que el personaje se mueva en el escenario
 *
 * Es una clase base que puede ser extendida por otros tipos de personajes
 * (por ejemplo, enemigos o jugador).
 */
public class Personaje implements Destruible, ElementoDinamico {

    /** Nombre del personaje */
    private String nombre;

    /** Cantidad de vida del personaje */
    private int vida;

    /** Posición en el eje X */
    private int posicionX;

    /** Posición en el eje Y */
    private int posicionY;

    /**
     * Constructor de la clase Personaje.
     *
     * Inicializa los atributos principales del personaje.
     *
     * @param nombre Nombre del personaje
     * @param vida Vida inicial
     * @param posicionX Posición inicial en X
     * @param posicionY Posición inicial en Y
     */
    public Personaje(String nombre, int vida, int posicionX, int posicionY) {
        this.nombre = nombre;
        this.vida = vida;
        this.posicionX = posicionX;
        this.posicionY = posicionY;
    }

    /**
     * Obtiene el nombre del personaje.
     *
     * @return nombre del personaje
     */
    public String getNombre(){
        return nombre;
    }

    /**
     * Obtiene la vida actual del personaje.
     *
     * @return vida restante
     */
    public int getVida(){
        return vida;
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
     * Establece la posición en el eje X.
     *
     * @param posicionX nueva posición (se convierte a entero)
     */
    public void setPosicionX(double posicionX){
        this.posicionX = (int) posicionX;
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
     * Establece la posición completa del personaje.
     *
     * @param x nueva posición en X
     * @param y nueva posición en Y
     */
    public void setPosicion(int x, int y){
        posicionX = x;
        posicionY = y;
    }

    /**
     * Aplica daño al personaje.
     *
     * Reduce la vida y, si llega a cero, lo destruye.
     *
     * @param daño cantidad de daño recibido
     */
    public void recibirDaño(int daño){
        vida -= daño;

        if (vida <= 0) { // 🔥 mejor práctica (evita valores negativos)
            destruye();
        }
    }

    /**
     * Mueve al personaje en una dirección específica.
     *
     * Implementa el método de la interfaz ElementoDinamico.
     *
     * @param direccion Dirección del movimiento (norte, sur, este, oeste)
     * @param distancia Cantidad de unidades a mover
     */
    @Override
    public void mover(String direccion, int distancia){
        String cadena = direccion.toLowerCase();

        switch(cadena){
            case "norte":
                posicionY -= distancia;
                break;
            case "sur":
                posicionY += distancia;
                break;
            case "este":
                posicionX += distancia;
                break;
            case "oeste":
                posicionX -= distancia;
                break;
        }
    }

    /**
     * Destruye al personaje.
     *
     * Implementa la interfaz Destruible.
     * Establece la vida en cero y muestra un mensaje.
     */
    @Override
    public void destruye(){
        vida = 0;
        System.out.println(nombre + " ha sido eliminado");
    }
}

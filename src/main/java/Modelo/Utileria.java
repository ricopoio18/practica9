package Modelo;

import Interfaces.ElementoDinamico;

/**
 * La clase Utileria representa un objeto utilizable dentro del juego
 * que puede interactuar con el entorno y ser usado una sola vez.
 *
 * Implementa la interfaz ElementoDinamico, lo que permite que el objeto
 * pueda moverse dentro del escenario.
 *
 * Ejemplos de utilería:
 * - Botones
 * - Palancas
 * - Objetos interactivos
 */
public class Utileria implements ElementoDinamico {

    /** Nombre de la utilería */
    private String nombre;

    /** Descripción del objeto */
    private String descripcion;

    /** Posición en el eje X */
    private int posicionX;

    /** Posición en el eje Y */
    private int posicionY;

    /** Indica si la utilería ya fue utilizada */
    private boolean usada;

    /**
     * Constructor de la clase Utileria.
     *
     * Inicializa los atributos del objeto y establece su estado como no usado.
     *
     * @param nombre Nombre de la utilería
     * @param descripcion Descripción del objeto
     * @param posicionX Posición inicial en X
     * @param posicionY Posición inicial en Y
     */
    public Utileria(String nombre, String descripcion, int posicionX, int posicionY){
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.posicionX = posicionX;
        this.posicionY = posicionY;
        this.usada = false;
    }

    /**
     * Obtiene el nombre de la utilería.
     *
     * @return nombre del objeto
     */
    public String getNombre(){
        return nombre;
    }

    /**
     * Obtiene la descripción de la utilería.
     *
     * @return descripción del objeto
     */
    public String getDescripcion(){
        return descripcion;
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
     * Indica si la utilería ya fue utilizada.
     *
     * @return true si ya fue usada, false en caso contrario
     */
    public boolean isUsada(){
        return usada;
    }

    /**
     * Permite usar la utilería.
     *
     * Si no ha sido usada, cambia su estado a usada
     * y muestra un mensaje en consola.
     * Si ya fue usada, informa que no puede volver a utilizarse.
     */
    public void usar(){
        if (!usada) {
            usada = true;
            System.out.println(nombre + " ha sido usado");
        } else {
            System.out.println(nombre + " ya fue usado");
        }
    }

    /**
     * Mueve la utilería en una dirección específica.
     *
     * Implementa el método de la interfaz ElementoDinamico.
     *
     * @param direccion Dirección del movimiento (norte, sur, este, oeste)
     * @param distancia Cantidad de unidades a mover
     */
    @Override
    public void mover(String direccion, int distancia){
        switch(direccion.toLowerCase()){
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
}

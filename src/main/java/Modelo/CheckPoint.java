package Modelo;

/**
 * La clase CheckPoint representa un punto de control dentro de un nivel del juego.
 *
 * Permite guardar el progreso del jugador en una posición específica
 * y dentro de un nivel determinado.
 *
 * Un CheckPoint puede ser activado cuando el jugador lo alcanza.
 */
public class CheckPoint {

    /** Número del nivel al que pertenece el checkpoint */
    private int nivel;

    /** Posición en el eje X */
    private int posicionX;

    /** Posición en el eje Y */
    private int posicionY;

    /** Indica si el checkpoint ha sido activado */
    private boolean activado;

    /**
     * Constructor de la clase CheckPoint.
     *
     * Inicializa el nivel y la posición del punto de control.
     * Por defecto, el checkpoint inicia desactivado.
     *
     * @param nivel número del nivel
     * @param posicionX posición en el eje X
     * @param posicionY posición en el eje Y
     */
    public CheckPoint(int nivel, int posicionX, int posicionY) {
        this.nivel = nivel;
        this.posicionX = posicionX;
        this.posicionY = posicionY;
        this.activado = false;
    }

    /**
     * Obtiene el número del nivel.
     *
     * @return nivel del checkpoint
     */
    public int getNivel() {
        return nivel;
    }

    /**
     * Obtiene la posición en X.
     *
     * @return posición X
     */
    public int getPosicionX() {
        return posicionX;
    }

    /**
     * Obtiene la posición en Y.
     *
     * @return posición Y
     */
    public int getPosicionY(){
        return posicionY;
    }

    /**
     * Indica si el checkpoint está activado.
     *
     * @return true si está activado, false en caso contrario
     */
    public boolean isActivado() {
        return activado;
    }

    /**
     * Activa el checkpoint.
     *
     * Este método debe llamarse cuando el jugador alcanza este punto,
     * permitiendo guardar su progreso.
     */
    public void activar(){
        activado = true;
    }
}

package Modelo;

/**
 * La clase FrutaPoder representa un tipo especial de recompensa
 * que otorga un aumento temporal o permanente de fuerza al personaje.
 *
 * Extiende la clase Recompensa, heredando sus atributos básicos
 * como nombre, valor y tipo.
 *
 * Añade un multiplicador de fuerza y un estado que indica si ya fue consumida.
 */
public class FrutaPoder extends Recompensa {

    /** Multiplicador que incrementa la fuerza del personaje */
    private double multiplicadorFuerza;

    /** Indica si la fruta ya fue consumida */
    private boolean consumida = false;

    /**
     * Constructor de la clase FrutaPoder.
     *
     * Inicializa los atributos heredados y el multiplicador de fuerza.
     *
     * @param nombre Nombre de la fruta
     * @param puntos Valor de la recompensa
     * @param tipo Tipo de recompensa
     * @param multiplicadorFuerza Factor de aumento de fuerza
     */
    public FrutaPoder(String nombre, int puntos, String tipo, double multiplicadorFuerza) {
        super(nombre, puntos, tipo);
        this.multiplicadorFuerza = multiplicadorFuerza;
    }

    /**
     * Obtiene el multiplicador de fuerza de la fruta.
     *
     * @return multiplicador de fuerza
     */
    public double getMultiplicadorFuerza() {
        return multiplicadorFuerza;
    }

    /**
     * Indica si la fruta ya fue consumida.
     *
     * @return true si fue consumida, false en caso contrario
     */
    public boolean isConsumida() {
        return consumida;
    }

    /**
     * Marca la fruta como consumida.
     *
     * Este método debería llamarse cuando el jugador utiliza la fruta.
     */
    public void consumir() {
        this.consumida = true;
    }

    /**
     * Devuelve una representación en texto de la fruta.
     *
     * Incluye la información heredada de Recompensa y el multiplicador de fuerza.
     * También indica si ya fue consumida.
     *
     * @return cadena con la información completa de la fruta
     */
    @Override
    public String toString() {
        return super.toString() +
                "\nMultiplicador de Fuerza: x" + multiplicadorFuerza +
                (consumida ? " (Consumida)" : "");
    }
}
package Modelo;

/**
 * La clase NivelCombate representa un tipo específico de nivel
 * enfocado en combates entre personajes (Escarabajos).
 *
 * Extiende la clase Nivel, heredando sus propiedades generales
 * como nombre, número, dificultad e inventario.
 *
 * Añade la lógica de combate tipo "Ring Out", donde un personaje
 * pierde si sale de los límites del escenario.
 */
public class NivelCombate extends Nivel {

    /** Límite izquierdo del escenario */
    private int limiteIzquierdo = 0;

    /** Límite derecho del escenario */
    private int limiteDerecho = 670;

    /**
     * Constructor de la clase NivelCombate.
     *
     * Inicializa el nivel con sus atributos básicos heredados.
     *
     * @param nombre Nombre del nivel
     * @param numero Número del nivel
     * @param dificultad Nivel de dificultad
     * @param inventario Inventario asociado al nivel
     */
    public NivelCombate(String nombre, int numero, String dificultad, Inventario inventario) {
        super(nombre, numero, dificultad, inventario);
    }

    /**
     * Verifica si alguno de los jugadores ha salido de los límites del ring.
     *
     * Si un jugador cruza los límites establecidos, se considera derrotado
     * y el otro jugador gana el combate.
     *
     * @param p1 Primer jugador (Escarabajo)
     * @param p2 Segundo jugador (Escarabajo)
     * @return true si ocurrió un Ring Out (fin del combate), false en caso contrario
     */
    public boolean verificarRingOut(Escarabajo p1, Escarabajo p2) {

        // Verifica si el jugador 1 sale del ring
        if (p1.getPosicionX() < limiteIzquierdo || p1.getPosicionX() > limiteDerecho) {
            p1.destruye();
            System.out.println(p2.getNombre() + " gana!");
            return true;
        }

        // Verifica si el jugador 2 sale del ring
        if (p2.getPosicionX() < limiteIzquierdo || p2.getPosicionX() > limiteDerecho) {
            p2.destruye();
            System.out.println(p1.getNombre() + " gana!");
            return true;
        }

        return false;
    }
}
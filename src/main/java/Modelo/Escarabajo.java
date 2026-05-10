package Modelo;

/**
 * La clase Escarabajo representa un personaje jugable especializado en combate.
 *
 * Extiende la clase Personaje, heredando atributos como nombre, vida y posición.
 *
 * Un Escarabajo puede equipar un arma, atacar a otros personajes y aplicar
 * mecánicas de combate como tiempo de espera (cooldown) y multiplicador de fuerza.
 */
public class Escarabajo extends Personaje {

    /** Arma equipada por el escarabajo */
    private Arma arma;

    /** Tiempo del último ataque realizado (en milisegundos) */
    private long ultimoAtaque;

    /** Tiempo mínimo de espera entre ataques (cooldown) */
    private long tiempoEspera;

    /** Multiplicador de fuerza aplicado al ataque */
    private double fuerzaMultiplicador = 1.0;

    /**
     * Constructor de la clase Escarabajo.
     *
     * @param nombre Nombre del personaje
     * @param vida Vida inicial
     * @param x Posición inicial en X
     * @param y Posición inicial en Y
     * @param tiempoEspera Tiempo de espera entre ataques (ms)
     */
    public Escarabajo(String nombre, int vida, int x, int y, long tiempoEspera) {
        super(nombre, vida, x, y);
        this.tiempoEspera = tiempoEspera;
    }

    /**
     * Establece el multiplicador de fuerza.
     *
     * @param factor nuevo multiplicador
     */
    public void setFuerzaMultiplicador(double factor) {
        this.fuerzaMultiplicador = factor;
    }

    /**
     * Obtiene el multiplicador de fuerza actual.
     *
     * @return multiplicador de fuerza
     */
    public double getFuerzaMultiplicador() {
        return fuerzaMultiplicador;
    }

    /**
     * Asigna un arma al escarabajo.
     *
     * @param arma objeto arma
     */
    public void setArma(Arma arma) {
        this.arma = arma;
    }

    /**
     * Realiza un ataque contra otro personaje.
     *
     * El ataque:
     * - Verifica que el escarabajo tenga arma equipada
     * - Respeta el tiempo de espera entre ataques (cooldown)
     * - Calcula la distancia con el enemigo
     * - Si está dentro del alcance, empuja al enemigo según el daño del arma
     *
     * @param enemigo personaje objetivo del ataque
     */
    public void atacar(Personaje enemigo) {

        long tiempoActual = System.currentTimeMillis();

        // Verifica si tiene arma equipada
        if (arma == null) {
            System.out.println(getNombre() + " no tiene arma");
            return;
        }

        // Verifica cooldown
        if (tiempoActual - ultimoAtaque < tiempoEspera) {
            System.out.println(getNombre() + " está en cooldown");
            return;
        }

        // Calcula distancia en eje X
        double distancia = Math.abs(getPosicionX() - enemigo.getPosicionX());

        // Verifica alcance
        if (distancia <= arma.getAlcance()) {

            int empuje = arma.getDaño();

            // Aplica empuje según posición relativa
            if (getPosicionX() < enemigo.getPosicionX()) {
                enemigo.setPosicion(enemigo.getPosicionX() + empuje, enemigo.getPosicionY());
            } else {
                enemigo.setPosicion(enemigo.getPosicionX() - empuje, enemigo.getPosicionY());
            }

            System.out.println(getNombre() + " empuja a " + enemigo.getNombre());

            // Actualiza tiempo de ataque
            ultimoAtaque = tiempoActual;
        }
    }
}
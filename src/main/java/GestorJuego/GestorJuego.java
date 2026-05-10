package GestorJuego;

import Interfaces.Inventariable;
import Modelo.*;

/**
 * La clase GestorJuego se encarga de administrar el flujo general del juego.
 *
 * Controla:
 * - La carga de niveles
 * - El progreso del jugador
 * - El inventario
 * - Checkpoints
 * - Condiciones de victoria y derrota
 *
 * Actúa como puente entre la lógica del juego y la interfaz gráfica.
 */
public class GestorJuego {

    /** Nivel actual en el que se encuentra el jugador */
    private NivelCombate nivelActual;

    /** Número del nivel actual */
    private int numeroNivel = 1;

    /** Indica si el juego ha terminado */
    private boolean juegoTerminado = false;

    /** Inventario del jugador */
    private Inventario inventarioJugador = new Inventario(10);

    /** Checkpoint actual guardado */
    private CheckPoint checkPointActual = null;

    /**
     * Constructor del gestor del juego.
     *
     * Inicializa cargando el nivel 1.
     */
    public GestorJuego() {
        cargarNivel(1);
    }

    /**
     * Carga un nivel específico del juego.
     *
     * Si es el nivel 1 y el jugador no tiene la Fruta Poder,
     * se le añade al inventario.
     *
     * @param numero número del nivel a cargar
     */
    public void cargarNivel(int numero) {
        this.numeroNivel = numero;

        // Agregar fruta poder solo una vez
        if (numero == 1 && inventarioJugador.buscarPorNombre("Fruta Poder") == null) {
            inventarioJugador.agregarItem(
                    new FrutaPoder("Fruta Poder", 200, "Poder", 2.0)
            );
        }

        this.nivelActual = new NivelCombate(
                "Nivel " + numero,
                numero,
                "Normal",
                inventarioJugador
        );

        System.out.println("Cargando nivel " + numero);
    }

    /**
     * Obtiene el inventario del jugador.
     *
     * @return inventario
     */
    public Inventario getInventarioJugador() {
        return inventarioJugador;
    }

    /**
     * Obtiene el nivel actual.
     *
     * @return nivel actual
     */
    public NivelCombate getNivelActual() {
        return nivelActual;
    }

    /**
     * Obtiene el número del nivel actual.
     *
     * @return número de nivel
     */
    public int getNumeroNivel() {
        return numeroNivel;
    }

    /**
     * Configura el arma del enemigo según el nivel actual.
     *
     * @param enemigo personaje enemigo
     */
    public void configurarEnemigo(Escarabajo enemigo) {

        if (numeroNivel == 1) {
            enemigo.setArma(new Arma("Empuje", 1, 60));
        } else {
            enemigo.setArma(new Arma("Empuje", 5, 60));
        }
    }

    /**
     * Guarda el progreso del jugador en un checkpoint.
     *
     * @param jugador personaje del jugador
     */
    public void guardarPartida(Personaje jugador) {

        checkPointActual = new CheckPoint(
                numeroNivel,
                jugador.getPosicionX(),
                jugador.getPosicionY()
        );

        System.out.println("Checkpoint guardado en nivel " + numeroNivel);
    }

    /**
     * Carga el progreso desde el último checkpoint guardado.
     *
     * @param jugador personaje del jugador
     */
    public void cargarDesdeCheckpoint(Personaje jugador) {

        if (checkPointActual != null) {
            this.numeroNivel = checkPointActual.getNivel();

            cargarNivel(numeroNivel);

            jugador.setPosicion(
                    checkPointActual.getPosicionX(),
                    checkPointActual.getPosicionY()
            );
        }
    }

    /**
     * Maneja la lógica cuando el jugador gana un combate.
     *
     * - Puede guardar el progreso
     * - Aplica efectos de objetos (como FrutaPoder)
     * - Avanza de nivel o termina el juego
     *
     * @param jugador personaje del jugador
     * @param guardar indica si se debe guardar el progreso
     */
    public void jugadorGana(Personaje jugador, boolean guardar) {

        if (guardar) {
            guardarPartida(jugador);
        }

        // Consumir fruta poder al pasar del nivel 1
        if (numeroNivel == 1) {
            for (Inventariable item : inventarioJugador.getItems()) {
                if (item instanceof FrutaPoder fruta && !fruta.isConsumida()) {

                    fruta.consumir();

                    if (jugador instanceof Escarabajo esc) {
                        esc.setFuerzaMultiplicador(
                                fruta.getMultiplicadorFuerza()
                        );
                    }
                    break;
                }
            }
        }

        // Avanzar de nivel o terminar juego
        if (numeroNivel < 2) {
            numeroNivel++;
            cargarNivel(numeroNivel);
        } else {
            juegoTerminado = true;
        }
    }

    /**
     * Maneja la lógica cuando el jugador pierde.
     *
     * Si existe un checkpoint, se carga.
     * De lo contrario, se reinicia el juego desde el nivel 1.
     *
     * @param jugador personaje del jugador
     */
    public void jugadorPierde(Personaje jugador) {

        if (checkPointActual != null) {
            cargarDesdeCheckpoint(jugador);
        } else {
            cargarNivel(1);
        }
    }

    /**
     * Indica si el juego ha terminado.
     *
     * @return true si terminó, false en caso contrario
     */
    public boolean isJuegoTerminado() {
        return juegoTerminado;
    }
}
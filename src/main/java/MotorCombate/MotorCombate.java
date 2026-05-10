package MotorCombate;

import Modelo.*;

/**
 * La clase MotorCombate se encarga de gestionar la lógica principal
 * del combate entre dos objetos de tipo Escarabajo dentro de un NivelCombate.
 *
 * Controla:
 * - Movimiento del jugador y enemigo
 * - Sistema de empuje
 * - Cooldown del ataque
 * - Colisiones entre personajes
 * - Verificación de límites del ring (Ring Out)
 *
 * Esta clase actúa como el "motor" del juego en tiempo real.
 */
public class MotorCombate {

    /** Jugador controlado por el usuario */
    private Escarabajo jugador;

    /** Enemigo controlado por la IA */
    private Escarabajo enemigo;

    /** Nivel donde ocurre el combate */
    private NivelCombate nivel;

    /** Indica si el juego sigue activo */
    private boolean juegoActivo = true;

    // ===== SISTEMA DE COOLDOWN =====

    /** Contador de empujes realizados */
    private int empujes = 0;

    /** Máximo número de empujes antes de activar cooldown */
    private final int MAX_EMPUJES = 5;

    /** Indica si el jugador está en cooldown */
    private boolean enCooldown = false;

    /** Tiempo de espera del cooldown en milisegundos */
    private long tiempoEspera = 200;

    /** Momento en el que inicia el cooldown */
    private long inicioCooldown = 0;

    /**
     * Constructor de la clase MotorCombate.
     *
     * @param jugador Escarabajo controlado por el usuario
     * @param enemigo Escarabajo controlado por la IA
     * @param nivel Nivel donde ocurre el combate
     */
    public MotorCombate(Escarabajo jugador, Escarabajo enemigo, NivelCombate nivel) {
        this.jugador = jugador;
        this.enemigo = enemigo;
        this.nivel = nivel;
    }

    /**
     * Actualiza el estado del juego en cada frame.
     *
     * Se encarga de:
     * - Controlar el cooldown del ataque
     * - Detectar si el jugador está empujando
     * - Mover automáticamente al jugador y enemigo
     * - Aplicar colisiones y fuerzas
     * - Verificar condiciones de victoria (Ring Out)
     *
     * @param espacioPresionado true si el jugador está intentando empujar
     */
    public void actualizar(boolean espacioPresionado) {
        if (!juegoActivo) return;

        // Gestión del cooldown
        if (enCooldown) {
            if (System.currentTimeMillis() - inicioCooldown >= tiempoEspera) {
                enCooldown = false;
                empujes = 0;
            }
        }

        boolean estaEmpujando = false;

        if (espacioPresionado && !enCooldown) {
            estaEmpujando = true;
            empujes++;

            if (empujes >= MAX_EMPUJES) {
                enCooldown = true;
                inicioCooldown = System.currentTimeMillis();
            }
        }

        // Movimiento del jugador
        jugador.mover("este", 2);

        // Movimiento del enemigo (IA)
        moverIAEnemigo();

        // Cálculo de colisión y empuje
        double dist = Math.abs(jugador.getPosicionX() - enemigo.getPosicionX());

        if (dist <= 60) {

            double fuerzaJugadorBase = estaEmpujando ? 12.0 : 0.0;
            double fuerzaJugador = fuerzaJugadorBase * jugador.getFuerzaMultiplicador();
            double fuerzaEnemigo = 1.5;

            // Evita superposición de personajes
            double centro = (jugador.getPosicionX() + enemigo.getPosicionX()) / 2;
            jugador.setPosicionX(centro - 30);
            enemigo.setPosicionX(centro + 30);

            // Aplica fuerza resultante
            double resultado = fuerzaJugador - fuerzaEnemigo;
            jugador.setPosicionX(jugador.getPosicionX() + resultado);
            enemigo.setPosicionX(enemigo.getPosicionX() + resultado);
        }

        // Verifica si alguien salió del ring
        if (nivel.verificarRingOut(jugador, enemigo)) {
            juegoActivo = false;
        }
    }

    /**
     * Controla el movimiento automático del enemigo (IA básica).
     *
     * El enemigo se mueve hacia el jugador en el eje X.
     */
    private void moverIAEnemigo() {
        if (enemigo.getPosicionX() > jugador.getPosicionX()) {
            enemigo.mover("oeste", 2);
        } else {
            enemigo.mover("este", 2);
        }
    }

    /**
     * Indica si el juego sigue en ejecución.
     *
     * @return true si el combate sigue activo, false si terminó
     */
    public boolean isJuegoActivo() {
        return juegoActivo;
    }
}
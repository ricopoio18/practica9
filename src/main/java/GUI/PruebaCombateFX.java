package GUI;

import Interfaces.Inventariable;
import Modelo.*;
import GestorJuego.*;
import MotorCombate.*;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Clase principal de la interfaz gráfica del juego "Beatle Battles".
 *
 * Extiende Application de JavaFX y gestiona:
 * - Renderizado del juego
 * - Entrada del usuario (teclado)
 * - Animaciones
 * - Interacción con el motor de combate y gestor del juego
 *
 * Controla el flujo completo del combate entre dos Escarabajos.
 */
public class PruebaCombateFX extends Application {

    /** Personaje controlado por el jugador */
    private Escarabajo jugador;

    /** Personaje enemigo */
    private Escarabajo enemigo;

    /** Motor que gestiona la lógica del combate */
    private MotorCombate motor;

    /** Gestor general del juego (niveles, progreso, etc.) */
    private GestorJuego gestor;

    /** Indica si el juego está esperando decisión del usuario */
    private boolean esperandoDecision;

    /** Indica si el juego está pausado */
    private boolean juegoPausado = false;

    /** Control de movimiento */
    private boolean moverIzquierda = false;
    private boolean moverDerecha = false;

    /** Indica si el jugador está intentando atacar */
    private boolean teclaCombate = false;

    /**
     * Método principal de JavaFX.
     *
     * Inicializa la ventana, personajes, interfaz gráfica,
     * controles de teclado y ciclo de animación.
     *
     * @param stage ventana principal
     */
    @Override
    public void start(Stage stage) {

        Pane root = new Pane();

        // Inicialización de personajes
        jugador = new Escarabajo("Jugador", 100, 100, 180, 200);
        enemigo = new Escarabajo("Enemigo", 100, 670, 180, 200);

        jugador.setArma(new Arma("Empuje", 10, 60));
        enemigo.setArma(new Arma("Empuje", 5, 60));

        // --- Carga de imágenes ---
        Image fondoImg = new Image(getClass().getResource("/img/fondo.jpg").toExternalForm());
        ImageView fondo = new ImageView(fondoImg);

        Image imgRing = new Image(getClass().getResource("/img/tronco.png").toExternalForm());
        ImageView ring = new ImageView(imgRing);

        Image jugadorIdle = new Image(getClass().getResource("/img/jugador_idle.gif").toExternalForm());
        Image jugadorMove = new Image(getClass().getResource("/img/jugador_move.gif").toExternalForm());
        ImageView jugadorView = new ImageView(jugadorIdle);

        Image enemigoIdle = new Image(getClass().getResource("/img/enemigo_idle.gif").toExternalForm());
        Image enemigoMove = new Image(getClass().getResource("/img/enemigo_move.gif").toExternalForm());
        ImageView enemigoView = new ImageView(enemigoIdle);

        root.getChildren().addAll(fondo, ring, jugadorView, enemigoView);

        // Botón de pausa
        Button btnPausa = new Button("PAUSA");
        btnPausa.setOnAction(e -> togglePausa(btnPausa));
        root.getChildren().add(btnPausa);

        Scene scene = new Scene(root, 800, 400);

        /**
         * Manejo de teclas presionadas
         */
        scene.setOnKeyPressed(e -> {
            switch (e.getCode()) {
                case UP:
                    teclaCombate = true;
                    break;
                case A:
                    moverIzquierda = true;
                    break;
                case D:
                    moverDerecha = true;
                    break;
                case SPACE:
                    togglePausa(btnPausa);
                    break;
            }
            e.consume();
        });

        /**
         * Manejo de teclas liberadas
         */
        scene.setOnKeyReleased(e -> {
            switch (e.getCode()) {
                case UP:
                    teclaCombate = false;
                    break;
                case A:
                    moverIzquierda = false;
                    break;
                case D:
                    moverDerecha = false;
                    break;
            }
        });

        gestor = new GestorJuego();
        motor = new MotorCombate(jugador, enemigo, gestor.getNivelActual());

        /**
         * Bucle principal del juego usando AnimationTimer.
         */
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {

                // Pausa o espera
                if (juegoPausado || esperandoDecision) return;

                // Verifica fin del juego
                if (gestor.isJuegoTerminado()) {
                    stop();
                    return;
                }

                // Movimiento del jugador
                if (moverIzquierda && !moverDerecha) {
                    jugador.mover("oeste", 3);
                } else if (moverDerecha && !moverIzquierda) {
                    jugador.mover("este", 3);
                }

                // Actualiza combate
                motor.actualizar(teclaCombate);

                // Actualiza posiciones gráficas
                jugadorView.setX(jugador.getPosicionX());
                jugadorView.setY(jugador.getPosicionY());
                enemigoView.setX(enemigo.getPosicionX());
                enemigoView.setY(enemigo.getPosicionY());
            }
        };

        timer.start();

        stage.setTitle("Beatle Battles");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Alterna el estado de pausa del juego.
     *
     * @param btn botón de pausa
     */
    private void togglePausa(Button btn) {
        juegoPausado = !juegoPausado;
        btn.setText(juegoPausado ? "PLAY" : "PAUSA");
    }

    /**
     * Método auxiliar para actualizar sprites según distancia.
     */
    private void actualizarSprites(double dist, ImageView jV, ImageView eV, Image jI, Image jM, Image eI, Image eM) {
        if (dist <= 65 || !motor.isJuegoActivo()) {
            jV.setImage(jI);
            eV.setImage(eI);
        } else {
            jV.setImage(jM);
            eV.setImage(eM);
        }
    }

    /**
     * Reinicia el combate después de perder o avanzar.
     */
    private void resetearCombate(Pane root) {
        jugador.setPosicion(100, 190);
        enemigo.setPosicion(670, 190);
        teclaCombate = false;
        gestor.configurarEnemigo(enemigo);
        motor = new MotorCombate(jugador, enemigo, gestor.getNivelActual());
        root.requestFocus();
    }

    /**
     * Método principal de ejecución.
     *
     * @param args argumentos
     */
    public static void main(String[] args) {
        launch();
    }
}
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

public class PruebaCombateFX extends Application {

    private Escarabajo jugador;
    private Escarabajo enemigo;
    private MotorCombate motor;
    private GestorJuego gestor;
    private boolean esperandoDecision;
    private boolean juegoPausado = false;
    private boolean moverIzquierda = false;
    private boolean moverDerecha = false;

    // VARIABLE RENOMBRADA: Ahora es más claro qué hace
    private boolean teclaCombate = false;

    @Override
    public void start(Stage stage) {

        Pane root = new Pane();

        jugador = new Escarabajo("Jugador", 100, 100, 180, 200);
        enemigo = new Escarabajo("Enemigo", 100, 670, 180, 200);

        jugador.setArma(new Arma("Empuje", 10, 60));
        enemigo.setArma(new Arma("Empuje", 5, 60));


        // Imágenes
        Image fondoImg = new Image(getClass().getResource("/img/fondo.jpg").toExternalForm());
        ImageView fondo = new ImageView(fondoImg);
        fondo.setFitWidth(800);
        fondo.setFitHeight(400);

        Image imgRing = new Image(getClass().getResource("/img/tronco.png").toExternalForm());
        ImageView ring = new ImageView(imgRing);
        ring.setX(50);
        ring.setY(240);
        ring.setFitWidth(700);
        ring.setFitHeight(200);

        Image jugadorIdle = new Image(getClass().getResource("/img/jugador_idle.gif").toExternalForm());
        Image jugadorMove = new Image(getClass().getResource("/img/jugador_move.gif").toExternalForm());
        ImageView jugadorView = new ImageView(jugadorIdle);
        jugadorView.setFitWidth(100);
        jugadorView.setFitHeight(100);

        Image enemigoIdle = new Image(getClass().getResource("/img/enemigo_idle.gif").toExternalForm());
        Image enemigoMove = new Image(getClass().getResource("/img/enemigo_move.gif").toExternalForm());
        ImageView enemigoView = new ImageView(enemigoIdle);
        enemigoView.setFitWidth(100);
        enemigoView.setFitHeight(100);
        enemigoView.setScaleX(-1);

        root.getChildren().addAll(fondo, ring, jugadorView, enemigoView);

        // --- BOTÓN DE PAUSA/PLAY ---
        Button btnPausa = new Button("PAUSA");
        btnPausa.setLayoutX(700);
        btnPausa.setLayoutY(20);
        btnPausa.setStyle("-fx-background-color: #f1c40f; -fx-font-weight: bold; -fx-text-fill: black;");

        // IMPORTANTE: Evita que el botón robe el foco del teclado
        btnPausa.setFocusTraversable(false);

        btnPausa.setOnAction(e -> togglePausa(btnPausa));
        root.getChildren().add(btnPausa);

        Scene scene = new Scene(root, 800, 400);

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

        // Paneles de UI
        VBox panelGuardar = crearPanelGuardar();
        VBox panelVictoria = crearPanelVictoria(stage);

        Label lblNivel = new Label("NIVEL: " + gestor.getNumeroNivel());
        lblNivel.setStyle("-fx-text-fill: #ffffff; -fx-font-size: 40px; -fx-font-weight: 900;");
        lblNivel.setLayoutX(30);
        lblNivel.setLayoutY(5);

        root.getChildren().addAll(lblNivel, panelGuardar, panelVictoria);

        Label frutaLabel = new Label();
        frutaLabel.setLayoutX(10);
        frutaLabel.setLayoutY(80);
        frutaLabel.setStyle("-fx-text-fill: red; -fx-font-size: 30px; -fx-font-weight: bold;");
        root.getChildren().add(frutaLabel);

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (juegoPausado || esperandoDecision) return;

                if (gestor.isJuegoTerminado()) {
                    panelVictoria.setVisible(true);
                    stop();
                    return;
                }

                int velocidad = 3; // ajusta a gusto

                if (!motor.isJuegoActivo()) {
                    // si el juego está “terminando” no muevas
                } else {
                    if (moverIzquierda && !moverDerecha) {
                        jugador.mover("oeste", velocidad);
                    } else if (moverDerecha && !moverIzquierda) {
                        jugador.mover("este", velocidad);
                    }
                }
                // USANDO LA NUEVA VARIABLE
                motor.actualizar(teclaCombate);


                lblNivel.setText("NIVEL: " + gestor.getNumeroNivel());

                FrutaPoder frutaPoder = null;
                for (Inventariable item : gestor.getInventarioJugador().getItems()) {
                    if (item instanceof FrutaPoder) {
                        frutaPoder = (FrutaPoder) item;
                        break;
                    }
                }
                if (frutaPoder != null) {
                    if (frutaPoder.isConsumida()) {
                        frutaLabel.setText("¡Fruta Poder consumida! Fuerza x" + frutaPoder.getMultiplicadorFuerza());
                    } else {
                        frutaLabel.setText("Fruta Poder lista: Fuerza x" + frutaPoder.getMultiplicadorFuerza());
                    }
                } else {
                    frutaLabel.setText("");
                }

                jugadorView.setX(jugador.getPosicionX());
                jugadorView.setY(jugador.getPosicionY());
                enemigoView.setX(enemigo.getPosicionX());
                enemigoView.setY(enemigo.getPosicionY());

                double distancia = Math.abs(jugador.getPosicionX() - enemigo.getPosicionX());
                actualizarSprites(distancia, jugadorView, enemigoView, jugadorIdle, jugadorMove, enemigoIdle, enemigoMove);

                if (!motor.isJuegoActivo()) {
                    if (enemigo.getPosicionX() >= 670 || enemigo.getPosicionX() <= 10) {
                        esperandoDecision = true;
                        if (gestor.getNumeroNivel() < 2) {
                            panelGuardar.setVisible(true);
                        } else {
                            gestor.jugadorGana(jugador, false);
                            esperandoDecision = false;
                        }
                    } else {
                        gestor.jugadorPierde(jugador);
                        resetearCombate(root);
                    }
                }
            }
        };
        timer.start();

        stage.setTitle("Beatle Battles");
        stage.setScene(scene);
        stage.show();
        root.requestFocus(); // Pedir foco inicial
    }

    // Método auxiliar para la pausa (se usa en botón y tecla)
    private void togglePausa(Button btn) {
        juegoPausado = !juegoPausado;
        if (juegoPausado) {
            btn.setText("PLAY");
            btn.setStyle("-fx-background-color: #2ecc71; -fx-font-weight: bold; -fx-text-fill: white;");
        } else {
            btn.setText("PAUSA");
            btn.setStyle("-fx-background-color: #f1c40f; -fx-font-weight: bold; -fx-text-fill: black;");
        }
    }

    private void actualizarSprites(double dist, ImageView jV, ImageView eV, Image jI, Image jM, Image eI, Image eM) {
        if (dist <= 65 || !motor.isJuegoActivo()) {
            if (jV.getImage() != jI) jV.setImage(jI);
            if (eV.getImage() != eI) eV.setImage(eI);
        } else {
            if (jV.getImage() != jM) jV.setImage(jM);
            if (eV.getImage() != eM) eV.setImage(eM);
        }
    }

    private void resetearCombate(Pane root) {
        if (gestor.isJuegoTerminado()) return;
        jugador.setPosicion(100, 190);
        enemigo.setPosicion(670, 190);
        teclaCombate = false; // Resetear estado de tecla
        gestor.configurarEnemigo(enemigo);
        motor = new MotorCombate(jugador, enemigo, gestor.getNivelActual());
        root.requestFocus();
    }

    private VBox crearPanelGuardar() {
        VBox panel = new VBox(20);
        panel.setStyle("-fx-background-color: rgba(0, 0, 0, 0.8); -fx-padding: 20; -fx-alignment: center;");
        panel.setLayoutX(250);
        panel.setLayoutY(100);
        panel.setVisible(false);

        Label mensaje = new Label("¿Deseas guardar el progreso?");
        mensaje.setStyle("-fx-text-fill: white; -fx-font-size: 20px;");

        Button btnSi = new Button("SÍ");
        Button btnNo = new Button("NO");

        // IMPORTANTE: Evitar que roben el foco del teclado
        btnSi.setFocusTraversable(false);
        btnNo.setFocusTraversable(false);

        btnSi.setOnAction(e -> {
            gestor.jugadorGana(jugador, true); // Guarda y sube nivel
            panel.setVisible(false);
            esperandoDecision = false;
            resetearCombate((Pane) panel.getParent());
        });

        btnNo.setOnAction(e -> {
            gestor.jugadorGana(jugador, false); // Sube nivel sin guardar
            panel.setVisible(false);
            esperandoDecision = false;
            resetearCombate((Pane) panel.getParent());
        });

        panel.getChildren().addAll(mensaje, btnSi, btnNo);
        return panel;
    }

    private VBox crearPanelVictoria(Stage s) {
        VBox panel = new VBox(20);
        panel.setStyle("-fx-background-color: rgba(0, 0, 0, 0.9); -fx-padding: 30; -fx-alignment: center;");
        panel.setLayoutX(200);
        panel.setLayoutY(80);
        panel.setVisible(false);

        Label mensaje = new Label("¡HAS GANADO EL TORNEO!");
        mensaje.setStyle("-fx-text-fill: #f1c40f; -fx-font-size: 30px; -fx-font-weight: bold;");

        Button btnSalir = new Button("SALIR");
        btnSalir.setFocusTraversable(false);
        btnSalir.setOnAction(e -> s.close());

        panel.getChildren().addAll(mensaje, btnSalir);
        return panel;
    }

    public static void main(String[] args) { launch(); }
}
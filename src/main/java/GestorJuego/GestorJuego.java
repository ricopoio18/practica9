package GestorJuego;
import Interfaces.Inventariable;
import Modelo.*;

public class GestorJuego {

    private NivelCombate nivelActual;
    private int numeroNivel = 1;
    private boolean juegoTerminado = false;
    private Inventario inventarioJugador = new Inventario(10);

    private CheckPoint checkPointActual = null;

    public GestorJuego() {
        cargarNivel(1);
    }

    public void cargarNivel(int numero) {
        this.numeroNivel = numero;

        if (numero == 1 && inventarioJugador.buscarPorNombre("Fruta Poder") == null) {
            inventarioJugador.agregarItem(new FrutaPoder("Fruta Poder", 200, "Poder", 2.0));
        }

        this.nivelActual = new NivelCombate("Nivel " + numero, numero, "Normal", inventarioJugador);
        System.out.println("Cargando nivel " + numero);
    }
    public Inventario getInventarioJugador() {
        return inventarioJugador;
    }
    public NivelCombate getNivelActual() {
        return nivelActual;
    }

    public int getNumeroNivel() {
        return numeroNivel;
    }

    public void configurarEnemigo(Escarabajo enemigo) {

        if (numeroNivel == 1) {

            enemigo.setArma(new Arma("Empuje", 1, 60));
        } else {

            enemigo.setArma(new Arma("Empuje", 5, 60));
        }
    }

    public void guardarPartida(Personaje jugador) {
        checkPointActual = new CheckPoint(
                numeroNivel,
                jugador.getPosicionX(),
                jugador.getPosicionY()
        );

        System.out.println("Checkpoint guardado en nivel " + numeroNivel);
    }

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

    public void jugadorGana(Personaje jugador, boolean guardar) {
        if (guardar) guardarPartida(jugador);

        // Si está en nivel 1 y va a pasar a nivel 2, consumir fruta
        if (numeroNivel == 1) {
            for (Inventariable item : inventarioJugador.getItems()) {
                if (item instanceof FrutaPoder fruta && !fruta.isConsumida()) {
                    fruta.consumir();
                    if (jugador instanceof Escarabajo esc) {
                        esc.setFuerzaMultiplicador(fruta.getMultiplicadorFuerza());
                    }
                    break;
                }
            }
        }

        if (numeroNivel < 2) {
            numeroNivel++;
            cargarNivel(numeroNivel);
        } else {
            juegoTerminado = true;
        }
    }

    public void jugadorPierde(Personaje jugador) {

        if (checkPointActual != null) {
            cargarDesdeCheckpoint(jugador);
        } else {
            cargarNivel(1);
        }
    }

    public boolean isJuegoTerminado() {
        return juegoTerminado;
    }
}
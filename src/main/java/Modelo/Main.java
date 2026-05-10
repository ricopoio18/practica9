package Modelo;

public class Main {
    public static void main(String[] args) {

        Inventario inventario = new Inventario(5);

        Arma espada = new Arma("Espada", 50, 1.5);
        Recompensa oro = new Recompensa("Oro", 100, "Moneda");

        inventario.agregarItem(espada);
        inventario.agregarItem(oro);

        Personaje jugador = new Personaje("Heroe", 100, 0, 0);

        Nivel nivel1 = new Nivel("Bosque", 1, "Facil", inventario);
        nivel1.agregarElementoDinamico(jugador);

        nivel1.moverElementosDinamicos("derecha", 5);

        nivel1.mostrarEstado();
    }
}
package Modelo;

public class NivelCombate extends Nivel {

    private int limiteIzquierdo = 0;
    private int limiteDerecho = 670;

    public NivelCombate(String nombre, int numero, String dificultad, Inventario inventario) {
        super(nombre, numero, dificultad, inventario);
    }

    public boolean verificarRingOut(Escarabajo p1, Escarabajo p2) {

        if (p1.getPosicionX() < limiteIzquierdo || p1.getPosicionX() > limiteDerecho) {
            p1.destruye();
            System.out.println(p2.getNombre() + " gana!");
            return true;
        }

        if (p2.getPosicionX() < limiteIzquierdo || p2.getPosicionX() > limiteDerecho) {
            p2.destruye();
            System.out.println(p1.getNombre() + " gana!");
            return true;
        }

        return false;
    }
}
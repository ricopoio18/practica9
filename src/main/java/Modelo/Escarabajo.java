package Modelo;

public class Escarabajo extends Personaje {

    private Arma arma;
    private long ultimoAtaque;
    private long tiempoEspera;
    private double fuerzaMultiplicador = 1.0;

    public Escarabajo(String nombre, int vida, int x, int y, long tiempoEspera) {
        super(nombre, vida, x, y);
        this.tiempoEspera = tiempoEspera;
    }
    public void setFuerzaMultiplicador(double factor) {
        this.fuerzaMultiplicador = factor;
    }
    public double getFuerzaMultiplicador() {
        return fuerzaMultiplicador;
    }

    public void setArma(Arma arma) {
        this.arma = arma;
    }

    public void atacar(Personaje enemigo) {

        long tiempoActual = System.currentTimeMillis();

        if (arma == null) {
            System.out.println(getNombre() + " no tiene arma");
            return;
        }

        if (tiempoActual - ultimoAtaque < tiempoEspera) {
            System.out.println(getNombre() + " está en cooldown");
            return;
        }

        double distancia = Math.abs(getPosicionX() - enemigo.getPosicionX());

        if (distancia <= arma.getAlcance()) {

            int empuje = arma.getDaño();

            if (getPosicionX() < enemigo.getPosicionX()) {
                enemigo.setPosicion(enemigo.getPosicionX() + empuje, enemigo.getPosicionY());
            } else {
                enemigo.setPosicion(enemigo.getPosicionX() - empuje, enemigo.getPosicionY());
            }

            System.out.println(getNombre() + " empuja a " + enemigo.getNombre());

            ultimoAtaque = tiempoActual;
        }
    }
}
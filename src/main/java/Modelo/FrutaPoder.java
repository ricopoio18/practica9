package Modelo;

public class FrutaPoder extends Recompensa {
    private double multiplicadorFuerza;
    private boolean consumida = false;

    public FrutaPoder(String nombre, int puntos, String tipo, double multiplicadorFuerza) {
        super(nombre, puntos, tipo);
        this.multiplicadorFuerza = multiplicadorFuerza;
    }

    public double getMultiplicadorFuerza() {
        return multiplicadorFuerza;
    }

    public boolean isConsumida() {
        return consumida;
    }

    public void consumir() {
        this.consumida = true;
    }

    @Override
    public String toString() {
        return super.toString() + "\nMultiplicador de Fuerza: x" + multiplicadorFuerza + 
               (consumida ? " (Consumida)" : "");
    }
}
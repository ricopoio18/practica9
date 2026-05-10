package Modelo;

public class CheckPoint {
    //LIGERO CAMBIO QUE NO AFECTA A LA IMPLEMENTACIÓN DEL PROGRAMA BASE DADO, DE DAR NOMBRE A LOS NIVELES SOLO
    //SERÁ EL NÚMERO DEL NIVEL POR CUESTIONES CREATIVAS.
    private int nivel;
    private int posicionX;
    private int posicionY;
    private boolean activado;


    public CheckPoint(int nivel, int posicionX, int posicionY) {
        this.nivel = nivel;
        this.posicionX = posicionX;
        this.posicionY = posicionY;
        this.activado = false;
    }

    public int getNivel() {
        return nivel;
    }

    public int getPosicionX() {
        return posicionX;
    }

    public int getPosicionY(){
        return posicionY;
    }

    public boolean isActivado() {
        return activado;
    }

    public void activar(){
        activado = true;
    }
}

package Modelo;

import Interfaces.Destruible;
import Interfaces.ElementoDinamico;

public class Personaje implements Destruible, ElementoDinamico {
    private String nombre;
    private int vida;
    private int posicionX;
    private int posicionY;

    public Personaje(String nombre, int vida, int posicionX, int posicionY) {
        this.nombre = nombre;
        this.vida = vida;
        this.posicionX = posicionX;
        this.posicionY = posicionY;
    }

    public String getNombre(){
        return nombre;
    }

    public int getVida(){
        return vida;
    }

    public int getPosicionX(){
        return posicionX;
    }

    public void setPosicionX(double posicionX){

    }

    public int getPosicionY(){
        return posicionY;
    }


    public void setPosicion(int x, int y){
        posicionX = x;
        posicionY = y;
    }

    public void recibirDaño(int daño){

        vida -= daño;
        if (vida == 0) {
            destruye();
        }
    }

    @Override
    public void mover(String direccion, int distancia){
        String cadena = direccion.toLowerCase();
        switch(cadena){
            case "norte":
                posicionY -= distancia;
                break;
            case "sur":
                posicionY += distancia;
                break;
            case "este":
                posicionX += distancia;
                break;
            case "oeste":
                posicionX -= distancia;
                break;

        }
    }

    @Override
    public void destruye(){
        vida = 0;
        System.out.println(nombre + " ha sido eliminado");
    }

}

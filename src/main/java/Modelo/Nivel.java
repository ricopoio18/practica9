package Modelo;

import Interfaces.ElementoDinamico;

import java.util.ArrayList;
import java.util.List;

public class Nivel {
    private String nombre;
    private int numero;
    private String dificultad;
    private List<Obstaculo> obstaculos;
    private List<CheckPoint> checkPoints;
    private List<ElementoDinamico> elementosDinamicos;
    private Inventario inventario;

    public Nivel(String nombre, int numero, String dificultad,Inventario inventario){
        this.nombre = nombre;
        this.numero = numero;
        this.dificultad = dificultad;
        this.inventario = inventario;
        this.obstaculos = new ArrayList<>();
        this.checkPoints = new ArrayList<>();
        this.elementosDinamicos = new ArrayList<>();
    }

    public String getNombre(){
        return nombre;
    }

    public int getNumero(){
        return numero;
    }

    public String getDificultad(){
        return dificultad;
    }

    public void agregarObstaculo(Obstaculo obstaculo){
        obstaculos.add(obstaculo);
    }

    public void agregarCheckPoint(CheckPoint checkPoint){
        checkPoints.add(checkPoint);
    }

    public void agregarElementoDinamico(ElementoDinamico elemento){
        elementosDinamicos.add(elemento);
    }

    public void moverElementosDinamicos(String direccion, int distancia) {
        for (ElementoDinamico e : elementosDinamicos) {
            e.mover(direccion, distancia);
        }
    }
    public Inventario getInventario() {
        return this.inventario;
    }

    public void mostrarEstado(){
        System.out.println("Nombre: "+ nombre + "\nNúmero: " + numero + "\nDificultad: " + dificultad +
        "\nInventario: " + inventario + "CheckPoints: " + checkPoints.size() );
    }


}

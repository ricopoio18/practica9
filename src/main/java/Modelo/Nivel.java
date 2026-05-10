package Modelo;

import Interfaces.ElementoDinamico;

import java.util.ArrayList;
import java.util.List;

/**
 * La clase Nivel representa un escenario dentro del juego.
 *
 * Contiene todos los elementos necesarios para su funcionamiento:
 * - Obstáculos
 * - CheckPoints
 * - Elementos dinámicos
 * - Inventario asociado
 *
 * Sirve como clase base para otros tipos de niveles más específicos,
 * como niveles de combate.
 */
public class Nivel {

    /** Nombre del nivel */
    private String nombre;

    /** Número identificador del nivel */
    private int numero;

    /** Nivel de dificultad */
    private String dificultad;

    /** Lista de obstáculos presentes en el nivel */
    private List<Obstaculo> obstaculos;

    /** Lista de puntos de guardado (checkpoints) */
    private List<CheckPoint> checkPoints;

    /** Lista de elementos dinámicos (objetos que se pueden mover) */
    private List<ElementoDinamico> elementosDinamicos;

    /** Inventario asociado al nivel */
    private Inventario inventario;

    /**
     * Constructor de la clase Nivel.
     *
     * Inicializa los atributos principales del nivel y crea las listas vacías
     * para almacenar los distintos elementos del juego.
     *
     * @param nombre Nombre del nivel
     * @param numero Número del nivel
     * @param dificultad Dificultad del nivel
     * @param inventario Inventario asociado al nivel
     */
    public Nivel(String nombre, int numero, String dificultad, Inventario inventario){
        this.nombre = nombre;
        this.numero = numero;
        this.dificultad = dificultad;
        this.inventario = inventario;
        this.obstaculos = new ArrayList<>();
        this.checkPoints = new ArrayList<>();
        this.elementosDinamicos = new ArrayList<>();
    }

    /**
     * Obtiene el nombre del nivel.
     *
     * @return nombre del nivel
     */
    public String getNombre(){
        return nombre;
    }

    /**
     * Obtiene el número del nivel.
     *
     * @return número del nivel
     */
    public int getNumero(){
        return numero;
    }

    /**
     * Obtiene la dificultad del nivel.
     *
     * @return dificultad del nivel
     */
    public String getDificultad(){
        return dificultad;
    }

    /**
     * Agrega un obstáculo al nivel.
     *
     * @param obstaculo objeto de tipo Obstaculo
     */
    public void agregarObstaculo(Obstaculo obstaculo){
        obstaculos.add(obstaculo);
    }

    /**
     * Agrega un checkpoint al nivel.
     *
     * @param checkPoint objeto de tipo CheckPoint
     */
    public void agregarCheckPoint(CheckPoint checkPoint){
        checkPoints.add(checkPoint);
    }

    /**
     * Agrega un elemento dinámico al nivel.
     *
     * @param elemento objeto que implementa ElementoDinamico
     */
    public void agregarElementoDinamico(ElementoDinamico elemento){
        elementosDinamicos.add(elemento);
    }

    /**
     * Mueve todos los elementos dinámicos del nivel.
     *
     * Aplica el mismo movimiento a cada elemento registrado.
     *
     * @param direccion Dirección del movimiento (norte, sur, este, oeste)
     * @param distancia Cantidad de unidades a mover
     */
    public void moverElementosDinamicos(String direccion, int distancia) {
        for (ElementoDinamico e : elementosDinamicos) {
            e.mover(direccion, distancia);
        }
    }

    /**
     * Obtiene el inventario asociado al nivel.
     *
     * @return objeto Inventario
     */
    public Inventario getInventario() {
        return this.inventario;
    }

    /**
     * Muestra el estado actual del nivel en consola.
     *
     * Incluye información básica como nombre, número, dificultad,
     * inventario y cantidad de checkpoints.
     */
    public void mostrarEstado(){
        System.out.println("Nombre: " + nombre +
                "\nNúmero: " + numero +
                "\nDificultad: " + dificultad +
                "\nInventario: " + inventario +
                "CheckPoints: " + checkPoints.size());
    }
}
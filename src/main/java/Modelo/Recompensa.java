package Modelo;

import Interfaces.Inventariable;

/**
 * La clase Recompensa representa un objeto que puede ser obtenido
 * por el jugador como premio dentro del juego.
 *
 * Implementa la interfaz Inventariable, lo que permite que la recompensa
 * sea almacenada y gestionada dentro de un inventario.
 *
 * Cada recompensa tiene un nombre, un valor y un tipo que la clasifica.
 */
public class Recompensa implements Inventariable {

    /** Nombre de la recompensa */
    private String nombre;

    /** Valor numérico de la recompensa (puede representar puntos, dinero, etc.) */
    private int valor;

    /** Tipo de recompensa (por ejemplo: oro, experiencia, objeto especial) */
    private String tipo;

    /**
     * Constructor de la clase Recompensa.
     *
     * Inicializa los atributos principales del objeto.
     *
     * @param nombre Nombre de la recompensa
     * @param valor Valor asociado a la recompensa
     * @param tipo Tipo de recompensa
     */
    public Recompensa(String nombre, int valor, String tipo) {
        this.nombre = nombre;
        this.valor = valor;
        this.tipo = tipo;
    }

    /**
     * Obtiene el nombre de la recompensa.
     *
     * @return nombre de la recompensa
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Obtiene el valor de la recompensa.
     *
     * @return valor numérico
     */
    public int getValor(){
        return valor;
    }

    /**
     * Obtiene el tipo de recompensa.
     *
     * @return tipo de la recompensa
     */
    public String getTipo(){
        return tipo;
    }

    /**
     * Registra la recompensa en el sistema (por ejemplo, al agregarla al inventario).
     *
     * Implementación del método de la interfaz Inventariable.
     */
    @Override
    public void registrar() {
        System.out.println("Recompensa registrada: " + nombre);
    }

    /**
     * Elimina la recompensa del sistema (por ejemplo, al quitarla del inventario).
     *
     * Implementación del método de la interfaz Inventariable.
     */
    @Override
    public void borrar() {
        System.out.println("Recompensa eliminada: " + nombre);
    }

    /**
     * Devuelve una representación en texto de la recompensa.
     *
     * @return cadena con la información de la recompensa
     */
    @Override
    public String toString(){
        return "\nRecompensa: " + nombre +
                "\nValor: " + valor +
                "\nTipo: " + tipo;
    }
}
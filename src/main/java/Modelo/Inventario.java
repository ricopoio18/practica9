package Modelo;

import Interfaces.Inventariable;

import java.util.ArrayList;
import java.util.List;

/**
 * La clase Inventario representa una colección de objetos que pueden
 * ser almacenados dentro del juego.
 *
 * Gestiona elementos que implementan la interfaz Inventariable,
 * permitiendo agregar, eliminar, listar y buscar objetos.
 *
 * Controla también la capacidad máxima de almacenamiento.
 */
public class Inventario {

    /** Capacidad máxima de objetos que puede contener el inventario */
    private int capacidadMaxima;

    /** Lista de elementos almacenados en el inventario */
    private List<Inventariable> items;

    /**
     * Constructor de la clase Inventario.
     *
     * Inicializa la capacidad máxima y crea la lista de almacenamiento.
     *
     * @param capacidadMaxima número máximo de elementos permitidos
     */
    public Inventario(int capacidadMaxima){
        this.capacidadMaxima = capacidadMaxima;
        this.items = new ArrayList<>();
    }

    /**
     * Obtiene la capacidad máxima del inventario.
     *
     * @return capacidad máxima
     */
    public int getCapacidadMaxima() {
        return capacidadMaxima;
    }

    /**
     * Obtiene la lista de elementos del inventario.
     *
     * @return lista de objetos Inventariable
     */
    public List<Inventariable> getItems() {
        return items;
    }

    /**
     * Agrega un elemento al inventario.
     *
     * Verifica que el objeto no sea nulo y que exista espacio disponible.
     * Si se agrega correctamente, ejecuta el método registrar() del objeto.
     *
     * @param item objeto que implementa Inventariable
     * @return true si se agregó correctamente, false en caso contrario
     */
    public boolean agregarItem(Inventariable item){
        if (item == null){
            System.out.println("Item nulo");
            return false;
        }

        if (items.size() < capacidadMaxima) {
            items.add(item);
            item.registrar(); // uso de polimorfismo
            return true;
        }

        return false;
    }

    /**
     * Elimina un elemento del inventario.
     *
     * @param item objeto a eliminar
     * @return true si se eliminó correctamente, false si no estaba presente
     */
    public boolean eliminarItem(Inventariable item){
        return items.remove(item);
    }

    /**
     * Muestra todos los elementos del inventario en consola.
     *
     * Si está vacío, muestra un mensaje indicando que no hay elementos.
     */
    public void listarItems(){
        if(items.isEmpty()){
            System.out.println("Inventario vacío");
            return;
        }

        for(int i = 0; i < items.size(); i++){
            System.out.println((i + 1) + ". " + items.get(i));
        }
    }

    /**
     * Busca un elemento dentro del inventario por su nombre.
     *
     * La búsqueda se realiza utilizando el método toString() del objeto.
     *
     * @param nombre texto a buscar
     * @return objeto encontrado o null si no existe
     */
    public Inventariable buscarPorNombre(String nombre){
        for (Inventariable item : items){
            if(item.toString().toLowerCase().contains(nombre.toLowerCase())){
                return item;
            }
        }
        return null;
    }

    /**
     * Devuelve una representación en texto del inventario.
     *
     * @return cadena con todos los elementos o mensaje de vacío
     */
    @Override
    public String toString(){
        if(items.isEmpty()){
            return "Inventario vacío";
        }

        String resultado = "Inventario:\n";

        for(int i = 0; i < items.size(); i++){
            resultado += (i + 1) + ". " + items.get(i) + "\n";
        }

        return resultado;
    }
}
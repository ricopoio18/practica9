package Persistencia;

import Modelo.*;
import GestorJuego.*;
import Interfaces.Inventariable;
import java.io.*;

/**
 * Clase encargada de la persistencia del juego.
 * Permite guardar y cargar el estado del jugador y el nivel
 * mediante archivos de texto.
 */
public class ArchivoPersistencia {

    public static void guardarPartida(String archivo, GestorJuego gestor, Escarabajo jugador) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo))) {

            writer.write("nivel=" + gestor.getNumeroNivel());
            writer.newLine();
            writer.write("x=" + jugador.getPosicionX());
            writer.newLine();

            for (Inventariable item : gestor.getInventarioJugador().getItems()) {
                if (item != null) {
                    writer.write("item=" + item.toString());
                    writer.newLine();
                }
            }

            System.out.println("Partida guardada");

        } catch (IOException e) {
            System.err.println("Error al guardar: " + e.getMessage());
        }
    }

    public static void cargarPartida(String archivo, GestorJuego gestor, Escarabajo jugador) {
        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {

            String linea;

            while ((linea = reader.readLine()) != null) {

                if (linea.startsWith("nivel=")) {
                    int nivel = Integer.parseInt(linea.split("=")[1]);
                    gestor.cargarNivel(nivel);
                }
                if (linea.startsWith("x=")) {
                    int x = Integer.parseInt(linea.split("=")[1]);
                    jugador.setPosicion(x, jugador.getPosicionY());
                }

            }
            System.out.println("Partida cargada");

        } catch (IOException e) {
            System.err.println("Error al cargar: " + e.getMessage());
        }
    }
}
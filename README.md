EL SIGUIENTE ARCHIVO README FUE REALIZADO POR IA PERDO SUPERVISADO Y MODIFICADO POR EL ALUMNO

🐞 Practica 9 - Juego de Combate de Escarabajos

🎮 Descripción del juego

Este proyecto consiste en un videojuego desarrollado en Java donde dos
escarabajos se enfrentan en una arena de combate.

El objetivo principal es empujar al oponente fuera del escenario
(ring-out). El jugador puede utilizar armas y aprovechar objetos
especiales como la Fruta Poder, la cual aumenta su fuerza para facilitar
la victoria en niveles más difíciles.

El juego incluye: - Sistema de combate - Inventario de objetos -
Progresión por niveles - Checkpoints (guardado de progreso) - Mejora de
habilidades

⚙️ Instrucciones de ejecución

1.  Clona el repositorio: git clone
https://github.com/ricopoio18/practica9.git

2.  Abre el proyecto en un IDE (IntelliJ IDEA o Eclipse recomendado)

3.  Ejecuta la clase principal: "PruebaCombateFX"

4.  Requisitos:

-   Java JDK 17 o superior
-   JavaFX configurado (si el proyecto usa interfaz gráfica)

🎹 Controles del teclado

A y D para moverse a la derecha e izquierda, espacio para pausar el juego y la tecla UP o flecha arriba para empujar al enemigo

🖼️ Capturas de pantalla 

Agrega aquí tus imágenes o GIF del juego: Gameplay.png Combate.png

<img width="800" height="438" alt="image" src="https://github.com/user-attachments/assets/b36f84ca-1b91-4ced-b154-4cf26da7a895" />

<img width="810" height="421" alt="image" src="https://github.com/user-attachments/assets/99c0ee01-b451-4ab3-97c0-4db5aaf7b819" />


Ejemplo de gameplay

1.  El jugador inicia en el Nivel 1
2.  Obtiene la Fruta Poder en su inventario
3.  Derrota al enemigo empujándolo fuera del escenario
4.  Avanza al Nivel 2 con mayor dificultad
5.  Si pierde, puede volver desde el último checkpoint

🧠 Características técnicas

-   Programación Orientada a Objetos (POO)
-   Uso de herencia y polimorfismo
-   Interfaz Inventariable
-   Sistema de inventario dinámico
-   Control del juego con GestorJuego
-   Mecánica de cooldown en ataques
-   Sistema de checkpoints

📦 Estructura del proyecto

Modelo/ Escarabajo.java Arma.java Inventario.java FrutaPoder.java
NivelCombate.java CheckPoint.java

GestorJuego/ GestorJuego.java

Interfaces/ Inventariable.java

🔗 Repositorio
https://github.com/ricopoio18/practica9.git

👨‍💻 Autor

Josue Rodrigo

HAY ALGUNOS CAMBIOS POR CUESTIONES CREATIVAS.
1. El jugador solo se mueve al este o al oeste por el mismo hecho de que esta en un tronco.
2. No hay obstáculos porque se trata de una pelea tipo zumo donde el perdedor sale del límite.
3. Los checkpoints estan en forma de pregunta para el usuario por si quiere guardar la partida cuando gana un nivel.

(ACTUALIZACIÓN PARA LA PRÁCTICA 10).
Se creó la clase ArchivoPersistencia que contiene los métodos para guardar y cargar un archivo de texto.
El archivo contiene el nivel del juego, la posición en x y la lista de los objetos del inventario.
Para poder guardar un punto de la partida en el archivo se debe presionar la tecla "G".
Para cargar partida en el último punto guardado en el archivo se presiona "C".

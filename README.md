# 👑 Super Elizabeth Sis 👑
**Trabajo Práctico Final - Programación 1 - 1c2026 - UNGS**

Super Elizabeth Sys es un videojuego de plataformas en 2D desarrollado íntegramente en **Java** utilizando la librería gráfica `Entorno` provista por la cátedra. El objetivo principal es guiar a nuestro personaje **Elizabeth** para que pueda avanzar sobre el escenario, enfrentando a sus enemigos y llegar finalmente con vida al castillo.

---

## Características del Juego 🎮

* **Generación de Mapa:** Las islas se crean de manera aleatoria utilizando un algoritmo inteligente en "patrón de escalera". Esto asegura que el camino siempre sea continuo y ganable.
* **Cámara con Scroll Horizontal:** El mapa se desplaza suavemente hacia la izquierda a medida que la princesa avanza, simulando un mundo continuo.
* **Mecánica de Combate:** Elizabeth puede defenderse lanzando proyectiles mágicos en la dirección exacta donde apuntes con el cursor del mouse.
* **Ejército de Enemigos Continuo:** Sistema de *respawn* automático de enemigos. El juego garantiza que siempre haya un mínimo de 5 enemigos en pantalla persiguiendo a la princesa; si uno muere, otro aparece al final del mapa.
* **Audio y Efectos:** Música de fondo ambiental en bucle y efectos de sonido para el salto.
* **Pantallas de Estado:** Pantallas personalizadas de *Game Over* y *Victoria* que detienen por completo las físicas del juego para dar un cierre limpio.

---

## Tecnologías y Conceptos Aplicados 🖥️

Este proyecto fue desarrollado aplicando conceptos clave de Programación Orientada a Objetos (POO):
* **Lenguaje:** Java 
* **Librería Gráfica:** Entorno 2D (UNGS)
* **Conceptos:** Manejo de objetos con sus respectivas clases, constructores y métodos, trabajo sobre arreglos de objetos.

---

## Cómo Ejecutarlo 🤔

1. Cloná este repositorio o descargá el código fuente:
   ```bash
   git clone [https://github.com/ValentinaJaimes/https://github.com/ValentinaJaimes/TP-Progra1-1c2026.git]
   
2. Controles de Juego 🕹️

* **Flecha Derecha (`→`):** Caminar hacia adelante.
* **Flecha Izquierda (`←`):** Caminar hacia atrás.
* **Flecha Arriba (`↑`):** Saltar.
* **Click Izquierdo del Mouse:** Disparar en dirección del cursor.

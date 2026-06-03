package juego;

import java.awt.Image;
import java.util.Random;
import entorno.Entorno;
import entorno.Herramientas;

public class Mapa {
	double x;  
	double y;
	double ancho;
	double alto;
	
	Entorno entorno;
	Image fondoMapa;
	Image castillo;
	Isla [] islas;
	int indIslas;
	int islasEnMapa; 
	int tamanio; 
	int random;
	String nombre;
	double castilloX;
	double castilloY;
	double desplazamientoTotal=0;
	
	// constructor
	public Mapa(int tamanio) {
		castillo = Herramientas.cargarImagen("castillo.png");
		this.tamanio = tamanio;
		islas = new Isla[this.tamanio];
		islasEnMapa = 0; 
		castilloX = 4000;
		castilloY = 500;
		Random r = new Random(); 
		
		int nivelRandom = r.nextInt(3); // primer nivel random
		
		// bucle para los niveles
		for(int i = 1; i < this.tamanio; i++) {    
			int nivel = nivelRandom;
			if(nivel == 0) { 
				y = 500;  
			}
			else if(nivel == 1) {
				y = 350; 
			}
			else { 
				y = 200;
			}
			int imgj = r.nextInt(3) + 1;
			nombre = "Isla" + imgj + ".png";
			x = 150 + i * 220;
			islas[islasEnMapa] = new Isla(x, y, nombre);
			islasEnMapa++;

			// Defino un patron tipo escalera pero con random 
			if (nivel == 0) {
				nivelRandom = 1; 
			} else if (nivel == 1) {
				nivelRandom = r.nextBoolean() ? 2 : 0; 
			} else {
				nivelRandom = 1; 
			}
		}
		
		this.castilloX = x + 400;
		this.castilloY = 500;
	} 
	
	public Isla obtenerPrimeraIsla() {
		if (islasEnMapa > 0 && islas[0] != null) {
			return islas[0];
		}
		return null;
	}
	
	public void dibujar(Entorno e) {
		for(int i = 0; i < islasEnMapa; i++) {
			if(islas[i] != null) {
				if (islas[i].y == 500 && islas[i].x > this.castilloX - 100) {
					continue;
				}
				islas[i].dibujar(e); 
			}
		}
		e.dibujarImagen(castillo, castilloX, castilloY, 0, 0.50);
	}
	
	public void moverMapa(double dx) {
		desplazamientoTotal += dx;
		
		for(int i = 0; i < islasEnMapa; i++) {
			if (islas[i] != null) {
				islas[i].x += dx;
				islas[i].bordeDerecho += dx;
				islas[i].bordeIzquierdo += dx;
			}
		}
		castilloX += dx;
	}
}
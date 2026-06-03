package juego;

import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Proyectil {
	double x;  
	double y;
	double ancho;
	double alto;
	
	double bordeSuperior;
	double bordeInferior;
	double bordeDerecho;
	double bordeIzquierdo;
	double escala;
	
	double velocidad;
	double dx;
	double dy;
	boolean activo;
	Image proyectil;
	Entorno e;
	
	public Proyectil(double xInicial, double yInicial, double mouseX, double mouseY) {
		this.x = xInicial;
		this.y = yInicial;
		this.velocidad = 10;
		this.escala = 0.15;
		this.proyectil = Herramientas.cargarImagen("proyectil.png"); //pongo el nombre del archivo 
		this.alto = proyectil.getHeight(e)*this.escala; //al alto tengo que multiplicarlo x el valor de la escala
		this.ancho = proyectil.getWidth(e)*this.escala;
		this.activo = true;
		double distanciaX = mouseX - x;
		double distanciaY = mouseY - y;

		double modulo = Math.sqrt(distanciaX * distanciaX + distanciaY * distanciaY);
		dx = distanciaX / modulo;
		dy = distanciaY / modulo;
		actualizarBordes();
	}
	
	public void mover() {
		x += dx * velocidad;
		y += dy * velocidad;
		actualizarBordes();
	}
	
	public void dibujar(Entorno entorno) {
		if(activo) {
			entorno.dibujarImagen(proyectil,x,y,0,escala);
		}
	}
	
	public boolean fueraDePantalla() {
		return x < 0 || x > 800 || y < 0 || y > 600;
	}
	
	public void actualizarBordes(){
		this.bordeDerecho = this.x + this.ancho /2;
		this.bordeIzquierdo = this.x - this.ancho /2;
		this.bordeSuperior = this.y - this.alto /2;
		this.bordeInferior = this.y + this.alto /2;
	}
}

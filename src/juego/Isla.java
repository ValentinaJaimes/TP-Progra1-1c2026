package juego;

import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Isla {
	double x;  //voy a necesitarlos para desp comparar bordes y ver colisiones
	double y;
	double ancho;
	double alto;
	
	double bordeSuperior;
	double bordeInferior;
	double bordeDerecho;
	double bordeIzquierdo;
	double escala;
	Image isla;
	Entorno entorno;
	String nombreIsla;
	
	//constructor
	public Isla(double x, double y, String nombre) {
		this.x = x;
		this.y = y;
		this.escala = 0.06;
		this.nombreIsla = nombre;
		this.isla = Herramientas.cargarImagen(nombre); //pongo el nombre del archivo 
		this.alto = isla.getHeight(null)*this.escala; //al alto tengo que multiplicarlo x el valor de la escala
		this.ancho = isla.getWidth(null)*this.escala;
		this.bordeDerecho = this.x + this.ancho /2;
		this.bordeIzquierdo = this.x - this.ancho /2;
		this.bordeSuperior = this.y - this.alto /2;
		this.bordeInferior = this.y + this.alto /2;
	}
	/*public void actualizarBordes(){
		this.bordeDerecho = this.x + this.ancho /2;
		this.bordeIzquierdo = this.x - this.ancho /2;
		this.bordeSuperior = this.y - this.alto /2;
		this.bordeInferior = this.y + this.alto /2;
	}*/
	public void dibujar(Entorno e) {
		e.dibujarImagen(this.isla, this.x, this.y, 0, this.escala);
	}
}

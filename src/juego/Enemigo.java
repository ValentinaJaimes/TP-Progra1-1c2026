package juego;

import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Enemigo {
	double x;  
	double y;
	double ancho;
	double alto;
	
	double bordeSuperior;
	double bordeInferior;
	double bordeDerecho;
	double bordeIzquierdo;
	
	Image enem; 
	Image enemIzq;
	Image enemDer;
	double velocidad;
	double escala;
	double direccion;
	double alturaMaxima;
	Entorno entorno;
	
	public Enemigo(double x, double y, double direccion, Entorno e) {
		
		this.x = x;
		this.y = y;
		this.direccion=direccion;
		this.velocidad = 1.5;
		this.escala = 0.10;
		this.alturaMaxima = 150; //tiene que estar relacionado con los espacios que le vamos a dar a cada isla
		this.enem = Herramientas.cargarImagen("enem.png"); //pongo el nombre del archivo 
		this.enemIzq = Herramientas.cargarImagen("enemIzq.png");
		this.enemDer = Herramientas.cargarImagen("enemDer.png");
		this.alto = enem.getHeight(e)*this.escala; //al alto tengo que multiplicarlo x el valor de la escala
		this.ancho = enem.getWidth(e)*this.escala; //hago lo mismo con el ancho
	}
	
	//MÉTODOS
		public void dibujar(Entorno e) {
			e.dibujarImagen(this.enem, this.x, this.y, 0, this.escala); //paso imagen, paso el x, paso el y, paso angulo, paso escala
		}
		
		public void actualizarBordes() {
			this.bordeDerecho = this.x + this.ancho /2;
			this.bordeIzquierdo = this.x - this.ancho /2;
			this.bordeSuperior = this.y - this.alto /2;
			this.bordeInferior = this.y + this.alto /2;
		}
		
		
		public void mover() {
			this.x += this.velocidad * this.direccion;
			if(this.direccion > 0) {
		        this.enem = enemDer;
		    }
		    else {
		        this.enem = enemIzq;
		    }
		}
		
		
		public boolean colisionIsla(Mapa m) {
			actualizarBordes();
			for(int i = 0; i < m.tamanio; i++) {
				if(m.islas[i] != null) {
					if(this.bordeDerecho >= m.islas[i].bordeIzquierdo &&
					   this.bordeIzquierdo <= m.islas[i].bordeDerecho &&
					   this.bordeInferior >= m.islas[i].bordeSuperior &&
					   this.bordeSuperior <= m.islas[i].bordeInferior) {
						return true;
					}
				}
			}
			return false;
		}
		
		
		public boolean fueraDePantalla() {
			return this.x < -100 || this.x > 900;
		}
		
}

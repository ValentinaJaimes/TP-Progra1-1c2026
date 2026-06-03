package juego;

import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Princesa {
	double x;  //voy a necesitarlos para desp comparar bordes y ver colisiones
	double y;
	double ancho;
	double alto;
	
	double bordeSuperior;
	double bordeInferior;
	double bordeDerecho;
	double bordeIzquierdo;
	
	Image prin; // por cada imagen que tenga de la princesa, tengo que cargarla
	Image prinDer;
	Image prinIzq;
	Image prinFrente;
	int cantVidas;
	double velocidad;
	char direccion; //si le pongo una 'D', se mueve a la derecha / si pongo una 'I', se mueve a la izq
	double escala;
	double alturaMaxima;
	boolean saltando;
	boolean cayendo;
	boolean disparando;
	Entorno entorno;
	double yy;
	
	//una vez que tengo instanciada la clase, armo el constructor con sus respectivos parámetros
	public Princesa(double x, double y, double velocidad ,Entorno entorno) {
		//this.entorno = entorno; //pasar cosas a la clase princesa de cualquier obj que esté trabajando en la clase juego
		this.x = x;
		this.y = y;
		this.velocidad = 3.0;
		this.escala = 0.50;
		this.direccion ='d'; //comienza cuando va a la derecha
		this.alturaMaxima = 180; //tiene que estar relacionado con los espacios que le vamos a dar a cada isla
		this.cantVidas = 10; 
		this.saltando = false;
		this.cayendo = false;
		this.disparando = false;
		this.prin = Herramientas.cargarImagen("princesaQuieta.png"); //pongo el nombre del archivo 
		this.prinDer = Herramientas.cargarImagen("princesaDer.png");
		this.prinIzq = Herramientas.cargarImagen("princesaIzq.png");
		this.prinFrente = Herramientas.cargarImagen("princesaQuieta.png");
		this.alto = prin.getHeight(null)*this.escala; //al alto tengo que multiplicarlo x el valor de la escala
		this.ancho = prin.getWidth(null)*this.escala; //hago lo mismo con el ancho
		this.yy=0.0;
	}
	
	//MÉTODOS
	public void dibujar(Entorno entorno) {
		entorno.dibujarImagen(this.prin, this.x, this.y, 0, this.escala); //paso imagen, paso el x, paso el y, paso angulo, paso escala
	}
	
	public void mover(char dir) {
		if(dir=='d'){
			this.x +=this.velocidad;
			this.prin = prinDer;
			if(this.x + this.ancho/2 > 800) {
				this.x = 800-this.ancho/2;
			}
		}
		if(dir=='i') {
			this.x -=this.velocidad;
			this.prin = prinIzq;
			if(this.x - this.ancho/2<0) {
				this.x=this.ancho/2;
				} 
			}
		
	}
	
	public void saltar(Mapa mapa) {
		this.y -=5;
		actualizarBordes();
		if(colisionCabezaIsla(mapa) || this.y - this.alto/2 < 0) {
			this.saltando=false;
			this.cayendo=true;
			this.y +=this.velocidad;
		}
			if(this.yy-this.y > this.alturaMaxima) {
				this.saltando=false;
				this.cayendo=true;	
			}
		} 
	
	
	public void caer(Mapa mapa) {
	    this.y += 3;
	    actualizarBordes();
	    //recorro con un for para que colisione pie con isla cuando salta y cae
	    for(int i = 0; i < mapa.tamanio; i++) {
	        if(mapa.islas[i] != null) {
	            Isla isla = mapa.islas[i];
	            if(this.bordeInferior >= isla.bordeSuperior && this.bordeInferior <= isla.bordeSuperior + 10 &&
	               this.bordeDerecho >= isla.bordeIzquierdo && this.bordeIzquierdo <= isla.bordeDerecho) {
	            	
	                this.y = isla.bordeSuperior - this.alto/2;
	                this.cayendo = false;
	                return; }
	        }
	    }
	    
	}
	
	 
	public boolean colisionPieIsla(Mapa m) {
		actualizarBordes();
		for (int i=0; i < m.tamanio;i++ ) {
			if(m.islas[i] != null) {
				if(Math.abs(this.bordeInferior - m.islas[i].bordeSuperior) < 6 &&
						this.bordeDerecho >= m.islas[i].bordeIzquierdo && this.bordeIzquierdo <= m.islas[i].bordeDerecho)  {
					return true;
				}                           
			}
		}
		return false; 
	}
	
	public boolean colisionCabezaIsla(Mapa m){
		actualizarBordes();
		for (int i=0; i < m.tamanio;i++ ) {
			if(m.islas[i] != null) {
				if(this.bordeSuperior <= m.islas[i].bordeInferior &&
			               this.bordeSuperior >= m.islas[i].bordeInferior - 10 &&
			               this.bordeDerecho >= m.islas[i].bordeIzquierdo &&
			               this.bordeIzquierdo <= m.islas[i].bordeDerecho) {
					return true;
				} }
		}
		return false; 
	}
	
	
	public void actualizarBordes() {
		this.bordeDerecho = this.x + this.ancho /2;
		this.bordeIzquierdo = this.x - this.ancho /2;
		this.bordeSuperior = this.y - this.alto /2;
		this.bordeInferior = this.y + this.alto /2;
	}
}

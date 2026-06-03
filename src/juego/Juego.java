package juego;


import java.awt.Color;
import java.awt.Image;
import java.util.Random;

import entorno.Entorno;
import entorno.Herramientas;
import entorno.InterfaceJuego;

public class Juego extends InterfaceJuego
{
	// El objeto Entorno que controla el tiempo y otros
	private Entorno entorno;
	Princesa princesa;
	Isla isla;
	Mapa mapa;
	int tamanio;
	Image fondoMapa;
	Image corazon;
	Image pantallaGana;
	Image pantallaPierde;
	Proyectil proyectil;
	Enemigo[] enemigos;
	int enemigosMinimos;
	Random random;
	int vidas;
	boolean juegoPerdido;
	boolean llegoAlFinal;
	// Variables y métodos propios de cada grupo
	// ...
	
	Juego()
	{
		// Inicializa el objeto entorno
		this.entorno = new Entorno(this, "Proyecto para TP", 800, 600);
		
		// Inicializar lo que haga falta para el juego
		// ...
		tamanio = 15;
		mapa = new Mapa(tamanio);
		Isla primera = mapa.obtenerPrimeraIsla();
			double xInicio = 400; 
			double yInicio = 100; 
		
			if (primera != null) {
				xInicio = primera.x;       
				yInicio = primera.y - 120; 
			}
		princesa = new Princesa(xInicio, yInicio, 2.0, this.entorno);
		fondoMapa = Herramientas.cargarImagen("fondoMapa.jpg");
		corazon = Herramientas.cargarImagen("corazonVidas.png");
		pantallaGana= Herramientas.cargarImagen("ganador.png");
		pantallaPierde= Herramientas.cargarImagen("perdedor.png");
		enemigos = new Enemigo[20];
		enemigosMinimos = 5;
		random = new Random();
		this.vidas = 5;
		this.juegoPerdido = false;
		llegoAlFinal=false;
		
		
		// Inicia el juego!
		this.entorno.iniciar();
		Herramientas.loop("sonidoFondo.wav");
	}

	/**
	 * Durante el juego, el método tick() será ejecutado en cada instante y 
	 * por lo tanto es el método más importante de esta clase. Aquí se debe 
	 * actualizar el estado interno del juego para simular el paso del tiempo 
	 * (ver el enunciado del TP para mayor detalle).
	 */
	public void tick()
	{
		//Detener el juego
		if(juegoPerdido) {
			entorno.dibujarImagen(pantallaPierde, 400, 300, 0, 0.5);
		    return;
		}
		// Procesamiento de un instante de tiempo
		// ...
		entorno.dibujarImagen(fondoMapa,400,300,0,1.70);
		mapa.dibujar(entorno);
		princesa.dibujar(entorno);
		
		//Vidas
		for(int i = 0; i < vidas; i++) {
			entorno.dibujarImagen(corazon, 40 + i*40, 30, 0, 0.08);
		}
		
		
		
		//Movimiento Princesa
		if(entorno.estaPresionada(entorno.TECLA_DERECHA)) {
			if (!llegoAlFinal) {
				if (princesa.x < 400) {
					princesa.mover('d');
				}
				else {
					if(mapa.castilloX>700){
						mapa.moverMapa(-3);
					} else {
						princesa.mover('d');
					}
					princesa.prin=princesa.prinDer;
				}
			}
			
			
		}
		if(entorno.estaPresionada(entorno.TECLA_IZQUIERDA)) {
			princesa.mover('i');
		} 
		if(entorno.sePresiono(entorno.TECLA_ARRIBA)&& !princesa.saltando && !princesa.cayendo) {
			princesa.yy=princesa.y;
			princesa.saltando=true;
			Herramientas.play("sonidoSalto.wav");
		}
		
		//Caída Princesa
		if(princesa.saltando) {
			princesa.saltar(mapa);
		}
		if(!princesa.colisionPieIsla(mapa) && !princesa.saltando) { //ver otras colisiones para no dar traspaso
			princesa.cayendo=true;
		}
		if (princesa.cayendo) {
			princesa.caer(mapa);
		}
		
		//respawn si cae al vacio
		if (princesa.y > 650) {
			vidas--;
			mapa.moverMapa(-mapa.desplazamientoTotal);
			Isla primera=mapa.obtenerPrimeraIsla();
			if(primera!=null) {
				this.princesa.x=primera.x;
				this.princesa.y=primera.y - 100;
			}
			princesa.saltando=false;
			princesa.cayendo=false;
		}
		
		
		
		//mantener enemigos minimos
				if(enemigosVivos() < enemigosMinimos) {
					generarEnemigo();
				}
				for(int i = 0; i < enemigos.length; i++) {
					if(enemigos[i] != null) {
						enemigos[i].mover();
						enemigos[i].dibujar(entorno);
						
						//colision con isla
						if (enemigos[i] != null && enemigos[i].colisionIsla(mapa)) {
							enemigos[i]=null;
						}

						// eliminar enemigos fuera de pantalla
						if(enemigos[i] != null && enemigos[i].fueraDePantalla()) {
							enemigos[i] = null;
						}
						
						//Detectar choque
						if(enemigos[i]!=null) {
							double px = princesa.x;
							double py = princesa.y;
							double ex = enemigos[i].x;
							double ey = enemigos[i].y;
							
							double distancia = Math.sqrt((px - ex)*(px - ex) + (py-ey)*(py-ey));
							
							//colision
							if (distancia < 30) {
								vidas--;
								enemigos[i] = null;
								break;
							}
						}
					}
				}
						
				if(vidas <=0) {
					juegoPerdido = true;
				}
	
		
		//Disparo Princesa
		if(entorno.sePresionoBoton(entorno.BOTON_IZQUIERDO) && proyectil == null) {
		    proyectil = new Proyectil(princesa.x, princesa.y, entorno.mouseX(), entorno.mouseY() );
			}
		
		if(proyectil != null) {
			proyectil.mover();
			proyectil.dibujar(entorno);
			
			for(int i = 0; i < enemigos.length; i++) {
				 if(enemigos[i] != null) {
					    double px = proyectil.x;
			            double py = proyectil.y;

			            double ex = enemigos[i].x;
			            double ey = enemigos[i].y;

			            double distancia = Math.sqrt((px - ex)*(px - ex) + (py - ey)*(py - ey));
			            
			            if(distancia < 30) {
			            	enemigos[i] = null;   // muere enemigo
			                proyectil = null;     // desaparece proyectil
			                break; 
			            	
			            }
				 }
			}
			
			// fuera de pantalla
		    if(proyectil != null && proyectil.fueraDePantalla()) {
		        proyectil = null;
		    }    
		}
		if (Math.abs(princesa.x - mapa.castilloX)< 50) {
			llegoAlFinal=true;
			entorno.dibujarImagen(pantallaGana, 400, 300, 0, 0.7);
			return;
		}
		
		
		
	} //llave que cierra tick!
	
	
	//metodos auxiliares
	public int enemigosVivos() {
		int contador = 0;

		for(int i = 0; i < enemigos.length; i++) {
			if(enemigos[i] != null) {
				contador++;
			}
		}

		return contador;
	}
	
	public void generarEnemigo() {

		for(int i = 0; i < enemigos.length; i++) {

			if(enemigos[i] == null) {
				int lado = random.nextInt(2);
				double y = random.nextInt(600);
				
				if (lado == 0) {
					enemigos[i] = new Enemigo(-50.0, y, 1.0, entorno);
				} else {
					enemigos[i] = new Enemigo(850.0, y, -1.0, entorno);
				}
				
				break;

				
			}
		}
		
	}
	

	@SuppressWarnings("unused")
	public static void main(String[] args)
	{
		Juego juego = new Juego();
	}
}

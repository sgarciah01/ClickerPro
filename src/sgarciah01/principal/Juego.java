package sgarciah01.principal;

import java.util.ArrayList;

import sgarciah01.pantallas.PantallaJuego;

public class Juego implements Runnable {

	/** CONSTANTES **/
	public static final int MAX_ACCIONES_EJECUTADAS = 6;

	public static final String MSG_MEJORANDO = "Ejecutando Mejora:";
	public static final String MSG_ATAQUE = "ATAQUE";
	public static final String MSG_DEFENSA = "DEFENSA";
	public static final String MSG_VIDA = "VIDA MÁXIMA";
	public static final String MSG_INDICE_CRITICO = "ÍNDICE CRÍTICO";
	public static final String MSG_DINERO = "GENERADOR MONEDAS";

	/** VARIABLES DEL VALOR DEL PRECIO DE LAS MEJORAS **/
	private int precioMejoraVida;
	private int precioMejoraAtaque;
	private int precioMejoraDefensa;
	private int precioMejoraCritico;
	private int precioMejoraMonedas; // Será 10 veces más que el resto de precios
	private int precioPocion; // Será calculado en función a las skills del personaje

	/** VARIABLES NIVEL MEJORAS **/
	private int nivelMejoraVida;
	private int nivelMejoraAtaque;
	private int nivelMejoraDefensa;
	private int nivelMejoraCritico;
	private int nivelMejoraMonedas;

	/** PERSONAJE **/
	private Personaje personaje;

	/** MENSAJES A MOSTRAR **/
	private ArrayList<String> mensajesMostrar;
	
	/** PANTALLA DE JUEGO **/
	private PantallaJuego pantallaJuego;

	/**
	 * Constructor por defecto
	 */
	public Juego(PantallaJuego pantallaJuego) {
		// Creamos el personaje
		personaje = new Personaje(30, 30, 15, 5, 0);
		this.pantallaJuego = pantallaJuego;
		
		// El precio inicial de las mejoras será 10
		precioMejoraAtaque = 10;
		precioMejoraDefensa = 10;
		precioMejoraVida = 10;
		precioMejoraCritico = 10;
		precioPocion = calcularPrecioPocion();
		precioMejoraMonedas = 100;

		// Nivel de mejoras, inicialmente 1 para todos
		nivelMejoraVida = 1;
		nivelMejoraAtaque = 1;
		nivelMejoraDefensa = 1;
		nivelMejoraCritico = 1;
		nivelMejoraMonedas = 1;

		// Generamos el hilo
		new Thread(this).start();
	}

	// ***** GETTERS Y SETTERS ***** //
	public int getPrecioMejoraVida() {
		return precioMejoraVida;
	}

	public void setPrecioMejoraVida(int precioMejoraVida) {
		this.precioMejoraVida = precioMejoraVida;
	}

	public int getPrecioMejoraAtaque() {
		return precioMejoraAtaque;
	}

	public void setPrecioMejoraAtaque(int precioMejoraAtaque) {
		this.precioMejoraAtaque = precioMejoraAtaque;
	}

	public int getPrecioMejoraDefensa() {
		return precioMejoraDefensa;
	}

	public void setPrecioMejoraDefensa(int precioMejoraDefensa) {
		this.precioMejoraDefensa = precioMejoraDefensa;
	}

	public int getPrecioMejoraCritico() {
		return precioMejoraCritico;
	}

	public void setPrecioMejoraCritico(int precioMejoraCritico) {
		this.precioMejoraCritico = precioMejoraCritico;
	}

	public int getPrecioMejoraMonedas() {
		return precioMejoraMonedas;
	}

	public void setPrecioMejoraMonedas(int precioMejoraMonedas) {
		this.precioMejoraMonedas = precioMejoraMonedas;
	}

	public int getPrecioPocion() {
		return precioPocion;
	}

	public void setPrecioPocion(int precioPocion) {
		this.precioPocion = precioPocion;
	}

	public int getNivelMejoraVida() {
		return nivelMejoraVida;
	}

	public void setNivelMejoraVida(int nivelMejoraVida) {
		this.nivelMejoraVida = nivelMejoraVida;
	}

	public int getNivelMejoraAtaque() {
		return nivelMejoraAtaque;
	}

	public void setNivelMejoraAtaque(int nivelMejoraAtaque) {
		this.nivelMejoraAtaque = nivelMejoraAtaque;
	}

	public int getNivelMejoraDefensa() {
		return nivelMejoraDefensa;
	}

	public void setNivelMejoraDefensa(int nivelMejoraDefensa) {
		this.nivelMejoraDefensa = nivelMejoraDefensa;
	}

	public int getNivelMejoraCritico() {
		return nivelMejoraCritico;
	}

	public void setNivelMejoraCritico(int nivelMejoraCritico) {
		this.nivelMejoraCritico = nivelMejoraCritico;
	}

	public int getNivelMejoraMonedas() {
		return nivelMejoraMonedas;
	}

	public void setNivelMejoraMonedas(int nivelMejoraMonedas) {
		this.nivelMejoraMonedas = nivelMejoraMonedas;
	}

	public Personaje getPersonaje() {
		return personaje;
	}

	public void setPersonaje(Personaje personaje) {
		this.personaje = personaje;
	}

	public ArrayList<String> getMensajesMostrar() {
		return mensajesMostrar;
	}

	public void setMensajesMostrar(ArrayList<String> mensajesMostrar) {
		this.mensajesMostrar = mensajesMostrar;
	}
	// ***** GETTERS Y SETTERS ***** //
	
	/**
	 * El precio de la poción será 10 * la vida que recupere el personaje.
	 * Recuperará la mitad de su vida máxima
	 * 
	 * @return (10 * VIDA MÁXIMA PERSONAJE / 2)
	 */
	private int calcularPrecioPocion() {
		return 10 * (personaje.getVidaMaxima() / 2);
	}

	// *********************************************************************** //
	// *************** 			MEJORAS DEL PERSONAJE 			************** //
	// *********************************************************************** //
	/**
	 * Si el personaje tiene monedas suficientes, mejora el ataque del personaje.
	 * A su vez, aumenta en 1 el nivel de mejora y aumenta el precio de mejora.
	 */
	public void mejorarAtaquePersonaje () {
		if (personaje.getMonedas() >= precioMejoraAtaque) {
			personaje.mejorarAtaque(precioMejoraAtaque);
			nivelMejoraAtaque++;
			precioMejoraAtaque = 10 * (int) Math.pow(2, nivelMejoraAtaque);			
		}		
	}
	
	/**
	 * Si el personaje tiene monedas suficientes, mejora la defensa del personaje.
	 * A su vez, aumenta en 1 el nivel de mejora y aumenta el precio de mejora.
	 */
	public void mejorarDefensaPersonaje () {
		if (personaje.getMonedas() >= precioMejoraDefensa) {
			personaje.mejorarDefensa(precioMejoraDefensa);
			nivelMejoraDefensa++;
			precioMejoraDefensa = 10 * (int) Math.pow(2, nivelMejoraDefensa);
		}
	}
	
	/**
	 * Si el personaje tiene monedas suficientes, mejora la vida máxima del personaje.
	 * A su vez, aumenta en 1 el nivel de mejora y aumenta el precio de mejora.
	 */
	public void mejorarVidaMaximaPersonaje () {
		if (personaje.getMonedas() >= precioMejoraVida) {
			personaje.mejorarVidaMaxima(precioMejoraVida);
			nivelMejoraVida++;
			precioMejoraVida = 10 * (int) Math.pow(2, nivelMejoraVida);
		}		
	}
	
	/**
	 * Si el personaje tiene monedas suficientes, mejora el generador de monedas del personaje.
	 * A su vez, aumenta en 1 el nivel de mejora y aumenta el precio de mejora.
	 */
	public void mejorarGeneradorMonedas () {
		if (personaje.getMonedas() >= precioMejoraMonedas) {
			nivelMejoraMonedas++;
			precioMejoraMonedas = 150 * (int) Math.pow(2, nivelMejoraAtaque);
		}
	}
	
	/**
	 * Si el personaje tiene monedas suficientes, mejora el índice de golpes críticos del personaje.
	 * A su vez, aumenta en 1 el nivel de mejora y aumenta el precio de mejora.
	 */
	public void mejorarIndiceCritico () {
		if (personaje.getMonedas() >= precioMejoraCritico) {
			nivelMejoraCritico++;
			precioMejoraCritico = 10 * (int) Math.pow(2, nivelMejoraCritico);
		}
	}
	
	/**
	 * Será el encargado de generar el dinero cada cierto tiempo
	 */
	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(400);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			// Generamos monedas en el personaje
			personaje.generarMonedas(nivelMejoraMonedas);
		}

	}

}

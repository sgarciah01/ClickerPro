package sgarciah01.principal;

import java.util.ArrayList;

import sgarciah01.pantallas.PantallaJuego;

public class Juego implements Runnable {

	/** CONSTANTES **/
	public static final int MAX_ACCIONES_EJECUTADAS = 6;

	public static final String MSG_MEJORANDO = "Mejorando: ";
	public static final String MSG_TIEMPO_RESTANTE = "Tiempo restante: ";
	public static final String MSG_ATAQUE = "ATAQUE";
	public static final String MSG_DEFENSA = "DEFENSA";
	public static final String MSG_VIDA = "VIDA MÁXIMA";
	public static final String MSG_INDICE_CRITICO = "CRÍTICO";
	public static final String MSG_DINERO = "G.MONEDAS";
	
	public static final int MEJORA_ATAQUE = 1;
	public static final int MEJORA_DEFENSA = 2;
	public static final int MEJORA_VIDA = 3;
	public static final int MEJORA_CRITICO = 6;
	public static final int MEJORA_DINERO = 5;
	public static final int GENERAR_ENEMIGO = 8;

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
	
	/** SIRVE PARA SABER SI SE ESTÁ MEJORANDO ALGO **/
	private boolean [] vMejorando;

	/** PERSONAJE **/
	private Personaje personaje;
	
	/** ACCIONES REALIZÁNDOSE **/
	private ArrayList<Accion> accionesRealizandose;
	private boolean enemigoViniendo;
	private Accion accionEnemigo;
	private Personaje enemigo;
	
	/** PANTALLA DE JUEGO **/
	private PantallaJuego pantallaJuego;
	
	/** TIEMPO INICIAL (Para generación de enemigos) **/
	private double tiempoInicial;	

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

		// Iniciamos las acciones
		accionesRealizandose = new ArrayList<Accion>();
		vMejorando = new boolean [7];
		enemigoViniendo = false;
		
		for (int i = 0; i < vMejorando.length; i++) 
			vMejorando[i] = false;
		
		// Generamos el hilo y comenzamos el tiempo
		new Thread(this).start();
		tiempoInicial = System.nanoTime();
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

	public ArrayList<Accion> getAccionesRealizandose() {
		return accionesRealizandose;
	}

	public void setAccionesRealizandose(ArrayList<Accion> accionesRealizandose) {
		this.accionesRealizandose = accionesRealizandose;
	}

	public PantallaJuego getPantallaJuego() {
		return pantallaJuego;
	}

	public void setPantallaJuego(PantallaJuego pantallaJuego) {
		this.pantallaJuego = pantallaJuego;
	}

	public boolean enemigoViniendo () {
		return enemigoViniendo;
	}

	public Personaje getEnemigo() {
		return enemigo;
	}
	
	public Accion getAccionEnemigo() {
		return accionEnemigo;
	}
	// ***** GETTERS Y SETTERS ***** //
	
	
	

	/**
	 * El precio de la poción será 10 * la vida que recupere el personaje.
	 * Recuperará la mitad de su vida máxima
	 * 
	 * @return (10 * VIDA MÁXIMA PERSONAJE / 2)
	 */
	private int calcularPrecioPocion() {
		return 150 + (10 * nivelMejoraVida);
	}

	// *********************************************************************** //
	// *************** 			MEJORAS DEL PERSONAJE 			************** //
	// *********************************************************************** //
	/**
	 * Si el personaje tiene monedas suficientes, mejora el ataque del personaje.
	 * A su vez, aumenta en 1 el nivel de mejora y aumenta el precio de mejora.
	 */
	public void mejorarAtaquePersonaje () {
		if (personaje.getMonedas() >= precioMejoraAtaque && !vMejorando[MEJORA_ATAQUE]
				&& accionesRealizandose.size() < MAX_ACCIONES_EJECUTADAS) {
			vMejorando[MEJORA_ATAQUE] = true;		// Indico que estoy mejorando
			personaje.comprar(precioMejoraAtaque);	// El personaje compra la mejora 
			
			// Añado la acción a la lista de acciones
			accionesRealizandose.add(new Accion(MEJORA_ATAQUE, calcularDuracion(nivelMejoraAtaque)));
		}		
	}
	
	/**
	 * Si el personaje tiene monedas suficientes, mejora la defensa del personaje.
	 * A su vez, aumenta en 1 el nivel de mejora y aumenta el precio de mejora.
	 */
	public void mejorarDefensaPersonaje () {
		if (personaje.getMonedas() >= precioMejoraDefensa && !vMejorando[MEJORA_DEFENSA]
				&& accionesRealizandose.size() < MAX_ACCIONES_EJECUTADAS) {
			vMejorando[MEJORA_DEFENSA] = true;	// Indico que estoy mejorando
			personaje.comprar(precioMejoraDefensa);	// El personaje compra la mejora
			
			// Añado la acción a la lista de acciones
			accionesRealizandose.add(new Accion(MEJORA_DEFENSA, calcularDuracion(nivelMejoraDefensa)));
		}
	}
	
	/**
	 * Si el personaje tiene monedas suficientes, mejora la vida máxima del personaje.
	 * A su vez, aumenta en 1 el nivel de mejora y aumenta el precio de mejora.
	 */
	public void mejorarVidaMaximaPersonaje () {
		if (personaje.getMonedas() >= precioMejoraVida && !vMejorando[MEJORA_VIDA]
				&& accionesRealizandose.size() < MAX_ACCIONES_EJECUTADAS) {
			vMejorando[MEJORA_VIDA] = true;	// Indico que estoy mejorando
			personaje.comprar(precioMejoraVida);	// El personaje compra la mejora
			
			// Añado la acción a la lista de acciones
			accionesRealizandose.add(new Accion(MEJORA_VIDA, calcularDuracion(nivelMejoraVida)));
			
			
		}		
	}
	
	/**
	 * Si el personaje tiene monedas suficientes, mejora el generador de monedas del personaje.
	 * A su vez, aumenta en 1 el nivel de mejora y aumenta el precio de mejora.
	 */
	public void mejorarGeneradorMonedas () {
		if (personaje.getMonedas() >= precioMejoraMonedas && !vMejorando[MEJORA_DINERO]
				&& accionesRealizandose.size() < MAX_ACCIONES_EJECUTADAS) {
			vMejorando[MEJORA_DINERO] = true;	// Indico que estoy mejorando
			personaje.comprar(precioMejoraMonedas);	// El personaje compra la mejora
			
			// Añado la acción a la lista de acciones
			accionesRealizandose.add(new Accion(MEJORA_DINERO, calcularDuracion(nivelMejoraMonedas)));
		}
	}
	
	/**
	 * Si el personaje tiene monedas suficientes, mejora el índice de golpes críticos del personaje.
	 * A su vez, aumenta en 1 el nivel de mejora y aumenta el precio de mejora.
	 */
	public void mejorarIndiceCritico () {
		if (personaje.getMonedas() >= precioMejoraCritico && !vMejorando[MEJORA_CRITICO]
				&& accionesRealizandose.size() < MAX_ACCIONES_EJECUTADAS) {
			vMejorando[MEJORA_CRITICO] = true;	// Indico que estoy mejorando
			personaje.comprar(precioMejoraCritico);	// El personaje compra la mejora
			
			// Añado la acción a la lista de acciones
			accionesRealizandose.add(new Accion(MEJORA_CRITICO, calcularDuracion(nivelMejoraCritico)));			
		}
	}
	
	/**
	 * El personaje toma una poción.
	 */
	public void tomarPocion() {
		int rest;
		
		if (personaje.getMonedas() >= precioPocion && 
				personaje.getVidaActual() < personaje.getVidaMaxima()) {
			rest = personaje.getVidaMaxima() / 2;
			personaje.tomarPocion(precioPocion);
		}
	}
	
	/**
	 * Aumenta en 25 el precio de una poción.
	 */
	public void actualizarPrecioPocion() {
		this.precioPocion += 20;
	}
	
	/**
	 * En función al nivel, calcula la duración que tiene la mejora.
	 * @param nivel
	 * @return Duración de la mejora
	 */
	public int calcularDuracion(int nivel) {
		return (int) (10 + Math.pow(2, nivel - 1));
	}
	
	/**
	 * Ejecuta la mejora indicada (ATAQUE, DEFENSA, VIDA, etc.)
	 * @param tipo Tipo de mejora a realizar.
	 */
	public void ejecutarMejora(int tipo) {
		switch (tipo) {
		case MEJORA_ATAQUE:
			
			personaje.mejorarAtaque();
			nivelMejoraAtaque++;
			precioMejoraAtaque = 10 * (int) Math.pow(2, nivelMejoraAtaque-1);	
			vMejorando[MEJORA_ATAQUE] = false;		
			
			break;
		case MEJORA_DEFENSA:
			
			personaje.mejorarDefensa();
			nivelMejoraDefensa++;
			precioMejoraDefensa = 10 * (int) Math.pow(2, nivelMejoraDefensa-1);
			vMejorando[MEJORA_DEFENSA] = false;
			
			break;
		case MEJORA_VIDA:
			
			personaje.mejorarVidaMaxima();
			nivelMejoraVida++;
			precioMejoraVida = 10 * (int) Math.pow(2, nivelMejoraVida-1);
			vMejorando[MEJORA_VIDA] = false;
			actualizarPrecioPocion();
			
			break;
		case MEJORA_DINERO:
			
			nivelMejoraMonedas++;
			precioMejoraMonedas = 150 * (int) Math.pow(2, nivelMejoraMonedas-1);
			vMejorando[MEJORA_DINERO] = false;	
			
			break;
		case MEJORA_CRITICO:
			
			personaje.mejorarIndiceCritico();
			nivelMejoraCritico++;
			precioMejoraCritico = 10 * (int) Math.pow(2, nivelMejoraCritico-1);
			vMejorando[MEJORA_CRITICO] = false;
			
			break;
		}
	}
	
	/**
	 * Gestiona la lista de acciones que hay. Las que se hayan realizado, las ejecuta 
	 * y elimina de la lista.
	 */
	public void gestionarAcciones() {
		Accion accion;
		for (int i = 0; i < accionesRealizandose.size(); i++) {
			accion = accionesRealizandose.get(i);
			
			if (accion.esFinDeAccion()) {
				ejecutarMejora(accion.getTipoAccion());
				accionesRealizandose.remove(accion);
			}
		}
	}
	
	/**
	 * Gestiona los datos del enemigo. Si ha llegado la hora, ataca.
	 */
	private void gestionarEnemigos() {
		int tiempo = (int) ((System.nanoTime() - tiempoInicial)/1e9);
		//System.out.println("TIEMPO: " + tiempo);
		
		// Si el enemigo está viniendo y ha llegado ya el fin de acción, COMBATE
		if (enemigoViniendo) {
			if (accionEnemigo.esFinDeAccion()) {			
				combatir();
				enemigoViniendo = false;
			}			
		} else if (tiempo % 30 == 0 && tiempo != 0) {
			System.out.println("ENEMIGO GENERADO");
			generarEnemigo();
		}
	}
	
	/**
	 * Combate hasta el final entre el personaje y el enemigo.
	 */
	private void combatir() {
		boolean finCombate = false;
		
		System.out.println("¡¡¡ COMBATE !!!");
		
		while (!finCombate) {
			personaje.atacar(enemigo);
			finCombate = !enemigo.estaVivo();
			System.out.println("Personaje ataca. ");
			System.out.println("ENEMIGO: " + enemigo.toString());
			
			if (!finCombate) {
				enemigo.atacar(personaje);
				finCombate = !personaje.estaVivo();
				System.out.println("Enemigo ataca.");
				System.out.println("PERSONAJE: " + personaje.toString());
			}				
		}
		
		// Si el personaje ha muerto, cambiamos a pantalla final
		if (!personaje.estaVivo()) {
			pantallaJuego.cambiarAPantallaFin();
		}
	}
	
	/**
	 * Genera el enemigo y su acción.
	 */
	private void generarEnemigo() {
		int ataque, defensa, vida, rango;
		
		accionEnemigo = new Accion(GENERAR_ENEMIGO, 25);
		enemigoViniendo = true;
		
		rango = (int) (Math.random()*7 - 3);
		vida = 15 + (2 * nivelMejoraVida) + rango;
		
		rango = (int) (Math.random()*5 - 2);		
		ataque = 7 + (2 * nivelMejoraAtaque) + rango;
		
		rango = (int) (Math.random()*5 - 2);
		defensa = (2 * nivelMejoraDefensa) + rango;
		
		enemigo = new Personaje(vida, vida, ataque, defensa, 0);		
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
			gestionarAcciones();
			gestionarEnemigos();
			
		}

	}

}

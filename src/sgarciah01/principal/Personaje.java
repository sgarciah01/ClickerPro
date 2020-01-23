package sgarciah01.principal;

public class Personaje {

	/** ELEMENTOS DEL PERSONAJE **/
	private int vidaActual;
	private int vidaMaxima;
	private int ataque;
	private int defensa;
	private int indiceCritico;
	private int monedas;

	/**
	 * Constructor parametrizado.
	 */
	public Personaje(int vidaActual, int vidaMaxima, int ataque, int defensa, int indiceCritico) {
		this.vidaActual = vidaActual;
		this.vidaMaxima = vidaMaxima;
		this.ataque = ataque;
		this.defensa = defensa;
		this.indiceCritico = indiceCritico;
		this.monedas = 0;
	}

	/**
	 * Constructor por defecto
	 */
	public Personaje() {
		this.vidaActual = 0;
		this.vidaMaxima = 0;
		this.ataque = 0;
		this.defensa = 0;
		this.indiceCritico = 0;
		this.monedas = 0;
	}

	// ***** GETTERS Y SETTERS ***** //
	public int getVidaActual() {
		return vidaActual;
	}

	public void setVidaActual(int vidaActual) {
		this.vidaActual = vidaActual;
	}

	public int getVidaMaxima() {
		return vidaMaxima;
	}

	public void setVidaMaxima(int vidaMaxima) {
		this.vidaMaxima = vidaMaxima;
	}

	public int getAtaque() {
		return ataque;
	}

	public void setAtaque(int ataque) {
		this.ataque = ataque;
	}

	public int getDefensa() {
		return defensa;
	}

	public void setDefensa(int defensa) {
		this.defensa = defensa;
	}

	public int getIndiceCritico() {
		return indiceCritico;
	}

	public void setIndiceCritico(int indiceCritico) {
		this.indiceCritico = indiceCritico;
	}

	public int getMonedas() {
		return monedas;
	}

	public void setMonedas(int monedas) {
		this.monedas = monedas;
	}	
	// ***** GETTERS Y SETTERS ***** //

	/**
	 * Añade monedas al personaje en función al nivel que tenga de mejora
	 * @param nivel Nivel de mejora de monedas
	 */
	public void generarMonedas(int nivel) {
		this.monedas += nivel;
	}
	
	/**
	 * Mejora en 2 puntos el ataque del personaje.
	 */
	public void mejorarAtaque() {
		this.ataque += 2;
	}
	
	/**
	 * Mejora en 2 puntos la defensa del personaje.
	 */
	public void mejorarDefensa() {
		this.defensa += 1;
	}
	
	/**
	 * Mejora en 2 puntos la vida máxima y actual del personaje.
	 */
	public void mejorarVidaMaxima () {
		this.vidaMaxima += 2;
		this.vidaActual += 2;
	}
	
	public void mejorarIndiceCritico() {
		this.indiceCritico++;
	}
	
	@Override
	public String toString() {
		return "Personaje ( HP=[ " + vidaActual + "/ " + vidaMaxima + " ], AT = " + ataque
				+ ", DEF = " + defensa + ", CRÍTICO = " + indiceCritico + " )";
	}
	
	

}

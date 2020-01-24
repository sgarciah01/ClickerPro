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
	 * A�ade monedas al personaje en funci�n al nivel que tenga de mejora
	 * @param nivel Nivel de mejora de monedas
	 */
	public void generarMonedas(int nivel) {
		this.monedas += nivel;
	}
	
	/**
	 * Mejora en 2 puntos el ataque del personaje y restaremos el 
	 * pago a las monedas que tengamos.
	 */
	public void mejorarAtaque() {
		this.ataque += 2;
	}
	
	/**
	 * Mejora en 2 puntos la defensa del personaje y restaremos 
	 * el pago a las monedas que tengamos.
	 */
	public void mejorarDefensa() {
		this.defensa += 1;
	}
	
	/**
	 * Mejora en 2 puntos la vida m�xima y actual del personaje y restaremos 
	 * el pago a las monedas que tengamos.
	 */
	public void mejorarVidaMaxima () {
		this.vidaMaxima += 2;
		this.vidaActual += 2;
	}
	
	/**
	 * Mejora en 1 punto el �ndice de golpe cr�tico del personaje y restaremos 
	 * el pago a las monedas que tengamos.
	 */
	public void mejorarIndiceCritico() {
		this.indiceCritico++;
	}
	
	/**
	 * El personaje tomar� una poci�n y restaremos el pago a las monedas que tengamos.
	 * @param precio
	 */
	public void tomarPocion (int precio) {
		/*
		 *  Si al tomar la poci�n superamos la vida m�xima, nuestra vida actual ser� la vida m�xima.
		 *  De no ser as�, sumaremos a la vida actual la mitad de la vida m�xima.
		 */		
		this.vidaActual = (vidaActual + (vidaMaxima/2) > vidaMaxima) ? 
				vidaMaxima : vidaActual + (vidaMaxima/2); 
		this.monedas -= precio;
	}
	
	/**
	 * Resta el n�mero de monedas del precio a las monedas del personaje.
	 * @param precio
	 */
	public void comprar(int precio) {
		this.monedas -= precio;
	}
	
	/**
	 * Recibe da�o y se lo resta a la vida actual.
	 * @param danno Da�o a recibir.
	 */
	public void recibirDanno (int danno) {
		this.vidaActual -= danno;
	}
	
	/**
	 * Ataca al enemigo.
	 * @param enemigo
	 */
	public void atacar (Personaje enemigo) {
		int danno = this.ataque + (int) (Math.random()-8) - enemigo.getDefensa();
		enemigo.recibirDanno(danno);
	}
	
	/**
	 * Estar� vivo si la vida actual es mayor que 0.
	 * @return True si est� vivo.
	 */
	public boolean estaVivo () {
		return (this.vidaActual > 0);
	}
	
	@Override
	public String toString() {
		return "Personaje ( HP: [ " + vidaActual + "/ " + vidaMaxima + " ], AT: " + ataque
				+ ", DEF: " + defensa + " )";
	}
	
	

}

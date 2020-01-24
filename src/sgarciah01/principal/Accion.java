package sgarciah01.principal;

import java.text.DecimalFormat;

public class Accion {

	private String mensajeAccion;
	private int tipoAccion;
	
	/** TIEMPO INICIAL Y DURACIÓN DE LA ACCIÓN **/
	private double tiempoInicial;
	private int duracion;
	
	/** FORMATO PARA MOSTRAR EL TIEMPO **/
	private DecimalFormat formato = new DecimalFormat("#");
		
	/**
	 * Constructor parametrizado
	 * @param accion
	 * @param duracion
	 */
	public Accion (int tipoAccion, int duracion) {
		this.duracion = duracion;
		this.tipoAccion = tipoAccion;
		
		switch (this.tipoAccion) {
		case 1:
			this.mensajeAccion = Juego.MSG_MEJORANDO + " " + Juego.MSG_ATAQUE;
			break;
		case 2:
			this.mensajeAccion = Juego.MSG_MEJORANDO + " " + Juego.MSG_DEFENSA;
			break;
		case 3:
			this.mensajeAccion = Juego.MSG_MEJORANDO + " " + Juego.MSG_VIDA;
			break;
		case 5:
			this.mensajeAccion = Juego.MSG_MEJORANDO + " " + Juego.MSG_DINERO;
			break;
		case 6:
			this.mensajeAccion = Juego.MSG_MEJORANDO + " " + Juego.MSG_INDICE_CRITICO;
			break;
		case 8:		// Viene un enemigo
			this.mensajeAccion = "¡ ENEMIGO NUEVO !";
			break;
		}
		
		tiempoInicial = System.nanoTime();
	}	
	
	/**
	 * Obtiene el tiempo que va de la acción
	 * @return Tiempo que va de la acción ejecutada.
	 */
	public String tiempoQueVa () {
		return formato.format((System.nanoTime() - tiempoInicial)/1e9);
	}
	
	/**
	 * Obtiene el tiempo que queda de la acción.
	 * @return tiempo restante
	 */
	public String tiempoQueQuedaString () {
		return formato.format(duracion - ((System.nanoTime() - tiempoInicial)/1e9)); 
	}
	
	// ***** GETTERS Y SETTERS ***** //
	public String getMensajeAccion() {
		return this.mensajeAccion;
	}
	
		
	public int getTipoAccion() {
		return tipoAccion;
	}

	public void setTipoAccion(int tipoAccion) {
		this.tipoAccion = tipoAccion;
	}
	// ***** GETTERS Y SETTERS ***** //
	
	/**
	 * Comprueba si la acción ha finalizado
	 * @return Verdadero si ha finalizado la acción (Tiempo que va es mayor que la duración)
	 */
	public boolean esFinDeAccion() {		
		return (duracion - ((System.nanoTime() - tiempoInicial)/1e9)) < 0;
	}
	
	


}

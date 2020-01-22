package sgarciah01.pantallas;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import sgarciah01.principal.PanelJuego;

/**
 * Pantalla de inicio del juego.
 * @author Sergio García Hernández
 */
public class PantallaInicio implements Pantalla {
	
	/** PANEL JUEGO **/
	PanelJuego panelJuego;
	final Font fuenteInicio = new Font("", Font.BOLD, 50);
	
	/**PINICIAL COLOR **/
	Color colorLetraInicio = Color.WHITE;

	/**
	 * Constructor parametrizado. Inicializa el juego.
	 * @param panelJuego	Panel del juego 
	 */
	public PantallaInicio(PanelJuego panelJuego) {
		inicializarPantalla(panelJuego);
	}
	
	/**
	 * Inicializa la pantalla.
	 */
	@Override
	public void inicializarPantalla(PanelJuego panelJuego) {
		this.panelJuego = panelJuego;		
	}

	/**
	 * Pinta todos los elementos en la pantalla.
	 */
	@Override
	public void pintarPantalla(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, panelJuego.getWidth(), panelJuego.getHeight());
		g.setFont(fuenteInicio);
		g.setColor(colorLetraInicio);
		g.drawString("BIENVENIDO", panelJuego.getWidth()/2-120, panelJuego.getHeight()/2);
	}

	/**
	 * Método que se ejecuta y hace que el hilo se ejecute.
	 */
	@Override
	public void ejecutarFrame() {
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {e.printStackTrace();}
		colorLetraInicio = colorLetraInicio == Color.WHITE ? Color.ORANGE : Color.WHITE;
	}

	/**
	 * Método que se ejecuta cuando se pulsa el ratón en la pantalla.
	 */
	@Override
	public void pulsarRaton(MouseEvent e) {
		this.panelJuego.setPantallaActual(new PantallaJuego(panelJuego));
	}

	/**
	 * Calcula la posición de la nave en el movimiento del ratón.
	 */
	@Override
	public void moverRaton(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Redimensiona el fondo y lo reescala.
	 */
	@Override
	public void redimensionar() {
		// TODO Auto-generated method stub
		
	}


}

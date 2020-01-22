package sgarciah01.pantallas;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;

import sgarciah01.principal.PanelJuego;

public class PantallaJuego implements Pantalla {

	// ***** CONSTANTES ***** //
	public final static int OPCIONES = 6;

	/** PANEL JUEGO **/
	private PanelJuego panelJuego;

	/**
	 * Constructor por defecto
	 * 
	 * @param panelJuego Panel del juego
	 */
	public PantallaJuego(PanelJuego panelJuego) {
		inicializarPantalla(panelJuego);
	}

	@Override
	public void inicializarPantalla(PanelJuego panelJuego) {
		this.panelJuego = panelJuego;

	}

	@Override
	public void pintarPantalla(Graphics g) {
		// Panel central
		g.setColor(Color.CYAN);
		g.fillRect(250, 0, 500, panelJuego.getHeight());

		// Panel izquierdo
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, 250, panelJuego.getHeight());

		g.setColor(Color.BLACK);
		int altoCelda = panelJuego.getHeight() / OPCIONES;
		int posYCelda;
		for (int i = 0; i < OPCIONES; i++) {
			posYCelda = i * altoCelda;
			g.drawRect(0, posYCelda+i, 249, altoCelda);
		}

		// Panel derecho
		g.setColor(Color.WHITE);
		g.fillRect(750, 0, panelJuego.getWidth() - 750, panelJuego.getHeight());

		g.setColor(Color.BLACK);
		for (int i = 0; i < OPCIONES; i++) {
			posYCelda = i * altoCelda;
			g.drawRect(750, posYCelda+i, 249, altoCelda);
		}

	}

	@Override
	public void ejecutarFrame() {
		// TODO Auto-generated method stub

	}

	@Override
	public void pulsarRaton(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void moverRaton(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void redimensionar() {
		// TODO Auto-generated method stub

	}

}

package sgarciah01.pantallas;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import sgarciah01.principal.Juego;
import sgarciah01.principal.PanelJuego;

public class PantallaDatosBatalla implements Pantalla {
	
	/** JUEGO **/
	private Juego juego;
	
	/** PANEL JUEGO **/
	private PanelJuego panelJuego;
	
	/** LISTA DE ACCIONES EN BATALLA **/
	private ArrayList<String> listaAcciones;
	
	public PantallaDatosBatalla(PanelJuego panelJuego, Juego juego, 
			ArrayList<String> listaAcciones) {
		this.panelJuego = panelJuego;
		this.juego = juego;
		this.listaAcciones = listaAcciones;
		
		inicializarPantalla(panelJuego);
	}

	@Override
	public void inicializarPantalla(PanelJuego panelJuego) {
		
	}

	@Override
	public void pintarPantalla(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, panelJuego.getWidth(), panelJuego.getHeight());
		
		
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

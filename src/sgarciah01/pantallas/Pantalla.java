package sgarciah01.pantallas;


import java.awt.Graphics;
import java.awt.event.MouseEvent;

import sgarciah01.principal.PanelJuego;

public interface Pantalla {

	public void inicializarPantalla(PanelJuego panelJuego);
	public void pintarPantalla(Graphics g);
	public void ejecutarFrame();
	
	// Listeners
	public void pulsarRaton(MouseEvent e);
	public void moverRaton(MouseEvent e);
	public void redimensionar();
	
}

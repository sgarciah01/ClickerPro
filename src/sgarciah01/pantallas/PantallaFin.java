package sgarciah01.pantallas;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import sgarciah01.principal.PanelJuego;

/**
 * Pantalla de final del juego.
 * 
 * @author Sergio García Hernández
 */
public class PantallaFin implements Pantalla {
	
	/** PANEL JUEGO **/
	PanelJuego panelJuego;
	
	/** FUENTES **/
	final Font fuenteTitulo = new Font("", Font.BOLD, 50);
	final Font fuenteClick = new Font("", Font.BOLD, 25);
	
	/** IMÁGEN MR.CLICKER **/
	private Image imagenMrClicker;
	
	/** PINICIAL COLOR **/
	Color colorTitulo = Color.RED;
	Color colorTexto = new Color(255, 205, 205);
	Color colorClick = colorTexto;
	
	public PantallaFin(PanelJuego panelJuego) {
		inicializarPantalla(panelJuego);
	}
	
	/**
	 * Inicializa la pantalla.
	 */
	@Override
	public void inicializarPantalla(PanelJuego panelJuego) {
		this.panelJuego = panelJuego;
		
		try {
			imagenMrClicker = ImageIO.read(new File("img/deadpool1.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		imagenMrClicker = imagenMrClicker.getScaledInstance(
				getAnchoEscalado(imagenMrClicker.getWidth(null), imagenMrClicker.getHeight(null), 300), 
				300, Image.SCALE_SMOOTH);
		
	}
	
	/**
	 * Sirve para obtener el ancho escalado de una imagen, aportando previamente las medidas originales y el alto que queremos
	 * @param ancho 		Ancho de la imagen
	 * @param alto 			Alto de la imagen
	 * @param altoEscalado	Algo escalado que tendrá la imagen de destino
	 * @return 				Ancho que tendrá la imagen de destino
	 */
	public int getAnchoEscalado(int ancho, int alto, int altoEscalado) {
		return ((int) (ancho * altoEscalado) / alto);
	}

	@Override
	public void pintarPantalla(Graphics g) {
		String titulo = "Fin del juego...";		
		String click = "Haz click para VOLVER A EMPEZAR";	
		
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, panelJuego.getWidth(), panelJuego.getHeight());
		
		g.setFont(fuenteTitulo);
		g.setColor(colorTitulo);
		g.drawString(titulo, panelJuego.getWidth()/2 - 180, panelJuego.getHeight()/2-100);
		
		g.setFont(fuenteClick);
		g.setColor(colorClick);
		g.drawString(click, panelJuego.getWidth()/2 - 250, panelJuego.getHeight()/2 + 180);
		
		g.drawImage(imagenMrClicker, panelJuego.getWidth() - 320, panelJuego.getHeight() - 350, null);
	}

	@Override
	public void ejecutarFrame() {
		try {
			Thread.sleep(400);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		colorClick = (colorClick == colorTexto) ? colorTitulo : colorTexto;
		
	}

	@Override
	public void pulsarRaton(MouseEvent e) {
		this.panelJuego.setPantallaActual(new PantallaInicio(panelJuego));
		
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

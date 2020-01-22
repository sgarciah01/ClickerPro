package sgarciah01.pantallas;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import sgarciah01.principal.PanelJuego;

public class PantallaJuego implements Pantalla {

	// ***** CONSTANTES ***** //
	public final static int OPCIONES = 6;

	/** FONDO **/
	private BufferedImage fondo;
	private Image fondoEscalado;
	private Image iconoAtaque;
	private Image iconoDefensa;
	private Image iconoVida;
	private Image iconoPersonaje;
	
	private Image iconoSpiderman1;
	private Image iconoSpiderman2;
	
	/** VARIABLE GIF **/
	private boolean flagGif = true;;
	
	/** PANEL JUEGO **/
	private PanelJuego panelJuego;

	/** FUENTE **/
	final Font fuenteSuperior = new Font("", Font.BOLD, 30);
	
	
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
		
		// Imágenes
		try {
			fondo = ImageIO.read(new File("img/fondo1.png"));
			iconoAtaque = ImageIO.read(new File("img/espada.png"));
			iconoDefensa = ImageIO.read(new File("img/escudo.png"));
			iconoVida = ImageIO.read(new File("img/corazon.png"));
			iconoSpiderman1 = ImageIO.read(new File("img/spiderman1.png"));
			iconoSpiderman2 = ImageIO.read(new File("img/spiderman2.png"));
		} catch (IOException e1) {
			e1.printStackTrace();
			System.out.println("PROBLEMAS AL CARGAR LAS IMÁGENES. FIN DEL PROGRAMA");
			System.exit(1);
		}
		
		iconoAtaque = iconoAtaque.getScaledInstance(getAnchoEscalado(iconoAtaque.getWidth(null), 
				iconoAtaque.getHeight(null), 40), 40, Image.SCALE_SMOOTH);
		iconoDefensa = iconoDefensa.getScaledInstance(getAnchoEscalado(iconoDefensa.getWidth(null), 
				iconoDefensa.getHeight(null), 40), 40, Image.SCALE_SMOOTH);
		iconoVida = iconoVida.getScaledInstance(getAnchoEscalado(iconoVida.getWidth(null), 
				iconoVida.getHeight(null), 40), 40, Image.SCALE_SMOOTH);
		fondoEscalado = fondo.getScaledInstance(panelJuego.getWidth(), panelJuego.getHeight(),
				BufferedImage.SCALE_SMOOTH);
		iconoSpiderman1 = iconoSpiderman1.getScaledInstance(getAnchoEscalado(iconoSpiderman1.getWidth(null), 
				iconoSpiderman1.getHeight(null), 80), 80, Image.SCALE_SMOOTH);
		iconoSpiderman2 = iconoSpiderman2.getScaledInstance(getAnchoEscalado(iconoSpiderman2.getWidth(null), 
				iconoSpiderman2.getHeight(null), 80), 80, Image.SCALE_SMOOTH);
		
		iconoPersonaje = iconoSpiderman1;
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
		// ***** PANEL CENTRAL ***** //
		// Fondo
		g.drawImage(fondoEscalado, 250, 0, null);
		
		// Iconos superiores
		g.setColor(new Color(147, 158, 117, 200));
		g.fillRect(250, 0, 500, 80);
		g.drawImage(iconoAtaque, 270, 20, null);
		g.drawImage(iconoDefensa, 445, 20, null);
		g.drawImage(iconoVida, 600, 20, null);
		
		g.setColor(new Color(218, 239, 159));
		g.setFont(fuenteSuperior);
		g.drawString("40", 330, 50);
		g.drawString("40", 505, 50);
		g.drawString("100", 660, 50);
		
		// Icono personaje
		g.drawImage(iconoPersonaje, 460, 350, null);

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
	
	/**
	 * Rellena el fondo de la pantalla con la imagen reescalada.
	 * @param g Gráficos
	 */
	private void rellenarFondo(Graphics g) {
		g.drawImage(fondoEscalado, 0, 0, null);
	}

	@Override
	public void ejecutarFrame() {
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		iconoPersonaje = (iconoPersonaje == iconoSpiderman1) ? iconoSpiderman2 : iconoSpiderman1;

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

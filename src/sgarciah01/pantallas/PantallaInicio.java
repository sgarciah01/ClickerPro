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

/**
 * Pantalla de inicio del juego.
 * @author Sergio García Hernández
 */
public class PantallaInicio implements Pantalla {
	
	/** PANEL JUEGO **/
	PanelJuego panelJuego;
	
	/** FUENTES **/
	final Font fuenteTitulo = new Font("", Font.BOLD, 50);
	final Font fuenteTexto = new Font("", Font.PLAIN, 20);
	final Font fuenteClick = new Font("", Font.BOLD, 25);

	/** PINICIAL COLOR **/
	Color colorTitulo = Color.RED;
	Color colorTexto = new Color(255, 205, 205);
	Color colorClick = colorTexto;
	
	/** IMÁGEN MR.CLICKER **/
	private Image imagenMrClicker;

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
		
		try {
			imagenMrClicker = ImageIO.read(new File("img/deadpool2.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		imagenMrClicker = imagenMrClicker.getScaledInstance(
				getAnchoEscalado(imagenMrClicker.getWidth(null), imagenMrClicker.getHeight(null), 200), 
				200, Image.SCALE_SMOOTH);
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
	
	/**
	 * Pinta todos los elementos en la pantalla.
	 */
	@Override
	public void pintarPantalla(Graphics g) {
		String titulo = "Mister Clicker";
		String descripcion1 = "En la vida todo el mundo tiene un propósito y Mr.Clicker, nuestro protagonista, no es distinto.";
		String descripcion2 = "Este chico vive en un enfado permanente con el mundo, no quiere compañía nunca, por ello se";
		String descripcion3 = "ha trasladado al bosque más alejado de la civilización. Pero hay un problema, a lo largo de";
		String descripcion4 = "su vida ha hecho muchos enemigos que, a día de hoy, siguen queriendo matarle. Saben que";
		String descripcion5 = "vive en el bosque y van continuamente a matarle.";
		String descripcion6 = "Su objetivo es SOBREVIVIR. ¡ AYÚDALE !";
		
		String click = "Haz click para EMPEZAR";		
		
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, panelJuego.getWidth(), panelJuego.getHeight());
		
		g.setFont(fuenteTitulo);
		g.setColor(colorTitulo);
		g.drawString(titulo, panelJuego.getWidth()/2 - 150, panelJuego.getHeight()/2-190);
		
		g.setFont(fuenteTexto);
		g.setColor(colorTexto);
		g.drawString(descripcion1, panelJuego.getWidth()/2 - 400, panelJuego.getHeight()/2 - 130);
		g.drawString(descripcion2, panelJuego.getWidth()/2 - 400, panelJuego.getHeight()/2 - 90);
		g.drawString(descripcion3, panelJuego.getWidth()/2 - 400, panelJuego.getHeight()/2 - 50);
		g.drawString(descripcion4, panelJuego.getWidth()/2 - 400, panelJuego.getHeight()/2 - 10);
		g.drawString(descripcion5, panelJuego.getWidth()/2 - 400, panelJuego.getHeight()/2 + 30);
		g.drawString(descripcion6, panelJuego.getWidth()/2 - 400, panelJuego.getHeight()/2 + 80);
		
		g.setFont(fuenteClick);
		g.setColor(colorClick);
		g.drawString(click, panelJuego.getWidth()/2 - 150, panelJuego.getHeight()/2 + 180);		
		
		g.drawImage(imagenMrClicker, panelJuego.getWidth() - 220, panelJuego.getHeight() - 250, null);
	}

	/**
	 * Método que se ejecuta y hace que el hilo se ejecute.
	 */
	@Override
	public void ejecutarFrame() {
		
		try {
			Thread.sleep(400);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		colorClick = (colorClick == colorTexto) ? colorTitulo : colorTexto;
		
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

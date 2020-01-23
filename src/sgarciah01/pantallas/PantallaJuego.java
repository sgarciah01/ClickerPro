package sgarciah01.pantallas;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;

import javax.imageio.ImageIO;

import sgarciah01.principal.PanelJuego;

public class PantallaJuego implements Pantalla {

	// ***** CONSTANTES ***** //
	public final static int OPCIONES = 6;

	/** FONDO, IM�GENES **/
	private BufferedImage fondo;
	private Image fondoEscalado;
	private Image fondoLateralEscalado;
	private Image iconoAtaque;
	private Image iconoDefensa;
	private Image iconoVida;
	private Image iconoPersonaje;
	private Image iconoDinero;
	private Image iconoMas;
	private Image iconoPocion;
	
	private Image iconoSpiderman1;
	private Image iconoSpiderman2;
		
	/** PANEL JUEGO **/
	private PanelJuego panelJuego;

	/** FUENTE **/
	final Font fuenteSuperior = new Font("", Font.BOLD, 30);
	final Font fuenteDinero = new Font("", Font.PLAIN, 20);
	final Font fuenteLateralIzquierda = new Font("", Font.PLAIN, 25);
	
	/** VARIABLES PARA TIEMPO **/
	private double tiempoInicial = 0;
	private DecimalFormat formato = new DecimalFormat("#.##");
	final Font fuenteTiempo = new Font("", Font.BOLD, 30);
	
	/** VARIABLES DEL VALOR DE LOS DATOS DEL PERSONALE **/
	private int vida;
	private int ataque;
	private int defensa;
	private int dinero;
	
	
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
		
		// Im�genes
		obtencionYReescaladoImagenes(panelJuego);
	}

	/**
	 * Obtiene todas las im�genes y las reescala.
	 * @param panelJuego Panel de juego
	 */
	private void obtencionYReescaladoImagenes(PanelJuego panelJuego) {
		try {
			fondo = ImageIO.read(new File("img/fondo1.png"));
			fondoLateralEscalado = ImageIO.read(new File("img/fondo_lateral.jpg"));
			iconoAtaque = ImageIO.read(new File("img/espada.png"));
			iconoDefensa = ImageIO.read(new File("img/escudo.png"));
			iconoVida = ImageIO.read(new File("img/corazon.png"));
			iconoSpiderman1 = ImageIO.read(new File("img/spiderman1.png"));
			iconoSpiderman2 = ImageIO.read(new File("img/spiderman2.png"));
			iconoDinero = ImageIO.read(new File("img/moneda.png"));
			iconoMas = ImageIO.read(new File("img/mas.png"));
			iconoPocion = ImageIO.read(new File("img/pocion.png"));
		} catch (IOException e1) {
			e1.printStackTrace();
			System.out.println("PROBLEMAS AL CARGAR LAS IM�GENES. FIN DEL PROGRAMA");
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
		iconoDinero = iconoDinero.getScaledInstance(getAnchoEscalado(iconoDinero.getWidth(null), 
				iconoDinero.getHeight(null), 25), 25, Image.SCALE_SMOOTH);
		iconoMas = iconoMas.getScaledInstance(getAnchoEscalado(iconoMas.getWidth(null), 
				iconoMas.getHeight(null), 30), 30, Image.SCALE_SMOOTH);
		iconoPocion = iconoPocion.getScaledInstance(getAnchoEscalado(iconoPocion.getWidth(null), 
				iconoPocion.getHeight(null), 30), 30, Image.SCALE_SMOOTH);
		fondoLateralEscalado = fondoLateralEscalado.getScaledInstance(250, 
				panelJuego.getHeight() / OPCIONES, Image.SCALE_SMOOTH);		
		
		iconoPersonaje = iconoSpiderman1;
	}

	/**
	 * Sirve para obtener el ancho escalado de una imagen, aportando previamente las medidas originales y el alto que queremos
	 * @param ancho 		Ancho de la imagen
	 * @param alto 			Alto de la imagen
	 * @param altoEscalado	Algo escalado que tendr� la imagen de destino
	 * @return 				Ancho que tendr� la imagen de destino
	 */
	public int getAnchoEscalado(int ancho, int alto, int altoEscalado) {
		return ((int) (ancho * altoEscalado) / alto);
	}
	
	@Override
	public void pintarPantalla(Graphics g) {
		// Fondo de los laterales
		
		
		// Fondo
		g.drawImage(fondoEscalado, 250, 0, null);
		
		// ***** PANEL CENTRAL ***** //		
		// Iconos superiores
		g.setColor(new Color(147, 158, 117, 200));	// Fondo verde transparente
		g.fillRect(250, 0, 500, 80);
		g.drawImage(iconoAtaque, 270, 20, null);
		g.drawImage(iconoDefensa, 445, 20, null);
		g.drawImage(iconoVida, 600, 20, null);
		
		g.setColor(new Color(218, 239, 159));	// Color letra
		
		g.setFont(fuenteSuperior);
		g.drawString(String.valueOf(ataque), 330, 50);
		g.drawString(String.valueOf(defensa), 505, 50);
		g.drawString(String.valueOf(vida), 660, 50);
		
		// Zona del dinero
		g.setColor(new Color(147, 158, 117, 200));	// Fondo verde transparente
		g.fillRect(250, 80, 150, 40);
		
		g.setFont(fuenteDinero);
		g.setColor(Color.WHITE);
		g.drawImage(iconoDinero, 280, 90, null);
		g.drawString(String.valueOf(dinero), 310, 108);
		
		// Icono personaje
		g.drawImage(iconoPersonaje, 460, 350, null);
		

		// FONDOS DE LOS PANELES LATERALES //
		int altoCelda = panelJuego.getHeight() / OPCIONES;
		int posYCelda;
		
		g.setColor(new Color(147, 158, 117, 200));
		for (int i=0; i<OPCIONES; i++) {
			posYCelda = i * altoCelda;
			g.drawImage(fondoLateralEscalado, 0, posYCelda+i, null);
			g.drawImage(fondoLateralEscalado, 750, posYCelda+i, null);
			g.drawRect(0, posYCelda+i, 249, altoCelda);
			g.drawRect(750, posYCelda+i, 249, altoCelda);
		}

		// Panel izquierdo
		int posYItem;
		String [] textos = {"Mejorar Ataque", "Mejorar Defensa", "Mejorar Vida M�xima",
				"Tomar una Poci�n", "Generaci�n Monedas", 
				"�ndice Cr�tico"};
		
		g.setFont(fuenteDinero);
		g.setColor(new Color(194, 255, 246));
		
		// Iconos y textos
		for (int i=0; i<3; i++) {
			posYCelda = i * altoCelda;
			posYItem = posYCelda + (altoCelda/2) - 15;
			g.drawImage(iconoMas, 15, posYItem, null);
			g.drawString(textos[i], 50, posYItem+22);			
		}
						
		posYCelda = 3 * altoCelda;
		posYItem = posYCelda + (altoCelda/2) - 15;
		g.drawImage(iconoPocion, 15, posYItem, null);
		g.drawString(textos[3], 50, posYItem+22);
		
		for (int i=4; i<6; i++) {
			posYCelda = i * altoCelda;
			posYItem = posYCelda + (altoCelda/2) - 15;
			g.drawImage(iconoMas, 15, posYItem, null);
			g.drawString(textos[i], 50, posYItem+22);			
		}

		
		// Panel derecho
		
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
 
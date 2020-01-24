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
import java.util.List;

import javax.imageio.ImageIO;

import sgarciah01.principal.Accion;
import sgarciah01.principal.Juego;
import sgarciah01.principal.PanelJuego;
import sgarciah01.principal.Personaje;

public class PantallaJuego implements Pantalla {

	// ***** CONSTANTES ***** //
	public final static int OPCIONES = 6;

	/** FONDO, IMÁGENES **/
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
	
	/** CONTROL DEL JUEGO **/
	private Juego juego;
	private Personaje personaje;

	/** FUENTE **/
	final Font fuenteSuperior = new Font("", Font.BOLD, 30);
	final Font fuenteDinero = new Font("", Font.PLAIN, 20);
	final Font fuenteMensaje = new Font("", Font.PLAIN, 18);
	final Font fuenteLateralIzquierda = new Font("", Font.PLAIN, 25);
	
	/** VARIABLES PARA TIEMPO **/
	private double tiempoInicial = 0;
	private DecimalFormat formato = new DecimalFormat("#.##");
	final Font fuenteTiempo = new Font("", Font.BOLD, 30);
	
	/** VARIABLES DEL VALOR DE LOS DATOS DEL PERSONAJE **/
	private int vida;
	private int ataque;
	private int defensa;
	private int dinero;
	
	/** VARIABLES DEL VALOR DEL PRECIO DE LAS MEJORAS **/
	private int precioMejoraVida;
	private int precioMejoraAtaque;
	private int precioMejoraDefensa;
	private int precioMejoraCritico;
	private int precioMejoraMonedas;
	private int precioPocion;
	
	
	
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
		this.juego = new Juego(this);
		this.personaje = juego.getPersonaje();
		
		// Imágenes
		obtencionYReescaladoImagenes(panelJuego);
	}

	/**
	 * Obtiene todas las imágenes y las reescala.
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
	 * @param altoEscalado	Algo escalado que tendrá la imagen de destino
	 * @return 				Ancho que tendrá la imagen de destino
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
		g.drawImage(iconoVida, 580, 20, null);
		
		g.setColor(new Color(218, 239, 159));	// Color letra
		
		// ESTADÍSTICAS DEL PERSONAJE //
		g.setFont(fuenteSuperior);
		g.drawString(String.valueOf(personaje.getAtaque()), 330, 50);
		g.drawString(String.valueOf(personaje.getDefensa()), 505, 50);
		g.drawString(String.valueOf(personaje.getVidaActual()) + " / " + 
					String.valueOf(personaje.getVidaMaxima()), 640, 50);
		
		// Zona del dinero
		g.setColor(new Color(147, 158, 117, 200));	// Fondo verde transparente
		g.fillRect(250, 80, 150, 40);
		
		g.setFont(fuenteDinero);
		g.setColor(Color.WHITE);
		g.drawImage(iconoDinero, 280, 90, null);
		g.drawString(String.valueOf(personaje.getMonedas()), 310, 108);
		
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
		String [] textos = {"Mejorar Ataque", "Mejorar Defensa", "Mejorar Vida Máxima",
				"Tomar una Poción", "Generación Monedas", 
				"Índice Crítico"};
		int [] valores = {
				juego.getPrecioMejoraAtaque(),
				juego.getPrecioMejoraDefensa(),
				juego.getPrecioMejoraVida(),
				juego.getPrecioPocion(),
				juego.getPrecioMejoraMonedas(),
				juego.getPrecioMejoraCritico()
		};
		
		g.setFont(fuenteDinero);
		g.setColor(new Color(194, 255, 246));
		
		// Iconos y textos
		for (int i=0; i<3; i++) {
			posYCelda = i * altoCelda;
			posYItem = posYCelda + (altoCelda/2) - 15;
			g.drawImage(iconoMas, 15, posYItem-10, null);	// Icono del botón
			g.drawString(textos[i], 50, posYItem+12);		// Texto del botón
			g.drawImage(iconoDinero, 150, posYItem + 35, null);	// Moneda
			g.drawString(String.valueOf(valores[i]), 180, posYItem + 55);
		}
						
		posYCelda = 3 * altoCelda;
		posYItem = posYCelda + (altoCelda/2) - 15;
		g.drawImage(iconoPocion, 15, posYItem-10, null);	// Icono del botón
		g.drawString(textos[3], 50, posYItem+12);			// Texto del botón
		g.drawImage(iconoDinero, 150, posYItem + 35, null);	// Moneda
		g.drawString(String.valueOf(valores[3]), 180, posYItem + 55);
			
		for (int i=4; i<6; i++) {
			posYCelda = i * altoCelda;
			posYItem = posYCelda + (altoCelda/2) - 15;
			g.drawImage(iconoMas, 15, posYItem-10, null);	// Icono del botón
			g.drawString(textos[i], 50, posYItem+12);		// Texto del botón
			g.drawImage(iconoDinero, 150, posYItem + 35, null);	// Moneda
			g.drawString(String.valueOf(valores[i]), 180, posYItem + 55);
		}
		
		// PANEL DERECHO //
		pintarMensajes(g);
		
		// PANEL INFERIOR PARA INFO DE ENEMIGOS //
		g.setColor(new Color(255, 255, 255, 200));
		g.fillRect(270, 480, 460, 60);
		g.setColor(Color.BLACK);
		g.drawRect(270, 480, 460, 60);
		
		if (juego.enemigoViniendo())
			mostrarDatosEnemigo(g, juego.getEnemigo(), juego.getAccionEnemigo());
		
	}

	private void mostrarDatosEnemigo(Graphics g, Personaje enemigo, 
			Accion accionEnemigo) {
		g.setColor(Color.BLACK);
		g.setFont(fuenteDinero);
		g.drawString("Enemigo a la vista. Atacará en " + 
				accionEnemigo.tiempoQueQuedaString(), 290, 500);
		g.drawString(enemigo.toString(), 310, 530);
	}
	
	/**
	 * Pinta los mensajes de las acciones que están en curso.
	 * @param g Gráficos
	 */
	public void pintarMensajes (Graphics g) {
		List<Accion> listaAcciones = juego.getAccionesRealizandose();
		int altoCelda = panelJuego.getHeight() / OPCIONES;
		int posYCelda, posYMensaje;
		
		for (int i=0; i<listaAcciones.size(); i++) {
			posYCelda = i * altoCelda;
			posYMensaje = posYCelda + (altoCelda/2) - 15;
			
			g.setFont(fuenteMensaje);
			g.drawString(listaAcciones.get(i).getMensajeAccion(), 770, posYMensaje);
			
			g.drawString("Tiempo restante: " + listaAcciones.get(i).tiempoQueQuedaString(), 
					770, posYMensaje + 30);
		}
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
		int posX = e.getX();
		int posY = e.getY();
		int altoOpcion = panelJuego.getHeight() / OPCIONES;
		int opcion = (posY / altoOpcion) + 1;
				
		if (posX <= 250) {	// Se ha pulsado en el panel de opciones de la izquierda
			switch (opcion) {
			case 1: 	// MEJORA ATAQUE
				juego.mejorarAtaquePersonaje();
				break;
			case 2: 	// MEJORA DEFENSA
				juego.mejorarDefensaPersonaje();
				break;
			case 3: 	// MEJORA VIDA MÁXIMA
				juego.mejorarVidaMaximaPersonaje();
				break;
			case 4: 	// POCIÓN
				
				break;
			case 5: 	// MEJORA GENERACIÓN MONEDAS
				juego.mejorarGeneradorMonedas();
				break;
			case 6: 	// MEJORA ÍNDICE CRÍTICO
				juego.mejorarIndiceCritico();
				break;
			}
		}

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
 
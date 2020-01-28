package sgarciah01.principal;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import sgarciah01.pantallas.Pantalla;
import sgarciah01.pantallas.PantallaInicio;


/**
 * Panel del juego. Extiende de JPanel
 * 
 * @author Sergio García Hernández
 */
public class PanelJuego extends JPanel implements Runnable{
		
	
	/** PANTALLAS **/
		
	private Pantalla pantallaActual;
	
	/**PINICIAL COLOR **/
	Color colorLetraInicio = Color.WHITE;
	
	//El contructor
	public PanelJuego(){

		pantallaActual = new PantallaInicio(this);
		
		new Thread(this).start();
		
		//L�?STENERS
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				pantallaActual.pulsarRaton(e);
			}
		});
		
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				pantallaActual.redimensionar();
			}
		});
		
		
		addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				pantallaActual.moverRaton(e);
			}
			@Override
			public void mouseDragged(MouseEvent e) {
				mouseMoved(e);
			}
		});
    }

	// Getters y setters //
	public void setPantallaActual(Pantalla pantallaActual) {
		this.pantallaActual = pantallaActual;
	}
	
	public Pantalla getPantallaActual() {
		return pantallaActual;
	}	
	// Getters y setters //
	
	//Método que se llama automáticamente
	@Override
	public void paintComponent(Graphics g){
		pantallaActual.pintarPantalla(g);
	}

	@Override
	public void run() {
		while(true) {
			repaint();
			Toolkit.getDefaultToolkit().sync();
			
			pantallaActual.ejecutarFrame();
			//Siempre repinto.
		}
		
	}
   }





















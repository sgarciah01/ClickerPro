package sgarciah01.principal;

import java.awt.EventQueue;

/**
 * Principal para el juego.
 * @author Sergio Garc�a
 */
public class Principal {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
		public void run() {
			try {
				VentanaPrincipal ventanaPrincipal = new VentanaPrincipal();
				ventanaPrincipal.inicializar();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	});	
	}
}

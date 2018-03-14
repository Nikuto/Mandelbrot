package mandelbrot;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import naive.NaiveServer;



public class Window extends JFrame {

	public static void main(String[] args) {
		
		/**
		 * TODO: The server has to be replaced by a one using a thread pool
		 */
		//Server server = new NaiveServer();
		Server server = new SynchronousServer(10);
		/**
		 * TODO: Tweak the following settings to find a difficulty that 
		 * makes the computation more interesting to observe on your machine
		 */

		// Size of the window in pixels
		int x_definition = 500;
		int y_definition = 500;
		// Side of one square block, in pixels
		int block_size = 50;

		// Part of the complex plane to draw
		double x_min;
		double y_min;
		double x_max;
		double y_max;
		/**
		 * TODO: chose one of those lines to zoom on interesting parts. 
		 * Ordered by expected computation time
		 */
		// x_min = -2.25; y_min = -1; x_max = 0.75; y_max = 1; // Full Mandelbrot set
		// x_min = -1.5; y_min = -0.1; x_max = -1.2; y_max = 0.1; // Biggest circle
		// x_min = -1.5; y_min = -0.01; x_max = -1.47; y_max = 0.01; // Replicate on the left
		x_min = -0.65; y_min = -0.72; x_max = -0.32; y_max = -0.5; // Side of the cardioid

		// Maximal number of iterations before we consider it will diverged
		int threshold = 1000000;		

		// The SynchronousServer class is so bad it has to cheat
		if(server.getClass() == NaiveServer.class){
			x_definition = 600;
			y_definition = 400;
			x_min = -2.25; y_min = -1; x_max = 0.75; y_max = 1; // Full Mandelbrot set
			threshold = 1000;
		}


		// Start the window
		final Vizualizor panel = new Vizualizor(server, x_min, y_min, x_max, y_max, x_definition, y_definition, block_size, threshold);
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new Window(panel);
			}
		});
	}


	/**
	 * Creation of the window object
	 */
	public Window(Vizualizor panel) {
		setSize(new Dimension(panel.x_def, panel.y_def));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("The Mandelbrot Set");
		this.getContentPane().add(panel);
		setVisible(true);
	}

	/**
	 * Generated UID
	 */
	private static final long serialVersionUID = -2378140076308891137L;
}

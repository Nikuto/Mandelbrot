package mandelbrot;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;

/**
 * Class in charge of computing and storing a square image of a part of the Mandelbrot set
 */
public class Block {

	private final int x_def;
	private final int y_def;
	private final int threshold;

	private final double ax;
	private final double bx;
	private final double ay;
	private final double by;

	private final Color colors [][];
	
	/**
	 * Creates a new block
	 * @param bs All the data needed to build a new block
	 */
	public Block(BlockSettings bs){
		this.x_def = bs.x_def;
		this.y_def = bs.y_def;
		this.threshold = bs.threshold;
		this.ax = (bs.x_max - bs.x_min)/x_def;
		this.bx = bs.x_min;
		this.ay = (bs.y_max - bs.y_min)/y_def;
		this.by = bs.y_min;
				
		this.colors = new Color[x_def][y_def];
	}

	/**
	 * Draws the square block on a given panel
	 * @param x abscissa of the first pixel on the screen
	 * @param y ordinate of the first pixel on the screen
	 * @param g2 object that describes the panel on which to draw
	 */
	public void draw(int x, int y, Graphics2D g2){
		for(int i = 0; i < x_def; i++){
			for(int j = 0; j < y_def; j++){
				Shape point = new Rectangle(x+i, y+j, 1, 1);
				g2.setPaint(colors[i][j]);
				g2.fill(point);
			}
		}
	}

/**
 * Do the actual work
 */
	public void compute(){
		for(int x = 0; x < x_def; ++x){
			for(int y = 0; y < y_def; ++y){

				double xc = ax * x + bx;
				double yc = ay * y + by;
				int i = mandelbrot(xc, yc);
				colors[x][y] = MapColor(i);
			}
		}
	}

	/**
	 * 
	 * @param xc real part of a complex number
	 * @param yc imaginary part of a complex number
	 * @return  an integer between 0 and threshold indicating the time it takes to ensure it diverges
	 */
	private int mandelbrot(double xc, double yc){

		// Main cardioid
		double p = Math.sqrt((xc - .25)*(xc - .25)+yc*yc);
		if(xc < p - 2*p*p + .25){
			return threshold;
		}
		// First circle
		if((xc+1)*(xc+1) +yc*yc < .25*.25){
			return threshold;
		}

		// Other cases
		int k = 0;
		double x = 0;
		double y = 0;
		while (x * x + y * y < 4 && k < threshold) {
			double xt = x * x - y * y + xc;
			double yt = 2 * x * y + yc;
			x = xt;
			y = yt;
			k += 1;
		}
		return k;
	}

/**
 * Associates a color to an integer
 * @param i value that was returned by mandelbrot
 * @return the color in which the data will be drawn on the screen
 */
	private Color MapColor(int i){
		if(i==threshold){
			// Probably inside the set
			return Color.black;
		}else{
			// Definitely outside the set
			int r = (int) Math.min(255, 6.0 * i);
			int g = (int) Math.min(255, 4.0 * i);
			int b = (int) Math.min(255, 2.0 * i);

			return new Color(r,g,b);
		}

	}

}

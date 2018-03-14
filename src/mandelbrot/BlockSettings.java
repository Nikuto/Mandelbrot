package mandelbrot;

/**
 * All the data needed to build a new block
 */
public class BlockSettings {

	public final double x_min;
	public final double y_min;
	public final double x_max;
	public final double y_max;

	public final int x_def;
	public final int y_def;
	public final int threshold;

	/**
	 * Builds and stores all the data needed to create a new block
	 * @param x_min minimal abscissa of the block
	 * @param y_min minimal ordinate of the block
	 * @param x_max maximal abscissa of the block
	 * @param y_max maximal ordinate of the block
	 * @param x_def number of pixels in a block horizontally
	 * @param y_def number of pixels in a block vertically
	 * @param threshold Maximal number of iterations before we consider a pixel is in the set
	 */
	public BlockSettings(double x_min, double y_min, double x_max, double y_max, int x_def, int y_def, int threshold){
		this.x_min = x_min;
		this.y_min = y_min;
		this.x_max = x_max;
		this.y_max = y_max;
		
		this.x_def = x_def;
		this.y_def = y_def;
		this.threshold = threshold;
	}
}

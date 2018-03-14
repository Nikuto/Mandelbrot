package mandelbrot;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.Future;


import javax.swing.JPanel;
import javax.swing.Timer;



public class Vizualizor extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;

	private Timer timer;
	long tempsinit=0,tempsfin=0;

	private Future<Block> blocks[][];

	public final int block_size;

	public final int x_def;
	public final int y_def;
	public final int x_block_nbr;
	public final int y_block_nbr;

	public final int threshold;

	/**
	 * Creates the panel
	 * @param server reference to the server that manages the computation of the blocks
	 * @param x_min minimal abscissa of the drawn part of the plan
	 * @param y_min minimal ordinate of the drawn part of the plan
	 * @param x_max maximal abscissa of the drawn part of the plan
	 * @param y_max maximal ordinate of the drawn part of the plan
	 * @param x_def number of pixels horizontally
	 * @param y_def number of pixels vertically
	 * @param block_size number of pixel in a side of a block
	 * @param threshold Maximal number of iterations before we consider a pixel is in the set
	 */
	@SuppressWarnings("unchecked")
	public Vizualizor(Server server, double x_min, double y_min, double x_max, double y_max, int x_def, int y_def, int block_size, int threshold){
		super();
		
		timer=new Timer(100, this);
		timer.start();
		
		tempsinit = System.currentTimeMillis();
		this.x_def = x_def;
		this.y_def = y_def;
		this.block_size = block_size;
		this.x_block_nbr = x_def/block_size;
		this.y_block_nbr = y_def/block_size;
		this.threshold = threshold;

		blocks = new Future[x_block_nbr][y_block_nbr];
		double xsize = (x_max - x_min)/(x_block_nbr-1);
		double ysize = (y_max - y_min)/(y_block_nbr-1);
		for(int x = 0; x < x_block_nbr; x++){
			for(int y = 0; y < y_block_nbr; y++){
				double xmin = x_min + x * xsize;
				double ymin = y_min + y * ysize;
				blocks[x][y] = server.getBlock(new BlockSettings(xmin, ymin, xmin + xsize, ymin + ysize, block_size, block_size, threshold));
			}
		}
		Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
		
		
	}

	/**
	 * The panel is drawn every 100ms.
	 * Should only be called by the system.
	 */
	public void actionPerformed(ActionEvent ev){
		if(ev.getSource()==timer){
			repaint();
		}
	}

	/**
	 * Called when the window needs to be refreshed
	 */
	@Override
	public void paintComponent(Graphics g) {

		Graphics2D g2 = (Graphics2D) g;

		for(int x = 0; x < x_block_nbr; x++){
			for(int y = 0; y < y_block_nbr; y++){
				//TODO (just to attract your attention...)
				// blocks are drawn only if they are computed
				if (blocks[x][y].isDone()){
					try {
						blocks[x][y].get().draw(x*block_size, y*block_size, g2);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				
			}
		}
		//TODO : Faire marcher le timer :'(
//		if(blocks[x_block_nbr-1][y_block_nbr-1].isDone()){
//			tempsfin = System.currentTimeMillis();
//			System.out.println(tempsfin - tempsinit);
//		}


	}
}

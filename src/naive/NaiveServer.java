package naive;

import java.util.concurrent.Future;

import mandelbrot.Block;
import mandelbrot.BlockSettings;
import mandelbrot.Server;


/**
 * Simple implementation of Server that does the job as soon as it is submitted
 */
public class NaiveServer implements Server {
	/**
	 * getBlock computes locally the block and returns an encapsulation of the result in SynchronousBlock
	 * @param bs All the data needed to build a new block
	 */
	public Future<Block> getBlock(BlockSettings bs){
		return new NaiveBlock(bs);
	}
}

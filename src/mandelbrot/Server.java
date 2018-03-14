package mandelbrot;

import java.util.concurrent.Future;

/**
 * Manages the computation of the blocks
 * TODO: That is the interface that must be implemented
 */
public interface Server {
	/**
	 * This method is called each time a block has to be computed
	 * @param bs all the data about a block that has to be built
	 * @return a Future<Block> object that, when ready, contains the corresponding block
	 */
	public Future<Block> getBlock(BlockSettings bs);
}

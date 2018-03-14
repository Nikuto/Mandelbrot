package mandelbrot;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


/**
 * Simple implementation of Server that does the job as soon as it is submitted
 */
public class SynchronousServer implements Server {
	
	ExecutorService executorService;
	
	public SynchronousServer(int nb){
		executorService = Executors.newFixedThreadPool(nb);
	}
	/**
	 * getBlock computes locally the block and returns an encapsulation of the result in SynchronousBlock
	 * @param bs All the data needed to build a new block
	 */
	public Future<Block> getBlock(BlockSettings bs){
		SynchronousBlock block = new SynchronousBlock(bs);
		executorService.submit(block);
		return block;
		
	}
}

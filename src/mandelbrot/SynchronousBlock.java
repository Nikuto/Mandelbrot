package mandelbrot;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import mandelbrot.Block;
import mandelbrot.BlockSettings;



/**
 * Simple implementation of a Future<Block> that does the job as soon as it is constructed
 */
public class SynchronousBlock implements Future<Block>, Runnable {

	private Block block;
	private boolean finish;
	private boolean cancelled;

	/**
	 * Constructor, that also does the job
	 * @param bs All the data needed to build a new block
	 */
	public SynchronousBlock(BlockSettings bs){
		// Build a new block instance
		block = new Block(bs);
		finish = false;
		cancelled = false;
	}
	
	@Override
	public void run() {
		block.compute();
		finish = true;
		
	}
	/*
	 * From this point, all the methods implement methods from Future<Block>
	 * @see java.util.concurrent.Future
	 */

	@Override
	public Block get() throws InterruptedException, ExecutionException {
		return block;
	}

	@Override
	public Block get(long arg0, TimeUnit arg1) throws InterruptedException,
			ExecutionException, TimeoutException {
		return block;
	}

	@Override
	public boolean cancel(boolean mayInterruptIfRunning) {
		cancelled = mayInterruptIfRunning;
		return false;
	}

	@Override
	public boolean isCancelled() {
		return cancelled;
	}

	@Override
	public boolean isDone() {
		return finish;
	}

	

}

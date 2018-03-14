package naive;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import mandelbrot.Block;
import mandelbrot.BlockSettings;



/**
 * Simple implementation of a Future<Block> that does the job as soon as it is constructed
 */
public class NaiveBlock implements Future<Block> {

	private Block block;

	/**
	 * Constructor, that also does the job
	 * @param bs All the data needed to build a new block
	 */
	public NaiveBlock(BlockSettings bs){
		// Build a new block instance
		block = new Block(bs);
		// compute the color of the pixels locally
		block.compute();
	}

	/*
	 * From this point, all the methods implement methods from Future<Block>
	 * @see java.util.concurrent.Future
	 */
	
	@Override
	public boolean cancel(boolean arg0) {
		return false;
	}

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
	public boolean isCancelled() {
		return false;
	}

	@Override
	public boolean isDone() {
		return true;
	}

}

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

/*import java.awt.*;
import java.awt.image.*;

import java.util.*;

import javax.imageio.*;
import javax.imageio.metadata.*;
import javax.swing.*;
*/

public class FractalPanel extends Canvas implements Runnable {
	protected static final String memory = "Ran out of memeory.\nTry runing with -Xmx1g option.\n";

	protected SimpleFractal fractal;
	protected int iterations;
	protected long delay;
	private Thread animationThread;

	public FractalPanel() {
		this(null);
	}

	public FractalPanel(SimpleFractal fractal) {
		this(fractal, 10, 500);
	}

	public FractalPanel(SimpleFractal fractal, int iterations, long delay) {
		super();
		this.fractal = fractal;
		this.iterations = iterations;
		this.delay = delay;
	}

	public void start() {
		if (animationThread == null || !animationThread.isAlive()) {
			animationThread = new Thread(this);
			animationThread.start();
		}
	}

	public void stop() {
		if (animationThread != null) {
			while (true) {
				animationThread.interrupt();
				try {
					animationThread.join(100);
					break;
				} catch (InterruptedException ie) {

				}
			}
		}
	}

	public void run() {
		BufferStrategy bufferStrategy = null;
		int curIter = iterations;
		long sleepTime;
		long fireAt = System.nanoTime();
		while (!Thread.currentThread().isInterrupted()) {
			if (fractal != null) {
				if (++curIter > iterations) {
					clearFractal();
					curIter = 0;
				} else {
					fractal.next();
				}
			}

			sleepTime = (fireAt - System.nanoTime()) / 1000000;
			if (sleepTime > 0) {
				try {
					Thread.sleep(sleepTime);
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
				}
			}

			try {
				if (bufferStrategy == null || bufferStrategy.contentsLost()) {
					createBufferStrategy(2);
					bufferStrategy = getBufferStrategy();
				}
				render(bufferStrategy);
			} catch (IllegalStateException ise) {

			}

			fireAt += delay * 1000000;
		}
	}

	protected void render(BufferStrategy bufferStrategy) {
		if (bufferStrategy == null) return;
		do {
			do {
				Graphics g = bufferStrategy.getDrawGraphics();
				try {
					BufferedImage bufferedImage = fractal.getFractalImage(getWidth(), getHeight());
					g.drawImage(bufferedImage, 0, 0, null);
				} catch (OutOfMemoryError e) {
					System.out.println(memory);
					System.exit(-1);
				} finally {
					g.dispose();
					bufferStrategy.show();
				}
			} while (bufferStrategy.contentsRestored());
		} while (bufferStrategy.contentsLost());
	}

	public void setFractal(SimpleFractal fractal) {
		this.fractal = fractal;
	}

	public SimpleFractal getFractal() {
		return fractal;
	}

	public void setIterations(int iterations) {
		this.iterations = iterations;
	}

	public void setDelay(long delay) {
		this.delay = delay;
	}

	public void clearFractal() {
		if (fractal != null) {
			fractal.clearFractal();
		}
	}
}

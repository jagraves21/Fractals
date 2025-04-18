import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.awt.Window;

import java.lang.reflect.InvocationTargetException;

import java.util.Stack;
import java.util.concurrent.CountDownLatch;

import javax.swing.JFrame;

public class ComplexFractalToGIF extends MultiComplexFractal
{
	protected int maxIterations;
	protected boolean reflect;
	protected double xZoom;
	protected double yZoom;
	protected double multiplier;
	protected int startZoom;
	protected int stopZoom;

	protected int curIteration;
	protected AnimatedGifEncoder gifEncoder;

	public ComplexFractalToGIF(
		double x,
		double y,
		double width,
		ComplexFunction complexFunction,
		ConvergenceFunction convergenceFunction,
		ColorFunction colorFunction,
		ColorFunction colorFunction2,
		ColorFunction colorFunction3,
		ColorFunction colorFunction4,
		FractalType fractalType,
		FractalStyle fractalStyle,
		boolean cycleColors,
		int maxIterations,
		boolean reflect,
		double xZoom,
		double yZoom,
		double multiplier,
		int startZoom,
		int stopZoom
	) {
		super(
			x,
			y,
			width,
			complexFunction,
			convergenceFunction,
			colorFunction,
			colorFunction2,
			colorFunction3,
			colorFunction4,
			fractalType,
			fractalStyle,
			cycleColors
		);
		this.maxIterations = maxIterations;
		this.reflect = reflect;
		this.xZoom = xZoom;
		this.yZoom = yZoom;
		this.multiplier = multiplier;
		this.startZoom = startZoom;
		this.stopZoom = stopZoom;
	}

	public static void printProgressBar(int count, int total) {
		int totalBars = 50;
		int percent = 100 * count / total;
		int filledBars = (percent * totalBars) / 100;

		StringBuilder bar = new StringBuilder();
		if (count != 0) {
			bar.append("\r");
		}
		bar.append("[");
		for (int i = 0; i < filledBars; i++) {
			bar.append("=");
		}
		for (int i = filledBars; i < totalBars; i++) {
			bar.append(" ");
		}
		bar.append("] ").append(
			String.format("%" + 3 + "d", percent)
		).append("%");
		
		bar.append(" (").append(
			String.format("%" + String.valueOf(total).length() + "d", count)
		).append(" / ").append(total).append(")");
		System.out.print(bar.toString());
		System.out.flush();
		if (count == total) {
			System.out.println();
		}
	}

	public void start() {
		curIteration = 0;
		String fileName = toString().replace(" ", "") + ".gif";
		System.out.println(fileName);
		gifEncoder = new AnimatedGifEncoder();
		gifEncoder.start(fileName);
		gifEncoder.setDelay(100);
		gifEncoder.setRepeat(0);
		super.start();
	}

	public void stop() {
		gifEncoder.finish();
		super.stop();
	}

	public void draw(Graphics g, int gWidth, int gHeight) {
		/*if(curIteration == 0) {
			for(int ii=0; ii<50; ii++) {
				step(gWidth, gHeight);
			}
			synchronized (lock) {
				totalIterations = 0;
			}
		}*/

		BufferedImage img = new BufferedImage(gWidth, gHeight, BufferedImage.TYPE_INT_RGB);
		Graphics imageGraphics = img.createGraphics();
		super.draw(imageGraphics, gWidth, gHeight);
		imageGraphics.dispose();

		gifEncoder.addFrame(img);
		g.drawImage(img, 0, 0, null);

		printProgressBar(curIteration, maxIterations);
		if (curIteration > startZoom && curIteration < stopZoom) {
			double newOriginX = xZoom - multiplier * (xZoom - originX);
			double newOriginY = yZoom - multiplier * (yZoom - originY);
			double newViewWidth = viewWidth * multiplier;
			setWindow(newOriginX, newOriginY, newViewWidth);
		}

		if(curIteration++ == maxIterations) {
			Component comp = this;
			while (comp != null && !(comp instanceof Window)) {
				comp = comp.getParent();
			}
			if (comp != null) {
				((Window) comp).dispose();//setVisible(false);
			}
		}
	}

	public static void main(String[] args)
	{
		ArgumentParser argumentParser = new ArgumentParser(
			"ComplexFractalToGIF",
			800,
			600,
			null,
			null,
			null,
			null,
			false,
			200,
			false,
			0.0,
			0.0,
			1.0,
			0,
			Integer.MAX_VALUE
		);
		argumentParser.parseArguments(args, true, true);
		if(
			argumentParser.colorFunction2 != null ||
			argumentParser.colorFunction3 != null ||
			argumentParser.colorFunction4 != null
		) {
			if(argumentParser.colorFunction2 == null) {
				argumentParser.colorFunction2 = new PalletedColorFunction();
			}
			if(argumentParser.colorFunction3 == null) {
				argumentParser.colorFunction3 = new PalletedColorFunction();
			}
			if(argumentParser.colorFunction4 == null) {
				argumentParser.colorFunction4 = new PalletedColorFunction();
			}
		}

		ClassLoader classLoader = ComplexFractalToGIF.class.getClassLoader();
		for (String className : argumentParser.classes) {
			try {
				Class<?> aClass = classLoader.loadClass(className);
				AbstractComplexFunction complexFunction = (AbstractComplexFunction) aClass.getDeclaredConstructor().newInstance();

				ConvergenceFunction convergenceFunction = 
					argumentParser.convergenceFunction != null 
					? argumentParser.convergenceFunction 
					: complexFunction.getSuggestedConvergenceFunction();
				ColorFunction colorFunction = 
					argumentParser.colorFunction != null 
					? argumentParser.colorFunction 
					: complexFunction.getSuggestedColorFunction();
				FractalType fractalType = 
					argumentParser.fractalType != null 
					? argumentParser.fractalType 
					: complexFunction.getSuggestedFractalType();
				FractalStyle fractalStyle = 
					argumentParser.fractalStyle != null 
					? argumentParser.fractalStyle 
					: complexFunction.getSuggestedFractalStyle();

				AnimatedPanel animatedPanel = new ComplexFractalToGIF(
					complexFunction.getOriginX(),
					complexFunction.getOriginY(),
					complexFunction.getWindowWidth(),
					complexFunction,
					convergenceFunction,
					colorFunction,
					argumentParser.colorFunction2,
					argumentParser.colorFunction3,
					argumentParser.colorFunction4,
					fractalType,
					fractalStyle,
					argumentParser.cycleColors,
					argumentParser.iterations,
					argumentParser.reflect,
					argumentParser.xZoom,
					argumentParser.yZoom,
					argumentParser.multiplier,
					argumentParser.startZoom,
					argumentParser.stopZoom
				);
				JFrame frame = AnimatedPanel.display(
					animatedPanel,
					argumentParser.width,
					argumentParser.height
				);

				final CountDownLatch latch = new CountDownLatch(1);
				frame.addWindowListener(new WindowAdapter() {
					public void windowClosed(WindowEvent e) {
						latch.countDown();
					}
				});
	
				try {
					latch.await();
				} catch (InterruptedException ie) {

				}
			} catch (ClassNotFoundException e) {
				System.out.println("Class not found: " + className);
			} catch (InstantiationException
					| IllegalAccessException
					| InvocationTargetException
					| NoSuchMethodException e) {
				System.out.println("Could not instantiate " + className + ": " + e.getMessage());
			} catch (ClassCastException e) {
				System.out.println(className + " does not extend AbstractComplexFunction.");
			}
		}
	}
}

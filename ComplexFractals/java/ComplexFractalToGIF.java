import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;

import java.lang.reflect.InvocationTargetException;

import java.util.Stack;
import java.util.concurrent.CountDownLatch;

import javax.swing.JFrame;

public class ComplexFractalToGIF extends ComplexFractal
{
	int maxIterations;
	int curIteration;
	AnimatedGifEncoder gifEncoder;

	public ComplexFractalToGIF(
		double x,
		double y,
		double width,
		ComplexFunction complexFunction,
		ConvergenceFunction convergenceFunction,
		ColorFunction colorFunction,
		FractalType fractalType,
		FractalStyle fractalStyle,
		boolean colorsCycle
	) {
		super(x, y, width, complexFunction, convergenceFunction, colorFunction, fractalType, fractalStyle, colorsCycle);

		maxIterations = 120;
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
		step(gWidth, gHeight);

		BufferedImage img = new BufferedImage(gWidth, gHeight, BufferedImage.TYPE_INT_RGB);
		Graphics imageGraphics = img.createGraphics();
		drawFractal(imageGraphics, gWidth, gHeight);
		imageGraphics.dispose();

		if (curIteration % 2 == 0) {
			gifEncoder.addFrame(img);
		}

		g.drawImage(img, 0, 0, null);

		System.out.println(curIteration + " curIteration");
		if(curIteration++ == maxIterations) {
			gifEncoder.finish();
			System.exit(0);
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
			false
		);
		argumentParser.parseArguments(args, true, false);

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
					fractalType,
					fractalStyle,
					argumentParser.colorsCycle
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

				System.out.println("Window closed. Proceeding.");
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

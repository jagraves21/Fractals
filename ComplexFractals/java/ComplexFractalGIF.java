import java.awt.Graphics;
import java.awt.Graphics2D;

import java.awt.image.BufferedImage;

import java.util.Stack;

public class ComplexFractalGIF extends ComplexFractal
{
	int maxIterations;
	int curIteration;
	AnimatedGifEncoder gifEncoder;

	public ComplexFractalGIF(double x, double y, double width, ComplexFunction complexFunction, ConvergenceFunction convergenceFunction, ColorFunction colorFunction, FractalType fractalType, FractalStyle fractalStyle, boolean colorsCycle) {
		super(x, y, width, complexFunction, convergenceFunction, colorFunction, fractalType, fractalStyle, colorsCycle);

		maxIterations = 20;
	}

	public void start() {
		curIteration = 0;
		gifEncoder = new AnimatedGifEncoder();
		gifEncoder.start(toString().replace(" ", "") + ".gif");
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
		gifEncoder.addFrame(img);

		g.drawImage(img, 0, 0, null);

		System.out.println(curIteration + " curIteration");
		if(curIteration++ == maxIterations) {
		}
	}

	public static void main(String[] args) {
		//ComplexFunction complexFunction = new TreeFunction();
		ComplexFunction complexFunction = new Infinity();
		ConvergenceFunction convergenceFunction = new ModulusFour();
		ColorFunction colorFunction = new PalletedColorFunction(PalletedColorFunction.getColorSpan(PalletedColorFunction.RAINBOW, 10));
		//PalletedColorFunction.getBlackRedYellow(10);
		ComplexFractal.FractalType fractalType = ComplexFractal.FractalType.MOVING;
		ComplexFractal.FractalStyle fractalStyle = ComplexFractal.FractalStyle.STANDARD;
		AnimatedPanel fractal = new ComplexFractalGIF(complexFunction.getOriginX(),complexFunction.getOriginY(),complexFunction.getWindowWidth(),complexFunction,convergenceFunction,colorFunction,fractalType,fractalStyle, false);
		AnimatedPanel.display(fractal);
	}
}

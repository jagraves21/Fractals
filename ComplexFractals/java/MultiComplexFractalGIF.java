import java.awt.Graphics;

import java.awt.image.BufferedImage;

import java.util.Stack;

public class MultiComplexFractalGIF extends MultiComplexFractal {
	public MultiComplexFractalGIF(ComplexFunction complexFunction, ConvergenceFunction convergenceFunction) {
		this(complexFunction, convergenceFunction, PalletedColorFunction.getRainbow(10), PalletedColorFunction.getRedBlue(10), PalletedColorFunction.getBlues(10), new PalletedColorFunction());
	}

	public MultiComplexFractalGIF(ComplexFunction complexFunction, ConvergenceFunction convergenceFunction, ColorFunction color1, ColorFunction color2, ColorFunction color3, ColorFunction color4) {
		this(complexFunction, convergenceFunction, color1, color2, color3, color4, FractalType.ITERATIVE);
	}

	public MultiComplexFractalGIF(ComplexFunction complexFunction, ConvergenceFunction convergenceFunction, ColorFunction color1, ColorFunction color2, ColorFunction color3, ColorFunction color4, FractalStyle fractalStyle) {
		this(complexFunction, convergenceFunction, color1, color2, color3, color4, FractalType.ITERATIVE, fractalStyle);
	}

	public MultiComplexFractalGIF(ComplexFunction complexFunction, ConvergenceFunction convergenceFunction, ColorFunction color1, ColorFunction color2, ColorFunction color3, ColorFunction color4, FractalType fractalType) {
		this(complexFunction, convergenceFunction, color1, color2, color3, color4, fractalType, FractalStyle.STANDARD);
	}

	public MultiComplexFractalGIF(ComplexFunction complexFunction, ConvergenceFunction convergenceFunction, ColorFunction color1, ColorFunction color2, ColorFunction color3, ColorFunction color4, FractalType fractalType, FractalStyle fractalStyle) {
		this(0, 0, 3, complexFunction, convergenceFunction, color1, color2, color3, color4, fractalType, fractalStyle);
	}

	public MultiComplexFractalGIF(double x, double y, double width, ComplexFunction complexFunction, ConvergenceFunction convergenceFunction, ColorFunction color1, ColorFunction color2, ColorFunction color3, ColorFunction color4) {
		this(x, y, width, complexFunction, convergenceFunction, color1, color2, color3, color4, FractalType.ITERATIVE, FractalStyle.STANDARD);
	}

	public MultiComplexFractalGIF(double x, double y, double width, ComplexFunction complexFunction, ConvergenceFunction convergenceFunction, ColorFunction color1, ColorFunction color2, ColorFunction color3, ColorFunction color4, FractalType fractalType) {
		this(x, y, width, complexFunction, convergenceFunction, color1, color2, color3, color4, fractalType, FractalStyle.STANDARD);
	}

	public MultiComplexFractalGIF(double x, double y, double width, ComplexFunction complexFunction, ConvergenceFunction convergenceFunction, ColorFunction color1, ColorFunction color2, ColorFunction color3, ColorFunction color4, FractalStyle fractalStyle) {
		this(x, y, width, complexFunction, convergenceFunction, color1, color2, color3, color4, FractalType.ITERATIVE, fractalStyle);
	}

	public MultiComplexFractalGIF(double x, double y, double width, ComplexFunction complexFunction, ConvergenceFunction convergenceFunction, ColorFunction color1, ColorFunction color2, ColorFunction color3, ColorFunction color4, FractalType fractalType, FractalStyle fractalStyle) {
		super(x, y, width, complexFunction, convergenceFunction, color1, color2, color3, color4, fractalType, fractalStyle);
	}

	public void run() {
		AnimatedGifEncoder e = new AnimatedGifEncoder();
		e.start(toString().replace(" ", "") + ".gif");
		e.setDelay(100);
		e.setRepeat(0);

		Stack<BufferedImage> frames = new Stack<BufferedImage>();
		Graphics g;
		BufferedImage img = null;
		try {
			int gWidth, gHeight;

			while(!Thread.currentThread().isInterrupted()) {
				gWidth = getWidth();
				gHeight = getHeight();

				img = new BufferedImage(gWidth, gHeight, BufferedImage.TYPE_INT_RGB);
				if(maxIteration < 550) {
					maxIteration+=3;
				}
				g = img.createGraphics();
				draw(g, gWidth, gHeight);
				g.dispose();
				e.addFrame(img);
				render(img);
				frames.push(img);
				System.out.println("push frame " + frames.size());
			}
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			System.out.println("clean up");
			while(!frames.isEmpty()) {
				System.out.println("pop frame " + frames.size());
				img = frames.pop();
				e.addFrame(img);
			}
			e.finish();
			System.exit(-1);
		}
	}

	public static void main(String[] args) {
		ComplexFunction complexFunction = new MandelbrotFunction();
		ConvergenceFunction convergenceFunction = new ModulusTwo();
		ColorFunction colorFunction = PalletedColorFunction.getRainbow(25);
		ComplexFractal.FractalType fractalType = ComplexFractal.FractalType.ITERATIVE;
		ComplexFractal.FractalStyle fractalStyle = ComplexFractal.FractalStyle.STANDARD;

		ColorFunction color1 = PalletedColorFunction.getRainbow(10);
		ColorFunction color2 = PalletedColorFunction.getRedBlue(10);
		ColorFunction color3 = PalletedColorFunction.getBlues(10);
		ColorFunction color4 = new PalletedColorFunction();

		AnimatedPanel fractal = new MultiComplexFractalGIF(complexFunction.getOriginX(),complexFunction.getOriginY(),complexFunction.getWindowWidth(),complexFunction,convergenceFunction,color1,color2,color3,color4);
		AnimatedPanel.display(fractal);
	}
}
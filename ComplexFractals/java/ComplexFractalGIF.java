import java.awt.Graphics;

import java.awt.image.BufferedImage;

import java.util.Stack;

public class ComplexFractalGIF extends ComplexFractal
{
	public ComplexFractalGIF(ComplexFunction complexFunction, ConvergenceFunction convergenceFunction) {
		this(complexFunction, convergenceFunction, new PalletedColorFunction());
	}
	
	public ComplexFractalGIF(ComplexFunction complexFunction, ConvergenceFunction convergenceFunction, ColorFunction colorFunction) {
		this(complexFunction, convergenceFunction, colorFunction, FractalType.ITERATIVE);
	}
	
	public ComplexFractalGIF(ComplexFunction complexFunction, ConvergenceFunction convergenceFunction, ColorFunction colorFunction, FractalStyle fractalStyle) {
		this(complexFunction, convergenceFunction, colorFunction, FractalType.ITERATIVE, fractalStyle);
	}
	
	public ComplexFractalGIF(ComplexFunction complexFunction, ConvergenceFunction convergenceFunction, ColorFunction colorFunction, FractalType fractalType) {
		this(complexFunction, convergenceFunction, colorFunction, fractalType, FractalStyle.STANDARD);
	}
	
	public ComplexFractalGIF(ComplexFunction complexFunction, ConvergenceFunction convergenceFunction, ColorFunction colorFunction, FractalType fractalType, FractalStyle fractalStyle) {
		this(0, 0, 3, complexFunction, convergenceFunction, colorFunction, fractalType, fractalStyle);
	}
	
	public ComplexFractalGIF(double x, double y, double width, ComplexFunction complexFunction, ConvergenceFunction convergenceFunction, ColorFunction colorFunction) {
		this(x, y, width, complexFunction, convergenceFunction, colorFunction, FractalType.ITERATIVE, FractalStyle.STANDARD);
	}
	
	public ComplexFractalGIF(double x, double y, double width, ComplexFunction complexFunction, ConvergenceFunction convergenceFunction, ColorFunction colorFunction, FractalType fractalType) {
		this(x, y, width, complexFunction, convergenceFunction, colorFunction, fractalType, FractalStyle.STANDARD);
	}
	
	public ComplexFractalGIF(double x, double y, double width, ComplexFunction complexFunction, ConvergenceFunction convergenceFunction, ColorFunction colorFunction, FractalStyle fractalStyle) {
		this(x, y, width, complexFunction, convergenceFunction, colorFunction, FractalType.ITERATIVE, fractalStyle);
	}
	
	public ComplexFractalGIF(double x, double y, double width, ComplexFunction complexFunction, ConvergenceFunction convergenceFunction, ColorFunction colorFunction, FractalType fractalType, FractalStyle fractalStyle) {
		super(x, y, width, complexFunction, convergenceFunction, colorFunction, fractalType, fractalStyle);
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
			
			int frameCount = 0;
			gWidth = 1920;//getWidth();
			gHeight = 1080;//getHeight();
			img = new BufferedImage(gWidth, gHeight, BufferedImage.TYPE_INT_RGB);
			while(!Thread.currentThread().isInterrupted() && frameCount++ <= 3200) {
				g = img.createGraphics();
				draw(g, gWidth, gHeight);
				g.dispose();
				render(img);
				
				e.addFrame(img);
				frames.push(img);
				System.out.println("push frame " + frames.size());
				
				/*String name = toString().replace(" ", "") + "_" + String.format("%05d", frameCount) + ".png";
				java.io.File outputfile = new java.io.File(name);
				javax.imageio.ImageIO.write(img, "png", outputfile);*/
			}
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
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
		//ComplexFunction complexFunction = new TreeFunction();
		ComplexFunction complexFunction = new Infinity();
		ConvergenceFunction convergenceFunction = new ModulusFour();
		ColorFunction colorFunction = new PalletedColorFunction(PalletedColorFunction.getColorSpan(PalletedColorFunction.RAINBOW, 10));
		//PalletedColorFunction.getBlackRedYellow(10);
		ComplexFractal.FractalType fractalType = ComplexFractal.FractalType.MOVING;
		ComplexFractal.FractalStyle fractalStyle = ComplexFractal.FractalStyle.STANDARD;
		AnimatedPanel fractal = new ComplexFractalGIF(complexFunction.getOriginX(),complexFunction.getOriginY(),complexFunction.getWindowWidth(),complexFunction,convergenceFunction,colorFunction,fractalType,fractalStyle);
		AnimatedPanel.display(fractal);
	}
}

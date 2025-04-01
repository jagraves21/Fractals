import java.awt.Graphics;

import java.awt.image.BufferedImage;

import java.util.Stack;

public class AttractorGIF extends CosFlameFractal
{
	public AttractorGIF() {
		super();
	}
	
	public void run() {
		AnimatedGifEncoder e = new AnimatedGifEncoder();
		e.start(toString().replace(" ", "") + ".gif");
		e.setDelay(100);
		e.setRepeat(0);
		
		int gWidth, gHeight;
		Stack<BufferedImage> frames = new Stack<BufferedImage>();
		Graphics g;
		BufferedImage img = null;
		try {
			int frameCount=0;
			while(!Thread.currentThread().isInterrupted() && frameCount++ < 25) {
				gWidth = getWidth();
				gHeight = getHeight();
				img = new BufferedImage(gWidth, gHeight, BufferedImage.TYPE_INT_RGB);
				g = img.createGraphics();
				draw(g, gWidth, gHeight);
				g.dispose();
				e.addFrame(img);
				render(img);
				frames.push(img);
			}
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			while(!frames.isEmpty()) {
				img = frames.pop();
				e.addFrame(img);
			}
			e.finish();
			System.exit(-1);
		}
	}
	
	public static void main(String[] args) {
		/*ComplexFunction function = new Mandelbrow();
		AnimatedPanel fractal = new SetGIF(0,0,8,function,null);
		AnimatedPanel.display(fractal);*/
		
		/*ColorField colorField = new ColorField(ColorField.getColorSpan(ColorField.RAINBOW, 25, false));
		ComplexFunction function = new MandelbrotSet();
		AnimatedPanel fractal = new SetGIF(-0.75,0,2.3,function,colorField);
		AnimatedPanel.display(fractal);*/
		
		AnimatedPanel fractal = new AttractorGIF();
		AnimatedPanel.display(fractal);
	}
}

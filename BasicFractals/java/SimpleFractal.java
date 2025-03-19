import java.util.*;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.RenderingHints;

import java.awt.image.*;

import java.io.*;
import javax.imageio.metadata.*;
import javax.imageio.*;

public abstract class SimpleFractal extends AbstractFractal
{
	public boolean REVERSE = true;
	public int IMAGE_TYPE = BufferedImage.TYPE_INT_RGB;
	
	protected MyPoint minPoint;
	protected MyPoint maxPoint;
	
	protected MyPoint newMinPoint;
	protected MyPoint newMaxPoint;
	
	public SimpleFractal()
	{
		minPoint = new MyPoint(Double.MAX_VALUE, Double.MAX_VALUE);
		maxPoint = new MyPoint(Double.MIN_VALUE, Double.MIN_VALUE);
	}
	
	public static MyPoint translate(MyPoint center, MyPoint point, double radius, double thetaOff)
	{
		double theta = Math.atan2(point.y-center.y,point.x-center.x);
		theta -= thetaOff;
		
		return new MyPoint(center.x + (radius * Math.cos(theta)), center.y + (radius * Math.sin(theta)));
	}
	
	public abstract List<FractalShape> getFractal();
	public abstract void next();
	public abstract void clearFractal();
	public abstract int getSuggestedIterations();
	
	protected Paint getForeground()
	{
		return Color.WHITE;
	}
	
	protected Paint getBackground()
	{
		return Color.BLACK;
	}
	
	public static List<FractalShape> scaleShapes(List<FractalShape> shapes, double xFactor, double yFactor)
	{
		List<FractalShape> scaledShapes = new LinkedList<FractalShape>();
		
		Iterator<FractalShape> iter = shapes.iterator();
		while(iter.hasNext())
		{
			scaledShapes.add((iter.next()).scale(xFactor, yFactor));
		}
		
		return scaledShapes;
	}
	
	public static List<FractalShape> translateShapes(List<FractalShape> shapes, MyPoint point)
	{
		List<FractalShape> translatedShapes = new LinkedList<FractalShape>();
		
		Iterator<FractalShape> iter = shapes.iterator();
		while(iter.hasNext())
		{
			translatedShapes.add((iter.next()).translate(point));
		}
		
		return translatedShapes;
	}
	
	protected List<FractalShape> centerShapes(List<FractalShape> shapes, double newWidth, double newHeight)
	{
		double width = newWidth;
		double height = newHeight;
		
		double oldWidth = (int)(maxPoint.x - minPoint.x);
		double oldHeight = (int)(maxPoint.y - minPoint.y);
		
		if(oldWidth == 0)
		{
			oldWidth = 1;
		}
		if(oldHeight == 0)
		{
			oldHeight = 1;
		}
		
		double xRatio = oldWidth / newWidth;
		double yRatio = oldHeight / newHeight;
		
		if(xRatio > yRatio)
		{
			// oldX / oldY = newX / newY
			newHeight = newWidth / (oldWidth / oldHeight);
		}
		else if(yRatio > xRatio)
		{
			newWidth = (oldWidth / oldHeight) * newHeight;
		}
		
		newHeight -= 30;
		newWidth -= 30;
		
		List<FractalShape> newShapes = new LinkedList<FractalShape>();
		double xFactor = newWidth/(double)oldWidth;
		double yFactor = newHeight/(double)oldHeight;
		
		newMinPoint = minPoint.scale(xFactor, yFactor);
		newMaxPoint = maxPoint.scale(xFactor, yFactor);
		
		double xT = -newMinPoint.x + (width-newWidth)/2;
		double yT = -newMinPoint.y + (height-newHeight)/2;
		
		MyPoint point = new MyPoint(xT, yT);
		newMinPoint = newMinPoint.translate(point);
		newMaxPoint = newMaxPoint.translate(point);
		
		Iterator<FractalShape> iter = shapes.iterator();
		while(iter.hasNext())
		{
			newShapes.add((iter.next()).scale(xFactor, yFactor).translate(point));
		}
		
		return newShapes;
	}
	
	public BufferedImage getFractalImage(int width, int height)
	{
		BufferedImage image = new BufferedImage(width, height, IMAGE_TYPE);
		//BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);
		
		List<FractalShape> shapes = getFractal();
		findMinMax(shapes);
		
		shapes = centerShapes(shapes, width, height);
		plotShapes(shapes, width, height, image.createGraphics());
		
		return image;
	}
	
	public void findMinMax()
	{
		findMinMax(getFractal());
	}
	
	protected void findMinMax(List<FractalShape> shapes)
	{
		minPoint.x = Double.MAX_VALUE;
		minPoint.y = Double.MAX_VALUE;
		
		maxPoint.x = Double.MIN_VALUE;
		maxPoint.y = Double.MIN_VALUE;
		
		FractalShape shape;
		
		Iterator<FractalShape> iter = shapes.iterator();
		while(iter.hasNext())
		{
			shape = iter.next();
			shape.checkMinMax(minPoint, maxPoint);
		}
	}
	
	public MyPoint getMinPoint()
	{
		return minPoint;
	}
	
	public MyPoint getMaxPoint()
	{
		return maxPoint;
	}
		
	protected void plotShapes(List<FractalShape> shapes, int width, int height, Graphics2D g)
	{
		g.setPaint(getBackground());
		g.fillRect(0, 0, width, height);
		g.setPaint(getForeground());
		
		/*try {
			BufferedImage image = ImageIO.read(new File("snowfield.jpeg"));
			g.drawImage(image, 0, 0, null);
		} catch (IOException e) {
			e.printStackTrace();
		}*/
		
		FractalShape shape;
		Iterator<FractalShape> iter = shapes.iterator();
		while(iter.hasNext())
		{
			shape = iter.next();
			shape.paint(g);
		}
	}
		
	public void createGIF(String fileName, int width, int height, int delay, int iterations)
	{
		try
		{
			clearFractal();
			
			AnimatedGifEncoder e = new AnimatedGifEncoder();
			e.start(fileName);
			e.setDelay(delay);
			e.setRepeat(0);
			
			BufferedImage[] images = new BufferedImage[iterations+1];
			for(int ii=0; ii < iterations; ii++)
			{
				images[ii] = getFractalImage(width, height);
				next();
			}
			images[iterations] = getFractalImage(width, height);
			
			for(int ii=0; ii < images.length; ii++)
			{
				e.addFrame(images[ii]);
				
				try
				{
					String name = fileName.replace(".gif", "_" + (1000+ii) + ".jpg");
					java.io.File outputfile = new java.io.File(name);
					javax.imageio.ImageIO.write(images[ii], "png", outputfile);
				}
				catch(IOException ioe)
				{
					ioe.printStackTrace();
					System.exit(-1);
				}
			}
			
			if(REVERSE)
			{
				for(int ii=images.length-2; ii > 0; ii--)
				{
					e.addFrame(images[ii]);
				}
			}
			
			e.finish();
		}
		catch(OutOfMemoryError e)
		{
			//e.printStackTrace();
			System.out.println(memory);
			System.exit(-1);
		}
	}
	
	public void createPic(String fileName, String type, int width, int height, int iterations)
	{
		try
		{
			clearFractal();
			for(int ii=0; ii < iterations; ii++)
			{
				next();
			}
			
			BufferedImage image = getFractalImage(width, height);
			File outputfile = new File(fileName);
			ImageIO.write(image, type, outputfile);
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		catch(OutOfMemoryError e)
		{
			//e.printStackTrace();
			System.out.println(memory);
			System.exit(-1);
		}
	}
	
	public void createJPG(String fileName, int width, int height, int iterations)
	{
		createPic(fileName, "jpg", width, height, iterations);
	}
	
	public void createPNG(String fileName, int width, int height, int iterations)
	{
		createPic(fileName, "png", width, height, iterations);
	}
	
	public String toString()
	{
		return "Simple Fractal";
	}
}
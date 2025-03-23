import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Point;
import java.awt.RadialGradientPaint;
import java.awt.RenderingHints;

import java.util.*;

import javax.swing.*;

public class HenonAttractor extends SimpleFractal
{
	public static final double r108 = (108 * Math.PI / 180.0);
	public static final double s108 = Math.sin(r108);
	public static final double c108 = Math.cos(r108);
	public static final double t108 = Math.tan(r108);
	
	protected int iteration;
	protected MyPoint point;
	protected List<FractalShape> shapes;
	
	public HenonAttractor()
	{
		iteration = 0;
		shapes = new LinkedList<FractalShape>();
		
		createBase();
	}
	
	public int getSuggestedIterations()
	{
		return 20;
	}
	
	protected Paint getForeground()
	{
		MyPoint mid = newMinPoint.midPoint(newMaxPoint);
		MyPoint tmp = new MyPoint(newMinPoint.x, mid.y);
		
		float[] dist = {0.0f, 0.75f};
		Color[] colors = {Color.RED, Color.BLUE};
		
		RadialGradientPaint paint = new RadialGradientPaint((float)mid.x, (float)mid.y, (float)mid.distance(tmp), dist, colors);
		return paint;
	}
	
	protected void plotShapes(List<FractalShape> shapes, int width, int height, Graphics2D g)
	{
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		super.plotShapes(shapes, width, height, g);
	}
	
	public List<FractalShape> getFractal()
	{
		return shapes;
	}
	
	public void clearFractal()
	{
		createBase();
	}
	
	protected void createBase()
	{
		point = new MyPoint(0.5,0.5,Color.RED);
		//shapes.add(point);
	}
	
	Random random = new Random();
	public void next()
	{
		double offset = ((double)iteration) / getSuggestedIterations();
		if(offset > 1.0)
		{
			offset = 1;
		}
		
		int red = (int)Math.round(255 * offset);
		int green = (int)Math.round(0 * offset);
		int blue = (int)Math.round(255 - 255 * offset);
		Color color = new Color(red, green, blue);
		System.out.println(offset + ": " + red + " " + green + " " + blue);
		
		double x;
		double y;
		
		for(int ii=0; ii < 2000; ii++)
		{
			x = 1 + point.y - 1.4*Math.pow(point.x,2);
			y = 0.3 * point.x;
			point = new MyPoint(x,y,color);
			shapes.add(point);
		}
		
		iteration++;
	}
	
	public String toString()
	{
		return "Henon Attractor";
	}
	
	public static void main(String[] args)
	{
		SimpleFractal.main(args);
	}
}

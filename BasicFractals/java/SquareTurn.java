import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Point;
import java.awt.RadialGradientPaint;
import java.awt.RenderingHints;

import java.util.*;

import javax.swing.*;

public class SquareTurn extends SimpleFractal
{
	public static final double r108 = (108 * Math.PI / 180.0);
	public static final double s108 = Math.sin(r108);
	public static final double c108 = Math.cos(r108);
	public static final double t108 = Math.tan(r108);
	
	protected int iteration;
	protected Rectangle square;
	protected List<FractalShape> shapes;
	
	public SquareTurn()
	{
		iteration = 0;
		shapes = new LinkedList<FractalShape>();
		
		createBase();
	}
	
	public int getSuggestedIterations()
	{
		return 60;
	}
	
	protected Paint getForeground()
	{
		MyPoint mid = newMinPoint.midPoint(newMaxPoint);
		MyPoint tmp = new MyPoint(newMinPoint.x, mid.y);
		
		float[] dist = {0.0f, 0.75f};
		Color[] colors = {new Color(31,255,31), new Color(153,255,153)};
		
		RadialGradientPaint paint = new RadialGradientPaint((float)mid.x, (float)mid.y, (float)mid.distance(tmp), dist, colors);
		return paint;
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
		MyPoint p1 = new MyPoint(0,0);
		MyPoint p2 = new MyPoint(WIDTH,0);
		MyPoint p3 = new MyPoint(WIDTH,HEIGHT);
		MyPoint p4 = new MyPoint(0,HEIGHT);
		square = new Rectangle(p1,p2,p3,p4,new Color(0,128,255),false);
		
		shapes.add(square);
	}
	
	Random random = new Random();
	public void next()
	{
		iteration++;
		double rotate = 0.95;
		
		MyPoint p1 = square.p1.interiorPoint(square.p2, rotate);
		MyPoint p2 = square.p2.interiorPoint(square.p3, rotate);
		MyPoint p3 = square.p3.interiorPoint(square.p4, rotate);
		MyPoint p4 = square.p4.interiorPoint(square.p1, rotate);
		
		double percent = Math.abs((iteration-15))/15.0;
		percent = 1-(square.p1.distance(square.p2)*square.p2.distance(square.p3))/(WIDTH*HEIGHT);
		
		int red = (int)(255 * percent);
		int green = (int)(128);
		int blue = (int)(255 - 255 * percent);
		
		square = new Rectangle(p1,p2,p3,p4,new Color(red,green,blue),false);
		
		shapes.add(square);
	}
	
	public String toString()
	{
		return "Square Turn";
	}
	
	public static void main(String[] args)
	{
		int iterations = 60;
		
		if(args.length > 0)
		{
			iterations = Integer.parseInt(args[0]);
		}
		
		FractalPanel fractal = new FractalPanel(new SquareTurn());		
		JFrame frame = new JFrame(fractal.toString());
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		frame.setSize(800,600);
		
		frame.setContentPane(fractal);
		for(int ii=0; ii < iterations; ii++)
		{
			fractal.next();
		}
		
		frame.setVisible(true);
	}
}
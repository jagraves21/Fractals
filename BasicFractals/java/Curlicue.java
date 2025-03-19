import java.awt.Color;
import java.awt.Paint;
import java.awt.Point;
import java.awt.GradientPaint;

import java.util.*;

import javax.swing.*;

public class Curlicue extends SimpleFractal
{
	//public static final double S = Math.PI;
	public static double S = 0.5772156659015328;
	double theta = 0;
	double phi = 0;
	MyPoint lastPoint;
	protected List<FractalShape> lines;

	public Curlicue()
	{
		super();
		lines = new LinkedList<FractalShape>();
		createBase();
	}
	
	public int getSuggestedIterations()
	{
		return 20;
	}
	
	protected Paint getForeground()
	{
		Color c1 = Color.BLUE;
		Color c2 = Color.CYAN;
		
		Point p1 = new Point((int)newMinPoint.x, (int)newMinPoint.y);
		Point p2 = new Point((int)newMaxPoint.x, (int)newMaxPoint.y);
		GradientPaint paint = new GradientPaint(p1, c1, p2, c2);
		
		return paint;
	}
	
	public List<FractalShape> getFractal()
	{
		return lines;
	}
	
	public void clearFractal()
	{
		createBase();
	}
	
	protected void createBase()
	{
		/*theta = 0;
		phi = 0;
		lines.clear();
		
		lastPoint = new MyPoint(0,0);
		MyPoint nextPoint = new MyPoint(lastPoint.x + Math.cos(phi), lastPoint.y + Math.sin(phi));
		lines.add(new LineSegment(lastPoint, nextPoint));
		lastPoint = nextPoint;
		
		phi = (theta + phi) % (2 * Math.PI);
		theta = (theta + 2 * Math.PI * S) % (2 * Math.PI);*/
		
		next();
	}
	
	public void myNext()
	{
		MyPoint nextPoint = new MyPoint(lastPoint.x + Math.cos(phi), lastPoint.y + Math.sin(phi));
		lines.add(new LineSegment(lastPoint, nextPoint));
		lastPoint = nextPoint;
		
		phi = (theta + phi) % (2 * Math.PI);
		theta = (theta + 2 * Math.PI * S) % (2 * Math.PI);
	}
	
	public void next()
	{
		S += 0.0000001;
		
		theta = 0;
		phi = 0;
		lines.clear();
		
		lastPoint = new MyPoint(0,0);
		MyPoint nextPoint = new MyPoint(lastPoint.x + Math.cos(phi), lastPoint.y + Math.sin(phi));
		lines.add(new LineSegment(lastPoint, nextPoint));
		lastPoint = nextPoint;
		
		phi = (theta + phi) % (2 * Math.PI);
		theta = (theta + 2 * Math.PI * S) % (2 * Math.PI);
		
		for(int ii=0; ii < 1000; ii++)
		{
			myNext();
		}
	}
	
	public String toString()
	{
		return "Koch Snowflake";
	}
	
	public static void main(String[] args)
	{
		int iterations = 5;
		
		if(args.length > 0)
		{
			iterations = Integer.parseInt(args[0]);
		}
		
		FractalPanel fractal = new FractalPanel(new Curlicue());		
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
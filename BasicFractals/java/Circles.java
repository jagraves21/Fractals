import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Point;
import java.awt.RadialGradientPaint;

import java.util.*;

import javax.swing.*;

public class Circles extends SimpleFractal
{
	public final static MyPoint NORTH = new MyPoint(WIDTH/2, 0);
	public final static MyPoint SOUTH = new MyPoint( WIDTH/2, HEIGHT);
	public final static MyPoint EAST = new MyPoint(WIDTH, HEIGHT/2);
	public final static MyPoint WEST = new MyPoint(0, HEIGHT/2);
	
	protected int iteration;
	protected int space;
	protected List<FractalShape> circles;
	
	public Circles()
	{
		super();
		circles = new LinkedList<FractalShape>();
		createBase();
	}
	
	public int getSuggestedIterations()
	{
		return 20;
	}
	
	protected Paint getForeground()
	{
		MyPoint mid = newMinPoint.midPoint(newMaxPoint);
		mid.y += mid.y * (1.0/10.0);
		
		float[] dist = {0.3f, 1.0f};
		Color[] colors = {Color.BLUE, Color.RED};
		
		RadialGradientPaint paint = new RadialGradientPaint((float)mid.x, (float)mid.y, (float)(mid.distance(newMinPoint)*(6.0/10.0)), dist, colors);
		return paint;
	}
	
	public List<FractalShape> getFractal()
	{
		return circles;
	}
	
	public void clearFractal()
	{
		createBase();
	}
	
	protected void createBase()
	{
		iteration = 0;
		space = 1;
		
		MyPoint[] points = new MyPoint[2];
		points[0] = NORTH;
		points[1] = SOUTH;
		
		circles.clear();
		circles.add(new Circle(NORTH, NORTH.distance(SOUTH)));
	}
	
	public void next()
	{
		iteration++;
		double step = 1.0 / (iteration+1);
		double offset = step;
		
		MyPoint[] points = new MyPoint[2 + iteration];
		points[0] = NORTH;
		points[1 + iteration] = SOUTH;
		
		MyPoint point;
		circles.clear();
		for(int ii=0; ii < iteration; ii++, offset += step)
		{
			point = NORTH.interiorPoint(SOUTH, offset);
			points[ii+1] = point;
		}
		
		Circle circle;
		for(int ii=0; ii < points.length; ii++)
		{
			for(int jj=ii+1; jj < points.length; jj++)
			{
				point = points[ii].midPoint(points[jj]);
				circle = new Circle(point, point.distance(points[jj]));
				circles.add(circle);
			}
		}
		
		points[0] = WEST;
		points[1 + iteration] = EAST;
		
		offset = step;
		for(int ii=0; ii < iteration; ii++, offset += step)
		{
			point = WEST.interiorPoint(EAST, offset);
			points[ii+1] = point;
			//circles.add(point);
		}
		
		for(int ii=0; ii < points.length; ii++)
		{
			for(int jj=ii+1; jj < points.length; jj++)
			{
				point = points[ii].midPoint(points[jj]);
				circle = new Circle(point, point.distance(points[jj]));
				circles.add(circle);
			}
		}
	}
	
	public String toString()
	{
		return "Circles";
	}
	
	public static void main(String[] args)
	{
		int iterations = 20;
		
		if(args.length > 0)
		{
			iterations = Integer.parseInt(args[0]);
		}
		
		FractalPanel fractal = new FractalPanel(new Circles());		
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
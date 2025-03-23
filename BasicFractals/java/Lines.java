import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Point;
import java.awt.RadialGradientPaint;

import java.util.*;

import javax.swing.*;

public class Lines extends SimpleFractal
{
	protected int iteration;
	protected int space;
	protected MyPoint[] points;
	protected LineSegment[] lines;
	protected List<FractalShape> shapes;
	
	public Lines()
	{
		super();
		points = null;
		shapes = new LinkedList<FractalShape>();
		createBase();
	}
	
	public int getSuggestedIterations()
	{
		int i = 20;
		
		return (i*(i-1)/2) - 1 - 2;
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
		return shapes;
	}
	
	public void clearFractal()
	{
		createBase();
	}
	
	protected void createBase()
	{
		iteration = 3;
		space = 1;
		
		MyPoint p1 = new MyPoint(WIDTH/2, HEIGHT/2);
		MyPoint p2 = new MyPoint(WIDTH/2, 0);
		double radius = p1.distance(p2);
		double delta = (2 * Math.PI)/iteration;
		double theta = 0;
		
		points = new MyPoint[iteration];
		lines = new LineSegment[iteration];
		for(int ii=0; ii < iteration; ii++, theta += delta)
		{
			points[ii] = translate(p1, p2, radius, theta);
			points[ii].paint = Color.WHITE;
			lines[ii] = new NewLineSegment(points[ii], points[ii]);
		}
		
		shapes.clear();
		for(int ii=0; ii < points.length; ii++)
		{
			shapes.add(points[ii]);
			shapes.add(lines[ii]);
		}
	}
	
	public void next()
	{
		if((space%points.length) == 0)
		{
			iteration++;
			space = 0;
			
			MyPoint p1 = new MyPoint(WIDTH/2, HEIGHT/2);
			MyPoint p2 = new MyPoint(WIDTH/2, 0);
			double radius = p1.distance(p2);
			double delta = (2 * Math.PI)/iteration;
			double theta = 0;
			
			points = new MyPoint[iteration];
			lines = new LineSegment[iteration];
			for(int ii=0; ii < iteration; ii++, theta += delta)
			{
				points[ii] = translate(p1, p2, radius, theta);
				points[ii].paint = Color.WHITE;
				lines[ii] = new NewLineSegment(points[ii], points[ii]);
			}
			
			shapes.clear();
			for(int ii=0; ii < points.length; ii++)
			{
				shapes.add(points[ii]);
				shapes.add(lines[ii]);
			}
		}
		
		for(int ii=0; ii < points.length; ii++)
		{
			lines[ii].p2 = points[(ii+space)%points.length];
		}
		space++;
	}
	
	public String toString()
	{
		return "Lines";
	}
	
	public static void main(String[] args)
	{
		SimpleFractal.main(args);
	}
}

class NewLineSegment extends LineSegment
{
	public NewLineSegment(MyPoint p1, MyPoint p2)
	{
		super(p1,p2);
	}
	
	public void paint(Graphics2D g)
	{
		if(p1.x != p2.x || p1.y != p2.y)
		{
			Paint tmpPaint = g.getPaint();
			Point tp1 = new Point((int)p1.x, (int)p1.y);
			Point tp2 = new Point((int)p2.x, (int)p2.y);
			g.setPaint(new GradientPaint(tp1, Color.RED, tp2, Color.BLUE));
			g.drawLine((int)Math.round(p1.x), (int)Math.round(p1.y), (int)Math.round(p2.x), (int)Math.round(p2.y));
			g.setPaint(tmpPaint);
		}
	}
}

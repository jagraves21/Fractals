import java.awt.Color;
import java.awt.Paint;
import java.awt.Point;
import java.awt.GradientPaint;

import java.util.*;

import javax.swing.*;

public class Plusses extends SimpleFractal
{
	protected List<FractalShape> lines;
	protected List<LineSegment> nextLines;
	
	public Plusses()
	{
		super();
		lines = new LinkedList<FractalShape>();
		nextLines = new LinkedList<LineSegment>();
		createBase();
	}
	
	public int getSuggestedIterations()
	{
		return 5;
	}
		
	protected Paint getForeground()
	{
		Color c1 = Color.RED;
		Color c2 = Color.ORANGE;
		
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
		MyPoint north = new MyPoint(WIDTH/2.0, 0);
		MyPoint south = new MyPoint(WIDTH/2.0, HEIGHT);
		MyPoint east = new MyPoint(WIDTH, HEIGHT/2.0);
		MyPoint west = new MyPoint(0, HEIGHT/2.0);
		
		lines.clear();
		nextLines.clear();
		lines.add(new LineSegment(north, south));
		lines.add(new LineSegment(east, west));
		
		MyPoint mid = north.midPoint(south);
		
		nextLines.add(new LineSegment(mid,north));
		nextLines.add(new LineSegment(mid,south));
		nextLines.add(new LineSegment(mid,west));
		nextLines.add(new LineSegment(mid,east));
	}
	
	public void next()
	{
		double x;
		double y;
		double dist;
		MyPoint p1;
		MyPoint p2;
		LineSegment line;
		MyPoint mid;
		MyPoint np1;
		MyPoint np2;
		
		List<LineSegment> newLines = new LinkedList<LineSegment>();
		
		Iterator<LineSegment> iter = nextLines.iterator();
		while(iter.hasNext())
		{
			line = iter.next();
			p1 = line.p1;
			p2 = line.p2;
			
			x = ((p2.x - p1.x)/3) + p2.x;
			y = ((p2.y - p1.y)/3) + p2.y;
			mid = p1.twoThirdsPoint(p2);
			
			dist = mid.distance(p2);
			
			if(p1.x == p2.x)
			{
				np1 = new MyPoint(mid.x-dist, mid.y);
				np2 = new MyPoint(mid.x+dist, mid.y);
			}
			else
			{
				np1 = new MyPoint(mid.x, mid.y-dist);
				np2 = new MyPoint(mid.x, mid.y+dist);
			}
			
			lines.add(new LineSegment(np1, np2));
			
			newLines.add(new LineSegment(mid,np1));
			newLines.add(new LineSegment(mid,np2));
			newLines.add(new LineSegment(mid,p2));
		}
		
		nextLines = newLines;
	}
	
	public String toString()
	{
		return "Plusses";
	}
	
	public static void main(String[] args)
	{
		int iterations = 5;
		
		if(args.length > 0)
		{
			iterations = Integer.parseInt(args[0]);
		}
		
		FractalPanel fractal = new FractalPanel(new Plusses());		
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
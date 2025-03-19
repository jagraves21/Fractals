import java.awt.Color;
import java.awt.Paint;
import java.awt.Point;
import java.awt.GradientPaint;

import java.util.*;

import javax.swing.*;

public class KochAntisnowflake extends SimpleFractal
{
	protected List<FractalShape> lines;

	public KochAntisnowflake()
	{
		super();
		lines = new LinkedList<FractalShape>();
		createBase();
	}
	
	public int getSuggestedIterations()
	{
		return 5;
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
		MyPoint center = new MyPoint(WIDTH/2.0, HEIGHT/2.0);
	
		double ratio = 9.0/10.0;
		double dist = (center.x > center.y) ? center.y*ratio : center.x*ratio;
		
		double y = s30 * dist;
		double x = c30 * dist;
		
		MyPoint p1 = new MyPoint(center.x, center.y - dist);
		MyPoint p2 = new MyPoint(center.x + x, center.y + y);
		MyPoint p3 = new MyPoint(center.x - x, center.y + y);
		
		lines.clear();
		lines.add(new LineSegment(p2,p1));
		lines.add(new LineSegment(p3,p2));
		lines.add(new LineSegment(p1,p3));
	}
	
	public void next()
	{
		double x;
		double y;
		MyPoint p1;
		MyPoint p2;
		MyPoint np1;
		MyPoint np2;
		MyPoint mid;
		LineSegment line;
		
		List<FractalShape> newLines = new LinkedList<FractalShape>();
		
		Iterator<FractalShape> iter = lines.iterator();
		while(iter.hasNext())
		{
			line = (LineSegment)iter.next();
			
			p1 = line.p1;
			p2 = line.p2;
			
			x = (p2.x-p1.x)/3;
			y = (p2.y-p1.y)/3;
			
			np1 = new MyPoint(p1.x+x, p1.y+y);
			np2 = new MyPoint(p2.x-x, p2.y-y);
			
			x = c60 * (np1.x - np2.x) - s60 * (np1.y - np2.y) + np2.x;
			y = s60 * (np1.x - np2.x) + c60 * (np1.y - np2.y) + np2.y;
			
			mid = translate(np1, np2, np1.distance(np2), r60);
			
			newLines.add(new LineSegment(p1,np1));
			newLines.add(new LineSegment(np1,mid));
			newLines.add(new LineSegment(mid,np2));
			newLines.add(new LineSegment(np2,p2));
		}
		
		lines = newLines;
	}
	
	public String toString()
	{
		return "Koch Antisnowflake";
	}
	
	public static void main(String[] args)
	{
		int iterations = 5;
		
		if(args.length > 0)
		{
			iterations = Integer.parseInt(args[0]);
		}
		
		FractalPanel fractal = new FractalPanel(new KochAntisnowflake());		
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
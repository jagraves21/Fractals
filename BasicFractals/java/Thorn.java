import java.awt.Color;
import java.awt.Paint;
import java.awt.Point;
import java.awt.RadialGradientPaint;

import java.util.*;

import javax.swing.*;

public class Thorn extends SimpleFractal
{
	protected List<FractalShape> lines;
	
	public Thorn()
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
		MyPoint mid = newMinPoint.midPoint(newMaxPoint);
		
		float[] dist = {0.5f, 1.0f};
		Color[] colors = {Color.RED, Color.BLACK};
		
		RadialGradientPaint paint = new RadialGradientPaint((float)mid.x, (float)mid.y, (float)mid.distance(newMinPoint), dist, colors);
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
		MyPoint p1 = new MyPoint(0, 0);
		MyPoint p2 = new MyPoint(WIDTH, 0);
		MyPoint p3 = new MyPoint(WIDTH, HEIGHT);
		MyPoint p4 = new MyPoint(0, HEIGHT);
		
		lines.clear();
		lines.add(new LineSegment(p1,p2));
		lines.add(new LineSegment(p2,p3));
		lines.add(new LineSegment(p3,p4));
		lines.add(new LineSegment(p4,p1));
	}
	
	public void next()
	{
		double d1;
		double d2;
		double radius;
		double theta;
		double ratio = 9.0/10.0;
		MyPoint p1;
		MyPoint p2;
		MyPoint np1;
		MyPoint np2;
		MyPoint np3;
		MyPoint np4;
		MyPoint np5;
		LineSegment line;
		
		List<FractalShape> newLines = new LinkedList<FractalShape>();
		
		Iterator<FractalShape> iter = lines.iterator();
		while(iter.hasNext())
		{
			line = (LineSegment)iter.next();
			
			p1 = line.p1;
			p2 = line.p2;
			
			np1 = p1;
			np5 = p2;
			
			d1 = np1.distance(np5)/2;
			d2 = d1 - (d1 * ratio);
			d1 = d1 * ratio;
			
			theta = Math.atan(d1 / d2) - r30;
			radius = Math.sqrt(Math.pow(d1,2) + Math.pow(d2,2));
			
			np2 = np5.rotate(np1, d1, 0);
			np4 = np1.rotate(np5, d1, 0);
			
			np3 = np2.rotate(np4, radius, theta);
			
			newLines.add(new LineSegment(np1, np2));
			newLines.add(new LineSegment(np2, np3));
			newLines.add(new LineSegment(np3, np4));
			newLines.add(new LineSegment(np4, np5));
		}
		
		lines = newLines;
	}
	
	public String toString()
	{
		return "Thorn";
	}
	
	public static void main(String[] args)
	{
				SimpleFractal.main(args);
	}
}

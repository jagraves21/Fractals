import java.awt.Color;
import java.awt.Paint;
import java.awt.Point;
import java.awt.GradientPaint;

import java.util.*;

import javax.swing.*;

public class ThreeBranch extends SimpleFractal
{
	protected List<FractalShape> lines;
	
	public ThreeBranch()
	{
		super();
		lines = new LinkedList<FractalShape>();
		createBase();
	}
	
	protected Paint getForeground()
	{
		Color c1 = Color.MAGENTA;
		Color c2 = Color.RED;
		
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
		MyPoint p1 = new MyPoint(0 + (WIDTH/10.0), HEIGHT/2);
		MyPoint p2 = new MyPoint(WIDTH - (WIDTH/10.0), HEIGHT/2);
		
		lines.clear();
		lines.add(new LineSegment(p2,p1));
	}
	
	public void next()
	{
		double radius;
		double x;
		double y;
		MyPoint p1;
		MyPoint p2;
		MyPoint mid;
		MyPoint np1;
		MyPoint np2;
		
		double yOff;
		
		LineSegment line;
		
		List<FractalShape> newLines = new LinkedList<FractalShape>();
		
		Iterator<FractalShape> iter = lines.iterator();
		while(iter.hasNext())
		{
			line = (LineSegment)iter.next();
			p1 = line.p1;
			p2 = line.p2;
			
			mid = p1.midPoint(p2);
			radius = p1.distance(mid);
			
			np1 = mid.rotate(p1, radius, r60);
			np2 = mid.rotate(p2, radius, -1 * r60);
			
			newLines.add(new LineSegment(p1,np1));
			newLines.add(new LineSegment(np1,np2));
			newLines.add(new LineSegment(np2,p2));
		}
		
		lines = newLines;
	}
	
	public String toString()
	{
		return "Three Branch";
	}
	
	public static void main(String[] args)
	{
				SimpleFractal.main(args);
	}
}

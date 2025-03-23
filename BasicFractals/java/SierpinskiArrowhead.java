import java.awt.Color;
import java.awt.Paint;
import java.awt.Point;
import java.awt.RadialGradientPaint;

import java.util.*;

import javax.swing.*;

public class SierpinskiArrowhead extends SimpleFractal
{
	protected List<FractalShape> lines;
	
	public SierpinskiArrowhead()
	{
		super();
		lines = new LinkedList<FractalShape>();
		createBase();
	}
	
	public int getSuggestedIterations()
	{
		return 10;
	}
	
	protected Paint getForeground()
	{
		MyPoint mid = newMinPoint.midPoint(newMaxPoint);
		
		mid.y += ((newMaxPoint.y - newMinPoint.y)/2.0) * (1.0/10.0);
		
		float[] dist = {0.0f, 0.50f, 0.6f};
		Color[] colors = {Color.RED, Color.BLUE, Color.GREEN};
		
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
		MyPoint p1 = new MyPoint(0 + (WIDTH/10.0), HEIGHT*(4.0/5.0));
		MyPoint p2 = new MyPoint(WIDTH - (WIDTH/10.0), HEIGHT*(4.0/5.0));
		
		lines.clear();
		lines.add(new LineSegment(p2,p1));
		lines.add(new MyPoint(0,0));
		lines.add(new MyPoint(WIDTH,HEIGHT));
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
		
		FractalShape shape;
		LineSegment line;
		
		List<FractalShape> newLines = new LinkedList<FractalShape>();
		
		Iterator<FractalShape> iter = lines.iterator();
		while(iter.hasNext())
		{
			shape = iter.next();
			if(shape instanceof LineSegment)
			{
				line = (LineSegment)shape;
				p1 = line.p1;
				p2 = line.p2;
				
				mid = p1.midPoint(p2);
				radius = p1.distance(mid);
				
				np1 = translate(mid, p1, radius, r60);
				np2 = translate(mid, p2, radius, -1 * r60);
				
				newLines.add(new LineSegment(np1,p1));
				newLines.add(new LineSegment(np1,np2));
				newLines.add(new LineSegment(p2,np2));
			}
			else
			{
				newLines.add(shape);
			}
		}
		
		lines = newLines;
	}
	
	public String toString()
	{
		return "Sierpinski Arrowhead";
	}
	
	public static void main(String[] args)
	{
				SimpleFractal.main(args);
	}
}

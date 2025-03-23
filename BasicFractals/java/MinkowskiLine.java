import java.awt.Color;
import java.awt.Paint;
import java.awt.Point;
import java.awt.RadialGradientPaint;

import java.util.*;

import javax.swing.*;

public class MinkowskiLine extends SimpleFractal
{
	protected List<FractalShape> lines;
	
	public MinkowskiLine()
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
		MyPoint tmp = new MyPoint(newMinPoint.x, mid.y);
		
		float[] dist = {0.0f, 0.75f};
		Color[] colors = {new Color(51,102,255), new Color(0,14,56)};
		
		RadialGradientPaint paint = new RadialGradientPaint((float)mid.x, (float)mid.y, (float)mid.distance(tmp), dist, colors);
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
		double ratio = 9.0/10.0;
		
		MyPoint p1 = new MyPoint(0, HEIGHT/2);
		MyPoint p2 = new MyPoint(WIDTH, HEIGHT/2);
		
		lines.clear();
		lines.add(new LineSegment(p1,p2));
	}
	
	public void next()
	{
		double radius;
		MyPoint p1;
		MyPoint p2;
		MyPoint np1;
		MyPoint np2;
		MyPoint np3;
		MyPoint np4;
		MyPoint np5;
		MyPoint np6;
		MyPoint np7;
		MyPoint np8;
		MyPoint mid;
		LineSegment line;
		
		List<FractalShape> newLines = new LinkedList<FractalShape>();
		
		Iterator<FractalShape> iter = lines.iterator();
		while(iter.hasNext())
		{
			line = (LineSegment)iter.next();
			
			p1 = line.p1;
			p2 = line.p2;
			
			np1 = p1;
			np8 = p2;
			
			mid = np1.midPoint(np8);
			
			np2 = p1.midPoint(mid);
			np7 = mid.midPoint(p2);
			
			radius = np1.distance(np2);
			
			np3 = translate(np2, mid, radius, -r90);
			np4 = translate(mid, np2, radius, r90);
			
			np5 = translate(mid, np7, radius, r90);
			np6 = translate(np7, mid, radius, -r90);
			
			newLines.add(new LineSegment(np1,np2));
			newLines.add(new LineSegment(np2,np3));
			newLines.add(new LineSegment(np3,np4));
			newLines.add(new LineSegment(np4,np5));
			newLines.add(new LineSegment(np5,np6));
			newLines.add(new LineSegment(np6,np7));
			newLines.add(new LineSegment(np7,np8));
		}
		
		lines = newLines;
	}
	
	public String toString()
	{
		return "Minkowski Line";
	}
	
	public static void main(String[] args)
	{
		SimpleFractal.main(args);
	}
}

import java.awt.Color;
import java.awt.Paint;
import java.awt.Point;
import java.awt.RadialGradientPaint;

import java.util.*;

import javax.swing.*;

public class KochLineMod extends SimpleFractal
{
	protected List<FractalShape> lines;
	
	public KochLineMod()
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
		
		float[] dist = {0.3f, 1.0f};
		Color[] colors = {Color.WHITE, Color.BLUE};
		
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
		double ratio = 9.0/10.0;
		
		MyPoint p1 = new MyPoint(0, 0);
		MyPoint p2 = new MyPoint(WIDTH, HEIGHT);
		MyPoint p3 = new MyPoint(0, HEIGHT);
		MyPoint p4 = new MyPoint(WIDTH, 0);
		
		lines.clear();
		lines.add(new LineSegment(p1,p2));
		lines.add(new LineSegment(p2,p1));
		lines.add(new LineSegment(p3,p4));
		lines.add(new LineSegment(p4,p3));
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
		return "Koch Line Modification";
	}
	
	public static void main(String[] args)
	{
		SimpleFractal.main(args);
	}
}

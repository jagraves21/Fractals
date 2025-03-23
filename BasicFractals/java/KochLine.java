import java.awt.Color;
import java.awt.Paint;
import java.awt.Point;
import java.awt.RadialGradientPaint;

import java.util.*;

import javax.swing.*;

public class KochLine extends SimpleFractal
{
	protected List<FractalShape> lines;
	
	public KochLine()
	{
		super();
		lines = new LinkedList<FractalShape>();
		createBase();
	}
	
	public int getSuggestedIterations()
	{
		return 8;
	}
	
	protected Paint getForeground()
	{
		MyPoint mid = newMinPoint.midPoint(newMaxPoint);
		MyPoint tmp = new MyPoint(newMinPoint.x, mid.y);
		
		float[] dist = {0.30f, 1.0f};
		Color[] colors = {Color.RED, Color.YELLOW};
		
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
		
		lines.add(new MyPoint(0,0));
		lines.add(new MyPoint(WIDTH,HEIGHT));
		lines.add(new LineSegment(p1,p2));
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
			else
			{
				newLines.add(shape);
			}
		}
		
		lines = newLines;
	}
	
	public String toString()
	{
		return "Koch Line";
	}
	
	public static void main(String[] args)
	{
		SimpleFractal.main(args);
	}
}

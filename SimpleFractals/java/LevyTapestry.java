import java.awt.Color;
import java.awt.Paint;
import java.awt.Point;
import java.awt.RadialGradientPaint;
import java.awt.MultipleGradientPaint.CycleMethod;

import java.util.*;

import javax.swing.*;

public class LevyTapestry extends SimpleFractal
{
	protected List<FractalShape> lines;
	
	public LevyTapestry()
	{
		super();
		lines = new LinkedList<FractalShape>();
		createBase();
	}
	
	public int getSuggestedIterations()
	{
		return 13;
	}
	
	protected Paint getForeground()
	{
		MyPoint mid = newMinPoint.midPoint(newMaxPoint);
		
		float[] dist = {0.0f, 0.75f};
		Color[] colors = {Color.BLUE, Color.GREEN};
		
		RadialGradientPaint paint = new RadialGradientPaint((float)mid.x, (float)mid.y, (float)mid.distance(newMinPoint), (float)mid.x, (float)mid.y, dist, colors, CycleMethod.NO_CYCLE);
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
		MyPoint p1 = new MyPoint(WIDTH/4, HEIGHT/4);
		MyPoint p2 = new MyPoint(WIDTH - (WIDTH/4), HEIGHT/4);
		MyPoint p3 = new MyPoint(WIDTH - (WIDTH/4), HEIGHT - (HEIGHT/4));
		MyPoint p4 = new MyPoint(WIDTH/4, HEIGHT - (HEIGHT/4));
		
		lines.clear();
		
		lines.add(new MyPoint(0,0));
		lines.add(new MyPoint(WIDTH,HEIGHT));
		lines.add(new LineSegment(p2,p1));
		lines.add(new LineSegment(p3,p2));
		lines.add(new LineSegment(p4,p3));
		lines.add(new LineSegment(p1,p4));
	}
	
	public void next()
	{
		double radius;
		MyPoint p1;
		MyPoint p2;
		MyPoint np1 = new MyPoint(0,0);
		MyPoint np2 = new MyPoint(0,0);
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
				line = (LineSegment) shape;
				p1 = line.p1;
				p2 = line.p2;
				
				radius = p1.distance(p2) / sqrt2;
				
				mid = translate(p1, p2, radius, r45);
				
				newLines.add(new LineSegment(p1,mid));
				newLines.add(new LineSegment(mid,p2));
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
		return "Levy Tapestry";
	}
	
	public static void main(String[] args)
	{
		SimpleFractal.main(args);
	}
}

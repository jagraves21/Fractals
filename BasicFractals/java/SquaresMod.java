import java.awt.Color;
import java.awt.Paint;
import java.awt.Point;
import java.awt.RadialGradientPaint;

import java.util.*;

import javax.swing.*;

public class SquaresMod extends SimpleFractal
{
	protected static final Color[] colors = {Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN, Color.BLUE, new Color(138, 43, 226)};
	
	protected List<FractalShape> rectangles;
	protected List<Rectangle> nextRectangles;
	protected int iteration;
	
	public SquaresMod()
	{
		super();
		rectangles = new LinkedList<FractalShape>();
		nextRectangles = new LinkedList<Rectangle>();
		iteration = 0;
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
		Color[] colors = {new Color(147,112,219), Color.RED};
		
		RadialGradientPaint paint = new RadialGradientPaint((float)mid.x, (float)mid.y, (float)mid.distance(newMaxPoint), dist, colors);
		return paint;
	}
	
	public List<FractalShape> getFractal()
	{
		return rectangles;
	}
	
	public void clearFractal()
	{
		createBase();
	}
	
	protected void createBase()
	{
		MyPoint p1 = new MyPoint(WIDTH/4, HEIGHT/4);
		MyPoint p2 = new MyPoint(WIDTH-p1.x, p1.y);
		MyPoint p3 = new MyPoint(WIDTH-p1.x, HEIGHT-p1.y);
		MyPoint p4 = new MyPoint(p1.x,p3.y);
		
		rectangles.clear();
		nextRectangles.clear();
		iteration = 0;
		
		Rectangle rect = new Rectangle(p1,p2,p3,p4,colors[iteration%colors.length],true);
		nextRectangles.add(rect);
		
		rectangles.add(rect);
		rectangles.add(new MyPoint(0,0));
		rectangles.add(new MyPoint(WIDTH,HEIGHT));
	}
	
	public void next()
	{
		iteration++;
		
		double dist;
		MyPoint p1;
		MyPoint p2;
		MyPoint p3;
		MyPoint p4;
		MyPoint np1;
		MyPoint np2;
		MyPoint np3;
		MyPoint np4;
		Rectangle rect;
		Rectangle nRect;
		
		List<Rectangle> newRectangles = new LinkedList<Rectangle>();
		
		Iterator<Rectangle> iter = nextRectangles.iterator();
		while(iter.hasNext())
		{
			rect = iter.next();
			p1 = rect.p1;
			p2 = rect.p2;
			p3 = rect.p3;
			p4 = rect.p4;
			
			dist = p1.distance(p2)/4;
			
			np1 = new MyPoint(p1.x-dist,p1.y-dist);
			np2 = new MyPoint(p1.x+dist,p1.y-dist);
			np3 = new MyPoint(p1.x+dist,p1.y+dist);
			np4 = new MyPoint(p1.x-dist,p1.y+dist);
			nRect = new Rectangle(np1,np2,np3,np4,colors[iteration%colors.length],true);
			rectangles.add(nRect);
			newRectangles.add(nRect);
			
			np1 = new MyPoint(p2.x-dist,p2.y-dist);
			np2 = new MyPoint(p2.x+dist,p2.y-dist);
			np3 = new MyPoint(p2.x+dist,p2.y+dist);
			np4 = new MyPoint(p2.x-dist,p2.y+dist);
			nRect = new Rectangle(np1,np2,np3,np4,colors[iteration%colors.length],true);
			rectangles.add(nRect);
			newRectangles.add(nRect);
			
			np1 = new MyPoint(p3.x-dist,p3.y-dist);
			np2 = new MyPoint(p3.x+dist,p3.y-dist);
			np3 = new MyPoint(p3.x+dist,p3.y+dist);
			np4 = new MyPoint(p3.x-dist,p3.y+dist);
			nRect = new Rectangle(np1,np2,np3,np4,colors[iteration%colors.length],true);
			rectangles.add(nRect);
			newRectangles.add(nRect);
			
			np1 = new MyPoint(p4.x-dist,p4.y-dist);
			np2 = new MyPoint(p4.x+dist,p4.y-dist);
			np3 = new MyPoint(p4.x+dist,p4.y+dist);
			np4 = new MyPoint(p4.x-dist,p4.y+dist);
			nRect = new Rectangle(np1,np2,np3,np4,colors[iteration%colors.length],true);
			rectangles.add(nRect);
			newRectangles.add(nRect);
		}
		
		nextRectangles = newRectangles;
	}
	
	public String toString()
	{
		return "Squares Mod";
	}
	
	public static void main(String[] args)
	{
				SimpleFractal.main(args);
	}
}

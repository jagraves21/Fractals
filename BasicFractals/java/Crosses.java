import java.awt.Color;
import java.awt.Paint;
import java.awt.Point;
import java.awt.RadialGradientPaint;

import java.util.*;

import javax.swing.*;

public class Crosses extends SimpleFractal
{
	protected List<FractalShape> lines;
	protected List<MyPoint[]> crosses;
	
	public Crosses()
	{
		super();
		lines = new LinkedList<FractalShape>();
		crosses = new LinkedList<MyPoint[]>();
		createBase();
	}
	
	public int getSuggestedIterations()
	{
		return 7;
	}
		
	protected Paint getForeground()
	{
		MyPoint mid = newMinPoint.midPoint(newMaxPoint);
		double radius = mid.x * 0.8;
		
		float[] dist = {0.0f, 0.5f, 1.0f};
		Color[] colors = {Color.RED, Color.BLUE, Color.GREEN};
		
		RadialGradientPaint paint = new RadialGradientPaint((float)mid.x, (float)mid.y, (float)radius, dist, colors);
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
		MyPoint p1 = new MyPoint(WIDTH/2.0, HEIGHT/4.0);
		MyPoint p2 = new MyPoint(p1.x, HEIGHT-p1.y);
		MyPoint p3 = new MyPoint(WIDTH/4.0, HEIGHT/2.0);
		MyPoint p4 = new MyPoint(WIDTH-p3.x, p3.y);
		
		lines.clear();
		crosses.clear();
		
		lines.add(new MyPoint(0,0));
		lines.add(new MyPoint(WIDTH,HEIGHT));
		lines.add(new LineSegment(p1, p2));
		lines.add(new LineSegment(p3, p4));
		
		MyPoint[] cross = new MyPoint[4];
		cross[0] = p1;
		cross[1] = p2;
		cross[2] = p3;
		cross[3] = p4;
		crosses.add(cross);
	}
	
	public void next()
	{
		double dist;
		MyPoint p1;
		MyPoint p2;
		MyPoint p3;
		MyPoint p4;
		MyPoint np1;
		MyPoint np2;
		MyPoint np3;
		MyPoint np4;
		MyPoint[] cross;
		
		List<MyPoint[]> newCrosses = new LinkedList<MyPoint[]>();
		
		Iterator<MyPoint[]> iter = crosses.iterator();
		while(iter.hasNext())
		{
			cross = iter.next();
			p1 = cross[0];
			p2 = cross[1];
			p3 = cross[2];
			p4 = cross[3];
			
			dist = p1.distance(p2)/4;
			
			np1 = new MyPoint(p3.x, p1.y-dist);
			np2 = new MyPoint(p3.x, p1.y+dist);
			np3 = new MyPoint(p3.x-dist, p1.y);
			np4 = new MyPoint(p3.x+dist, p1.y);
			cross = new MyPoint[4];
			cross[0] = np1;
			cross[1] = np2;
			cross[2] = np3;
			cross[3] = np4;
			newCrosses.add(cross);
			lines.add(new LineSegment(np1,np2));
			lines.add(new LineSegment(np3,np4));
			
			np1 = new MyPoint(p4.x, p1.y - dist);
			np2 = new MyPoint(p4.x, p1.y + dist);
			np3 = new MyPoint(p4.x - dist, p1.y);
			np4 = new MyPoint(p4.x + dist, p1.y);
			cross = new MyPoint[4];
			cross[0] = np1;
			cross[1] = np2;
			cross[2] = np3;
			cross[3] = np4;
			newCrosses.add(cross);
			lines.add(new LineSegment(np1,np2));
			lines.add(new LineSegment(np3,np4));
			
			np1 = new MyPoint(p4.x, p2.y - dist);
			np2 = new MyPoint(p4.x, p2.y + dist);
			np3 = new MyPoint(p4.x - dist, p2.y);
			np4 = new MyPoint(p4.x + dist, p2.y);
			cross = new MyPoint[4];
			cross[0] = np1;
			cross[1] = np2;
			cross[2] = np3;
			cross[3] = np4;
			newCrosses.add(cross);
			lines.add(new LineSegment(np1,np2));
			lines.add(new LineSegment(np3,np4));
			
			np1 = new MyPoint(p3.x, p2.y - dist);
			np2 = new MyPoint(p3.x, p2.y + dist);
			np3 = new MyPoint(p3.x - dist, p2.y);
			np4 = new MyPoint(p3.x + dist, p2.y);
			cross = new MyPoint[4];
			cross[0] = np1;
			cross[1] = np2;
			cross[2] = np3;
			cross[3] = np4;
			newCrosses.add(cross);
			lines.add(new LineSegment(np1,np2));
			lines.add(new LineSegment(np3,np4));
		}
		
		crosses = newCrosses;
	}
	
	public String toString()
	{
		return "Crosses";
	}
	
	public static void main(String[] args)
	{
		int iterations = 7;
		
		if(args.length > 0)
		{
			iterations = Integer.parseInt(args[0]);
		}
		
		FractalPanel fractal = new FractalPanel(new Crosses());		
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
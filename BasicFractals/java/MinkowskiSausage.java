import java.awt.Color;
import java.awt.Paint;
import java.awt.Point;
import java.awt.RadialGradientPaint;

import java.util.*;

import javax.swing.*;

public class MinkowskiSausage extends SimpleFractal
{
	protected List<FractalShape> lines;
	
	public MinkowskiSausage()
	{
		lines = new LinkedList<FractalShape>();
		createBase();
	}
	
	public int getSuggestedIterations()
	{
		return 6;
	}
	
	protected Paint getForeground()
	{
		MyPoint mid = newMinPoint.midPoint(newMaxPoint);
		MyPoint tmp = new MyPoint(newMinPoint.x, mid.y);
		
		float[] dist = {0.0f, 0.75f};
		Color[] colors = {new Color(31,255,31), new Color(153,255,153)};
		
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
		double radius;
		MyPoint p1;
		MyPoint p2;
		MyPoint np1;
		MyPoint np2;
		MyPoint np3;
		MyPoint np4;
		LineSegment line;
		
		List<FractalShape> newLines = new LinkedList<FractalShape>();
		
		Iterator<FractalShape> iter = lines.iterator();
		while(iter.hasNext())
		{
			line = (LineSegment)iter.next();
			
			p1 = line.p1;
			p2 = line.p2;
			
			np1 = p1;
			np4 = p2;
			
			radius = np1.distance(np4)/2;
			
			radius = c30 * radius;
			
			np2 = translate(np1, np4, radius, -r30);
			np3 = translate(np4, np1, radius, -r30);
			
			newLines.add(new LineSegment(np1,np2));
			newLines.add(new LineSegment(np2,np3));
			newLines.add(new LineSegment(np3,np4));
		}
		
		lines = newLines;
	}
	
	public String toString()
	{
		return "Minkowski Sausage";
	}
	
	public static void main(String[] args)
	{
		int iterations = 6;
		
		if(args.length > 0)
		{
			iterations = Integer.parseInt(args[0]);
		}
		
		FractalPanel fractal = new FractalPanel(new MinkowskiSausage());		
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
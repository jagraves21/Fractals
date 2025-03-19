import java.awt.Color;
import java.awt.Paint;
import java.awt.Point;
import java.awt.RadialGradientPaint;

import java.util.*;

import javax.swing.*;

public class Dragon extends SimpleFractal
{
	protected List<Integer> turns;
	
	public Dragon()
	{
		super();
		turns = new LinkedList<Integer>();
		createBase();
	}
	
	public int getSuggestedIterations()
	{
		return 19;
	}
	
	protected Paint getForeground()
	{
		MyPoint mid = newMinPoint.midPoint(newMaxPoint);
		mid.y += mid.y * (1.0/10.0);
		
		float[] dist = {0.3f, 1.0f};
		Color[] colors = {Color.BLUE, Color.RED};
		
		RadialGradientPaint paint = new RadialGradientPaint((float)mid.x, (float)mid.y, (float)(mid.distance(newMinPoint)*(6.0/10.0)), dist, colors);
		return paint;
	}
	
	public List<FractalShape> getFractal()
	{
		List<FractalShape> lines = new LinkedList<FractalShape>();
		
		MyPoint p1 = new MyPoint(0,HEIGHT/2);
		MyPoint p2 = new MyPoint(WIDTH,HEIGHT/2);
		MyPoint mid;
		
		double radius = p1.distance(p2);
		
		lines.add(new LineSegment(p1,p2));
		
		Iterator<Integer> iter = turns.iterator();
		while(iter.hasNext())
		{ 
			mid = translate(p2, p1, radius, r90 * iter.next().intValue());
			
			lines.add(new LineSegment(mid,p2));
			
			p1 = p2;
			p2 = mid;
		}
		
		return lines;
	}
	
	public void clearFractal()
	{
		createBase();
	}
	
	protected void createBase()
	{
		turns.clear();
	}
	
	public void next()
	{
		List<Integer> copy = new LinkedList<Integer>(turns);
		Collections.reverse(copy);
		
		turns.add(1);
		
		Iterator<Integer> iter = copy.iterator();
		while(iter.hasNext())
		{
			turns.add(-iter.next());
		}
	}
	
	public String toString()
	{
		return "Dragon Curve";
	}
	
	public static void main(String[] args)
	{
		int iterations = 19;
		
		if(args.length > 0)
		{
			iterations = Integer.parseInt(args[0]);
		}
		
		FractalPanel fractal = new FractalPanel(new Dragon());		
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
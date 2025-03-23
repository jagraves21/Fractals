import java.awt.Color;
import java.awt.Paint;
import java.awt.Point;
import java.awt.RadialGradientPaint;

import java.util.*;

import javax.swing.*;

public class DragonColor extends SimpleFractal
{
	protected List<Integer> turns;

	public DragonColor()
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
		
		if(mid.x != -1)
		{
			return Color.WHITE;
		}
		
		float[] dist = {0.3f, 1.0f};
		Color[] colors = {Color.BLUE, Color.RED};
		
		RadialGradientPaint paint = new RadialGradientPaint((float)mid.x, (float)mid.y, (float)(mid.distance(newMinPoint)*(6.0/10.0)), dist, colors);
		return paint;
	}
	
	public int lg(double value)
	{
		if(value == 0)
		{
			return 0;
		}
		return (int)(Math.log(value) / Math.log(2));
	}
	
	public List<FractalShape> getFractal()
	{
		double width = WIDTH * 4.5;
		double height = HEIGHT * 4.5;
		
		List<FractalShape> lines = new LinkedList<FractalShape>();
		
		MyPoint p1 = new MyPoint((width - (width/3)) - 2,height/3);
		MyPoint p2 = new MyPoint((width - (width/3)) + 2,height/3);
		MyPoint mid;
		
		double radius = p1.distance(p2);
		
		int colorCount = lg(Math.pow(2,getSuggestedIterations()));
		Color[] colors = new Color[colorCount];
		
		int curColor = Integer.MIN_VALUE + 100;
		int colorOff = (int)(((double)(Integer.MAX_VALUE - (long)curColor)) / colorCount);
		curColor = Integer.MAX_VALUE;
		for(int ii=0; ii < colors.length; ii++)
		{
			colors[ii] = new Color(curColor);
			curColor -= colorOff;
		}
		
		int count=0;
		lines.add(new LineSegment(p1,p2,colors[lg(count++)%colors.length]));
		
		Iterator<Integer> iter = turns.iterator();
		while(iter.hasNext())
		{ 
			mid = translate(p2, p1, radius, r90 * iter.next().intValue());
			
			lines.add(new LineSegment(mid,p2,colors[lg(count++)%colors.length]));
			
			p1 = p2;
			p2 = mid;
		}
		
		p1 = new MyPoint(0,0);
		p2 = new MyPoint(width,height);
		lines.add(new LineSegment(p1,p1));
		lines.add(new LineSegment(p2,p2));
		
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
		return "Dragon Curve 2";
	}
	
	public static void main(String[] args)
	{
		SimpleFractal.main(args);
	}
}

import java.awt.Color;
import java.awt.Paint;
import java.awt.Point;
import java.awt.GradientPaint;

import java.util.*;

import javax.swing.*;

public class TernaryTree extends SimpleFractal
{
	/*public static final int R_INIT = 255;
	public static final int G_INIT = 255;
	public static final int B_INIT = 255;
	
	public static final int R_DELTA = -50;
	public static final int G_DELTA = -20;
	public static final int B_DELTA = 20;*/
	
	public static final int R_INIT = 34;
	public static final int G_INIT = 139;
	public static final int B_INIT = 34;
	
	public static final int R_DELTA = 22;
	public static final int G_DELTA = -11;
	public static final int B_DELTA = 11;

	protected List<FractalShape> lines;
	protected List<LineSegment> nextLines;
	
	public TernaryTree()
	{
		super();
		lines = new LinkedList<FractalShape>();
		nextLines = new LinkedList<LineSegment>();
		createBase();
	}
	
	protected Paint getForeground()
	{
		return Color.WHITE;
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
		double dist;
		
		MyPoint center = new MyPoint(WIDTH/2, HEIGHT* (2.0/3.0));
		MyPoint p1 = new MyPoint(WIDTH/2, center.y - (center.y*(1.0/2.0)));
		
		dist = p1.distance(center);
		
		MyPoint p2 = center.rotate(p1, dist, r120);
		MyPoint p3 = center.rotate(p1, dist, -r120);
		
		lines.clear();
		nextLines.clear();
		
		LineSegment line = new LineSegment(center,p1,Color.RED);
		lines.add(line);
		nextLines.add(line);
		
		line = new LineSegment(center,p2,Color.BLUE);
		lines.add(line);
		nextLines.add(line);
		
		line = new LineSegment(center,p3,Color.GREEN);
		lines.add(line);
		nextLines.add(line);
		
		p1 = new MyPoint(0,0);
		p2 = new MyPoint(WIDTH,HEIGHT); 
		lines.add(p1);
		lines.add(p2);
	}
	
	protected Color getColor()
	{
		int count = (int)Math.sqrt(lines.size());
		
		int red = R_INIT + (count * R_DELTA);
		int green = G_INIT + (count * G_DELTA);
		int blue = B_INIT + (count * B_DELTA);
		
		if(red < 0)
		{
			red = 0;
		}
		else if(red > 255)
		{
			red = 255;
		}
		
		if(green < 0)
		{
			green = 0;
		}
		else if(green > 255)
		{
			green = 255;
		}
		
		if(blue < 0)
		{
			blue = 0;
		}
		else if(blue > 255)
		{
			blue = 255;
		}
		
		return new Color(red,green,blue);
	}
	
	public void next()
	{
		double dist;
		MyPoint center;
		MyPoint p1;
		MyPoint np1;
		MyPoint np2;
		MyPoint np3;
		LineSegment line;
		
		Paint paint;
		
		List<LineSegment> newLines = new LinkedList<LineSegment>();
		
		Iterator<LineSegment> iter = nextLines.iterator();
		while(iter.hasNext())
		{
			line = iter.next();
			paint = line.paint;
			center = line.p1;
			p1 = line.p2;
			
			dist = p1.distance(center)/2;
			
			np1 = p1.rotate(center, dist, r180);
			np2 = p1.rotate(np1, dist, r120);
			np3 = p1.rotate(np1, dist, -r120);
			
			line = new LineSegment(p1, np1, paint);
			lines.add(line);
			newLines.add(line);
			
			line = new LineSegment(p1, np2, paint);
			lines.add(line);
			newLines.add(line);
			
			line = new LineSegment(p1, np3, paint);
			lines.add(line);
			newLines.add(line);
		}
		
		nextLines = newLines;
	}
	
	public String toString()
	{
		return "Ternary Tree";
	}
	
	public static void main(String[] args)
	{
				SimpleFractal.main(args);
	}
}

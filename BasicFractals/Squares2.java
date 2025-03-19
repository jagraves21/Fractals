import java.awt.Color;
import java.awt.Paint;
import java.awt.Point;
import java.awt.RadialGradientPaint;

import java.util.*;

import javax.swing.*;

public class Squares2 extends SimpleFractal
{
	protected static final Color[] colors = {Color.RED, Color.YELLOW, Color.GREEN, Color.BLUE, new Color(138, 43, 226)};
	
	protected static final boolean FILL = false;
	protected static final double LENGTH = WIDTH/2.0;
	
	protected List<FractalShape> rectangles;
	protected List<UnorderedLineSegment> lines;
	protected Map<UnorderedLineSegment,UnorderedLineSegment> allLines;
	protected int iteration;
	
	public Squares2()
	{
		super();
		rectangles = new LinkedList<FractalShape>();
		lines = new LinkedList<UnorderedLineSegment>();
		allLines = new TreeMap<UnorderedLineSegment,UnorderedLineSegment>();
		iteration = 0;
		createBase();
	}
	
	public int getSuggestedIterations()
	{
		return 63;
	}
		
	protected Paint getForeground()
	{
		MyPoint mid = newMinPoint.midPoint(newMaxPoint);
		
		float[] dist = {0.1f, 0.5f};
		Color[] colors = {Color.BLUE, Color.RED};
		
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
		MyPoint p2 = new MyPoint(p1.x+LENGTH, p1.y);
		MyPoint p3 = new MyPoint(p1.x+LENGTH, p1.y+LENGTH);
		MyPoint p4 = new MyPoint(p1.x,p3.y);
		
		rectangles.clear();
		lines.clear();
		allLines.clear();
		iteration = 0;
		
		Rectangle rect = new Rectangle(p1,p2,p3,p4,colors[iteration%colors.length],FILL);
		rectangles.add(rect);
		
		UnorderedLineSegment l1 = new UnorderedLineSegment(p1,p2);
		UnorderedLineSegment l2 = new UnorderedLineSegment(p2,p3);
		UnorderedLineSegment l3 = new UnorderedLineSegment(p3,p4);
		UnorderedLineSegment l4 = new UnorderedLineSegment(p4,p1);
		
		lines.add(l1);
		lines.add(l2);
		lines.add(l3);
		lines.add(l4);
		
		allLines.put(l1, l1);
		allLines.put(l2, l2);
		allLines.put(l3, l3);
		allLines.put(l4, l4);
	}
	
	public void next()
	{
		iteration++;
		
		MyPoint p1;
		MyPoint p2;
		MyPoint np1;
		MyPoint np2;
		MyPoint np3;
		MyPoint np4;
		UnorderedLineSegment line;
		UnorderedLineSegment l1;
		UnorderedLineSegment l2;
		UnorderedLineSegment l3;
		UnorderedLineSegment l4;
		Rectangle rect;
		
		List<UnorderedLineSegment> newLines = new LinkedList<UnorderedLineSegment>();
		
		Iterator<UnorderedLineSegment> iter = lines.iterator();
		while(iter.hasNext())
		{
			line = iter.next();
			p1 = line.p1;
			p2 = line.p2;
			
			if(p1.y == p2.y)
			{
				if(p1.x < p2.x)
				{
					np1 = p1;
					np2 = new MyPoint(p1.x, p1.y-LENGTH);
					np3 = new MyPoint(p2.x, np2.y);
					np4 = p2;
				}
				else
				{
					np1 = p1;
					np2 = new MyPoint(p1.x, p1.y+LENGTH);
					np3 = new MyPoint(p2.x, np2.y);
					np4 = p2;
				}
			}
			else
			{
				if(p1.y < p2.y)
				{
					np1 = p1;
					np2 = new MyPoint(p1.x+LENGTH, p1.y);
					np3 = new MyPoint(np2.x, p2.y);
					np4 = p2;
				}
				else
				{
					np1 = p1;
					np2 = new MyPoint(p1.x-LENGTH, p1.y);
					np3 = new MyPoint(np2.x, p2.y);
					np4 = p2;
				}
			}
			
			l1 = new UnorderedLineSegment(np1,np2);
			l2 = new UnorderedLineSegment(np2,np3);
			l3 = new UnorderedLineSegment(np3,np4);
			l4 = new UnorderedLineSegment(np4,np1);
			
			if(!allLines.containsKey(l1) && !allLines.containsKey(l2) && !allLines.containsKey(l3))
			{
				allLines.put(l1,l1);
				allLines.put(l2,l2);
				allLines.put(l3,l3);
				
				newLines.add(l1);
				newLines.add(l2);
				newLines.add(l3);
				
				rectangles.add(0, new Rectangle(np1,np2,np3,np4,colors[iteration%colors.length],FILL));
			}
		}
		
		lines = newLines;
	}
	
	public String toString()
	{
		return "Squares 2";
	}
	
	public static void main(String[] args)
	{
		int iterations = 63;
		
		if(args.length > 0)
		{
			iterations = Integer.parseInt(args[0]);
		}
		
		FractalPanel fractal = new FractalPanel(new Squares2());		
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
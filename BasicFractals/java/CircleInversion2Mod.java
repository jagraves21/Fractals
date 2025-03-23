import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Point;
import java.awt.RadialGradientPaint;
import java.awt.RenderingHints;

import java.awt.image.BufferedImage;

import java.util.*;

import javax.swing.*;

public class CircleInversion2Mod extends SimpleFractal
{
	protected List<Circle> nextCircles;
	protected List<Circle> outerCircles;
	protected List<FractalShape> circles;
	
	public CircleInversion2Mod()
	{
		super();
		
		nextCircles = new LinkedList<Circle>();
		outerCircles = new LinkedList<Circle>();
		circles = new LinkedList<FractalShape>();
		createBase();
	}
	
	public int getSuggestedIterations()
	{
		return 4;
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
	
	protected void plotShapes(List<FractalShape> shapes, int width, int height, Graphics2D g)
	{
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		super.plotShapes(shapes, width, height, g);
	}
	
	public List<FractalShape> getFractal()
	{
		return circles;
	}
	
	public void clearFractal()
	{
		createBase();
	}
	
	protected void createBase()
	{
		circles.clear();
		nextCircles.clear();
		outerCircles.clear();
		
		Circle c = new Bubble(new MyPoint(WIDTH/2, HEIGHT/2), WIDTH/2, Color.RED);
		Circle c1 = new Bubble(new MyPoint(c.c.x, c.c.y - WIDTH - 10), WIDTH/2, Color.YELLOW);
		Circle c2 = new Bubble(translate(c.c, c1.c, c.c.distance(c1.c), 1*r60), WIDTH/2, new Color(239, 0, 222));
		Circle c3 = new Bubble(translate(c.c, c1.c, c.c.distance(c1.c), 2*r60), WIDTH/2, new Color(115, 0, 247));
		Circle c4 = new Bubble(translate(c.c, c1.c, c.c.distance(c1.c), 3*r60), WIDTH/2, new Color(0, 132, 255));
		Circle c5 = new Bubble(translate(c.c, c1.c, c.c.distance(c1.c), 4*r60), WIDTH/2, new Color(0, 239, 140));// new Color(0, 239, 140));
		Circle c6 = new Bubble(translate(c.c, c1.c, c.c.distance(c1.c), 5*r60), WIDTH/2, new Color(0, 231, 0));
		
		circles.add(c);
		circles.add(c1);
		circles.add(c2);
		circles.add(c3);
		circles.add(c4);
		circles.add(c5);
		circles.add(c6);
		
		outerCircles.add(c);
		outerCircles.add(c1);
		outerCircles.add(c2);
		outerCircles.add(c3);
		outerCircles.add(c4);
		outerCircles.add(c5);
		outerCircles.add(c6);
		
		nextCircles.add(c);
		nextCircles.add(c1);
		nextCircles.add(c2);
		nextCircles.add(c3);
		nextCircles.add(c4);
		nextCircles.add(c5);
		nextCircles.add(c6);
	}
	
	public void next()
	{
		double c1x, c2x, c3x;
		double c1y, c2y, c3y;
		double c1r, c2r, c3r;
		double dist;
		
		Circle c1;
		Circle c2;
		Circle c3;
		
		List<Circle> newCircles = new LinkedList<Circle>();
		
		Iterator<Circle> nextIter;
		Iterator<Circle> outerIter = outerCircles.iterator();
		while(outerIter.hasNext())
		{
			c2 = outerIter.next();
			
			nextIter = nextCircles.iterator();
			while(nextIter.hasNext())
			{
				c1 = nextIter.next();
				
				if(c1 != c2 && !c1.inside(c2))
				{
					c1x = c1.c.x;
					c1y = c1.c.y;
					c2x = c2.c.x;
					c2y = c2.c.y;
					dist = c1.c.distance(c2.c);
					c1r = c1.radius();
					c2r = c2.radius();
					
					c3x = (c1x - c2x)/dist*(dist * c2r*c2r / (dist * dist - c1r * c1r)) + c2x;
					c3y = (c1y - c2y)/dist*(dist * c2r*c2r / (dist * dist - c1r * c1r)) + c2y;
					c3r = Math.abs(c2r*c2r * c1r / (dist*dist - c1r*c1r));
					
					c3 = new Bubble(new MyPoint(c3x,c3y), c3r, c1.paint);
					if(c3.radius > 10)
					{
						circles.add(c3);
						newCircles.add(c3);
					}
				}
			}
		}
		//outerCircles = newCircles;
		nextCircles = newCircles;
	}
	
	java.util.Random random = null;
	protected Color getColor(double radius)
	{
		double percent = 1-(radius / (WIDTH * .30));
		int red = (int)Math.round(255 * percent);
		int green = (int)Math.round(255 * percent);
		int blue = (int)Math.round(255 * percent);
		
		if(random == null)
		{
			long seed = System.currentTimeMillis();
			seed = 1355959557249L;
			System.out.println("color " + seed + "L");
			random = new Random(seed);
		}
		
		
		
		red = (int)Math.round(255 * random.nextDouble());
		green = (int)Math.round(255 * random.nextDouble());
		blue = (int)Math.round(255 * random.nextDouble());
		
		return new Color(red, green, blue);
	}
	
	public String toString()
	{
		return "Circle Inversion 2 Mod";
	}
	
	public static void main(String[] args)
	{
		SimpleFractal.main(args);
	}
}

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Point;
import java.awt.RadialGradientPaint;

import java.util.*;

import javax.swing.*;

public class CircleLimitSetNew extends SimpleFractal
{
	protected List<Circle> nextCircles;
	protected List<Circle> outerCircles;
	protected List<FractalShape> circles;
	
	double MY_DIST = 50.5548469825328;
	
	public CircleLimitSetNew()
	{
		super();
		
		nextCircles = new LinkedList<Circle>();
		outerCircles = new LinkedList<Circle>();
		circles = new LinkedList<FractalShape>();
		createBase();
	}
	
	public int getSuggestedIterations()
	{
		return 74;
	}
	
	protected void findMinMax(List<FractalShape> shapes)
	{
		minPoint.x = -200;//-1654;
		minPoint.y = -200;
		
		maxPoint.x = 1200;//2654;
		maxPoint.y = 1200;
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
		
		Circle c = new Circle(new MyPoint(WIDTH/2, HEIGHT/2), MY_DIST, Color.WHITE);
		//Circle c = new Circle(new MyPoint(WIDTH/2, HEIGHT/2), WIDTH/2, Color.RED);
		Circle c1 = new Circle(new MyPoint(c.c.x, c.c.y - WIDTH - 10), WIDTH, new Color(255,0,0));
		Circle c2 = new Circle(translate(c.c, c1.c, c.c.distance(c1.c), 1*(2*Math.PI/7)), c1.radius, new Color(255,127,0));
		c1.radius = c1.c.distance(c2.c);
		c2.radius = c1.radius;
		Circle c3 = new Circle(translate(c.c, c1.c, c.c.distance(c1.c), 2*(2*Math.PI/7)), c1.radius, new Color(255, 255, 0));
		Circle c4 = new Circle(translate(c.c, c1.c, c.c.distance(c1.c), 3*(2*Math.PI/7)), c1.radius, new Color(0, 255, 0));
		Circle c5 = new Circle(translate(c.c, c1.c, c.c.distance(c1.c), 4*(2*Math.PI/7)), c1.radius, new Color(0, 0, 255));// new Color(0, 239, 140));
		Circle c6 = new Circle(translate(c.c, c1.c, c.c.distance(c1.c), 5*(2*Math.PI/7)), c1.radius, new Color(75, 0, 130));
		Circle c7 = new Circle(translate(c.c, c1.c, c.c.distance(c1.c), 6*(2*Math.PI/7)), c1.radius, new Color(143, 0, 255));
		
		//c.radius = c.c.distance(c1.c) - c1.radius;
		
		/*c.paint = Color.WHITE;
		c1.paint = Color.WHITE;
		c2.paint = Color.WHITE;
		c3.paint = Color.WHITE;
		c4.paint = Color.WHITE;
		c5.paint = Color.WHITE;
		c6.paint = Color.WHITE;
		c7.paint = Color.WHITE;*/
		
		c.c.paint = c.paint;
		c1.c.paint = c1.paint;
		c2.c.paint = c2.paint;
		c3.c.paint = c3.paint;
		c4.c.paint = c4.paint;
		c5.c.paint = c5.paint;
		c6.c.paint = c6.paint;
		c7.c.paint = c7.paint;
		
		/*circles.add(c);
		circles.add(c1);
		circles.add(c2);
		circles.add(c3);
		circles.add(c4);
		circles.add(c5);
		circles.add(c6);
		circles.add(c7);*/
		
		outerCircles.add(c);
		outerCircles.add(c1);
		outerCircles.add(c2);
		outerCircles.add(c3);
		outerCircles.add(c4);
		outerCircles.add(c5);
		outerCircles.add(c6);
		outerCircles.add(c7);
		
		nextCircles.add(c);
		nextCircles.add(c1);
		nextCircles.add(c2);
		nextCircles.add(c3);
		nextCircles.add(c4);
		nextCircles.add(c5);
		nextCircles.add(c6);
		nextCircles.add(c7);
		
		myNext();
		myNext();
		myNext();
		myNext();
		myNext();
	}
	
	public void myNext()
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
					c1r = c1.radius;
					c2r = c2.radius;
					
					c3x = (c1x - c2x)/dist*(dist * c2r*c2r / (dist * dist - c1r * c1r)) + c2x;
					c3y = (c1y - c2y)/dist*(dist * c2r*c2r / (dist * dist - c1r * c1r)) + c2y;
					c3r = Math.abs(c2r*c2r * c1r / (dist*dist - c1r*c1r));
					
					c3 = new Circle(new MyPoint(c3x,c3y,c2.paint), c3r, c1.paint);
					
					double error = 1000;
					if(c1.c.distance(c3.c) < error || c2.c.distance(c3.c) < error)
					{
						//circles.add(c3);
						circles.add(c3.c);
						newCircles.add(c3);
					}
				}
			}
		}
		nextCircles = newCircles;
	}
	
	public void next() {
		MY_DIST += 10;
		createBase();
	}
		
	public String toString()
	{
		return "Circle Limit Set New";
	}
	
	public static void main(String[] args)
	{
		int iterations = 6;
		
		if(args.length > 0)
		{
			iterations = Integer.parseInt(args[0]);
		}
		
		FractalPanel fractal = new FractalPanel(new CircleLimitSetNew());	
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
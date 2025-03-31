import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Point;
import java.awt.RadialGradientPaint;

import java.util.*;

import javax.swing.*;

public class CircleLimitSet3 extends SimpleFractal
{
	protected Circle center;
	protected Circle center1;
	protected Circle center2;
	protected Circle center3;
	protected Circle center4;
	protected List<Circle> nextCircles;
	protected List<Circle> outerCircles;
	protected List<FractalShape> circles;
	
	public CircleLimitSet3()
	{
		super();
		
		nextCircles = new LinkedList<Circle>();
		outerCircles = new LinkedList<Circle>();
		circles = new LinkedList<FractalShape>();
		createBase();
	}
	
	public int getSuggestedIterations()
	{
		return 36;
	}
	
	public long getSuggestedDelay() {
		return 100;
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
		center = new Circle(new MyPoint(WIDTH/2, HEIGHT/2), (WIDTH > HEIGHT ? HEIGHT/2 : WIDTH/2), Color.RED);
		center.c.paint = center.paint;
		
		double diff = center.radius()/2;
		center1 = new Circle(new MyPoint(center.c.x, center.c.y-diff), center.radius()-diff, center.paint);
		center1.c = translate(center.c, center1.c, center.c.distance(center1.c), (2 * Math.PI)/getSuggestedIterations());
		
		center2 = new Circle(new MyPoint(center.c.x+diff, center.c.y), center.radius()-diff, center.paint);
		center2.c = translate(center.c, center2.c, center.c.distance(center2.c), (2 * Math.PI)/getSuggestedIterations());
		
		center3 = new Circle(new MyPoint(center.c.x, center.c.y+diff), center.radius()-diff, center.paint);
		center3.c = translate(center.c, center3.c, center.c.distance(center3.c), (2 * Math.PI)/getSuggestedIterations());
		
		center4 = new Circle(new MyPoint(center.c.x-diff, center.c.y), center.radius()-diff, center.paint);
		center4.c = translate(center.c, center4.c, center.c.distance(center4.c), (2 * Math.PI)/getSuggestedIterations());
		
		next();
	}
	
	public void next()
	{
		center1.c = translate(center.c, center1.c, center.c.distance(center1.c), (-2 * Math.PI)/getSuggestedIterations());
		center2.c = translate(center.c, center2.c, center.c.distance(center2.c), (-2 * Math.PI)/getSuggestedIterations());
		center3.c = translate(center.c, center3.c, center.c.distance(center3.c), (-2 * Math.PI)/getSuggestedIterations());
		center4.c = translate(center.c, center4.c, center.c.distance(center4.c), (-2 * Math.PI)/getSuggestedIterations());
		
		circles.clear();
		nextCircles.clear();
		outerCircles.clear();
		
		Circle c1 = new Circle(new MyPoint(center.c.x, center.c.y - WIDTH - 10), center.radius(), Color.YELLOW);
		Circle c2 = new Circle(translate(center.c, c1.c, center.c.distance(c1.c), 1*r60), center.radius(), new Color(239, 0, 222));
		Circle c3 = new Circle(translate(center.c, c1.c, center.c.distance(c1.c), 2*r60), center.radius(), new Color(115, 0, 247));
		Circle c4 = new Circle(translate(center.c, c1.c, center.c.distance(c1.c), 3*r60), center.radius(), new Color(0, 132, 255));
		Circle c5 = new Circle(translate(center.c, c1.c, center.c.distance(c1.c), 4*r60), center.radius(), new Color(0, 239, 140));// new Color(0, 239, 140));
		Circle c6 = new Circle(translate(center.c, c1.c, center.c.distance(c1.c), 5*r60), center.radius(), new Color(0, 231, 0));
		
		center1.c.paint = center1.paint;
		center2.c.paint = center2.paint;
		center3.c.paint = center3.paint;
		center4.c.paint = center4.paint;
		c1.c.paint = c1.paint;
		c2.c.paint = c2.paint;
		c3.c.paint = c3.paint;
		c4.c.paint = c4.paint;
		c5.c.paint = c5.paint;
		c6.c.paint = c6.paint;
		
		/*circles.add(center);
		circles.add(center1);
		circles.add(center2);
		circles.add(center3);
		circles.add(center4);
		circles.add(c1);
		circles.add(c2);
		circles.add(c3);
		circles.add(c4);
		circles.add(c5);
		circles.add(c6);*/
		
		outerCircles.add(center1);
		outerCircles.add(center2);
		outerCircles.add(center3);
		outerCircles.add(center4);
		outerCircles.add(c1);
		outerCircles.add(c2);
		outerCircles.add(c3);
		outerCircles.add(c4);
		outerCircles.add(c5);
		outerCircles.add(c6);
		
		nextCircles.add(center1);
		nextCircles.add(center2);
		nextCircles.add(center3);
		nextCircles.add(center4);
		nextCircles.add(c1);
		nextCircles.add(c2);
		nextCircles.add(c3);
		nextCircles.add(c4);
		nextCircles.add(c5);
		nextCircles.add(c6);
		
		for(int ii=0; ii < 5; ii++)
		{
			MyNext();
		}
	}
	
	protected void MyNext()
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
					
					c3 = new Circle(new MyPoint(c3x,c3y,c2.paint), c3r, c1.paint);
					circles.add(c3.c);
					newCircles.add(c3);
				}
			}
		}
		nextCircles = newCircles;
	}
		
	public String toString()
	{
		return "Circle Limit Set";
	}
	
	public static void main(String[] args)
	{
		SimpleFractal.main(args);
	}
}

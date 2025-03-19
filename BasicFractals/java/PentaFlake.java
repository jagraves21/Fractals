import java.awt.Color;
import java.awt.Paint;
import java.awt.Point;
import java.awt.RadialGradientPaint;

import java.util.*;

import javax.swing.*;

public class PentaFlake extends SimpleFractal
{
	public static final double r108 = (108 * Math.PI / 180.0);
	public static final double s108 = Math.sin(r108);
	public static final double c108 = Math.cos(r108);
	public static final double t108 = Math.tan(r108);
	
	protected List<FractalShape> pentagons;
	protected int iteration;
	
	public PentaFlake()
	{
		pentagons = new LinkedList<FractalShape>();
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
		MyPoint tmp = new MyPoint(newMinPoint.x, mid.y);
		
		float[] dist = {0.25f, 1.0f};
		Color[] colors = {Color.WHITE, new Color(255,20,147)};
		
		RadialGradientPaint paint = new RadialGradientPaint((float)mid.x, (float)mid.y, (float)mid.distance(tmp), dist, colors);
		return paint;
	}
	
	public List<FractalShape> getFractal()
	{
		return pentagons;
	}
	
	public void clearFractal()
	{
		createBase();
	}
	
	protected void createBase()
	{
		double radius;
		
		MyPoint p4 = new MyPoint(0, HEIGHT);
		MyPoint p3 = new MyPoint(WIDTH, p4.y);
		radius = p4.distance(p3);
		MyPoint p2 = translate(p3, p4, radius, -r108);
		MyPoint p1 = translate(p2, p3, radius, -r108);
		MyPoint p5 = translate(p1, p2, radius, -r108);
		
		Pentagon pent = new Pentagon(p1,p2,p3,p4,p5);
		
		pentagons.clear();
		pentagons.add(pent);
		
		iteration = 1;
	}
	
	public void next()
	{
		double radius;
		MyPoint p1;
		MyPoint p2;
		MyPoint p3;
		MyPoint p4;
		MyPoint p5;
		MyPoint np1;
		MyPoint np2;
		MyPoint np3;
		MyPoint np4;
		MyPoint np5;
		MyPoint np6;
		MyPoint np7;
		MyPoint np8;
		MyPoint np9;
		MyPoint np10;
		LineSegment l1 = new LineSegment(null,null);
		LineSegment l2 = new LineSegment(null,null);
		Pentagon pent;
		
		List<FractalShape> newPentagons = new LinkedList<FractalShape>();
		
		Iterator<FractalShape> iter = pentagons.iterator();
		while(iter.hasNext())
		{
			pent = (Pentagon)iter.next();
			
			p1 = pent.p1;
			p2 = pent.p2;
			p3 = pent.p3;
			p4 = pent.p4;
			p5 = pent.p5;
			
			l1.p1 = p4;
			l1.p2 = p2;
			l2.p1 = p3;
			l2.p2 = p5;
			np1 = l1.intersect(l2);
			
			l2.p1 = p3;
			l2.p2 = p1;
			np5 = l1.intersect(l2);
			
			l1.p1 = p2;
			l1.p2 = p5;
			np4 = l1.intersect(l2);
			
			l2.p1 = p1;
			l2.p2 = p4;
			np3 = l1.intersect(l2);
			
			l1.p1 = p3;
			l1.p2 = p5;
			np2 = l1.intersect(l2);
			
			pent = new Pentagon(np1, np2, np3, np4, np5);
			newPentagons.add(pent);
			
			radius = np1.distance(np2);
			
			np6 = p1;
			np7 = translate(p1,p2,radius,0);
			np8 = np4;
			np9 = np3;
			np10 = translate(p1,p5,radius,0);
			pent = new Pentagon(np6, np7, np8, np9, np10);
			newPentagons.add(pent);
			
			np6 = translate(p2,p1,radius,0);
			np7 = p2;
			np8 = translate(p2,p3,radius,0);
			np9 = np5;
			np10 = np4;
			pent = new Pentagon(np6, np7, np8, np9, np10);
			newPentagons.add(pent);
			
			np6 = np5;
			np7 = translate(p3,p2,radius,0);
			np8 = p3;
			np9 = translate(p3,p4,radius,0);
			np10 = np1;
			pent = new Pentagon(np6, np7, np8, np9, np10);
			newPentagons.add(pent);
			
			np6 = np2;
			np7 = np1;
			np8 = translate(p4,p3,radius,0);
			np9 = p4;
			np10 = translate(p4,p5,radius,0);
			pent = new Pentagon(np6, np7, np8, np9, np10);
			newPentagons.add(pent);
			
			np6 = translate(p5,p1,radius,0);
			np7 = np3;
			np8 = np2;
			np9 = translate(p5,p4,radius,0);
			np10 = p5;
			pent = new Pentagon(np6, np7, np8, np9, np10);
			newPentagons.add(pent);
		}
		
		pentagons = newPentagons;
	}
	
	public String toString()
	{
		return "Penta Flake";
	}
	
	public static void main(String[] args)
	{
		int iterations = 5;
		
		if(args.length > 0)
		{
			iterations = Integer.parseInt(args[0]);
		}
		
		FractalPanel fractal = new FractalPanel(new PentaFlake());		
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
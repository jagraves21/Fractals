import java.awt.Color;
import java.awt.Paint;
import java.awt.Point;
import java.awt.RadialGradientPaint;

import java.util.*;

import javax.swing.*;

public class PentaStar extends SimpleFractal
{
	public static final double r108 = (108 * Math.PI / 180.0);
	public static final double s108 = Math.sin(r108);
	public static final double c108 = Math.cos(r108);
	public static final double t108 = Math.tan(r108);
	
	protected List<FractalShape> pentagons;
	protected List<Pentagon> nextPentagons;
	
	public PentaStar()
	{
		pentagons = new LinkedList<FractalShape>();
		nextPentagons = new LinkedList<Pentagon>();
		
		createBase();
	}
	
	public int getSuggestedIterations()
	{
		return 4;
	}
	
	protected Paint getForeground()
	{
		return Color.YELLOW;
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
		nextPentagons.clear();
		nextPentagons.add(pent);
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
		Pentagon pent;
		
		List<Pentagon> newPentagons = new LinkedList<Pentagon>();
		
		Iterator<Pentagon> iter = nextPentagons.iterator();
		while(iter.hasNext())
		{
			pent = iter.next();
			
			p1 = pent.p1;
			p2 = pent.p2;
			p3 = pent.p3;
			p4 = pent.p4;
			p5 = pent.p5;
			
			np1 = p1.oneThirdsPoint(p2);
			np2 = p1.twoThirdsPoint(p2);
			radius = np1.distance(np2);
			np3 = translate(np2, np1, radius, r108);
			np4 = translate(np3, np2, radius, r108);
			np5 = translate(np4, np3, radius, r108);
			pent = new Pentagon(np1,np2,np3,np4,np5);
			pentagons.add(pent);
			newPentagons.add(pent);
			
			np2 = p2.oneThirdsPoint(p3);
			np3 = p2.twoThirdsPoint(p3);
			radius = np2.distance(np3);
			np4 = translate(np3, np2, radius, r108);
			np5 = translate(np4, np3, radius, r108);
			np1 = translate(np5, np4, radius, r108);
			pent = new Pentagon(np1,np2,np3,np4,np5);
			pentagons.add(pent);
			newPentagons.add(pent);
			
			np3 = p3.oneThirdsPoint(p4);
			np4 = p3.twoThirdsPoint(p4);
			radius = np3.distance(np4);
			np5 = translate(np4, np3, radius, r108);
			np1 = translate(np5, np4, radius, r108);
			np2 = translate(np1, np5, radius, r108);
			pent = new Pentagon(np1,np2,np3,np4,np5);
			pentagons.add(pent);
			newPentagons.add(pent);
			
			np4 = p4.oneThirdsPoint(p5);
			np5 = p4.twoThirdsPoint(p5);
			radius = np4.distance(np5);
			np1 = translate(np5, np4, radius, r108);
			np2 = translate(np1, np5, radius, r108);
			np3 = translate(np2, np1, radius, r108);
			pent = new Pentagon(np1,np2,np3,np4,np5);
			pentagons.add(pent);
			newPentagons.add(pent);
			
			np5 = p5.oneThirdsPoint(p1);
			np1 = p5.twoThirdsPoint(p1);
			radius = np5.distance(np1);
			np2 = translate(np1, np5, radius, r108);
			np3 = translate(np2, np1, radius, r108);
			np4 = translate(np3, np2, radius, r108);
			pent = new Pentagon(np1,np2,np3,np4,np5);
			pentagons.add(pent);
			newPentagons.add(pent);
		}
		
		nextPentagons = newPentagons;
	}
	
	public String toString()
	{
		return "Penta Star";
	}
	
	public static void main(String[] args)
	{
		SimpleFractal.main(args);
	}
}

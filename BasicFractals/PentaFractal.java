import java.awt.Color;
import java.awt.Paint;
import java.awt.Point;
import java.awt.RadialGradientPaint;

import java.util.*;

import javax.swing.*;

public class PentaFractal extends SimpleFractal
{
	public static final double r108 = (108 * Math.PI / 180.0);
	public static final double s108 = Math.sin(r108);
	public static final double c108 = Math.cos(r108);
	public static final double t108 = Math.tan(r108);
	
	protected static final Color[] colors = {Color.RED, Color.YELLOW, Color.GREEN, Color.BLUE, Color.MAGENTA};
	
	protected List<FractalShape> pentagons;
	protected List<Pentagon> nextPentagons;
	protected int iteration;
	
	public PentaFractal()
	{
		pentagons = new LinkedList<FractalShape>();
		nextPentagons = new LinkedList<Pentagon>();
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
		
		float[] dist = {0.0f, 0.75f};
		Color[] colors = {new Color(31,255,31), new Color(153,255,153)};
		
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
		
		MyPoint p4 = new MyPoint(WIDTH * (6.0/17.0), HEIGHT * (8.0/11.0));
		MyPoint p3 = new MyPoint(WIDTH - p4.x, p4.y);
		radius = p4.distance(p3);
		MyPoint p2 = translate(p3, p4, radius, -r108);
		MyPoint p1 = translate(p2, p3, radius, -r108);
		MyPoint p5 = translate(p1, p2, radius, -r108);
		
		Pentagon pent = new Pentagon(p1,p2,p3,p4,p5,colors[0],true);
		pentagons.clear();
		pentagons.add(pent);
		nextPentagons.clear();
		nextPentagons.add(pent);
		
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
		Pentagon pent;
		
		Color color = colors[iteration++ % colors.length];
		
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
			
			radius = p1.distance(p2) * (5.0/14.0);
			
			if(pentagons.size() == 1)
			{
				np1 = p1;
				np2 = translate(p1, p2, radius, r180);
				np3 = translate(np2, np1, radius, r108);
				np4 = translate(np3, np2, radius, r108);
				np5 = translate(np4, np3, radius, r108);
				pent = new Pentagon(np1,np2,np3,np4,np5,color,true);
				pentagons.add(pent);
				newPentagons.add(pent);
			}
			
			np1 = p2;
			np2 = translate(p2, p3, radius, r180);
			np3 = translate(np2, np1, radius, r108);
			np4 = translate(np3, np2, radius, r108);
			np5 = translate(np4, np3, radius, r108);
			pent = new Pentagon(np1,np2,np3,np4,np5,color,true);
			pentagons.add(pent);
			newPentagons.add(pent);
			
			np1 = p3;
			np2 = translate(p3, p4, radius, r180);
			np3 = translate(np2, np1, radius, r108);
			np4 = translate(np3, np2, radius, r108);
			np5 = translate(np4, np3, radius, r108);
			pent = new Pentagon(np1,np2,np3,np4,np5,color,true);
			pentagons.add(pent);
			newPentagons.add(pent);
			
			np1 = p4;
			np2 = translate(p4, p5, radius, r180);
			np3 = translate(np2, np1, radius, r108);
			np4 = translate(np3, np2, radius, r108);
			np5 = translate(np4, np3, radius, r108);
			pent = new Pentagon(np1,np2,np3,np4,np5,color,true);
			pentagons.add(pent);
			newPentagons.add(pent);
			
			np1 = p5;
			np2 = translate(p5, p1, radius, r180);
			np3 = translate(np2, np1, radius, r108);
			np4 = translate(np3, np2, radius, r108);
			np5 = translate(np4, np3, radius, r108);
			pent = new Pentagon(np1,np2,np3,np4,np5,color,true);
			pentagons.add(pent);
			newPentagons.add(pent);
		}
		
		nextPentagons = newPentagons;
	}
	
	public String toString()
	{
		return "Pentagon Fractal";
	}
	
	public static void main(String[] args)
	{
		int iterations = 5;
		
		if(args.length > 0)
		{
			iterations = Integer.parseInt(args[0]);
		}
		
		FractalPanel fractal = new FractalPanel(new PentaFractal());		
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
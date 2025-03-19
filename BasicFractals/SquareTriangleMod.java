import java.awt.Color;
import java.awt.Paint;
import java.awt.Point;
import java.awt.RadialGradientPaint;

import java.util.*;

import javax.swing.*;

public class SquareTriangleMod extends SimpleFractal
{
	protected static final Color[] colors = {Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN, Color.BLUE, new Color(138, 43, 226)};
	
	protected List<FractalShape> squares;
	
	public SquareTriangleMod()
	{
		super();
		squares = new LinkedList<FractalShape>();
		createBase();
	}
	
	public int getSuggestedIterations()
	{
		return 9;
	}
		
	protected Paint getForeground()
	{
		MyPoint mid = newMinPoint.midPoint(newMaxPoint);
		
		float[] dist = {0.0f, 0.2f, 0.4f, 0.6f, 0.8f, 1.0f};
		Color[] colors = {Color.RED, Color.BLUE, Color.GREEN, Color.RED, Color.BLUE, Color.GREEN};
		
		RadialGradientPaint paint = new RadialGradientPaint((float)mid.x, (float)mid.y, (float)mid.distance(newMaxPoint), dist, colors);
		return paint;
	}
	
	public List<FractalShape> getFractal()
	{
		return squares;
	}
	
	public void clearFractal()
	{
		createBase();
	}
	
	protected void createBase()
	{
		MyPoint p1 = new MyPoint(WIDTH/4, HEIGHT/4);
		MyPoint p2 = new MyPoint(WIDTH-p1.x, p1.y);
		MyPoint p3 = new MyPoint(WIDTH-p1.x, HEIGHT-p1.y);
		MyPoint p4 = new MyPoint(p1.x,p3.y);
		
		squares.clear();
		
		Rectangle rect = new Rectangle(p1,p2,p3,p4,null,false);
		squares.add(rect);
	}
	
	public void next()
	{
		double dist;
		MyPoint p1;
		MyPoint p2;
		MyPoint p3;
		MyPoint p4;
		MyPoint np1;
		MyPoint np2;
		MyPoint np3;
		MyPoint np4;
		Rectangle rect;
		
		List<FractalShape> newSquares = new LinkedList<FractalShape>();
		
		Iterator<FractalShape> iter = squares.iterator();
		while(iter.hasNext())
		{
			rect = (Rectangle)iter.next();
			p1 = rect.p1;
			p2 = rect.p2;
			p3 = rect.p3;
			p4 = rect.p4;
			
			dist = p1.distance(p2)/2;
			
			np1 = new MyPoint(p1.x + dist/2, p1.y);
			np2 = new MyPoint(np1.x + dist, np1.y);
			np3 = new MyPoint(np2.x, np2.y + dist);
			np4 = new MyPoint(np1.x, np3.y);
			rect = new Rectangle(np1,np2,np3,np4,null,false);
			newSquares.add(rect);
			
			np1 = new MyPoint(p1.x, p1.y+dist);
			np2 = new MyPoint(np1.x + dist, np1.y);
			np3 = new MyPoint(np2.x, np2.y + dist);
			np4 = new MyPoint(np1.x, np3.y);
			rect = new Rectangle(np1,np2,np3,np4,null,false);
			newSquares.add(rect);
			
			np1 = new MyPoint(p1.x + dist, p1.y + dist);
			np2 = new MyPoint(np1.x + dist, np1.y);
			np3 = new MyPoint(np2.x, np2.y + dist);
			np4 = new MyPoint(np1.x, np3.y);
			rect = new Rectangle(np1,np2,np3,np4,null,false);
			newSquares.add(rect);
		}
		
		squares = newSquares;
	}
	
	public String toString()
	{
		return "Square Triangle Mod";
	}
	
	public static void main(String[] args)
	{
		int iterations = 9;
		
		if(args.length > 0)
		{
			iterations = Integer.parseInt(args[0]);
		}
		
		FractalPanel fractal = new FractalPanel(new SquareTriangleMod());		
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
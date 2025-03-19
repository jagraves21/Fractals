import java.awt.Color;
import java.awt.Paint;
import java.awt.Point;
import java.awt.RadialGradientPaint;

import java.util.*;

import javax.swing.*;

public class SierpinskiSquare extends LSystem
{
	public SierpinskiSquare()
	{
		super();
	}
	
	public int getSuggestedIterations()
	{
		return 5;
	}
		
	protected Paint getForeground()
	{
		MyPoint mid = newMinPoint.midPoint(newMaxPoint);
		MyPoint tmp = new MyPoint(newMinPoint.x, mid.y);
		
		float[] dist = {0.30f, 1.0f};
		Color[] colors = {Color.RED, Color.YELLOW};
		
		RadialGradientPaint paint = new RadialGradientPaint((float)mid.x, (float)mid.y, (float)mid.distance(tmp), dist, colors);
		return paint;
	}
	
	protected StringBuilder getAxiom()
	{
		return new StringBuilder("F+XF+F+XF");
	}
	
	public StringBuilder applyTransition(StringBuilder curve)
	{
		StringBuilder nextCurve = new StringBuilder();
		
		for(int ii=0; ii < curve.length(); ii++)
		{
			if(curve.charAt(ii) == 'X')
			{
				nextCurve.append("XF-F+F-XF+F+XF-F+F-X");
			}
			else
			{
				nextCurve.append(curve.charAt(ii));
			}
		}
		
		return nextCurve;
	}
	
	protected double getTurningAngle()
	{
		return r90;
	}
	
	public String toString()
	{
		return "Sierpinski Square";
	}
	
	public static void main(String[] args)
	{
		int iterations = 5;
		
		if(args.length > 0)
		{
			iterations = Integer.parseInt(args[0]);
		}
		
		FractalPanel fractal = new FractalPanel(new SierpinskiSquare());		
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
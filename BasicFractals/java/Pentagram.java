import java.awt.Color;
import java.awt.Paint;
import java.awt.Point;
import java.awt.RadialGradientPaint;

import java.util.*;

import javax.swing.*;

public class Pentagram extends LSystem
{
	public static final double r36 = (36 * Math.PI / 180.0);

	public Pentagram()
	{
		super();
	}
	
	public int getSuggestedIterations()
	{
		return 8;
	}
		
	protected Paint getForeground()
	{
		MyPoint mid = newMinPoint.midPoint(newMaxPoint);
		mid.y = newMinPoint.y + ((newMaxPoint.y - newMinPoint.y) * (7.0/13.0));
		
		float[] dist = {0.25f, 0.40f, 0.55f, 0.70f};
		Color[] colors = {Color.ORANGE, Color.RED, Color.BLUE, Color.GREEN};
		
		RadialGradientPaint paint = new RadialGradientPaint((float)mid.x, (float)mid.y, (float)mid.distance(newMaxPoint), dist, colors);
		return paint;
	}
	
	protected StringBuilder getAxiom()
	{
		return new StringBuilder("++F--F--F++--++F--F--F++--++F--F--F++--++F--F--F++--++F--F--F++");
	}
	
	public StringBuilder applyTransition(StringBuilder curve)
	{
		StringBuilder nextCurve = new StringBuilder();
		
		for(int ii=0; ii < curve.length(); ii++)
		{
			if(curve.charAt(ii) == 'F')
			{
				nextCurve.append("++F--F--F++");
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
		return r36;
	}
	
	public String toString()
	{
		return "Pentagram";
	}
	
	public static void main(String[] args)
	{
		int iterations = 8;
		
		if(args.length > 0)
		{
			iterations = Integer.parseInt(args[0]);
		}
		
		FractalPanel fractal = new FractalPanel(new Pentagram());		
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
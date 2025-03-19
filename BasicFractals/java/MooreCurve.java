import java.awt.Color;
import java.awt.Paint;
import java.awt.Point;
import java.awt.RadialGradientPaint;

import java.util.*;

import javax.swing.*;

public class MooreCurve extends LSystem
{
	public static final double r36 = (36 * Math.PI / 180.0);

	public MooreCurve()
	{
		super();
	}
	
	public int getSuggestedIterations()
	{
		return 6;
	}
		
	protected Paint getForeground()
	{
		MyPoint mid = newMinPoint.midPoint(newMaxPoint);
		mid.y = newMinPoint.y + ((newMaxPoint.y - newMinPoint.y) * (7.0/13.0));
		
		float[] dist = {0.0f, 0.33f, 0.66f, 1.0f};
		Color[] colors = {Color.ORANGE, Color.RED, Color.BLUE, Color.GREEN};
		
		RadialGradientPaint paint = new RadialGradientPaint((float)mid.x, (float)mid.y, (float)mid.distance(newMaxPoint), dist, colors);
		return paint;
	}
	
	protected StringBuilder getAxiom()
	{
		return new StringBuilder("LFL+F+LFL");
	}
	
	public StringBuilder applyTransition(StringBuilder curve)
	{
		StringBuilder nextCurve = new StringBuilder();
		
		for(int ii=0; ii < curve.length(); ii++)
		{
			if(curve.charAt(ii) == 'L')
			{
				nextCurve.append("-RF+LFL+FR-");
			}
			else if(curve.charAt(ii) == 'R')
			{
				nextCurve.append("+LF-RFR-FL+");
			}
			else
			{
				nextCurve.append(curve.charAt(ii));
			}
		}
		
		return nextCurve;
	}
	
	protected double getStartingAngle()
	{
		return r90;
	}
	
	protected double getTurningAngle()
	{
		return r90;
	}
	
	public String toString()
	{
		return "Moore's Curve";
	}
	
	public static void main(String[] args)
	{
		int iterations = 6;
		
		if(args.length > 0)
		{
			iterations = Integer.parseInt(args[0]);
		}
		
		FractalPanel fractal = new FractalPanel(new MooreCurve());		
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
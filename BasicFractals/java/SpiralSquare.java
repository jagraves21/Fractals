import java.awt.Color;
import java.awt.Paint;
import java.awt.Point;
import java.awt.RadialGradientPaint;

import java.util.*;

import javax.swing.*;

public class SpiralSquare extends LSystem
{
	public SpiralSquare()
	{
		super();
	}
	
	public int getSuggestedIterations()
	{
		return 10;
	}
		
	protected Paint getForeground()
	{
		float[] dist = {0.0f, 1.0f};
		Color[] colors = {Color.RED, Color.WHITE};
		
		RadialGradientPaint paint = new RadialGradientPaint((float)newMinPoint.x, (float)newMinPoint.y, (float)newMinPoint.distance(newMaxPoint), dist, colors);
		return paint;
	}
	
	protected StringBuilder getAxiom()
	{
		return new StringBuilder("F+F+F+F+fA");
	}
	
	public StringBuilder applyTransition(StringBuilder curve)
	{
		StringBuilder nextCurve = new StringBuilder();
		
		for(int ii=0; ii < curve.length(); ii++)
		{
			if(curve.charAt(ii) == 'A')
			{
				nextCurve.append(">)+(F+F+F+F+fA");
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
		return 0;
	}
	
	protected double getTurningAngle()
	{
		return -r90;
	}
	
	protected double getAngleIncrement()
	{
		return r45;
	}
	
	protected double getScaleFactor()
	{
		return 0.70710678118654746;  // 1 / sqrt(2)
	}
	
	public String toString()
	{
		return "SpiralSquare";
	}
	
	public static void main(String[] args)
	{
		int iterations = 10;
		
		if(args.length > 0)
		{
			iterations = Integer.parseInt(args[0]);
		}
		
		FractalPanel fractal = new FractalPanel(new SpiralSquare());		
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
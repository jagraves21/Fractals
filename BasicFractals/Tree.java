import java.awt.Color;
import java.awt.Paint;
import java.awt.Point;
import java.awt.RadialGradientPaint;

import java.util.*;

import javax.swing.*;

public class Tree extends LSystem
{
	public Tree()
	{
		super();
	}
	
	public int getSuggestedIterations()
	{
		return 10;
	}
		
	protected Paint getForeground()
	{
		MyPoint mid = newMinPoint.midPoint(newMaxPoint);
		
		float[] dist = {0.0f, 0.15f, 0.30f, 0.45f, 0.60f, 0.75f};
		Color[] colors = {Color.RED, Color.BLUE, Color.GREEN, Color.RED, Color.BLUE, Color.GREEN};
		
		RadialGradientPaint paint = new RadialGradientPaint((float)mid.x, (float)mid.y, (float)mid.distance(newMaxPoint), dist, colors);
		return paint;
	}
	
	protected StringBuilder getAxiom()
	{
		return new StringBuilder("FX");
	}
	
	public StringBuilder applyTransition(StringBuilder curve)
	{
		StringBuilder nextCurve = new StringBuilder();
		
		for(int ii=0; ii < curve.length(); ii++)
		{
			if(curve.charAt(ii) == 'X')
			{
				nextCurve.append(">[-FX]+FX");
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
		return r40;
	}
	
	protected double getScaleFactor()
	{
		return 3.0/5.0;
	}
	
	public String toString()
	{
		return "Tree";
	}
	
	public static void main(String[] args)
	{
		int iterations = 10;
		
		if(args.length > 0)
		{
			iterations = Integer.parseInt(args[0]);
		}
		
		FractalPanel fractal = new FractalPanel(new Tree());		
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

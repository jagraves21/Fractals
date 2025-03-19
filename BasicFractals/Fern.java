//http://en.wikipedia.org/wiki/File:Ifs-construction.png
import java.awt.Color;
import java.awt.Paint;
import java.awt.Point;
import java.awt.RadialGradientPaint;

import java.util.*;

import javax.swing.*;

public class Fern extends LSystem
{
	public Fern()
	{
		super();
	}
	
	public int getSuggestedIterations()
	{
		return 12;
	}
		
	protected Paint getForeground()
	{
		MyPoint mid = newMinPoint.midPoint(newMaxPoint);
		MyPoint tmp = new MyPoint(newMinPoint.x, mid.y);
		
		float[] dist = {0.0f, 1.0f};
		Color[] colors = {Color.WHITE, Color.BLUE};
		
		RadialGradientPaint paint = new RadialGradientPaint((float)mid.x, (float)mid.y, (float)mid.distance(tmp), dist, colors);
		return paint;
	}
	
	protected StringBuilder getAxiom()
	{
		return new StringBuilder("F-F-F-F-f-f(-f)|>F-F-F-F|f-<f>|A");
	}
	
	public StringBuilder applyTransition(StringBuilder curve)
	{
		StringBuilder nextCurve = new StringBuilder();
		
		for(int ii=0; ii < curve.length(); ii++)
		{
			if(curve.charAt(ii) == 'A')
			{
				nextCurve.append("F-F-F-F-f-f(-f)|>F-F-F-F|f-<f>|A");
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
		return 0.0;
	}
	
	protected double getTurningAngle()
	{
		return r90;
	}
	
	protected double getAngleIncrement()
	{
		return r45;
	}
	
	protected double getScaleFactor()
	{
		return 2.0;
	}
	
	public String toString()
	{
		return "Fern";
	}
	
	public static void main(String[] args)
	{
		int iterations = 7;
		
		if(args.length > 0)
		{
			iterations = Integer.parseInt(args[0]);
		}
		
		FractalPanel fractal = new FractalPanel(new Fern());		
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
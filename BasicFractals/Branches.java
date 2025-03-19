import java.awt.Color;
import java.awt.Paint;
import java.awt.Point;
import java.awt.RadialGradientPaint;

import java.util.*;

import javax.swing.*;

public class Branches extends LSystem
{
	private double turningAngle;
	
	public Branches()
	{
		super();
		
		turningAngle = 0;
	}
	
	public int getSuggestedIterations()
	{
		return 6;
	}
		
	protected Paint getForeground()
	{
		MyPoint mid = newMinPoint.midPoint(newMaxPoint);
		mid.y += mid.y * (1.0/10.0);
		
		float[] dist = {0.3f, 1.0f};
		Color[] colors = {Color.BLUE, Color.RED};
		
		RadialGradientPaint paint = new RadialGradientPaint((float)mid.x, (float)mid.y, (float)(mid.distance(newMinPoint)*(6.0/10.0)), dist, colors);
		return paint;
	}
	
	protected StringBuilder getAxiom()
	{
		StringBuilder str = new StringBuilder("FA");
		for(int ii=0; ii < 10; ii++)
		{
			str = applyTransition2(str);
		}
		
		return str;
	}
	
	public StringBuilder applyTransition2(StringBuilder curve)
	{
		StringBuilder nextCurve = new StringBuilder();
		
		for(int ii=0; ii < curve.length(); ii++)
		{
			if(curve.charAt(ii) == 'F')
			{
				nextCurve.append("F");
			}
			else if(curve.charAt(ii) == 'A')
			{
				nextCurve.append("[+>FA][->FA]");
			}
			else
			{
				nextCurve.append(curve.charAt(ii));
			}
		}
		
		return nextCurve;
	}
	
	public StringBuilder applyTransition(StringBuilder curve)
	{
		turningAngle += r10;
		return curve;
	}
	
	protected double getStartingAngle()
	{
		return r90;
	}
	
	protected double getTurningAngle()
	{
		return turningAngle;
	}
	
	protected double getScaleFactor()
	{
		return 0.75;
	}
	
	public String toString()
	{
		return "Branches";
	}
	
	public static void main(String[] args)
	{
		int iterations = 6;
		
		if(args.length > 0)
		{
			iterations = Integer.parseInt(args[0]);
		}
		
		FractalPanel fractal = new FractalPanel(new Branches());		
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
import java.awt.Color;
import java.awt.Paint;
import java.awt.Point;
import java.awt.RadialGradientPaint;

import java.util.*;

import javax.swing.*;

public class LevyCurves extends LSystem
{
	protected double curAngle = 0.0;
	
	public LevyCurves()
	{
		super();
	}
	
	public int getSuggestedIterations()
	{
		return 70;
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
		StringBuilder nextCurve;
		StringBuilder curve = new StringBuilder("F");
		
		for(int ii=0; ii < 15; ii++)
		{
			nextCurve = new StringBuilder();
			
			for(int jj=0; jj < curve.length(); jj++)
			{
				if(curve.charAt(jj) == 'F')
				{
					nextCurve.append("+F--F+");
				}
				else
				{
					nextCurve.append(curve.charAt(jj));
				}
			}
			
			curve = nextCurve;
		}		
		
		
		return curve;
	}
	
	public StringBuilder applyTransition(StringBuilder curve)
	{
		curAngle += r45 / getSuggestedIterations();
		System.out.println("applyTransition " + curAngle);
		return curve;
	}
	
	protected double getTurningAngle()
	{
		return curAngle;
	}
	
	public String toString()
	{
		return "Levy Curves";
	}
	
	public static void main(String[] args)
	{
		SimpleFractal.main(args);
	}
}

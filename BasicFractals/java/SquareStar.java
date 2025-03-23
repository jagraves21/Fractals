import java.awt.Color;
import java.awt.Paint;
import java.awt.Point;
import java.awt.RadialGradientPaint;

import java.util.*;

import javax.swing.*;

public class SquareStar extends LSystem
{
	public static final double r9 = (9 * Math.PI / 180.0);
	
	public SquareStar()
	{
		super();
	}
	
	public int getSuggestedIterations()
	{
		return 4;
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
		return new StringBuilder("++[F]++++++++[F]++++++++[F]++++++++[F]++++++++[F]");
	}
	
	public StringBuilder applyTransition(StringBuilder curve)
	{
		StringBuilder nextCurve = new StringBuilder();
		
		for(int ii=0; ii < curve.length(); ii++)
		{
			if(curve.charAt(ii) == 'F')
			{
				nextCurve.append("F[+++++F----------F]-----F++++++++++F-----F");
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
		return r9;
	}
	
	public String toString()
	{
		return "Square Star";
	}
	
	public static void main(String[] args)
	{
				SimpleFractal.main(args);
	}
}

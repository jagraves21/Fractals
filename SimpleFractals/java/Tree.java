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
		SimpleFractal.main(args);
	}
}

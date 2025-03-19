import java.awt.Color;
import java.awt.Paint;
import java.awt.Point;
import java.awt.RadialGradientPaint;

import java.util.*;

import javax.swing.*;

public class SquareStar2 extends LSystem
{
	public static final double r9 = (9 * Math.PI / 180.0);
	
	public SquareStar2()
	{
		super();
	}
	
	public int getSuggestedIterations()
	{
		return 3;
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
				nextCurve.append("F[+++++FF----------FF]-----FF++++++++++FF-----F");
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
		return "Square Star 2";
	}
	
	public static void main(String[] args)
	{
		int iterations = 5;
		
		if(args.length > 0)
		{
			iterations = Integer.parseInt(args[0]);
		}
		
		FractalPanel fractal = new FractalPanel(new SquareStar2());		
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
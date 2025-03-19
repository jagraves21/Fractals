import java.awt.Color;
import java.awt.Paint;
import java.awt.Point;
import java.awt.RadialGradientPaint;

import java.util.*;

import javax.swing.*;

public class PenroseTiling extends LSystem
{
	public static final double r17 = (17 * Math.PI / 180.0);
	public static final double r163 = (163 * Math.PI / 180.0);
	public static final double r36 = (36 * Math.PI / 180.0);
	
	protected int iteration;
	
	public PenroseTiling()
	{
		super();
		
		iteration = 1;
	}
	
	public int getSuggestedIterations()
	{
		return 6;
	}
		
	protected Paint getForeground()
	{
		MyPoint mid = newMinPoint.midPoint(newMaxPoint);
		MyPoint tmp = new MyPoint(newMinPoint.x, mid.y);
		
		float[] dist = {0.0f, 0.2f, 0.4f, 0.6f, 0.8f, 1.0f};
		Color[] colors = {Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN, Color.BLUE, new Color(138, 43, 226)};
		
		RadialGradientPaint paint = new RadialGradientPaint((float)mid.x, (float)mid.y, (float)mid.distance(tmp), dist, colors);
		return paint;
	}
	
	protected StringBuilder getAxiom()
	{
		iteration = 1;
		return new StringBuilder("[+8F--9F[---6F--7F]+]++[+8F--9F[---6F--7F]+]++[+8F--9F[---6F--7F]+]++[+8F--9F[---6F--7F]+]++[+8F--9F[---6F--7F]+]");
	}
	
	public StringBuilder applyTransition(StringBuilder curve)
	{
		StringBuilder nextCurve = new StringBuilder();
		
		for(int ii=0; ii < curve.length(); ii++)
		{
			if(curve.charAt(ii) == '6')
			{
				nextCurve.append("8F++9F----7F[-8F----6F]++");
			}
			else if(curve.charAt(ii) == '7')
			{
				nextCurve.append("+8F--9F[---6F--7F]+");
			}
			else if(curve.charAt(ii) == '8')
			{
				nextCurve.append("-6F++7F[+++8F++9F]-");
			}
			else if(curve.charAt(ii) == '9')
			{
				nextCurve.append("--8F++++6F[+9F++++7F]--7F");
			}
			else if(curve.charAt(ii) == 'F')
			{
			
			}
			else
			{
				nextCurve.append(curve.charAt(ii));
			}
		}
		
		iteration++;
		
		return nextCurve;
	}
	
	protected double getStartingAngle()
	{
		return (((iteration/2) % 2) == 0) ? r17 : -r17;
	}
	
	protected double getTurningAngle()
	{
		return r36;
	}
	
	public String toString()
	{
		return "Penrose Tiling";
	}
	
	public static void main(String[] args)
	{
		int iterations = 5;
		
		if(args.length > 0)
		{
			iterations = Integer.parseInt(args[0]);
		}
		
		FractalPanel fractal = new FractalPanel(new PenroseTiling());		
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
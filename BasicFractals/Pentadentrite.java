import java.awt.Color;
import java.awt.Paint;
import java.awt.Point;
import java.awt.RadialGradientPaint;

import java.util.*;

import javax.swing.*;

public class Pentadentrite extends LSystem
{
	public static final double r72 = (72 * Math.PI / 180.0);
	
	public Pentadentrite()
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
		
		float[] dist = {0.0f, 1.0f};
		Color[] colors = {Color.RED, Color.BLUE};
		
		RadialGradientPaint paint = new RadialGradientPaint((float)mid.x, (float)mid.y, (float)mid.distance(newMaxPoint), dist, colors);
		return paint;
	}
	
	public List<FractalShape> getFractal()
	{
		Stack<LState> stack = new Stack<LState>();
		List<FractalShape> lines = new LinkedList<FractalShape>();
		
		double curAngle = getStartingAngle();
		double turningAngle = getTurningAngle();
		double radius = 100;
		double scaleFactor = getScaleFactor();
		char plus = '+';
		char minus = '-';
		MyPoint p1 = new MyPoint(0,0);
		MyPoint p2 = new MyPoint(radius,0);
		MyPoint p3;
		LState state;
		
		for(int ii=0; ii < curve.length(); ii++)
		{
			if(curve.charAt(ii) == 'F')
			{
				p3 = translate(p2, p1, radius, r180 + curAngle);
				p1 = p2;
				p2 = p3;
				lines.add(new LineSegment(p1,p2));
				curAngle = 0;
			}
			else if(curve.charAt(ii) == 'f')
			{
				p3 = translate(p2, p1, radius, r180 + curAngle);
				p1 = p2;
				p2 = p3;
				curAngle = 0;
			}
			else if(curve.charAt(ii) == plus)
			{
				curAngle += turningAngle;
			}
			else if(curve.charAt(ii) == minus)
			{
				curAngle -= turningAngle;
			}
			else if(curve.charAt(ii) == '|')
			{
				curAngle += r180;
			}
			else if(curve.charAt(ii) == '[')
			{
				state = new LState(curAngle, turningAngle, radius, scaleFactor, plus, minus, p1, p2);
				stack.push(state);
			}
			else if(curve.charAt(ii) == ']')
			{
				state = stack.pop();
				
				curAngle = state.curAngle;
				turningAngle = state.turningAngle;
				radius = state.radius;
				scaleFactor = state.scaleFactor;
				plus = state.plus;
				minus = state.minus;
				p1 = state.p1;
				p2 = state.p2;
			}
			else if(curve.charAt(ii) == '>')
			{
				radius *= getScaleFactor();
			}
			else if(curve.charAt(ii) == '<')
			{
				radius /= getScaleFactor();
			}
			else if(curve.charAt(ii) == '&')
			{
				char tmp = minus;
				minus = plus;
				plus = tmp;
			}
			else if(curve.charAt(ii) == '(')
			{
				turningAngle -= getAngleIncrement();
			}
			else if(curve.charAt(ii) == ')')
			{
				turningAngle += getAngleIncrement();
			}
		}
		
		double sr = 0;
		double sg = 0;
		double sb = 255;
		
		double tr = 0;
		double tg = 191;
		double tb = 255;
		
		double dr = (tr - sr) / lines.size();
		double dg = (tg - sg) / lines.size();
		double db = (tb - sb) / lines.size();
		
		int r;
		int g;
		int b;
		
		int count=0;
		
		Iterator<FractalShape> iter = lines.iterator();
		while(iter.hasNext())
		{
			LineSegment line = (LineSegment) iter.next();
			
			r = (int)(sr + (dr * count));
			g = (int)(sg + (dg * count));
			b = (int)(sb + (db * count));
			
			line.paint = new Color(r,g,b);
			
			count++;
		}
		
		return lines;
	}
	
	protected StringBuilder getAxiom()
	{
		return new StringBuilder("F");
	}
	
	public StringBuilder applyTransition(StringBuilder curve)
	{
		StringBuilder nextCurve = new StringBuilder();
		
		for(int ii=0; ii < curve.length(); ii++)
		{
			if(curve.charAt(ii) == 'F')
			{
				nextCurve.append("F+F-F--F+F+F");
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
		return r72;
	}
	
	public String toString()
	{
		return "Pentadentrite";
	}
	
	public static void main(String[] args)
	{
		int iterations = 6;
		
		if(args.length > 0)
		{
			iterations = Integer.parseInt(args[0]);
		}
		
		FractalPanel fractal = new FractalPanel(new Pentadentrite());		
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
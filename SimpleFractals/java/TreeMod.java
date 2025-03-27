import java.awt.Color;
import java.awt.Paint;
import java.awt.Point;
import java.awt.RadialGradientPaint;

import java.util.*;

import javax.swing.*;

public class TreeMod extends LSystem
{
	protected StringBuilder curve;
	protected List<FractalShape> lines;
	protected Map<Double,List<LineSegment>> levels;
	
	public TreeMod()
	{
		super();
		
		lines = new LinkedList<FractalShape>();
		levels = new TreeMap<Double,List<LineSegment>>();
		
		curve = new StringBuilder("FX");
		
		StringBuilder nextCurve;
		for(int ii=0; ii < getSuggestedIterations(); ii++)
		{
			nextCurve = new StringBuilder();
			
			for(int jj=0; jj < curve.length(); jj++)
			{
				if(curve.charAt(jj) == 'X')
				{
					nextCurve.append(">[-FX]+FX");
				}
				else
				{
					nextCurve.append(curve.charAt(jj));
				}
			}
			
			curve = nextCurve;
		}
		
		createLines();
	}
	
	public int getSuggestedIterations()
	{
		return 10;
	}
	
	public List<FractalShape> createLines()
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
		
		LineSegment line;
		List<LineSegment> list;
		
		for(int ii=0; ii < curve.length(); ii++)
		{
			if(curve.charAt(ii) == 'F')
			{
				p3 = translate(p2, p1, radius, r180 + curAngle);
				p1 = p2;
				p2 = p3;
				line = new LineSegment(p1,p2);
				lines.add(line);
				curAngle = 0;
				
				if(!levels.containsKey(radius))
				{
					list = new LinkedList<LineSegment>();
					levels.put(radius, list);
				}
				else
				{
					list = levels.get(radius);	
				}
				
				list.add(line);
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
		
		return lines;
	}
	
	int count = 0;
	
	public List<FractalShape> getFractal()
	{
		Color[] colors = {Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN, Color.BLUE, new Color(138, 43, 226)};
		
		int index = count++;
		
		Map.Entry<Double,List<LineSegment>> entry;
		List<LineSegment> list;
		LineSegment line;
		Iterator<LineSegment> listIter;
		
		Iterator<Map.Entry<Double,List<LineSegment>>> levelsIter = levels.entrySet().iterator();
		while(levelsIter.hasNext())
		{
			entry = levelsIter.next();
			list = entry.getValue();
			
			listIter = list.iterator();
			while(listIter.hasNext())
			{
				line = listIter.next();
				line.paint = colors[index % colors.length];
				lines.add(line);	
			}
			
			index++;
		}
		
		return lines;
	}
	
	protected StringBuilder getAxiom()
	{
		return curve;
	}
	
	public StringBuilder applyTransition(StringBuilder curve)
	{
		return curve;
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
		return "Tree Mod";
	}
	
	public static void main(String[] args)
	{
				SimpleFractal.main(args);
	}
}

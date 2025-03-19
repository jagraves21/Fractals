import java.util.List;

import java.awt.Paint;
import java.awt.Graphics2D;

public class Polygon extends FractalShape implements Comparable<Polygon>
{
	public MyPoint[] points;
	public Paint paint;
	public boolean fill;
	
	public Polygon(MyPoint[] points)
	{
		this(points,null);
	}
	
	public Polygon(List<MyPoint> points)
	{
		this(points.toArray(new MyPoint[0]),null);
	}
	
	public Polygon(MyPoint[] points, Paint paint)
	{
		this(points,paint,false);
	}
	
	public Polygon(List<MyPoint> points, Paint paint)
	{
		this(points.toArray(new MyPoint[0]),paint,false);
	}
	
	public Polygon(MyPoint[] points, Paint paint, boolean fill)
	{
		this.points = points;
		this.paint = paint;
		this.fill = fill;
	}
	
	public Polygon(List<MyPoint> points, Paint paint, boolean fill)
	{
		this(points.toArray(new MyPoint[0]), paint, fill);
	}
	
	public int compareTo(Polygon that)
	{
		int res;
		
		for(int ii=0; ii < this.points.length; ii++)
		{
			res = this.points[ii].compareTo(that.points[ii]);
			
			if(res != 0)
			{
				return res;
			}
		}
		
		return 0;
	}
	
	public boolean equals(Object that)
	{
		return this.compareTo((Polygon)that) == 0;
	}
	
	public String toString()
	{
		StringBuilder str = new StringBuilder("[");
		
		for(int ii=0; ii < points.length; ii++)
		{
			str.append(points[ii]);
			
			if(ii+1 < points.length)
			{
				str.append(" ");
			}
		}
		
		str.append("]");
		
		return str.toString();
	}
	
	public void checkMinMax(MyPoint minPoint, MyPoint maxPoint)
	{
		for(int ii=0; ii < points.length; ii++)
		{
			points[ii].checkMinMax(minPoint, maxPoint);
		}
	}
	
	public Polygon translate(MyPoint p)
	{
		MyPoint[] translatedPoints = new MyPoint[points.length];
		for(int ii=0; ii < points.length; ii++)
		{
			translatedPoints[ii] = points[ii].translate(p);
		}
		
		return new Polygon(translatedPoints, paint, fill);
	}
	
	public Polygon scale(double xFactor, double yFactor)
	{
		MyPoint[] translatedPoints = new MyPoint[points.length];
		for(int ii=0; ii < points.length; ii++)
		{
			translatedPoints[ii] = points[ii].scale(xFactor,yFactor);
		}
		
		return new Polygon(translatedPoints, paint, fill);
	}
	
	public Polygon scale(double xFactor, double yFactor, MyPoint p)
	{
		MyPoint[] translatedPoints = new MyPoint[points.length];
		for(int ii=0; ii < points.length; ii++)
		{
			translatedPoints[ii] = points[ii].scale(xFactor,yFactor,p);
		}
		
		return new Polygon(translatedPoints, paint, fill);
	}
	
	public Polygon rotate(double thetaOff)
	{
		MyPoint[] rotatedPoints = new MyPoint[points.length];
		for(int ii=0; ii < points.length; ii++)
		{
			rotatedPoints[ii] = points[ii].rotate(thetaOff);
		}
		
		return new Polygon(rotatedPoints, paint, fill);
	}
	
	public Polygon rotate(double thetaOff, MyPoint p)
	{
		MyPoint[] rotatedPoints = new MyPoint[points.length];
		for(int ii=0; ii < points.length; ii++)
		{
			rotatedPoints[ii] = points[ii].rotate(thetaOff,p);
		}
		
		return new Polygon(rotatedPoints, paint, fill);
	}
	
	public void paint(Graphics2D g)
	{
		int[] xPoints = new int[points.length];
		int[] yPoints = new int[points.length];
		
		for(int ii=0; ii < points.length; ii++)
		{
			xPoints[ii] = (int)Math.round(points[ii].x);
			yPoints[ii] = (int)Math.round(points[ii].y);
		}
		
		if(paint != null)
		{
			Paint tmpPaint = g.getPaint();
			g.setPaint(paint);
			
			if(fill)
			{
				g.fillPolygon(xPoints, yPoints, xPoints.length);
			}
			else
			{
				g.drawPolygon(xPoints, yPoints, xPoints.length);
			}
			
			g.setPaint(tmpPaint);
		}
		else
		{
			if(fill)
			{
				g.fillPolygon(xPoints, yPoints, xPoints.length);
			}
			else
			{
				g.drawPolygon(xPoints, yPoints, xPoints.length);
			}
		}
	}
}
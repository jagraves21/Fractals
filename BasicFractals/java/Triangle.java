import java.awt.Paint;
import java.awt.Graphics2D;

public class Triangle extends FractalShape implements Comparable<Triangle>
{
	public MyPoint p1;
	public MyPoint p2;
	public MyPoint p3;
	public Paint paint;
	public boolean fill;
	
	public Triangle(MyPoint p1, MyPoint p2, MyPoint p3)
	{
		this(p1,p2,p3,null);
	}
	
	public Triangle(MyPoint p1, MyPoint p2, MyPoint p3, Paint paint)
	{
		this(p1,p2,p3,paint,false);
	}
	
	public Triangle(MyPoint p1, MyPoint p2, MyPoint p3, Paint paint, boolean fill)
	{
		this.p1 = p1;
		this.p2 = p2;
		this.p3 = p3;
		this.paint = paint;
		this.fill = fill;
	}
	
	public int compareTo(Triangle that)
	{
		int res;
		
		if((res = this.p1.compareTo(that.p1)) != 0)
		{
			return res;
		}
		else if((res = this.p2.compareTo(that.p2)) != 0)
		{
			return res;
		}
		else
		{
			return this.p3.compareTo(that.p3);
		}
	}
	
	public boolean equals(Object that)
	{
		return this.compareTo((Triangle)that) == 0;
	}
	
	public String toString()
	{
		return "[" + p1.toString() + " " + p2.toString() + " " + p3.toString() + "]";
	}
	
	public void checkMinMax(MyPoint minPoint, MyPoint maxPoint)
	{
		p1.checkMinMax(minPoint, maxPoint);
		p2.checkMinMax(minPoint, maxPoint);
		p3.checkMinMax(minPoint, maxPoint);
	}
	
	public Triangle scale(MyPoint oldMinPoint, double oldXRange, double oldYRange, MyPoint newMinPoint, double newXRange, double newYRange)
	{
		MyPoint np1 = p1.scale(oldMinPoint, oldXRange, oldYRange, newMinPoint, newXRange, newYRange);
		MyPoint np2 = p2.scale(oldMinPoint, oldXRange, oldYRange, newMinPoint, newXRange, newYRange);
		MyPoint np3 = p3.scale(oldMinPoint, oldXRange, oldYRange, newMinPoint, newXRange, newYRange);
		
		return new Triangle(np1,np2,np3,paint,fill);
	}
	
	public void paint(Graphics2D g)
	{
		int[] xPoints = new int[3];
		int[] yPoints = new int[3];
		
		xPoints[0] = (int)Math.round(p1.x);
		xPoints[1] = (int)Math.round(p2.x);
		xPoints[2] = (int)Math.round(p3.x);
		
		yPoints[0] = (int)Math.round(p1.y);
		yPoints[1] = (int)Math.round(p2.y);
		yPoints[2] = (int)Math.round(p3.y);
		
		if(paint != null)
		{
			Paint tmpPaint = g.getPaint();
			g.setPaint(paint);
			
			if(fill)
			{
				g.fillPolygon(xPoints, yPoints, 3);
			}
			else
			{
				g.drawPolygon(xPoints, yPoints, 3);
			}
			
			g.setPaint(tmpPaint);
		}
		else
		{
			if(fill)
			{
				g.fillPolygon(xPoints, yPoints, 3);
			}
			else
			{
				g.drawPolygon(xPoints, yPoints, 3);
			}
		}
	}
}
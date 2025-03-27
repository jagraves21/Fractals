import java.awt.Paint;
import java.awt.Graphics2D;

public class Rectangle extends FractalShape implements Comparable<Rectangle>
{
	public MyPoint p1;
	public MyPoint p2;
	public MyPoint p3;
	public MyPoint p4;
	public Paint paint;
	public boolean fill;
	
	public Rectangle(MyPoint p1, MyPoint p2, MyPoint p3, MyPoint p4)
	{
		this(p1,p2,p3,p4,null);
	}
	
	public Rectangle(MyPoint p1, MyPoint p2, MyPoint p3, MyPoint p4, Paint paint)
	{
		this(p1,p2,p3,p4,paint,false);
	}
	
	public Rectangle(MyPoint p1, MyPoint p2, MyPoint p3, MyPoint p4, Paint paint, boolean fill)
	{
		this.p1 = p1;
		this.p2 = p2;
		this.p3 = p3;
		this.p4 = p4;
		this.paint = paint;
		this.fill = fill;
	}
	
	public int compareTo(Rectangle that)
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
		else if((res = this.p3.compareTo(that.p3)) != 0)
		{
			return res;
		}
		else
		{
			return this.p4.compareTo(that.p4);
		}
	}
	
	public boolean equals(Object that)
	{
		return this.compareTo((Rectangle)that) == 0;
	}
	
	public String toString()
	{
		return "[" + p1.toString() + " " + p2.toString() + " " + p3.toString() + " " + p4.toString() + "]";
	}
	
	public void checkMinMax(MyPoint minPoint, MyPoint maxPoint)
	{
		p1.checkMinMax(minPoint, maxPoint);
		p2.checkMinMax(minPoint, maxPoint);
		p3.checkMinMax(minPoint, maxPoint);
		p4.checkMinMax(minPoint, maxPoint);
	}
	
	public Rectangle scale(MyPoint oldMinPoint, double oldXRange, double oldYRange, MyPoint newMinPoint, double newXRange, double newYRange)
	{
		MyPoint np1 = p1.scale(oldMinPoint, oldXRange, oldYRange, newMinPoint, newXRange, newYRange);
		MyPoint np2 = p2.scale(oldMinPoint, oldXRange, oldYRange, newMinPoint, newXRange, newYRange);
		MyPoint np3 = p3.scale(oldMinPoint, oldXRange, oldYRange, newMinPoint, newXRange, newYRange);
		MyPoint np4 = p4.scale(oldMinPoint, oldXRange, oldYRange, newMinPoint, newXRange, newYRange);
		
		return new Rectangle(np1,np2,np3,np4,paint,fill);
	}
	
	public void paint(Graphics2D g)
	{
		int[] xPoints = new int[4];
		int[] yPoints = new int[4];
		
		xPoints[0] = (int)Math.round(p1.x);
		xPoints[1] = (int)Math.round(p2.x);
		xPoints[2] = (int)Math.round(p3.x);
		xPoints[3] = (int)Math.round(p4.x);
		
		yPoints[0] = (int)Math.round(p1.y);
		yPoints[1] = (int)Math.round(p2.y);
		yPoints[2] = (int)Math.round(p3.y);
		yPoints[3] = (int)Math.round(p4.y);
		
		if(paint != null)
		{
			Paint tmpPaint = g.getPaint();
			g.setPaint(paint);
			
			if(fill)
			{
				g.fillPolygon(xPoints, yPoints, 4);
			}
			else
			{
				g.drawPolygon(xPoints, yPoints, 4);
			}
			
			g.setPaint(tmpPaint);
		}
		else
		{
			if(fill)
			{
				g.fillPolygon(xPoints, yPoints, 4);
			}
			else
			{
				g.drawPolygon(xPoints, yPoints, 4);
			}
		}
	}
}
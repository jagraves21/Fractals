import java.awt.Paint;
import java.awt.Graphics2D;

public class MyPoint extends FractalShape implements Comparable<MyPoint>
{
	public double x;
	public double y;
	public Paint paint;
	
	MyPoint(double x, double y)
	{
		this(x,y,null);
	}
	
	public MyPoint(double x, double y, Paint paint)
	{
		this.x = x;
		this.y = y;
		this.paint = paint;
	}
	
	public MyPoint midPoint(MyPoint that)
	{
		return new MyPoint((this.x+that.x)/2, (this.y+that.y)/2);
	}
	
	public MyPoint oneThirdsPoint(MyPoint that)
	{
		double x;
		double y;
		
		if(this.x <= that.x)
		{
			x = this.x + ((that.x - this.x)/3);
		}
		else
		{
			x = this.x - ((this.x - that.x)/3);
		}
		
		if(this.y <= that.y)
		{
			y = this.y + ((that.y - this.y)/3);
		}
		else
		{
			y = this.y - ((this.y - that.y)/3);
		}
		
		return new MyPoint(x,y);
	}
	
	public MyPoint twoThirdsPoint(MyPoint that)
	{
		double x;
		double y;
		
		if(this.x <= that.x)
		{
			x = that.x - ((that.x - this.x)/3);
		}
		else
		{
			x = that.x + ((this.x - that.x)/3);
		}
		
		if(this.y <= that.y)
		{
			y = that.y - ((that.y - this.y)/3);
		}
		else
		{
			y = that.y + ((this.y - that.y)/3);
		}
		
		return new MyPoint(x,y);
	}
	
	public MyPoint interiorPoint(MyPoint that, double where)
	{
		double x = this.x + ((that.x - this.x) * where);
		double y = this.y + ((that.y - this.y) * where);
		
		return new MyPoint(x,y,null);
	}
	
	public double distance(MyPoint that)
	{
		return Math.sqrt(Math.pow(this.x-that.x,2) + Math.pow(this.y-that.y,2));
	}
	
	public double slope(MyPoint that)
	{
		return (this.y-that.y)/(this.x-that.x);
	}
	
	public int compareTo(MyPoint that)
	{
		int res = Double.compare(this.x, that.x);
		if(res != 0)
		{
			return res;
		}
		else
		{
			return Double.compare(this.y, that.y);
		}
	}
	
	public boolean equals(Object that)
	{
		return this.compareTo((MyPoint)that) == 0;
	}
	
	public String toString()
	{
		return "(" + (int)x + "," + (int)y + ")";
	}
	
	public void checkMinMax(MyPoint minPoint, MyPoint maxPoint)
	{
		if(x < minPoint.x)
		{
			minPoint.x = x;
		}
		if(x > maxPoint.x)
		{
			maxPoint.x = x;
		}
		
		if(y < minPoint.y)
		{
			minPoint.y = y;
		}
		if(y > maxPoint.y)
		{
			maxPoint.y = y;
		}
	}
	
	public MyPoint scale(MyPoint oldMinPoint, double oldXRange, double oldYRange, MyPoint newMinPoint, double newXRange, double newYRange)
	{
		double newX = (((x - oldMinPoint.x) * newXRange) / oldXRange) + newMinPoint.x; 
		double newY = (((y - oldMinPoint.y) * newYRange) / oldYRange) + newMinPoint.y;
		return new MyPoint(newX,newY,paint);
	}
	
	public static MyPoint rotate(MyPoint center, MyPoint point, double radius, double thetaOff)
	{
		double theta = Math.atan2(point.y-center.y,point.x-center.x);
		theta -= thetaOff;
		
		return new MyPoint(center.x + (radius * Math.cos(theta)), center.y + (radius * Math.sin(theta)));
	}
	
	public MyPoint rotate(MyPoint point, double radius, double thetaOff)
	{
		return MyPoint.rotate(this, point, radius, thetaOff);
	}
	
	public void paint(Graphics2D g)
	{
		if(paint != null)
		{
			Paint tmpPaint = g.getPaint();
			g.setPaint(paint);
			
			g.drawLine((int)x, (int)y, (int)x, (int)y);
			//g.fillOval((int)x-5, (int)y-5, 10, 10);
			//g.fillOval((int)x, (int)y, 5, 5);
			//g.drawString((int)x + " " + (int)y, (int)x, (int)y+25);
			
			g.setPaint(tmpPaint);
		}
		else
		{
			//g.drawLine((int)x, (int)y, (int)x, (int)y);
			//g.fillOval((int)x-5, (int)y-5, 10, 10);
			//g.fillOval((int)x, (int)y, 5, 5);
			//g.drawString((int)x + " " + (int)y, (int)x, (int)y+25);
		}
	}
}

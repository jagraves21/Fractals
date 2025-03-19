import java.awt.Paint;
import java.awt.Graphics2D;

public class LineSegment extends FractalShape implements Comparable<LineSegment>
{
	public MyPoint p1;
	public MyPoint p2;
	public Paint paint;
	
	public LineSegment(MyPoint p1, MyPoint p2)
	{
		this(p1,p2,null);
	}
	
	public LineSegment(MyPoint p1, MyPoint p2, Paint paint)
	{
		this.p1 = p1;
		this.p2 = p2;
		this.paint = paint;
	}
	
	public MyPoint intersect(LineSegment that)
	{
		double mThis = this.p1.slope(this.p2);
		double mThat = that.p1.slope(that.p2);
		
		double x = ((mThat*that.p1.x) - that.p1.y - (mThis*this.p1.x) + this.p1.y) / (mThat - mThis);
		double y = (mThis * x) - (mThis * this.p1.x) + this.p1.y;
		
		return new MyPoint(x,y);
	}
	
	public int compareTo(LineSegment that)
	{
		int res = this.p1.compareTo(that.p1);
		
		if(res != 0)
		{
			return res;
		}
		else
		{
			return this.p2.compareTo(that.p2);
		}
	}
	
	public boolean equals(Object that)
	{
		return this.compareTo((LineSegment)that) == 0;
	}
	
	public String toString()
	{
		return "[" + p1.toString() + " " + p2.toString() + "]";
	}
	
	public void checkMinMax(MyPoint minPoint, MyPoint maxPoint)
	{
		p1.checkMinMax(minPoint, maxPoint);
		p2.checkMinMax(minPoint, maxPoint);
	}
	
	public LineSegment translate(MyPoint p)
	{
		return new LineSegment(p1.translate(p), p2.translate(p), paint);
	}
	
	public LineSegment scale(double xFactor, double yFactor)
	{
		return new LineSegment(p1.scale(xFactor,yFactor), p2.scale(xFactor,yFactor), paint);
	}
	
	public LineSegment scale(double xFactor, double yFactor, MyPoint p)
	{
		return new LineSegment(p1.scale(xFactor,yFactor,p), p2.scale(xFactor,yFactor,p), paint);
	}
	
	public LineSegment rotate(double thetaOff)
	{
		return new LineSegment(p1.rotate(thetaOff), p2.rotate(thetaOff), paint);
	}
	
	public LineSegment rotate(double thetaOff, MyPoint p)
	{
		return new LineSegment(p1.rotate(thetaOff,p), p2.rotate(thetaOff,p), paint);
	}
	
	public void paint(Graphics2D g)
	{
		if(p1.x != p2.x || p1.y != p2.y)
		{
			if(paint != null)
			{
				Paint tmpPaint = g.getPaint();
				g.setPaint(paint);
				g.drawLine((int)Math.round(p1.x), (int)Math.round(p1.y), (int)Math.round(p2.x), (int)Math.round(p2.y));
				g.setPaint(tmpPaint);
			}
			else
			{
				//g.drawLine((int)p1.x, (int)p1.y, (int)p2.x, (int)p2.y);
				g.drawLine((int)Math.round(p1.x), (int)Math.round(p1.y), (int)Math.round(p2.x), (int)Math.round(p2.y));
			}
		}
	}
}
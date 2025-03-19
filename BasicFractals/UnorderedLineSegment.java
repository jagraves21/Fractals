import java.awt.Paint;
import java.awt.Graphics2D;

public class UnorderedLineSegment extends LineSegment implements Comparable<LineSegment>
{
	public UnorderedLineSegment(MyPoint p1, MyPoint p2)
	{
		this(p1,p2,null);
	}
	
	public UnorderedLineSegment(MyPoint p1, MyPoint p2, Paint paint)
	{
		super(p1,p2,paint);
	}
	
	
	public int compareTo(LineSegment that)
	{
		MyPoint thisP1 = this.p1.compareTo(this.p2) > 0 ? this.p2 : this.p1;
		MyPoint thisP2 = thisP1 == this.p1 ? this.p2 : this.p1;
		
		MyPoint thatP1 = that.p1.compareTo(that.p2) > 0 ? that.p2 : that.p1;
		MyPoint thatP2 = thatP1 == that.p1 ? that.p2 : that.p1;
		
		int res = thisP1.compareTo(thatP1);
		
		if(res != 0)
		{
			return res;
		}
		else
		{
			return thisP2.compareTo(thatP2);
		}
	}
	
	public UnorderedLineSegment translate(MyPoint p)
	{
		return new UnorderedLineSegment(p1.translate(p), p2.translate(p), paint);
	}
	
	public UnorderedLineSegment scale(double xFactor, double yFactor)
	{
		return new UnorderedLineSegment(p1.scale(xFactor,yFactor), p2.scale(xFactor,yFactor), paint);
	}
	
	public UnorderedLineSegment scale(double xFactor, double yFactor, MyPoint p)
	{
		return new UnorderedLineSegment(p1.scale(xFactor,yFactor,p), p2.scale(xFactor,yFactor,p), paint);
	}
	
	public UnorderedLineSegment rotate(double thetaOff)
	{
		return new UnorderedLineSegment(p1.rotate(thetaOff), p2.rotate(thetaOff), paint);
	}
	
	public UnorderedLineSegment rotate(double thetaOff, MyPoint p)
	{
		return new UnorderedLineSegment(p1.rotate(thetaOff,p), p2.rotate(thetaOff,p), paint);
	}

}
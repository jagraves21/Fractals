import java.awt.Paint;
import java.awt.Graphics2D;

public class Circle extends FractalShape implements Comparable<Circle>
{
	public MyPoint c;
	//public MyPoint p2;
	double radius;
	public Paint paint;
	public boolean fill;
	
	public Circle(MyPoint c, double radius)
	{
		this(c,radius,null,false);
	}
	
	public Circle(MyPoint c, double radius, Paint paint)
	{
		this(c,radius,paint,false);
	}
	
	public Circle(MyPoint c, double radius, Paint paint, boolean fill)
	{
		this.c = c;
		this.radius = radius;
		this.paint = paint;
		this.fill = fill;
	}
	
	public double radius()
	{
		return radius;
	}
	
	public boolean inside(Circle that)
	{
		return that.c.distance(this.c) + this.radius() < that.radius();
	}
	
	public boolean intersects(Circle that)
	{
		return this.c.distance(that.c) < this.radius + that.radius;
	}
	
	public MyPoint[] intersect(Circle that)
	{
		double x0 = this.c.x;
		double y0 = this.c.y;
		double r0 = this.radius();
		
		double x1 = that.c.x;
		double y1 = that.c.y;
		double r1 = that.radius();
		
		double a, dx, dy, d, h, rx, ry;
		double x2, y2;
		
		/* dx and dy are the vertical and horizontal distances between
		* the circle centers.
		*/
		dx = x1 - x0;
		dy = y1 - y0;
		
		/* Determine the straight-line distance between the centers. */
		d = Math.sqrt((dy*dy) + (dx*dx));
		
		/* Check for solvability. */
		if (d > (r0 + r1))
		{
			/* no solution. circles do not intersect. */
			return null;
		}
		if (d < Math.abs(r0 - r1))
		{
			/* no solution. one circle is contained in the other */
			return null;
		}
		
		/* 'point 2' is the point where the line through the circle
		* intersection points crosses the line between the circle
		* centers.  
		*/
		
		/* Determine the distance from point 0 to point 2. */
		a = ((r0*r0) - (r1*r1) + (d*d)) / (2.0 * d) ;
		
		/* Determine the coordinates of point 2. */
		x2 = x0 + (dx * a/d);
		y2 = y0 + (dy * a/d);
		
		/* Determine the distance from point 2 to either of the
		* intersection points.
		*/
		h = Math.sqrt((r0*r0) - (a*a));
		
		/* Now determine the offsets of the intersection points from
		* point 2.
		*/
		rx = -dy * (h/d);
		ry = dx * (h/d);
		
		/* Determine the absolute intersection points. */
		MyPoint nc1 = new MyPoint(x2 + rx, y2 + ry);
		MyPoint nc2 = new MyPoint(x2 - rx, y2 - ry);
		
		MyPoint[] points = {nc1, nc2};
		return points;
	}
	
	public int compareTo(Circle that)
	{
		int res = this.c.compareTo(that.c);
		
		if(res != 0)
		{
			return res;
		}
		else
		{
			return Double.compare(this.radius, that.radius);
		}
	}
	
	public boolean equals(Object that)
	{
		return this.compareTo((Circle)that) == 0;
	}
	
	public String toString()
	{
		return "[" + c.toString() + " " + radius() + "]";
	}
	
	public void checkMinMax(MyPoint minPoint, MyPoint maxPoint)
	{
		double minX = c.x - radius();
		double minY = c.y - radius();
		
		double maxX = c.x + radius();
		double maxY = c.y + radius();
		
		if(minX < minPoint.x)
		{
			minPoint.x = minX;
		}
		if(maxX > maxPoint.x)
		{
			maxPoint.x = maxX;
		}
		
		if(minY < minPoint.y)
		{
			minPoint.y = minY;
		}
		if(maxY > maxPoint.y)
		{
			maxPoint.y = maxY;
		}
		
		c.x -= radius;
		c.y -= radius;
		c.checkMinMax(minPoint, maxPoint);
		
		c.x += 2*radius;
		c.y += 2*radius;
		c.checkMinMax(minPoint, maxPoint);
		
		c.x -= radius;
		c.y -= radius;
	}
	
	public Circle translate(MyPoint p)
	{
		return new Circle(c.translate(p), radius, paint, fill);
	}
	
	public Circle scale(double xFactor, double yFactor)
	{
		return new Circle(c.scale(xFactor,yFactor), radius*xFactor, paint, fill);
	}
	
	public Circle scale(double xFactor, double yFactor, MyPoint p)
	{
		return new Circle(c.scale(xFactor,yFactor,p), radius*xFactor, paint, fill);
	}
	
	public Circle rotate(double thetaOff)
	{
		return new Circle(c.rotate(thetaOff), radius, paint, fill);
	}
	
	public Circle rotate(double thetaOff, MyPoint p)
	{
		return new Circle(c.rotate(thetaOff,p), radius, paint, fill);
	}
	
	public Circle invert(Circle that) {
		double dist = that.c.distance(this.c);
		double x = (that.c.x - this.c.x)/dist*(dist * this.radius*this.radius / (dist * dist - that.radius * that.radius)) + this.c.x;
		double y = (that.c.y - this.c.y)/dist*(dist * this.radius*this.radius / (dist * dist - that.radius * that.radius)) + this.c.y;
		double radius = Math.abs(this.radius*this.radius * that.radius / (dist*dist - that.radius*that.radius));
		
		return new Circle(new MyPoint(x,y), radius, java.awt.Color.GREEN);
	}
	
	public void paint(Graphics2D g)
	{
		if(radius > 0)
		{
			if(paint != null)
			{
				Paint tmpPaint = g.getPaint();
				g.setPaint(paint);
				
				if(fill)
				{
					g.fillOval((int)Math.round(c.x-radius), (int)Math.round(c.y-radius), (int)Math.round(radius*2), (int)Math.round(radius*2));
					//g.drawString(c.toString(), (int)c.x, (int)c.y);
					//g.fillOval((int)c.x-2, (int)c.y-2, 4, 4);
				}
				else
				{
					g.drawOval((int)Math.round(c.x-radius), (int)Math.round(c.y-radius), (int)Math.round(radius*2), (int)Math.round(radius*2));
					//g.drawString(c.toString(), (int)c.x, (int)c.y);
					//g.fillOval((int)c.x-2, (int)c.y-2, 4, 4);
				}
				
				g.setPaint(tmpPaint);
			}
			else
			{
				if(fill)
				{
					g.fillOval((int)Math.round(c.x-radius), (int)Math.round(c.y-radius), (int)Math.round(radius*2), (int)Math.round(radius*2));
					//g.drawString(c.toString(), (int)c.x, (int)c.y);
					//g.fillOval((int)c.x-2, (int)c.y-2, 4, 4);
				}
				else
				{
					g.drawOval((int)Math.round(c.x-radius), (int)Math.round(c.y-radius), (int)Math.round(radius*2), (int)Math.round(radius*2));
					//g.drawString(c.toString(), (int)c.x, (int)c.y);
					//g.fillOval((int)c.x-2, (int)c.y-2, 4, 4);
				}
			}
		}
	}
}
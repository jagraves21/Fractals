import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Point;

class NewLineSegment extends LineSegment
{
	public NewLineSegment(MyPoint p1, MyPoint p2) 
	{
		super(p1,p2);
	}

	public void paint(Graphics2D g)
	{
		if(p1.x != p2.x || p1.y != p2.y)
		{
			Paint tmpPaint = g.getPaint();
			Point tp1 = new Point((int)p1.x, (int)p1.y);
			Point tp2 = new Point((int)p2.x, (int)p2.y);
			g.setPaint(new GradientPaint(tp1, Color.RED, tp2, Color.BLUE));
			g.drawLine((int)Math.round(p1.x), (int)Math.round(p1.y), (int)Math.round(p2.x), (int)Math.round(p2.y));
			g.setPaint(tmpPaint);
		}
	}
}

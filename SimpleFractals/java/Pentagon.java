import java.awt.Graphics2D;
import java.awt.Paint;

public class Pentagon extends FractalShape implements Comparable<Pentagon> {
	public MyPoint p1;
	public MyPoint p2;
	public MyPoint p3;
	public MyPoint p4;
	public MyPoint p5;
	public Paint paint;
	public boolean fill;

	public Pentagon(MyPoint p1, MyPoint p2, MyPoint p3, MyPoint p4, MyPoint p5) {
		this(p1, p2, p3, p4, p5, null);
	}

	public Pentagon(MyPoint p1, MyPoint p2, MyPoint p3, MyPoint p4, MyPoint p5, Paint paint) {
		this(p1, p2, p3, p4, p5, paint, false);
	}

	public Pentagon(
			MyPoint p1, MyPoint p2, MyPoint p3, MyPoint p4, MyPoint p5, Paint paint, boolean fill) {
		this.p1 = p1;
		this.p2 = p2;
		this.p3 = p3;
		this.p4 = p4;
		this.p5 = p5;
		this.paint = paint;
		this.fill = fill;
	}

	public int compareTo(Pentagon that) {
		int res;

		if ((res = this.p1.compareTo(that.p1)) != 0) {
			return res;
		} else if ((res = this.p2.compareTo(that.p2)) != 0) {
			return res;
		} else if ((res = this.p3.compareTo(that.p3)) != 0) {
			return res;
		} else if ((res = this.p4.compareTo(that.p4)) != 0) {
			return res;
		} else {
			return this.p5.compareTo(that.p5);
		}
	}

	public boolean equals(Object that) {
		return this.compareTo((Pentagon) that) == 0;
	}

	public String toString() {
		return "["
				+ p1.toString()
				+ " "
				+ p2.toString()
				+ " "
				+ p3.toString()
				+ " "
				+ p4.toString()
				+ " "
				+ p5.toString()
				+ "]";
	}

	public void checkMinMax(MyPoint minPoint, MyPoint maxPoint) {
		p1.checkMinMax(minPoint, maxPoint);
		p2.checkMinMax(minPoint, maxPoint);
		p3.checkMinMax(minPoint, maxPoint);
		p4.checkMinMax(minPoint, maxPoint);
		p5.checkMinMax(minPoint, maxPoint);
	}

	public Pentagon scale(
			MyPoint oldMinPoint,
			double oldXRange,
			double oldYRange,
			MyPoint newMinPoint,
			double newXRange,
			double newYRange) {
		MyPoint np1 = p1.scale(oldMinPoint, oldXRange, oldYRange, newMinPoint, newXRange, newYRange);
		MyPoint np2 = p2.scale(oldMinPoint, oldXRange, oldYRange, newMinPoint, newXRange, newYRange);
		MyPoint np3 = p3.scale(oldMinPoint, oldXRange, oldYRange, newMinPoint, newXRange, newYRange);
		MyPoint np4 = p4.scale(oldMinPoint, oldXRange, oldYRange, newMinPoint, newXRange, newYRange);
		MyPoint np5 = p5.scale(oldMinPoint, oldXRange, oldYRange, newMinPoint, newXRange, newYRange);

		return new Pentagon(np1, np2, np3, np4, np5, paint, fill);
	}

	public void paint(Graphics2D g) {
		int[] xPoints = new int[5];
		int[] yPoints = new int[5];

		xPoints[0] = (int) Math.round(p1.x);
		xPoints[1] = (int) Math.round(p2.x);
		xPoints[2] = (int) Math.round(p3.x);
		xPoints[3] = (int) Math.round(p4.x);
		xPoints[4] = (int) Math.round(p5.x);

		yPoints[0] = (int) Math.round(p1.y);
		yPoints[1] = (int) Math.round(p2.y);
		yPoints[2] = (int) Math.round(p3.y);
		yPoints[3] = (int) Math.round(p4.y);
		yPoints[4] = (int) Math.round(p5.y);

		if (paint != null) {
			Paint tmpPaint = g.getPaint();
			g.setPaint(paint);

			if (fill) {
				g.fillPolygon(xPoints, yPoints, 5);
			} else {
				g.drawPolygon(xPoints, yPoints, 5);
			}

			g.setPaint(tmpPaint);
		} else {
			if (fill) {
				g.fillPolygon(xPoints, yPoints, 5);
			} else {
				g.drawPolygon(xPoints, yPoints, 5);
			}
		}
	}
}

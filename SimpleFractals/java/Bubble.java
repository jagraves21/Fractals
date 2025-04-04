import java.awt.*;

class Bubble extends Circle {
	public Bubble(MyPoint c, double radius, Paint paint) {
		super(c, radius, paint);
	}

	public void paint(Graphics2D g) {
		if (radius > 0) {
			if (paint instanceof Color) {
				Color color = (Color) paint;

				float[] dist = {0.7f, 1.0f};
				Color[] colors = {new Color(color.getRed(), color.getGreen(), color.getBlue(), 0), color};

				RadialGradientPaint paint =
						new RadialGradientPaint((float) c.x, (float) c.y, (float) radius, dist, colors);

				Paint tmpPaint = g.getPaint();
				g.setPaint(paint);

				g.fillOval(
						(int) Math.round(c.x - radius),
						(int) Math.round(c.y - radius),
						(int) Math.round(radius * 2),
						(int) Math.round(radius * 2));
				// g.drawString(c.toString(), (int)c.x, (int)c.y);
				// g.fillOval((int)c.x-2, (int)c.y-2, 4, 4);

				g.setPaint(tmpPaint);
			} else {
				super.paint(g);
			}
		}
	}

	public boolean equals(Object that) {
		return this.c.equals(((Bubble) that).c);
	}

	public Bubble scale(
			MyPoint oldMinPoint,
			double oldXRange,
			double oldYRange,
			MyPoint newMinPoint,
			double newXRange,
			double newYRange) {
		MyPoint nc = c.scale(oldMinPoint, oldXRange, oldYRange, newMinPoint, newXRange, newYRange);
		return new Bubble(nc, radius / (oldXRange) * (newXRange), paint);
	}
}

import java.awt.Color;
import java.awt.Paint;
import java.awt.RadialGradientPaint;
import java.util.*;
import javax.swing.*;

public class CircleInversion extends SimpleFractal {
	protected List<Circle> nextCircles;
	protected List<Circle> outerCircles;
	protected List<FractalShape> circles;

	public CircleInversion() {
		super();

		nextCircles = new LinkedList<Circle>();
		outerCircles = new LinkedList<Circle>();
		circles = new LinkedList<FractalShape>();
		createBase();
	}

	public int getSuggestedIterations() {
		return 3;
	}

	protected Paint getForeground() {
		MyPoint mid = newMinPoint.midPoint(newMaxPoint);
		mid.y += mid.y * (1.0 / 10.0);

		float[] dist = {0.3f, 1.0f};
		Color[] colors = {Color.BLUE, Color.RED};

		RadialGradientPaint paint =
				new RadialGradientPaint(
						(float) mid.x,
						(float) mid.y,
						(float) (mid.distance(newMinPoint) * (6.0 / 10.0)),
						dist,
						colors);
		return paint;
	}

	public List<FractalShape> getFractal() {
		return circles;
	}

	public void clearFractal() {
		createBase();
	}

	protected void createBase() {
		random = null;
		circles.clear();
		nextCircles.clear();
		outerCircles.clear();

		long seed = System.currentTimeMillis();

		double x;
		double y;
		double r;
		double scale = .25;
		Circle c1;
		Circle c2;

		seed = 1355959557249L;
		java.util.Random random = new java.util.Random(seed);
		// System.out.println("circle " + seed + "L");

		boolean inside;
		Iterator<Circle> outerIter;
		for (int ii = 0; ii < 500; ii++) {
			x = random.nextDouble() * WIDTH;
			y = random.nextDouble() * HEIGHT;
			r = WIDTH * scale;
			c1 = new Circle(new MyPoint(x, y), r, getColor(r));

			inside = false;
			for (int jj = 0; jj < 500000 && !inside; jj++) {
				outerIter = outerCircles.iterator();
				while (outerIter.hasNext()) {
					c2 = outerIter.next();
					if (c1.c.distance(c2.c) < (c1.radius() + c2.radius())) {
						inside = true;
						break;
					}
				}

				if (!inside) {
					nextCircles.add(c1);
					outerCircles.add(c1);
					circles.add(c1);
					scale *= .9;
				}
			}
		}
	}

	public void next() {
		double c1x, c2x, c3x;
		double c1y, c2y, c3y;
		double c1r, c2r, c3r;
		double dist;

		Circle c1;
		Circle c2;
		Circle c3;

		List<Circle> newCircles = new LinkedList<Circle>();

		Iterator<Circle> nextIter;
		Iterator<Circle> outerIter = outerCircles.iterator();
		while (outerIter.hasNext()) {
			c2 = outerIter.next();

			nextIter = nextCircles.iterator();
			while (nextIter.hasNext()) {
				c1 = nextIter.next();

				if (c1 != c2 && !c1.inside(c2)) {
					c1x = c1.c.x;
					c1y = c1.c.y;
					c2x = c2.c.x;
					c2y = c2.c.y;
					dist = c1.c.distance(c2.c);
					c1r = c1.radius();
					c2r = c2.radius();

					c3x = (c1x - c2x) / dist * (dist * c2r * c2r / (dist * dist - c1r * c1r)) + c2x;
					c3y = (c1y - c2y) / dist * (dist * c2r * c2r / (dist * dist - c1r * c1r)) + c2y;
					c3r = Math.abs(c2r * c2r * c1r / (dist * dist - c1r * c1r));

					c3 = new Circle(new MyPoint(c3x, c3y), c3r, c1.paint);

					circles.add(c3);
					newCircles.add(c3);
				}
			}
		}

		nextCircles = newCircles;
	}

	java.util.Random random = null;

	protected Color getColor(double radius) {
		double percent = 1 - (radius / (WIDTH * .30));
		int red = (int) Math.round(255 * percent);
		int green = (int) Math.round(255 * percent);
		int blue = (int) Math.round(255 * percent);

		if (random == null) {
			long seed = System.currentTimeMillis();
			seed = 1355959557249L;
			// System.out.println("color " + seed + "L");
			random = new Random(seed);
		}

		red = (int) Math.round(255 * random.nextDouble());
		green = (int) Math.round(255 * random.nextDouble());
		blue = (int) Math.round(255 * random.nextDouble());

		return new Color(red, green, blue);
	}

	public String toString() {
		return "Circle Inversion";
	}

	public static void main(String[] args) {
		SimpleFractal.main(args);
	}
}

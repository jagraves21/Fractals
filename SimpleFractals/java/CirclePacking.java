import java.awt.Color;
import java.awt.Paint;
import java.awt.RadialGradientPaint;
import java.util.*;
import javax.swing.*;

public class CirclePacking extends SimpleFractal {
	protected double area;
	protected Random random;
	protected double multiplier;
	protected List<FractalShape> circles;

	public CirclePacking() {
		super();

		random = new Random(3L);
		area = 0;
		multiplier = 0.125;
		circles = new LinkedList<FractalShape>();
		createBase();
	}

	public int getSuggestedIterations() {
		return 30;
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
		circles.clear();
		area = 0;
		multiplier = 1.0 / 6.0;

		MyPoint p1 = new MyPoint(0, 0);
		MyPoint p2 = new MyPoint(WIDTH, 0);
		MyPoint p3 = new MyPoint(WIDTH, HEIGHT);
		MyPoint p4 = new MyPoint(0, HEIGHT);

		circles.add(new Rectangle(p1, p2, p3, p4));
	}

	Random r = new Random(3L);

	public Color getColor(double radius) {
		double offset = radius / ((WIDTH < HEIGHT ? WIDTH : HEIGHT) * (1.0 / 6.0));

		int red = (int) (255 * r.nextDouble());
		int green = (int) (255 * r.nextDouble());
		int blue = (int) (255 * r.nextDouble());

		return new Color(red, green, blue);
	}

	public void next() {
		int miss;
		double x;
		double y;
		double radius;
		boolean intersectsFlag;
		Circle circle;
		FractalShape shape;
		Iterator<FractalShape> iter;

		int startingSize = circles.size();
		do {
			miss = 1000000;
			radius = (WIDTH < HEIGHT ? WIDTH : HEIGHT) * multiplier;
			circle = new Circle(new MyPoint(0, 0), radius, getColor(radius), true);

			// System.out.println(multiplier);
			do {
				x = (WIDTH - (2 * radius)) * random.nextDouble() + radius;
				y = (HEIGHT - (2 * radius)) * random.nextDouble() + radius;

				circle.c.x = x;
				circle.c.y = y;

				intersectsFlag = false;
				iter = circles.iterator();
				while (iter.hasNext()) {
					shape = iter.next();

					if (shape instanceof Circle) {
						if (((Circle) shape).intersects(circle)) {
							intersectsFlag = true;
							break;
						}
					}
				}

				if (intersectsFlag) {
					miss--;
				} else {
					area += Math.PI * Math.pow(circle.radius, 2);
					circles.add(circle);
					circle = new Circle(new MyPoint(0, 0), radius, getColor(radius), true);
				}
			} while (miss > 0);

			multiplier *= 0.9;
		} while (startingSize == circles.size());

		// System.out.println((WIDTH*HEIGHT) + " - " + area + " = " + ((WIDTH*HEIGHT)-area));
	}

	public String toString() {
		return "Circle Packing";
	}

	public static void main(String[] args) {
		SimpleFractal.main(args);
	}
}

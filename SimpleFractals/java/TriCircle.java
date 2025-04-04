import java.awt.Color;
import java.awt.Paint;
import java.awt.RadialGradientPaint;
import java.util.*;
import javax.swing.*;

public class TriCircle extends SimpleFractal {
	protected List<Circle> nextCircles;
	protected List<FractalShape> circles;

	public TriCircle() {
		super();
		circles = new LinkedList<FractalShape>();
		nextCircles = new LinkedList<Circle>();
		createBase();
	}

	protected Paint getForeground() {
		MyPoint mid = newMinPoint.midPoint(newMaxPoint);

		float[] dist = {0.20f, 0.30f, 0.40f, 0.50f, 0.60f};
		Color[] colors = {Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN, Color.BLUE};

		RadialGradientPaint paint =
				new RadialGradientPaint(
						(float) mid.x, (float) mid.y, (float) mid.distance(newMinPoint), dist, colors);
		return paint;
	}

	public List<FractalShape> getFractal() {
		return circles;
	}

	public void clearFractal() {
		createBase();
	}

	protected void createBase() {
		MyPoint center = new MyPoint(WIDTH / 2, HEIGHT / 2);
		Circle circle = new Circle(center, WIDTH / 2);

		circles.clear();
		nextCircles.clear();

		circles.add(circle);
		nextCircles.add(circle);
	}

	public void next() {
		double radius;
		MyPoint c;

		MyPoint nc;
		double xOff;
		double yOff;

		Circle circle;

		List<Circle> newCircles = new LinkedList<Circle>();

		Iterator<Circle> iter = nextCircles.iterator();
		while (iter.hasNext()) {
			circle = iter.next();

			c = circle.c;

			radius = circle.radius() / 2;

			nc = new MyPoint(c.x, c.y - radius);
			circle = new Circle(nc, radius);
			circles.add(circle);
			newCircles.add(circle);

			xOff = c30 * radius;
			yOff = s30 * radius;

			nc = new MyPoint(c.x - xOff, c.y + yOff);
			circle = new Circle(nc, radius);
			circles.add(circle);
			newCircles.add(circle);

			nc = new MyPoint(c.x + xOff, c.y + yOff);
			circle = new Circle(nc, radius);
			circles.add(circle);
			newCircles.add(circle);
		}

		nextCircles = newCircles;
	}

	public String toString() {
		return "Tri Circle Fractal";
	}

	public static void main(String[] args) {
		SimpleFractal.main(args);
	}
}

import java.awt.Color;
import java.awt.Paint;
import java.awt.RadialGradientPaint;
import java.util.*;
import javax.swing.*;

public class CircleLimitSet extends SimpleFractal {
	protected List<Circle> basesCircles;
	protected List<Circle> currentCircles;
	protected List<FractalShape> fractal;

	public CircleLimitSet() {
		super();
	}

	public int getSuggestedIterations() {
		return 6;
	}

	public List<FractalShape> getFractal() {
		return fractal;
	}

	public void clearFractal() {
		basesCircles = new LinkedList<Circle>();
		currentCircles = new LinkedList<Circle>();
		fractal = new LinkedList<FractalShape>();
		createBases();
	}

	protected void createBases() {
		basesCircles.clear();
		currentCircles.clear();
		fractal.clear();

		Circle c = new Circle(new MyPoint(WIDTH / 2, HEIGHT / 2), WIDTH / 2, Color.RED);
		Circle c1 = new Circle(new MyPoint(c.c.x, c.c.y - WIDTH - 10), WIDTH / 2, Color.YELLOW);
		Circle c2 = new Circle(
			translate(c.c, c1.c, c.c.distance(c1.c), 1 * r60), WIDTH / 2, new Color(239, 0, 222)
		);
		Circle c3 = new Circle(
			translate(c.c, c1.c, c.c.distance(c1.c), 2 * r60), WIDTH / 2, new Color(115, 0, 247)
		);
		Circle c4 = new Circle(
			translate(c.c, c1.c, c.c.distance(c1.c), 3 * r60), WIDTH / 2, new Color(0, 132, 255)
		);
		Circle c5 = new Circle(
			translate(c.c, c1.c, c.c.distance(c1.c), 4 * r60), WIDTH / 2, new Color(0, 239, 140)
		);
		Circle c6 = new Circle(
			translate(c.c, c1.c, c.c.distance(c1.c), 5 * r60), WIDTH / 2, new Color(0, 231, 0)
		);

		c.c.paint = c.paint;
		c1.c.paint = c1.paint;
		c2.c.paint = c2.paint;
		c3.c.paint = c3.paint;
		c4.c.paint = c4.paint;
		c5.c.paint = c5.paint;
		c6.c.paint = c6.paint;
		
		basesCircles.add(c);
		basesCircles.add(c1);
		basesCircles.add(c2);
		basesCircles.add(c3);
		basesCircles.add(c4);
		basesCircles.add(c5);
		basesCircles.add(c6);

		currentCircles.addAll(basesCircles);
		fractal.addAll(basesCircles);
	}

	public void next() {
		double c1x, c2x, c3x;
		double c1y, c2y, c3y;
		double c1r, c2r, c3r;
		double dist;

		List<Circle> newCircles = new LinkedList<Circle>();
		for (Circle baseCircle : basesCircles) {
			for (Circle c1 : currentCircles) {
				if (c1 != baseCircle && !c1.inside(baseCircle)) {
					c1x = c1.c.x;
					c1y = c1.c.y;
					c2x = baseCircle.c.x;
					c2y = baseCircle.c.y;
					dist = c1.c.distance(baseCircle.c);
					c1r = c1.radius();
					c2r = baseCircle.radius();

					c3x = (c1x - c2x) / dist * (dist * c2r * c2r / (dist * dist - c1r * c1r)) + c2x;
					c3y = (c1y - c2y) / dist * (dist * c2r * c2r / (dist * dist - c1r * c1r)) + c2y;
					c3r = Math.abs(c2r * c2r * c1r / (dist * dist - c1r * c1r));

					Circle c3 = new Circle(new MyPoint(c3x, c3y, baseCircle.paint), c3r, c1.paint);
					fractal.add(c3.c);
					newCircles.add(c3);
				}
			}
		}
		currentCircles = newCircles;
	}

	public String toString() {
		return "Circle Limit Set";
	}

	public static void main(String[] args) {
		SimpleFractal.main(args);
	}
}

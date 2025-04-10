import java.awt.Color;
import java.awt.Paint;
import java.awt.RadialGradientPaint;
import java.util.*;
import javax.swing.*;

public class CircleLimitSetNew extends CircleLimitSet {
	static final double MY_DIST = 50.5548469825328;

	protected Circle movingCircle;

	public CircleLimitSetNew() {
		super();
	}

	public int getSuggestedIterations() {
		return 175;
	}

	public int getSuggestedDelay() {
		return 100;
	}
	
	protected void findMinMax(List<FractalShape> shapes) {
		minPoint.x = -200; // -1654;
		minPoint.y = -200;
			
		maxPoint.x = 1200; // 2654;
		maxPoint.y = 1200;
	}

	protected void createBases() {
		basesCircles.clear();
		currentCircles.clear();
		fractal.clear();

		movingCircle = new Circle(new MyPoint(WIDTH / 2, HEIGHT / 2), MY_DIST, Color.WHITE);
		Circle c1 = new Circle(
			new MyPoint(movingCircle.c.x, movingCircle.c.y - WIDTH - 10), WIDTH, new Color(255, 0, 0)
		);
		Circle c2 = new Circle(
			translate(movingCircle.c, c1.c, movingCircle.c.distance(c1.c), 1 * (2 * Math.PI / 7)),
			c1.radius,
			new Color(255, 127, 0)
		);
		c1.radius = c1.c.distance(c2.c);
		c2.radius = c1.radius;
		Circle c3 = new Circle(
			translate(movingCircle.c, c1.c, movingCircle.c.distance(c1.c), 2 * (2 * Math.PI / 7)),
			c1.radius,
			new Color(255, 255, 0)
		);
		Circle c4 =	new Circle(
			translate(movingCircle.c, c1.c, movingCircle.c.distance(c1.c), 3 * (2 * Math.PI / 7)),
			c1.radius,
			new Color(0, 255, 0)
		);
		Circle c5 = new Circle(
			translate(movingCircle.c, c1.c, movingCircle.c.distance(c1.c), 4 * (2 * Math.PI / 7)),
			c1.radius,
			new Color(0, 0, 255)
		);
		Circle c6 = new Circle(
			translate(movingCircle.c, c1.c, movingCircle.c.distance(c1.c), 5 * (2 * Math.PI / 7)),
			c1.radius,
			new Color(75, 0, 130)
		);
		Circle c7 = new Circle(
			translate(movingCircle.c, c1.c, movingCircle.c.distance(c1.c), 6 * (2 * Math.PI / 7)),
			c1.radius,
			new Color(143, 0, 255)
		);

		movingCircle.c.paint = movingCircle.paint;
		c1.c.paint = c1.paint;
		c2.c.paint = c2.paint;
		c3.c.paint = c3.paint;
		c4.c.paint = c4.paint;
		c5.c.paint = c5.paint;
		c6.c.paint = c6.paint;
		c7.c.paint = c7.paint;
		
		basesCircles.add(movingCircle);
		basesCircles.add(c1);
		basesCircles.add(c2);
		basesCircles.add(c3);
		basesCircles.add(c4);
		basesCircles.add(c5);
		basesCircles.add(c6);
		basesCircles.add(c7);

		next();
	}

	public void next() {
		movingCircle.radius += 5;

		currentCircles.clear();
		currentCircles.addAll(basesCircles);

		fractal.clear();
		for (int ii=0; ii < 4; ii++) {
			next2();
		}
	}

	public void next2() {
		double c1x, c2x, c3x;
		double c1y, c2y, c3y;
		double c1r, c2r, c3r;
		double dist;

		Circle c3;

		List<Circle> newCircles = new LinkedList<Circle>();
		for (Circle c2 : basesCircles) {
			for (Circle c1 : currentCircles) {
				if (c1 != c2 && !c1.inside(c2)) {
					c1x = c1.c.x;
					c1y = c1.c.y;
					c2x = c2.c.x;
					c2y = c2.c.y;
					dist = c1.c.distance(c2.c);
					c1r = c1.radius;
					c2r = c2.radius;

					c3x = (c1x - c2x) / dist * (dist * c2r * c2r / (dist * dist - c1r * c1r)) + c2x;
					c3y = (c1y - c2y) / dist * (dist * c2r * c2r / (dist * dist - c1r * c1r)) + c2y;
					c3r = Math.abs(c2r * c2r * c1r / (dist * dist - c1r * c1r));

					c3 = new Circle(new MyPoint(c3x, c3y, c2.paint), c3r, c1.paint);

					double error = 1000;
					if (c1.c.distance(c3.c) < error || c2.c.distance(c3.c) < error) {
						fractal.add(c3.c);
						newCircles.add(c3);
					}
				}
			}
		}
		currentCircles = newCircles;
	}
		
	public String toString() {
		return "Circle Limit Set New";
	}

	public static void main(String[] args) {
		SimpleFractal.main(args);
	}
}

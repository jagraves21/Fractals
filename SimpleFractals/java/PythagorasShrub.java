import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Paint;
import java.awt.Point;
import java.util.*;
import javax.swing.*;

public class PythagorasShrub extends SimpleFractal {
	protected final double angle1 = r30;
	protected final double sinAngle1 = s30;

	protected final double angle2 = r60;
	protected final double sinAngle2 = s60;

	protected final double square_len = WIDTH / 7;

	protected final double threshold =
			sinAngle1 * (sinAngle1 * Math.sqrt(Math.pow(square_len, 2)) / s45) / s45;

	protected final Color brown = new Color(160, 82, 45);
	protected final Color green = new Color(0, 100, 0);

	protected List<FractalShape> rectangles;
	protected List<Rectangle> nextRectangles;

	public PythagorasShrub() {
		super();
		rectangles = new LinkedList<FractalShape>();
		nextRectangles = new LinkedList<Rectangle>();
		createBase();
	}

	protected Paint getForeground() {
		Color c1 = Color.MAGENTA;
		Color c2 = Color.RED;

		Point p1 = new Point((int) newMinPoint.x, (int) newMinPoint.y);
		Point p2 = new Point((int) newMaxPoint.x, (int) newMaxPoint.y);
		GradientPaint paint = new GradientPaint(p1, c1, p2, c2);

		return paint;
	}

	public List<FractalShape> getFractal() {
		return rectangles;
	}

	public void clearFractal() {
		createBase();
	}

	protected void createBase() {
		MyPoint p1 =
				new MyPoint((WIDTH * (2.0 / 3.0)) - (square_len / 2), (HEIGHT * (3.0 / 4.0)) - square_len);
		MyPoint p2 =
				new MyPoint((WIDTH * (2.0 / 3.0)) + (square_len / 2), (HEIGHT * (3.0 / 4.0)) - square_len);
		MyPoint p3 = new MyPoint((WIDTH * (2.0 / 3.0)) + (square_len / 2), (HEIGHT * (3.0 / 4.0)));
		MyPoint p4 = new MyPoint((WIDTH * (2.0 / 3.0)) - (square_len / 2), (HEIGHT * (3.0 / 4.0)));

		rectangles.clear();
		nextRectangles.clear();

		Rectangle rect = new Rectangle(p1, p2, p3, p4, brown);
		rectangles.add(rect);
		nextRectangles.add(rect);

		p1 = new MyPoint(0, 0);
		p2 = new MyPoint(WIDTH, HEIGHT);
		rectangles.add(p1);
		rectangles.add(p2);
	}

	public void next() {
		MyPoint p1;
		MyPoint p2;
		MyPoint p3;
		MyPoint p4;
		MyPoint np1;
		MyPoint np2;
		MyPoint np3;
		MyPoint np4;

		double radius;

		Rectangle rect;

		Color color;

		List<Rectangle> newRectangles = new LinkedList<Rectangle>();

		Iterator<Rectangle> iter = nextRectangles.iterator();
		while (iter.hasNext()) {
			rect = iter.next();
			p1 = rect.p1;
			p2 = rect.p2;

			np4 = p1;

			radius = sinAngle2 * p1.distance(p2);
			np3 = translate(p1, p2, radius, angle1);

			radius = radius / s45;
			np1 = translate(np3, np4, radius, -r45);
			np2 = translate(np4, np3, radius, r45);

			if (np2.distance(np4) < threshold) {
				color = green;
			} else {
				color = brown;
			}

			rect = new Rectangle(np1, np2, np3, np4, color);
			rectangles.add(rect);
			newRectangles.add(rect);

			radius = sinAngle1 * p1.distance(p2) / s45;
			np4 = np3;
			np3 = p2;
			np1 = translate(np3, np4, radius, -r45);
			np2 = translate(np4, np3, radius, r45);

			rect = new Rectangle(np1, np2, np3, np4, color);
			rectangles.add(rect);
			newRectangles.add(rect);
		}

		nextRectangles = newRectangles;
	}

	public String toString() {
		return "Pythagoras Shrub";
	}

	public static void main(String[] args) {
		SimpleFractal.main(args);
	}
}

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Paint;
import java.awt.Point;
import java.util.*;
import javax.swing.*;

public class PythagorasTree extends SimpleFractal {
	protected final double angle1 = r45;
	protected final double sinAngle1 = s45;

	protected final double angle2 = r45;
	protected final double sinAngle2 = s45;

	protected final Color brown = new Color(160, 82, 45);
	protected final Color green = new Color(0, 100, 0);

	protected int curIteration;

	protected List<FractalShape> rectangles;
	protected List<Rectangle> nextRectangles;

	public PythagorasTree() {
		super();
		curIteration = 0;
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
		double ratio = 1.0 / 6.0;
		curIteration = 0;

		MyPoint p1 = new MyPoint(WIDTH / 2 - (WIDTH * (ratio / 2)), HEIGHT - (HEIGHT * ratio));
		MyPoint p2 = new MyPoint(WIDTH / 2 + (WIDTH * (ratio / 2)), HEIGHT - (HEIGHT * ratio));
		MyPoint p3 = new MyPoint(WIDTH / 2 + (WIDTH * (ratio / 2)), HEIGHT);
		MyPoint p4 = new MyPoint(WIDTH / 2 - (WIDTH * (ratio / 2)), HEIGHT);

		/*MyPoint p1 = new MyPoint((WIDTH*(2.0/5.0)), 0);
		MyPoint p2 = new MyPoint(WIDTH - (WIDTH*(2.0/5.0)), 0);
		MyPoint p3 = new MyPoint(WIDTH - (WIDTH*(2.0/5.0)), HEIGHT);
		MyPoint p4 = new MyPoint((WIDTH*(2.0/5.0)), HEIGHT);*/

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
		curIteration++;

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

			if (curIteration > 4) {
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
		return "Pythagoras Tree";
	}

	public static void main(String[] args) {
		SimpleFractal.main(args);
	}
}

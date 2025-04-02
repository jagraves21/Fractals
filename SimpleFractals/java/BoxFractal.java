import java.awt.Color;
import java.awt.Paint;
import java.awt.RadialGradientPaint;
import java.util.*;
import javax.swing.*;

public class BoxFractal extends SimpleFractal {
	protected List<FractalShape> rectangles;

	public BoxFractal() {
		super();
		rectangles = new LinkedList<FractalShape>();
		createBase();
	}

	public int getSuggestedIterations() {
		return 5;
	}

	protected Paint getForeground() {
		MyPoint mid = newMinPoint.midPoint(newMaxPoint);

		float[] dist = {0.0f, 1.0f};
		Color[] colors = {Color.GREEN, Color.BLUE};

		RadialGradientPaint paint =
				new RadialGradientPaint(
						(float) mid.x, (float) mid.y, (float) mid.distance(newMaxPoint), dist, colors);
		return paint;
	}

	public List<FractalShape> getFractal() {
		return rectangles;
	}

	public void clearFractal() {
		createBase();
	}

	protected void createBase() {
		MyPoint p1 = new MyPoint(0, 0);
		MyPoint p2 = new MyPoint(WIDTH, 0);
		MyPoint p3 = new MyPoint(WIDTH, HEIGHT);
		MyPoint p4 = new MyPoint(0, HEIGHT);

		rectangles.clear();

		Rectangle rect = new Rectangle(p1, p2, p3, p4);

		rectangles.add(rect);
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
		MyPoint np5;
		MyPoint np6;
		MyPoint np7;
		MyPoint np8;
		MyPoint np9;
		MyPoint np10;
		MyPoint np11;
		MyPoint np12;
		MyPoint np13;
		MyPoint np14;
		MyPoint np15;
		MyPoint np16;
		Rectangle rect;

		List<FractalShape> newRectangles = new LinkedList<FractalShape>();

		Iterator<FractalShape> iter = rectangles.iterator();
		while (iter.hasNext()) {
			rect = (Rectangle) iter.next();
			p1 = rect.p1;
			p2 = rect.p2;
			p3 = rect.p3;
			p4 = rect.p4;

			np1 = p1;
			np2 = p1.oneThirdsPoint(p2);
			np4 = p1.oneThirdsPoint(p4);
			np3 = new MyPoint(np2.x, np4.y);

			np5 = p1.twoThirdsPoint(p2);
			np6 = p2;
			np7 = p2.oneThirdsPoint(p3);
			np8 = new MyPoint(np5.x, np7.y);

			np10 = p2.twoThirdsPoint(p3);
			np11 = p3;
			np12 = p3.oneThirdsPoint(p4);
			np9 = new MyPoint(np12.x, np10.y);

			np13 = p4.oneThirdsPoint(p1);
			np15 = p3.twoThirdsPoint(p4);
			np16 = p4;
			np14 = new MyPoint(np15.x, np13.y);

			rect = new Rectangle(np1, np2, np3, np4);
			newRectangles.add(rect);

			rect = new Rectangle(np5, np6, np7, np8);
			newRectangles.add(rect);

			rect = new Rectangle(np9, np10, np11, np12);
			newRectangles.add(rect);

			rect = new Rectangle(np13, np14, np15, np16);
			newRectangles.add(rect);

			rect = new Rectangle(np3, np8, np9, np14);
			newRectangles.add(rect);
		}

		rectangles = newRectangles;
	}

	public String toString() {
		return "Box Fractal";
	}

	public static void main(String[] args) {
		SimpleFractal.main(args);
	}
}

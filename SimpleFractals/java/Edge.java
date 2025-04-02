import java.awt.Color;
import java.awt.Paint;
import java.awt.RadialGradientPaint;
import java.util.*;
import javax.swing.*;

public class Edge extends SimpleFractal {
	protected List<FractalShape> squares;
	protected List<Rectangle> nextSquares;
	protected int iteration;

	public Edge() {
		super();
		squares = new LinkedList<FractalShape>();
		nextSquares = new LinkedList<Rectangle>();
		createBase();
	}

	public int getSuggestedIterations() {
		return 7;
	}

	protected Paint getForeground() {
		MyPoint min = new MyPoint(newMinPoint.x, newMaxPoint.y);
		MyPoint max = new MyPoint(newMaxPoint.x, newMinPoint.y);

		float[] dist = {0.0f, 0.2f, 0.4f, 0.6f, 0.8f, 1.0f};
		Color[] colors = {Color.RED, Color.BLUE, Color.GREEN, Color.RED, Color.BLUE, Color.GREEN};

		RadialGradientPaint paint =
				new RadialGradientPaint(
						(float) min.x, (float) min.y, (float) min.distance(max), dist, colors);
		return paint;
	}

	public List<FractalShape> getFractal() {
		return squares;
	}

	public void clearFractal() {
		createBase();
	}

	protected void createBase() {
		MyPoint p1 = new MyPoint(0, 0);
		MyPoint p2 = new MyPoint(WIDTH, 0);
		MyPoint p3 = new MyPoint(WIDTH, HEIGHT);
		MyPoint p4 = new MyPoint(0, HEIGHT);

		squares.clear();
		nextSquares.clear();

		Rectangle square = new Rectangle(p1, p2, p3, p4, null, true);
		squares.add(square);
		nextSquares.add(square);
	}

	public void next() {
		double dist;
		MyPoint p1;
		MyPoint p2;
		MyPoint p3;
		MyPoint p4;
		MyPoint np1;
		MyPoint np2;
		MyPoint np3;
		MyPoint np4;
		Rectangle rect;

		List<Rectangle> newSquares = new LinkedList<Rectangle>();

		Iterator<Rectangle> iter = nextSquares.iterator();
		while (iter.hasNext()) {
			rect = iter.next();

			p1 = rect.p1;
			p2 = rect.p2;
			p3 = rect.p3;
			p4 = rect.p4;

			dist = p1.distance(p2) / 2;

			np4 = p1;
			np3 = new MyPoint(np4.x + dist, np4.y);
			np2 = new MyPoint(np3.x, np3.y - dist);
			np1 = new MyPoint(np4.x, np2.y);
			rect = new Rectangle(np1, np2, np3, np4, null, true);
			squares.add(rect);
			newSquares.add(rect);

			np4 = p2;
			np3 = new MyPoint(np4.x + dist, np4.y);
			np2 = new MyPoint(np3.x, np3.y - dist);
			np1 = new MyPoint(np4.x, np2.y);
			rect = new Rectangle(np1, np2, np3, np4, null, true);
			squares.add(rect);
			newSquares.add(rect);

			np4 = p3;
			np3 = new MyPoint(np4.x + dist, np4.y);
			np2 = new MyPoint(np3.x, np3.y - dist);
			np1 = new MyPoint(np4.x, np2.y);
			rect = new Rectangle(np1, np2, np3, np4, null, true);
			squares.add(rect);
			newSquares.add(rect);
		}

		nextSquares = newSquares;
	}

	public String toString() {
		return "Edge";
	}

	public static void main(String[] args) {
		SimpleFractal.main(args);
	}
}

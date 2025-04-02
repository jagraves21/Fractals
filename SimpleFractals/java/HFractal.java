import java.awt.Color;
import java.awt.Paint;
import java.awt.RadialGradientPaint;
import java.util.*;
import javax.swing.*;

public class HFractal extends SimpleFractal {
	protected List<FractalShape> lines;
	protected List<LineSegment> nextLines;

	public HFractal() {
		super();
		lines = new LinkedList<FractalShape>();
		nextLines = new LinkedList<LineSegment>();
		createBase();
	}

	public int getSuggestedIterations() {
		return 9;
	}

	protected Paint getForeground() {
		MyPoint mid = newMinPoint.midPoint(newMaxPoint);

		float[] dist = {0.75f, 1.0f};
		Color[] colors = {Color.RED, Color.BLACK};

		RadialGradientPaint paint =
				new RadialGradientPaint(
						(float) mid.x, (float) mid.y, (float) mid.distance(newMinPoint), dist, colors);
		return paint;
	}

	public List<FractalShape> getFractal() {
		return lines;
	}

	public void clearFractal() {
		createBase();
	}

	protected void createBase() {
		MyPoint p1 = new MyPoint(0, 0);
		MyPoint p2 = new MyPoint(WIDTH, 0);
		MyPoint p3 = new MyPoint(WIDTH, HEIGHT);
		MyPoint p4 = new MyPoint(0, HEIGHT);

		lines.clear();
		nextLines.clear();

		lines.add(new Rectangle(p1, p2, p3, p4));

		p1 = new MyPoint(WIDTH / 4, HEIGHT / 4);
		p2 = new MyPoint(WIDTH / 4, HEIGHT - HEIGHT / 4);
		LineSegment l1 = new LineSegment(p1, p2);
		lines.add(l1);
		nextLines.add(l1);

		p3 = new MyPoint(WIDTH - WIDTH / 4, HEIGHT / 4);
		p4 = new MyPoint(WIDTH - WIDTH / 4, HEIGHT - HEIGHT / 4);
		LineSegment l2 = new LineSegment(p3, p4);
		lines.add(l2);
		nextLines.add(l2);

		lines.add(new LineSegment(p1.midPoint(p2), p3.midPoint(p4)));
	}

	public void next() {
		double dist;
		MyPoint p1;
		MyPoint p2;
		MyPoint np1;
		MyPoint np2;
		MyPoint np3;
		MyPoint np4;
		LineSegment line;

		List<LineSegment> newLines = new LinkedList<LineSegment>();

		Iterator<LineSegment> iter = nextLines.iterator();
		while (iter.hasNext()) {
			line = iter.next();
			p1 = line.p1;
			p2 = line.p2;

			dist = p1.distance(p2) / 3.30;

			if (p1.x == p2.x) {
				np1 = new MyPoint(p1.x - dist, p1.y);
				np2 = new MyPoint(p1.x + dist, p1.y);

				np3 = new MyPoint(p1.x - dist, p2.y);
				np4 = new MyPoint(p1.x + dist, p2.y);
			} else {
				np1 = new MyPoint(p1.x, p1.y + dist);
				np2 = new MyPoint(p1.x, p1.y - dist);

				np3 = new MyPoint(p2.x, p1.y + dist);
				np4 = new MyPoint(p2.x, p1.y - dist);
			}

			newLines.add(new LineSegment(np1, np2));
			newLines.add(new LineSegment(np3, np4));
		}

		lines.addAll(newLines);
		nextLines = newLines;
	}

	public String toString() {
		return "H-Fractal";
	}

	public static void main(String[] args) {
		SimpleFractal.main(args);
	}
}

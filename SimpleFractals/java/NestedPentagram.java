import java.awt.Color;
import java.awt.Paint;
import java.awt.RadialGradientPaint;
import java.util.*;
import javax.swing.*;

public class NestedPentagram extends SimpleFractal {
	protected List<FractalShape> lines;
	protected LineSegment[] next;

	public NestedPentagram() {
		super();
		lines = new LinkedList<FractalShape>();
		createBase();
	}

	public int getSuggestedIterations() {
		return 5;
	}

	protected Paint getForeground() {
		MyPoint mid = newMinPoint.midPoint(newMaxPoint);

		mid.y += ((newMaxPoint.y - newMinPoint.y) / 2.0) * (1.0 / 10.0);

		float[] dist = {0.0f, 0.50f, 0.6f};
		Color[] colors = {Color.RED, Color.BLUE, Color.GREEN};

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
		// a/sinA = b/sinB = c/sinC
		double c54 = Math.cos(54 * (Math.PI / 180));
		double s36 = Math.sin(36 * (Math.PI / 180));
		double s54 = Math.sin(54 * (Math.PI / 180));
		double s72 = Math.sin(72 * (Math.PI / 180));
		double t72 = Math.tan(72 * (Math.PI / 180));
		double xOff;
		double yOff;

		MyPoint p1 = new MyPoint(WIDTH / 2, 0);

		xOff = HEIGHT / t72;
		MyPoint p3 = new MyPoint(p1.x + xOff, HEIGHT);
		MyPoint p4 = new MyPoint(p1.x - xOff, HEIGHT);

		double dist = p1.distance(p3);
		double hyp = (dist / s72) * s36;

		// xOff = s54 * hyp - 25;  // -10 beause of round off error
		yOff = c54 * hyp;

		MyPoint p2 = new MyPoint(WIDTH, p1.y + yOff);
		MyPoint p5 = new MyPoint(0, p1.y + yOff);

		LineSegment l1 = new LineSegment(p1, p2);
		LineSegment l2 = new LineSegment(p2, p3);
		LineSegment l3 = new LineSegment(p3, p4);
		LineSegment l4 = new LineSegment(p4, p5);
		LineSegment l5 = new LineSegment(p5, p1);

		lines.clear();
		lines.add(l1);
		lines.add(l2);
		lines.add(l3);
		lines.add(l4);
		lines.add(l5);

		next = new LineSegment[5];
		next[0] = new LineSegment(p1, p3);
		next[1] = new LineSegment(p2, p4);
		next[2] = new LineSegment(p3, p5);
		next[3] = new LineSegment(p4, p1);
		next[4] = new LineSegment(p5, p2);

		p1 = new MyPoint(0, 0);
		p2 = new MyPoint(WIDTH, HEIGHT);

		lines.add(p1);
		lines.add(p2);
	}

	public void next() {
		LineSegment l1 = next[0];
		LineSegment l2 = next[1];
		LineSegment l3 = next[2];
		LineSegment l4 = next[3];
		LineSegment l5 = next[4];

		lines.add(l1);
		lines.add(l2);
		lines.add(l3);
		lines.add(l4);
		lines.add(l5);

		MyPoint p1 = l2.intersect(l3);
		MyPoint p2 = l3.intersect(l4);
		MyPoint p3 = l4.intersect(l5);
		MyPoint p4 = l1.intersect(l5);
		MyPoint p5 = l1.intersect(l2);

		LineSegment nl1 = new LineSegment(p1, p3);
		LineSegment nl2 = new LineSegment(p2, p4);
		LineSegment nl3 = new LineSegment(p3, p5);
		LineSegment nl4 = new LineSegment(p4, p1);
		LineSegment nl5 = new LineSegment(p5, p2);

		next[0] = nl1;
		next[1] = nl2;
		next[2] = nl3;
		next[3] = nl4;
		next[4] = nl5;
	}

	public String toString() {
		return "Nested Pentagram";
	}

	public static void main(String[] args) {
		SimpleFractal.main(args);
	}
}

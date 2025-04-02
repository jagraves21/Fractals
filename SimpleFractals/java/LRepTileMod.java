import java.awt.Color;
import java.awt.Paint;
import java.awt.RadialGradientPaint;
import java.util.*;
import javax.swing.*;

public class LRepTileMod extends SimpleFractal {
	protected static final Color[] colors = {
		Color.RED, Color.YELLOW, Color.GREEN, Color.BLUE, Color.MAGENTA
	};

	protected List<FractalShape> lines;
	protected List<MyPoint[]> ells;
	int iteration;

	public LRepTileMod() {
		super();
		lines = new Stack<FractalShape>();
		ells = new LinkedList<MyPoint[]>();
		iteration = 0;
		createBase();
	}

	public int getSuggestedIterations() {
		return 5;
	}

	protected Paint getForeground() {
		MyPoint mid = newMinPoint.midPoint(newMaxPoint);

		Color c1 = new Color(0, 255, 255);
		Color c2 = new Color(0, 0, 139);

		float[] dist = {0.0f, 0.25f, 0.50f, 0.75f, 1.0f};
		Color[] colors = {c2, c1, c2, c1, c2};

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
		MyPoint p2 = new MyPoint(WIDTH / 4, HEIGHT / 4);
		MyPoint p3 = new MyPoint(WIDTH / 3, HEIGHT - HEIGHT / 3);
		MyPoint p4 = new MyPoint(WIDTH - WIDTH / 4, HEIGHT - HEIGHT / 4);
		MyPoint p5 = new MyPoint(WIDTH, HEIGHT);
		MyPoint p6 = new MyPoint(0, HEIGHT);

		lines.clear();
		lines.add(new LineSegment(p1, p2, Color.WHITE));
		lines.add(new LineSegment(p2, p3, Color.WHITE));
		lines.add(new LineSegment(p3, p4, Color.WHITE));
		lines.add(new LineSegment(p4, p5, Color.WHITE));
		lines.add(new LineSegment(p5, p6, Color.WHITE));
		lines.add(new LineSegment(p6, p1, Color.WHITE));

		ells.clear();
		MyPoint[] ell = new MyPoint[6];
		ell[0] = p1;
		ell[1] = p2;
		ell[2] = p3;
		ell[3] = p4;
		ell[4] = p5;
		ell[5] = p6;
		ells.add(ell);

		iteration = 0;
	}

	public void next() {
		MyPoint p1;
		MyPoint p2;
		MyPoint p3;
		MyPoint p4;
		MyPoint p5;
		MyPoint p6;
		MyPoint n1p1;
		MyPoint n1p2;
		MyPoint n1p3;
		MyPoint n1p4;
		MyPoint n1p5;
		MyPoint n1p6;
		MyPoint n2p1;
		MyPoint n2p2;
		MyPoint n2p3;
		MyPoint n2p4;
		MyPoint n2p5;
		MyPoint n2p6;
		MyPoint n3p1;
		MyPoint n3p2;
		MyPoint n3p3;
		MyPoint n3p4;
		MyPoint n3p5;
		MyPoint n3p6;
		MyPoint n4p1;
		MyPoint n4p2;
		MyPoint n4p3;
		MyPoint n4p4;
		MyPoint n4p5;
		MyPoint n4p6;
		MyPoint[] ell;
		Color color = colors[iteration++ % colors.length];

		List<MyPoint[]> newElls = new LinkedList<MyPoint[]>();

		Iterator<MyPoint[]> iter = ells.iterator();
		while (iter.hasNext()) {
			ell = iter.next();

			p1 = ell[0];
			p2 = ell[1];
			p3 = ell[2];
			p4 = ell[3];
			p5 = ell[4];
			p6 = ell[5];

			n1p1 = p2;
			n1p2 = p2.midPoint(p3);
			n1p5 = p1.midPoint(p6);
			n1p3 = p1.midPoint(p3);
			n1p4 = n1p5.midPoint(p3);
			n1p6 = p1;

			n2p1 = n1p3;
			n2p2 = n1p2;
			n2p3 = p3;
			n2p4 = p3.midPoint(p4);
			n2p5 = p3.midPoint(p5);
			n2p6 = p3.midPoint(p6);

			n3p1 = p5.midPoint(p6);
			n3p2 = p3.midPoint(n3p1);
			n3p3 = n2p5;
			n3p4 = n2p4;
			n3p5 = p4;
			n3p6 = p5;

			n4p1 = n1p5;
			n4p2 = n1p4;
			n4p3 = p3.midPoint(p6);
			n4p4 = n3p2;
			n4p5 = n3p1;
			n4p6 = p6;

			// lines.add(new LineSegment(n1p1, n1p2, color));
			lines.add(new LineSegment(n1p2, n1p3, color));
			lines.add(new LineSegment(n1p3, n1p4, color));
			lines.add(new LineSegment(n1p4, n1p5, color));
			// lines.add(new LineSegment(n1p5, n1p6, color));
			// lines.add(new LineSegment(n1p6, n1p1, color));
			ell = new MyPoint[6];
			ell[0] = n1p1;
			ell[1] = n1p2;
			ell[2] = n1p3;
			ell[3] = n1p4;
			ell[4] = n1p5;
			ell[5] = n1p6;
			newElls.add(ell);

			lines.add(new LineSegment(n2p1, n2p2, color));
			// lines.add(new LineSegment(n2p2, n2p3, color));
			// lines.add(new LineSegment(n2p3, n2p4, color));
			lines.add(new LineSegment(n2p4, n2p5, color));
			lines.add(new LineSegment(n2p5, n2p6, color));
			lines.add(new LineSegment(n2p6, n2p1, color));
			ell = new MyPoint[6];
			ell[0] = n2p1;
			ell[1] = n2p2;
			ell[2] = n2p3;
			ell[3] = n2p4;
			ell[4] = n2p5;
			ell[5] = n2p6;
			newElls.add(ell);

			lines.add(new LineSegment(n3p1, n3p2, color));
			lines.add(new LineSegment(n3p2, n3p3, color));
			lines.add(new LineSegment(n3p3, n3p4, color));
			// lines.add(new LineSegment(n3p4, n3p5, color));
			// lines.add(new LineSegment(n3p5, n3p6, color));
			// lines.add(new LineSegment(n3p6, n3p1, color));
			ell = new MyPoint[6];
			ell[0] = n3p1;
			ell[1] = n3p2;
			ell[2] = n3p3;
			ell[3] = n3p4;
			ell[4] = n3p5;
			ell[5] = n3p6;
			newElls.add(ell);

			lines.add(new LineSegment(n4p1, n4p2, color));
			lines.add(new LineSegment(n4p2, n4p3, color));
			lines.add(new LineSegment(n4p3, n4p4, color));
			lines.add(new LineSegment(n4p4, n4p5, color));
			// lines.add(new LineSegment(n4p5, n4p6, color));
			// lines.add(new LineSegment(n4p6, n4p1, color));
			ell = new MyPoint[6];
			ell[0] = n4p1;
			ell[1] = n4p2;
			ell[2] = n4p3;
			ell[3] = n4p4;
			ell[4] = n4p5;
			ell[5] = n4p6;
			newElls.add(ell);
		}

		ells = newElls;
	}

	public String toString() {
		return "L Rep Tile Mod";
	}

	public static void main(String[] args) {
		SimpleFractal.main(args);
	}
}

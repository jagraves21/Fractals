import java.awt.Color;
import java.awt.Paint;
import java.util.*;
import javax.swing.*;

public class TreeBlue extends SimpleFractal {
	public static Random gen = new Random(10);

	public static final int R_INIT = 255;
	public static final int G_INIT = 255;
	public static final int B_INIT = 255;

	public static final int R_DELTA = -50;
	public static final int G_DELTA = -20;
	public static final int B_DELTA = 20;

	/*public static final int R_INIT = 34;
	public static final int G_INIT = 139;
	public static final int B_INIT = 34;

	public static final int R_DELTA = 22;
	public static final int G_DELTA = -11;
	public static final int B_DELTA = 11;*/

	protected List<FractalShape> lines;
	protected List<LineSegment> nextLines;

	public TreeBlue() {
		super();
		lines = new LinkedList<FractalShape>();
		nextLines = new LinkedList<LineSegment>();
		createBase();
	}

	protected Paint getForeground() {
		return Color.WHITE;
	}

	public List<FractalShape> getFractal() {
		return lines;
	}

	public void clearFractal() {
		createBase();
	}

	protected void createBase() {
		MyPoint p1 = new MyPoint(WIDTH / 2, HEIGHT - HEIGHT / 5);
		MyPoint p2 = new MyPoint(WIDTH / 2, HEIGHT);

		lines.clear();
		nextLines.clear();

		LineSegment line = new LineSegment(p2, p1, getColor());
		lines.add(line);
		nextLines.add(line);

		p1 = new MyPoint(0, 0);
		p2 = new MyPoint(WIDTH, HEIGHT);

		lines.add(p1);
		lines.add(p2);
	}

	protected Color getColor() {
		int count = (int) Math.sqrt(lines.size());

		int red = R_INIT + (count * R_DELTA);
		int green = G_INIT + (count * G_DELTA);
		int blue = B_INIT + (count * B_DELTA);

		if (red < 0) {
			red = 0;
		} else if (red > 255) {
			red = 255;
		}

		if (green < 0) {
			green = 0;
		} else if (green > 255) {
			green = 255;
		}

		if (blue < 0) {
			blue = 0;
		} else if (blue > 255) {
			blue = 255;
		}

		return new Color(red, green, blue);
	}

	public void next() {
		double dist;
		double angle1;
		double angle2;
		MyPoint p1;
		MyPoint p2;
		MyPoint np1;
		MyPoint np2;
		LineSegment line;

		Color color = getColor();

		List<LineSegment> newLines = new LinkedList<LineSegment>();

		Iterator<LineSegment> iter = nextLines.iterator();
		while (iter.hasNext()) {
			angle1 = r5 + ((r20 - r5) * gen.nextDouble());
			angle2 = r5 + ((r20 - r5) * gen.nextDouble());

			line = iter.next();
			p1 = line.p1;
			p2 = line.p2;

			dist = p1.distance(p2) * 10 / 6;

			np1 = p1.rotate(p2, dist, angle1);
			np2 = p1.rotate(p2, dist, -angle2);

			line = new LineSegment(p2, np1, color);
			lines.add(line);
			newLines.add(line);

			line = new LineSegment(p2, np2, color);
			lines.add(line);
			newLines.add(line);
		}

		nextLines = newLines;
	}

	public String toString() {
		return "Tree Blue";
	}

	public static void main(String[] args) {
		SimpleFractal.main(args);
	}
}

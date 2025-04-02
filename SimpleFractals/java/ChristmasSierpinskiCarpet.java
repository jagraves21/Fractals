import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Paint;
import java.awt.Point;
import java.util.*;
import javax.swing.*;

public class ChristmasSierpinskiCarpet extends SimpleFractal {
	public static final Color BROWN1 = new Color(92, 51, 23);
	public static final Color BROWN2 = new Color(139, 69, 19);
	protected List<Rectangle> nextRectangles;
	protected List<FractalShape> rectangles;

	public ChristmasSierpinskiCarpet() {
		super();
		nextRectangles = new LinkedList<Rectangle>();
		rectangles = new LinkedList<FractalShape>();
		createBase();
	}

	public int getSuggestedIterations() {
		return 3;
	}

	protected Paint getForeground() {
		Color c1 = Color.MAGENTA;
		Color c2 = Color.RED;

		Point p1 = new Point((int) newMinPoint.x, (int) newMinPoint.y);
		Point p2 = new Point((int) newMaxPoint.x, (int) newMaxPoint.y);
		GradientPaint paint = new GradientPaint(p1, c1, p2, c2);

		return Color.BLACK;
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

		Rectangle rect = new Rectangle(p1, p2, p3, p4, BROWN1, true);

		rectangles.clear();
		nextRectangles.clear();

		rectangles.add(rect);
		nextRectangles.add(rect);

		for (int ii = 0; ii < getSuggestedIterations(); ii++) {
			myNext();
		}
	}

	protected void myNext() {
		int ii;
		int jj;

		MyPoint p1;
		MyPoint p2;
		MyPoint p3;
		MyPoint p4;

		MyPoint np1;
		MyPoint np2;
		MyPoint np3;
		MyPoint np4;

		double width;
		double height;

		Rectangle rect;

		List<Rectangle> newRectangles = new LinkedList<Rectangle>();

		Iterator<Rectangle> iter = nextRectangles.iterator();
		while (iter.hasNext()) {
			rect = iter.next();

			p1 = rect.p1;
			p2 = rect.p2;
			p3 = rect.p3;
			p4 = rect.p4;

			width = p1.distance(p2) / 3;
			height = p1.distance(p4) / 3;

			for (ii = 0; ii < 3; ii++) {
				for (jj = 0; jj < 3; jj++) {
					np1 = new MyPoint(p1.x + (width * ii), p1.y + (height * jj));
					np2 = new MyPoint(np1.x + width, np1.y);
					np3 = new MyPoint(np1.x + width, np1.y + height);
					np4 = new MyPoint(np1.x, np1.y + height);

					rect = new Rectangle(np1, np2, np3, np4, BROWN2, true);

					if ((ii == 1) && (jj == 1)) {
						rectangles.add(rect);
					} else {
						newRectangles.add(rect);
					}
				}
			}
		}

		nextRectangles = newRectangles;
	}

	public void next() {
		/*FractalShape shape;
		Rectangle rectangle;
		Iterator<FractalShape> iter = rectangles.iterator();
		while(iter.hasNext())
		{
			shape = iter.next();

			if(shape instanceof Rectangle)
			{
				rectangle = (Rectangle)shape;
				if(rectangle.paint == BROWN1)
				{
					rectangle.paint = BROWN2;
				} else {
					rectangle.paint = BROWN1;
				}
			}
		}*/
	}

	public String toString() {
		return "Christmas Sierpinski Carpet";
	}

	public static void main(String[] args) {
		SimpleFractal.main(args);
	}
}

import java.awt.Color;
import java.awt.Paint;
import java.awt.RadialGradientPaint;
import java.util.*;
import javax.swing.*;

public class ChristmasSierpinskiTriangle extends SimpleFractal {
	public static final Color GREEN1 = new Color(0, 100, 0);
	public static final Color GREEN2 = new Color(0, 200, 0);

	protected List<FractalShape> triangles;
	protected List<Triangle> nextTriangles;

	public ChristmasSierpinskiTriangle() {
		super();
		triangles = new LinkedList<FractalShape>();
		nextTriangles = new LinkedList<Triangle>();
		createBase();
	}

	public int getSuggestedIterations() {
		return 1;
	}

	protected Paint getForeground() {
		MyPoint mid = newMinPoint.midPoint(newMaxPoint);
		mid.y += mid.y * (1.0 / 10.0);

		float[] dist = {0.3f, 1.0f};
		Color[] colors = {Color.BLUE, Color.RED};

		RadialGradientPaint paint =
				new RadialGradientPaint(
						(float) mid.x,
						(float) mid.y,
						(float) (mid.distance(newMinPoint) * (6.0 / 10.0)),
						dist,
						colors);
		return paint;
	}

	public List<FractalShape> getFractal() {
		return triangles;
	}

	public void clearFractal() {
		createBase();
	}

	protected void createBase() {
		MyPoint p2 = new MyPoint(0, HEIGHT);
		MyPoint p3 = new MyPoint(WIDTH, HEIGHT);
		MyPoint p1 = translate(p2, p3, p2.distance(p3), r60);

		Triangle tri = new Triangle(p1, p2, p3, GREEN2, true);

		triangles.clear();
		triangles.add(tri);

		nextTriangles.clear();
		nextTriangles.add(tri);

		for (int ii = 0; ii < 5; ii++) {
			myNext();
		}
	}

	protected void myNext() {
		MyPoint p1;
		MyPoint p2;
		MyPoint p3;
		MyPoint m1;
		MyPoint m2;
		MyPoint m3;

		Triangle tri;

		List<Triangle> newTriangles = new LinkedList<Triangle>();

		Iterator<Triangle> iter = nextTriangles.iterator();
		while (iter.hasNext()) {
			tri = iter.next();
			p1 = tri.p1;
			p2 = tri.p2;
			p3 = tri.p3;

			m1 = p2.midPoint(p3);
			m2 = p3.midPoint(p1);
			m3 = p1.midPoint(p2);

			tri = new Triangle(m1, m2, m3, GREEN1, true);
			triangles.add(tri);

			tri = new Triangle(p1, m3, m2);
			newTriangles.add(tri);

			tri = new Triangle(m3, p2, m1);
			newTriangles.add(tri);

			tri = new Triangle(m2, m1, p3);
			newTriangles.add(tri);
		}

		nextTriangles = newTriangles;
	}

	public void next() {
		/*FractalShape shape;
		Triangle triangle;
		Iterator<FractalShape> iter = triangles.iterator();
		while(iter.hasNext())
		{
			shape = iter.next();

			if(shape instanceof Triangle)
			{
				triangle = (Triangle)shape;
				if(triangle.paint == GREEN1)
				{
					triangle.paint = GREEN2;
				} else {
					triangle.paint = GREEN1;
				}
			}
		}*/
	}

	public String toString() {
		return "Christmas Sierpinski Triangle";
	}

	public static void main(String[] args) {
		SimpleFractal.main(args);
	}
}

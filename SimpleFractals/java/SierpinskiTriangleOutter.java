import java.awt.Color;
import java.awt.Paint;
import java.awt.RadialGradientPaint;
import java.util.*;
import javax.swing.*;

public class SierpinskiTriangleOutter extends SimpleFractal {
	protected List<FractalShape> triangles;
	protected List<Triangle> nextTriangles;

	public SierpinskiTriangleOutter() {
		super();
		triangles = new LinkedList<FractalShape>();
		nextTriangles = new LinkedList<Triangle>();
		createBase();
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

		Triangle tri = new Triangle(
			translate(p1, p2, p1.distance(p2), r30),
			translate(p2, p3, p2.distance(p3), r30),
			translate(p3, p1, p3.distance(p1), r30)
		);

		triangles.clear();
		triangles.add(tri);

		nextTriangles.clear();
		nextTriangles.add(tri);
	}

	public void next() {
		MyPoint p1;
		MyPoint p2;
		MyPoint p3;
		MyPoint m1;
		MyPoint m2;
		MyPoint m3;

		List<Triangle> newTriangles = new LinkedList<Triangle>();
		for(Triangle tri : nextTriangles) {
			p1 = tri.p1;
			p2 = tri.p2;
			p3 = tri.p3;

			m1 = p2.midPoint(p3);
			m2 = p3.midPoint(p1);
			m3 = p1.midPoint(p2);
			
			/*tri = new Triangle(
				translate(B, C, B.distance(C), r120),
				translate(B, A, B.distance(A), r120),
				B
			);
			newTriangles.add(tri);
			triangles.add(tri);*/
			
			tri = new Triangle(
				translate(m3, m2, m3.distance(m2), r120),
				translate(m3, p1, m3.distance(p1), r120),
				m3
			);
			newTriangles.add(tri);
			triangles.add(tri);
			
			tri = new Triangle(
				translate(m1, m3, m1.distance(m3), r120),
				translate(m1, p2, m1.distance(p2), r120),
				m1
			);
			newTriangles.add(tri);
			triangles.add(tri);
			
			tri = new Triangle(
				translate(m2, m1, m2.distance(m1), r120),
				translate(m2, p3, m2.distance(p3), r120),
				m2
			);
			newTriangles.add(tri);
			triangles.add(tri);
		}

		nextTriangles = newTriangles;
	}

	public String toString() {
		return "Sierpinski Triangle Outter";
	}

	public static void main(String[] args) {
		SimpleFractal.main(args);
	}
}

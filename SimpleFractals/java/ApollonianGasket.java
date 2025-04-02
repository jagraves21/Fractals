import java.awt.Color;
import java.awt.Paint;
import java.awt.RadialGradientPaint;
import java.util.*;
import javax.swing.*;

public class ApollonianGasket extends SimpleFractal {
	protected List<Circle[]> outsideAll;
	protected List<Circle[]> outsideTwo;
	protected List<FractalShape> circles;

	public ApollonianGasket() {
		super();
		circles = new LinkedList<FractalShape>();
		outsideAll = new LinkedList<Circle[]>();
		outsideTwo = new LinkedList<Circle[]>();
		createBase();
	}

	public int getSuggestedIterations() {
		return 8;
	}

	protected Paint getForeground() {
		MyPoint mid = newMinPoint.midPoint(newMaxPoint);

		float[] dist = {0.20f, 0.30f, 0.40f, 0.50f, 0.60f};
		Color[] colors = {Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN, Color.BLUE};

		RadialGradientPaint paint =i new RadialGradientPaint(
			(float) mid.x, (float) mid.y, (float) mid.distance(newMinPoint), dist, colors
		);
		return paint;
	}

	public List<FractalShape> getFractal() {
		return circles;
	}

	public void clearFractal() {
		createBase();
	}

	protected void createBase() {
		/*Random random = new Random();
		MyPoint centerA = new MyPoint(random.nextInt((int)WIDTH), random.nextInt((int)HEIGHT));
		MyPoint centerB = new MyPoint(random.nextInt((int)WIDTH), random.nextInt((int)HEIGHT));
		MyPoint centerC = new MyPoint(random.nextInt((int)WIDTH), random.nextInt((int)HEIGHT));*/

		MyPoint centerA = new MyPoint(0, 0);
		MyPoint centerB = new MyPoint(WIDTH, 0);
		MyPoint centerC = translate(centerA, centerB, centerA.distance(centerB), -r60);

		// MyPoint centerA = new MyPoint(0,0);
		// MyPoint centerB = new MyPoint(5,0);
		// MyPoint centerC = translate(centerA, centerB, 4, Math.acos(32/40));

		Triangle t = new Triangle(centerA, centerB, centerC, Color.WHITE);

		double a = centerB.distance(centerC);
		double b = centerA.distance(centerC);
		double c = centerA.distance(centerB);

		double radiusA = 0.5 * (-a + b + c);
		double radiusB = 0.5 * (a - b + c);
		double radiusC = 0.5 * (a + b - c);

		Circle A = new Circle(centerA, radiusA);
		Circle B = new Circle(centerB, radiusB);
		Circle C = new Circle(centerC, radiusC);

		circles.clear();
		outsideAll.clear();
		outsideTwo.clear();

		Circle D = solveApollonius(A, B, C, -1, -1, -1);
		circles.add(D);

		Circle[] circleArray = new Circle[3];
		circleArray[0] = A;
		circleArray[1] = B;
		circleArray[2] = C;
		outsideAll.add(circleArray);

		circleArray = new Circle[3];
		circleArray[0] = A;
		circleArray[1] = B;
		circleArray[2] = D;
		outsideTwo.add(circleArray);

		circleArray = new Circle[3];
		circleArray[0] = B;
		circleArray[1] = C;
		circleArray[2] = D;
		outsideTwo.add(circleArray);

		circleArray = new Circle[3];
		circleArray[0] = C;
		circleArray[1] = A;
		circleArray[2] = D;
		outsideTwo.add(circleArray);
	}

	public void next() {
		Circle A;
		Circle B;
		Circle C;
		Circle D;
		Circle[] circleArray;

		if (circles.size() == 1) {
			circleArray = outsideAll.get(0);
			circles.add(circleArray[0]);
			circles.add(circleArray[1]);
			circles.add(circleArray[2]);
			return;
		}

		List<Circle[]> nextOutsideAll = new LinkedList<Circle[]>();

		Iterator<Circle[]> iter = outsideAll.iterator();
		while (iter.hasNext()) {
			circleArray = iter.next();
			A = circleArray[0];
			B = circleArray[1];
			C = circleArray[2];

			D = solveApollonius(A, B, C, 1, 1, 1);
			if (D == null) {
				// System.out.println("null 135");
				continue;
			}
			if (D.radius() < 0.1) {
				// System.out.println("null 140");
				continue;
			}

			circles.add(D);

			circleArray = new Circle[3];
			circleArray[0] = A;
			circleArray[1] = B;
			circleArray[2] = D;
			nextOutsideAll.add(circleArray);

			circleArray = new Circle[3];
			circleArray[0] = A;
			circleArray[1] = C;
			circleArray[2] = D;
			nextOutsideAll.add(circleArray);

			circleArray = new Circle[3];
			circleArray[0] = B;
			circleArray[1] = C;
			circleArray[2] = D;
			nextOutsideAll.add(circleArray);
		}

		List<Circle[]> nextOutsideTwo = new LinkedList<Circle[]>();

		iter = outsideTwo.iterator();
		while (iter.hasNext()) {
			circleArray = iter.next();
			A = circleArray[0];
			B = circleArray[1];
			C = circleArray[2];

			D = solveApollonius(A, B, C, 1, 1, -1);
			if (D == null) {
				// System.out.println("null 179");
				continue;
			}
			if (D.radius() < 0.1) {
				// System.out.println("null 184");
				continue;
			}

			circles.add(D);

			circleArray = new Circle[3];
			circleArray[0] = D;
			circleArray[1] = A;
			circleArray[2] = C;
			nextOutsideTwo.add(circleArray);

			circleArray = new Circle[3];
			circleArray[0] = D;
			circleArray[1] = B;
			circleArray[2] = C;
			nextOutsideTwo.add(circleArray);

			circleArray = new Circle[3];
			circleArray[0] = A;
			circleArray[1] = B;
			circleArray[2] = D;
			nextOutsideAll.add(circleArray);
		}
		outsideAll = nextOutsideAll;
		outsideTwo = nextOutsideTwo;
	}

	public Circle solveApollonius(Circle c1, Circle c2, Circle c3, int s1, int s2, int s3) {
		/*double x1 = c1.p1.x;
			double y1 = c1.p1.y;
			double r1 = c1.radius();
			double x2 = c2.p1.x;
			double y2 = c2.p1.y;
			double r2 = c2.radius();
			double x3 = c3.p1.x;
			double y3 = c3.p1.y;
			double r3 = c3.radius();

			double v11 = 2*x2 - 2*x1;
			double v12 = 2*y2 - 2*y1;
			double v13 = x1*x1 - x2*x2 + y1*y1 - y2*y2 - r1*r1 + r2*r2;
			double v14 = 2*s2*r2 - 2*s1*r1;

			double v21 = 2*x3 - 2*x2;
			double v22 = 2*y3 - 2*y2;
			double v23 = x2*x2 - x3*x3 + y2*y2 - y3*y3 - r2*r2 + r3*r3;
			double v24 = 2*s3*r3 - 2*s2*r2;

			double w12 = v12/v11;
			double w13 = v13/v11;
			double w14 = v14/v11;

			double w22 = v22/v21-w12;
			double w23 = v23/v21-w13;
			double w24 = v24/v21-w14;

			double P = -w23/w22;
			double Q = w24/w22;
			double M = -w12*P-w13;
			double N = w14 - w12*Q;

			double a = N*N + Q*Q - 1;
			double b = 2*M*N - 2*N*x1 + 2*P*Q - 2*Q*y1 + 2*s1*r1;
			double c = x1*x1 + M*M - 2*M*x1 + P*P + y1*y1 - 2*P*y1 - r1*r1;

		// Find roots of a quadratic equation
		//double[] quadSols = Polynomial.solve(new double[]{a,b,c});
		//double rs = (double)quadSols[0];
		double rs = (-b + Math.sqrt((b*b) - (4*a*c))) / (2 * a);
		double xs = M+N*rs;
		double ys = P+Q*rs;

		if(Double.isNaN(rs))
		{
		System.out.println("a " + a);
		System.out.println("b " + b);
		System.out.println("c " + c);
		System.out.println((b*b) - (4*a*c));
		return null;
		}

		return new Circle(new MyPoint(xs,ys), new MyPoint(xs+rs, ys));*/

		// Make sure c2 doesn't have the same X or Y coordinate as the others.
		double tiny = 0.0001f;
		if ((Math.abs(c2.c.x - c1.c.x) < tiny) || (Math.abs(c2.c.y - c1.c.y) < tiny)) {
			Circle temp_circle = c2;
			c2 = c3;
			c3 = temp_circle;
			int temp_s = s2;
			s2 = s3;
			s3 = temp_s;
		}
		if ((Math.abs(c2.c.x - c3.c.x) < tiny) || (Math.abs(c2.c.y - c3.c.y) < tiny)) {
			Circle temp_circle = c2;
			c2 = c1;
			c1 = temp_circle;
			int temp_s = s2;
			s2 = s1;
			s1 = temp_s;
		}

		double x1 = c1.c.x;
		double y1 = c1.c.y;
		double r1 = c1.radius();
		double x2 = c2.c.x;
		double y2 = c2.c.y;
		double r2 = c2.radius();
		double x3 = c3.c.x;
		double y3 = c3.c.y;
		double r3 = c3.radius();

		double v11 = 2 * x2 - 2 * x1;
		double v12 = 2 * y2 - 2 * y1;
		double v13 = x1 * x1 - x2 * x2 + y1 * y1 - y2 * y2 - r1 * r1 + r2 * r2;
		double v14 = 2 * s2 * r2 - 2 * s1 * r1;

		double v21 = 2 * x3 - 2 * x2;
		double v22 = 2 * y3 - 2 * y2;
		double v23 = x2 * x2 - x3 * x3 + y2 * y2 - y3 * y3 - r2 * r2 + r3 * r3;
		double v24 = 2 * s3 * r3 - 2 * s2 * r2;

		double w12 = v12 / v11;
		double w13 = v13 / v11;
		double w14 = v14 / v11;

		double w22 = v22 / v21 - w12;
		double w23 = v23 / v21 - w13;
		double w24 = v24 / v21 - w14;

		double P = -w23 / w22;
		double Q = w24 / w22;
		double M = -w12 * P - w13;
		double N = w14 - w12 * Q;

		double a = N * N + Q * Q - 1;
		double b = 2 * M * N - 2 * N * x1 + 2 * P * Q - 2 * Q * y1 + 2 * s1 * r1;
		double c = x1 * x1 + M * M - 2 * M * x1 + P * P + y1 * y1 - 2 * P * y1 - r1 * r1;

		// Find roots of a quadratic equation
		double solution = quadraticSolutions(a, b, c);
		if (Double.isNaN(solution)) {
			return null;
		}
		double rs = solution;
		double xs = M + N * rs;
		double ys = P + Q * rs;

		if ((Math.abs(xs) < tiny) || (Math.abs(ys) < tiny) || (Math.abs(rs) < tiny)) return null;
		return new Circle(new MyPoint(xs, ys), Math.abs(rs));
	}

	public double quadraticSolutions(double a, double b, double c) {
		double tiny = 0.000001;
		double discriminant = b * b - 4 * a * c;

		// See if there are no real solutions.
		if (discriminant < 0) {
			return Double.NaN;
		}

		// See if there is one solution.
		if (discriminant < tiny) {
			return -b / (2 * a);
		}

		return (-b + Math.sqrt(discriminant)) / (2 * a);
		// return (-b - Math.sqrt(discriminant)) / (2 * a);
	}

	public String toString() {
		return "Apollonian Gasket";
	}

	public static void main(String[] args) {
		SimpleFractal.main(args);
	}
}

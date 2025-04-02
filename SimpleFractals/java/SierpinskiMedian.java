import java.awt.Color;
import java.awt.Paint;
import java.awt.RadialGradientPaint;
import java.util.*;
import javax.swing.*;

public class SierpinskiMedian extends LSystem {
	public SierpinskiMedian() {
		super();
	}

	protected Paint getForeground() {
		MyPoint mid = newMinPoint.midPoint(newMaxPoint);
		MyPoint tmp = new MyPoint(newMinPoint.x, mid.y);

		float[] dist = {0.3f, 1.0f};
		Color[] colors = {Color.MAGENTA, Color.BLUE};

		RadialGradientPaint paint =
				new RadialGradientPaint(
						(float) mid.x, (float) mid.y, (float) mid.distance(tmp), dist, colors);
		return paint;
	}

	protected StringBuilder getAxiom() {
		return new StringBuilder("L--F--L--F");
	}

	public StringBuilder applyTransition(StringBuilder curve) {
		StringBuilder nextCurve = new StringBuilder();

		for (int ii = 0; ii < curve.length(); ii++) {
			if (curve.charAt(ii) == 'L') {
				nextCurve.append("+R-F-R+");
			} else if (curve.charAt(ii) == 'R') {
				nextCurve.append("-L+F+L-");
			} else {
				nextCurve.append(curve.charAt(ii));
			}
		}

		return nextCurve;
	}

	protected double getStartingAngle() {
		return r45;
	}

	protected double getTurningAngle() {
		return r45;
	}

	public String toString() {
		return "Sierpinski Median";
	}

	public static void main(String[] args) {
		SimpleFractal.main(args);
	}
}

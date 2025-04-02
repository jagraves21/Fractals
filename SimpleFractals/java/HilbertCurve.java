import java.awt.Color;
import java.awt.Paint;
import java.awt.RadialGradientPaint;
import java.util.*;
import javax.swing.*;

public class HilbertCurve extends LSystem {
	public HilbertCurve() {
		super();
	}

	public int getSuggestedIterations() {
		return 5;
	}

	protected Paint getForeground() {
		MyPoint mid = newMinPoint.midPoint(newMaxPoint);

		float[] dist = {0.0f, 1.0f};
		Color[] colors = {Color.GREEN, Color.BLUE};

		RadialGradientPaint paint =
				new RadialGradientPaint(
						(float) mid.x, (float) mid.y, (float) mid.distance(newMaxPoint), dist, colors);
		return paint;
	}

	protected StringBuilder getAxiom() {
		return new StringBuilder("+RF-LFL-FR+");
	}

	public StringBuilder applyTransition(StringBuilder curve) {
		StringBuilder nextCurve = new StringBuilder();

		for (int ii = 0; ii < curve.length(); ii++) {
			if (curve.charAt(ii) == 'L') {
				nextCurve.append("+RF-LFL-FR+");
			} else if (curve.charAt(ii) == 'R') {
				nextCurve.append("-LF+RFR+FL-");
			} else {
				nextCurve.append(curve.charAt(ii));
			}
		}

		return nextCurve;
	}

	protected double getTurningAngle() {
		return r90;
	}

	public String toString() {
		return "Hilbert Curve";
	}

	public static void main(String[] args) {
		SimpleFractal.main(args);
	}
}

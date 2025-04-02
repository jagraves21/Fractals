import java.awt.Color;
import java.awt.Paint;
import java.awt.RadialGradientPaint;
import java.util.*;
import javax.swing.*;

public class MooreCurve extends LSystem {
	public static final double r36 = (36 * Math.PI / 180.0);

	public MooreCurve() {
		super();
	}

	public int getSuggestedIterations() {
		return 6;
	}

	protected Paint getForeground() {
		MyPoint mid = newMinPoint.midPoint(newMaxPoint);
		mid.y = newMinPoint.y + ((newMaxPoint.y - newMinPoint.y) * (7.0 / 13.0));

		float[] dist = {0.0f, 0.33f, 0.66f, 1.0f};
		Color[] colors = {Color.ORANGE, Color.RED, Color.BLUE, Color.GREEN};

		RadialGradientPaint paint =
				new RadialGradientPaint(
						(float) mid.x, (float) mid.y, (float) mid.distance(newMaxPoint), dist, colors);
		return paint;
	}

	protected StringBuilder getAxiom() {
		return new StringBuilder("LFL+F+LFL");
	}

	public StringBuilder applyTransition(StringBuilder curve) {
		StringBuilder nextCurve = new StringBuilder();

		for (int ii = 0; ii < curve.length(); ii++) {
			if (curve.charAt(ii) == 'L') {
				nextCurve.append("-RF+LFL+FR-");
			} else if (curve.charAt(ii) == 'R') {
				nextCurve.append("+LF-RFR-FL+");
			} else {
				nextCurve.append(curve.charAt(ii));
			}
		}

		return nextCurve;
	}

	protected double getStartingAngle() {
		return r90;
	}

	protected double getTurningAngle() {
		return r90;
	}

	public String toString() {
		return "Moore's Curve";
	}

	public static void main(String[] args) {
		SimpleFractal.main(args);
	}
}

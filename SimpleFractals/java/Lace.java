import java.awt.Color;
import java.awt.Paint;
import java.awt.RadialGradientPaint;
import java.util.*;
import javax.swing.*;

public class Lace extends LSystem {
	public Lace() {
		super();
	}

	public int getSuggestedIterations() {
		return 7;
	}

	protected Paint getForeground() {
		MyPoint mid = newMinPoint.midPoint(newMaxPoint);
		MyPoint tmp = new MyPoint(newMinPoint.x, mid.y);

		float[] dist = {0.0f, 1.0f};
		Color[] colors = {Color.WHITE, Color.BLUE};

		RadialGradientPaint paint =
				new RadialGradientPaint(
						(float) mid.x, (float) mid.y, (float) mid.distance(tmp), dist, colors);
		return paint;
	}

	protected StringBuilder getAxiom() {
		return new StringBuilder("+++X--F--ZFX+");
	}

	public StringBuilder applyTransition(StringBuilder curve) {
		StringBuilder nextCurve = new StringBuilder();

		for (int ii = 0; ii < curve.length(); ii++) {
			if (curve.charAt(ii) == 'W') {
				nextCurve.append("+++X--F--ZFX+");
			} else if (curve.charAt(ii) == 'X') {
				nextCurve.append("---W++F++YFW-");
			} else if (curve.charAt(ii) == 'Y') {
				nextCurve.append("+ZFX--F--Z+++");
			} else if (curve.charAt(ii) == 'Z') {
				nextCurve.append("-YFW++F++Y---");
			} else {
				nextCurve.append(curve.charAt(ii));
			}
		}

		return nextCurve;
	}

	protected double getStartingAngle() {
		return 0.0;
	}

	protected double getTurningAngle() {
		return r30;
	}

	public String toString() {
		return "Lace";
	}

	public static void main(String[] args) {
		SimpleFractal.main(args);
	}
}

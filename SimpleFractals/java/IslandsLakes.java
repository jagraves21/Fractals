import java.awt.Color;
import java.awt.Paint;
import java.awt.RadialGradientPaint;
import java.util.*;
import javax.swing.*;

public class IslandsLakes extends LSystem {
	public IslandsLakes() {
		super();
	}

	public int getSuggestedIterations() {
		return 2;
	}

	protected Paint getForeground() {
		MyPoint mid = newMinPoint.midPoint(newMaxPoint);

		float[] dist = {0.0f, 1.0f};
		Color[] colors = {Color.ORANGE, Color.RED};

		RadialGradientPaint paint =
				new RadialGradientPaint(
						(float) mid.x, (float) mid.y, (float) mid.distance(newMaxPoint), dist, colors);
		return paint;
	}

	protected StringBuilder getAxiom() {
		return new StringBuilder("F+F+F+F");
	}

	public StringBuilder applyTransition(StringBuilder curve) {
		StringBuilder nextCurve = new StringBuilder();

		for (int ii = 0; ii < curve.length(); ii++) {
			if (curve.charAt(ii) == 'F') {
				nextCurve.append("F+f-FF+F+FF+Ff+FF-f+FF-F-FF-Ff-FFF");
			} else if (curve.charAt(ii) == 'f') {
				nextCurve.append("ffffff");
			} else {
				nextCurve.append(curve.charAt(ii));
			}
		}

		return nextCurve;
	}

	protected double getTurningAngle() {
		return -r90;
	}

	public String toString() {
		return "Islands and Lakes";
	}

	public static void main(String[] args) {
		SimpleFractal.main(args);
	}
}

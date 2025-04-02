import java.awt.Color;
import java.awt.Paint;
import java.awt.RadialGradientPaint;
import java.util.*;
import javax.swing.*;

public class Square extends LSystem {
	public Square() {
		super();
	}

	public int getSuggestedIterations() {
		return 5;
	}

	protected Paint getForeground() {
		MyPoint mid = newMinPoint.midPoint(newMaxPoint);

		float[] dist = {0.30f, 0.40f, 0.50f, 0.60f};
		Color[] colors = {Color.ORANGE, Color.RED, Color.BLUE, Color.GREEN};

		RadialGradientPaint paint =
				new RadialGradientPaint(
						(float) mid.x, (float) mid.y, (float) mid.distance(newMaxPoint), dist, colors);
		return paint;
	}

	protected StringBuilder getAxiom() {
		return new StringBuilder("F-F-F-F");
	}

	public StringBuilder applyTransition(StringBuilder curve) {
		StringBuilder nextCurve = new StringBuilder();

		for (int ii = 0; ii < curve.length(); ii++) {
			if (curve.charAt(ii) == 'F') {
				nextCurve.append("+F-F+F-F-F+F-F+");
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
		return "Square";
	}

	public static void main(String[] args) {
		SimpleFractal.main(args);
	}
}

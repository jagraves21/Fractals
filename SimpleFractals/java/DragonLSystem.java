import java.awt.Color;
import java.awt.Paint;
import java.awt.RadialGradientPaint;
import java.util.*;
import javax.swing.*;

public class DragonLSystem extends LSystem {
	public DragonLSystem() {
		super();
	}

	public int getSuggestedIterations() {
		return 13;
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
		return new StringBuilder("FX");
	}

	public StringBuilder applyTransition(StringBuilder curve) {
		StringBuilder nextCurve = new StringBuilder();

		for (int ii = 0; ii < curve.length(); ii++) {
			if (curve.charAt(ii) == 'X') {
				nextCurve.append("X+YF");
			} else if (curve.charAt(ii) == 'Y') {
				nextCurve.append("FX-Y");
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
		return r80;
	}

	public String toString() {
		return "Dragon LSytem";
	}

	public static void main(String[] args) {
		SimpleFractal.main(args);
	}
}

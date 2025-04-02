import java.awt.Color;
import java.awt.Paint;
import java.awt.RadialGradientPaint;
import java.util.*;
import javax.swing.*;

public class HexagonalTile extends LSystem {
	public HexagonalTile() {
		super();
	}

	public int getSuggestedIterations() {
		return 6;
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
		return new StringBuilder("F");
	}

	public StringBuilder applyTransition(StringBuilder curve) {
		StringBuilder nextCurve = new StringBuilder();

		for (int ii = 0; ii < curve.length(); ii++) {
			if (curve.charAt(ii) == 'F') {
				nextCurve.append("-F+F+f[+F+F]-");
			} else if (curve.charAt(ii) == 'f') {
				nextCurve.append("ff");
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
		return r60;
	}

	public String toString() {
		return "Hexagonal Tile";
	}

	public static void main(String[] args) {
		SimpleFractal.main(args);
	}
}

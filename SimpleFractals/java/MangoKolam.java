import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Paint;
import java.awt.Point;
import java.util.*;
import javax.swing.*;

public class MangoKolam extends LSystem {
	public MangoKolam() {
		super();
	}

	public int getSuggestedIterations() {
		return 18;
	}

	protected Paint getForeground() {
		Color c1 = Color.GREEN;
		Color c2 = Color.BLUE;

		Point p1 = new Point((int) newMinPoint.x, (int) newMinPoint.y);
		Point p2 = new Point((int) newMaxPoint.x, (int) newMaxPoint.y);
		GradientPaint paint = new GradientPaint(p1, c1, p2, c2);

		return paint;
	}

	protected StringBuilder getAxiom() {
		return new StringBuilder("--f-F+Z+F-fA---f-F+Z+F-fA");
	}

	public StringBuilder applyTransition(StringBuilder curve) {
		StringBuilder nextCurve = new StringBuilder();

		for (int ii = 0; ii < curve.length(); ii++) {
			if (curve.charAt(ii) == 'A') {
				nextCurve.append("f-F+Z+F-fA");
			} else if (curve.charAt(ii) == 'Z') {
				nextCurve.append("F-FF-F--[--Z]F-FF-F--F-FF-F--");
			} else {
				nextCurve.append(curve.charAt(ii));
			}
		}

		return nextCurve;
	}

	protected double getTurningAngle() {
		return r60;
	}

	public String toString() {
		return "Mango Kolam";
	}

	public static void main(String[] args) {
		SimpleFractal.main(args);
	}
}

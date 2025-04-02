import java.awt.Color;
import java.awt.Paint;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public abstract class LSystem extends SimpleFractal {
	protected StringBuilder curve;

	public LSystem() {
		super();
		curve = new StringBuilder("");
		createBase();
	}

	protected Paint getForeground() {
		return Color.WHITE;
	}

	/*
	Character        Meaning
		 F	         Move forward by line length drawing a line
		 f	         Move forward by line length without drawing a line
		 +	         Turn left by turning angle
		 -	         Turn right by turning angle
		 |	         Reverse direction (ie: turn by 180 degrees)
		 [	         Push current drawing state onto stack
		 ]	         Pop current drawing state from the stack
		 #	         Increment the line width by line width increment
		 !	         Decrement the line width by line width increment
		 @	         Draw a dot with line width radius
		 {	         Open a polygon
		 }	         Close a polygon and fill it with fill colour
		 >	         Multiply the line length by the line length scale factor
		 <	         Divide the line length by the line length scale factor
		 &	         Swap the meaning of + and -
		 (	         Decrement turning angle by turning angle increment
		 )	         Increment turning angle by turning angle increment
	*/
	public List<FractalShape> getFractal() {
		Stack<LState> stack = new Stack<LState>();
		List<FractalShape> lines = new LinkedList<FractalShape>();

		double curAngle = getStartingAngle();
		double turningAngle = getTurningAngle();
		double radius = 100;
		double scaleFactor = getScaleFactor();
		char plus = '+';
		char minus = '-';
		MyPoint p1 = new MyPoint(0, 0);
		MyPoint p2 = new MyPoint(radius, 0);
		MyPoint p3;
		LState state;

		for (int ii = 0; ii < curve.length(); ii++) {
			if (curve.charAt(ii) == 'F') {
				p3 = translate(p2, p1, radius, r180 + curAngle);
				p1 = p2;
				p2 = p3;
				lines.add(new LineSegment(p1, p2));
				curAngle = 0;
				// System.out.println(p1 + " " + p2);
			} else if (curve.charAt(ii) == 'f') {
				p3 = translate(p2, p1, radius, r180 + curAngle);
				p1 = p2;
				p2 = p3;
				curAngle = 0;
			} else if (curve.charAt(ii) == plus) {
				curAngle += turningAngle;
			} else if (curve.charAt(ii) == minus) {
				curAngle -= turningAngle;
			} else if (curve.charAt(ii) == '|') {
				curAngle += r180;
			} else if (curve.charAt(ii) == '[') {
				state = new LState(curAngle, turningAngle, radius, scaleFactor, plus, minus, p1, p2);
				stack.push(state);
			} else if (curve.charAt(ii) == ']') {
				state = stack.pop();

				curAngle = state.curAngle;
				turningAngle = state.turningAngle;
				radius = state.radius;
				scaleFactor = state.scaleFactor;
				plus = state.plus;
				minus = state.minus;
				p1 = state.p1;
				p2 = state.p2;
			} else if (curve.charAt(ii) == '>') {
				radius *= getScaleFactor();
			} else if (curve.charAt(ii) == '<') {
				radius /= getScaleFactor();
			} else if (curve.charAt(ii) == '&') {
				char tmp = minus;
				minus = plus;
				plus = tmp;
			} else if (curve.charAt(ii) == '(') {
				turningAngle -= getAngleIncrement();
			} else if (curve.charAt(ii) == ')') {
				turningAngle += getAngleIncrement();
			}
		}

		return lines;
	}

	public void clearFractal() {
		createBase();
	}

	protected void createBase() {
		curve = getAxiom();
	}

	public void next() {
		curve = applyTransition(curve);
	}

	protected abstract StringBuilder getAxiom();

	protected abstract StringBuilder applyTransition(StringBuilder curve);

	protected double getStartingAngle() {
		return 0.0;
	}

	protected double getTurningAngle() {
		return 0.0;
	}

	protected double getAngleIncrement() {
		return 0.0;
	}

	protected double getScaleFactor() {
		return 1.0;
	}
}

import java.awt.Color;
import java.awt.Paint;
import java.awt.RadialGradientPaint;
import java.util.*;
import javax.swing.*;

public class CircleLimitSet4 extends CircleLimitSet {
	protected Circle center;
	protected List<Circle> movingCircles;

	public CircleLimitSet4() {
		super();
	}

	public int getSuggestedIterations() {
		return 36;
	}

	public int getSuggestedDelay() {
		return 100;
	}

	public void clearFractal() {
		movingCircles = new LinkedList<Circle>();
		super.clearFractal();
	}

	protected void createBases() {
		super.createBases();
		movingCircles.clear();

		center = basesCircles.remove(0);
		double diff = center.radius() * 0.59;
		Circle movingBase = new Circle(
			new MyPoint(center.c.x, center.c.y - diff), center.radius() - diff, Color.RED
		);
		movingCircles.add(movingBase);

		int N = 4;
		for(int ii=0; ii < N; ii++) {
			Circle tmpCircle = new Circle(
				translate(
					center.c,
					movingBase.c,
					center.c.distance(movingBase.c),
					(-2 * Math.PI) / N * (ii+1)
				),
				center.radius() - diff,
				movingBase.paint
			);
			movingCircles.add(tmpCircle);
		}

		basesCircles.addAll(movingCircles);
		
		next();
	}

	public void next() {
		for (Circle movingCircle : movingCircles) {
			movingCircle.c = translate(
				center.c,
				movingCircle.c,
				center.c.distance(movingCircle.c),
				(-2 * Math.PI) / getSuggestedIterations()
			);
		}

		currentCircles.clear();	
		currentCircles.addAll(basesCircles);
		
		fractal.clear();
		//fractal.addAll(basesCircles);
		for (int ii=0; ii < 5; ii++) {
			super.next();
		}
	}

	public String toString() {
		return "Circle Limit Set 4";
	}

	public static void main(String[] args) {
		SimpleFractal.main(args);
	}
}

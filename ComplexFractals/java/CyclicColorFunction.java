import java.awt.Color;

public class CyclicColorFunction implements ColorFunction {
	public Color getConvergentColor(Complex z, int iteration, int maxIteration) {
		return Color.BLACK;
	}
	
	public Color getDivergentColor(Complex z, int iteration, int maxIteration) {
		return new Color((iteration % 4) * 64, (iteration % 8) * 32, (iteration % 16) * 16);
	}
	
	public String toString() {
		return "Cyclic Coloring";
	}
}
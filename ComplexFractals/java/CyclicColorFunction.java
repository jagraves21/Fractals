import java.awt.Color;

public class CyclicColorFunction implements ColorFunction {
	public Color getConvergentColor(Complex z, int iteration, int maxIteration) {
		return Color.BLACK;
	}

	public Color getDivergentColor(Complex z, int iteration, int maxIteration) {
		int r = (iteration * 5) % 256; // (iteration % 4) * 64;
		int g = (iteration * 7) % 256; // (iteration % 8) * 32;
		int b = (iteration * 11) % 256; // (iteration % 16) * 16;
		return new Color(r, g, b);
	}

	public String toString() {
		return "Cyclic Coloring";
	}
}

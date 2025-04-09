import java.awt.Color;

public class CosineColorFunction implements ColorFunction {
	public Color getConvergentColor(Complex z, int iteration, int maxIteration) {
		return Color.BLACK;
	}

	public Color getDivergentColor(Complex z, int iteration, int maxIteration) {
		double modulus = z.modulus();
		if (modulus == 0) {
			modulus = 1e-10;
		}

		double cos = Math.pow(Math.cos(z.re / modulus), 2);
		double sin = Math.pow(Math.sin(z.im / modulus), 2);

		int red = (int)Math.round(255 * cos * sin);
		int green = (int)Math.round(255 * sin);
		int blue = (int)Math.round(255 * cos);

		red = Math.min(255, Math.max(0, red));
		green = Math.min(255, Math.max(0, green));
		blue = Math.min(255, Math.max(0, blue));

		return new Color(red, green, blue);
	}

	public String toString() {
		return "Cosine Coloring";
	}
}

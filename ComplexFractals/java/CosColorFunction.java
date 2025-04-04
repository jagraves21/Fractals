import java.awt.Color;

public class CosColorFunction implements ColorFunction {
	public Color getConvergentColor(Complex z, int iteration, int maxIteration) {
		return Color.BLACK;
	}

	public Color getDivergentColor(Complex z, int iteration, int maxIteration) {
		double cos = Math.pow(Math.cos(z.re/Math.sqrt(z.re*z.re + z.im*z.im)),2);
		double sin = Math.pow(Math.sin(z.im/Math.sqrt(z.re*z.re + z.im*z.im)),2);
		int red = (int)Math.round(255*cos*sin);
		int green = (int)Math.round(255*sin);
		int blue = (int)Math.round(255*cos);
		return new Color(red,green,blue);
	}

	public String toString() {
		return "Cosine Coloring";
	}
}
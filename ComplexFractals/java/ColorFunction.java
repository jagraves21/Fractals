import java.awt.Color;

public interface ColorFunction {
	public Color getConvergentColor(Complex z, int iteration, int maxIteration);
	public Color getDivergentColor(Complex z, int iteration, int maxIteration);
}
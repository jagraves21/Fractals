import java.awt.Color;

import java.util.ArrayList;
import java.util.List;

public class AbstractColorFunction implements ColorFunction {

	public Color getConvergentColor(Complex complex, int iteration, int maxIteration) {
		return BLACK;
	}
	
	public Color getDivergentColor(Complex complex, int iteration, int maxIteration) {
		return RED;
	}

	public static Color[] generateColorGradient(Color[] bases, int steps) {
		List<Color> colorList = new ArrayList<>();
		for (int ii = 0; ii < bases.length - 1; ii++) {
			Color start = bases[ii];
			Color end = bases[ii + 1];

			for (int jj = 0; jj < steps; jj++) {
				double ratio = (double) jj / steps;
				int red = (int) Math.round(start.getRed() + ratio * (end.getRed() - start.getRed()));
				int green = (int) Math.round(start.getGreen() + ratio * (end.getGreen() - start.getGreen()));
				int blue = (int) Math.round(start.getBlue() + ratio * (end.getBlue() - start.getBlue()));
				colorList.add(new Color(red, green, blue));
			}
		}
		colorList.add(bases[bases.length - 1]);

		return colorList.toArray(new Color[0]);
	}
}

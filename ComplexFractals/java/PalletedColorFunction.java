/*
 * Decompiled with CFR 0.151.
 * 
 * Could not load the following classes:
 *  ColorFunction
 *  Complex
 */
import java.util.ArrayList;
import java.awt.Color;
import java.util.List;

public class PalletedColorFunction implements ColorFunction {
	public static Color[] RGB = new Color[]{
		RED,
		GREEN,
		BLUE
	};

	public static Color[] RAINBOW = new Color[]{
		RED,
		ORANGE,
		YELLOW,
		GREEN,
		BLUE,
		INDIGO,
		VIOLET
	};

	public static Color[] SPECTRUM = new Color[]{
		RED,
		YELLOW,
		GREEN,
		BLUE,
		BLUE_VIOLET,
		INDIGO,
		BLUE_VIOLET,
		BLUE,
		GREEN,
		YELLOW,
		RED
	};

	public static Color[] FOREST = new Color[]{
		FOREST_GREEN,
		FOREST_OLIVE,
		FOREST_SADDLE_BROWN,
		FOREST_LIGHT_GREEN,
		FOREST_SIENNA
	};

	public static Color[] SEAWEED = new Color[]{
		BRIGHT_CYAN,
		TEAL_BLUE,
		SEAFOAM,
		OLIVE,
		MOSS_GREEN,
		DEEP_GREEN
	};

	public static Color[] OCEAN = new Color[]{
		OCEAN_BLUE,
		OCEAN_SKY_BLUE,
		OCEAN_DEEP_BLUE,
		OCEAN_STEEL_BLUE,
		OCEAN_DARK_SLATE_BLUE
	};

	public static Color[] AQUA = new Color[]{
		BLUE,
		CYAN,
		GREEN,
		CYAN,
		BLUE
	};

	public static Color[] SUNSET = new Color[]{
		SUNSET_CORAL,
		SUNSET_ORANGE,
		SUNSET_YELLOW,
		SUNSET_LIGHT_RED,
		SUNSET_AMBER
	};

	public static Color[] TROPICAL = new Color[]{
		TROPICAL_HOT_PINK,
		TROPICAL_ORANGE,
		TROPICAL_CYAN,
		TROPICAL_GOLD,
		TROPICAL_FOREST_GREEN
	};

	public static Color[] NEON = new Color[]{
		NEON_CYAN,
		NEON_MAGENTA,
		NEON_YELLOW,
		NEON_GREEN,
		NEON_RED
	};

	public static Color[] SPACE = new Color[]{
		DEEP_PURPLE,
		DARK_BLUE,
		DARK_RED,
		INDIGO,
		VIOLET
	};

	public static final Color[] TWILIGHT = new Color[]{
		TWILIGHT_LAVENDER,
		TWILIGHT_PERIWINKLE,
		TWILIGHT_SKY_BLUE,
		BLUE_VIOLET,
		OCEAN_DARK_SLATE_BLUE,
		INDIGO,
		DEEP_PURPLE
	};

	public static Color[] ICE_FIRE = new Color[]{
		DARK_BLUE,
		BLUE,
		RED,
		DARK_RED,
		DARK_BLUE
	};

	public static Color[] ZEBRA = new Color[]{
		BLACK,
		WHITE
	};

	public static Color[] CANDY_CANE = new Color[]{
		WHITE,
		RED,
		WHITE
	};

	public static Color[] MAGMA = new Color[]{
		DEEP_PURPLE,
		BROWN_RED,
		BRICK_RED,
		HOT_ORANGE,
		GLOW_YELLOW,
		BRICK_RED,
		BROWN_RED,
		DEEP_PURPLE
	};

	public static Color[] LAVA = new Color[]{
		BLACK,
		RED,
		YELLOW,
		RED,
		BLACK
	};

	public static Color[] PINK_BLUES = new Color[]{
		PINK_BLUSH,
		PINK_SKY_BLUE,
		PINK_HOT_PINK,
		PINK_SKY_BLUE_2,
		PINK_SOFT_PINK
	};

	protected Color[] pallet;

	public static PalletedColorFunction getRGB(int steps) {
		return new PalletedColorFunction(PalletedColorFunction.getColorSpan(RGB, steps));
	}

	public static PalletedColorFunction getRainbow(int steps) {
		return new PalletedColorFunction(PalletedColorFunction.getColorSpan(RAINBOW, steps));
	}

	public static PalletedColorFunction getSpectrum(int steps) {
		return new PalletedColorFunction(PalletedColorFunction.getColorSpan(SPECTRUM, steps));
	}

	public static PalletedColorFunction getForest(int steps) {
		return new PalletedColorFunction(PalletedColorFunction.getColorSpan(FOREST, steps));
	}

	public static PalletedColorFunction getSeaweed(int steps) {
		return new PalletedColorFunction(PalletedColorFunction.getColorSpan(SEAWEED, steps));
	}

	public static PalletedColorFunction getOcean(int steps) {
		return new PalletedColorFunction(PalletedColorFunction.getColorSpan(OCEAN, steps));
	}

	public static PalletedColorFunction getAqua(int steps) {
		return new PalletedColorFunction(PalletedColorFunction.getColorSpan(AQUA, steps));
	}

	public static PalletedColorFunction getSunset(int steps) {
		return new PalletedColorFunction(PalletedColorFunction.getColorSpan(SUNSET, steps));
	}

	public static PalletedColorFunction getTropical(int steps) {
		return new PalletedColorFunction(PalletedColorFunction.getColorSpan(TROPICAL, steps));
	}

	public static PalletedColorFunction getNeon(int steps) {
		return new PalletedColorFunction(PalletedColorFunction.getColorSpan(NEON, steps));
	}

	public static PalletedColorFunction getSpace(int steps) {
		return new PalletedColorFunction(PalletedColorFunction.getColorSpan(SPACE, steps));
	}
	
	public static PalletedColorFunction getTwilight(int steps) {
		return new PalletedColorFunction(PalletedColorFunction.getColorSpan(TWILIGHT, steps));
	}

	public static PalletedColorFunction getIceFire(int steps) {
		return new PalletedColorFunction(PalletedColorFunction.getColorSpan(ICE_FIRE, steps));
	}

	public static PalletedColorFunction getZebra(int steps) {
		return new PalletedColorFunction(PalletedColorFunction.getColorSpan(ZEBRA, steps));
	}

	public static PalletedColorFunction getCandyCane(int steps) {
		return new PalletedColorFunction(PalletedColorFunction.getColorSpan(CANDY_CANE, steps));
	}

	public static PalletedColorFunction getMagma(int steps) {
		return new PalletedColorFunction(PalletedColorFunction.getColorSpan(MAGMA, steps));
	}

	public static PalletedColorFunction getLava(int steps) {
		return new PalletedColorFunction(PalletedColorFunction.getColorSpan(LAVA, steps));
	}

	public static PalletedColorFunction getPinkBlues(int steps) {
		return new PalletedColorFunction(PalletedColorFunction.getColorSpan(PINK_BLUES, steps));
	}
	
	public PalletedColorFunction() {
		this(RAINBOW);
	}

	public PalletedColorFunction(Color[] pallet) {
		this.pallet = pallet;
	}

	public Color getConvergentColor(Complex complex, int iteration, int maxIteration) {
		return BLACK;
	}

	public Color getDivergentColor(Complex z, int iteration, int maxIteration) {
		double s1 = iteration + 1 - Math.log(Math.log(z.modulus())) / Math.log(2);

		if (s1 < 0) {
			s1 = 0;
		}

		int index1 = ((int) s1) % pallet.length;
		int index2 = (index1 + 1) % pallet.length;

		Color c1 = pallet[index1];
		Color c2 = pallet[index2];

		double fraction = s1 - (int) s1;
		double weight1 = 1 - fraction;
		double weight2 = fraction;

		int red = (int) Math.round(c1.getRed() * weight1 + c2.getRed() * weight2);
		int green = (int) Math.round(c1.getGreen() * weight1 + c2.getGreen() * weight2);
		int blue = (int) Math.round(c1.getBlue() * weight1 + c2.getBlue() * weight2);

		// Clamp values to avoid out-of-range errors (just in case)
		red = Math.min(255, Math.max(0, red));
		green = Math.min(255, Math.max(0, green));
		blue = Math.min(255, Math.max(0, blue));

		return new Color(red, green, blue);
		//return pallet[iteration % pallet.length];
	}

	public static Color[] getColorSpan(Color[] bases, int steps) {
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

		return colorList.toArray(new Color[0]);
	}

	public String toString() {
		return "Palleted Coloring";
	}
}

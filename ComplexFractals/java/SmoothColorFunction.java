/*
 * Decompiled with CFR 0.151.
 * 
 * Could not load the following classes:
 *  Complex
 *  PalletedColorFunction
 */
import java.awt.Color;

public class SmoothColorFunction extends PalletedColorFunction {
	public static SmoothColorFunction getRgb(int steps) {
		return new SmoothColorFunction(SmoothColorFunction.getColorSpan(RGB, steps));
	}

	public static SmoothColorFunction getRainbow(int steps) {
		return new SmoothColorFunction(SmoothColorFunction.getColorSpan(RAINBOW, steps));
	}

	public static SmoothColorFunction getSpectrum(int steps) {
		return new SmoothColorFunction(SmoothColorFunction.getColorSpan(SPECTRUM, steps));
	}

	public static SmoothColorFunction getForest(int steps) {
		return new SmoothColorFunction(SmoothColorFunction.getColorSpan(FOREST, steps));
	}

	public static SmoothColorFunction getSeaweed(int steps) {
		return new SmoothColorFunction(SmoothColorFunction.getColorSpan(SEAWEED, steps));
	}

	public static SmoothColorFunction getOcean(int steps) {
		return new SmoothColorFunction(SmoothColorFunction.getColorSpan(OCEAN, steps));
	}

	public static SmoothColorFunction getAqua(int steps) {
		return new SmoothColorFunction(SmoothColorFunction.getColorSpan(AQUA, steps));
	}

	public static SmoothColorFunction getSunset(int steps) {
		return new SmoothColorFunction(SmoothColorFunction.getColorSpan(SUNSET, steps));
	}

	public static SmoothColorFunction getTropical(int steps) {
		return new SmoothColorFunction(SmoothColorFunction.getColorSpan(TROPICAL, steps));
	}

	public static SmoothColorFunction getNeon(int steps) {
		return new SmoothColorFunction(SmoothColorFunction.getColorSpan(NEON, steps));
	}

	public static SmoothColorFunction getSpace(int steps) {
		return new SmoothColorFunction(SmoothColorFunction.getColorSpan(SPACE, steps));
	}

	public static SmoothColorFunction getIceFire(int steps) {
		return new SmoothColorFunction(SmoothColorFunction.getColorSpan(ICE_FIRE, steps));
	}

	public static SmoothColorFunction getZebra(int steps) {
		return new SmoothColorFunction(SmoothColorFunction.getColorSpan(ZEBRA, steps));
	}

	public static SmoothColorFunction getCandyCane(int steps) {
		return new SmoothColorFunction(SmoothColorFunction.getColorSpan(CANDY_CANE, steps));
	}

	public static SmoothColorFunction getMagma(int steps) {
		return new SmoothColorFunction(SmoothColorFunction.getColorSpan(MAGMA, steps));
	}

	public static SmoothColorFunction getLava(int steps) {
		return new SmoothColorFunction(SmoothColorFunction.getColorSpan(LAVA, steps));
	}

	public static SmoothColorFunction getPinkBlues(int steps) {
		return new SmoothColorFunction(SmoothColorFunction.getColorSpan(PINK_BLUES, steps));
	}

	public SmoothColorFunction() {
		super();
	}

	public SmoothColorFunction(Color[] pallet) {
		super(pallet);
	}

	public Color getDivergentColor(Complex complex, int n, int n2) {
		double d = (double)(n + 1) - Math.log(Math.log(complex.mod())) / Math.log(2.0);
		if (d < 0.0) {
			d = 0.0;
		}
		d = d / (double)(n2 + 1) * (double)this.pallet.length;
		Color color = this.pallet[(int)d % this.pallet.length];
		Color color2 = this.pallet[((int)d + 1) % this.pallet.length];
		d -= (double)((int)d);
		double d2 = 1.0 - d;
		int n3 = (int)Math.round((double)color.getRed() * d2 + (double)color2.getRed() * d);
		int n4 = (int)Math.round((double)color.getGreen() * d2 + (double)color2.getGreen() * d);
		int n5 = (int)Math.round((double)color.getBlue() * d2 + (double)color2.getBlue() * d);
		try {
			return new Color(n3, n4, n5);
		}
		catch (Exception exception) {
			System.out.println(n3 + " " + n4 + " " + n5);
			System.out.println(color.getGreen() + " * " + d2 + " + " + color2.getGreen() + " * " + d);
			return new Color(n3, n4, n5);
		}
	}

	public String toString() {
		return "Smooth Coloring";
	}
}

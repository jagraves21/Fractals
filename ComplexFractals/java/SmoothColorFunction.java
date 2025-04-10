import java.util.ArrayList;
import java.awt.Color;
import java.util.List;

public class SmoothColorFunction extends AbstractColorFunction {
	protected Color[] pallet;
	protected String name;
	
	public static SmoothColorFunction getTEST(int steps) {
		return new SmoothColorFunction(
			generateColorGradient(TEST, steps),
			"TEST (S)"
		);
	}

	public static SmoothColorFunction getRGB(int steps) {
		return new SmoothColorFunction(
			generateColorGradient(RGB, steps),
			"RGB (S)"
		);
	}

	public static SmoothColorFunction getRainbow(int steps) {
		return new SmoothColorFunction(
			generateColorGradient(RAINBOW, steps),
			"Rainbow (S)"
		);
	}

	public static SmoothColorFunction getSpectrum(int steps) {
		return new SmoothColorFunction(
			generateColorGradient(SPECTRUM, steps),
			"Spectrum (S)"
		);
	}

	public static SmoothColorFunction getForest(int steps) {
		return new SmoothColorFunction(
			generateColorGradient(FOREST, steps),
			"Forest (S)"
		);
	}

	public static SmoothColorFunction getSeaweed(int steps) {
		return new SmoothColorFunction(
			generateColorGradient(SEAWEED, steps),
			"Seaweed (S)"
		);
	}

	public static SmoothColorFunction getOcean(int steps) {
		return new SmoothColorFunction(
			generateColorGradient(OCEAN, steps),
			"Ocean (S)"
		);
	}

	public static SmoothColorFunction getAqua(int steps) {
		return new SmoothColorFunction(
			generateColorGradient(AQUA, steps),
			"Aqua (S)"
		);
	}

	public static SmoothColorFunction getSunset(int steps) {
		return new SmoothColorFunction(
			generateColorGradient(SUNSET, steps),
			"Sunset (S)"
		);
	}

	public static SmoothColorFunction getTropical(int steps) {
		return new SmoothColorFunction(
			generateColorGradient(TROPICAL, steps),
			"Tropical (S)"
		);
	}

	public static SmoothColorFunction getNeon(int steps) {
		return new SmoothColorFunction(
			generateColorGradient(NEON, steps),
			"Neon (S)"
		);
	}

	public static SmoothColorFunction getSpace(int steps) {
		return new SmoothColorFunction(
			generateColorGradient(SPACE, steps),
			"Space (S)"
		);
	}
	
	public static SmoothColorFunction getTwilight(int steps) {
		return new SmoothColorFunction(
			generateColorGradient(TWILIGHT, steps),
			"Twilight (S)"
		);
	}

	public static SmoothColorFunction getIceFire(int steps) {
		return new SmoothColorFunction(
			generateColorGradient(ICE_FIRE, steps),
			"IceFire (S)"
		);
	}

	public static SmoothColorFunction getZebra(int steps) {
		return new SmoothColorFunction(
			generateColorGradient(ZEBRA, steps),
			"Zebra (S)"
		);
	}

	public static SmoothColorFunction getCandyCane(int steps) {
		return new SmoothColorFunction(
			generateColorGradient(CANDY_CANE, steps),
			"CandyCane (S)"
		);
	}

	public static SmoothColorFunction getMagma(int steps) {
		return new SmoothColorFunction(
			generateColorGradient(MAGMA, steps),
			"Magma (S)"
		);
	}

	public static SmoothColorFunction getLava(int steps) {
		return new SmoothColorFunction(
			generateColorGradient(LAVA, steps),
			"Lava (S)"
		);
	}

	public static SmoothColorFunction getPinkBlue(int steps) {
		return new SmoothColorFunction(
			generateColorGradient(PINK_BLUE, steps),
			"PinkBlue (S)"
		);
	}
	
	public static SmoothColorFunction getOilSlick(int steps) {
		return new SmoothColorFunction(
			generateColorGradient(OIL_SLICK, steps),
			"OilSlick (S)"
		);
	}
	
	public static SmoothColorFunction getGasOnWater(int steps) {
		return new SmoothColorFunction(
			generateColorGradient(GAS_ON_WATER, steps),
			"GasOnWater (S)"
		);
	}
	
	public SmoothColorFunction() {
		this(RAINBOW, "Rainbow (S)");
	}
	
	public SmoothColorFunction(Color[] pallet) {
		this(pallet, "Custom");
	}

	public SmoothColorFunction(Color[] pallet, String name) {
		this.pallet = pallet;
		this.name = name;
	}

	public Color getDivergentColor(Complex complex, int iteration, int maxIteration) {
		double d = (double)(iteration + 1) - Math.log(Math.log(complex.modulus())) / Math.log(2.0);
		if (d < 0.0) {
			d = 0.0;
		}
		d = d / (double)(maxIteration + 1) * (double)this.pallet.length;
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
		return name;
	}
}

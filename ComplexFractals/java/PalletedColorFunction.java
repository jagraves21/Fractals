import java.util.ArrayList;
import java.awt.Color;
import java.util.List;

public class PalletedColorFunction extends AbstractColorFunction {
	protected Color[] pallet;
	protected String name;
	
	public static PalletedColorFunction getTEST(int steps) {
		return new PalletedColorFunction(
			generateColorGradient(TEST, steps),
			"TEST"
		);
	}

	public static PalletedColorFunction getRGB(int steps) {
		return new PalletedColorFunction(
			generateColorGradient(RGB, steps),
			"RGB"
		);
	}

	public static PalletedColorFunction getRainbow(int steps) {
		return new PalletedColorFunction(
			generateColorGradient(RAINBOW, steps),
			"Rainbow"
		);
	}

	public static PalletedColorFunction getSpectrum(int steps) {
		return new PalletedColorFunction(
			generateColorGradient(SPECTRUM, steps),
			"Spectrum"
		);
	}

	public static PalletedColorFunction getForest(int steps) {
		return new PalletedColorFunction(
			generateColorGradient(FOREST, steps),
			"Forest"
		);
	}

	public static PalletedColorFunction getSeaweed(int steps) {
		return new PalletedColorFunction(
			generateColorGradient(SEAWEED, steps),
			"Seaweed"
		);
	}

	public static PalletedColorFunction getOcean(int steps) {
		return new PalletedColorFunction(
			generateColorGradient(OCEAN, steps),
			"Ocean"
		);
	}

	public static PalletedColorFunction getAqua(int steps) {
		return new PalletedColorFunction(
			generateColorGradient(AQUA, steps),
			"Aqua"
		);
	}

	public static PalletedColorFunction getSunset(int steps) {
		return new PalletedColorFunction(
			generateColorGradient(SUNSET, steps),
			"Sunset"
		);
	}

	public static PalletedColorFunction getTropical(int steps) {
		return new PalletedColorFunction(
			generateColorGradient(TROPICAL, steps),
			"Tropical"
		);
	}

	public static PalletedColorFunction getNeon(int steps) {
		return new PalletedColorFunction(
			generateColorGradient(NEON, steps),
			"Neon"
		);
	}

	public static PalletedColorFunction getSpace(int steps) {
		return new PalletedColorFunction(
			generateColorGradient(SPACE, steps),
			"Space"
		);
	}
	
	public static PalletedColorFunction getTwilight(int steps) {
		return new PalletedColorFunction(
			generateColorGradient(TWILIGHT, steps),
			"Twilight"
		);
	}

	public static PalletedColorFunction getIceFire(int steps) {
		return new PalletedColorFunction(
			generateColorGradient(ICE_FIRE, steps),
			"IceFire"
		);
	}

	public static PalletedColorFunction getZebra(int steps) {
		return new PalletedColorFunction(
			generateColorGradient(ZEBRA, steps),
			"Zebra"
		);
	}

	public static PalletedColorFunction getCandyCane(int steps) {
		return new PalletedColorFunction(
			generateColorGradient(CANDY_CANE, steps),
			"CandyCane"
		);
	}

	public static PalletedColorFunction getMagma(int steps) {
		return new PalletedColorFunction(
			generateColorGradient(MAGMA, steps),
			"Magma"
		);
	}

	public static PalletedColorFunction getLava(int steps) {
		return new PalletedColorFunction(
			generateColorGradient(LAVA, steps),
			"Lava"
		);
	}

	public static PalletedColorFunction getPinkBlue(int steps) {
		return new PalletedColorFunction(
			generateColorGradient(PINK_BLUE, steps),
			"PinkBlue"
		);
	}
	
	public static PalletedColorFunction getOilSlick(int steps) {
		return new PalletedColorFunction(
			generateColorGradient(OIL_SLICK, steps),
			"OilSlick"
		);
	}
	
	public static PalletedColorFunction getGasOnWater(int steps) {
		return new PalletedColorFunction(
			generateColorGradient(GAS_ON_WATER, steps),
			"GasOnWater"
		);
	}
	
	public PalletedColorFunction() {
		this(ZEBRA, "Default");
	}
	
	public PalletedColorFunction(Color[] pallet) {
		this(pallet, "Custom");
	}

	public PalletedColorFunction(Color[] pallet, String name) {
		this.pallet = pallet;
		this.name = name;
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
	}

	public String toString() {
		return name;
	}
}

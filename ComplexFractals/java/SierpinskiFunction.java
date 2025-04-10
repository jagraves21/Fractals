public class SierpinskiFunction extends AbstractComplexFunction {
	public SierpinskiFunction() {
		super();
		init();
	}

	public double getOriginX() {
		return 0.5;
	}
	public double getOriginY() {
		return 0.5;
	}
	public double getWindowWidth() {
		return 1.2;
	}

	public void init() {
		super.init();
		c.re = 0.0; c.im = 0.0;
	}

	public void convert(Complex z, Complex mu) {
		mu.re = z.re;
		mu.im = z.im;
	}

	public void next(Complex z, Complex mu) {
		if(z.im > 0.5) {
			z.re *= 2;
			z.im *= 2;
			z.im--;
		}   
		else if(z.re > 0.5) {
			z.re *= 2;
			z.im *= 2;
			z.re--;
		}   
		else {
			z.re *= 2;
			z.im *= 2;
		}
		z.re += c.re;
		z.im += c.im;
	}

	public ColorFunction getSuggestedColorFunction() {
		java.awt.Color[] c = {java.awt.Color.BLUE, java.awt.Color.YELLOW};
		return new PalletedColorFunction(PalletedColorFunction.generateColorGradient(
			c, 15
		));
	}

	public String toString() {
		return "Sierpinski Triangle";
	}

	public static void main(String[] args) {
		AbstractComplexFunction.main(args);
	}
}

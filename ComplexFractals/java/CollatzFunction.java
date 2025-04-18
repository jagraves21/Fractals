public class CollatzFunction extends AbstractComplexFunction {
	public CollatzFunction() {
		super();
		init();
	}

	public double getOriginX() {
		return 0.25;
	}

	public double getViewWidth() {
		return 2;
	}

	public void convert(Complex z, Complex mu) {
		mu.re = z.re;
		mu.im = z.im;
	}
	
	public void next(Complex z, Complex mu) {
		next1(z, mu);
	}

	public void next1(Complex z, Complex mu) {
		double x = z.re; // real part
		double y = z.im; // imaginary part

		// Precompute constants
		double piX = Math.PI * x;
		double piY = Math.PI * y;

		// Trig/hyperbolic components
		double cos_piX = Math.cos(piX);
		double sin_piX = Math.sin(piX);
        double cosh_piY = Complex.cosh(piY);
        double sinh_piY = Complex.sinh(piY);

        // Real part calculation
        double real = 0.25 * (
            (7 * x + 2)
            - (
				(5 * x + 2) * cos_piX * cosh_piY
				+ 5 * y * sin_piX * sinh_piY
			)
        );

        // Imaginary part calculation
        double imag = 0.25 * (
            (7 * y)
            + (
				(5 * x + 2) * sin_piX * sinh_piY
				- 5 * y * cos_piX * cosh_piY
			)
        );

		z.re = real + c.re;
		z.im = imag + c.im;
	}

	public void next2(Complex z, Complex mu) {
		// 0.25 * (7z+2−(5z+2)⋅cos(πz))
		Complex res = z.times(7).plus(2).minus(z.times(5).plus(2).times(z.times(Math.PI).cos())).times(0.25);
		z.re = res.re + c.re;
		z.im = res.im + c.im;
	}

	public ConvergenceFunction getSuggestedConvergenceFunction() {
		return new ModulusFiftyConvergence();
	}

	public ColorFunction getSuggestedColorFunction() {
		return PalletedColorFunction.getRainbow(3);
	}

	public String toString() {
		return "Collatz Set";
	}

	public static void main(String[] args) {
		AbstractComplexFunction.main(args);
	}
}

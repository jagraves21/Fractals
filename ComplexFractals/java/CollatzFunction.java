public class CollatzFunction extends AbstractComplexFunction {
	public CollatzFunction() {
		super();
		init();
	}

	public double getOriginX() {
		return 0.25;
	}

	public double getWindowWidth() {
		return 2;
	}

	public void convert(Complex z, Complex mu) {
		mu.re = z.re;
		mu.im = z.im;
	}

	public void next(Complex z, Complex mu) {
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

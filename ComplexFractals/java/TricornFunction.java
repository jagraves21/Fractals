public class TricornFunction extends AbstractComplexFunction {
	public TricornFunction() {
		super();
		init();
	}

	public double getOriginX() {
		return -0.35;
	}
	public double getOriginY() {
		return 0;
	}
	public double getWindowWidth() {
		return 3.75;
	}

	public void init() {
		super.init();
	}

	public void convert(Complex z, Complex mu) {
		mu.re = z.re;
		mu.im = z.im;
	}

	public void next(Complex z, Complex mu) {
		double temp = z.re*z.re - z.im*z.im;
		z.im = -2*z.re*z.im;
		z.re = temp;

		z.re += mu.re + c.re;
		z.im += mu.im + c.im;
	}

	public ColorFunction getSuggestedColorFunction() {
		return PalletedColorFunction.getRainbow(5);
		//return SmoothColorFunction.getRainbow(5);
	}

	public String toString() {
		return "Tricorn Set";
	}

	public static void main(String[] args) {
		AbstractComplexFunction.main(args);
	}
}

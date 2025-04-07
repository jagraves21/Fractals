public class Spider extends AbstractComplexFunction {
	public Spider() {
		super();
		init();
	}

	public double getOriginX() {
		return 0;
	}
	public double getOriginY() {
		return 0.5;
	}
	public double getWindowWidth() {
		return 3;
	}

	public void init() {
		super.init();
	}

	public void convert(Complex z, Complex mu) {
		double temp = z.im;
		z.im = z.re;
		z.re = -temp;
		mu.re = z.re; mu.im = z.im;
	}

	public void next(Complex z, Complex mu) {
		Complex res1 = z.power(2).plus(mu);
		Complex res2 = mu.times(0.5).plus(res1);

		z.re = res1.re;
		z.im = res1.im;

		mu.re = res2.re + c.re;
		mu.im = res2.im + c.im;
	}

	public ConvergenceFunction getSuggestedConvergenceFunction() {
		return new RealTwoConvergence();
	}

	public ColorFunction getSuggestedColorFunction() {
		return new PalletedColorFunction();
		//return new SmoothColorFunction();
	}

	public String toString() {
		return "Spider";
	}

	public static void main(String[] args) {
		AbstractComplexFunction.main(args);
	}
}

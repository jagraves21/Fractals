public class ModifiedCollatzFunction extends AbstractComplexFunction {
	public ModifiedCollatzFunction() {
		super();
		init();
	}

	public double getOriginX() {
		//return -7.7124366603572145;
		return 3.94939259635873;
	}
	public double getWindowWidth() {
		return 1;
	}

	public void init() {
		super.init();
		thetaOff = 0.5;
		c.re = 0; c.im = 0;
	}

	public void convert(Complex z, Complex mu) {
		mu.re = z.re;
		mu.im = z.im;
	}

	public void next(Complex z, Complex mu) {
		Complex res1 = z.times(0.25).times(z.times(Math.PI).cos().plus(1));
		Complex res2 = z.times(3).plus(1).divide(16);
		Complex res3 = z.times(Math.PI).cos().times(-1).plus(1);
		Complex res4 = z.times(2).minus(1).times(Math.PI*0.25).cos().times(-Math.sqrt(2)).plus(3);
		Complex res = res1.plus(res2.times(res3).times(res4));
		z.re = res.re + c.re;
		z.im = res.im + c.im;
	}

	public ConvergenceFunction getSuggestedConvergenceFunction() {
		return new ModulusFifty();
	}

	public ColorFunction getSuggestedColorFunction() {
		return PalletedColorFunction.getRedBlue(3);
	}

	public String toString() {
		return "Modified Collatz Set";
	}

	public static void main(String[] args) {
		AbstractComplexFunction.main(args);
	}
}
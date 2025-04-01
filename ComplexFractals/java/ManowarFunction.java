public class ManowarFunction extends AbstractComplexFunction {
	public double getOriginX() {
		return -0.75;
	}

	public void init() {
		c.re = 0; c.im = 0;

		a = 0;
		b = 0.05;
		theta = 0;
		thetaOff = 0.05;
	}

	public void convert(Complex z, Complex mu) {
		mu.re = z.re + c.re;
		mu.im = z.im + c.im;
	}

	public void next(Complex z, Complex mu) {
		Complex res = z.power(2).plus(mu);

		mu.re = z.re;
		mu.im = z.im;

		z.re = res.re;
		z.im = res.im;
	}

	public ColorFunction getColorFunction() {
		return PalletedColorFunction.getBlackRedYellow(10);
	}
	
	public String toString() {
		return "Manowar";
	}

	public static void main(String[] args) {
		AbstractComplexFunction.main(args);
	}
}

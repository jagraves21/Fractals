public class MandelbrotFunction extends AbstractComplexFunction {
	public MandelbrotFunction() {
		super();
		init();
	}

	public double getOriginX() {
		return -0.75;
	}

	public void init() {
		super.init();
		b = 0.05;
		thetaOff = 0.05;
		c.re = 0; c.im = 0;
	}

	public void convert(Complex z, Complex mu) {
		mu.re = z.re + c.re;
		mu.im = z.im + c.im;
	}

	public void next(Complex z, Complex mu) {
		//Complex res = z.power(2).plus(mu);
		double temp = z.re*z.re - z.im*z.im + mu.re;
		z.im = 2*z.re*z.im + mu.im;
		z.re = temp;
	}
	
	public ColorFunction getSuggestedColorFunction() {
		return PalletedColorFunction.getRainbow(25);
	}

	public String toString() {
		return "Mandelbrot Set";
	}

	public static void main(String[] args) {
		AbstractComplexFunction.main(args);
	}
}

public class GlynnFunction extends AbstractComplexFunction {
	protected double e;

	public GlynnFunction() {
		super();
		init();
	}

	public double getOriginY() {
		return -0.5;
	}
	public double getWindowWidth() {
		return 0.4;
	}

	public void init() {
		super.init();
		e = 1.5;
	}

	public void move() {
		e -= 0.001;
	}

	public void convert(Complex z, Complex mu) {
		mu.re = -0.2;
		mu.im = 0.0;

		double temp = z.re;
		z.re = - z.im;
		z.im = temp;

		temp = z.re;
		z.re = - z.im;
		z.im = temp;

		temp = z.re;
		z.re = - z.im;
		z.im = temp;
	}

	public void next(Complex z, Complex mu) {
		double m = Math.pow(Math.sqrt(z.re*z.re + z.im*z.im), e);
		double a = Math.atan2(z.im, z.re) * e;
		double temp = m * Math.cos(a);
		z.im = m * Math.sin(a);
		z.re = temp;

		z.re += mu.re;
		z.im += mu.im;
	}

	public ConvergenceFunction getSuggestedConvergenceFunction() {
		return new ModulusFour();
	}

	public ColorFunction getSuggestedColorFunction() {
		return PalletedColorFunction.getBlues(5);
	}

	public String toString() {
		return "Glynn Set";
	}

	public static void main(String[] args) {
		AbstractComplexFunction.main(args);
	}
}
public class BurningShip extends AbstractComplexFunction {
	public BurningShip() {
		super();
		init();
	}

	public double getOriginX() {
		return -1.7526674680844139;
	}
	public double getOriginY() {
		return -0.036522864408293804;
	}
	public double getWindowWidth() {
		return 0.10082811617813109;
	}

	public void init() {
		super.init();
		thetaOff = 0.005;
		c.re = 0; c.im = 0;
	}

	public void convert(Complex z, Complex mu) {
		mu.re = z.re + c.re;
		mu.im = z.im + c.re;
	}

	public void next(Complex z, Complex mu) {
		double temp = z.re*z.re - z.im*z.im;
		z.im = 2*Math.abs(z.re*z.im);
		z.re = temp;

		z.re += mu.re;
		z.im += mu.im;
	}

	public ColorFunction getSuggestedColorFunction() {
		return SmoothColorFunction.getRedBlue(10);
	}

	public String toString() {
		return "Burning Ship";
	}

	public static void main(String[] args) {
		AbstractComplexFunction.main(args);
	}
}
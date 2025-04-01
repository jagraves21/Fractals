public class MandelbrotFunction extends AbstractComplexFunction {
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
		double temp = z.re*z.re - z.im*z.im + mu.re;
		z.im = 2*z.re*z.im + mu.im;
		z.re = temp;
	}

	public ColorFunction getColorFunction() {
		return PalletedColorFunction.getRainbow(25);
	}
	
	public String toString() {
		return "Mandelbrot Set";
	}

	public static void main(String[] args) {
		AbstractComplexFunction.main(args);
	}
}

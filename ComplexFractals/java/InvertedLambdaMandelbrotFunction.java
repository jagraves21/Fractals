public class InvertedLambdaMandelbrotFunction extends InvertedMandelbrotFunction {
	public InvertedLambdaMandelbrotFunction() {
		super();
		init();
	}

	public double getOriginX() {
		return 0;
	}
	public double getOriginY() {
		return 0;
	}
	public double getWindowWidth() {
		return 2;
	}

	public void convert(Complex z, Complex mu) {
		double temp = z.re/(z.re*z.re+z.im*z.im);
		z.im = -z.im/(z.re*z.re+z.im*z.im);
		z.re = temp;

		z.re /= 2.0;
		z.im /= 2.0;

		temp = z.re*z.re - z.im*z.im - z.re;
		z.im = 2*z.re*z.im - z.im;
		z.re = temp;

		mu.re = -z.re + c.re;
		mu.im = -z.im + c.im;
	}

	public ColorFunction getColorFunction() {
		return PalletedColorFunction.getRainbow(25);
	}
	
	public String toString() {
		return "Mandelbrot Set (1/\u03BB)";
	}

	public static void main(String[] args) {
		AbstractComplexFunction.main(args);
	}
}

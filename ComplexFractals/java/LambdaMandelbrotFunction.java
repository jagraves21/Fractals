public class LambdaMandelbrotFunction extends MandelbrotFunction {
	public LambdaMandelbrotFunction() {
		super();
		init();
	}

	public double getOriginX() {
		return -1;
	}
	public double getOriginY() {
		return 0;
	}
	public double getWindowWidth() {
		return 6;
	}

	public void convert(Complex z, Complex mu) {
		z.re /= 2.0;
		z.im /= 2.0;

		double temp = z.re*z.re - z.im*z.im + z.re;
		z.im = 2*z.re*z.im + z.im;
		z.re = temp;

		mu.re = -z.re + c.re;
		mu.im = -z.im + c.im;
	}

	public String toString() {
		return "Mandelbrot Set (\u03BB)";
	}

	public static void main(String[] args) {
		AbstractComplexFunction.main(args);
	}
}
public class CubicMandelbrotFunction extends MandelbrotFunction {
	public CubicMandelbrotFunction() {
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
		return 2.75;
	}

	public void next(Complex z, Complex mu) {
		double temp = z.re*z.re*z.re - 3*z.re*z.im*z.im;
		z.im = 3*z.re*z.re*z.im - z.im*z.im*z.im;
		z.re = temp;

		z.re += mu.re;
		z.im += mu.im;
	}

	public ColorFunction getSuggestedColorFunction() {
		return PalletedColorFunction.getRedBlue(5);
	}

	public String toString() {
		return "Cubic Mandelbrot Set";
	}

	public static void main(String[] args) {
		AbstractComplexFunction.main(args);
	}
}
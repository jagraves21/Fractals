public class CosMandelbrotFunction extends MandelbrotFunction
{
	public double getOriginX() {
		return 0;
	}
	public double getOriginY() {
		return 0;
	}
	public double getWindowWidth() {
		return 6;
	}

	public void next(Complex z, Complex mu) {
		double temp = Math.cos(z.re)*Math.cosh(z.im);
		z.im = Math.sin(z.re)*Math.sinh(z.im);
		z.re = temp;

		temp = z.re*mu.re - z.im*mu.im;
		z.im = z.re*mu.im + z.im*mu.re;
		z.re = temp;

		temp = z.im;
		z.im = z.re;
		z.re = -1*temp;
	}

	public ConvergenceFunction getConvergenceFunction() {
		return new ModulusFiftyMod();
	}

	public ColorFunction getColorFunction() {
		return new PalletedColorFunction(
			PalletedColorFunction.getColorSpan(PalletedColorFunction.BLACK_RED_YELLOW, 10)
		);
	}

	public String toString() {
		return "Cosine Mandelbrot Set";
	}

	public static void main(String[] args) {
		AbstractComplexFunction.main(args);
	}
}

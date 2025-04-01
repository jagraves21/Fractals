public class MandeldiskFunction extends InvertedMandelbrotFunction
{
	public double getOriginX() {
		return 0;
	}
	public double getOriginY() {
		return 0;
	}
	public double getWindowWidth() {
		return 8;
	}

	public void convert(Complex z, Complex mu) {
		double temp = z.re/(z.re*z.re+z.im*z.im)-1;
		z.im = -z.im/(z.re*z.re+z.im*z.im);
		z.re = temp;

		mu.re = z.re + c.re;
		mu.im = z.im + c.im;
	}

	public ColorFunction getColorFunction() {
		//return PalletedColorFunction.getRainbow(13);
		return new SmoothColorFunction(PalletedColorFunction.getColorSpan(PalletedColorFunction.BLACK_RED_YELLOW, 20));
	}
	
	public String toString() {
		return "Mandeldisk Set";
	}

	public static void main(String[] args) {
		AbstractComplexFunction.main(args);
	}
}

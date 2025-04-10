public class MandeldiskFunction extends InvertedMandelbrotFunction {
	public MandeldiskFunction() {
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
		return 8;
	}

	public void convert(Complex z, Complex mu) {
		double temp = z.re/(z.re*z.re+z.im*z.im)-1;
		z.im = -z.im/(z.re*z.re+z.im*z.im);
		z.re = temp;

		mu.re = z.re + c.re;
		mu.im = z.im + c.im;
	}

	public ColorFunction getSuggestedColorFunction() {
		//return PalletedColorFunction.getRainbow(13);
		return SmoothColorFunction.getLava(20);
	}

	public String toString() {
		return "Mandeldisk Set";
	}

	public static void main(String[] args) {
		AbstractComplexFunction.main(args);
	}
}

public class InvertedJuliaFunction extends JuliaFunction {
	public InvertedJuliaFunction() {
		super();
		init();
	}

	public double getViewWidth() {
		return 4;
	}

	public void init() {
		super.init();
		theta = 37.25;
		thetaOff = 0.001;
		//c.re = -0.4; c.im = 0.6;
		//c.re = 0.285; c.im = 0;
		c.re = 0.285; c.im = 0.01;
		//c.re = 0.45; c.im = 0.1428;
		//c.re = -0.835; c.im = 0.2321;
		//c.re = -0.8; c.im = 0.156;
		//c.re = -0.63; c.im = -0.407;
	}

	public void convert(Complex z, Complex mu) {
		double temp = z.re/(z.re*z.re+z.im*z.im);
		z.im = -z.im/(z.re*z.re+z.im*z.im);
		z.re = temp;

		mu.re = c.re;
		mu.im = c.im;
	}

	public ColorFunction getSuggestedColorFunction() {
		return PalletedColorFunction.getRainbow(10);
	}

	public ComplexFractal.FractalType getSuggestedFractalType() {
		return ComplexFractal.FractalType.ITERATIVE;
	}

	public String toString() {
		return "Julia Set (1/\u03BC)";
	}

	public static void main(String[] args) {
		AbstractComplexFunction.main(args);
	}
}

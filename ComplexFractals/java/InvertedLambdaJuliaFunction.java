public class InvertedLambdaJuliaFunction extends JuliaFunction {
	public double getWindowWidth() {
		return 18;
	}

	public void init() {
		c.re = -0.4; c.im = 0.6;
		//c.re = 0.285; c.im = 0;
		//c.re = 0.285; c.im = 0.01;
		//c.re = 0.45; c.im = 0.1428;
		//c.re = -0.835; c.im = 0.2321;
		//c.re = -0.8; c.im = 0.156;
		//c.re = -0.63; c.im = -0.407;

		a = 0;
		b = 0.01;
		theta = 55.46;
		thetaOff = 0.0001;
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

		mu.re = c.re;
		mu.im = c.im;
	}

	public ColorFunction getColorFunction() {
		return PalletedColorFunction.getRainbow(10);
	}

	public ComplexFractal.FractalType getFractalType() {
		return ComplexFractal.FractalType.ITERATIVE;
	}

	public String toString() {
		return "Julia Set (1/\u03BB)";
	}

	public static void main(String[] args) {
		AbstractComplexFunction.main(args);
	}
}

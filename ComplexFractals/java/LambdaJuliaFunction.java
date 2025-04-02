public class LambdaJuliaFunction extends JuliaFunction {
	public LambdaJuliaFunction() {
		super();
		init();	
	}
	
	public double getOriginX() {
		return -1;
	}
	public double getWindowWidth() {
		return 6;
	}

	public void init() {
		super.init();
		a = 0;
		b = 0.01;
		theta = 55.35;
		thetaOff = 0.005;
		c.re = -0.4; c.im = 0.6;
		//c.re = 0.285; c.im = 0;
		//c.re = 0.285; c.im = 0.01;
		//c.re = 0.45; c.im = 0.1428;
		//c.re = -0.835; c.im = 0.2321;
		//c.re = -0.8; c.im = 0.156;
		//c.re = -0.63; c.im = -0.407;
	}

	public void convert(Complex z, Complex mu) {
		z.re /= 2.0;
		z.im /= 2.0;

		double temp = z.re*z.re - z.im*z.im + z.re;
		z.im = 2*z.re*z.im + z.im;
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
		return "Julia Set (\u03BB)";
	}

	public static void main(String[] args) {
		AbstractComplexFunction.main(args);
	}
}

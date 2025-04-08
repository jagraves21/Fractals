public class SinJuliaFunction extends JuliaFunction {
	public SinJuliaFunction() {
		super();
		init();
	}

	public double getWindowWidth() {
		return 12.0;
	}

	public void init() {
		super.init();
		//c.re = 1; c.im = 0;
		//c.re = 1; c.im = 0.1;
		c.re = 1; c.im = 0.2;
		//c.re = 1; c.im = 0.3;
		//c.re = 1; c.im = 0.4;
		//c.re = 1; c.im = 0.5;
	}

	public void next(Complex z, Complex mu) {
		double temp = Math.sin(z.re)*Math.cosh(z.im);
		z.im = Math.cos(z.re)*Math.sinh(z.im);
		z.re = temp;

		temp = z.re*mu.re - z.im*mu.im;
		z.im = z.re*mu.im + z.im*mu.re;
		z.re = temp;
	}
	
	public ConvergenceFunction getSuggestedConvergenceFunction() {
		return new ModulusFiftyConvergence();
	}

	public ColorFunction getSuggestedColorFunction() {
		return PalletedColorFunction.getRGB(10);
	}

	public ComplexFractal.FractalType getSuggestedFractalType() {
		return ComplexFractal.FractalType.ITERATIVE;
	}

	public String toString() {
		return "Sine Julia Set";
	}

	public static void main(String[] args) {
		AbstractComplexFunction.main(args);
	}
}

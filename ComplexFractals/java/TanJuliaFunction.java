public class TanJuliaFunction extends JuliaFunction {
	public TanJuliaFunction() {
		super();
		init();
	}

	public double getWindowWidth() {
		return 4.0;
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
		double temp = Math.sin(2*z.re)/(Math.cos(2*z.re)+Math.cosh(2*z.im));
		z.im = Math.sinh(2*z.im)/(Math.cos(2*z.re)+Math.cosh(2*z.im));
		z.re = temp;

		temp = z.re*mu.re - z.im*mu.im;
		z.im = z.re*mu.im + z.im*mu.re;
		z.re = temp;
	}

	public ColorFunction getSuggestedColorFunction() {
		return PalletedColorFunction.getRainbow(5);
	}

	public ComplexFractal.FractalType getSuggestedFractalType() {
		return ComplexFractal.FractalType.ITERATIVE;
	}

	public String toString() {
		return "Tangent Julia Set";
	}

	public static void main(String[] args) {
		AbstractComplexFunction.main(args);
	}
}

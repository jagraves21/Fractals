public class JuliaFunction extends AbstractComplexFunction {
	public JuliaFunction() {
		super();
		init();
	}

	public void init() {
		super.init();
		a = 0;
		b = 0.01;
		theta = 60;
		//theta = 76.7;
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
		mu.re = c.re;
		mu.im = c.im;
	}

	public void next(Complex z, Complex mu) {
		double temp = z.re*z.re - z.im*z.im + mu.re;
		z.im = 2*z.re*z.im + mu.im;
		z.re = temp;
	}

	public ColorFunction getColorFunction() {
		return SmoothColorFunction.getBlackRedYellow(15);
	}
	
	public ComplexFractal.FractalType getFractalType() {
		//return ComplexFractal.FractalType.MOVING;
		return ComplexFractal.FractalType.ITERATIVE;
	}
	
	public String toString() {
		return "Julia Set";
	}

	public static void main(String[] args) {
		AbstractComplexFunction.main(args);
	}
}

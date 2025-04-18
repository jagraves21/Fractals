public class SinMandelbrotFunction extends MandelbrotFunction {
	public SinMandelbrotFunction() {
		super();
		init();
	}

	public double getOriginX() {
		return 0;
	}
	public double getOriginY() {
		return 0;
	}
	public double getViewWidth() {
		return 6;
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
		return SmoothColorFunction.getRainbow(1);
	}

	public ComplexFractal.FractalType getSuggestedFractalType() {
		return ComplexFractal.FractalType.ITERATIVE;
	}

	public String toString() {
		return "Sine Mandelbrot Set";
	}

	public static void main(String[] args) {
		AbstractComplexFunction.main(args);
	}
}

public class QuarticMandelbrotFunction extends MandelbrotFunction {
	public QuarticMandelbrotFunction() {
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
		return 3;
	}  

	public void init() {
		super.init();
		c.re = 0.0; c.im = 0.0;
	}

	public void convert(Complex z, Complex mu) {
		mu.re = z.re + c.re;
		mu.im = z.im + c.im;
	}

	public void next(Complex z, Complex mu) {
		double temp = z.re*z.re*z.re*z.re - 6*z.re*z.re*z.im*z.im + z.im*z.im*z.im*z.im + mu.re;
        z.im = 4*z.re*z.re*z.re*z.im - 4*z.re*z.im*z.im*z.im + mu.im;
        z.re = temp;
	}

	public ColorFunction getSuggestedColorFunction() {
		return PalletedColorFunction.getRainbow(3);
	}

	public ComplexFractal.FractalType getSuggestedFractalType() {
		return ComplexFractal.FractalType.ITERATIVE;
	}

	public String toString() {
		return "Quartic Mandelbrot Set";
	}

	public static void main(String[] args) {
		AbstractComplexFunction.main(args);
	}
}

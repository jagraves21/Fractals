public class QuarticJuliaFunction extends JuliaFunction {
	public QuarticJuliaFunction() {
		super();
		init();
	}

	public double getViewWidth() {
		return 2.75;
	}

	public void init() {
		super.init();
		//c.re = 0.4594607043643477; c.im = -0.8906783315011968;
		//c.re = 0.49818325168178546; c.im = -0.6228171754164098;
		//c.re = 0.6284296740900919; c.im = 0.38586854406782045;
		//c.re = 0.7057787525717916; c.im = 0.4190615286593895;
		c.re = -0.7461288870884397; c.im = 0.2855441787456068;
	}

	public void next(Complex z, Complex mu) {
		double temp = z.re*z.re*z.re*z.re - 6*z.re*z.re*z.im*z.im + z.im*z.im*z.im*z.im + mu.re;
		z.im = 4*z.re*z.re*z.re*z.im - 4*z.re*z.im*z.im*z.im + mu.im;
		z.re = temp;
	}

	public ColorFunction getSuggestedColorFunction() {
		return PalletedColorFunction.getRainbow(5);
	}

	public ComplexFractal.FractalType getSuggestedFractalType() {
		return ComplexFractal.FractalType.ITERATIVE;
	}

	public String toString() {
		return "Quartic Julia Set";
	}

	public static void main(String[] args) {
		AbstractComplexFunction.main(args);
	}
}

public class Infinity extends AbstractComplexFunction {
	public Infinity() {
		super();
		init();
	}

	public double getWindowWidth() {
		return 5;
	}
	
	public void init() {
		super.init();
		thetaOff = 0.5;
		c.re = 0; c.im = 0;
	}
	
	public void convert(Complex z, Complex mu) {
		double temp = z.re/(z.re*z.re+z.im*z.im);
		z.im = -z.im/(z.re*z.re+z.im*z.im);
		z.re = temp;
		
		Complex res = z.rotate(57 * Math.PI / 180.0);
		z.re = res.re + c.re;
		z.im = res.im + c.im;
		
		mu.re = 0.285 + c.re;
		mu.im = 0.01 + c.im;
	}
	
	public void next(Complex z, Complex mu) {
		double temp = z.re*z.re - z.im*z.im + mu.re;
		z.im = 2*z.re*z.im + mu.im;
		z.re = temp;
	}

	public ColorFunction getColorFunction() {
		return PalletedColorFunction.getRainbow(10);
	}

	public ComplexFractal.FractalStyle getFractalStyle() {
		return ComplexFractal.FractalStyle.CONTOURED;
	}

	public String toString() {
		return "Infinity";
	}

	public static void main(String[] args) {
		AbstractComplexFunction.main(args);
	}
}

public class Infinity extends AbstractComplexFunction {
	public double getWindowWidth() {
		return 5;
	}
	
	public void init() {
		c.re = 0; c.im = 0;
		
		a = 0;
		b = 0.01;
		theta = 0;
		thetaOff = 0.5;
	}
	
	public void convert(Complex z, Complex mu) {
		double temp = z.re/(z.re*z.re+z.im*z.im);
		z.im = -z.im/(z.re*z.re+z.im*z.im);
		z.re = temp;
		
		Complex res = z.rotate(57 * Math.PI / 180.0);
		z.re = res.re;
		z.im = res.im;
		
		mu.re = 0.285;// + c.re;
		mu.im = 0.01;// + c.im;
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

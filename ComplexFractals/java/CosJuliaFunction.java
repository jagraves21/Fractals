public class CosJuliaFunction extends JuliaFunction {
	public double getWindowWidth() {
		return 12;
	}

	public void init() {
		//c.re = 1; c.im = 0;
		//c.re = 1; c.im = 0.1;
		c.re = 1; c.im = 0.2;
		//c.re = 1; c.im = 0.3;
		//c.re = 1; c.im = 0.4;
		//c.re = 1; c.im = 0.5;

		a = 0;
		b = 0;
		theta = 0;
		thetaOff = 0;
	}

	public void next(Complex z, Complex mu) {
		double temp = Math.cos(z.re)*Math.cosh(z.im);
		z.im = Math.sin(z.re)*Math.sinh(z.im);
		z.re = temp;

		temp = z.re*mu.re - z.im*mu.im;
		z.im = z.re*mu.im + z.im*mu.re;
		z.re = temp;

		temp = z.im;
		z.im = z.re;
		z.re = -1*temp;
	}

	public ConvergenceFunction getConvergenceFunction() {
		return new ModulusFiftyMod();
	}
	
	public ColorFunction getColorFunction() {
		return PalletedColorFunction.getRedBlue(6);
	}

	public ComplexFractal.FractalType getFractalType() {
		return ComplexFractal.FractalType.ITERATIVE;
	}
	
	public String toString() {
		return "Cosine Julia Set";
	}

	public static void main(String[] args) {
		AbstractComplexFunction.main(args);
	}
}

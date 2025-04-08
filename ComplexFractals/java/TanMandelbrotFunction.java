public class TanMandelbrotFunction extends MandelbrotFunction {
	public TanMandelbrotFunction() {
		super();
		init();
	}

	public double getOriginX() {
		return 0;
	}   
	public double getOriginY() {
		return 0;
	}   
	public double getWindowWidth() {
		return 3;
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
		return PalletedColorFunction.getAqua(2);
	}

	public ComplexFractal.FractalType getSuggestedFractalType() {
		return ComplexFractal.FractalType.ITERATIVE;
	}

	public String toString() {
		return "Tangent Mandelbrot Set";
	}

	public static void main(String[] args) {
		AbstractComplexFunction.main(args);
	}
}

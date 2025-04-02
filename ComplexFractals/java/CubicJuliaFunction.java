public class CubicJuliaFunction extends JuliaFunction {
	public CubicJuliaFunction() {
		super();
		init();
	}

	public double getWindowWidth() {
		return 2.75;
	}
	
	public void init() {
		super.init();
		a = 0;
		b = 0.01;
		theta = 59;
		thetaOff = 0.01;
		c.re = 0.4; c.im = 0;
		//c.re = -0.4; c.im = 0.6;
		//c.re = -0.835; c.im = 0.2321;
		//c.re = -0.63; c.im = -0.407;
		//c.re = -0.6010055361351887; c.im = -0.6371499264493796;
		//c.re = 0.5407460878908774; c.im = 0.1566496372516717;
		//c.re = 0.1025585222236709; c.im = -0.7941841609541218;
		//c.re = 0.5579646218084231; c.im = -0.14883748762037508;
		//c.re = 0.48923549297880875; c.im = 0.05197582800437739;
		//c.re = -0.41662264629299717; c.im = 0.6311524039759373;
		//c.re = 0.155953756389009; c.im = 0.7599701396963163;
	}
	
	public void next(Complex z, Complex mu) {
		double temp = z.re*z.re*z.re - 3*z.re*z.im*z.im;
		z.im = 3*z.re*z.re*z.im - z.im*z.im*z.im;
		z.re = temp;
					
		z.re += mu.re;
		z.im += mu.im;
	}
	
	public ColorFunction getColorFunction() {
		return PalletedColorFunction.getRainbow(5);
	}

	public ComplexFractal.FractalType getFractalType() {
		return ComplexFractal.FractalType.ITERATIVE;
	}

	public String toString() {
		return "Cubic Julia Set";
	}

	public static void main(String[] args) {
		AbstractComplexFunction.main(args);
	}
}

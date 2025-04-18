public class VortexFunction extends AbstractComplexFunction {
	public VortexFunction() {
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
		//c.re = -0.4; c.im = 0.6;
		//c.re = 0.285; c.im = 0;
		//c.re = 0.285; c.im = 0.01;
		//c.re = 0.45; c.im = 0.1428;
		c.re = -0.835; c.im = 0.2321;
		//c.re = -0.8; c.im = 0.156;
		//c.re = -0.63; c.im = -0.407;
	}

	public void convert(Complex z, Complex mu) {
		mu.re = z.re;
		mu.im = z.im;
	}

	public void next(Complex z, Complex mu) {
		Complex res = z.power(2).plus(c.re).plus(mu.times(c.im));
        mu = z;
        z = res;

        mu.re = z.re;
        mu.im = z.im;
        z.re = res.re;
        z.im = res.im;
	}

	public ColorFunction getSuggestedColorFunction() {
		return PalletedColorFunction.getRainbow(25);
	}

	public String toString() {
		return "Vortex Set";
	}

	public static void main(String[] args) {
		AbstractComplexFunction.main(args);
	}
}

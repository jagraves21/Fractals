public class ExponentialCubicJuliaFunction extends AbstractComplexFunction {
	public ExponentialCubicJuliaFunction() {
		super();
		init();
	}

	public void init() {
		super.init();
		c.re = 0; c.im = 0;
	}

	public void timeIt(int count) {
		long startTime, endTime, diff;
		Complex z = new Complex();
		Complex mu = new Complex(2,1);

		z.re = 5; z.im = 3;
		startTime = System.nanoTime();
		for(int ii=0; ii < count; ii++) {
			next1(z, mu);
		}
		endTime = System.nanoTime();
		diff = endTime - startTime;
		System.out.println(diff + " " + diff/count);
		
		z.re = 5; z.im = 3;
		startTime = System.nanoTime();
		for(int ii=0; ii < count; ii++) {
			next2(z, mu);
		}
		endTime = System.nanoTime();
		diff = endTime - startTime;
		System.out.println(diff + " " + diff/count);
		System.out.println();
	}

	public double getViewWidth() {
		return 2;
	}
	
	public void move() {
		c.re += 0.0001;
		c.im -= 0.0001;
	}

	public void convert(Complex z, Complex mu) {
		mu.re = -0.59 + c.re;
		mu.im = 0 + c.im;
	}
	
	public void next(Complex z, Complex mu) {
		next2(z, mu);
	}

	public void next1(Complex z, Complex mu) {
		// z = x + iy
		double x = z.re; // real part of z
		double y = z.im; // imaginary part of z

		// mu = a + ib
		double a = mu.re; // real part of mu
		double b = mu.im; // imaginary part of mu

		// Step 1: z^3
		double u = Math.pow(x, 3) - 3 * x * Math.pow(y, 2);       // real part of z^3
		double v = 3 * Math.pow(x, 2) * y - Math.pow(y, 3);       // imag part of z^3

		// Step 2: e^(z^3)
		double exp_u = Math.exp(u);
		double realExp = exp_u * Math.cos(v);
		double imagExp = exp_u * Math.sin(v);

		// Step 3: Add mu
		z.re = realExp + a;
		z.im = imagExp + b;
	}

	public void next2(Complex z, Complex mu) {
		Complex res = z.power(3).exp().plus(mu);
		z.re = res.re;
		z.im = res.im;
	}

	public ColorFunction getSuggestedColorFunction() {
		return PalletedColorFunction.getRainbow(5);
	}
	
	public ComplexFractal.FractalStyle getSuggestedFractalStyle() {
		return ComplexFractal.FractalStyle.CONTOURED;
	}

	public String toString() {
		return "Exponential Cubic Julia Set";
	}

	public static void main(String[] args) {
		AbstractComplexFunction.main(args);
	}
}

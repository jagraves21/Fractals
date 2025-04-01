public class InvertedMandelbrotFunction extends MandelbrotFunction
{
	protected boolean right = true;

	public double getOriginX() {
		return 1.4;
	}
	public double getOriginY() {
		return 0;
	}
	public double getWindowWidth() {
		return 6;
	}

	public void init() {
		c.re = 0;
		c.im = 0;

		right = true;
	}

	public void move() {
		if(right) {
			c.re += 0.05;
			if(c.re > 1) {
				c.re = 1;
				right = false;
			}

		}
		else {
			c.re -= 0.05;
			if(c.re < -1) {
				c.re = -1;
				right = true;
			}
		}
	}

	public void convert(Complex z, Complex mu) {
		double temp = z.re/(z.re*z.re+z.im*z.im);
		z.im = -z.im/(z.re*z.re+z.im*z.im);
		z.re = temp;

		mu.re = z.re + c.re;
		mu.im = z.im + c.im;
	}

	public String toString() {
		return "Mandelbrot Set (1/\u03BC)";
	}

	public static void main(String[] args) {
		AbstractComplexFunction.main(args);
	}
}

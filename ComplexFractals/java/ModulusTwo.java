public class ModulusTwo implements ConvergenceFunction {
	public boolean escaped(Complex z) {
		return z.re*z.re + z.im*z.im > 2*2;
	}

	public String toString() {
		return "|z| \u2264 2";
	}
}
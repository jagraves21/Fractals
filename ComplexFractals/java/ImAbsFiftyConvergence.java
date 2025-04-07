public class ImAbsFiftyConvergence implements ConvergenceFunction {
	public boolean escaped(Complex z) {
		return z.im*z.im > 50*50;
	}

	public String toString() {
		return "|Im(z)| \u2264 50";
	}
}

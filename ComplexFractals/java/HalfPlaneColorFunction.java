public class HalfPlaneColorFunction implements ConvergenceFunction {
	public boolean escaped(Complex z) {
		return z.re > 2;
	}

	public String toString() {
		return "Re(z) \u2264 2";
	}
}
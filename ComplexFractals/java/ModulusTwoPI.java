public class ModulusTwoPI implements ConvergenceFunction {
	public boolean escaped(Complex z) {
		return z.re*z.re + z.im*z.im > Math.pow(2*Math.PI,2);
	}

	public String toString() {
		return "|z| \u2264 2";
	}
}
public class ModulusTwoPIConvergence implements ConvergenceFunction {
	public boolean escaped(Complex z) {
		return z.modulusSquared() > 2*Math.PI * 2*Math.PI;
	}

	public String toString() {
		return "|z| \u2264 2\u03C0";
	}
}

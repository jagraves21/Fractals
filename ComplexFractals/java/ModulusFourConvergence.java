public class ModulusFourConvergence implements ConvergenceFunction {
	public boolean escaped(Complex z) {
		return z.modulusSquared() > 4*4;
	}

	public String toString() {
		return "|z| \u2264 4";
	}
}

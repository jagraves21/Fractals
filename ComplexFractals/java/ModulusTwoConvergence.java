public class ModulusTwoConvergence implements ConvergenceFunction {
	public boolean escaped(Complex z) {
		return z.modulusSquared() > 2*2;
	}

	public String toString() {
		return "|z| \u2264 2";
	}
}

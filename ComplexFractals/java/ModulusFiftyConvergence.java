public class ModulusFiftyConvergence  implements ConvergenceFunction {
	public boolean escaped(Complex z) {
		return z.modulusSquared() > 50*50;
	}

	public String toString() {
		return "|z| \u2264 50";
	}
}

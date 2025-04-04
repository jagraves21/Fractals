public class ModulusSquare implements ConvergenceFunction {
	public boolean escaped(Complex z) {
		return Math.abs(z.re) > 2 || Math.abs(z.im) > 2;
	}

	public String toString() {
		return "|Re(z),Im(z)| \u2264 2 ";
	}
}
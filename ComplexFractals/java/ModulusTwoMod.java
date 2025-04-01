public class ModulusTwoMod implements ConvergenceFunction {
	public boolean escaped(Complex z) {
		return Math.abs(z.re) > 2;
	}
	
	public String toString() {
		return "|Re(z)| \u2264 2";
	}
}
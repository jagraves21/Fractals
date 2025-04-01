public class ModulusFour implements ConvergenceFunction {
	public boolean escaped(Complex z) {
		return z.re*z.re + z.im*z.im > 4*4;
	}
	
	public String toString() {
		return "|z| \u2264 4";
	}
}
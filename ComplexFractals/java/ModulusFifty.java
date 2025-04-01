public class ModulusFifty implements ConvergenceFunction {
	public boolean escaped(Complex z) {
		return z.re*z.re + z.im*z.im > 50*50;
	}
	
	public String toString() {
		return "|z| \u2264 50";
	}
}
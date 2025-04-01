public class ModulusFiftyMod implements ConvergenceFunction {
	public boolean escaped(Complex z) {
		return z.im*z.im > 50*50;
	}
	
	public String toString() {
		return "Im(z)\u00B2 \u2264 50";
	}
}
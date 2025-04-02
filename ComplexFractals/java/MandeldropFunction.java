public class MandeldropFunction extends MandelbrotFunction {
	public MandeldropFunction() {
		super();
		init();
	}

	public double getOriginX() {
        return 1.4;
    }
    
	public double getWindowWidth() {
        return 6;
    }

	public void convert(Complex z, Complex mu) {
		double temp = z.re/(z.re*z.re+z.im*z.im);
		z.im = -z.im/(z.re*z.re+z.im*z.im);
		z.re = temp;
		
		mu.re = z.re;
		mu.im = z.im;
	}

	public ColorFunction getColorFunction() {
		return PalletedColorFunction.getRainbow(25);
	}
	
	public String toString() {
		return "Mandeldrop Set";
	}

	public static void main(String[] args) {
		AbstractComplexFunction.main(args);
	}
}

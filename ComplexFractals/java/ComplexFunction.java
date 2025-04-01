public interface ComplexFunction {
	public double getOriginX();
	public double getOriginY();
	public double getWindowWidth();
	public void init();
	public void random();
	public void move();
	public void convert(Complex z, Complex mu);
	public void next(Complex z, Complex mu);
	public ConvergenceFunction getConvergenceFunction();
	public ColorFunction getColorFunction();
	public ComplexFractal.FractalType getFractalType();
	public ComplexFractal.FractalStyle getFractalStyle();
}

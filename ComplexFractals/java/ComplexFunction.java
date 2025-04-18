public interface ComplexFunction {
	public double getOriginX();
	public double getOriginY();
	public double getViewWidth();
	public void init();
	public void random();
	public void move();
	public void convert(Complex z, Complex mu);
	public void next(Complex z, Complex mu);
	public ConvergenceFunction getSuggestedConvergenceFunction();
	public ColorFunction getSuggestedColorFunction();
	public ComplexFractal.FractalType getSuggestedFractalType();
	public ComplexFractal.FractalStyle getSuggestedFractalStyle();
}

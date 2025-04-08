public class MandelbrotGUI extends ChasingInfinity {
	public MandelbrotGUI() {
		super();
	}

	public void addFractalFunctions() {
		fractalFunctions.addItem(new MandelbrotFunction());
		fractalFunctions.addItem(new InvertedMandelbrotFunction());
		fractalFunctions.addItem(new LambdaMandelbrotFunction());
		fractalFunctions.addItem(new InvertedLambdaMandelbrotFunction());
		fractalFunctions.addItem(new MandeldiskFunction());
		fractalFunctions.addItem(new MandeldropFunction());
		fractalFunctions.addItem(new CubicMandelbrotFunction());
		fractalFunctions.addItem(new QuarticMandelbrotFunction());
		fractalFunctions.addItem(new SinMandelbrotFunction());
		fractalFunctions.addItem(new CosMandelbrotFunction());
		fractalFunctions.addItem(new TanMandelbrotFunction());
	}

	public String getGUIName() {
		return "Mandelbrot Sets";
	}

	public static void main(String[] args) {
		ChasingInfinity.displayGUI(new MandelbrotGUI());
	}
}

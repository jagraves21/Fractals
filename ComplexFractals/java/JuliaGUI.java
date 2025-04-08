public class JuliaGUI extends ChasingInfinity {
	public JuliaGUI() {
		super();
	}

	public void addFractalFunctions() {
		fractalFunctions.addItem(new JuliaFunction());
		fractalFunctions.addItem(new InvertedJuliaFunction());
		fractalFunctions.addItem(new LambdaJuliaFunction());
		fractalFunctions.addItem(new InvertedLambdaJuliaFunction());
		fractalFunctions.addItem(new CubicJuliaFunction());
		fractalFunctions.addItem(new QuarticJuliaFunction());
		fractalFunctions.addItem(new SinJuliaFunction());
		fractalFunctions.addItem(new CosJuliaFunction());
		fractalFunctions.addItem(new TanJuliaFunction());
	}

	public String getGUIName() {
		return "Julia Sets";
	}

	public static void main(String[] args) {
		ChasingInfinity.displayGUI(new JuliaGUI());
	}
}

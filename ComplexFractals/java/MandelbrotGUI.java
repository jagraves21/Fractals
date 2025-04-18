public class MandelbrotGUI extends ChasingInfinity {
	public MandelbrotGUI() {
		super();
	}

	public void addFractalFunctions() {
        addMandelbrotFractalsFunctions();
	}

	public String getGUIName() {
		return "Mandelbrot Sets";
	}

	public static void main(String[] args) {
		ChasingInfinity.displayGUI(new MandelbrotGUI());
	}
}

public class JuliaGUI extends ChasingInfinity {
	public JuliaGUI() {
		super();
	}

	public void addFractalFunctions() {
        addJuliaFractalsFunctions();
	}

	public String getGUIName() {
		return "Julia Sets";
	}

	public static void main(String[] args) {
		ChasingInfinity.displayGUI(new JuliaGUI());
	}
}

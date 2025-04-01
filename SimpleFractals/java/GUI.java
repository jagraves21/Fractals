import java.awt.*;
import java.awt.event.*;

import java.util.*;

import javax.swing.*;

public class GUI extends JPanel implements ActionListener
{
	private static final long serialVersionUID = 1L;

	protected java.util.List<SimpleFractal> fractalList;
	
	protected JComboBox<SimpleFractal> fractalComboBox;
	protected FractalPanel fractalPanel;
	
	public GUI(java.util.List<SimpleFractal> fractalList)
	{
		this.fractalList = fractalList;
		
		setLayout(new BorderLayout());
		
		fractalComboBox = new JComboBox<SimpleFractal>(fractalList.toArray(new SimpleFractal[0]));
		fractalComboBox.addActionListener(this);
		add(fractalComboBox, BorderLayout.SOUTH);
		
		fractalPanel = new FractalPanel((SimpleFractal)fractalComboBox.getSelectedItem());
		add(fractalPanel, BorderLayout.CENTER);
	}

	public void start() {
		fractalPanel.start();
	}
	
	public void stop() {
		fractalPanel.stop();
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == fractalComboBox)
		{
			SimpleFractal fractal = (SimpleFractal)fractalComboBox.getSelectedItem();
			System.out.println(fractal);

			stop();
			fractalPanel.setFractal(fractal);
			fractalPanel.setIterations(fractal.getSuggestedIterations());
			fractalPanel.setDelay(fractal.getSuggestedDelay());
			fractalPanel.clearFractal();
			start();	
		}
	}
	
	public static void main(String[] args)
	{
		java.util.List<SimpleFractal> fractalList = new LinkedList<SimpleFractal>();
		
		fractalList.add(new ApollonianGasket());
		fractalList.add(new BoxFractal());
		fractalList.add(new Branches());
		fractalList.add(new CesaroFractal());
		fractalList.add(new Christmas());
		fractalList.add(new ChristmasApollonianGasket());
		fractalList.add(new ChristmasKochSnowflake());
		fractalList.add(new ChristmasSierpinskiCarpet());
		fractalList.add(new ChristmasSierpinskiTriangle());
		fractalList.add(new CircleInversion());
		fractalList.add(new CircleInversion2());
		fractalList.add(new CircleInversion2Mod());
		fractalList.add(new CircleInversionMod());
		fractalList.add(new CircleLimitSet());
		fractalList.add(new CircleLimitSet1());
		fractalList.add(new CircleLimitSet2());
		fractalList.add(new CircleLimitSet3());
		fractalList.add(new CircleLimitSet4());
		fractalList.add(new CircleLimitSetNew());
		fractalList.add(new CirclePacking());
		fractalList.add(new Circles());
		fractalList.add(new Cross());
		fractalList.add(new Cross2());
		fractalList.add(new Crosses());
		fractalList.add(new Curlicue());
		fractalList.add(new DimondFlake());
		fractalList.add(new Dragon());
		fractalList.add(new DragonColor());
		fractalList.add(new DragonLSystem());
		fractalList.add(new DragonMod());
		fractalList.add(new Edge());
		fractalList.add(new EdgeMod());
		fractalList.add(new Fern());
		fractalList.add(new HFractal());
		fractalList.add(new HenonAttractor());
		fractalList.add(new HexagonalTile());
		fractalList.add(new HilbertCurve());
		fractalList.add(new InwardCircle());
		fractalList.add(new IslandsLakes());
		fractalList.add(new KochAntisnowflake());
		fractalList.add(new KochCubes());
		fractalList.add(new KochCurve());
		fractalList.add(new KochLine());
		fractalList.add(new KochLineMod());
		fractalList.add(new KochMoss());
		fractalList.add(new KochSnowflake());
		fractalList.add(new LRepTile());
		fractalList.add(new LRepTileMod());
		fractalList.add(new Lace());
		fractalList.add(new LevyCurve());
		fractalList.add(new LevyCurves());
		fractalList.add(new LevyTapestry());
		fractalList.add(new Lines());
		fractalList.add(new MangoKolam());
		fractalList.add(new MinkowskiLine());
		fractalList.add(new MinkowskiSausage());
		fractalList.add(new MooreCurve());
		fractalList.add(new NestedPentagram());
		fractalList.add(new OutwardCircle());
		fractalList.add(new Peano());
		fractalList.add(new PeanoGosperCurve());
		fractalList.add(new PenroseTiling());
		fractalList.add(new PentaFlake());
		fractalList.add(new PentaFlakeMod());
		fractalList.add(new PentaFractal());
		fractalList.add(new PentaStar());
		fractalList.add(new PentaStarMod());
		fractalList.add(new Pentadentrite());
		fractalList.add(new Pentagram());
		fractalList.add(new Pentigree());
		fractalList.add(new Plusses());
		fractalList.add(new PythagorasShrub());
		fractalList.add(new PythagorasTree());
		fractalList.add(new QuadraticFlake());
		fractalList.add(new RhombusFlake());
		fractalList.add(new SierpinskiArrowhead());
		fractalList.add(new SierpinskiCarpet());
		fractalList.add(new SierpinskiMedian());
		fractalList.add(new SierpinskiSquare());
		fractalList.add(new SierpinskiTriangle());
		fractalList.add(new SpiralSquare());
		fractalList.add(new Square());
		fractalList.add(new SquareFlake());
		fractalList.add(new SquareMesh());
		fractalList.add(new SquareStar());
		fractalList.add(new SquareStar2());
		fractalList.add(new SquareTiling());
		fractalList.add(new SquareTriangle());
		fractalList.add(new SquareTriangleMod());
		fractalList.add(new SquareTurn());
		fractalList.add(new Squares());
		fractalList.add(new Squares2());
		fractalList.add(new Squares2Mod());
		fractalList.add(new SquaresMod());
		fractalList.add(new TSquare());
		fractalList.add(new TernaryTree());
		fractalList.add(new TetraDragon());
		fractalList.add(new Thorn());
		fractalList.add(new ThreeBranch());
		fractalList.add(new TornSquare());
		fractalList.add(new Tree());
		fractalList.add(new TreeBlue());
		fractalList.add(new TreeMod());
		fractalList.add(new TreePink());
		fractalList.add(new TriCircle());
		fractalList.add(new TriangleFlake());

		System.out.println(fractalList.size() + " fractals.");

		JFrame frame = new JFrame("Fractals");
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setPreferredSize(new Dimension(800, 600));

		GUI gui = new GUI(fractalList);
		frame.setContentPane(gui);
		frame.pack();
		frame.setLocationRelativeTo(null);

		frame.addWindowListener(new WindowAdapter() {
			public void windowOpened(WindowEvent e) {
				new SwingWorker<Void, Void>() {
					protected Void doInBackground() throws Exception {
						gui.start();
						return null;
					}
				}.execute();
			}
			
			public void windowClosing(WindowEvent e) {
				new SwingWorker<Void, Void>() {
					protected Void doInBackground() throws Exception {
						gui.stop();
						return null;
					}
				}.execute();
			}
		});

		String os = System.getProperty("os.name").toLowerCase();
		String keyStroke = os.contains("mac") ? "meta W" : "control W";

		JRootPane rootPane = frame.getRootPane();
		InputMap inputMap = rootPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
		ActionMap actionMap = rootPane.getActionMap();
		inputMap.put(KeyStroke.getKeyStroke(keyStroke), "close");
		actionMap.put("close", new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
			}
		});

		frame.setVisible(true);
	}
}


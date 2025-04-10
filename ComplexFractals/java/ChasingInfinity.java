import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

public class ChasingInfinity extends JPanel {
	protected JComboBox<ComplexFunction> fractalFunctions;
	protected JComboBox<ConvergenceFunction> convergenceFunctions;
	protected JComboBox<ColorFunction> colorFunctions;
	protected JComboBox<ComplexFractal.FractalType> fractalTypes;
	protected JComboBox<ComplexFractal.FractalStyle> fractalStyles;

	public ComplexFractal complexFractal;

	public ChasingInfinity() {
		super();
		initComponents();
		layoutContent();
	}

	public void initComponents() {
		ItemListener itemListener= new ItemListener(){
			public void itemStateChanged(ItemEvent e) {
				System.out.println(e.getItem() + " " + e.getStateChange());
				if (e.getStateChange() == ItemEvent.DESELECTED) {
					return;
				}
				else if (e.getItem() instanceof ComplexFunction) {
					ComplexFunction complexFunction = (ComplexFunction)e.getItem();
					try {
						complexFractal.stop();
						complexFractal.setMaxIteration(0);
						complexFractal.setWindow(complexFunction.getOriginX(), complexFunction.getOriginY(), complexFunction.getWindowWidth());
						complexFractal.setComplexFunction(complexFunction);
						complexFractal.start();
					} catch(Exception ex) {
						System.exit(-1);
					}
				}
				else if(e.getItem() instanceof ColorFunction) {
					ColorFunction colorFunction = (ColorFunction)e.getItem();
					complexFractal.setColorFunction(colorFunction);
				}
				else if(e.getItem() instanceof ConvergenceFunction) {
					ConvergenceFunction convergenceFunction = (ConvergenceFunction)e.getItem();
					complexFractal.setConvergenceFunction(convergenceFunction);
				}
				else if(e.getItem() instanceof ComplexFractal.FractalType) {
					ComplexFractal.FractalType fractalType = (ComplexFractal.FractalType)e.getItem();
					complexFractal.setFractalType(fractalType);
				}
				else if(e.getItem() instanceof ComplexFractal.FractalStyle) {
					ComplexFractal.FractalStyle fractalStyle = (ComplexFractal.FractalStyle)e.getItem();
					complexFractal.setFractalStyle(fractalStyle);
				}
			}
		};

		fractalFunctions = new JComboBox<ComplexFunction>();
		addFractalFunctions();
		fractalFunctions.addItemListener(itemListener);

		convergenceFunctions = new JComboBox<ConvergenceFunction>();
		addConvergentFunctions();
		convergenceFunctions.addItemListener(itemListener);

		colorFunctions = new JComboBox<ColorFunction>();
		addColorFunctions();
		colorFunctions.addItemListener(itemListener);

		fractalTypes = new JComboBox<ComplexFractal.FractalType>();
		addFractalTypes();
		fractalTypes.addItemListener(itemListener);

		fractalStyles = new JComboBox<ComplexFractal.FractalStyle>();
		addFractalStyles();
		fractalStyles.addItemListener(itemListener);
		
		complexFractal = new ComplexFractal(
			fractalFunctions.getItemAt(0),
			convergenceFunctions.getItemAt(0),
			colorFunctions.getItemAt(0),
			fractalTypes.getItemAt(0),
			fractalStyles.getItemAt(0)
		);
	}

	public void addFractalFunctions() {
		fractalFunctions.addItem(new BurningShip());
		fractalFunctions.addItem(new CollatzFunction());
		fractalFunctions.addItem(new ModifiedCollatzFunction());
		fractalFunctions.addItem(new GlynnFunction());
		fractalFunctions.addItem(new Infinity());
		fractalFunctions.addItem(new ManowarFunction());
		fractalFunctions.addItem(new SierpinskiFunction());
		fractalFunctions.addItem(new Spider());
		fractalFunctions.addItem(new TricornFunction());
		fractalFunctions.addItem(new VortexFunction());

		fractalFunctions.addItem(new JuliaFunction());
		fractalFunctions.addItem(new InvertedJuliaFunction());
		fractalFunctions.addItem(new LambdaJuliaFunction());
		fractalFunctions.addItem(new InvertedLambdaJuliaFunction());
		fractalFunctions.addItem(new CubicJuliaFunction());
		fractalFunctions.addItem(new QuarticJuliaFunction());
		fractalFunctions.addItem(new SinJuliaFunction());
		fractalFunctions.addItem(new CosJuliaFunction());
		fractalFunctions.addItem(new TanJuliaFunction());

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

	public void addConvergentFunctions() {
		convergenceFunctions.addItem(new ModulusTwoConvergence());
		convergenceFunctions.addItem(new ModulusFourConvergence());
		convergenceFunctions.addItem(new ModulusTwoPIConvergence());
		convergenceFunctions.addItem(new ModulusFiftyConvergence());
		convergenceFunctions.addItem(new RealTwoConvergence());
		convergenceFunctions.addItem(new RealAbsTwoConvergence());
		convergenceFunctions.addItem(new ImAbsFiftyConvergence());
		convergenceFunctions.addItem(new ModulusSquareConvergence());
	}

	public void addColorFunctions() {
		addColorFunction(PalletedColorFunction.getTEST(5));
		addColorFunction(SmoothColorFunction.getTEST(10));

		addColorFunction(PalletedColorFunction.getRGB(5));
		addColorFunction(SmoothColorFunction.getRGB(10));
		addColorFunction(PalletedColorFunction.getRainbow(5));
		addColorFunction(SmoothColorFunction.getRainbow(10));
		addColorFunction(PalletedColorFunction.getSpectrum(5));
		addColorFunction(SmoothColorFunction.getSpectrum(10));
		addColorFunction(PalletedColorFunction.getForest(5));
		addColorFunction(SmoothColorFunction.getForest(10));
		addColorFunction(PalletedColorFunction.getSeaweed(5));
		addColorFunction(SmoothColorFunction.getSeaweed(10));
		addColorFunction(PalletedColorFunction.getOcean(5));
		addColorFunction(SmoothColorFunction.getOcean(10));
		addColorFunction(PalletedColorFunction.getAqua(5));
		addColorFunction(SmoothColorFunction.getAqua(10));
		addColorFunction(PalletedColorFunction.getSunset(5));
		addColorFunction(SmoothColorFunction.getSunset(10));
		addColorFunction(PalletedColorFunction.getTropical(5));
		addColorFunction(SmoothColorFunction.getTropical(10));
		addColorFunction(PalletedColorFunction.getNeon(5));
		addColorFunction(SmoothColorFunction.getNeon(10));
		addColorFunction(PalletedColorFunction.getSpace(5));
		addColorFunction(SmoothColorFunction.getSpace(10));
		addColorFunction(PalletedColorFunction.getTwilight(5));
		addColorFunction(SmoothColorFunction.getTwilight(10));
		addColorFunction(PalletedColorFunction.getIceFire(5));
		addColorFunction(SmoothColorFunction.getIceFire(10));
		addColorFunction(PalletedColorFunction.getZebra(5));
		addColorFunction(SmoothColorFunction.getZebra(10));
		addColorFunction(PalletedColorFunction.getCandyCane(5));
		addColorFunction(SmoothColorFunction.getCandyCane(10));
		addColorFunction(PalletedColorFunction.getMagma(5));
		addColorFunction(SmoothColorFunction.getMagma(10));
		addColorFunction(PalletedColorFunction.getLava(5));
		addColorFunction(SmoothColorFunction.getLava(10));
		addColorFunction(PalletedColorFunction.getPinkBlue(5));
		addColorFunction(SmoothColorFunction.getPinkBlue(10));
		addColorFunction(PalletedColorFunction.getOilSlick(5));
		addColorFunction(SmoothColorFunction.getOilSlick(10));
		addColorFunction(PalletedColorFunction.getGasOnWater(5));
		addColorFunction(SmoothColorFunction.getGasOnWater(10));
		
		addColorFunction(new CosineColorFunction());
		addColorFunction(new CyclicColorFunction());
	}

	public void addFractalTypes() {
		addFractalType(ComplexFractal.FractalType.ITERATIVE);
		addFractalType(ComplexFractal.FractalType.MOVING);
		addFractalType(ComplexFractal.FractalType.RANDOM);
	}

	public void addFractalStyles() {
		fractalStyles.addItem(ComplexFractal.FractalStyle.STANDARD);
		fractalStyles.addItem(ComplexFractal.FractalStyle.CONTOURED);
		fractalStyles.addItem(ComplexFractal.FractalStyle.FREQUENCY);
	}

	public void start() {
		complexFractal.start();
	}

	public void stop() {
		complexFractal.stop();
	}

	protected void layoutContent() {
		JPanel south = new JPanel();
		south.add(fractalFunctions);
		south.add(convergenceFunctions);
		south.add(colorFunctions);
		south.add(fractalTypes);
		south.add(fractalStyles);

		setLayout(new BorderLayout());
		add(south, BorderLayout.SOUTH);
		add(complexFractal, BorderLayout.CENTER);

		/*PalletCreator palletCreator = new PalletCreator();
		palletCreator.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ColorFunction colorFunction = ((PalletCreator)e.getSource()).getColorFunction();
				complexFractal.setColorFunction(colorFunction);
			}
		});*/
		//add(palletCreator, BorderLayout.WEST);

		/*SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				fractal.start();
			}
		});*/
	}

	public void addColorFunction(ColorFunction colorFunction) {
		colorFunctions.addItem(colorFunction);
	}

	public void addFractalType(ComplexFractal.FractalType fractalType) {
		fractalTypes.addItem(fractalType);
	}

	public void addFractalStyle(ComplexFractal.FractalStyle fractalStyle) {
		fractalStyles.addItem(fractalStyle);
	}

	public String getGUIName() {
		return "Chasing Infinity";
	}

	public static void displayGUI(ChasingInfinity content) {
		final JFrame frame = new JFrame(content.getGUIName());
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.add(content);
		content.setPreferredSize(new Dimension(800, 600));
		frame.pack();
		frame.setLocationRelativeTo(null);

		frame.addWindowListener(new WindowAdapter() {
			public void windowOpened(WindowEvent e) {
				new SwingWorker<Void, Void>() {
					protected Void doInBackground() throws Exception {
						content.start();
						return null;
					}
				}.execute();
			}

			public void windowClosing(WindowEvent e) {
				new SwingWorker<Void, Void>() {
					protected Void doInBackground() throws Exception {
						content.stop();
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
	
	public static void main(String[] args) {
		ChasingInfinity.displayGUI(new ChasingInfinity());
	}
}

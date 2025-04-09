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
		addColorFunction(new PalletedColorFunction(PalletedColorFunction.getColorSpan(PalletedColorFunction.RGB, 10)) {
			public String toString() {
				return "RGB";
			}
		});
		addColorFunction(new SmoothColorFunction(SmoothColorFunction.getColorSpan(SmoothColorFunction.RGB, 10)) {
			public String toString() {
				return "RGB (S)";
			}
		});
		addColorFunction(new PalletedColorFunction(PalletedColorFunction.getColorSpan(PalletedColorFunction.RAINBOW, 10)) {
			public String toString() {
				return "Rainbow";
			}
		});
		addColorFunction(new SmoothColorFunction(SmoothColorFunction.getColorSpan(SmoothColorFunction.RAINBOW, 10)) {
			public String toString() {
				return "Rainbow (S)";
			}
		});
		addColorFunction(new PalletedColorFunction(PalletedColorFunction.getColorSpan(PalletedColorFunction.SPECTRUM, 10)) {
			public String toString() {
				return "Spectrum";
			}
		});
		addColorFunction(new SmoothColorFunction(SmoothColorFunction.getColorSpan(SmoothColorFunction.SPECTRUM, 10)) {
			public String toString() {
				return "Spectrum (S)";
			}
		});
		addColorFunction(new PalletedColorFunction(PalletedColorFunction.getColorSpan(PalletedColorFunction.FOREST, 10)) {
			public String toString() {
				return "Forest";
			}
		});
		addColorFunction(new SmoothColorFunction(SmoothColorFunction.getColorSpan(SmoothColorFunction.FOREST, 10)) {
			public String toString() {
				return "Forest (S)";
			}
		});
		addColorFunction(new PalletedColorFunction(PalletedColorFunction.getColorSpan(PalletedColorFunction.SEAWEED, 10)) {
			public String toString() {
				return "Seaweed";
			}
		});
		addColorFunction(new SmoothColorFunction(SmoothColorFunction.getColorSpan(SmoothColorFunction.SEAWEED, 10)) {
			public String toString() {
				return "Seaweed (S)";
			}
		});
		addColorFunction(new PalletedColorFunction(PalletedColorFunction.getColorSpan(PalletedColorFunction.OCEAN, 10)) {
			public String toString() {
				return "Ocean";
			}
		});
		addColorFunction(new SmoothColorFunction(SmoothColorFunction.getColorSpan(SmoothColorFunction.OCEAN, 10)) {
			public String toString() {
				return "Ocean (S)";
			}
		});
		addColorFunction(new PalletedColorFunction(PalletedColorFunction.getColorSpan(PalletedColorFunction.AQUA, 10)) {
			public String toString() {
				return "Aqua";
			}
		});
		addColorFunction(new SmoothColorFunction(SmoothColorFunction.getColorSpan(SmoothColorFunction.AQUA, 10)) {
			public String toString() {
				return "Aqua (S)";
			}
		});
		addColorFunction(new PalletedColorFunction(PalletedColorFunction.getColorSpan(PalletedColorFunction.SUNSET, 10)) {
			public String toString() {
				return "Sunset";
			}
		});
		addColorFunction(new SmoothColorFunction(SmoothColorFunction.getColorSpan(SmoothColorFunction.SUNSET, 10)) {
			public String toString() {
				return "Sunset (S)";
			}
		});
		addColorFunction(new PalletedColorFunction(PalletedColorFunction.getColorSpan(PalletedColorFunction.TROPICAL, 10)) {
			public String toString() {
				return "Tropical";
			}
		});
		addColorFunction(new SmoothColorFunction(SmoothColorFunction.getColorSpan(SmoothColorFunction.TROPICAL, 10)) {
			public String toString() {
				return "Tropical (S)";
			}
		});
		addColorFunction(new PalletedColorFunction(PalletedColorFunction.getColorSpan(PalletedColorFunction.NEON, 10)) {
			public String toString() {
				return "Neon";
			}
		});
		addColorFunction(new SmoothColorFunction(SmoothColorFunction.getColorSpan(SmoothColorFunction.NEON, 10)) {
			public String toString() {
				return "Neon (S)";
			}
		});
		addColorFunction(new PalletedColorFunction(PalletedColorFunction.getColorSpan(PalletedColorFunction.SPACE, 10)) {
			public String toString() {
				return "Space";
			}
		});
		addColorFunction(new SmoothColorFunction(SmoothColorFunction.getColorSpan(SmoothColorFunction.SPACE, 10)) {
			public String toString() {
				return "Space (S)";
			}
		});
		addColorFunction(new PalletedColorFunction(PalletedColorFunction.getColorSpan(PalletedColorFunction.ICE_FIRE, 10)) {
			public String toString() {
				return "IceFire";
			}
		});
		addColorFunction(new SmoothColorFunction(SmoothColorFunction.getColorSpan(SmoothColorFunction.ICE_FIRE, 10)) {
			public String toString() {
				return "IceFire (S)";
			}
		});
		addColorFunction(new PalletedColorFunction(PalletedColorFunction.getColorSpan(PalletedColorFunction.ZEBRA, 10)) {
			public String toString() {
				return "Zebra";
			}
		});
		addColorFunction(new SmoothColorFunction(SmoothColorFunction.getColorSpan(SmoothColorFunction.ZEBRA, 10)) {
			public String toString() {
				return "Zebra (S)";
			}
		});
		addColorFunction(new PalletedColorFunction(PalletedColorFunction.getColorSpan(PalletedColorFunction.CANDY_CANE, 10)) {
			public String toString() {
				return "CandyCane";
			}
		});
		addColorFunction(new SmoothColorFunction(SmoothColorFunction.getColorSpan(SmoothColorFunction.CANDY_CANE, 10)) {
			public String toString() {
				return "CandyCane (S)";
			}
		});
		addColorFunction(new PalletedColorFunction(PalletedColorFunction.getColorSpan(PalletedColorFunction.MAGMA, 10)) {
			public String toString() {
				return "Magma";
			}
		});
		addColorFunction(new SmoothColorFunction(SmoothColorFunction.getColorSpan(SmoothColorFunction.MAGMA, 10)) {
			public String toString() {
				return "Magma (S)";
			}
		});
		addColorFunction(new PalletedColorFunction(PalletedColorFunction.getColorSpan(PalletedColorFunction.LAVA, 10)) {
			public String toString() {
				return "Lava";
			}
		});
		addColorFunction(new SmoothColorFunction(SmoothColorFunction.getColorSpan(SmoothColorFunction.LAVA, 10)) {
			public String toString() {
				return "Lava (S)";
			}
		});
		addColorFunction(new PalletedColorFunction(PalletedColorFunction.getColorSpan(PalletedColorFunction.PINK_BLUES, 10)) {
			public String toString() {
				return "PinkBlues";
			}
		});
		addColorFunction(new SmoothColorFunction(SmoothColorFunction.getColorSpan(SmoothColorFunction.PINK_BLUES, 10)) {
			public String toString() {
				return "PinkBlues (S)";
			}
		});
		
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

		PalletCreator palletCreator = new PalletCreator();
		palletCreator.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ColorFunction colorFunction = ((PalletCreator)e.getSource()).getColorFunction();
				complexFractal.setColorFunction(colorFunction);
			}
		});
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

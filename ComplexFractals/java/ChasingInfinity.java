import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.Dimension;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JApplet;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class ChasingInfinity extends JApplet {
	protected JComboBox fractalFunctions;
	protected JComboBox colorFunctions;
	protected JComboBox convergenceFunctions;
	protected JComboBox fractalTypes;
	protected JComboBox fractalStyles;
	
	public ComplexFractal complexFractal;
	
	public void addFractalFunction() {
		addComplexFunction(new ManowarFunction());
		addComplexFunction(new MandelbrotFunction());
		addComplexFunction(new JuliaFunction());
		addComplexFunction(new BurningShip());
		addComplexFunction(new Spider());
		addComplexFunction(new CollatzFunction());
		addComplexFunction(new ModifiedCollatzFunction());
		addComplexFunction(new Infinity());
		addComplexFunction(new TricornFunction());
		addComplexFunction(new SierpinskiFunction());
		addComplexFunction(new GlynnFunction());
		//addComplexFunction(new TreeFunction());
		addComplexFunction(new VortexFunction());
		addComplexFunction(new CosMandelbrotFunction());
	}
	
	public void addConvergentFunctions() {
		addConvergenceFunction(new ModulusTwo());
		addConvergenceFunction(new ModulusTwoMod());
		addConvergenceFunction(new ModulusFour());
		addConvergenceFunction(new ModulusFifty());
		addConvergenceFunction(new ModulusFiftyMod());
		addConvergenceFunction(new ModulusSquare());
		addConvergenceFunction(new HalfPlaneColorFunction());
	}
	
	public void addColorFunctions() {
		addColorFunction(new PalletedColorFunction(PalletedColorFunction.getColorSpan(PalletedColorFunction.RAINBOW, 10)) {
			public String toString() {
				return "Rainbow";
			}
		});
		addColorFunction(new SmoothColorFunction(PalletedColorFunction.getColorSpan(PalletedColorFunction.RAINBOW, 10)) {
			public String toString() {
				return "Rainbow (S)";
			}
		});
		addColorFunction(new PalletedColorFunction(PalletedColorFunction.getColorSpan(PalletedColorFunction.BLUES, 10)) {
			public String toString() {
				return "Blues";
			}
		});
		addColorFunction(new SmoothColorFunction(PalletedColorFunction.getColorSpan(PalletedColorFunction.BLUES, 10)) {
			public String toString() {
				return "Blues (S)";
			}
		});
		addColorFunction(new PalletedColorFunction(PalletedColorFunction.getColorSpan(PalletedColorFunction.RED_BLUE, 10)) {
			public String toString() {
				return "Red/Blue";
			}
		});
		addColorFunction(new SmoothColorFunction(PalletedColorFunction.getColorSpan(PalletedColorFunction.RED_BLUE, 10)) {
			public String toString() {
				return "Red/Blue (S)";
			}
		});
		addColorFunction(new PalletedColorFunction(PalletedColorFunction.getColorSpan(PalletedColorFunction.BLACK_RED_YELLOW, 10)) {
			public String toString() {
				return "Black/Red/Yellow";
			}
		});
		addColorFunction(new SmoothColorFunction(PalletedColorFunction.getColorSpan(PalletedColorFunction.BLACK_RED_YELLOW, 10)) {
			public String toString() {
				return "Black/Red/Yellow (S)";
			}
		});
		addColorFunction(new PalletedColorFunction(PalletedColorFunction.BLACK_WHITE) {
			public String toString() {
				return "Black/White";
			}
		});
		addColorFunction(new SmoothColorFunction(PalletedColorFunction.BLACK_WHITE) {
			public String toString() {
				return "Black/White (S)";
			}
		});
		addColorFunction(new CosColorFunction());
		addColorFunction(new CyclicColorFunction());
	}
	
	public void addFractalTypes() {
		addFractalType(ComplexFractal.FractalType.ITERATIVE);
		addFractalType(ComplexFractal.FractalType.MOVING);
		addFractalType(ComplexFractal.FractalType.RANDOM);
	}
	
	public void addFractalStyles() {
		addFractalStyle(ComplexFractal.FractalStyle.STANDARD);
		addFractalStyle(ComplexFractal.FractalStyle.CONTOURED);
		addFractalStyle(ComplexFractal.FractalStyle.FREQUENCY);
	}
	
	public void init() {
		complexFractal = new ComplexFractal(0,0,3,null,null,null,null,null);
		
		fractalFunctions = new JComboBox();
		colorFunctions = new JComboBox();
		convergenceFunctions = new JComboBox();
		fractalTypes = new JComboBox(); 
		fractalStyles = new JComboBox();
		
		if(SwingUtilities.isEventDispatchThread()) {
			layoutContent();
		}
		else {
			try {
				SwingUtilities.invokeAndWait(new Runnable() {
					public void run() {
						layoutContent();
					}
				});
			} catch (Exception e) {
				System.err.println("Error initalizing applet.");
				e.printStackTrace();
			}
		}
	}
	
	public void start() {
		addFractalFunction();
		addConvergentFunctions();
		addColorFunctions();
		addFractalTypes();
		addFractalStyles();
		
		fractalFunctions.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent e) {
				System.out.println(e.getItem() + " " + e.getStateChange());
				
				if(e.getStateChange() == e.SELECTED && e.getItem() instanceof ComplexFunction) {
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
			}
		});
		
		colorFunctions.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent e) {
				System.out.println(e.getItem() + " " + e.getStateChange());
				
				if(e.getStateChange() == e.SELECTED && e.getItem() instanceof ColorFunction) {
					ColorFunction colorFunction = (ColorFunction)e.getItem();
					complexFractal.setColorFunction(colorFunction);
				}
			}
		});
		
		convergenceFunctions.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent e) {
				System.out.println(e.getItem() + " " + e.getStateChange());
				
				if(e.getStateChange() == e.SELECTED && e.getItem() instanceof ConvergenceFunction) {
					ConvergenceFunction convergenceFunction = (ConvergenceFunction)e.getItem();
					complexFractal.setConvergenceFunction(convergenceFunction);
				}
			}
		});
		
		fractalTypes.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent e) {
				System.out.println(e.getItem() + " " + e.getStateChange());
				
				if(e.getStateChange() == e.SELECTED && e.getItem() instanceof ComplexFractal.FractalType) {
					ComplexFractal.FractalType fractalType = (ComplexFractal.FractalType)e.getItem();
					complexFractal.setFractalType(fractalType);
				}
			}
		});
		
		fractalStyles.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent e) {
				System.out.println(e.getItem() + " " + e.getStateChange());
				
				if(e.getStateChange() == e.SELECTED && e.getItem() instanceof ComplexFractal.FractalStyle) {
					ComplexFractal.FractalStyle fractalStyle = (ComplexFractal.FractalStyle)e.getItem();
					complexFractal.setFractalStyle(fractalStyle);
				}
			}
		});
		
		complexFractal.start();
	}
	
	public void stop() {
		
		ItemListener[] listenres = fractalFunctions.getItemListeners();
		for(int ii=0; ii < listenres.length; ii++)
		{
			fractalFunctions.removeItemListener(listenres[ii]);
		}
		
		listenres = colorFunctions.getItemListeners();
		for(int ii=0; ii < listenres.length; ii++)
		{
			colorFunctions.removeItemListener(listenres[ii]);
		}
		
		listenres = convergenceFunctions.getItemListeners();
		for(int ii=0; ii < listenres.length; ii++)
		{
			convergenceFunctions.removeItemListener(listenres[ii]);
		}
		
		complexFractal.stop();
	}
	
	protected void layoutContent() {
		JPanel south = new JPanel();
		south.add(fractalFunctions);
		south.add(colorFunctions);
		south.add(convergenceFunctions);
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
	
	public void addComplexFunction(ComplexFunction complexFunction) {
		fractalFunctions.addItem(complexFunction);
		
		if(complexFractal.getComplexFunction() == null) {
			complexFractal.setComplexFunction(complexFunction);
			complexFractal.setWindow(complexFunction.getOriginX(),complexFunction.getOriginY(),complexFunction.getWindowWidth());
		}
	}
	
	public void addConvergenceFunction(ConvergenceFunction convergenceFunction) {
		convergenceFunctions.addItem(convergenceFunction);
		
		if(complexFractal.getConvergenceFunction() == null) {
			complexFractal.setConvergenceFunction(convergenceFunction);
		}
	}
	
	public void addColorFunction(ColorFunction colorFunction) {
		colorFunctions.addItem(colorFunction);
		
		if(complexFractal.getColorFunction() == null) {
			complexFractal.setColorFunction(colorFunction);
		}
	}
	
	public void addFractalType(ComplexFractal.FractalType fractalType) {
		fractalTypes.addItem(fractalType);
		
		if(complexFractal.getFractalType() == null) {
			complexFractal.setFractalType(fractalType);
		}
	}
	
	public void addFractalStyle(ComplexFractal.FractalStyle fractalStyle) {
		fractalStyles.addItem(fractalStyle);
		
		if(complexFractal.getFractalStyle() == null) {
			complexFractal.setFractalStyle(fractalStyle);
		}
	}
	
	public static void main(String[] args){
		final JFrame frame = new JFrame("Chasing Infinity");
		final ChasingInfinity app = new ChasingInfinity();
		app.init();
		frame.add(app);
		frame.setSize(new Dimension(700, 600));
		frame.setLocationRelativeTo(null);
		
		frame.addComponentListener(new ComponentAdapter() {
			public void componentShown(ComponentEvent evt) {
				app.start();
			}
			
			public void componentHidden(ComponentEvent evt) {
				app.stop();
			}
		});
		
		frame.setVisible(true);
	}
}

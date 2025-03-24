import java.awt.*;
import java.awt.event.*;

import java.util.*;

import javax.swing.*;

public class Fractal extends JPanel implements ActionListener
{
	private static final long serialVersionUID = 1L;

	protected java.util.List<SimpleFractal> fractalList;
	
	protected JPanel controlPanel;
	protected JComboBox<SimpleFractal> fractalComboBox;
	protected JCheckBox stepCheckBox;
	protected JComboBox<Integer> iterationsComboBox;
	protected JButton redrawButton;
	protected FractalPanel fractalPanel;
	
	public Fractal(java.util.List<SimpleFractal> fractalList)
	{
		this.fractalList = fractalList;
		
		setLayout(new BorderLayout());
		
		createControlPanel();
		
		fractalPanel = new FractalPanel((SimpleFractal)fractalComboBox.getSelectedItem());
		add(fractalPanel, BorderLayout.CENTER);
	}
	
	protected void createControlPanel()
	{
		GridBagLayout layout = new GridBagLayout();
		
		controlPanel = new JPanel(layout);
		
		fractalComboBox = new JComboBox<SimpleFractal>(fractalList.toArray(new SimpleFractal[0]));
		fractalComboBox.addActionListener(this);
		
		stepCheckBox = new JCheckBox("Step");
		stepCheckBox.setSelected(true);
		
		iterationsComboBox = new JComboBox<Integer>();
		int max = ((SimpleFractal)fractalComboBox.getSelectedItem()).getSuggestedIterations();
		for(int ii=0; ii <= max; ii++)
		{
			iterationsComboBox.addItem(ii);
		}
		iterationsComboBox.setSelectedIndex(iterationsComboBox.getItemCount()-1);
		
		redrawButton = new JButton("Redraw");
		redrawButton.addActionListener(this);
		
		Component glue = Box.createGlue();
		
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = GridBagConstraints.RELATIVE;
		c.gridy = GridBagConstraints.RELATIVE;
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.gridheight = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		//c.ipadx
		//c.ipady
		c.insets = new Insets(5,5,5,5);
		c.anchor = GridBagConstraints.NORTHEAST;
		c.weightx = 1;
		c.weighty = 1;
		
		layout.setConstraints(fractalComboBox, c);
		controlPanel.add(fractalComboBox);
		
		c.fill = GridBagConstraints.NONE;
		layout.setConstraints(stepCheckBox, c);
		controlPanel.add(stepCheckBox);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		layout.setConstraints(iterationsComboBox, c);
		controlPanel.add(iterationsComboBox);
		
		layout.setConstraints(redrawButton, c);
		controlPanel.add(redrawButton);
		
		c.gridheight = GridBagConstraints.REMAINDER;
		layout.setConstraints(glue, c);
		controlPanel.add(glue);
		
		JPanel newPanel = new JPanel();
		newPanel.add(controlPanel);
		
		add(newPanel, BorderLayout.WEST);
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == fractalComboBox)
		{
			iterationsComboBox.removeAllItems();
			
			int max = ((SimpleFractal)fractalComboBox.getSelectedItem()).getSuggestedIterations();
			for(int ii=0; ii <= max; ii++)
			{
				iterationsComboBox.addItem(ii);
			}
			iterationsComboBox.setSelectedIndex(max);
		}
		else if(e.getSource() == redrawButton)
		{
			SimpleFractal fractal = (SimpleFractal)fractalComboBox.getSelectedItem();
			
			if(fractalPanel.getFractal() != fractal)
			{
				fractalPanel.setFractal(fractal);
			}
			
			fractal.clearFractal();
			fractalPanel.clearFractal();
			
			Integer iterations = (Integer)iterationsComboBox.getSelectedItem();
			FractalWorker worker;
			
			if(stepCheckBox.isSelected())
			{
				worker = new FractalWorker(redrawButton, fractalPanel, iterations.intValue());
			}
			else
			{
				for(int ii=0; ii < iterations.intValue(); ii++)
				{
					fractalPanel.next();
				}
				
				worker = new FractalWorker(redrawButton, fractalPanel, 0);
			}
			
			redrawButton.setEnabled(false);
			worker.execute();
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

		//System.out.println(fractalList.size() + " fractals.");

		JFrame frame = new JFrame("Fractals");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800,600);

		frame.setContentPane(new Fractal(fractalList));

		frame.setVisible(true);
	}
}


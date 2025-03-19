import java.awt.*;
import java.awt.event.*;

import java.util.*;

import javax.swing.*;

public class Fractal extends JPanel implements ActionListener
{
	protected java.util.List<SimpleFractal> fractalList;
	
	protected JPanel controlPanel;
	protected JComboBox fractalComboBox;
	protected JCheckBox stepCheckBox;
	protected JComboBox iterationsComboBox;
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
		
		fractalComboBox = new JComboBox(fractalList.toArray());
		fractalComboBox.addActionListener(this);
		
		stepCheckBox = new JCheckBox("Step");
		stepCheckBox.setSelected(true);
		
		iterationsComboBox = new JComboBox();
		int max = ((SimpleFractal)fractalComboBox.getSelectedItem()).getSuggestedIterations();
		for(int ii=0; ii <= max; ii++)
		{
			iterationsComboBox.addItem(new Integer(ii));
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
			int cur = ((Integer)iterationsComboBox.getSelectedItem()).intValue();
			
			iterationsComboBox.removeAllItems();
			
			int max = ((SimpleFractal)fractalComboBox.getSelectedItem()).getSuggestedIterations();
			for(int ii=0; ii <= max; ii++)
			{
				iterationsComboBox.addItem(new Integer(ii));
			}
			
			if(cur > max)
			{
				cur = max;
			}
			
			iterationsComboBox.setSelectedIndex(cur);
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
		
		fractalList.add(new SquareTriangle());
		fractalList.add(new SquareTriangleMod());
		fractalList.add(new BoxFractal());
		fractalList.add(new CesaroFractal());
		fractalList.add(new Crosses());
		fractalList.add(new Dragon());
		fractalList.add(new DragonMod());
		fractalList.add(new DragonColor());
		fractalList.add(new HFractal());
		fractalList.add(new InwardCircle());
		fractalList.add(new OutwardCircle());
		fractalList.add(new KochLine());
		fractalList.add(new KochLineMod());
		fractalList.add(new KochSnowflake());
		fractalList.add(new KochAntisnowflake());
		fractalList.add(new LevyCurve());
		fractalList.add(new LevyTapestry());
		fractalList.add(new LRepTile());
		fractalList.add(new LRepTileMod());
		fractalList.add(new MinkowskiLine());
		fractalList.add(new MinkowskiSausage());
		fractalList.add(new NestedPentagram());
		fractalList.add(new PentaFlake());
		fractalList.add(new PentaFlakeMod());
		fractalList.add(new PentaFractal());
		fractalList.add(new PythagorasShrub());
		fractalList.add(new PythagorasTree());
		fractalList.add(new Plusses());
		fractalList.add(new SierpinskiArrowhead());
		fractalList.add(new SierpinskiCarpet());
		fractalList.add(new SierpinskiTriangle());
		fractalList.add(new Squares());
		fractalList.add(new SquaresMod());
		fractalList.add(new TernaryTree());
		fractalList.add(new Thorn());
		fractalList.add(new TornSquare());
		fractalList.add(new ThreeBranch());
		fractalList.add(new TreeBlue());
		fractalList.add(new TreePink());
		fractalList.add(new TriCircle());
		fractalList.add(new TSquare());
		
		System.out.println(fractalList.size() + " fractals.");
		
		JFrame frame = new JFrame("Fractals");
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		frame.setSize(800,600);
		
		frame.setContentPane(new Fractal(fractalList));
		
		frame.setVisible(true);
	}
}

class FractalWorker extends SwingWorker<FractalPanel,FractalPanel>
{
	static final int delay = 1000;
	
	JButton redrawButton;
	FractalPanel fractalPanel;
	int iterations;
	long fireAt;
	
	public FractalWorker(JButton redrawButton, FractalPanel fractalPanel, int iterations)
	{
		this(redrawButton, fractalPanel, iterations, System.currentTimeMillis() + delay);
	}
	
	protected FractalWorker(JButton redrawButton, FractalPanel fractalPanel, int iterations, long fireAt)
	{
		this.redrawButton = redrawButton;
		this.fractalPanel = fractalPanel;
		this.iterations = iterations;
		this.fireAt = fireAt;
		
		fractalPanel.repaint();
		
		System.out.println(iterations);
	}
	
	protected FractalPanel doInBackground()
	{
		if(iterations > 0)
		{
			long fireIn = fireAt - System.currentTimeMillis();
			if(fireIn > 0)
			{
				try
				{
					Thread.sleep(fireIn);
				}
				catch(Exception e)
				{
				
				}
			}
			
			fractalPanel.next();
			
			FractalWorker nextWoker = new FractalWorker(redrawButton, fractalPanel, iterations-1, fireAt+delay);
			nextWoker.execute();
		}
		
		return fractalPanel;
	}
	
	protected void done()
	{
		if(iterations <= 0 )
		{
			redrawButton.setEnabled(true);
		}
		
		fractalPanel.repaint();
	}
}
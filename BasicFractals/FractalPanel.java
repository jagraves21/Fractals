import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Point;

import java.awt.image.*;

import java.util.*;

import javax.imageio.*;
import javax.imageio.metadata.*;
import javax.swing.*;

public class FractalPanel extends JPanel
{
	protected static final String memory = "Ran out of memeory.\nTry runing with -Xmx1g option.\n";
	
	protected BufferedImage bufferedImage;
	protected SimpleFractal fractal;
	
	public FractalPanel()
	{
		this(null);
	}
	
	public FractalPanel(SimpleFractal fractal)
	{
		super(true);
		
		this.fractal = fractal;
		bufferedImage = null;
	}
	
	public void setFractal(SimpleFractal fractal)
	{
		this.fractal = fractal;
	}
	
	public SimpleFractal getFractal()
	{
		return fractal;
	}
	
	public void next()
	{
		if(fractal != null)
		{
			try
			{
				fractal.next();
				clearFractal();
			}
			catch(OutOfMemoryError e)
			{
				//e.printStackTrace();
				System.out.println(memory);
				System.exit(-1);
			}
		}
	}
	
	public void clearFractal()
	{
		bufferedImage = null;
	}
	
	public void paint(Graphics g)
	{
		if(fractal != null)
		{
			try
			{
				if((bufferedImage == null) || (bufferedImage.getWidth() != getWidth()) || (bufferedImage.getHeight() != getHeight()))
				{
					try
					{
						bufferedImage = fractal.getFractalImage(getWidth(), getHeight());
					}
					catch(OutOfMemoryError e)
					{
						//e.printStackTrace();
						System.out.println(memory);
						System.exit(-1);
					}
				}
				
				g.drawImage(bufferedImage, 0, 0, null);
			}
			catch(OutOfMemoryError e)
			{
				//e.printStackTrace();
				System.out.println(memory);
				System.exit(-1);
			}
		}
	}
	
	public String toString()
	{
		return fractal.toString();
	}
	
	public static void displayFractal(SimpleFractal fractal)
	{
		displayFractal(fractal, fractal.getSuggestedIterations());
	}
	
	public static void displayFractal(SimpleFractal fractal, int iterations)
	{
		FractalPanel fractalPanel = new FractalPanel(new Dragon());
		for(int ii=0; ii < iterations; ii++)
		{
			fractalPanel.next();
		}
		
		fractal.findMinMax();
		MyPoint minPoint = fractal.getMinPoint();
		MyPoint maxPoint = fractal.getMaxPoint();
		int width = (int)(maxPoint.x - minPoint.x + 5);
		int height = (int)(maxPoint.y - minPoint.y + 5);
		Dimension dim = new Dimension(width, height);
		
		fractalPanel.setMinimumSize(dim);
		fractalPanel.setMaximumSize(dim);
		fractalPanel.setPreferredSize(dim);
		
		JFrame frame = new JFrame(fractal.toString());
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		frame.setSize(800,600);
		frame.setContentPane(new JScrollPane(fractalPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS));
		frame.setVisible(true);
	}
}
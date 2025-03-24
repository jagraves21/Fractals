//import java.awt.*;
//import java.awt.event.*;

//import java.util.*;

import javax.swing.*;

public class FractalWorker extends SwingWorker<FractalPanel,FractalPanel>
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

		//System.out.println(iterations);
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

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import java.awt.event.ActionEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.KeyStroke;
import javax.swing.SwingWorker;

import javax.swing.JFrame;
import javax.swing.JRootPane;

public abstract class AnimatedPanel extends Canvas implements Runnable {

	protected volatile Thread animationThread;
	protected volatile BufferStrategy bs;

	public AnimatedPanel() {
		super();
	}

	public void start() {
		if(animationThread == null || !animationThread.isAlive()) {
			animationThread = new Thread(this);
			animationThread.start();
		}
	}

	public void stop() {
		if(animationThread != null) {
			while(true) {
				animationThread.interrupt();
				try {
					animationThread.join(100);
					break;
				} catch(InterruptedException ie) {
					//ie.printStackTrace();
				}
			}
		}
	}

	public void run() {
		BufferStrategy bufferStrategy = null;
		while(!Thread.currentThread().isInterrupted()) {
			try {
				if(bufferStrategy == null || bufferStrategy.contentsLost()) {
					createBufferStrategy(2);
					bufferStrategy = getBufferStrategy();
				}
				render(bufferStrategy);
				
				try {
					Thread.sleep(10);
				} catch(InterruptedException ie) {
					//ie.printStackTrace();
					Thread.currentThread().interrupt();
				}
			} catch(IllegalStateException ise) {
				//ise.printStackTrace();
			}
		}
	}

	protected void render(BufferStrategy bufferStrategy) {
		if (bufferStrategy == null) return;
		do {
			do {
				Graphics g = bufferStrategy.getDrawGraphics();
				try {
					draw(g, getWidth(), getHeight());
					g.dispose();
				} finally {
					g.dispose();
					bufferStrategy.show();
				}
			} while (bufferStrategy.contentsRestored());
		} while (bufferStrategy.contentsLost());
	}

	public void render(Image image) {
		if (bs == null || !this.isDisplayable()) {
			return;
		}

		try {
			do {
				do {
					Graphics g = bs.getDrawGraphics();
					g.drawImage(image, 0, 0, Color.BLACK, null);
					g.dispose();
				} while(bs.contentsRestored());
				bs.show();
			} while(bs.contentsLost());
		} catch(IllegalStateException e) {

		}
	}

	public abstract void draw(Graphics g, int gWidth, int gHeight);

	public String toString() {
		return "Animated Panel";
	}

	public static JFrame display(final AnimatedPanel animatedPanel) {
		return display(animatedPanel, 500, 500);
	}

	public static JFrame display(final AnimatedPanel animatedPanel, int width, int height) {
		final JFrame frame = new JFrame(animatedPanel.toString());
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.add(animatedPanel);
		animatedPanel.setPreferredSize(new Dimension(width, height));
		frame.pack();
		frame.setLocationRelativeTo(null);

		frame.addWindowListener(new WindowAdapter() {
			public void windowOpened(WindowEvent e) {
				new SwingWorker<Void, Void>() {
					protected Void doInBackground() throws Exception {
						animatedPanel.start();
						return null;
					}
				}.execute();
			}

			public void windowClosed(WindowEvent e) {
				new SwingWorker<Void, Void>() {
					protected Void doInBackground() throws Exception {
						animatedPanel.stop();
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
		return frame;
	}
}

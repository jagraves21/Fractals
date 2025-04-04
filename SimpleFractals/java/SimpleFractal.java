import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import java.util.*;
import javax.imageio.*;
import javax.imageio.metadata.*;
import javax.swing.*;

public abstract class SimpleFractal extends AbstractFractal {
	protected MyPoint minPoint;
	protected MyPoint maxPoint;

	protected MyPoint newMinPoint;
	protected MyPoint newMaxPoint;

	public SimpleFractal() {
		minPoint = new MyPoint(Double.MAX_VALUE, Double.MAX_VALUE);
		maxPoint = new MyPoint(Double.MIN_VALUE, Double.MIN_VALUE);
	}

	public static MyPoint translate(MyPoint p1, MyPoint p2, double radius, double thetaOff) {
		double theta = Math.atan2(p2.y - p1.y, p2.x - p1.x);
		theta -= thetaOff;

		return new MyPoint(p1.x + (radius * Math.cos(theta)), p1.y + (radius * Math.sin(theta)));
	}

	public abstract List<FractalShape> getFractal();

	public abstract void next();

	public abstract void clearFractal();

	public int getSuggestedIterations() {
		return 10;
	}

	public int getSuggestedDelay() {
		return 500;
	}

	protected Paint getForeground() {
		return Color.WHITE;
	}

	protected Paint getBackground() {
		return Color.BLACK;
	}

	public BufferedImage getFractalImage(int width, int height) {
		// BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

		List<FractalShape> shapes = getFractal();
		findMinMax(shapes);
		shapes = scalePoints(shapes, image.getWidth(), image.getHeight());
		plotShapes(shapes, image.getWidth(), image.getHeight(), image.createGraphics());
		return image;
	}

	public void findMinMax() {
		findMinMax(getFractal());
	}

	protected void findMinMax(List<FractalShape> shapes) {
		minPoint.x = Double.MAX_VALUE;
		minPoint.y = Double.MAX_VALUE;

		maxPoint.x = Double.MIN_VALUE;
		maxPoint.y = Double.MIN_VALUE;

		FractalShape shape;

		Iterator<FractalShape> iter = shapes.iterator();
		while (iter.hasNext()) {
			shape = iter.next();
			shape.checkMinMax(minPoint, maxPoint);
		}
	}

	public MyPoint getMinPoint() {
		return minPoint;
	}

	public MyPoint getMaxPoint() {
		return maxPoint;
	}

	public List<FractalShape> scalePoints(int width, int height) {
		return scalePoints(getFractal(), width, height);
	}

	public List<FractalShape> scalePoints(int width, int height, double initXOff, double initYOff) {
		return scalePoints(getFractal(), width, height, initXOff, initYOff);
	}

	protected List<FractalShape> scalePoints(List<FractalShape> shapes, int width, int height) {
		return scalePoints(shapes, width, height, 0, 0);
	}

	protected List<FractalShape> scalePoints(
			List<FractalShape> shapes, int width, int height, double initXOff, double initYOff) {
		List<FractalShape> scaledShapes = new LinkedList<FractalShape>();

		double oldXRange = maxPoint.x - minPoint.x;
		double oldYRange = maxPoint.y - minPoint.y;

		if (oldXRange == 0) {
			oldXRange = 1;
		}
		if (oldYRange == 0) {
			oldYRange = 1;
		}

		double newXRange = width - 20;
		double newYRange = height - 20;

		double xRatio = oldXRange / newXRange;
		double yRatio = oldYRange / newYRange;

		if (xRatio > yRatio) {
			// oldX / oldY = newX / newY
			newYRange = newXRange / (oldXRange / oldYRange);
		} else if (yRatio > xRatio) {
			newXRange = (oldXRange / oldYRange) * newYRange;
		}

		double xOff = (width - newXRange) / 2;
		double yOff = (height - newYRange) / 2;

		newMinPoint = new MyPoint(xOff + initXOff, yOff + initYOff);
		newMaxPoint = new MyPoint(newXRange + xOff, newYRange + yOff);

		FractalShape shape;
		Iterator<FractalShape> lineIter = shapes.iterator();
		while (lineIter.hasNext()) {
			shape = lineIter.next();
			scaledShapes.add(
					shape.scale(minPoint, oldXRange, oldYRange, newMinPoint, newXRange, newYRange));
		}

		return scaledShapes;
	}

	protected void plotShapes(List<FractalShape> shapes, int width, int height, Graphics2D g) {
		g.setPaint(getBackground());
		g.fillRect(0, 0, width, height);
		g.setPaint(getForeground());

		/*try {
			BufferedImage image = ImageIO.read(new File("snowfield.jpeg"));
			g.drawImage(image, 0, 0, null);
		} catch (IOException e) {
			e.printStackTrace();
		}*/

		FractalShape shape;
		Iterator<FractalShape> iter = shapes.iterator();
		while (iter.hasNext()) {
			shape = iter.next();
			shape.paint(g);
		}
	}

	public void createSVG(String fileName, int width, int height, int iterations) {
		try {
			File file = new File(fileName);

			if (!file.exists()) {
				file.createNewFile();
			}

			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);

			bw.write("<svg xmlns=\"http://www.w3.org/2000/svg\" version=\"1.1\">");

			clearFractal();
			List<FractalShape> shapes;
			Iterator<FractalShape> iter;
			FractalShape shape;
			for (int ii = 0; ii <= iterations; ii++) {
				next();
			}

			shapes = getFractal();
			findMinMax(shapes);
			shapes = scalePoints(getFractal(), width, height);

			iter = shapes.iterator();
			while (iter.hasNext()) {
				shape = iter.next();
				if (shape instanceof LineSegment) {
					bw.write("<line x1=\"");
					bw.write(Double.toString(((LineSegment) shape).p1.x));
					bw.write("\" y1=\"");
					bw.write(Double.toString(((LineSegment) shape).p1.y));
					bw.write("\" x2=\"");
					bw.write(Double.toString(((LineSegment) shape).p2.x));
					bw.write("\" y2=\"");
					bw.write(Double.toString(((LineSegment) shape).p2.y));
					bw.write("\" style=\"stroke: green;\"/>");
					bw.newLine();
				}
			}

			bw.write("</svg>");

			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (OutOfMemoryError e) {
			// e.printStackTrace();
			System.out.println(memory);
			System.exit(-1);
		}
	}

	public void createGIF(String fileName, int width, int height, int iterations, int delay) {
		createGIF(fileName, width, height, iterations, delay, true);
	}

	public void createGIF(
			String fileName, int width, int height, int iterations, int delay, boolean reflect) {
		try {
			clearFractal();

			AnimatedGifEncoder e = new AnimatedGifEncoder();
			e.start(fileName);
			e.setDelay(delay);
			e.setRepeat(0);

			BufferedImage[] images = new BufferedImage[iterations + 1];
			for (int ii = 0; ii < iterations; ii++) {
				images[ii] = getFractalImage(width, height);
				next();
			}
			images[iterations] = getFractalImage(width, height);

			for (int ii = 0; ii < images.length; ii++) {
				e.addFrame(images[ii]);
			}

			if (reflect) {
				// for(int ii=images.length-1; ii > 0; ii--)
				for (int ii = images.length - 2; ii > 0; ii--) {
					e.addFrame(images[ii]);
				}
			}

			e.finish();
		} catch (OutOfMemoryError e) {
			// e.printStackTrace();
			System.out.println(memory);
			System.exit(-1);
		}
	}

	public void createPic(String fileName, String type, int width, int height, int iterations) {
		try {
			clearFractal();
			for (int ii = 0; ii < iterations; ii++) {
				next();
			}

			BufferedImage image = getFractalImage(width, height);
			File outputfile = new File(fileName);
			ImageIO.write(image, type, outputfile);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (OutOfMemoryError e) {
			// e.printStackTrace();
			System.out.println(memory);
			System.exit(-1);
		}
	}

	public void createJPG(String fileName, int width, int height, int iterations) {
		createPic(fileName, "jpg", width, height, iterations);
	}

	public void createPNG(String fileName, int width, int height, int iterations) {
		createPic(fileName, "png", width, height, iterations);
	}

	public String toString() {
		return "Simple Fractal";
	}

	public void displayFractal(int width, int height, int iterations, int delay) {
		System.out.println(this + " " + width + " " + height + " " + iterations + " " + delay);
		JFrame frame = new JFrame(toString());
		// frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setPreferredSize(new Dimension(width, height));

		FractalPanel fractalPanel = new FractalPanel(this, iterations, delay);
		frame.add(fractalPanel);
		frame.pack();
		frame.setLocationRelativeTo(null);

		frame.addWindowListener(
			new WindowAdapter() {
				public void windowOpened(WindowEvent e) {
					new SwingWorker<Void, Void>() {
						protected Void doInBackground() throws Exception {
							fractalPanel.start();
							return null;
						}
					}.execute();
				}

				public void windowClosing(WindowEvent e) {
					new SwingWorker<Void, Void>() {
						protected Void doInBackground() throws Exception {
							fractalPanel.stop();
							return null;
						}
					}.execute();
				}
			}
		);

		String os = System.getProperty("os.name").toLowerCase();
		String keyStroke = os.contains("mac") ? "meta W" : "control W";

		JRootPane rootPane = frame.getRootPane();
		InputMap inputMap = rootPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
		ActionMap actionMap = rootPane.getActionMap();
		inputMap.put(KeyStroke.getKeyStroke(keyStroke), "close");
		actionMap.put(
			"close",
			new AbstractAction() {
				public void actionPerformed(ActionEvent e) {
					frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
				}
			}
		);

		frame.setVisible(true);
	}

	public static void main(String[] args) {
		try {
			String callerClassName = Thread.currentThread().getStackTrace()[2].getClassName();
			Class<?> callerClass = Class.forName(callerClassName);
			Object instance = callerClass.getConstructor().newInstance();
			SimpleFractal simpleFractal = (SimpleFractal) instance;

			ArgumentParser argumentParser = new ArgumentParser(
				callerClassName,
				800,
				600,
				simpleFractal.getSuggestedIterations(),
				simpleFractal.getSuggestedDelay()
			);
			argumentParser.parseArguments(args);
			simpleFractal.displayFractal(
				argumentParser.width,
				argumentParser.height,
				argumentParser.iterations,
				argumentParser.delay
			);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

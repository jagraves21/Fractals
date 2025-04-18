import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

public class ComplexFractal extends AnimatedPanel {
	public enum FractalType {
		ITERATIVE {
			public String toString() {
				return "Iterative";
			}
		},
		MOVING {
			public String toString() {
				return "Moving";
			}
		},
		RANDOM {
			public String toString() {
				return "Random";
			}
		}
	}

	public enum FractalStyle {
		STANDARD {
			public String toString() {
				return "Standard";
			}
		},
		CONTOURED {
			public String toString() {
				return "Contoured";
			}
		},
		FREQUENCY {
			public String toString() {
				return "Frequency";
			}
		}
	}

	protected Object lock;

	protected double originX, originY;
	protected double viewWidth;
	protected int totalIterations;
	protected int maxIteration;
	protected int prevIterationSum;
	protected int curIterationSum;

	protected ComplexFunction complexFunction;
	protected ConvergenceFunction convergenceFunction;
	protected ColorFunction colorFunction;
	protected FractalType fractalType;
	protected FractalStyle fractalStyle;
	protected boolean cycleColors;

	protected int shift;

	int[][] pointIterations;
	Complex[][][] points;

	public ComplexFractal(
		ComplexFunction complexFunction,
		ConvergenceFunction convergenceFunction
	) {
		this(complexFunction, convergenceFunction, new PalletedColorFunction());
	}

	public ComplexFractal(
		ComplexFunction complexFunction,
		ConvergenceFunction convergenceFunction,
		ColorFunction colorFunction
	) {
		this(complexFunction, convergenceFunction, colorFunction, FractalType.ITERATIVE);
	}

	public ComplexFractal(
		ComplexFunction complexFunction,
		ConvergenceFunction convergenceFunction,
		ColorFunction colorFunction,
		FractalStyle fractalStyle
	) {
		this(complexFunction, convergenceFunction, colorFunction, FractalType.ITERATIVE, fractalStyle);
	}

	public ComplexFractal(
		ComplexFunction complexFunction,
		ConvergenceFunction convergenceFunction,
		ColorFunction colorFunction,
		FractalType fractalType
	) {
		this(complexFunction, convergenceFunction, colorFunction, fractalType, FractalStyle.STANDARD);
	}

	public ComplexFractal(
		ComplexFunction complexFunction,
		ConvergenceFunction convergenceFunction,
		ColorFunction colorFunction,
		FractalType fractalType,
		FractalStyle fractalStyle
	) {
		this(
			complexFunction == null ? 0 : complexFunction.getOriginX(),
			complexFunction == null ? 0 : complexFunction.getOriginY(),
			complexFunction == null ? 0 : complexFunction.getViewWidth(),
			complexFunction,
			convergenceFunction,
			colorFunction,
			fractalType,
			fractalStyle
		);
	}

	public ComplexFractal(
		double x,
		double y,
		double width,
		ComplexFunction complexFunction,
		ConvergenceFunction convergenceFunction,
		ColorFunction colorFunction
	) {
		this(
			x,
			y,
			width,
			complexFunction,
			convergenceFunction,
			colorFunction,
			FractalType.ITERATIVE,
			FractalStyle.STANDARD
		);
	}

	public ComplexFractal(
		double x,
		double y,
		double width,
		ComplexFunction complexFunction,
		ConvergenceFunction convergenceFunction,
		ColorFunction colorFunction,
		FractalType fractalType
	) {
		this(
			x,
			y,
			width,
			complexFunction,
			convergenceFunction,
			colorFunction,
			fractalType,
			FractalStyle.STANDARD
		);
	}

	public ComplexFractal(
		double x,
		double y,
		double width,
		ComplexFunction complexFunction,
		ConvergenceFunction convergenceFunction,
		ColorFunction colorFunction,
		FractalStyle fractalStyle
	) {
		this(
			x,
			y,
			width,
			complexFunction,
			convergenceFunction,
			colorFunction,
			FractalType.ITERATIVE,
			fractalStyle
		);
	}

	public ComplexFractal(
		double x,
		double y,
		double width,
		ComplexFunction complexFunction,
		ConvergenceFunction convergenceFunction,
		ColorFunction colorFunction,
		FractalType fractalType,
		FractalStyle fractalStyle
	) {
		this(
			x,
			y,
			width,
			complexFunction,
			convergenceFunction,
			colorFunction,
			FractalType.ITERATIVE,
			fractalStyle,
			false
		);
	}

	public ComplexFractal(
		double x,
		double y,
		double width,
		ComplexFunction complexFunction,
		ConvergenceFunction convergenceFunction,
		ColorFunction colorFunction,
		FractalType fractalType,
		FractalStyle fractalStyle,
		boolean cycleColors
	) {
		super();

		lock = new Object();

		originX = x;
		originY = y;
		viewWidth = width;
		this.complexFunction = complexFunction;
		this.convergenceFunction = convergenceFunction;
		this.colorFunction = colorFunction;
		this.fractalType = fractalType;
		this.fractalStyle = fractalStyle;
		this.

		shift = 0;

		pointIterations = new int[0][0];
		points = new Complex[0][0][0];

		totalIterations = 0;
		maxIteration = 0;
		prevIterationSum = 1;
		curIterationSum = 1;

		addMouseListeners();
	}

	protected void addMouseListeners() {
		addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				double widthScale = (getWidth() < getHeight()) ? 1.0 : getWidth() / (double) getHeight();
				double heightScale = (getWidth() < getHeight()) ? getHeight() / (double) getWidth() : 1.0;

				double mouseFracX = (e.getX()/(double)getWidth()) * widthScale * viewWidth - (widthScale*viewWidth/2);
				double mouseFracY = (e.getY()/(double)getHeight()) * heightScale * viewWidth - (heightScale*viewWidth/2);

				double newOriginX = originX + mouseFracX;
				double newOriginY = originY + mouseFracY;
				System.out.println("origin: -x " + newOriginX + " -y " + newOriginY);
				setWindow(newOriginX, newOriginY, viewWidth);
			}
		});

		addMouseWheelListener(new MouseAdapter() {
			public void mouseWheelMoved(MouseWheelEvent e) {
				double rotation = e.getPreciseWheelRotation();
				double scaleBase = (rotation < 0) ? 0.95 : 1.05;
				double zoomFactor = Math.pow(scaleBase, Math.abs(rotation)) * e.getScrollAmount();

				double widthScale = (getWidth() < getHeight()) ? 1.0 : getWidth() / (double) getHeight();
				double heightScale = (getWidth() < getHeight()) ? getHeight() / (double) getWidth() : 1.0;

				double mouseFracX = (e.getX()/(double)getWidth()) * widthScale * viewWidth - (widthScale*viewWidth/2);
				double mouseFracY = (e.getY()/(double)getHeight()) * heightScale * viewWidth - (heightScale*viewWidth/2);

				double newOriginX = originX + mouseFracX;
				double newOriginY = originY + mouseFracY;

				double newViewWidth = viewWidth * zoomFactor;

				mouseFracX = (e.getX()/(double)getWidth()) * widthScale * newViewWidth - (widthScale*newViewWidth/2);
				mouseFracY = (e.getY()/(double)getHeight()) * heightScale * newViewWidth - (heightScale*newViewWidth/2);

				newOriginX -= mouseFracX;
				newOriginY -= mouseFracY;

				setWindow(newOriginX, newOriginY, newViewWidth);
			}
		});
	}

	public ComplexFunction getComplexFunction() {
		return complexFunction;
	}

	public void setComplexFunction(ComplexFunction complexFunction) {
		this.complexFunction = complexFunction;
		synchronized (lock) {
			totalIterations = 0;
		}
		if(complexFunction != null) {
			complexFunction.init();
		}
	}

	public ConvergenceFunction getSuggestedConvergenceFunction() {
		return convergenceFunction;
	}

	public void setConvergenceFunction(ConvergenceFunction convergenceFunction) {
		this.convergenceFunction = convergenceFunction;
		synchronized (lock) {
			totalIterations = 0;
		}
	}

	public ColorFunction getSuggestedColorFunction(){
		return colorFunction;
	}

	public void setColorFunction(ColorFunction colorFunction) {
		this.colorFunction = colorFunction;
	}

	public FractalType getSuggestedFractalType() {
		return fractalType;
	}

	public void setFractalType(FractalType fractalType) {
		this.fractalType = fractalType;

		maxIteration = 0;
		synchronized (lock) {
			totalIterations = 0;
		}

		if(complexFunction != null) {
			complexFunction.init();
		}
	}

	public FractalStyle getSuggestedFractalStyle() {
		return fractalStyle;
	}

	public void setFractalStyle(FractalStyle fractalStyle) {
		this.fractalStyle = fractalStyle;
	}

	public void setWindow(double originX, double originY, double viewWidth) {
		this.originX = originX;
		this.originY = originY;
		this.viewWidth = viewWidth;

		synchronized (lock) {
			totalIterations = 0;
		}
	}

	public void setMaxIteration(int maxIteration) {
		this.maxIteration = maxIteration;
	}

	protected void allocateComplexMatrices(double originX, double originY, double viewWidth, double width, double height, double widthScale, double heightScale) {
		if(points.length != height || (points.length > 0 && points[0].length != width)) {
			points = new Complex[(int)height][(int)width][2];
			pointIterations = new int[(int)height][(int)width];
		}

		for(int ii=0; ii < height; ii++) {
			for(int jj=0; jj < width; jj++) {
				points[ii][jj][0] = new Complex(0,0);
				points[ii][jj][1] = new Complex(0,0);
				points[ii][jj][0].re = originX + jj/width * widthScale*viewWidth - widthScale*viewWidth/2;
				points[ii][jj][0].im = originY + ii/height * heightScale*viewWidth - heightScale*viewWidth/2;

				complexFunction.convert(points[ii][jj][0], points[ii][jj][1]);

				pointIterations[ii][jj] = 0;
			}
		}
	}

	protected void initializeComplexMatrices(double originX, double originY, double viewWidth, double width, double height, double widthScale, double heightScale) {
		for(int ii=0; ii < height; ii++) {
			for(int jj=0; jj < width; jj++) {
				points[ii][jj][0].re = originX + jj/width * widthScale*viewWidth - widthScale*viewWidth/2;
				points[ii][jj][0].im = originY + ii/height * heightScale*viewWidth - heightScale*viewWidth/2;

				complexFunction.convert(points[ii][jj][0], points[ii][jj][1]);

				pointIterations[ii][jj] = 0;
			}
		}
	}

	public void start() {
		totalIterations = 0;
		maxIteration = 0;

		if(complexFunction != null) {
			complexFunction.init();
		}

		super.start();
	}

	public void draw(Graphics g, int gWidth, int gHeight) {
		step(gWidth, gHeight);
		drawFractal(g, gWidth, gHeight);

		/*
		g.setColor(Color.WHITE);
        g.drawLine(gWidth / 2, 0, gWidth / 2, gHeight);
        g.drawLine(0, gHeight / 2, gWidth, gHeight / 2);
        g.drawLine(0, 0, gWidth, gHeight);
        g.drawLine(0, gHeight, gWidth, 0);

        g.setColor(Color.RED);
        g.drawLine((int)(gWidth * 1.0/4.0), 0, (int)(gWidth * 1.0/4.0), gHeight);
        g.drawLine((int)(gWidth * 3.0/4.0), 0, (int)(gWidth * 3.0/4.0), gHeight);
        g.drawLine(0, (int)(gHeight * 1.0/4.0), gWidth, (int)(gHeight * 1.0/4.0));
        g.drawLine(0, (int)(gHeight * 3.0/4.0), gWidth, (int)(gHeight * 3.0/4.0));
		*/
	}

	public void step(int gWidth, int gHeight) {
		Complex z = null;
		Complex mu = null;
		double widthScale, heightScale;
		int iteration;

		int totalIterations;
		double originY = this.originY;
		double viewWidth = this.viewWidth;
		double width = gWidth;
		double height = gHeight;
		ComplexFunction complexFunction = this.complexFunction;
		ConvergenceFunction convergenceFunction = this.convergenceFunction;
		ColorFunction colorFunction = this.colorFunction;
		FractalStyle fractalStyle = this.fractalStyle;
		
		if(complexFunction == null || convergenceFunction == null || colorFunction == null) {
			return;
		}

		synchronized (lock) {
			totalIterations = this.totalIterations++;
		}

		if(width < height) {
			widthScale = 1;
			heightScale = height/width;
		} else {
			widthScale = width/height;
			heightScale = 1;
		}

		if(points.length != height || (points.length > 0 && points[0].length != width)) {
	 		allocateComplexMatrices(originX, originY, viewWidth, width, height, widthScale, heightScale);
	 		totalIterations = 0;
		} else if(totalIterations == 0) {
			initializeComplexMatrices(originX, originY, viewWidth, width, height, widthScale, heightScale);
		}

		if(fractalType == FractalType.ITERATIVE) {
			if(fractalStyle == FractalStyle.FREQUENCY) {
				maxIteration += 1000;
			} else {
				maxIteration++;
			}
		} else if(fractalType == FractalType.MOVING) {
			complexFunction.move();
			maxIteration = 200;
			totalIterations = 0;
			initializeComplexMatrices(originX, originY, viewWidth, width, height, widthScale, heightScale);
		} else if(fractalType == FractalType.RANDOM) {
			if(prevIterationSum/(double)curIterationSum > 0.95) {
				complexFunction.random();
				maxIteration = 0;

				if(totalIterations != 0) {
					totalIterations = 0;
					initializeComplexMatrices(originX, originY, viewWidth, width, height, widthScale, heightScale);
				}
			} else {
				maxIteration++;
			}
		}

		prevIterationSum = curIterationSum;
		curIterationSum = 0;
		for(int ii=0; ii < height; ii++) {
			for(int jj=0; jj < width; jj++) {
				z = points[ii][jj][0];
				mu = points[ii][jj][1];

				iteration = totalIterations;

				while(!convergenceFunction.escaped(z) && iteration < maxIteration) {
					complexFunction.next(z, mu);
					pointIterations[ii][jj]++;
					iteration++;
				}

				if(convergenceFunction.escaped(z)) {
					curIterationSum += iteration;
				}
			}
		}

		synchronized (lock) {
			if(this.totalIterations != 0) {
				this.totalIterations = maxIteration;
			}
		}
	}

	public synchronized void drawFractal(Graphics g, int gWidth, int gHeight) {
		Complex z = null;
		Complex mu = null;
		double widthScale, heightScale;
		int iteration;

		double originX = this.originX;
		double originY = this.originY;
		double viewWidth = this.viewWidth;
		double width = gWidth;
		double height = gHeight;
		ComplexFunction complexFunction = this.complexFunction;
		ConvergenceFunction convergenceFunction = this.convergenceFunction;
		ColorFunction colorFunction = this.colorFunction;
		FractalStyle fractalStyle = this.fractalStyle;

		if(cycleColors) {
			shift++;
		}

		if(complexFunction == null || convergenceFunction == null || colorFunction == null) {
			return;
		}

		if(width < height) {
			widthScale = 1;
			heightScale = height/width;
		} else {
			widthScale = width/height;
			heightScale = 1;
		}

		if(fractalStyle == FractalStyle.STANDARD) {
			for(int ii=0; ii < height; ii++) {
				for(int jj=0; jj < width; jj++) {
					z = points[ii][jj][0];
					mu = points[ii][jj][1];
					if(!convergenceFunction.escaped(z)) {
						g.setColor(
							colorFunction.getConvergentColor(
								z, pointIterations[ii][jj]+shift, maxIteration
							)
						);
					} else {
						g.setColor(
							colorFunction.getDivergentColor(
								z, pointIterations[ii][jj]+shift, maxIteration
							)
						);
					}
					g.drawLine(jj,ii,jj,ii);
				}
			}
		} else if(fractalStyle == FractalStyle.CONTOURED) {
			g.setColor(Color.BLACK);
			g.drawRect(0,0,(int)width-1,(int)height-1);
			for(int ii=1; ii < height-1; ii++) {
				for(int jj=1; jj < width-1; jj++) {
					if(pointIterations[ii][jj] != pointIterations[ii-1][jj] ||
						pointIterations[ii][jj] != pointIterations[ii+1][jj] ||
						pointIterations[ii][jj] != pointIterations[ii][jj-1] ||
						pointIterations[ii][jj] != pointIterations[ii][jj+1])
					{
						z = points[ii][jj][0];
						mu = points[ii][jj][1];

						if(!convergenceFunction.escaped(z)) {
							g.setColor(
								colorFunction.getConvergentColor(
									z, pointIterations[ii][jj]+shift, maxIteration
								)
							);
						} else {
							g.setColor(
								colorFunction.getDivergentColor(
									z, pointIterations[ii][jj]+shift, maxIteration
								)
							);
						}
					} else {
						g.setColor(Color.BLACK);
					}

					g.drawLine(jj,ii,jj,ii);
				}
			}
		} else if(fractalStyle == FractalStyle.FREQUENCY) {
			int[][] field = new int[(int)height][(int)width];
			for(int ii=0; ii < height; ii++) {
				for(int jj=0; jj < width; jj++) {
					field[ii][jj] = 1;
				}
			}

			z = new Complex(0,0);
			mu = new Complex(0,0);
			double LOG_MAX = 1;
			for(int ii=0; ii < height; ii++) {
				for(int jj=0; jj < width; jj++) {
					if(convergenceFunction.escaped(points[ii][jj][0])) {
						z.re = originX + jj/width * widthScale*viewWidth - widthScale*viewWidth/2;
						z.im = originY + ii/height * heightScale*viewWidth - heightScale*viewWidth/2;
						complexFunction.convert(z, mu);

						iteration = 0;
						while(!convergenceFunction.escaped(z) && iteration < maxIteration) {
							complexFunction.next(z, mu);
							iteration++;

							int x = (int)Math.round((z.re - (originX - ((viewWidth * widthScale) / 2)))/(viewWidth * widthScale) * width);
							int y = (int)Math.round((z.im - (originY - ((viewWidth * heightScale) / 2)))/(viewWidth * heightScale) * height);

							if(x >= 0 && x < width && y >= 0 && y < height) {
								if(iteration < (maxIteration / 3.0)) {
									field[y][x]++;
									if(field[y][x] > LOG_MAX) {
										LOG_MAX = field[y][x];
									}
								}
							}
						}
					}
				}
			}

			LOG_MAX = Math.log(LOG_MAX);
			double M = (LOG_MAX) / 255.0;
			double logVal;
			int red, green, blue;
			for(int ii=0; ii < height; ii++) {
				for(int jj=0; jj < width; jj++) {
					logVal = Math.log(field[ii][jj]);
					logVal = logVal / M;

					red = green = blue = (int)Math.round(logVal);

					g.setColor(new Color(red, green, blue));
					g.drawLine(jj,ii,jj,ii);
				}
			}
		}
	}

	public String toString() {
		return complexFunction.toString();
	}
}

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
	protected double windowWidth;
	protected int totalIterations;
	protected int maxIteration;
	protected int prevIterationSum;
	protected int curIterationSum;
	
	protected ComplexFunction complexFunction;
	protected ConvergenceFunction convergenceFunction;
	protected ColorFunction colorFunction;
	protected FractalType fractalType;
	protected FractalStyle fractalStyle;
	protected boolean colorsCycle;

	protected int shift;
	
	int[][] pointIterations;
	Complex[][][] points;
	
	public ComplexFractal(ComplexFunction complexFunction, ConvergenceFunction convergenceFunction) {
		this(complexFunction, convergenceFunction, new PalletedColorFunction());
	}
	
	public ComplexFractal(ComplexFunction complexFunction, ConvergenceFunction convergenceFunction, ColorFunction colorFunction) {
		this(complexFunction, convergenceFunction, colorFunction, FractalType.ITERATIVE);
	}
	
	public ComplexFractal(ComplexFunction complexFunction, ConvergenceFunction convergenceFunction, ColorFunction colorFunction, FractalStyle fractalStyle) {
		this(complexFunction, convergenceFunction, colorFunction, FractalType.ITERATIVE, fractalStyle);
	}
	
	public ComplexFractal(ComplexFunction complexFunction, ConvergenceFunction convergenceFunction, ColorFunction colorFunction, FractalType fractalType) {
		this(complexFunction, convergenceFunction, colorFunction, fractalType, FractalStyle.STANDARD);
	}
	
	public ComplexFractal(ComplexFunction complexFunction, ConvergenceFunction convergenceFunction, ColorFunction colorFunction, FractalType fractalType, FractalStyle fractalStyle) {
		this(0, 0, 3, complexFunction, convergenceFunction, colorFunction, fractalType, fractalStyle);
	}
	
	public ComplexFractal(double x, double y, double width, ComplexFunction complexFunction, ConvergenceFunction convergenceFunction, ColorFunction colorFunction) {
		this(x, y, width, complexFunction, convergenceFunction, colorFunction, FractalType.ITERATIVE, FractalStyle.STANDARD);
	}
	
	public ComplexFractal(double x, double y, double width, ComplexFunction complexFunction, ConvergenceFunction convergenceFunction, ColorFunction colorFunction, FractalType fractalType) {
		this(x, y, width, complexFunction, convergenceFunction, colorFunction, fractalType, FractalStyle.STANDARD);
	}
	
	public ComplexFractal(double x, double y, double width, ComplexFunction complexFunction, ConvergenceFunction convergenceFunction, ColorFunction colorFunction, FractalStyle fractalStyle) {
		this(x, y, width, complexFunction, convergenceFunction, colorFunction, FractalType.ITERATIVE, fractalStyle);
	}
	
	public ComplexFractal(double x, double y, double width, ComplexFunction complexFunction, ConvergenceFunction convergenceFunction, ColorFunction colorFunction, FractalType fractalType, FractalStyle fractalStyle) {
		this(x, y, width, complexFunction, convergenceFunction, colorFunction, FractalType.ITERATIVE, fractalStyle, false);
	}
	
	public ComplexFractal(double x, double y, double width, ComplexFunction complexFunction, ConvergenceFunction convergenceFunction, ColorFunction colorFunction, FractalType fractalType, FractalStyle fractalStyle, boolean colorsCycle) {
		super();
		
		lock = new Object();
		
		originX = x;
		originY = y;
		windowWidth = width;
		this.complexFunction = complexFunction;
		this.convergenceFunction = convergenceFunction;
		this.colorFunction = colorFunction;
		this.fractalType = fractalType;
		this.fractalStyle = fractalStyle;
		this.colorsCycle = colorsCycle;

		shift = 0;
		
		pointIterations = new int[0][0];
		points = new Complex[0][0][0];
		
		totalIterations = 0;
		maxIteration = 0;
		prevIterationSum = 1;
		curIterationSum = 1;
		
		addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				originX = originX + (((double)e.getX())/getWidth()) * windowWidth - windowWidth/2;
				originY = originY + (((double)e.getY())/getHeight()) * windowWidth - windowWidth/2;

				double widthScale, heightScale;
				if(getWidth() < getHeight()) {
					widthScale = 1;
					heightScale = getHeight()/(double)getWidth();
				}
				else {
					widthScale = getWidth()/(double)getHeight();
					heightScale = 1;
				}

				synchronized (lock) {
					totalIterations = 0;
				}

				System.out.println("Center: " + originX + " " + originY);
			}
		});

		addMouseWheelListener(new MouseAdapter() {
			public void mouseWheelMoved(MouseWheelEvent e) {
				double rotation = e.getPreciseWheelRotation();
				double scalingFactor = (rotation < 0) ? 0.95 : 1.05;
				double multiplier = Math.pow((rotation < 0) ? 0.95 : 1.05, Math.abs(rotation));

				if(e.getScrollType() == MouseWheelEvent.WHEEL_UNIT_SCROLL) {

					double widthScale, heightScale;
					if(getWidth() < getHeight()) {
						widthScale = 1;
						heightScale = getHeight()/(double)getWidth();
					}
					else {
						widthScale = getWidth()/(double)getHeight();
						heightScale = 1;
					}

					double x0 = e.getX()/(double)getWidth() * widthScale*windowWidth - widthScale*windowWidth/2;
					double y0 = e.getY()/(double)getHeight() * heightScale*windowWidth - heightScale*windowWidth/2;

					originX += x0;
					originY += y0;

					windowWidth *= (multiplier*e.getScrollAmount());

					x0 = e.getX()/(double)getWidth() * widthScale*windowWidth - widthScale*windowWidth/2;
					y0 = e.getY()/(double)getHeight() * heightScale*windowWidth - heightScale*windowWidth/2;

					originX -= x0;
					originY -= y0;

					synchronized (lock) {
						totalIterations = 0;
					}
				}
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
	
	public ConvergenceFunction getConvergenceFunction() {
		return convergenceFunction;
	}
	
	public void setConvergenceFunction(ConvergenceFunction convergenceFunction) {
		this.convergenceFunction = convergenceFunction;
		
		synchronized (lock) {
			totalIterations = 0;
		}
	}
	
	public ColorFunction getColorFunction(){
		return colorFunction;
	}
	
	public void setColorFunction(ColorFunction colorFunction) {
		this.colorFunction = colorFunction;
	}
	
	public FractalType getFractalType() {
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
	
	public FractalStyle getFractalStyle() {
		return fractalStyle;
	}
	
	public void setFractalStyle(FractalStyle fractalStyle) {
		this.fractalStyle = fractalStyle;
	}
	
	public void setWindow(double originX, double originY, double windowWidth) {
		this.originX = originX;
		this.originY = originY;
		this.windowWidth = windowWidth;
		
		synchronized (lock) {
			totalIterations = 0;
		}
	}
	
	public void setMaxIteration(int maxIteration) {
		this.maxIteration = maxIteration;
	}
	
	protected void allocateComplexMatrices(double originX, double originY, double windowWidth, double maxWidth, double maxHeight, double widthScale, double heightScale) {
		if(points.length != maxWidth || (points.length > 0 && points[0].length != maxHeight)) {
			points = new Complex[(int)maxWidth][(int)maxHeight][2];
			pointIterations = new int[(int)maxWidth][(int)maxHeight];
		}
		
		for(int width=0; width < maxWidth; width++) {
			for(int height=0; height < maxHeight; height++) {
				points[width][height][0] = new Complex(0,0);
				points[width][height][1] = new Complex(0,0);
				points[width][height][0].re = originX + width/maxWidth * widthScale*windowWidth - widthScale*windowWidth/2;
				points[width][height][0].im = originY + height/maxHeight * heightScale*windowWidth - heightScale*windowWidth/2;
				
				complexFunction.convert(points[width][height][0], points[width][height][1]);
				
				pointIterations[width][height] = 0;
			}
		}
	}
	
	protected void initializeComplexMatrices(double originX, double originY, double windowWidth, double maxWidth, double maxHeight, double widthScale, double heightScale) {
		for(int width=0; width < maxWidth; width++) {
			for(int height=0; height < maxHeight; height++) {
				points[width][height][0].re = originX + width/maxWidth * widthScale*windowWidth - widthScale*windowWidth/2;
				points[width][height][0].im = originY + height/maxHeight * heightScale*windowWidth - heightScale*windowWidth/2;
				
				complexFunction.convert(points[width][height][0], points[width][height][1]);
				
				pointIterations[width][height] = 0;
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
		drawFractal(g, gWidth, gHeight);
	}
	
	public synchronized void drawFractal(Graphics g, int gWidth, int gHeight) {
		Complex z = null;
		Complex mu = null;
		double widthScale, heightScale;
		int iteration;
		
		int totalIterations;
		double originX = this.originX;
		double originY = this.originY;
		double windowWidth = this.windowWidth;
		double maxWidth = gWidth;
		double maxHeight = gHeight;
		ComplexFunction complexFunction = this.complexFunction;
		ConvergenceFunction convergenceFunction = this.convergenceFunction;
		ColorFunction colorFunction = this.colorFunction;
		FractalStyle fractalStyle = this.fractalStyle;
		
		if(colorsCycle) {
			shift++;
		}
		
		synchronized (lock) {
			totalIterations = this.totalIterations++;
		}
		
		if(complexFunction == null || convergenceFunction == null || colorFunction == null) {
			return;
		}
		
		if(maxWidth < maxHeight) {
			widthScale = 1;
			heightScale = maxHeight/maxWidth;
		}
		else {
			widthScale = maxWidth/maxHeight;
			heightScale = 1;
		}
		
		if(points.length != maxWidth || (points.length > 0 && points[0].length != maxHeight)) {
	 		allocateComplexMatrices(originX, originY, windowWidth, maxWidth, maxHeight, widthScale, heightScale);
	 		totalIterations = 0;
		}
		else if(totalIterations == 0) {
			initializeComplexMatrices(originX, originY, windowWidth, maxWidth, maxHeight, widthScale, heightScale);
		}
		
		if(fractalType == FractalType.ITERATIVE) {
			maxIteration++;
			
			if(fractalStyle == FractalStyle.FREQUENCY) {
				maxIteration += 1000;
			}
		}
		else if(fractalType == FractalType.MOVING)
		{
			complexFunction.move();
			maxIteration = 200;
			
			initializeComplexMatrices(originX, originY, windowWidth, maxWidth, maxHeight, widthScale, heightScale);
			totalIterations = 0;
		}
		else if(fractalType == FractalType.RANDOM)
		{
			maxIteration++;
			
			if(prevIterationSum/(double)curIterationSum > 0.95) {
				complexFunction.random();
				maxIteration = 0;
				
				if(totalIterations != 0) {
					initializeComplexMatrices(originX, originY, windowWidth, maxWidth, maxHeight, widthScale, heightScale);
					totalIterations = 0;
				}
			}
		}
		
		prevIterationSum = curIterationSum;
		curIterationSum = 0;
		for(int width=0; width < maxWidth; width++) {
			for(int height=0; height < maxHeight; height++) {
				z = points[width][height][0];
				mu = points[width][height][1];
				
				iteration = totalIterations;
				
				while(!convergenceFunction.escaped(z) && iteration < maxIteration) {
					complexFunction.next(z, mu);
					iteration++;
					
					pointIterations[width][height]++;
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
		
		if(fractalStyle == FractalStyle.STANDARD) {
			for(int width=0; width < maxWidth; width++) {
				for(int height=0; height < maxHeight; height++) {
					z = points[width][height][0];
					mu = points[width][height][1];
					
					if(!convergenceFunction.escaped(z)) {
						g.setColor(colorFunction.getConvergentColor(z,pointIterations[width][height]+shift,maxIteration));
					}
					else {
						g.setColor(colorFunction.getDivergentColor(z,pointIterations[width][height]+shift,maxIteration));
					}
					
					g.drawLine(width,height,width,height);
				}
			}
		}
		else if(fractalStyle == FractalStyle.CONTOURED) {
			g.setColor(Color.BLACK);
			g.drawRect(0,0,(int)maxWidth-1,(int)maxHeight-1);
			for(int width=1; width < maxWidth-1; width++) {
				for(int height=1; height < maxHeight-1; height++) {
					if(pointIterations[width][height] != pointIterations[width-1][height] || 
						pointIterations[width][height] != pointIterations[width+1][height] ||
						pointIterations[width][height] != pointIterations[width][height-1] ||
						pointIterations[width][height] != pointIterations[width][height+1])
					{
						z = points[width][height][0];
						mu = points[width][height][1];
						
						if(!convergenceFunction.escaped(z)) {
							g.setColor(colorFunction.getConvergentColor(z,pointIterations[width][height]+shift,maxIteration));
						}
						else {
							g.setColor(colorFunction.getDivergentColor(z,pointIterations[width][height]+shift,maxIteration));
						}
					}
					else {
						g.setColor(Color.BLACK);
					}
					
					g.drawLine(width,height,width,height);
				}
			}
		}
		else if(fractalStyle == FractalStyle.FREQUENCY) {
			int[][] field = new int[(int)maxWidth][(int)maxHeight];
			for(int width=0; width < maxWidth; width++) {
				for(int height=0; height < maxHeight; height++) {
					field[width][height] = 1;
				}
			}
			
			z = new Complex(0,0);
			mu = new Complex(0,0);
			int x, y;
			double LOG_MAX = 1;
			for(int width=0; width < maxWidth; width++) {
				for(int height=0; height < maxHeight; height++) {
					if(convergenceFunction.escaped(points[width][height][0])) {
						z.re = originX + width/maxWidth * widthScale*windowWidth - widthScale*windowWidth/2;
						z.im = originY + height/maxHeight * heightScale*windowWidth - heightScale*windowWidth/2;
						complexFunction.convert(z, mu);
						
						iteration = 0;
						while(!convergenceFunction.escaped(z) && iteration < maxIteration) {
							complexFunction.next(z, mu);
							iteration++;
							
							x = (int)Math.round((z.re - (originX - ((windowWidth * widthScale) / 2)))/(windowWidth * widthScale) * maxWidth);
							y = (int)Math.round((z.im - (originY - ((windowWidth * heightScale) / 2)))/(windowWidth * heightScale) * maxHeight);
							
							if(x >= 0 && x < maxWidth && y >= 0 && y < maxHeight) {
								if(iteration < (maxIteration / 3.0)) {
									field[x][y]++;
									if(field[x][y] > LOG_MAX) {
										LOG_MAX = field[x][y];
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
			for(int width=0; width < maxWidth; width++) {
				for(int height=0; height < maxHeight; height++) {
					logVal = Math.log(field[width][height]);
					logVal = logVal / M;
					
					red = green = blue = (int)Math.round(logVal);
					
					
					
					g.setColor(new Color(red, green, blue));
					g.drawLine(width,height,width,height);
				}
			}
		}
		
		/*if(complexFunction instanceof AbstractComplexFunction) {
			mu = ((AbstractComplexFunction)complexFunction).c;
			z.re = ((mu.re - (originX - (widthScale*windowWidth*0.5))) / (widthScale*windowWidth)) * maxWidth;
			z.im = ((mu.im - (originY - (heightScale*windowWidth*0.5))) / (heightScale*windowWidth)) * maxHeight;
			
			System.out.println(z + " <- " + mu);
			g.setColor(Color.GREEN);
			g.fillRect((int)z.re-1,(int)z.im-1,3,3);
		}*/
	}
			
	public String toString() {
		return complexFunction.toString();
	}
}

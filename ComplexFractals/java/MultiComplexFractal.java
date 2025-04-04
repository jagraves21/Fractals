import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class MultiComplexFractal extends ComplexFractal {
	protected ColorFunction color1;
	protected ColorFunction color2;
	protected ColorFunction color3;
	protected ColorFunction color4;

	public MultiComplexFractal(ComplexFunction complexFunction, ConvergenceFunction convergenceFunction) {
		this(complexFunction, convergenceFunction, PalletedColorFunction.getRainbow(10), PalletedColorFunction.getRedBlue(10), PalletedColorFunction.getBlues(10), new PalletedColorFunction());
	}

	public MultiComplexFractal(ComplexFunction complexFunction, ConvergenceFunction convergenceFunction, ColorFunction color1, ColorFunction color2, ColorFunction color3, ColorFunction color4) {
		this(complexFunction, convergenceFunction, color1, color2, color3, color4, FractalType.ITERATIVE);
	}

	public MultiComplexFractal(ComplexFunction complexFunction, ConvergenceFunction convergenceFunction, ColorFunction color1, ColorFunction color2, ColorFunction color3, ColorFunction color4, FractalStyle fractalStyle) {
		this(complexFunction, convergenceFunction, color1, color2, color3, color4, FractalType.ITERATIVE, fractalStyle);
	}

	public MultiComplexFractal(ComplexFunction complexFunction, ConvergenceFunction convergenceFunction, ColorFunction color1, ColorFunction color2, ColorFunction color3, ColorFunction color4, FractalType fractalType) {
		this(complexFunction, convergenceFunction, color1, color2, color3, color4, fractalType, FractalStyle.STANDARD);
	}

	public MultiComplexFractal(ComplexFunction complexFunction, ConvergenceFunction convergenceFunction, ColorFunction color1, ColorFunction color2, ColorFunction color3, ColorFunction color4, FractalType fractalType, FractalStyle fractalStyle) {
		this(0, 0, 3, complexFunction, convergenceFunction, color1, color2, color3, color4, fractalType, fractalStyle);
	}

	public MultiComplexFractal(double x, double y, double width, ComplexFunction complexFunction, ConvergenceFunction convergenceFunction, ColorFunction color1, ColorFunction color2, ColorFunction color3, ColorFunction color4) {
		this(x, y, width, complexFunction, convergenceFunction, color1, color2, color3, color4, FractalType.ITERATIVE, FractalStyle.STANDARD);
	}

	public MultiComplexFractal(double x, double y, double width, ComplexFunction complexFunction, ConvergenceFunction convergenceFunction, ColorFunction color1, ColorFunction color2, ColorFunction color3, ColorFunction color4, FractalType fractalType) {
		this(x, y, width, complexFunction, convergenceFunction, color1, color2, color3, color4, fractalType, FractalStyle.STANDARD);
	}

	public MultiComplexFractal(double x, double y, double width, ComplexFunction complexFunction, ConvergenceFunction convergenceFunction, ColorFunction color1, ColorFunction color2, ColorFunction color3, ColorFunction color4, FractalStyle fractalStyle) {
		this(x, y, width, complexFunction, convergenceFunction, color1, color2, color3, color4, FractalType.ITERATIVE, fractalStyle);
	}

	public MultiComplexFractal(double x, double y, double width, ComplexFunction complexFunction, ConvergenceFunction convergenceFunction, ColorFunction color1, ColorFunction color2, ColorFunction color3, ColorFunction color4, FractalType fractalType, FractalStyle fractalStyle) {
		super(x,y,width,complexFunction,convergenceFunction,color1,fractalType,fractalStyle);

		this.color1 = color1;
		this.color2 = color2;
		this.color3 = color3;
		this.color4 = color4;

		MouseListener[] mouseListeners = getMouseListeners();
		for(int ii=0; ii < mouseListeners.length; ii++) {
			removeMouseListener(mouseListeners[ii]);
		}

		MouseWheelListener[] wheelListeners = getMouseWheelListeners();
		for(int ii=0; ii < wheelListeners.length; ii++) {
			removeMouseWheelListener(wheelListeners[ii]);
		}

		MouseAdapter mouseAdapter = new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				System.out.println("HERE");
				double newMaxWidth = getWidth()/2; double newMaxHeight = getHeight()/2;
				int newX = e.getX() % (int)(getWidth()/2);
				int newY = e.getY() % (int)(getHeight()/2);

				originX = originX + (((double)newX)/newMaxWidth) * windowWidth - windowWidth/2;
				originY = originY + (((double)newY)/newMaxHeight) * windowWidth - windowWidth/2;

				synchronized (lock) {
					totalIterations = 0;
				}

				System.out.println("Center: " + originX + " " + originY);
			}

			public void mouseWheelMoved(MouseWheelEvent e) {
				int newX;
				int newY;
				double newMaxWidth;
				double newMaxHeight;
				double multiplier = (e.getWheelRotation() < 0) ? 0.95 : 1.05;

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

					newMaxWidth = getWidth()/2; newMaxHeight = getHeight()/2;

					newX = e.getX() % (int)(getWidth()/2);
					newY = e.getY() % (int)(getHeight()/2);

					double x0 = newX/(double)newMaxWidth * widthScale*windowWidth - widthScale*windowWidth/2;
					double y0 = newY/(double)newMaxHeight * heightScale*windowWidth - heightScale*windowWidth/2;

					originX += x0;
					originY += y0;

					windowWidth *= (multiplier*e.getScrollAmount());

					x0 = newX/(double)newMaxWidth * widthScale*windowWidth - widthScale*windowWidth/2;
					y0 = newY/(double)newMaxHeight * heightScale*windowWidth - heightScale*windowWidth/2;

					originX -= x0;
					originY -= y0;

					synchronized (lock) {
						totalIterations = 0;
					}
				}
			}
		};

		addMouseListener(mouseAdapter);
		addMouseWheelListener(mouseAdapter);
	}

	protected void allocateComplexMatrices(double originX, double originY, double windowWidth, double maxWidth, double maxHeight, double widthScale, double heightScale) {
		double newMaxWidth = maxWidth/2;
		double newMaxHeight = maxHeight/2;
		double newWidth, newHeight;

		if(points.length != maxWidth || (points.length > 0 && points[0].length != maxHeight)) {
			points = new Complex[(int)maxWidth][(int)maxHeight][2];
			pointIterations = new int[(int)maxWidth][(int)maxHeight];
		}

		for(int width=0; width < maxWidth; width++) {
			for(int height=0; height < maxHeight; height++) {
				points[width][height][0] = new Complex(0,0);
				points[width][height][1] = new Complex(0,0);

				newWidth = width % (int)(maxWidth/2);
				newHeight = height % (int)(maxHeight/2);
				points[width][height][0].re = originX + newWidth/newMaxWidth * widthScale*windowWidth - widthScale*windowWidth/2;
				points[width][height][0].im = originY + newHeight/newMaxHeight * heightScale*windowWidth - heightScale*windowWidth/2;

				complexFunction.convert(points[width][height][0], points[width][height][1]);

				pointIterations[width][height] = 0;
			}
		}
	}

	protected void initializeComplexMatrices(double originX, double originY, double windowWidth, double maxWidth, double maxHeight, double widthScale, double heightScale) {
		double newMaxWidth = maxWidth/2;
		double newMaxHeight = maxHeight/2;
		double newWidth, newHeight;

		for(int width=0; width < maxWidth; width++) {
			for(int height=0; height < maxHeight; height++) {
				newWidth = width % (int)(maxWidth/2);
				newHeight = height % (int)(maxHeight/2);
				points[width][height][0].re = originX + newWidth/newMaxWidth * widthScale*windowWidth - widthScale*windowWidth/2;
				points[width][height][0].im = originY + newHeight/newMaxHeight * heightScale*windowWidth - heightScale*windowWidth/2;

				complexFunction.convert(points[width][height][0], points[width][height][1]);

				pointIterations[width][height] = 0;
			}
		}
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
		double newMaxWidth = maxWidth/2;
		double newMaxHeight = maxHeight/2;
		ComplexFunction complexFunction = this.complexFunction;
		ConvergenceFunction convergenceFunction = this.convergenceFunction;
		ColorFunction colorFunction = this.colorFunction;
		FractalStyle fractalStyle = this.fractalStyle;

		synchronized (lock) {
			totalIterations = this.totalIterations++;
		}

		if(complexFunction == null || convergenceFunction == null || colorFunction == null) {
			return;
		}

		if(newMaxWidth < newMaxHeight) {
			widthScale = 1;
			heightScale = newMaxHeight/newMaxWidth;
		}
		else {
			widthScale = newMaxWidth/newMaxHeight;
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
			maxIteration = 100;

			if(totalIterations != 0) {
				initializeComplexMatrices(originX, originY, windowWidth, maxWidth, maxHeight, widthScale, heightScale);
				totalIterations = 0;
			}
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

				if(width < maxWidth/2) {
					if(height < maxHeight/2) {
						colorFunction = color1;
						convergenceFunction = this.convergenceFunction;
					}
					else {
						colorFunction = color2;
						convergenceFunction = this.convergenceFunction;
					}
				}
				else {
					if(height < maxHeight/2) {
						colorFunction = color3;
						convergenceFunction = this.convergenceFunction;
					}
					else {
						colorFunction = color4;
						convergenceFunction = new HalfPlaneColorFunction();
					}
				}

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

					if(width < maxWidth/2) {
						if(height < maxHeight/2) {
							colorFunction = color1;
							convergenceFunction = this.convergenceFunction;
						}
						else {
							colorFunction = color2;
							convergenceFunction = this.convergenceFunction;
						}
					}
					else {
						if(height < maxHeight/2) {
							colorFunction = color3;
							convergenceFunction = this.convergenceFunction;
						}
						else {
							colorFunction = color4;
							convergenceFunction = new HalfPlaneColorFunction();
						}
					}

					if(!convergenceFunction.escaped(z)) {
						g.setColor(colorFunction.getConvergentColor(z,pointIterations[width][height],maxIteration));
					}
					else {
						g.setColor(colorFunction.getDivergentColor(z,pointIterations[width][height],maxIteration));
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

						if(width < maxWidth/2) {
							if(height < maxHeight/2) {
								colorFunction = color1;
								convergenceFunction = this.convergenceFunction;
							}
							else {
								colorFunction = color2;
								convergenceFunction = this.convergenceFunction;
							}
						}
						else {
							if(height < maxHeight/2) {
								colorFunction = color3;
								convergenceFunction = this.convergenceFunction;
							}
							else {
								colorFunction = color4;
								convergenceFunction = new HalfPlaneColorFunction();
							}
						}

						if(!convergenceFunction.escaped(z)) {
							g.setColor(colorFunction.getConvergentColor(z,pointIterations[width][height],maxIteration));
						}
						else {
							g.setColor(colorFunction.getDivergentColor(z,pointIterations[width][height],maxIteration));
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
								field[x][y]++;
								if(field[x][y] > LOG_MAX) {
									LOG_MAX = field[x][y];
								}
							}
						}
					}
				}
			}

			LOG_MAX = Math.log(LOG_MAX);
			double M = (LOG_MAX) / 255.0;
			double logVal;
			for(int width=0; width < maxWidth; width++) {
				for(int height=0; height < maxHeight; height++) {
					logVal = Math.log(field[width][height]);
					logVal = logVal / M;

					x = (int)Math.round(logVal);

					g.setColor(new Color(x,x,x));
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
}
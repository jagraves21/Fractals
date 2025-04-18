import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseListener;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

import java.awt.image.BufferedImage;

public class MultiComplexFractal extends ComplexFractal {

	protected ColorFunction color1;
	protected ColorFunction color2;
	protected ColorFunction color3;
	protected ColorFunction color4;
	boolean allNull;

	public MultiComplexFractal(
		ComplexFunction complexFunction,
		ConvergenceFunction convergenceFunction
	) {
		this(
			complexFunction,
			convergenceFunction,
			PalletedColorFunction.getRainbow(10)
		);
	}
	
	public MultiComplexFractal(
		ComplexFunction complexFunction,
		ConvergenceFunction convergenceFunction,
		ColorFunction color1
	) {
		this(
			complexFunction,
			convergenceFunction,
			color1,
			PalletedColorFunction.getIceFire(10),
			PalletedColorFunction.getNeon(10),
			new PalletedColorFunction()
		);
	}

	public MultiComplexFractal(
		ComplexFunction complexFunction,
		ConvergenceFunction convergenceFunction,
		ColorFunction color1,
		ColorFunction color2,
		ColorFunction color3,
		ColorFunction color4
	) {
		this(
			complexFunction, 
			convergenceFunction,
			color1,
			color2,
			color3,
			color4,
			FractalType.ITERATIVE
		);
	}
	
	public MultiComplexFractal(
		ComplexFunction complexFunction,
		ConvergenceFunction convergenceFunction,
		ColorFunction color1,
		FractalStyle fractalStyle
	) {
		this(
			complexFunction,
			convergenceFunction,
			color1,
			PalletedColorFunction.getIceFire(10),
			PalletedColorFunction.getNeon(10),
			new PalletedColorFunction(),
			fractalStyle
		);
	}

	public MultiComplexFractal(
		ComplexFunction complexFunction,
		ConvergenceFunction convergenceFunction,
		ColorFunction color1,
		ColorFunction color2,
		ColorFunction color3,
		ColorFunction color4,
		FractalStyle fractalStyle
	) {
		this(
			complexFunction,
			convergenceFunction,
			color1,
			color2,
			color3,
			color4,
			FractalType.ITERATIVE,
			fractalStyle
		);
	}

	public MultiComplexFractal(
		ComplexFunction complexFunction,
		ConvergenceFunction convergenceFunction,
		ColorFunction color1,
		FractalType fractalType
	) {
		this(
			complexFunction,
			convergenceFunction,
			color1,
			PalletedColorFunction.getIceFire(10),
			PalletedColorFunction.getNeon(10),
			new PalletedColorFunction(),
			fractalType
		);
	}
	
	public MultiComplexFractal(
		ComplexFunction complexFunction,
		ConvergenceFunction convergenceFunction,
		ColorFunction color1,
		ColorFunction color2,
		ColorFunction color3,
		ColorFunction color4,
		FractalType fractalType
	) {
		this(
			complexFunction,
			convergenceFunction,
			color1,
			color2,
			color3,
			color4,
			fractalType,
			FractalStyle.STANDARD
		);
	}
	
	public MultiComplexFractal(
		ComplexFunction complexFunction,
		ConvergenceFunction convergenceFunction,
		ColorFunction color1,
		FractalType fractalType,
		FractalStyle fractalStyle
	) {
		this(
			complexFunction,
			convergenceFunction,
			color1,
			PalletedColorFunction.getIceFire(10),
			PalletedColorFunction.getNeon(10),
			new PalletedColorFunction(),
			fractalType,
			fractalStyle
		);
	}

	public MultiComplexFractal(
		ComplexFunction complexFunction,
		ConvergenceFunction convergenceFunction,
		ColorFunction color1,
		ColorFunction color2,
		ColorFunction color3,
		ColorFunction color4,
		FractalType fractalType,
		FractalStyle fractalStyle
	) {
		this(
			0,
			0,
			3,
			complexFunction,
			convergenceFunction,
			color1,
			color2,
			color3,
			color4,
			fractalType,
			fractalStyle
		);
	}

	public MultiComplexFractal(
		double x,
		double y,
		double width,
		ComplexFunction
		complexFunction,
		ConvergenceFunction
		convergenceFunction,
		ColorFunction color1
	) {
		this(
			x,
			y,
			width,
			complexFunction,
			convergenceFunction,
			color1,
			PalletedColorFunction.getIceFire(10),
			PalletedColorFunction.getNeon(10),
			new PalletedColorFunction()
		);
	}

	public MultiComplexFractal(
		double x,
		double y,
		double width,
		ComplexFunction
		complexFunction,
		ConvergenceFunction
		convergenceFunction,
		ColorFunction color1,
		ColorFunction color2,
		ColorFunction color3,
		ColorFunction color4
	) {
		this(
			x,
			y,
			width,
			complexFunction,
			convergenceFunction,
			color1,
			color2,
			color3,
			color4,
			FractalType.ITERATIVE,
			FractalStyle.STANDARD
		);
	}

	public MultiComplexFractal(
		double x,
		double y,
		double width,
		ComplexFunction complexFunction,
		ConvergenceFunction convergenceFunction,
		ColorFunction color1,
		FractalType fractalType
	) {
		this(
			x,
			y,
			width,
			complexFunction,
			convergenceFunction,
			color1,
			PalletedColorFunction.getIceFire(10),
			PalletedColorFunction.getNeon(10),
			new PalletedColorFunction(),
			fractalType
		);
	}
	
	public MultiComplexFractal(
		double x,
		double y,
		double width,
		ComplexFunction complexFunction,
		ConvergenceFunction convergenceFunction,
		ColorFunction color1,
		ColorFunction color2,
		ColorFunction color3,
		ColorFunction color4,
		FractalType fractalType
	) {
		this(
			x,
			y,
			width,
			complexFunction,
			convergenceFunction,
			color1,
			color2,
			color3,
			color4,
			fractalType,
			FractalStyle.STANDARD
		);
	}
	
	public MultiComplexFractal(
		double x,
		double y,
		double width,
		ComplexFunction complexFunction,
		ConvergenceFunction convergenceFunction,
		ColorFunction color1,
		FractalStyle fractalStyle
	) {
		this(
			x,
			y,
			width,
			complexFunction,
			convergenceFunction,
			color1,
			PalletedColorFunction.getIceFire(10),
			PalletedColorFunction.getNeon(10),
			new PalletedColorFunction(),
			fractalStyle
		);
	}

	public MultiComplexFractal(
		double x,
		double y,
		double width,
		ComplexFunction complexFunction,
		ConvergenceFunction convergenceFunction,
		ColorFunction color1,
		ColorFunction color2,
		ColorFunction color3,
		ColorFunction color4,
		FractalStyle fractalStyle
	) {
		this(
			x,
			y,
			width,
			complexFunction,
			convergenceFunction,
			color1,
			color2,
			color3,
			color4,
			FractalType.ITERATIVE,
			fractalStyle
		);
	}
	
	public MultiComplexFractal(
		double x,
		double y,
		double width,
		ComplexFunction complexFunction,
		ConvergenceFunction convergenceFunction,
		ColorFunction color1,
		FractalType fractalType,
		FractalStyle fractalStyle
	) {
		this(
			x,
			y,
			width,
			complexFunction,
			convergenceFunction,
			color1,
			PalletedColorFunction.getIceFire(10),
			PalletedColorFunction.getNeon(10),
			new PalletedColorFunction(),
			fractalType,
			fractalStyle
		);
	}

	public MultiComplexFractal(
		double x,
		double y,
		double width,
		ComplexFunction complexFunction,
		ConvergenceFunction convergenceFunction,
		ColorFunction color1,
		ColorFunction color2,
		ColorFunction color3,
		ColorFunction color4,
		FractalType fractalType,
		FractalStyle fractalStyle
	) {
		this(
			x,
			y,
			width,
			complexFunction,
			convergenceFunction,
			color1,
			color2,
			color3,
			color4,
			fractalType,
			fractalStyle,
			false
		);
	}
	
	public MultiComplexFractal(
		double x,
		double y,
		double width,
		ComplexFunction complexFunction,
		ConvergenceFunction convergenceFunction,
		ColorFunction color1,
		FractalType fractalType,
		FractalStyle fractalStyle,
		boolean cycleColors
	) {
		this(
			x,
			y,
			width,
			complexFunction,
			convergenceFunction,
			color1,
			PalletedColorFunction.getIceFire(10),
			PalletedColorFunction.getNeon(10),
			new PalletedColorFunction(),
			fractalType,
			fractalStyle,
			cycleColors
		);
	}
	
	public MultiComplexFractal(
		double x,
		double y,
		double width,
		ComplexFunction complexFunction,
		ConvergenceFunction convergenceFunction,
		ColorFunction color1,
		ColorFunction color2,
		ColorFunction color3,
		ColorFunction color4,
		FractalType fractalType,
		FractalStyle fractalStyle,
		boolean cycleColors
	) {
		super(
			x,
			y,
			width,
			complexFunction,
			convergenceFunction,
			color1,
			fractalType,
			fractalStyle,
			cycleColors
		);

		allNull = color2 == null && color3 == null && color4 == null;
		boolean noneNull = color2 != null && color3 != null && color4 != null;
		if (!(allNull || noneNull)) {
			throw new IllegalArgumentException("Either all colors must be null, or none of them.");
		}
		this.color1 = color1;
		this.color2 = color2;
		this.color3 = color3;
		this.color4 = color4;

		if(noneNull) {
			addNewMouseListeners();
		}
	}

	protected void addNewMouseListeners() {
		for (MouseListener listener : getMouseListeners()) {
			removeMouseListener(listener);
		}
			
		addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				double localWidth = getWidth()/2.0;
				double localHeight = getHeight()/2.0;

				int localX = e.getX() % (int)localWidth;
				int localY = e.getY() % (int)localHeight;

				double widthScale = (localWidth < localHeight) ? 1.0 : localWidth / (double) localHeight;
				double heightScale = (localWidth < localHeight) ? localHeight / (double) localWidth : 1.0;

				double mouseFracX = (localX/localWidth) * widthScale * viewWidth - (widthScale*viewWidth/2);
				double mouseFracY = (localY/localHeight) * heightScale * viewWidth - (heightScale*viewWidth/2);

				double newOriginX = originX + mouseFracX;
				double newOriginY = originY + mouseFracY;
				
				setWindow(newOriginX, newOriginY, viewWidth);
			}
		});

		addMouseWheelListener(new MouseAdapter() {
			public void mouseWheelMoved(MouseWheelEvent e) {
				double rotation = e.getPreciseWheelRotation();
				double scaleBase = (rotation < 0) ? 0.95 : 1.05;
				double zoomFactor = Math.pow(scaleBase, Math.abs(rotation)) * e.getScrollAmount();

				double localWidth = getWidth()/2.0;
				double localHeight = getHeight()/2.0;

				int localX = e.getX() % (int)localWidth;
				int localY = e.getY() % (int)localHeight;

				double widthScale = (localWidth < localHeight) ? 1.0 : localWidth / (double) localHeight;
				double heightScale = (localWidth < localHeight) ? localHeight / (double) localWidth : 1.0;

				double mouseFracX = (localX/localWidth) * widthScale * viewWidth - (widthScale*viewWidth/2);
				double mouseFracY = (localY/localHeight) * heightScale * viewWidth - (heightScale*viewWidth/2);

				double newOriginX = originX + mouseFracX;
				double newOriginY = originY + mouseFracY;

				double newViewWidth = viewWidth * zoomFactor;

				mouseFracX = (localX/localWidth) * widthScale * newViewWidth - (widthScale*newViewWidth/2);
				mouseFracY = (localY/localHeight) * heightScale * newViewWidth - (heightScale*newViewWidth/2);

				newOriginX -= mouseFracX;
				newOriginY -= mouseFracY;

				setWindow(newOriginX, newOriginY, newViewWidth);
			}
		});
	}

	public void draw(Graphics g, int gWidth, int gHeight) {
		if(allNull) {
			super.draw(g, gWidth, gHeight);
			return;
		}

		step(gWidth/2, gHeight/2);

		BufferedImage img = new BufferedImage(gWidth, gHeight, BufferedImage.TYPE_INT_RGB);
		Graphics imageGraphics = img.createGraphics();
	
		colorFunction = color1;	
		drawFractal(imageGraphics, gWidth/2, gHeight/2);
		g.drawImage(img, 0, 0, null);
		
		colorFunction = color2;	
		drawFractal(imageGraphics, gWidth/2, gHeight/2);
		g.drawImage(img, gWidth/2, 0, null);
		
		colorFunction = color3;	
		drawFractal(imageGraphics, gWidth/2, gHeight/2);
		g.drawImage(img, 0, gHeight/2, null);
		
		colorFunction = color4;	
		drawFractal(imageGraphics, gWidth/2, gHeight/2);
		g.drawImage(img, gWidth/2, gHeight/2, null);
		
		imageGraphics.dispose();
		
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

	public String toString() {
		return complexFunction.toString();
	}
}

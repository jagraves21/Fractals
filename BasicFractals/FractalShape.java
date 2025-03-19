import java.awt.Graphics;
import java.awt.Graphics2D;

public abstract class FractalShape
{
	public abstract void checkMinMax(MyPoint minPoint, MyPoint maxPoint);
	
	public abstract FractalShape translate(MyPoint p);
	
	public abstract FractalShape scale(double xFactor, double yFactor);
	public abstract FractalShape scale(double xFactor, double yFactor, MyPoint p);
	
	public abstract FractalShape rotate(double thetaOff);
	public abstract FractalShape rotate(double thetaOff, MyPoint p);
	
	//public abstract FractalShape scale(MyPoint oldMinPoint, double oldXRange, double oldYRange, MyPoint newMinPoint, double newXRange, double newYRange);
	public abstract void paint(Graphics2D g);
}
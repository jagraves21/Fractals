import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import java.util.function.Function;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

public abstract class AbstractComplexFunction implements ComplexFunction {
	protected double a;
	protected double b;
	protected double theta;
	protected double thetaOff;

	public Complex c;
	protected Random random;

	public AbstractComplexFunction() {
		c = new Complex(0,0);
		random = new Random(0);
	}

	public double getOriginX() {
		return 0;
	}
	public double getOriginY() {
		return 0;
	}
	public double getWindowWidth() {
		return 3;
	}

	public void random() {
		c.re = random.nextDouble() * 2 - 1;
		c.im = random.nextDouble() * 2 - 1;
	}

	public  void init() {
		a = 0;
		b = 0.01;
		theta = 0;
		thetaOff = 0.1;
		c.re = 0; c.im = 0;
		random.setSeed(0);
	}

	public void move() {
		double r = a + b*theta;
		c.re = r * Math.cos(theta);
		c.im = r * Math.sin(theta);
		if(r > 1.5) {
			theta = 0;
		}
		theta += thetaOff;//1/(r+1) * 0.3;
	}

	public abstract void convert(Complex z, Complex mu);

	public abstract void next(Complex z, Complex mu);

	public ConvergenceFunction getSuggestedConvergenceFunction() {
		return new ModulusTwo();
	}

	public ColorFunction getSuggestedColorFunction() {
		//return new PalletedColorFunction();
		return PalletedColorFunction.getRainbow(10);
	}

	public ComplexFractal.FractalType getSuggestedFractalType() {
		return ComplexFractal.FractalType.ITERATIVE;
	}

	public ComplexFractal.FractalStyle getSuggestedFractalStyle() {
		return ComplexFractal.FractalStyle.STANDARD;
	}

	public static void main(String[] args)
	{
		try
		{
			String callerClassName = Thread.currentThread().getStackTrace()[2].getClassName();
			Class<?> callerClass = Class.forName(callerClassName);
			Object instance = callerClass.getConstructor().newInstance();
			AbstractComplexFunction complexFunction = (AbstractComplexFunction) instance;

			ArgumentParser argumentParser = new ArgumentParser(
				callerClassName,
				800,
				600,
				complexFunction.getSuggestedConvergenceFunction(),
				complexFunction.getSuggestedColorFunction(),
				complexFunction.getSuggestedFractalType(),
				complexFunction.getSuggestedFractalStyle(),
				false
			);
			argumentParser.parseArguments(args);
			AnimatedPanel animatedPanel = new ComplexFractal(
				complexFunction.getOriginX(),
				complexFunction.getOriginY(),
				complexFunction.getWindowWidth(),
				complexFunction,
				argumentParser.convergenceFunction,
				argumentParser.colorFunction,
				argumentParser.fractalType,
				argumentParser.fractalStyle,
				argumentParser.colorsCycle
			);
			AnimatedPanel.display(
				animatedPanel,
				argumentParser.width,
				argumentParser.height
			);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}

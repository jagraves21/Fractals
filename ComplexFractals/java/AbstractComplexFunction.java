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
	
	public ConvergenceFunction getConvergenceFunction() {
		return new ModulusTwo();
	}
	
	public ColorFunction getColorFunction() {
		//return new PalletedColorFunction();
		return PalletedColorFunction.getRainbow(10);
	}

	public ComplexFractal.FractalType getFractalType() {
		return ComplexFractal.FractalType.ITERATIVE;
	}
	
	public ComplexFractal.FractalStyle getFractalStyle() {
		return ComplexFractal.FractalStyle.STANDARD;
	}

	public ColorFunction parseColorArgument(String argument) throws IllegalArgumentException {
		Map<String, Function<Integer, ColorFunction>> palletedColorMap = new HashMap<>();
		palletedColorMap.put("redgreenblue", PalletedColorFunction::getRedGreenBlue);
		palletedColorMap.put("blackredyellow", PalletedColorFunction::getBlackRedYellow);
		palletedColorMap.put("redblue", PalletedColorFunction::getRedBlue);
		palletedColorMap.put("whitered", PalletedColorFunction::getWhiteRed);
		palletedColorMap.put("blackwhite", PalletedColorFunction::getBlackWhite);
		palletedColorMap.put("rainbow", PalletedColorFunction::getRainbow);
		palletedColorMap.put("blue", PalletedColorFunction::getBlues);
		
		Map<String, Function<Integer, ColorFunction>> smoothColorMap = new HashMap<>();
		smoothColorMap.put("blackredyellow", SmoothColorFunction::getBlackRedYellow);
		smoothColorMap.put("blackwhite", SmoothColorFunction::getBlackWhite);
		smoothColorMap.put("blue", SmoothColorFunction::getBlues);
		smoothColorMap.put("rainbow", SmoothColorFunction::getRainbow);
		smoothColorMap.put("redblue", SmoothColorFunction::getRedBlue);
		smoothColorMap.put("redgreenblue", SmoothColorFunction::getRedGreenBlue);
		smoothColorMap.put("whitered", SmoothColorFunction::getWhiteRed);

		Pattern pattern = Pattern.compile("^(blackredyellow|blackwhite|blue|rainbow|redblue|redgreenblue|whitered)(\\d+)?(s)?$");
		Matcher matcher = pattern.matcher(argument);

		if(!matcher.matches()) {
			throw new IllegalArgumentException(
					"Invalid color function. Must be of the form <color>[n][s] where color is one of ['blackredyellow', 'blackwhite', 'rainbow', 'redblue', 'redgreenblue', 'whitered']."
			);
		}

		String color = matcher.group(1);
		int count = matcher.group(2) != null ? Integer.parseInt(matcher.group(2)) : 10;
		boolean smooth = matcher.group(3) != null;

		if (count <= 0) {
			throw new IllegalArgumentException("Invalid value for n, must be a positive integer.");
		}

		Function<Integer, ColorFunction> function = smooth ? smoothColorMap.get(color) : palletedColorMap.get(color);
		if (function == null) {
			throw new IllegalArgumentException("Internal error: No function found for color palette: " + color);
		}

		return function.apply(count);
	}	
	
	public void printHelp(String callerClassName)
	{
		System.out.println("Usage: java " + callerClassName + " [options]");
		System.out.println("Options:");
		System.out.println("  -w, --width <width>                    Set the width of the fractal (default: 800)");
		System.out.println("  -h, --height <height>                  Set the height of the fractal (default: 600)");
		System.out.println("  -f, --convergence-function <function>  Set the convergence function");
		System.out.println("  -c, --color <color>[n][s]?             Set the color palette for the fractal");
		System.out.println("                                         Format: <color>[n][s]");
		System.out.println("                                         [n] is an optional number for color count");
		System.out.println("                                         [s] is an optional 's' to enable smooth colors");
		System.out.println("  -t, --fractal-type <type>              Set the fractal type");
		System.out.println("  -s, --fractal-style <style>            Set the fractal style");
		System.out.println("  --colors-cycle                         Enable color cycling (optional)");
		System.out.println("  --colors-cycle                         Enable color cycling (optional)");
		System.out.println("  --help                                 Show this help message");
		System.out.println();
		System.out.println("convergence functions: ['HalfPlaneColorFunction', 'ModulusFifty', 'ModulusFiftyMod', 'ModulusFour', 'ModulusSquare', 'ModulusTwo', 'ModulusTwoMod', 'ModulusTwoPI']");
		System.out.println();
		System.out.println("color functions: ['blackredyellow', 'blackwhite', 'rainbow', 'redblue', 'redgreenblue', 'whitered']");
		System.out.println();
		System.out.println("fractal types: ['iterative', 'moving', 'random']");
		System.out.println();
		System.out.println("fractal styles: ['standard', 'contoured', 'frequency']");
	}
	
	public void displayFractal(String callerClassName, String[] args)
	{
		int width = 800;
		int height = 600;

		ConvergenceFunction convergenceFunction = getConvergenceFunction();
		ColorFunction colorFunction = getColorFunction();
		ComplexFractal.FractalType fractalType = getFractalType();
		ComplexFractal.FractalStyle fractalStyle = getFractalStyle();
		boolean colorsCycle = false;
		
		for (String arg : args)
		{
			if (arg.equals("--help"))
			{
				printHelp(callerClassName);
				return;
			}
		}

		for (int ii = 0; ii < args.length; ii++)
		{
			switch (args[ii])
			{
				case "-w":
				case "--width":
					if (ii + 1 < args.length)
					{
						try
						{
							width = Integer.parseInt(args[ii + 1]);
							ii++;
						}
						catch (NumberFormatException e)
						{
							System.out.println("Invalid width value. Must be an integer.");
							System.out.println();
							printHelp(callerClassName);
							System.exit(-1);
						}
					}
					else
					{
						System.out.println("No value provided for width.");
						System.out.println();
						printHelp(callerClassName);
						System.exit(-1);
					}
					break;
				case "-h":
				case "--height":
					if (ii + 1 < args.length)
					{
						try
						{
							height = Integer.parseInt(args[ii + 1]);
							ii++;
						}
						catch (NumberFormatException e)
						{
							System.out.println("Invalid height value. Must be an integer.");
							System.out.println();
							printHelp(callerClassName);
							System.exit(-1);
						}
					}
					else
					{
						System.out.println("No value provided for height.");
						System.out.println();
						printHelp(callerClassName);
						System.exit(-1);
					}
					break;
				case "-f":
				case "--convergence-function":
					if (ii + 1 < args.length)
					{
						if(args[ii + 1].equals("HalfPlaneColorFunction"))
						{
							convergenceFunction = new HalfPlaneColorFunction();
							ii++;
						}
						else if(args[ii + 1].equals("ModulusFifty"))
						{
							convergenceFunction = new ModulusFifty();
							ii++;
						}
						else if(args[ii + 1].equals("ModulusFiftyMod"))
						{
							convergenceFunction = new ModulusFiftyMod();
							ii++;
						}
						else if(args[ii + 1].equals("ModulusFour"))
						{
							convergenceFunction = new ModulusFour();
							ii++;
						}
						else if(args[ii + 1].equals("ModulusSquare"))
						{
							convergenceFunction = new ModulusSquare();
							ii++;
						}
						else if(args[ii + 1].equals("ModulusTwo"))
						{
							convergenceFunction = new ModulusTwo();
							ii++;
						}
						else if(args[ii + 1].equals("ModulusTwoMod"))
						{
							convergenceFunction = new ModulusTwoMod();
							ii++;
						}
						else if(args[ii + 1].equals("ModulusTwoPI"))
						{
							convergenceFunction = new ModulusTwoPI();
							ii++;
						}
						else
						{
							System.out.println("Invalid convergence function. Must be one of ['HalfPlaneColorFunction', 'ModulusFifty', 'ModulusFiftyMod', 'ModulusFour', 'ModulusSquare', 'ModulusTwo', 'ModulusTwoMod', 'ModulusTwoPI'].");
							System.out.println();
							printHelp(callerClassName);
							System.exit(-1);
						}
					}
					else
					{
						System.out.println("No value provided for convergence function.");
						System.out.println();
						printHelp(callerClassName);
						System.exit(-1);
					}
					break;
				case "-c":
				case "--color":
					if (ii + 1 < args.length)
					{
						try {
							colorFunction = parseColorArgument(args[ii + 1]);
							ii++;
						} catch (IllegalArgumentException iae) {
							System.out.println(iae.getMessage());
							System.out.println();
							printHelp(callerClassName);
							System.exit(-1);
						}
					}
					else
					{
						System.out.println("No value provided for fractal type.");
						System.out.println();
						printHelp(callerClassName);
						System.exit(-1);
					}
					break;
				case "-t":
				case "--fractal-type":
					if (ii + 1 < args.length)
					{
						if(args[ii + 1].equals("iterative"))
						{
							fractalType = ComplexFractal.FractalType.ITERATIVE;
							ii++;
						}
						else if(args[ii + 1].equals("moving"))
						{
							fractalType = ComplexFractal.FractalType.MOVING;
							ii++;
						}
						else if(args[ii + 1].equals("random"))
						{
							fractalType = ComplexFractal.FractalType.RANDOM;
							ii++;
						}
						else
						{
							System.out.println("Invalid fractal type. Must be one of ['iterative', 'moving', 'random'].");
							System.out.println();
							printHelp(callerClassName);
							System.exit(-1);
						}
					}
					else
					{
						System.out.println("No value provided for fractal type.");
						System.out.println();
						printHelp(callerClassName);
						System.exit(-1);
					}
					break;
				case "-s":
				case "--fractal-style":
					if (ii + 1 < args.length)
					{
						if(args[ii + 1].equals("standard"))
						{
							fractalStyle = ComplexFractal.FractalStyle.STANDARD;
							ii++;
						}
						else if(args[ii + 1].equals("contoured"))
						{
							fractalStyle = ComplexFractal.FractalStyle.CONTOURED;
							ii++;
						}
						else if(args[ii + 1].equals("frequency"))
						{
							fractalStyle = ComplexFractal.FractalStyle.FREQUENCY;
							ii++;
						}
						else
						{
							System.out.println("Invalid fractal style. Must be one if ['standard', 'contoured', 'frequency'].");
							System.out.println();
							printHelp(callerClassName);
							System.exit(-1);
						}
					}
					else
					{
						System.out.println("No value provided for fractal type.");
						System.out.println();
						printHelp(callerClassName);
						System.exit(-1);
					}
					break;
				case "--colors-cycle":
					colorsCycle = true;
					break;
				default:
					System.out.println("Unknown argument: " + args[ii]);
					System.out.println();
					printHelp(callerClassName);
					System.exit(-1);
			}
		}

		displayFractal(
			width,
			height,
			convergenceFunction,
			colorFunction,
			fractalType,
			fractalStyle,
			colorsCycle
		);
	}
	
	public void displayFractal(int width, int height, ConvergenceFunction convergenceFunction, ColorFunction colorFunction, ComplexFractal.FractalType fractalType, ComplexFractal.FractalStyle fractalStyle, boolean colorsCycle) {

		AnimatedPanel fractal = new ComplexFractal(
			getOriginX(),
			getOriginY(),
			getWindowWidth(),
			this,
			convergenceFunction,
			colorFunction,
			fractalType,
			fractalStyle,
			colorsCycle
		);
		AnimatedPanel.display(fractal, width, height);
	}


	public static void main(String[] args)
	{
		try
		{
			String callerClassName = Thread.currentThread().getStackTrace()[2].getClassName();
			Class<?> callerClass = Class.forName(callerClassName);
			Object instance = callerClass.getConstructor().newInstance();
			AbstractComplexFunction complexFunction = (AbstractComplexFunction) instance;
			complexFunction.displayFractal(callerClassName, args);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}

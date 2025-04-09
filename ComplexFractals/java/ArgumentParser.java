import java.util.TreeMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import java.util.function.Function;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class ArgumentParser {
	public String callerName;

	public int width;
	public int height;
	public ConvergenceFunction convergenceFunction;
	public ColorFunction colorFunction;
	public ComplexFractal.FractalType fractalType;
	public ComplexFractal.FractalStyle fractalStyle;
	public boolean cycleColors;
	public int iterations;
	public boolean reflect;
	public double xZoom;
	public double yZoom;
	public double multiplier;
	public int startZoom;
	public int stopZoom;

	public List<String> classes;
	
	Set<String> colorNames;	
	Map<String, Function<Integer, ColorFunction>> palletedColorMap;
	Map<String, Function<Integer, ColorFunction>> smoothColorMap;

	public ArgumentParser(
		String callerName,
		int width,
		int height,
		ConvergenceFunction convergenceFunction,
		ColorFunction colorFunction,
		ComplexFractal.FractalType fractalType,
		ComplexFractal.FractalStyle fractalStyle,
		boolean cycleColors
	) {
		this(
			callerName,
			width,
			height,
			convergenceFunction,
			colorFunction,
			fractalType,
			fractalStyle,
			cycleColors,
			200,
			false,
			0,
			0,
			1.0,
			0,
			Integer.MAX_VALUE
		);
	}

	public ArgumentParser(
		String callerName,
		int width,
		int height,
		ConvergenceFunction convergenceFunction,
		ColorFunction colorFunction,
		ComplexFractal.FractalType fractalType,
		ComplexFractal.FractalStyle fractalStyle,
		boolean cycleColors,
		int iterations,
		boolean reflect,
		double xZoom,
		double yZoom,
		double multiplier,
		int startZoom,
		int stopZoom
	) {
		this.callerName = callerName;
		this.width = width;
		this.height = height;
		this.convergenceFunction = convergenceFunction;
		this.colorFunction = colorFunction;
		this.fractalType = fractalType;
		this.fractalStyle = fractalStyle;
		this.cycleColors = cycleColors;
		this.iterations = iterations;
		this.reflect = reflect;
		this.xZoom = xZoom;
		this.yZoom = yZoom;
		this.multiplier = multiplier;
		this.startZoom = startZoom;
		this.stopZoom = stopZoom;
		this.classes = new LinkedList<String>();
		
		palletedColorMap = new TreeMap<>();
		palletedColorMap.put("rgb", PalletedColorFunction::getRGB);
		palletedColorMap.put("rainbow", PalletedColorFunction::getRainbow);
		palletedColorMap.put("spectrum", PalletedColorFunction::getSpectrum);
		palletedColorMap.put("forest", PalletedColorFunction::getForest);
		palletedColorMap.put("seaweed", PalletedColorFunction::getSeaweed);
		palletedColorMap.put("ocean", PalletedColorFunction::getOcean);
		palletedColorMap.put("aqua", PalletedColorFunction::getAqua);
		palletedColorMap.put("sunset", PalletedColorFunction::getSunset);
		palletedColorMap.put("tropical", PalletedColorFunction::getTropical);
		palletedColorMap.put("neon", PalletedColorFunction::getNeon);
		palletedColorMap.put("space", PalletedColorFunction::getSpace);
		palletedColorMap.put("ice-fire", PalletedColorFunction::getIceFire);
		palletedColorMap.put("zebra", PalletedColorFunction::getZebra);
		palletedColorMap.put("candy-cane", PalletedColorFunction::getCandyCane);
		palletedColorMap.put("magma", PalletedColorFunction::getMagma);
		palletedColorMap.put("lava", PalletedColorFunction::getLava);
		palletedColorMap.put("pink-blues", PalletedColorFunction::getPinkBlues);

		smoothColorMap = new TreeMap<>();
		smoothColorMap.put("rgb", SmoothColorFunction::getRGB);
		smoothColorMap.put("rainbow", SmoothColorFunction::getRainbow);
		smoothColorMap.put("spectrum", SmoothColorFunction::getSpectrum);
		smoothColorMap.put("forest", SmoothColorFunction::getForest);
		smoothColorMap.put("seaweed", SmoothColorFunction::getSeaweed);
		smoothColorMap.put("ocean", SmoothColorFunction::getOcean);
		smoothColorMap.put("aqua", SmoothColorFunction::getAqua);
		smoothColorMap.put("sunset", SmoothColorFunction::getSunset);
		smoothColorMap.put("tropical", SmoothColorFunction::getTropical);
		smoothColorMap.put("neon", SmoothColorFunction::getNeon);
		smoothColorMap.put("space", SmoothColorFunction::getSpace);
		smoothColorMap.put("ice-fire", SmoothColorFunction::getIceFire);
		smoothColorMap.put("zebra", SmoothColorFunction::getZebra);
		smoothColorMap.put("candy-cane", SmoothColorFunction::getCandyCane);
		smoothColorMap.put("magma", SmoothColorFunction::getMagma);
		smoothColorMap.put("lava", SmoothColorFunction::getLava);
		smoothColorMap.put("pink-blues", SmoothColorFunction::getPinkBlues);
        
		colorNames = palletedColorMap.keySet();
	}

	public String getColorChoices() {
		StringBuilder stringBuilder = new StringBuilder();
		for (String key : colorNames) {
            if (stringBuilder.length() > 0) {
                stringBuilder.append(", ");
            }
			stringBuilder.append("'");
            stringBuilder.append(key);
			stringBuilder.append("'");
        }
		if (stringBuilder.length() != 0) {
			stringBuilder.append(", ");
		}
		stringBuilder.append("'cyclic-colors', 'cosine-colors'");
		return "[" + stringBuilder.toString() + "]";	
	}

	public void printHelp(boolean withClasses, boolean withZoom) {
		if (withClasses) {
			System.out.println("Usage: java " + callerName + " [options]");
		} else {
			System.out.println("Usage: java " + callerName + " [options] <class files>");
		}
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
		System.out.println("  --cycle-colors                         Enable color cycling (optional)");
		if (withZoom) {
			System.out.println("  -i, --iterations                       Number of ierations for animation (default: 200)");
			System.out.println("  -r, --reflect                          Reflect GIF animation (default: no reflection)");
			System.out.println("  -x, --x-zoom                           x location to zoom (default: 0)");
			System.out.println("  -y, --y-zoom                           y location to zoom (default: 0)");
			System.out.println("  -m, --multiplier                       Zooming multiplier (default: 1.0)");
			System.out.println("  --start-zoom                           Iteration to start zooming (default: 0)");
			System.out.println("  --stop-zoom                            Iteration to start zooming (default: max int)");
		}
		System.out.println("  --help                                 Show this help message");
		System.out.println();
		System.out.println("convergence functions: ['ModulusTwoConvergence', 'ModulusFourConvergence', 'ModulusTwoPIConvergence', 'ModulusFiftyConvergence', 'RealTwoConvergence', 'RealAbsTwoConvergence', 'ImAbsFiftyConvergence', 'ModulusSquareConvergence']");
		System.out.println();
		System.out.println("color functions: " + getColorChoices());
		System.out.println();
		System.out.println("fractal types: ['iterative', 'moving', 'random']");
		System.out.println();
		System.out.println("fractal styles: ['standard', 'contoured', 'frequency']");
	}

	public void parseArguments(String[] args) {
		this.parseArguments(args, false, false);
	}	
	
	public void parseArguments(String[] args, boolean withClasses, boolean withZoom) {
		for (String arg : args) {
			if (arg.equals("--help")) {
				printHelp(withClasses, withZoom);
				return;
			}
		}

		for (int ii = 0; ii < args.length; ii++) {
			if (withClasses && args[ii].endsWith(".class")) {
				String className = args[ii].substring(0, args[ii].lastIndexOf(".class"));
				classes.add(className);
			} else {
				switch (args[ii]) {
					case "-w":
					case "--width":
						if (ii + 1 < args.length) {
							try {
								width = Integer.parseInt(args[ii + 1]);
								ii++;
							} catch (NumberFormatException e) {
								System.out.println("Invalid width value. Must be an integer.");
								System.out.println();
								printHelp(withClasses, withZoom);
								System.exit(-1);
							}
						} else {
							System.out.println("No value provided for width.");
							System.out.println();
							printHelp(withClasses, withZoom);
							System.exit(-1);
						}
						break;
					case "-h":
					case "--height":
						if (ii + 1 < args.length) {
							try {
								height = Integer.parseInt(args[ii + 1]);
								ii++;
							} catch (NumberFormatException e) {
								System.out.println("Invalid height value. Must be an integer.");
								System.out.println();
								printHelp(withClasses, withZoom);
								System.exit(-1);
							}
						}
						else {
							System.out.println("No value provided for height.");
							System.out.println();
							printHelp(withClasses, withZoom);
							System.exit(-1);
						}
						break;
					case "-f":
					case "--convergence-function":
						if (ii + 1 < args.length) {
		System.out.println("convergence functions: ['ModulusTwoConvergence', 'ModulusFourConvergence', 'ModulusTwoPIConvergence', 'ModulusFiftyConvergence', 'RealTwoConvergence', 'RealAbsTwoConvergence', 'ImAbsFiftyConvergence', 'ModulusSquareConvergence']");
							if(args[ii + 1].equals("ModulusTwoConvergence")) {
								convergenceFunction = new ModulusTwoConvergence();
								ii++;
							} else if(args[ii + 1].equals("ModulusFourConvergence")) {
								convergenceFunction = new ModulusFourConvergence();
								ii++;
							} else if(args[ii + 1].equals("ModulusTwoPIConvergence")) {
								convergenceFunction = new ModulusTwoPIConvergence();
								ii++;
							} else if(args[ii + 1].equals("ModulusFiftyConvergence")) {
								convergenceFunction = new ModulusFiftyConvergence();
								ii++;
							} else if(args[ii + 1].equals("RealTwoConvergence")) {
								convergenceFunction = new RealTwoConvergence();
								ii++;
							} else if(args[ii + 1].equals("RealAbsTwoConvergence")) {
								convergenceFunction = new RealAbsTwoConvergence();
								ii++;
							} else if(args[ii + 1].equals("ImAbsFiftyConvergence")) {
								convergenceFunction = new ImAbsFiftyConvergence();
								ii++;
							} else if(args[ii + 1].equals("ModulusSquareConvergence")) {
								convergenceFunction = new ModulusSquareConvergence();
								ii++;
							} else {
								System.out.println("Invalid convergence function. Must be one of ['RealTwo', 'ModulusFifty', 'ImAbsFifty', 'ModulusFour', 'ModulusSquare', 'ModulusTwo', 'RealAbsTwo', 'ModulusTwoPI'].");
								System.out.println();
								printHelp(withClasses, withZoom);
								System.exit(-1);
							}
						} else {
							System.out.println("No value provided for convergence function.");
							System.out.println();
							printHelp(withClasses, withZoom);
							System.exit(-1);
						}
						break;
					case "-c":
					case "--color":
						if (ii + 1 < args.length) {
							try {
								colorFunction = parseColorArgument(args[ii + 1]);
								ii++;
							} catch (IllegalArgumentException iae) {
								System.out.println(iae.getMessage());
								System.out.println();
								printHelp(withClasses, withZoom);
								System.exit(-1);
							}
						} else {
							System.out.println("No value provided for fractal type.");
							System.out.println();
							printHelp(withClasses, withZoom);
							System.exit(-1);
						}
						break;
					case "-t":
					case "--fractal-type":
						if (ii + 1 < args.length) {
							if(args[ii + 1].equals("iterative")) {
								fractalType = ComplexFractal.FractalType.ITERATIVE;
								ii++;
							} else if(args[ii + 1].equals("moving")) {
								fractalType = ComplexFractal.FractalType.MOVING;
								ii++;
							} else if(args[ii + 1].equals("random")) {
								fractalType = ComplexFractal.FractalType.RANDOM;
								ii++;
							} else {
								System.out.println("Invalid fractal type. Must be one of ['iterative', 'moving', 'random'].");
								System.out.println();
								printHelp(withClasses, withZoom);
								System.exit(-1);
							}
						} else {
							System.out.println("No value provided for fractal type.");
							System.out.println();
							printHelp(withClasses, withZoom);
							System.exit(-1);
						}
						break;
					case "-s":
					case "--fractal-style":
						if (ii + 1 < args.length) {
							if(args[ii + 1].equals("standard")) {
								fractalStyle = ComplexFractal.FractalStyle.STANDARD;
								ii++;
							} else if(args[ii + 1].equals("contoured")) {
								fractalStyle = ComplexFractal.FractalStyle.CONTOURED;
								ii++;
							} else if(args[ii + 1].equals("frequency")) {
								fractalStyle = ComplexFractal.FractalStyle.FREQUENCY;
								ii++;
							} else {
								System.out.println("Invalid fractal style. Must be one if ['standard', 'contoured', 'frequency'].");
								System.out.println();
								printHelp(withClasses, withZoom);
								System.exit(-1);
							}
						} else {
							System.out.println("No value provided for fractal type.");
							System.out.println();
							printHelp(withClasses, withZoom);
							System.exit(-1);
						}
						break;
					case "--cycle-colors":
						cycleColors = true;
						break;
					case "-i": case "--iterations":
					case "-r": case "--reflect":
					case "-x": case "--x-zoom":
					case "-y": case "--y-zoom":
					case "-m": case "--multiplier":
					case "--start-zoom":
					case "--stop-zoom":
						if (withZoom) {
							switch (args[ii]) {
								case "-i": case "--iterations":
									if (ii + 1 < args.length) {
										try {
											iterations = Integer.parseInt(args[ii + 1]);
											ii++;
										} catch (NumberFormatException e) {
											System.out.println("Invalid iterations value. Must be an integer.");
											System.out.println();
											printHelp(withClasses, withZoom);
											System.exit(-1);
										}
									} else {
										System.out.println("No value provided for iterations.");
										System.out.println();
										printHelp(withClasses, withZoom);
										System.exit(-1);
									}
									break;
								case "-r": case "--reflect":
									reflect = true;
									break;
								case "-x": case "--x-zoom":
									if (ii + 1 < args.length) {
										try {
											xZoom = Double.parseDouble(args[ii + 1]);
											ii++;
										} catch (NumberFormatException e) {
											System.out.println("Invalid x zoom value. Must be a double.");
											System.out.println();
											printHelp(withClasses, withZoom);
											System.exit(-1);
										}
									} else {
										System.out.println("No value provided for x zoom.");
										System.out.println();
										printHelp(withClasses, withZoom);
										System.exit(-1);
									}
									break;
								case "-y": case "--y-zoom":
									if (ii + 1 < args.length) {
										try {
											yZoom = Double.parseDouble(args[ii + 1]);
											ii++;
										} catch (NumberFormatException e) {
											System.out.println("Invalid y zoom value. Must be a double.");
											System.out.println();
											printHelp(withClasses, withZoom);
											System.exit(-1);
										}
									} else {
										System.out.println("No value provided for y zoom.");
										System.out.println();
										printHelp(withClasses, withZoom);
										System.exit(-1);
									}
									break;
								case "-m": case "--multiplier":
									if (ii + 1 < args.length) {
										try {
											multiplier = Double.parseDouble(args[ii + 1]);
											ii++;
										} catch (NumberFormatException e) {
											System.out.println("Invalid multiplier value. Must be a double.");
											System.out.println();
											printHelp(withClasses, withZoom);
											System.exit(-1);
										}
									} else {
										System.out.println("No value provided for y multiplier.");
										System.out.println();
										printHelp(withClasses, withZoom);
										System.exit(-1);
									}
									break;
								case "--start-zoom":
									if (ii + 1 < args.length) {
										try {
											startZoom = Integer.parseInt(args[ii + 1]);
											ii++;
										} catch (NumberFormatException e) {
											System.out.println("Invalid start zoom value. Must be an integer.");
											System.out.println();
											printHelp(withClasses, withZoom);
											System.exit(-1);
										}
									} else {
										System.out.println("No value provided for start zoom.");
										System.out.println();
										printHelp(withClasses, withZoom);
										System.exit(-1);
									}
									break;
								case "--stop-zoom":
									if (ii + 1 < args.length) {
										try {
											stopZoom = Integer.parseInt(args[ii + 1]);
											ii++;
										} catch (NumberFormatException e) {
											System.out.println("Invalid stop zoom value. Must be an integer.");
											System.out.println();
											printHelp(withClasses, withZoom);
											System.exit(-1);
										}
									} else {
										System.out.println("No value provided for stop zoom.");
										System.out.println();
										printHelp(withClasses, withZoom);
										System.exit(-1);
									}
									break;
							}
							break;
						}
					default:
						System.out.println("Unknown argument: " + args[ii]);
						System.out.println();
						printHelp(withClasses, withZoom);
						System.exit(-1);
				}
			}
		}
	}

	protected ColorFunction parseColorArgument(String argument) throws IllegalArgumentException {
		if (argument.equals("cyclic-colors")) {
			return new CyclicColorFunction();
		}
		else if (argument.equals("cosine-colors")) {
			return new CosineColorFunction();
		}

		String regx = "^(" + String.join("|", colorNames) + ")(\\d+)?(s)?$";
		Pattern pattern = Pattern.compile(regx);
		Matcher matcher = pattern.matcher(argument);

		if(!matcher.matches()) {
			throw new IllegalArgumentException(
				"Invalid color function. Must be of the form <color>[n][s] where color is one of " + getColorChoices() + "."
			);
		}

		String color = matcher.group(1);
		int count = matcher.group(2) != null ? Integer.parseInt(matcher.group(2)) : 10;
		boolean smooth = matcher.group(3) != null;

		if (count <= 0) {
			throw new IllegalArgumentException(
				"Invalid value for n, must be a positive integer."
			);
		}

		Function<Integer, ColorFunction> function = smooth ? smoothColorMap.get(color) : palletedColorMap.get(color);
		if (function == null) {
			throw new IllegalArgumentException(
				"Internal error: No function found for color palette: " + color
			);
		}

		return function.apply(count);
	}
}

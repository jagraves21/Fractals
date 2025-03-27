import java.lang.reflect.InvocationTargetException;

import java.util.*;

public class FractalToGIF
{
	public static void printHelp()
    {
        System.out.println("Usage: java FractalToGIF [options] <class files>");
        System.out.println("Options:");
        System.out.println("  -w, --width <width>        Set the width of the fractal (default: 800)");
        System.out.println("  -h, --height <height>      Set the height of the fractal (default: 600)");
        System.out.println("  -i, --iterations <count>   Set the number of iterations (default: auto)");
		System.out.println("  -d, --delay  <numner>      Set the frame delay in milliseconds (default: 500)");
		System.out.println("  -n, --no-reflect           Prevent the GIF from being reflect (default: reflected)");
		System.out.println("  -h, --help                 Show this help message");
    }
	
	public static void main(String[] args)
	{
		int width = 800;
		int height = 600;
		int iterations = -1;
		int delay = 500;
		boolean reflect = true;
		
		List<String> classes = new LinkedList<String>();
		ClassLoader classLoader = FractalToGIF.class.getClassLoader();

		for (String arg : args)
		{
			if (arg.equals("-h") || arg.equals("--help"))
			{
				printHelp();
				return;
			}
		}

		for (int ii = 0; ii < args.length; ii++)
		{
			if(args[ii].endsWith(".class"))
			{
				String className = args[ii].substring(0, args[ii].lastIndexOf(".class"));
				classes.add(className);
			}
			else {
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
							}
						}
						else
						{
							System.out.println("No value provided for width.");
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
							}
						}
						else
						{
							System.out.println("No value provided for height.");
						}
						break;
					case "-i":
					case "--iterations":
						if (ii + 1 < args.length)
						{
							try
							{
								iterations = Integer.parseInt(args[ii + 1]);
								ii++;
							}
							catch (NumberFormatException e)
							{
								System.out.println("Invalid iterations value. Must be an integer.");
							}
						}
						else
						{
							System.out.println("No value provided for iterations.");
						}
						break;
					case "-d":
					case "--delay":
						if (ii + 1 < args.length)
						{
							try
							{
								delay = Integer.parseInt(args[ii + 1]);
								ii++;
							}
							catch (NumberFormatException e)
							{
								System.out.println("Invalid delay value. Must be an integer.");
							}
						}
						else
						{
							System.out.println("No value provided for delay.");
						}
						break;
					case "-n":
					case "--no-reflect":
						reflect = false;
                break;
					default:
						System.out.println("Unknown argument: " + args[ii]);
						System.out.println();
						printHelp();
						System.exit(-1);
				}
			}
		}


		Iterator<String> iter = classes.iterator();
		for(String className : classes)
		{
			try
			{
				Class<?> aClass = classLoader.loadClass(className);
				SimpleFractal fractal = (SimpleFractal) aClass.getDeclaredConstructor().newInstance();

				if(iterations == -1)
				{
					fractal.createGIF(className + ".gif", width, height, delay, fractal.getSuggestedIterations(), reflect);
				}
				else
				{
					fractal.createGIF(className + ".gif", width, height, delay, iterations, reflect);
				}
				System.out.println(className + ".gif");
			}
			catch (ClassNotFoundException e)
			{
				System.err.println("Class not found: " + className);
			}
			catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e)
			{
				System.err.println("Could not instantiate " + className + ": " + e.getMessage());
			}
			catch (ClassCastException e)
			{
				System.err.println(className + " does not extend SimpleFractal.");
			}
		}
	}
}

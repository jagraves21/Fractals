import java.lang.reflect.InvocationTargetException;

import java.util.*;

public class FractalToPNG
{
	public static void printHelp()
    {
        System.out.println("Usage: java FractalToPNG [options] <class files>");
        System.out.println("Options:");
        System.out.println("  -w, --width <width>        Set the width of the fractal (default: 800)");
        System.out.println("  -h, --height <height>      Set the height of the fractal (default: 600)");
        System.out.println("  -i, --iterations <count>   Set the number of iterations");
        System.out.println("  -h, --help                 Show this help message");
    }
	
	public static void main(String[] args)
	{
		int width = 800;
		int height = 600;
		int iterations = -1;
		
		List<String> classes = new LinkedList<String>();
		ClassLoader classLoader = FractalToPNG.class.getClassLoader();

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
					fractal.createPNG(className + ".png", width, height, fractal.getSuggestedIterations());
				}
				else
				{
					fractal.createPNG(className + ".png", width, height, iterations);
				}
				System.out.println(className + ".png");
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

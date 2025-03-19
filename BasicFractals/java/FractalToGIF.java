import java.util.*;

public class FractalToGIF
{
	public static void usage()
	{
		System.out.println("java FractalToGIF [-options] <class files>");
		System.out.println("  -w <numner>     specify the width of the gif");
		System.out.println("  -h <numner>     specify the height of the gif");
		System.out.println("  -d <numner>     specify the frame delay in milliseconds");
		System.out.println("  -i <numner>     iterations");
		System.out.println("");
		System.out.println("example:  java FractalToGIF -w 800 -h 500 -d 500 Dragon.class LevyCurve.class\n");
		
		System.exit(-1);
	}
	
	public static void main(String[] args)
	{
		int width = 800;
		int height = 600;
		int delay = 500;
		int iterations=-1;
		
		String arg;
		String className;
		List<String> classes = new LinkedList<String>();
		ClassLoader classLoader = FractalToGIF.class.getClassLoader();
		
		if(args.length < 1)
		{
			usage();
		}
		
		for(int ii=0; ii < args.length; ii++)
		{
			arg = args[ii];
			if(arg.endsWith(".class"))
			{
				className = arg.substring(0, arg.lastIndexOf(".class"));
				classes.add(className);
			}
			else if(arg.equals("-w") || (arg.equals("--width")))
			{
				ii++;
				if(ii < args.length)
				{
					try
					{
						width = Integer.parseInt(args[ii]);
					}
					catch(NumberFormatException nfe)
					{
						System.out.println("width argument must be a number");
						usage();
					}
				}
				else
				{
					System.out.println("width argument must be a number");
					usage();
				}
			}
			else if(arg.equals("-h") || (arg.equals("--height")))
			{
				ii++;
				if(ii < args.length)
				{
					try
					{
						height = Integer.parseInt(args[ii]);
					}
					catch(NumberFormatException nfe)
					{
						System.out.println("height argument must be a number");
						usage();
					}
				}
				else
				{
					System.out.println("height argument must be a number");
					usage();
				}
			}
			else if(arg.equals("-d") || (arg.equals("--delay")))
			{
				ii++;
				if(ii < args.length)
				{
					try
					{
						delay = Integer.parseInt(args[ii]);
					}
					catch(NumberFormatException nfe)
					{
						System.out.println("delay argument must be a number");
						usage();
					}
				}
				else
				{
					System.out.println("delay argument must be a number");
					usage();
				}
			}
			else if(arg.equals("-i") || (arg.equals("--iterations")))
			{
				ii++;
				if(ii < args.length)
				{
					try
					{
						iterations = Integer.parseInt(args[ii]);
					}
					catch(NumberFormatException nfe)
					{
						System.out.println("iterations argument must be a positve number");
						usage();
					}
				}
				else
				{
					System.out.println("iterations argument must be a number");
					usage();
				}
			}
			else
			{
				System.out.println("unknown argument " + arg);	
			}
		}
		
		if(classes.size() == 0)
		{
			usage();
		}
		
		Iterator<String> iter = classes.iterator();
		while(iter.hasNext())
		{
			try
			{
				className = iter.next();
				Class aClass = classLoader.loadClass(className);
				SimpleFractal fractal = (SimpleFractal) aClass.newInstance();
				
				if(iterations == -1)
				{
					fractal.createGIF(className + ".gif", width, height, delay, fractal.getSuggestedIterations());
				}
				else
				{
					fractal.createGIF(className + ".gif", width, height, delay, iterations);
				}
				System.out.println(className + ".gif");
			}
			catch (ClassNotFoundException e) {
				System.err.println(e.getMessage() + " class not found.");
			}
			catch(InstantiationException ie)
			{
				System.err.println("Unable to create instance of " + ie.getMessage() + ".");
			}
			catch(IllegalAccessException iae)
			{
				iae.printStackTrace();
			}
			catch(ClassCastException cce)
			{
				System.err.println(cce.getMessage() + ".");
			}
		}
	}
}
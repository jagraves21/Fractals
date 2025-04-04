import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class FractalToGIF {
	public static void main(String[] args) {
		ArgumentParser argumentParser = new ArgumentParser(
			"FractalToGIF",
			800,
			600,
			-1,
			-1,
			false
		);
		argumentParser.parseArguments(args, true, true);

		ClassLoader classLoader = FractalToGIF.class.getClassLoader();
		for (String className : argumentParser.classes) {
			try {
				Class<?> aClass = classLoader.loadClass(className);
				SimpleFractal fractal = (SimpleFractal) aClass.getDeclaredConstructor().newInstance();

				int delay = argumentParser.delay;
				if (delay == -1) {
					delay = fractal.getSuggestedDelay();
				}
				int iterations = argumentParser.iterations;
				if (iterations == -1) {
					iterations = fractal.getSuggestedIterations();
				}
				fractal.createGIF(
					className + ".gif",
					argumentParser.width,
					argumentParser.height,
					iterations,
					delay,
					argumentParser.reflect
				);
				System.out.println(className + ".gif");
			} catch (ClassNotFoundException e) {
				System.out.println("Class not found: " + className);
			} catch (InstantiationException
					| IllegalAccessException
					| InvocationTargetException
					| NoSuchMethodException e) {
				System.out.println("Could not instantiate " + className + ": " + e.getMessage());
			} catch (ClassCastException e) {
				System.out.println(className + " does not extend SimpleFractal.");
			}
		}
	}
}

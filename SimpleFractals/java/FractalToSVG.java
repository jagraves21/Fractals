import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class FractalToSVG {
	public static void main(String[] args) {
		ArgumentParser argumentParser = new ArgumentParser(
			"FractalToSVG",
			800,
			600,
			-1,
			-1,
			false
		);
		argumentParser.parseArguments(args, true, false);

		ClassLoader classLoader = FractalToSVG.class.getClassLoader();
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
				fractal.createSVG(
					className + ".svg",
					argumentParser.width,
					argumentParser.height,
					iterations
				);
				System.out.println(className + ".svg");
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

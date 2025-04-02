import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;

public class FractalSubclassFinder {
	public static void main(String[] args) {
		if (args.length == 0) {
			System.out.println("Usage: java FractalSubclassFinder <classfile1> <classfile2> ...");
			return;
		}

		for (String filePath : args) {
			File file = new File(filePath);
			if (!file.exists() || !file.getName().endsWith(".class")) {
				System.out.println("Skipping invalid file: " + filePath);
				continue;
			}

			try {
				// Extract directory and class name
				String dir = file.getParent();
				String className = file.getName().replace(".class", "");

				URLClassLoader classLoader =
						URLClassLoader.newInstance(new URL[] {new File(dir).toURI().toURL()});
				Class<?> clazz = Class.forName(className, false, classLoader);

				if (isSubclassOf(clazz, SimpleFractal.class)) {
					// System.out.println(className + " extends SimpleFractal");
					System.out.println(className);
				}
			} catch (Exception e) {
				System.out.println("Error loading " + filePath + ": " + e.getMessage());
			}
		}
	}

	private static boolean isSubclassOf(Class<?> clazz, Class<?> target) {
		while (clazz != null) {
			if (clazz == target) {
				return true;
			}
			clazz = clazz.getSuperclass();
		}
		return false;
	}
}

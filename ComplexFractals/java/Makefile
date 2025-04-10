JAVAC = javac
SRC = $(wildcard *.java)
CLASSES = $(SRC:.java=.class)

FRACTAL_TO_GIF = FractalToGIF
FRACTAL_TO_PNG = FractalToPNG
FRACTAL_TO_SVG = FractalToSVG

JULIA_MAIN = JuliaGUI
JULIA_JAR = JuliaGUI.jar

MANDELBROT_MAIN = MandelbrotGUI
MANDELBROT_JAR = MandelbrotGUI.jar

CHASING_INFINITY_MAIN = ChasingInfinity
CHASING_INFINITY_JAR= ChasingInfinity.jar

.PHONY: all
all: $(CLASSES)

%.class: %.java
	$(JAVAC) $<

$(JULIA_JAR): all
	jar -cfe $(JULIA_JAR) $(JULIA_MAIN) *.class *.java

$(MANDELBROT_JAR): all
	jar -cfe $(MANDELBROT_JAR) $(MANDELBROT_MAIN) *.class *.java

$(CHASING_INFINITY_JAR): all
	jar -cfe $(CHASING_INFINITY_JAR) $(CHASING_INFINITY_MAIN) *.class *.java

.PHONY: jar
jar: $(JULIA_JAR) $(MANDELBROT_JAR) $(CHASING_INFINITY_JAR)

.PHONY: clean-class
clean-class:
	rm -f *.class

.PHONY: clean-jar
clean-jar:
	rm -f $(JULIA_JAR)
	rm -f $(MANDELBROT_JAR)
	rm -f $(CHASING_INFINITY_JAR)

.PHONY: clean
clean: clean-class

.PHONY: veryclean
veryclean: clean clean-jar


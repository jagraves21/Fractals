JAVAC = javac
SRC = $(wildcard *.java)
CLASSES = $(SRC:.java=.class)

FractalToGIF = FractalToGIF
FractalToPNG = FractalToPNG
FractalToSVG = FractalToSVG

JAR_NAME = GUI.jar
MAIN_CLASS = Fractal

EXCLUDE_CLASSES = AbstractFractal AnimatedGifEncoder Bubble Circle Fractal FractalPanel FractalShape FractalToGIF FractalToPNG FractalToSVG FractalTree LineSegment LSystem MyPoint Pentagon Polygon Rectangle SimpleFractal Triangle UnorderedLineSegment

FRACTAL_CLASSES = $(filter-out $(EXCLUDE_CLASSES:%=%.class), $(CLASSES))

%.class: %.java
	$(JAVAC) $<

.PHONY: all
all: $(CLASSES)

.PHONY: gif
gif: all
	java $(FractalToGIF) $(FRACTAL_CLASSES)

.PHONY: png
png: all
	java $(FractalToPNG) $(FRACTAL_CLASSES)

.PHONY: svg
svg: all
	java $(FractalToSVG) $(FRACTAL_CLASSES)

manifest.mf:
	echo "Main-Class: $(MAIN_CLASS)" > manifest.mf
	echo "" >> manifest.mf

.PHONY: jar
jar: all manifest.mf
	jar -cvfm $(JAR_NAME) manifest.mf $(CLASSES) $(SRC)
	rm -f manifest.mf

.PHONY: cleanclass
cleanclass:
	rm -f *.class

.PHONY: cleanpng
cleanpng:
	rm -f *.png

.PHONY: cleansvg
cleansvg:
	rm -f *.svg

.PHONY: cleangif
cleangif:
	rm -f *.gif

.PHONY: clean
clean: cleanclass

.PHONY: veryclean
veryclean: clean cleanpng cleansvg cleangif
	rm -f $(JAR_NAME)


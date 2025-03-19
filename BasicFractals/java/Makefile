JAVAC = javac
SRC = $(wildcard *.java)
CLASSES = $(SRC:.java=.class)
TAR_NAME = fractals.tar.gz

FractalToGIF = FractalToGIF
FractalToPNG = FractalToPNG

EXCLUDE_CLASSES = AbstractFractal AnimatedGifEncoder Bubble Circle Fractal FractalPanel FractalShape FractalToGIF FractalToPNG FractalTree LineSegment LSystem MyPoint Pentagon Polygon Rectangle SimpleFractal Triangle UnorderedLineSegment

OTHER_CLASSES = $(filter-out $(EXCLUDE_CLASSES:%=%.class) $(FractalToGIF).class, $(CLASSES))

.PHONY: all
all: $(CLASSES)

%.class: %.java
	$(JAVAC) $<

.PHONY: cleanclass
cleanclass:
	rm -f *.class

.PHONY: cleanjpg
cleanjpg:
	rm -f *.jpg

.PHONY: cleanpng
cleanpng:
	rm -f *.png

.PHONY: cleangif
cleangif:
	rm -f *.gif

.PHONY: clean
clean: cleanclass cleanjpg

.PHONY: tar
tar: all
	tar -czf $(TAR_NAME) *.java *.class

.PHONY: gif
gif: all
	java $(FractalToGIF) $(OTHER_CLASSES)
	$(MAKE) cleanjpg

.PHONY: png
png: all
	java $(FractalToPNG) $(OTHER_CLASSES)

.PHONY: veryclean
veryclean: clean cleanpng cleangif
	rm -f $(TAR_NAME)

.PHONY: tell
tell:
	@echo $(EXCLUDE_CLASSES)
	@echo ""
	@echo $(OTHER_CLASSES)


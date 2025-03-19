JAVAC = javac
SRC = $(wildcard *.java)
CLASSES = $(SRC:.java=.class)
TAR_NAME = fractals.tar.gz

FractalToGIF = FractalToGIF  # Change this to the name of your runner class

EXCLUDE_CLASSES = AbstractFractal AnimatedGifEncoder Bubble Circle Fractal FractalPanel FractalShape FractalToGIF FractalToPNG FractalTree LineSegment LSystem MyPoint Pentagon Polygon Rectangle SimpleFractal Triangle UnorderedLineSegment

# Get all class file names except the runner's class and the excluded classes
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
	java $(FractalToGIF) -w 800 -h 600 -d 500 $(OTHER_CLASSES)
	$(MAKE) cleanjpg

.PHONY: veryclean
veryclean: clean cleangif
	rm -f $(TAR_NAME)

.PHONY: tell
tell:
	@echo $(EXCLUDE_CLASSES)
	@echo ""
	@echo $(OTHER_CLASSES)


JAVAC = javac
SRC = $(wildcard *.java)
CLASSES = $(SRC:.java=.class)

FractalToGIF = FractalToGIF
FractalToPNG = FractalToPNG
FractalToSVG = FractalToSVG

JAR_NAME = GUI.jar
MAIN_CLASS = GUI

FRACTALS = ApollonianGasket BoxFractal Branches CesaroFractal Christmas ChristmasApollonianGasket ChristmasKochSnowflake ChristmasSierpinskiCarpet ChristmasSierpinskiTriangle CircleInversion CircleInversion2 CircleInversion2Mod CircleInversionMod CircleLimitSet CircleLimitSet1 CircleLimitSet2 CircleLimitSet4 CircleLimitSet4Overlap CircleLimitSetNew CirclePacking Circles Cross Cross2 Crosses Curlicue DimondFlake Dragon DragonColor DragonLSystem DragonMod Edge EdgeMod Fern HFractal HenonAttractor HexagonalTile HilbertCurve InwardCircle IslandsLakes KochAntisnowflake KochCubes KochCurve KochLine KochLineMod KochMoss KochSnowflake LRepTile LRepTileMod Lace LevyCurve LevyCurves LevyTapestry Lines MangoKolam MinkowskiLine MinkowskiSausage MooreCurve NestedPentagram OutwardCircle Peano PeanoGosperCurve PenroseTiling PentaFlake PentaFlakeMod PentaFractal PentaStar PentaStarMod Pentadentrite Pentagram Pentigree Plusses PythagorasShrub PythagorasTree QuadraticFlake RhombusFlake SierpinskiArrowhead SierpinskiCarpet SierpinskiMedian SierpinskiSquare SierpinskiTriangle SpiralSquare Square SquareFlake SquareMesh SquareStar SquareStar2 SquareTiling SquareTriangle SquareTriangleMod SquareTurn Squares Squares2 Squares2Mod SquaresMod TSquare TernaryTree Test TetraDragon Thorn ThreeBranch TornSquare Tree TreeBlue TreeMod TreePink TriCircle TriangleFlake

FRACTAL_CLASSES = $(addsuffix .class, $(FRACTALS))

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

$(JAR_NAME): all
	jar -cfe $(JAR_NAME) $(MAIN_CLASS) *.class $(SRC)

.PHONY: jar
jar: $(JAR_NAME)

.PHONY: clean-class
clean-class:
	rm -f *.class

.PHONY: clean-gif
clean-gif:
	rm -f *.gif

.PHONY: clean-png
clean-png:
	rm -f *.png

.PHONY: clean-svg
clean-svg:
	rm -f *.svg

.PHONY: clean-jar
clean-jar:
	rm -f $(JAR_NAME)

.PHONY: clean
clean: clean-class

.PHONY: veryclean
veryclean: clean clean-gif clean-png clean-svg clean-jar


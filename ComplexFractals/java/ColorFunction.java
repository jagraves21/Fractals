import java.awt.Color;

public interface ColorFunction {
	public static final Color BLACK = Color.BLACK;                          // Black
	public static final Color WHITE = Color.WHITE;                          // WHite

	// Reds and Warm Tones
	public static final Color DARK_RED     = new Color(100, 0, 0);          // Dark Red
	public static final Color BROWN_RED    = new Color(60, 16, 0);          // Brown Red
	public static final Color BRICK_RED    = new Color(120, 20, 5);         // Brick Red
	public static final Color HOT_ORANGE   = new Color(200, 60, 10);        // Hot Orange
	public static final Color ORANGE       = Color.ORANGE;                  // Orange
	public static final Color GLOW_YELLOW  = new Color(250, 180, 50);       // Glow Yellow
	public static final Color RED          = Color.RED;                     // Red
	public static final Color YELLOW       = Color.YELLOW;                  // Yellow

	// Greens and Earthy Tones
	public static final Color DEEP_GREEN   = new Color(38, 58, 31);         // Deep Green
	public static final Color MOSS_GREEN   = new Color(90, 130, 55);        // Moss Green
	public static final Color SEAFOAM      = new Color(160, 215, 122);      // Seafoam
	public static final Color OLIVE        = new Color(106, 134, 59);       // Olive
	public static final Color GREEN        = Color.GREEN;                   // Green

	// Blues and Cool Tones
	public static final Color DARK_BLUE    = new Color(0, 0, 100);          // Dark Blue
	public static final Color TEAL_BLUE    = new Color(0, 116, 112);        // Teal Blue
	public static final Color BLUE         = Color.BLUE;         ;          // Blue
	public static final Color BRIGHT_CYAN  = new Color(0, 183, 211);        // Bright Cyan
	public static final Color BLUE_VIOLET  = new Color(138, 43, 226);       // Blue Violet
	public static final Color INDIGO       = new Color(75, 0, 130);         // Indigo
	public static final Color VIOLET       = new Color(148, 0, 211);        // Violet
	public static final Color CYAN         = Color.CYAN;                    // Cyan

	// Purple and Deep Tones
	public static final Color DEEP_PURPLE  = new Color(15, 8, 20);          // Deep Purple

	// Additional Palettes
	// Sunset Palette (warm colors, sunset effect)
	public static final Color SUNSET_CORAL     = new Color(255, 94, 77);    // Coral
	public static final Color SUNSET_ORANGE    = new Color(255, 140, 0);    // Sunset Orange
	public static final Color SUNSET_YELLOW    = new Color(255, 204, 0);    // Yellow
	public static final Color SUNSET_LIGHT_RED = new Color(255, 85, 85);    // Light Red
	public static final Color SUNSET_AMBER     = new Color(255, 147, 41);   // Amber

	// Neon Palette (bright, glowing colors)
	public static final Color NEON_CYAN       = new Color(0, 255, 255);     // Neon Cyan
	public static final Color NEON_MAGENTA    = new Color(255, 0, 255);     // Neon Magenta
	public static final Color NEON_YELLOW     = new Color(255, 255, 0);     // Neon Yellow
	public static final Color NEON_GREEN      = new Color(0, 255, 0);       // Neon Green
	public static final Color NEON_RED        = new Color(255, 0, 0);       // Neon Red

	// Ocean Palette (cool colors, aquatic effect)
	public static final Color OCEAN_BLUE      = new Color(0, 0, 255);         // Ocean Blue
	public static final Color OCEAN_SKY_BLUE  = new Color(0, 128, 255);       // Sky Blue
	public static final Color OCEAN_DEEP_BLUE = new Color(0, 191, 255);       // Deep Sky Blue
	public static final Color OCEAN_STEEL_BLUE = new Color(70, 130, 180);     // Steel Blue
	public static final Color OCEAN_DARK_SLATE_BLUE = new Color(72, 61, 139); // Dark Slate Blue

	// Forest Palette (earthy tones, nature)
	public static final Color FOREST_GREEN    = new Color(34, 139, 34);       // Forest Green
	public static final Color FOREST_OLIVE    = new Color(85, 107, 47);       // Olive Green
	public static final Color FOREST_SADDLE_BROWN = new Color(139, 69, 19);   // Saddle Brown
	public static final Color FOREST_LIGHT_GREEN = new Color(0, 128, 0);      // Green
	public static final Color FOREST_SIENNA   = new Color(160, 82, 45);       // Sienna

	// Tropical Palette (bright, tropical colors)
	public static final Color TROPICAL_HOT_PINK = new Color(255, 105, 180);   // Hot Pink
	public static final Color TROPICAL_ORANGE   = new Color(255, 165, 0);     // Tropical Orange
	public static final Color TROPICAL_CYAN     = new Color(0, 255, 255);     // Tropical Cyan
	public static final Color TROPICAL_GOLD     = new Color(255, 215, 0);     // Tropical Gold
	public static final Color TROPICAL_FOREST_GREEN = new Color(34, 139, 34); // Tropical Forest Green

	// Pink and Blue Palette (dreamy colors)
	public static final Color PINK_BLUSH       = new Color(255, 182, 193);    // Light Pink
	public static final Color PINK_SKY_BLUE    = new Color(173, 216, 230);    // Light Blue
	public static final Color PINK_HOT_PINK    = new Color(255, 105, 180);    // Hot Pink
	public static final Color PINK_SKY_BLUE_2  = new Color(135, 206, 250);    // Sky Blue
	public static final Color PINK_SOFT_PINK   = new Color(255, 192, 203);    // Soft Pink

	// Twilight Palette (blend of purples and blues, from deep night to bright horizon)
	public static final Color TWILIGHT_LAVENDER   = new Color(230, 230, 250);  // Soft lavender
	public static final Color TWILIGHT_SKY_BLUE   = new Color(80, 180, 255);   // Vibrant horizon blue
	public static final Color TWILIGHT_PERIWINKLE = new Color(204, 204, 255);  // Gentle periwinkle

	// Oil Slick Palette (vibrant, iridescent rainbow colors with slick oil-sheen effect)
	public static final Color DEEP_MAGENTA       = new Color(255, 0, 128);     // Deep Magenta
	public static final Color OIL_CYAN           = new Color(0, 255, 255);     // Slick Cyan
	public static final Color GOLDEN_YELLOW      = new Color(255, 215, 0);     // Golden Yellow
	public static final Color RICH_FUCHSIA       = new Color(255, 20, 147);    // Rich Fuchsia
	public static final Color STORMY_BLUE        = new Color(25, 25, 112);     // Deep Stormy Blue
	public static final Color MINT_GLARE         = new Color(0, 255, 127);     // Minty Glare
	public static final Color HOT_SAFFRON        = new Color(255, 140, 0);     // Hot Saffron
	public static final Color VIOLET_FLARE       = new Color(138, 43, 226);    // Violet Flare

	// Gas on Water Palette (vibrant, iridescent rainbow colors with slick oil-sheen effect)
	public static final Color SLICK_MAGENTA      = new Color(255, 0, 255);     // Hot Magenta
	public static final Color ELECTRIC_CYAN      = new Color(0, 255, 255);     // Electric Cyan
	public static final Color IRIDESCENT_YELLOW  = new Color(255, 255, 0);     // Bright Yellow
	public static final Color SHIMMER_PINK       = new Color(255, 0, 128);     // Fuchsia Pink
	public static final Color OIL_SHEEN_BLUE     = new Color(0, 128, 255);     // Radiant Blue
	public static final Color MINT_FLARE         = new Color(0, 255, 128);     // Minty Green
	public static final Color FLAME_ORANGE       = new Color(255, 100, 0);     // Fiery Orange
	public static final Color VIOLET_GLEAM       = new Color(160, 32, 240);    // Electric Purple

	public static Color[] RGB = new Color[]{
		RED,
		GREEN,
		BLUE
	};

	public static Color[] RAINBOW = new Color[]{
		RED,
		ORANGE,
		YELLOW,
		GREEN,
		BLUE,
		INDIGO,
		VIOLET
	};

	public static Color[] SPECTRUM = new Color[]{
		RED,
		YELLOW,
		GREEN,
		BLUE,
		BLUE_VIOLET,
		INDIGO,
		BLUE_VIOLET,
		BLUE,
		GREEN,
		YELLOW,
		RED
	};

	public static Color[] FOREST = new Color[]{
		FOREST_GREEN,
		FOREST_OLIVE,
		FOREST_SADDLE_BROWN,
		FOREST_LIGHT_GREEN,
		FOREST_SIENNA
	};

	public static Color[] SEAWEED = new Color[]{
		BRIGHT_CYAN,
		TEAL_BLUE,
		SEAFOAM,
		OLIVE,
		MOSS_GREEN,
		DEEP_GREEN
	};

	public static Color[] OCEAN = new Color[]{
		OCEAN_BLUE,
		OCEAN_SKY_BLUE,
		OCEAN_DEEP_BLUE,
		OCEAN_STEEL_BLUE,
		OCEAN_DARK_SLATE_BLUE
	};

	public static Color[] AQUA = new Color[]{
		BLUE,
		CYAN,
		GREEN,
		CYAN,
		BLUE
	};

	public static Color[] SUNSET = new Color[]{
		SUNSET_CORAL,
		SUNSET_ORANGE,
		SUNSET_YELLOW,
		SUNSET_LIGHT_RED,
		SUNSET_AMBER
	};

	public static Color[] TROPICAL = new Color[]{
		TROPICAL_HOT_PINK,
		TROPICAL_ORANGE,
		TROPICAL_CYAN,
		TROPICAL_GOLD,
		TROPICAL_FOREST_GREEN
	};

	public static Color[] NEON = new Color[]{
		NEON_CYAN,
		NEON_MAGENTA,
		NEON_YELLOW,
		NEON_GREEN,
		NEON_RED
	};

	public static Color[] SPACE = new Color[]{
		DEEP_PURPLE,
		DARK_BLUE,
		DARK_RED,
		INDIGO,
		VIOLET
	};

	public static final Color[] TWILIGHT = new Color[]{
		TWILIGHT_LAVENDER,
		TWILIGHT_PERIWINKLE,
		TWILIGHT_SKY_BLUE,
		BLUE_VIOLET,
		OCEAN_DARK_SLATE_BLUE,
		INDIGO,
		DEEP_PURPLE,
		INDIGO,
		OCEAN_DARK_SLATE_BLUE,
		BLUE_VIOLET,
		TWILIGHT_SKY_BLUE,
		TWILIGHT_PERIWINKLE,
		TWILIGHT_LAVENDER
	};

	public static Color[] ICE_FIRE = new Color[]{
		DARK_BLUE,
		BLUE,
		RED,
		DARK_RED,
		DARK_BLUE
	};

	public static Color[] ZEBRA = new Color[]{
		BLACK,
		WHITE
	};

	public static Color[] CANDY_CANE = new Color[]{
		WHITE,
		RED,
		WHITE
	};

	public static Color[] MAGMA = new Color[]{
		DEEP_PURPLE,
		BROWN_RED,
		BRICK_RED,
		HOT_ORANGE,
		GLOW_YELLOW,
		BRICK_RED,
		BROWN_RED,
		DEEP_PURPLE
	};

	public static Color[] LAVA = new Color[]{
		BLACK,
		RED,
		YELLOW,
		RED,
		BLACK
	};

	public static Color[] PINK_BLUE = new Color[]{
		PINK_BLUSH,
		PINK_SKY_BLUE,
		PINK_HOT_PINK,
		PINK_SKY_BLUE_2,
		PINK_SOFT_PINK
	};

	public static final Color[] OIL_SLICK = new Color[]{
		DEEP_MAGENTA,
		OIL_CYAN,
		GOLDEN_YELLOW,
		RICH_FUCHSIA,
		STORMY_BLUE,
		MINT_GLARE,
		HOT_SAFFRON,
		VIOLET_FLARE
	};

	public static final Color[] GAS_ON_WATER = new Color[]{
		SLICK_MAGENTA,
		ELECTRIC_CYAN,
		IRIDESCENT_YELLOW,
		SHIMMER_PINK,
		OIL_SHEEN_BLUE,
		MINT_FLARE,
		FLAME_ORANGE,
		VIOLET_GLEAM
	};

	public static Color[] TEST = new Color[]{
		new Color(149, 69, 133),
		new Color(47, 63, 111),
		new Color(94, 196, 228),
		new Color(221, 238, 255),
		new Color(255, 185, 97),
		new Color(234, 117, 0),
		new Color(134, 68, 180),
		new Color(94, 196, 228)
	};

	public Color getConvergentColor(Complex z, int iteration, int maxIteration);
	public Color getDivergentColor(Complex z, int iteration, int maxIteration);
}

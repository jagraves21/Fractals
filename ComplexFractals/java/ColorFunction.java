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

	public Color getConvergentColor(Complex z, int iteration, int maxIteration);
	public Color getDivergentColor(Complex z, int iteration, int maxIteration);
}

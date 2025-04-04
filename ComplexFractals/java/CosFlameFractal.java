import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

public class CosFlameFractal extends AnimatedPanel {
	public static final int MAX_ITER = 100000000/10;

	protected int maxIteration;

	protected int maxIterations;
	double[][][] accumulationPoints;

	public CosFlameFractal() {
		super();

		maxIterations = MAX_ITER;
		accumulationPoints = new double[0][0][0];
	}

	double a = 1.4+(5*0.01), b = -2.3+(5*0.002), c = 2.4-(5*0.001), d = -2.1-(5*0.002);
	//double a = 1.40, b = 1.56, c = 1.40, d = -6.56;
	double x = 0.6, y = 0.9, z = 0.3;
	public void draw(Graphics g, int gWidth, int gHeight) {
		java.util.Random random = new java.util.Random(1);
		int maxWidth = gWidth;
		int maxHeight = gHeight;

		a += 0.01; b += 0.002; c -= 0.001; d -= 0.002;
		double tx, ty, tz;
		int u, v;
		int red, green, blue;

		if(accumulationPoints.length != maxWidth || (accumulationPoints.length > 0 && accumulationPoints[0].length != maxHeight)) {
			accumulationPoints = new double[maxWidth][maxHeight][4];

			for (int width=0; width < maxWidth; width++) {
				for (int height=0; height < maxHeight; height++) {
					for (int ii=0; ii < 4; ii++) {
						accumulationPoints[width][height][ii] = 0;     //Set All to 1.0 (Log-Valued Zero)
					}
				}
			}
		}
		else {
			for (int width=0; width < maxWidth; width++) {
				for (int height=0; height < maxHeight; height++) {
					for (int ii=0; ii < 4; ii++) {
						accumulationPoints[width][height][ii] = 0;     //Set All to 1.0 (Log-Valued Zero)
					}
				}
			}
		}

		for(int ii=0; ii < maxIterations; ii++) {
			tx = Math.sin(a*y) - Math.cos(b*x);
			ty = Math.sin(c*x) - Math.cos(d*y);
			tz = Math.sin(0.7*x) - Math.cos(-1.1*z);

			x = tx;
			y = ty;
			z = tz;

			u = (int)Math.round((x+2)/4 * (maxWidth-1));
			v = (int)Math.round((y+2)/4 * (maxHeight-1));

			accumulationPoints[u][v][0] = x;//z * 0.2 + (1.0-z) * 0.4;
			accumulationPoints[u][v][1] = y;//z * 0.9 + (1.0-z) * 0.6;
			accumulationPoints[u][v][2] = z;//z * 0.5 + (1.0-z) * 0.9;
			accumulationPoints[u][v][3] += 1.0;
		}

		double max = -1.0;
		for (int width=0; width < maxWidth; width++) {
			for (int height=0; height < maxHeight; height++) {
				if(max < accumulationPoints[width][height][3]) {
					max = accumulationPoints[width][height][3];
				}
			}
		}

		//Adjust Values and Fill Image
		double logval, logmax = Math.log(max);
		double M = (logmax * logmax) / 255.0;  //Precomputation for ratio (log(val)/log(max))^2
		double cos;

		for (int width=0; width < maxWidth; width++) {
			for (int height=0; height < maxHeight; height++) {
				logval = Math.log(accumulationPoints[width][height][3]);
				logval = logval * logval / M;

				x = accumulationPoints[width][height][0];
				y = accumulationPoints[width][height][1];
				cos = Math.pow(Math.cos(x/Math.sqrt(x*x+y*y)),2);
				red = (int)Math.round(cos * logval);

				x = accumulationPoints[width][height][0];
				y = accumulationPoints[width][height][2];
				cos = Math.pow(Math.cos(x/Math.sqrt(x*x+y*y)),2);
				green = (int)Math.round(cos * logval);

				x = accumulationPoints[width][height][1];
				y = accumulationPoints[width][height][2];
				cos = Math.pow(Math.cos(x/Math.sqrt(x*x+y*y)),2);
				blue = (int)Math.round(cos * logval);

				/*red = (int)Math.round(accumulationPoints[width][height][0]/maxRGB[0]);
				green = (int)Math.round(accumulationPoints[width][height][1]/maxRGB[1]);
				blue = (int)Math.round(accumulationPoints[width][height][2]/maxRGB[2]);*/

				//System.out.println(accumulationPoints[width][height][0] + " " + accumulationPoints[width][height][1] + " " + accumulationPoints[width][height][2]);
				//System.out.println("\t" + red + " " + green + " " + blue);

				try {
					g.setColor(new Color(red,green,blue));
					g.drawLine(width,height,width,height);
				} catch(IllegalArgumentException iae) {
					System.out.println("\t" + red + " " + green + " " + blue);
					throw iae;
				}
			}
		}
	}

	public String toString() {
		return "Cosine Flame Fractal";
	}

	public static void main(String[] args) {
		AnimatedPanel fractal = new CosFlameFractal();
		AnimatedPanel.display(fractal);
	}
}
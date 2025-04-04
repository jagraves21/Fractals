import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

public class FlameRing extends AnimatedPanel {
	public static final int MAX_ITER = 10000000/25;

	protected int maxIteration;

	protected int totalIterations;
	protected int maxIterations;
	double[][][] accumulationPoints;

	public FlameRing() {
		super();

		totalIterations = 1;
		maxIterations = MAX_ITER;
		accumulationPoints = new double[0][0][0];
	}

	double a = 1.40, b = 1.56, c = 1.40, d = -6.56;
	double x = 0.6, y = 0.9, z = 0.3;
	public void draw(Graphics g, int gWidth, int gHeight) {
		java.util.Random random = new java.util.Random(1);
		int maxWidth = gWidth;
		int maxHeight = gHeight;

		double tx, ty, tz;
		int u, v;
		int red, green, blue;

		if(accumulationPoints.length != maxWidth || (accumulationPoints.length > 0 && accumulationPoints[0].length != maxHeight)) {
			accumulationPoints = new double[maxWidth][maxHeight][4];

			for (int width=0; width < maxWidth; width++) {
				for (int height=0; height < maxHeight; height++) {
					for (int ii=0; ii < 4; ii++) {
						accumulationPoints[width][height][ii] = 1;     //Set All to 1.0 (Log-Valued Zero)
					}
				}
			}

			totalIterations = 1;
			maxIterations = MAX_ITER;
		}
		else {
			/*for (int width=0; width < maxWidth; width++) {
				for (int height=0; height < maxHeight; height++) {
					for (int ii=0; ii < 4; ii++) {
						accumulationPoints[width][height][ii] = 1;     //Set All to 1.0 (Log-Valued Zero)
					}
				}
			}*/
		}

		if(totalIterations < maxIterations) {
			for(int ii=0; ii < maxIterations; ii++) {
				tx = d * Math.sin(a*x) - Math.sin(b*y);
				ty = c * Math.cos(a*x) + Math.cos(b*y);
				tz = Math.sin(0.7*x) - Math.cos(-1.1*z);

				x = tx;
				y = ty;
				z = tz;

				u = (int)Math.round((tx + 13.12) / 26.24 * (maxWidth-1));
				v = (int)Math.round((ty + 2.8) / 5.6 * (200-1) + (maxHeight/2-((200-1.0)/2)));

				accumulationPoints[u][v][0] += z * 0.2 + (1.0-z) * 0.4;
				accumulationPoints[u][v][1] += z * 0.9 + (1.0-z) * 0.6;
				accumulationPoints[u][v][2] += z * 0.5 + (1.0-z) * 0.9;
				accumulationPoints[u][v][3] += 1.0;
			}
			//totalIterations += totalIterations;
		}
		else {
			System.out.println("DONE");
		}

		double max = -1.0;
		for (int width=0; width < maxWidth; width++) {
			for (int height=0; height < maxHeight; height++) {
				for(int jj=0; jj < 4; jj++) {
					if(max < accumulationPoints[width][height][jj]) {
						max = accumulationPoints[width][height][jj];
					}
				}
			}
		}

		//Adjust Values and Fill Image
		double logval, logmax = Math.log(max);
		double M = (logmax * logmax) / 255.0;  //Precomputation for ratio (log(val)/log(max))^2

		for (int width=0; width < maxWidth; width++) {
			for (int height=0; height < maxHeight; height++) {
				logval = Math.log(accumulationPoints[width][height][0]);
				red = (int) (logval * logval / M);

				logval = Math.log(accumulationPoints[width][height][1]);
				green = (int) (logval * logval / M);

				logval = Math.log(accumulationPoints[width][height][2]);
				blue = (int) (logval * logval / M);

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
		return "Flame Ring";
	}

	public static void main(String[] args) {
		AnimatedPanel fractal = new FlameRing();
		AnimatedPanel.display(fractal);
	}
}
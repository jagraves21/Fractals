import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

public class FlameFractal extends AnimatedPanel {
	public static final int MAX_ITER = 10000000/25;

	protected int maxIteration;

	protected int totalIterations;
	protected int maxIterations;
	double[][][] accumulationPoints;

	public FlameFractal() {
		super();

		totalIterations = 1;
		maxIterations = MAX_ITER;
		accumulationPoints = new double[0][0][0];
	}

	//double a = 1.4+(5*0.01), b = -2.3+(5*0.002), c = 2.4-(5*0.001), d = -2.1-(5*0.002);
	double a = 1.40, b = 1.56, c = 1.40, d = -6.56;
	double x = 0.6, y = 0.9, z = 0.3;
	public void draw(Graphics g, int gWidth, int gHeight) {
		java.util.Random random = new java.util.Random(1);
		int maxWidth = gWidth;
		int maxHeight = gHeight;

		//a += 0.01; b += 0.002; c -= 0.001; d -= 0.002;
		//double a = 1.4, b = -2.3, c = 2.4, d = -2.1;
		//double a = -2, b = 2, c = -1.2, d = -2;
		//double a = 0.4, b = 60, c = 10, d = 1.6;
		double ta, tb, tc, td;
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
				//System.out.println(x + " " + y + " " + z);
				ta = a;// + random.nextDouble() * 0.002 - 0.001;
				tb = b;// + random.nextDouble() * 0.002 - 0.001;
				tc = c;// + random.nextDouble() * 0.002 - 0.001;
				td = d;// + random.nextDouble() * 0.002 - 0.001;
				//tx = Math.sin(ta*y) - Math.cos(tb*x);
				//ty = Math.sin(tc*x) - Math.cos(td*y);
				tx = td * Math.sin(ta*x) - Math.sin(tb*y);
				ty = tc * Math.cos(ta*x) + Math.cos(tb*y);
				tz = Math.sin(0.7*x) - Math.cos(-1.1*z);
				x = tx;
				y = ty;
				z = tz;
				//u = (int)Math.round((x+2)/4 * (maxWidth-1));
				//v = (int)Math.round((y+2)/4 * (maxHeight-1));
				u = (int)Math.round((tx + 13.12) / 26.24 * (maxWidth-1));
				v = (int)Math.round((ty + 2.8) / 5.6 * (200-1) + (maxHeight/2-((200-1.0)/2)));


				/*accumulationPoints[u][v][0] += z * 0.9 + (1.0-z) * 0.6;
				accumulationPoints[u][v][1] += z * 0.2 + (1.0-z) * 0.4;
				accumulationPoints[u][v][2] += z * 0.5 + (1.0-z) * 0.9;
				accumulationPoints[u][v][3] += 1.0;*/

				accumulationPoints[u][v][0] += z * 0.2 + (1.0-z) * 0.4;
				accumulationPoints[u][v][1] += z * 0.9 + (1.0-z) * 0.6;
				accumulationPoints[u][v][2] += z * 0.5 + (1.0-z) * 0.9;
				accumulationPoints[u][v][3] += 1.0;

				/*accumulationPoints[u][v][0] += 1.0;
				accumulationPoints[u][v][1] += Math.round((x+z+4)/8);
				accumulationPoints[u][v][2] += Math.round((y+z+4)/8);
				accumulationPoints[u][v][3] += 1.0;*/
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
					/*if(max < accumulationPoints[width][height][3]) {
						max = accumulationPoints[width][height][3];
					}*/
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
		return "Flame Fractal";
	}

	public static void main(String[] args) {
		AnimatedPanel fractal = new FlameFractal();
		AnimatedPanel.display(fractal);
	}
}
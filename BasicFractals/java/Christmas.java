import java.awt.Color;
import java.awt.Paint;
import java.awt.Point;
import java.awt.RadialGradientPaint;

import java.util.*;

import javax.swing.*;

public class Christmas extends SimpleFractal
{
	ChristmasSierpinskiTriangle triangle;
	ChristmasSierpinskiCarpet square;
	ChristmasKochSnowflake flake;
	ChristmasApollonianGasket gasket;
	
	public Christmas()
	{
		super();
		triangle = new ChristmasSierpinskiTriangle();
		square = new ChristmasSierpinskiCarpet();
		flake = new ChristmasKochSnowflake();
		gasket = new ChristmasApollonianGasket();
		createBase();
	}
	
	public int getSuggestedIterations()
	{
		return 9;
	}
		
	protected Paint getForeground()
	{
		MyPoint mid = newMinPoint.midPoint(newMaxPoint);
		mid.y += mid.y * (1.0/10.0);
		
		float[] dist = {0.3f, 1.0f};
		Color[] colors = {Color.BLUE, Color.RED};
		
		RadialGradientPaint paint = new RadialGradientPaint((float)mid.x, (float)mid.y, (float)(mid.distance(newMinPoint)*(6.0/10.0)), dist, colors);
		return paint;
	}
	
	protected void translate(List<FractalShape> shapes, MyPoint point)
	{
		
	}
	
	public List<FractalShape> getFractal()
	{
		triangle.findMinMax();
		MyPoint min = triangle.getMinPoint();
		MyPoint max = triangle.getMaxPoint();
		List<FractalShape> branches = triangle.getFractal();
		
		int x;
		int y;
		int treeWidth = (int)(max.x - min.x);
		int treeHeight = (int)(max.y - min.y);
		
		square.findMinMax();
		x = (int)(min.x + (treeWidth/2) - (3*treeWidth/20));
		y = (int)(max.y)-10;
		List<FractalShape> trunk = square.scalePoints((3*treeWidth/10), (3*treeWidth/10), x, y);
		
		flake.findMinMax();
		x = (int)(min.x + (treeWidth/2) - (treeWidth/8));// - ((flake.getMaxPoint().x - flake.getMinPoint().x)/2));
		y = (int)min.y - (treeWidth/8);
		List<FractalShape> star = flake.scalePoints(treeWidth/4, treeWidth/4, x, y);
		
		gasket.findMinMax();
		x = (int)(min.x - (treeWidth/12) + (treeWidth/6));
		y = (int)(max.y - (treeWidth/12) - (1*treeHeight/10));
		List<FractalShape> ball1 = gasket.scalePoints(treeWidth/6, treeWidth/6, x, y);
		
		x = (int)(min.x - (treeWidth/12) + (3.10*treeWidth/6));
		y = (int)(max.y - (treeWidth/12) - (2.2*treeHeight/10));
		List<FractalShape> ball2 = gasket.scalePoints(treeWidth/6, treeWidth/6, x, y);
		
		
		x = (int)(min.x - (treeWidth/12) + (4.90*treeWidth/6));
		y = (int)(max.y - (treeWidth/12) - (1.4*treeHeight/10));
		List<FractalShape> ball3 = gasket.scalePoints(treeWidth/6, treeWidth/6, x, y);
		
		x = (int)(min.x - (treeWidth/12) + (2.15*treeWidth/6));
		y = (int)(max.y - (treeWidth/12) - (5.1*treeHeight/10));
		List<FractalShape> ball4 = gasket.scalePoints(treeWidth/6, treeWidth/6, x, y);
		
		x = (int)(min.x - (treeWidth/12) + (3.50*treeWidth/6));
		y = (int)(max.y - (treeWidth/12) - (6.50*treeHeight/10));
		List<FractalShape> ball5 = gasket.scalePoints(treeWidth/6, treeWidth/6, x, y);
		
		List<FractalShape> tree = new LinkedList<FractalShape>();
		tree.addAll(branches);
		tree.addAll(trunk);
		tree.addAll(star);
		tree.addAll(ball1);
		tree.addAll(ball2);
		tree.addAll(ball3);
		tree.addAll(ball4);
		tree.addAll(ball5);
		
		
		return tree;
	}
	
	public void clearFractal()
	{
		createBase();
	}
	
	protected void createBase()
	{
		triangle.clearFractal();
		triangle.createBase();
		
		square.clearFractal();
		square.createBase();
		
		flake.clearFractal();
		flake.createBase();
		
		gasket.clearFractal();
		gasket.createBase();
	}
	
	public void next()
	{
		triangle.next();
		square.next();
		flake.next();
		gasket.next();
	}
	
	public String toString()
	{
		return "Christmas";
	}
	
	public static void main(String[] args)
	{
		SimpleFractal.main(args);
	}
}

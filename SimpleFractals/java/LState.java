public class LState {
	double curAngle;
	double turningAngle;
	double radius;
	double scaleFactor;
	char plus;
	char minus;
	MyPoint p1;
	MyPoint p2;

	public LState(
			double curAngle,
			double turningAngle,
			double radius,
			double scaleFactor,
			char plus,
			char minus,
			MyPoint p1,
			MyPoint p2) {
		this.curAngle = curAngle;
		this.turningAngle = turningAngle;
		this.radius = radius;
		this.scaleFactor = scaleFactor;
		this.plus = plus;
		this.minus = minus;
		this.p1 = p1;
		this.p2 = p2;
	}
}

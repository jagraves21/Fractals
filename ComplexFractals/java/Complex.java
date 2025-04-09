public class Complex implements Comparable<Complex> {

	public double re;
	public double im;

	public Complex(double re, double im) {
		this.re = re;
		this.im = im;
	}

	public boolean isZero() {
		return re == 0 && im == 0;
	}

	public boolean isOne() {
		return re == 1 && im == 0;
	}

	public double modulus() {
		return Math.sqrt(modulusSquared());
	}

	public double modulusSquared() {
		return re*re + im*im;
	}

	public double distance(Complex that) {
		return Math.sqrt(distanceSquare(that));
	}

	public double distanceSquare(Complex that) {
		double nRE = this.re - that.re;
		double nIM = this.im - that.im;

		return nRE*nRE + nIM*nIM;
	}

	public double arg() {
		return Math.atan2(im,re);
	}

	public Complex conjugate() {
		return new Complex(re,-im);
	}

	public Complex plus(double re) {
		return new Complex(this.re + re, this.im);
	}

	public Complex plus(Complex that) {
		return new Complex(this.re + that.re, this.im + that.im);
	}

	public Complex minus(double re) {
		return new Complex(this.re - re, this.im);
	}

	public Complex minus(Complex that) {
		return new Complex(this.re - that.re, this.im - that.im);
	}

	public Complex times(double re) {
		return new Complex(this.re * re, this.im * re);
	}

	public Complex times(Complex that) {
		return new Complex(this.re * that.re - this.im * that.im, this.im * that.re + this.re * that.im);
	}

	public Complex divide(double re) {
		if(re == 0) {
			throw new ArithmeticException("division by zero");
		}

		return new Complex(this.re / re, this.im / re);
	}

	public Complex divide(Complex that) {
		if(that.isZero()) {
			throw new ArithmeticException("division by zero");
		}

		double d = that.re * that.re + that.im * that.im;
		return new Complex((this.re * that.re + this.im * that.im)/d, (this.im*that.re - this.re*that.im)/d);
	}

	public Complex exp() {
		return new Complex(Math.exp(re)*Math.cos(im), Math.exp(re)*Math.sin(im));
	}

	public Complex log() {
		return new Complex(Math.log(this.modulus()),this.arg());
	}

	public Complex sqrt() {
		double r = Math.sqrt(modulus());
		double theta = arg()/2;
		return new Complex(r*Math.cos(theta), r*Math.sin(theta));
	}

	public Complex sin() {
		return new Complex(cosh(im)*Math.sin(re),sinh(im)*Math.cos(re));
	}

	public Complex cos() {
		return new Complex(cosh(im)*Math.cos(re),-sinh(im)*Math.sin(re));
	}

	public Complex tan() {
		return (this.sin()).divide(this.cos());
	}

	private double cosh(double theta) {
		return (Math.exp(theta)+Math.exp(-theta))/2;
	}

	public Complex cosh() {
		return new Complex(cosh(re)*Math.cos(im),sinh(re)*Math.sin(im));
	}

	private double sinh(double theta) {
		return (Math.exp(theta)-Math.exp(-theta))/2;
	}

	public Complex sinh() {
		return new Complex(sinh(re)*Math.cos(im),cosh(re)*Math.sin(im));
	}

	public Complex rotate(double theta) {
		double x = Math.cos(theta) * this.re - Math.sin(theta) * this.im;
		double y = Math.sin(theta) * this.re + Math.cos(theta) * this.im;
		return new Complex(x,y);
	}

	public Complex rotate(double theta, Complex that) {
		double x = Math.cos(theta) * (this.re-that.re) - Math.sin(theta) * (this.im-that.im) + that.re;
		double y = Math.sin(theta) * (this.re-that.re) + Math.cos(theta) * (this.im-that.im) + that.im;
		return new Complex(x,y);
	}

	public Complex power(int power) {
		double re = this.re;
		double im = this.im;
		double retemp;

		/*double negativePower = false;
		if(power < 0) {
			power *= -1;
			negativePower = true;
		}*/

		power--;

		for(int ii=0; ii < power; ii++) {
			retemp = re * this.re - im * this.im;
			im = re * this.im + im * this.re;
			re = retemp;
		}

		return new Complex(re, im);
	}

	public int compareTo(Complex that) {
		int res = Double.compare(this.re, that.re);
		if(res == 0) {
			res = Double.compare(this.im, that.im);
		}

		return res;
	}

	public String toString() {
		if(re != 0 && im != 0) {
			if(im > 0) {
				return "(" + re + " + " + im + "i)";
			}
			else {
				return "(" + re + " - " + (-1*im) + "i)";
			}
		}
		else if(re == 0) {
			if(im == 0) {
				return "0";
			}
			else {
				return im + "i";
			}
		}
		else {
			return "" + re;
		}
	}
}

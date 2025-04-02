public abstract class AbstractFractal {
	protected static final String memory = "Ran out of memeory.\nTry runing with -Xmx1g option.\n";

	public static final double WIDTH = 1000;
	public static final double HEIGHT = 1000;

	public static final double r5 = (5 * Math.PI / 180.0);
	public static final double s5 = Math.sin(r5);
	public static final double c5 = Math.cos(r5);
	public static final double t5 = Math.tan(r5);

	public static final double r10 = (10 * Math.PI / 180.0);
	public static final double s10 = Math.sin(r10);
	public static final double c10 = Math.cos(r10);
	public static final double t10 = Math.tan(r10);

	public static final double r15 = (15 * Math.PI / 180.0);
	public static final double s15 = Math.sin(r15);
	public static final double c15 = Math.cos(r15);
	public static final double t15 = Math.tan(r15);

	public static final double r20 = (20 * Math.PI / 180.0);
	public static final double s20 = Math.sin(r20);
	public static final double c20 = Math.cos(r20);
	public static final double t20 = Math.tan(r20);

	public static final double r25 = (25 * Math.PI / 180.0);
	public static final double s25 = Math.sin(r25);
	public static final double c25 = Math.cos(r25);
	public static final double t25 = Math.tan(r25);

	public static final double r30 = (30 * Math.PI / 180.0);
	public static final double s30 = Math.sin(r30);
	public static final double c30 = Math.cos(r30);
	public static final double t30 = Math.tan(r30);

	public static final double r35 = (35 * Math.PI / 180.0);
	public static final double s35 = Math.sin(r35);
	public static final double c35 = Math.cos(r35);
	public static final double t35 = Math.tan(r35);

	public static final double r40 = (40 * Math.PI / 180.0);
	public static final double s40 = Math.sin(r40);
	public static final double c40 = Math.cos(r40);
	public static final double t40 = Math.tan(r40);

	public static final double r45 = (45 * Math.PI / 180.0);
	public static final double s45 = Math.sin(r45);
	public static final double c45 = Math.cos(r45);
	public static final double t45 = Math.tan(r45);

	public static final double r50 = (50 * Math.PI / 180.0);
	public static final double s50 = Math.sin(r50);
	public static final double c50 = Math.cos(r50);
	public static final double t50 = Math.tan(r50);

	public static final double r55 = (55 * Math.PI / 180.0);
	public static final double s55 = Math.sin(r55);
	public static final double c55 = Math.cos(r55);
	public static final double t55 = Math.tan(r55);

	public static final double r60 = (60 * Math.PI / 180.0);
	public static final double s60 = Math.sin(r60);
	public static final double c60 = Math.cos(r60);
	public static final double t60 = Math.tan(r60);

	public static final double r65 = (65 * Math.PI / 180.0);
	public static final double s65 = Math.sin(r65);
	public static final double c65 = Math.cos(r65);
	public static final double t65 = Math.tan(r65);

	public static final double r70 = (70 * Math.PI / 180.0);
	public static final double s70 = Math.sin(r70);
	public static final double c70 = Math.cos(r70);
	public static final double t70 = Math.tan(r70);

	public static final double r75 = (75 * Math.PI / 180.0);
	public static final double s75 = Math.sin(r75);
	public static final double c75 = Math.cos(r75);
	public static final double t75 = Math.tan(r75);

	public static final double r80 = (80 * Math.PI / 180.0);
	public static final double s80 = Math.sin(r80);
	public static final double c80 = Math.cos(r80);
	public static final double t80 = Math.tan(r80);

	public static final double r85 = (85 * Math.PI / 180.0);
	public static final double s85 = Math.sin(r85);
	public static final double c85 = Math.cos(r85);
	public static final double t85 = Math.tan(r85);

	public static final double r90 = (90 * Math.PI / 180.0);
	public static final double s90 = Math.sin(r90);
	public static final double c90 = Math.cos(r90);
	public static final double t90 = Math.tan(r90);

	public static final double r95 = (95 * Math.PI / 180.0);
	public static final double s95 = Math.sin(r95);
	public static final double c95 = Math.cos(r95);
	public static final double t95 = Math.tan(r95);

	public static final double r100 = (100 * Math.PI / 180.0);
	public static final double s100 = Math.sin(r100);
	public static final double c100 = Math.cos(r100);
	public static final double t100 = Math.tan(r100);

	public static final double r105 = (105 * Math.PI / 180.0);
	public static final double s105 = Math.sin(r105);
	public static final double c105 = Math.cos(r105);
	public static final double t105 = Math.tan(r105);

	public static final double r110 = (110 * Math.PI / 180.0);
	public static final double s110 = Math.sin(r110);
	public static final double c110 = Math.cos(r110);
	public static final double t110 = Math.tan(r110);

	public static final double r115 = (115 * Math.PI / 180.0);
	public static final double s115 = Math.sin(r115);
	public static final double c115 = Math.cos(r115);
	public static final double t115 = Math.tan(r115);

	public static final double r120 = (120 * Math.PI / 180.0);
	public static final double s120 = Math.sin(r120);
	public static final double c120 = Math.cos(r120);
	public static final double t120 = Math.tan(r120);

	public static final double r125 = (125 * Math.PI / 180.0);
	public static final double s125 = Math.sin(r125);
	public static final double c125 = Math.cos(r125);
	public static final double t125 = Math.tan(r125);

	public static final double r130 = (130 * Math.PI / 180.0);
	public static final double s130 = Math.sin(r130);
	public static final double c130 = Math.cos(r130);
	public static final double t130 = Math.tan(r130);

	public static final double r135 = (135 * Math.PI / 180.0);
	public static final double s135 = Math.sin(r135);
	public static final double c135 = Math.cos(r135);
	public static final double t135 = Math.tan(r135);

	public static final double r140 = (140 * Math.PI / 180.0);
	public static final double s140 = Math.sin(r140);
	public static final double c140 = Math.cos(r140);
	public static final double t140 = Math.tan(r140);

	public static final double r145 = (145 * Math.PI / 180.0);
	public static final double s145 = Math.sin(r145);
	public static final double c145 = Math.cos(r145);
	public static final double t145 = Math.tan(r145);

	public static final double r150 = (150 * Math.PI / 180.0);
	public static final double s150 = Math.sin(r150);
	public static final double c150 = Math.cos(r150);
	public static final double t150 = Math.tan(r150);

	public static final double r155 = (155 * Math.PI / 180.0);
	public static final double s155 = Math.sin(r155);
	public static final double c155 = Math.cos(r155);
	public static final double t155 = Math.tan(r155);

	public static final double r160 = (160 * Math.PI / 180.0);
	public static final double s160 = Math.sin(r160);
	public static final double c160 = Math.cos(r160);
	public static final double t160 = Math.tan(r160);

	public static final double r165 = (165 * Math.PI / 180.0);
	public static final double s165 = Math.sin(r165);
	public static final double c165 = Math.cos(r165);
	public static final double t165 = Math.tan(r165);

	public static final double r170 = (170 * Math.PI / 180.0);
	public static final double s170 = Math.sin(r170);
	public static final double c170 = Math.cos(r170);
	public static final double t170 = Math.tan(r170);

	public static final double r175 = (175 * Math.PI / 180.0);
	public static final double s175 = Math.sin(r175);
	public static final double c175 = Math.cos(r175);
	public static final double t175 = Math.tan(r175);

	public static final double r180 = (180 * Math.PI / 180.0);
	public static final double s180 = Math.sin(r180);
	public static final double c180 = Math.cos(r180);
	public static final double t180 = Math.tan(r180);

	public static final double r185 = (185 * Math.PI / 180.0);
	public static final double s185 = Math.sin(r185);
	public static final double c185 = Math.cos(r185);
	public static final double t185 = Math.tan(r185);

	public static final double r190 = (190 * Math.PI / 180.0);
	public static final double s190 = Math.sin(r190);
	public static final double c190 = Math.cos(r190);
	public static final double t190 = Math.tan(r190);

	public static final double r195 = (195 * Math.PI / 180.0);
	public static final double s195 = Math.sin(r195);
	public static final double c195 = Math.cos(r195);
	public static final double t195 = Math.tan(r195);

	public static final double sqrt2 = Math.sqrt(2);

	public String toString() {
		return "Abstract Fractal";
	}
}

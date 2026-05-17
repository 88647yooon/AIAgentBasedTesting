import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Triangle. The main function takes 3 positive whole-number lengths
 * to be typed in as command line arguments. The program responds
 * with a description of the triangle, as follows:
 *
 * <ul>
 *  <li><b>equilateral</b> - if all three sides have equal length
 *  <li><b>isosceles</b> - if two sides have equal length
 *  <li><b>right-angled</b> - if one angle is a right angle
 *  <li><b>scalene</b> - all sides different lengths, no right angles
 *  <li><b>impossible</b> - if the given side lengths do not form a triangle
 * </ul>
 * Area and perimeter of the triangle are calculated, too.
 *
 * @author Mikko Rusama, SoberIT
 * @version 26.8.2004
 */
public class Triangle {

	private static final Logger LOGGER = Logger.getLogger(Triangle.class.getName());
	private static final String USAGE_MESSAGE = "Usage: java Triangle <side1:int> <side2:int> <side3:int>";

	private int side1;
	private int side2;
	private int side3;

	private static final String P_EQUILATERAL = "equilateral";
	private static final String P_ISOSCELES   = "isossceles";
	private static final String P_RIGHTANGLED = "right-angled";
	private static final String P_SCALENE     = "scalene";
	private static final String P_IMPOSSIBLE  = "impossible";

	/**
	 * Constructor (without error checking)
	 * @param s1 length of the side1 as an integer.
	 * @param s2 length of the side2 as an integer.
	 * @param s3 length of the side3 as an integer.
	 */
	public Triangle(int s1, int s2, int s3) {
		this.side1 = s1;
		this.side2 = s2;
		this.side3 = s3;
	}

	/**
	 * Sets the lengths of the sides of this triangle.
	 * @param s1 length of the side1
	 * @param s2 length of the side2
	 * @param s3 length of the side3
	 * @return a reference to this triangle.
	 */
	public Triangle setSideLengths(int s1, int s2, int s3) {
		this.side1 = s1;
		this.side2 = s2;
		this.side3 = s3;
		return this;
	}

	/**
	 * Gets the side lengths.
	 * @return a comma separated list of side lengths
	 */
	public String getSideLengths() {
		return side1 + "," + side2 + "," + side3;
	}

	/**
	 * Gets the perimeter of the triangle.
	 * @return -1 if input values are invalid, otherwise the perimeter.
	 */
	public long getPerimeter() {  
		return (long) side1 + side2 + side3;
	}

	/**
	 * Gets the area of the triangle.
	 * @return area of the triangle, -1.0 if triangle is impossible.
	 */
	public double getArea() {
		if (isImpossible()) {
			return -1.0;
		}
		
		double s = getPerimeter() / 2.0;
		return Math.sqrt(s * (s - side1) * (s - side2) * (s - side3));
	}

	/**
	 * Classifies the triangle. Possible types, returned as a string, are:
	 * <ul>
	 *  <li>equilateral - if all three sides have equal length
	 *  <li>isosceles - if two sides have equal length
	 *  <li>right-angled - if one angle is a right angle
	 *  <li>scalene - all sides different lengths, no right angles
	 *  <li>impossible - if the lengths can't form a triangle
	 * </ul>
	 * @return type of the triangle as a string.
	 */
	public String classify() {
		if (isImpossible()) {
			return P_IMPOSSIBLE;
		}
		if (side1 == side2 && side2 == side3) {
			return P_EQUILATERAL;
		}
		if (side1 == side2 || side2 == side3 || side1 == side3) {
			return P_ISOSCELES;
		}
		if (isRightAngled()) {
			return P_RIGHTANGLED;
		}
		
		return P_SCALENE;
	}
	
	/**
	 * Checks if the triangle is right-angled. Note: right-angled triangle may
	 * also be isosceles.
	 * @return true if one angle is a right angle, otherwise false.
	 */
	public boolean isRightAngled() {
		long a = (long) side1 * side1;
		long b = (long) side2 * side2;
		long c = (long) side3 * side3;
		
		return (a + b == c) || (a + c == b) || (b + c == a);
	}
	
	/**
	 * Checks whether the given side lengths form a
	 * valid triangle.
	 * @return true if the given side lengths do not form a triangle.
	 */
	public boolean isImpossible() {
		if (side1 <= 0 || side2 <= 0 || side3 <= 0) {
			return true;
		}
		return (long) side1 + side2 <= side3 
		    || (long) side1 + side3 <= side2 
		    || (long) side2 + side3 <= side1;
	}

	/**
	 * Usage: java Triangle &lt;side1:int&gt; &lt;side2:int&gt; &lt;side3:int&gt;
	 * <p>Main method is only used for testing purposes, no unit tests need to
	 * be written for this method.</p>
	 */
	public static void main(String[] args) {
		if (args.length < 3) {
			LOGGER.info(USAGE_MESSAGE);
			return;
		}

		try {
			Triangle triangle = new Triangle(
					Integer.parseInt(args[0]),
					Integer.parseInt(args[1]),
					Integer.parseInt(args[2]));
			
			if (LOGGER.isLoggable(Level.INFO)) {
				LOGGER.log(Level.INFO, "Type: {0}", triangle.classify());
				LOGGER.log(Level.INFO, "Triangle sides: {0}", triangle.getSideLengths());
				LOGGER.log(Level.INFO, "Area: {0}", triangle.getArea());
				LOGGER.log(Level.INFO, "Perimeter: {0}", triangle.getPerimeter());
			}
		} catch (NumberFormatException e) {
			LOGGER.info(USAGE_MESSAGE);
		}
	}
}

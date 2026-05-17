import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.*;

class TriangleTest {

    private static final String SCALENE = "scalene";
    private static final String ISOSCELES = "isossceles";
    private static final String IMPOSSIBLE = "impossible";
    private static final String EQUILATERAL = "equilateral";
    private static final String RIGHT_ANGLED = "right-angled";

    @ParameterizedTest(name = "Sides: {0}, {1}, {2} -> {3}")
    @CsvSource({
        "2, 3, 4, " + SCALENE,
        "2, 2, 3, " + ISOSCELES,
        "0, 0, 0, " + IMPOSSIBLE,
        "3, 3, 3, " + EQUILATERAL,
        "3, 4, 5, " + RIGHT_ANGLED,
        "10, 11, 12, " + SCALENE,
        "10, 1, 1, " + IMPOSSIBLE,
        "3, 4, 4, " + ISOSCELES,
        "4, 3, 4, " + ISOSCELES,
        "-1, 5, 5, " + IMPOSSIBLE,
        "2147483647, 2147483647, 2147483647, " + EQUILATERAL
    })
    void testClassify(int s1, int s2, int s3, String expected) {
        Triangle t = new Triangle(s1, s2, s3);
        assertEquals(expected, t.classify());
    }

    @ParameterizedTest(name = "Sides: {0}, {1}, {2} -> isImpossible={3}")
    @CsvSource({
        "0, 5, 5, true",
        "2, 2, 3, false",
        "4, 4, 4, false",
        "10, 11, 12, false",
        "3, 4, 5, false",
        "0, 0, 0, true",
        "0, 0, 1, true",
        "0, 1, 0, true",
        "1, 0, 0, true",
        "0, 1, 1, true",
        "1, 1, 0, true",
        "1, 0, 1, true",
        "1, 1, 1, false",
        "2, 3, 10, true",
        "2, 10, 3, true",
        "10, 2, 3, true",
        "-1, 5, 5, true",
        "2147483647, 2147483647, 2147483647, false"
    })
    void testIsImpossible(int s1, int s2, int s3, boolean expected) {
        Triangle t = new Triangle(s1, s2, s3);
        assertEquals(expected, t.isImpossible());
    }

    @ParameterizedTest(name = "Sides: {0}, {1}, {2} -> isRightAngled={3}")
    @CsvSource({
        "3, 4, 5, true",
        "3, 5, 4, true",
        "5, 3, 4, true",
        "2, 3, 4, false"
    })
    void testIsRightAngled(int s1, int s2, int s3, boolean expected) {
        Triangle t = new Triangle(s1, s2, s3);
        assertEquals(expected, t.isRightAngled());
    }

    @Test
    void testOtherMethods() {
        Triangle t = new Triangle(3, 4, 5);
        assertEquals("3,4,5", t.getSideLengths());
        assertEquals(12L, t.getPerimeter());
        assertEquals(6.0, t.getArea(), 0.001); // 정상 삼각형의 면적 (Decision: False 분기)
        
        t.setSideLengths(2, 2, 10);
        assertEquals(-1.0, t.getArea(), 0.001); // 불가능한 삼각형의 면적 (Decision: True 분기)
    }

    @Test
    void testArea_DecimalSemiPerimeter() {
        Triangle t = new Triangle(2, 2, 3);
        assertEquals(1.984313, t.getArea(), 0.000001);
    }

    @Test
    void testSetSideLengths_ReturnsSelf() {
        Triangle t = new Triangle(1, 1, 1);
        assertSame(t, t.setSideLengths(3, 4, 5));
    }

    @Test
    void testBoundary_PerimeterAndAreaOverflow() {
        Triangle t = new Triangle(Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE);
        assertEquals(6442450941L, t.getPerimeter(), "Perimeter는 3 * Integer.MAX_VALUE 여야 함");
        assertTrue(t.getArea() > 0, "오버플로우로 인해 넓이가 잘못 계산되면 안 됨");
    }
}

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TriangleTest {

    // =========================================================================
    // 1. 삼각형 유형 분류 테스트 (Equivalence/Boundary)
    // =========================================================================
    
    @Test
    public void testClassification_TC1() {
        Triangle t = new Triangle(2, 3, 4);
        assertEquals("scalene", t.classify());
    }

    @Test
    public void testClassification_TC2() {
        Triangle t = new Triangle(2, 2, 3);
        assertEquals("isossceles", t.classify()); // 요구사항에 맞게 'isossceles' (원래 코드의 오타와 동일)
    }

    @Test
    public void testClassification_TC3() {
        Triangle t = new Triangle(0, 0, 0);
        assertEquals("impossible", t.classify());
    }

    @Test
    public void testClassification_TC4() {
        Triangle t = new Triangle(3, 3, 3);
        assertEquals("equilateral", t.classify());
    }

    @Test
    public void testClassification_TC5() {
        Triangle t = new Triangle(3, 4, 5);
        assertEquals("right-angled", t.classify());
    }

    @Test
    public void testClassification_TC6() {
        Triangle t = new Triangle(10, 11, 12);
        assertEquals("scalene", t.classify());
    }

    @Test
    public void testClassification_TC7() {
        Triangle t = new Triangle(10, 1, 1);
        assertEquals("impossible", t.classify());
    }

    // =========================================================================
    // 2. 삼각형 성립 여부 테스트 (isImpossible)
    // =========================================================================

    @Test
    public void testIsImpossible_TC1() {
        Triangle t = new Triangle(0, 5, 5);
        assertTrue(t.isImpossible());
    }

    @Test
    public void testIsImpossible_TC2() {
        Triangle t = new Triangle(2, 2, 3);
        assertFalse(t.isImpossible());
    }

    @Test
    public void testIsImpossible_TC3() {
        Triangle t = new Triangle(4, 4, 4);
        assertFalse(t.isImpossible());
    }

    @Test
    public void testIsImpossible_TC4() {
        Triangle t = new Triangle(10, 11, 12);
        assertFalse(t.isImpossible());
    }

    @Test
    public void testIsImpossible_TC5() {
        Triangle t = new Triangle(3, 4, 5);
        assertFalse(t.isImpossible());
    }

    // =========================================================================
    // 3. isImpossible() 메소드에 대한 MC/DC Pair 테스트케이스
    // (조건: side1<=0, side2<=0, side3<=0)
    // =========================================================================

    @Test
    public void testMcdc_TC1() {
        // T, T, T -> output: T
        Triangle t = new Triangle(0, 0, 0);
        assertTrue(t.isImpossible());
    }

    @Test
    public void testMcdc_TC2() {
        // T, T, F -> output: T
        Triangle t = new Triangle(0, 0, 1);
        assertTrue(t.isImpossible());
    }

    @Test
    public void testMcdc_TC3() {
        // T, F, T -> output: T
        Triangle t = new Triangle(0, 1, 0);
        assertTrue(t.isImpossible());
    }

    @Test
    public void testMcdc_TC4() {
        // F, T, T -> output: T
        Triangle t = new Triangle(1, 0, 0);
        assertTrue(t.isImpossible());
    }

    @Test
    public void testMcdc_TC5() {
        // T, F, F -> output: T
        Triangle t = new Triangle(0, 1, 1);
        assertTrue(t.isImpossible());
    }

    @Test
    public void testMcdc_TC6() {
        // F, F, T -> output: T
        Triangle t = new Triangle(1, 1, 0);
        assertTrue(t.isImpossible());
    }

    @Test
    public void testMcdc_TC7() {
        // F, T, F -> output: T
        Triangle t = new Triangle(1, 0, 1);
        assertTrue(t.isImpossible());
    }

    @Test
    public void testMcdc_TC8() {
        // F, F, F -> output: F
        Triangle t = new Triangle(1, 1, 1);
        assertFalse(t.isImpossible());
    }

    // =========================================================================
    // 4. isImpossible() 두 번째 조건식(삼각형 성립 조건)에 대한 MC/DC
    // 조건: (side1 + side2 <= side3) || (side1 + side3 <= side2) || (side2 + side3 <= side1)
    // =========================================================================

    @Test
    public void testIsImpossible_Inequality_Mcdc1() {
        // T, F, F -> output: T
        Triangle t = new Triangle(2, 3, 10);
        assertTrue(t.isImpossible());
    }

    @Test
    public void testIsImpossible_Inequality_Mcdc2() {
        // F, T, F -> output: T
        Triangle t = new Triangle(2, 10, 3);
        assertTrue(t.isImpossible());
    }

    @Test
    public void testIsImpossible_Inequality_Mcdc3() {
        // F, F, T -> output: T
        Triangle t = new Triangle(10, 2, 3);
        assertTrue(t.isImpossible());
    }

    // F, F, F (모두 만족하는 정상 삼각형)는 기존 testIsImpossible_TC2(2, 2, 3) 등이 커버함.

    // =========================================================================
    // 5. isRightAngled() 조건식에 대한 MC/DC
    // 조건: (a+b==c) || (a+c==b) || (b+c==a)
    // =========================================================================

    @Test
    public void testIsRightAngled_Mcdc1() {
        // T, F, F -> output: T (side3이 빗변)
        Triangle t = new Triangle(3, 4, 5);
        assertTrue(t.isRightAngled());
    }

    @Test
    public void testIsRightAngled_Mcdc2() {
        // F, T, F -> output: T (side2가 빗변)
        Triangle t = new Triangle(3, 5, 4);
        assertTrue(t.isRightAngled());
    }

    @Test
    public void testIsRightAngled_Mcdc3() {
        // F, F, T -> output: T (side1이 빗변)
        Triangle t = new Triangle(5, 3, 4);
        assertTrue(t.isRightAngled());
    }

    @Test
    public void testIsRightAngled_Mcdc4() {
        // F, F, F -> output: F (직각삼각형 아님)
        Triangle t = new Triangle(2, 3, 4);
        assertFalse(t.isRightAngled());
    }

    // =========================================================================
    // 6. 기타 메서드 Statement & Decision Coverage
    // =========================================================================

    @Test
    public void testOtherMethods() {
        Triangle t = new Triangle(3, 4, 5);
        assertEquals("3,4,5", t.getSideLengths());
        assertEquals(12L, t.getPerimeter());
        assertEquals(6.0, t.getArea(), 0.001); // 정상 삼각형의 면적 (Decision: False 분기)
        
        t.setSideLengths(2, 2, 10);
        assertEquals(-1.0, t.getArea(), 0.001); // 불가능한 삼각형의 면적 (Decision: True 분기)
    }

    // =========================================================================
    // 7. classify() 메서드 Decision Coverage 심화 (이등변 삼각형 누락된 분기 검증)
    // =========================================================================

    @Test
    public void testClassify_Isosceles_Side2And3() {
        Triangle t = new Triangle(3, 4, 4);
        assertEquals("isossceles", t.classify()); // side2 == side3인 이등변 삼각형
    }

    @Test
    public void testClassify_Isosceles_Side1And3() {
        Triangle t = new Triangle(4, 3, 4);
        assertEquals("isossceles", t.classify()); // side1 == side3인 이등변 삼각형
    }

    // =========================================================================
    // 8. 극한 경계값 및 견고성(Robustness) 테스트
    // =========================================================================

    @Test
    public void testRobustness_NegativeSides() {
        // 정확히 0이 아닌 음수(-1)가 들어갔을 때의 동작 검증
        Triangle t = new Triangle(-1, 5, 5);
        assertTrue(t.isImpossible());
        assertEquals("impossible", t.classify());
    }

    @Test
    public void testArea_DecimalSemiPerimeter() {
        // 둘레가 홀수(7)여서 반둘레(s)가 3.5인 경우 넓이 계산 정밀도 검증
        Triangle t = new Triangle(2, 2, 3); // 둘레 = 7, 반둘레(s) = 3.5
        // 넓이 = sqrt(3.5 * 1.5 * 1.5 * 0.5) = sqrt(3.9375) ≈ 1.984313
        assertEquals(1.984313, t.getArea(), 0.000001);
    }

    @Test
    public void testSetSideLengths_ReturnsSelf() {
        // setSideLengths가 this를 올바르게 반환하여 체이닝이 가능한지 검증
        Triangle t = new Triangle(1, 1, 1);
        assertSame(t, t.setSideLengths(3, 4, 5));
    }

    @Test
    public void testBoundary_IntegerOverflow() {
        // int의 최대값을 넣었을 때 덧셈(side1 + side2) 오버플로우가 발생해
        // 잘못된 삼각형(impossible)으로 판별되는 현상을 방지하는지 검증
        Triangle t = new Triangle(Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE);
        assertFalse(t.isImpossible(), "오버플로우로 인해 정상 삼각형이 impossible로 판별됨");
        assertEquals("equilateral", t.classify());
    }

    @Test
    public void testBoundary_PerimeterAndAreaOverflow() {
        // int의 최대값을 변의 길이로 가질 때 둘레(getPerimeter) 합산 과정에서
        // 오버플로우가 발생하여 음수가 반환되는 현상을 방지하는지 검증
        Triangle t = new Triangle(Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE);
        assertEquals(6442450941L, t.getPerimeter(), "Perimeter는 3 * Integer.MAX_VALUE 여야 함");
        assertTrue(t.getArea() > 0, "오버플로우로 인해 넓이가 잘못 계산되면 안 됨");
    }

    // =========================================================================
    // 9. getTypeFlags() 메서드 테스트 (Phase 6 - Task 6.1)
    // =========================================================================

    @Test
    public void testGetTypeFlags_Equilateral() {
        Triangle t = new Triangle(3, 3, 3);
        assertEquals("equilateral", t.getTypeFlags());
    }

    @Test
    public void testGetTypeFlags_Isosceles() {
        Triangle t = new Triangle(3, 3, 4);
        assertEquals("isossceles", t.getTypeFlags());
    }

    @Test
    public void testGetTypeFlags_RightAngledAndScalene() {
        Triangle t = new Triangle(3, 4, 5);
        assertEquals("right-angled, scalene", t.getTypeFlags());
    }

    @Test
    public void testGetTypeFlags_Scalene() {
        Triangle t = new Triangle(4, 5, 6);
        assertEquals("scalene", t.getTypeFlags());
    }

    @Test
    public void testGetTypeFlags_Impossible() {
        Triangle t = new Triangle(0, 0, 0);
        assertEquals("impossible", t.getTypeFlags());
    }

    @Test
    public void testGetTypeFlags_BoundaryMax() {
        Triangle t = new Triangle(Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE);
        assertEquals("equilateral", t.getTypeFlags());
    }

    @Test
    public void testGetTypeFlags_MockingRightAngled() {
        // Mocking을 포함한 단위 테스트 (수동 Mock 객체 사용)
        // isRightAngled()를 true로 강제 반환하도록 오버라이드 (Mocking)
        Triangle mockTriangle = new Triangle(2, 2, 3) {
            @Override
            public boolean isRightAngled() {
                return true;
            }
        };
        // 원래 2,2,3은 직각삼각형이 아니지만, Mocking으로 인해 직각삼각형 속성이 포함되어야 함
        assertEquals("right-angled, isossceles", mockTriangle.getTypeFlags());
    }

    // =========================================================================
    // 10. 통합 테스트 (Integration Testing) - Phase 6 Task 6.2
    // 메인 메서드 실행 흐름과 콘솔 출력 결과 연동 검증
    // =========================================================================

    private String getConsoleOutput(Runnable runnable) {
        java.io.ByteArrayOutputStream outContent = new java.io.ByteArrayOutputStream();
        java.io.PrintStream originalOut = System.out;
        System.setOut(new java.io.PrintStream(outContent));
        try {
            runnable.run();
        } finally {
            System.setOut(originalOut);
        }
        return outContent.toString();
    }

    @Test
    public void testIntegration_Main_RightAngledScalene() {
        
        String output = getConsoleOutput(() -> {
            Triangle.main(new String[]{"3", "4", "5"});
        });
        
        // main 메소드가 출력하는 전체 포맷 검증 (getTypeFlags 적용됨)
        assertTrue(output.contains("Type: right-angled, scalene"));
        assertTrue(output.contains("Triangle sides: 3,4,5"));
        assertTrue(output.contains("Area: 6.0"));
        assertTrue(output.contains("Perimeter: 12"));
    }

    @Test
    public void testIntegration_Main_InvalidArguments_Count() {
        // 통합 테스트: 인자 개수 부족
        String output = getConsoleOutput(() -> {
            Triangle.main(new String[]{"3", "4"});
        });
        assertTrue(output.contains("Usage: java Quadrangle <side1:int> <side2:int> <side3:int>"));
    }

    @Test
    public void testIntegration_Main_InvalidArguments_Type() {
        // 통합 테스트: 숫자가 아닌 문자열 입력
        String output = getConsoleOutput(() -> {
            Triangle.main(new String[]{"a", "b", "c"});
        });
        assertTrue(output.contains("Usage: java Quadrangle <side1:int> <side2:int> <side3:int>"));
    }

    @Test
    public void testIntegration_Main_Equilateral() {
        // 통합 테스트: 정삼각형
        String output = getConsoleOutput(() -> {
            Triangle.main(new String[]{"3", "3", "3"});
        });
        assertTrue(output.contains("Type: equilateral"));
        assertTrue(output.contains("Triangle sides: 3,3,3"));
    }

    @Test
    public void testIntegration_Main_Isosceles() {
        // 통합 테스트: 이등변삼각형
        String output = getConsoleOutput(() -> {
            Triangle.main(new String[]{"3", "3", "4"});
        });
        assertTrue(output.contains("Type: isossceles"));
        assertTrue(output.contains("Triangle sides: 3,3,4"));
    }

    @Test
    public void testIntegration_Main_Scalene() {
        // 통합 테스트: 일반 부등변삼각형
        String output = getConsoleOutput(() -> {
            Triangle.main(new String[]{"4", "5", "6"});
        });
        assertTrue(output.contains("Type: scalene"));
        assertTrue(output.contains("Triangle sides: 4,5,6"));
    }

    @Test
    public void testIntegration_Main_Impossible() {
        // 통합 테스트: 삼각형 성립 불가
        String output = getConsoleOutput(() -> {
            Triangle.main(new String[]{"1", "2", "3"});
        });
        assertTrue(output.contains("Type: impossible"));
        assertTrue(output.contains("Triangle sides: 1,2,3"));
    }
}

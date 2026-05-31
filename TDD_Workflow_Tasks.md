### AI Agent-Based TDD Workflow Automation Task List

#### Phase 1: 요구사항 분석 및 초기 테스트 설계 (Analyze & Plan)
- [ ] **Task 1.1: 요구사항(Specification) 파싱 및 분석**
  - 주어진 문제(예: 삼각형 분류 규칙, 넓이/둘레 계산)의 핵심 도메인 로직 추출.
  - 입력값의 제약 조건(예: 양의 정수) 및 출력 형태(String, double, long 등) 정의.
- [ ] **Task 1.2: 동치분할(Equivalence Partitioning) 및 경계값(Boundary Value) 테스트 케이스 도출**
  - 정상 동작 케이스 (정삼각형, 이등변삼각형, 직각삼각형, 부등변삼각형) 도출.
  - 비정상 동작 케이스 (삼각형 성립 불가 조건, 0 입력 등) 도출.
- [ ] **Task 1.3: 초기 테스트 코드 뼈대(Skeleton) 생성 (Red Phase)**
  - 도출된 케이스를 바탕으로 실패하는 초기 단위 테스트(`@Test`) 작성.

#### Phase 2: 초기 비즈니스 로직 구현 (Implement - Green Phase)
- [ ] **Task 2.1: 클래스 및 메서드 시그니처 생성**
  - 테스트 코드를 기반으로 대상 클래스(Target Class)와 필요 메서드 선언.
- [ ] **Task 2.2: 기본 기능 로직 구현**
  - 초기 단위 테스트를 통과할 수 있는 수준의 비즈니스 로직(분류, 계산 로직 등) 작성.
- [ ] **Task 2.3: 1차 테스트 실행 및 피드백 루프**
  - 테스트 실행 후 실패하는 케이스 분석 및 코드 수정 반복 (모든 기본 테스트 통과 목표).

#### Phase 3: 커버리지 기반 테스트 증강 (Coverage Augmentation)
- [ ] **Task 3.1: 구문(Statement) 및 결정(Decision) 커버리지 분석**
  - 현재 로직에서 누락된 분기(Branch) 탐색 (예: 이등변 삼각형의 `side1 == side3` 조건 등).
  - 누락된 분기를 실행시키는 테스트 케이스 추가 생성.
- [ ] **Task 3.2: 복합 조건식에 대한 MC/DC (Modified Condition/Decision Coverage) 테스트 생성**
  - `||`, `&&` 등으로 이루어진 복잡한 조건식 식별.
  - 각 개별 조건이 결과에 독립적으로 영향을 미치는지 확인하는 MC/DC 페어 테스트 작성.
    - *예: `side1 <= 0 || side2 <= 0 || side3 <= 0` 검증*
    - *예: 삼각형 부등식 조건식 검증*
    - *예: 직각삼각형 피타고라스 조건식 검증*

#### Phase 4: 견고성(Robustness) 및 엣지 케이스 처리
- [ ] **Task 4.1: 데이터 타입 한계 및 오버플로우 테스트 생성**
  - 입력값이 자료형의 최대치(`Integer.MAX_VALUE`)일 때 연산 과정에서 발생할 수 있는 오버플로우 검증 테스트 작성.
- [ ] **Task 4.2: 부동소수점 정밀도 및 극한 환경 테스트 생성**
  - 소수점 연산 시 정밀도 오차 확인 (예: 넓이 계산 시 반둘레(s)가 소수점인 경우).
  - 의도치 않은 음수(-1)나 쓰레기값 입력 시의 예외 처리 검증.
- [ ] **Task 4.3: 프로덕션 코드 리팩토링 (Refactor Phase)**
  - 엣지 케이스 테스트가 실패할 경우, 코드 수정 (예: 오버플로우 방지를 위해 내부 연산 시 `int`를 `long`으로 캐스팅).

#### Phase 5: 최종 검증 및 리포팅 (Finalize)
- [ ] **Task 5.1: 전체 테스트 스위트 회귀 테스트(Regression Test) 실행**
  - 리팩토링 후 기존 기능에 사이드 이펙트가 없는지 전체 테스트 실행.
- [ ] **Task 5.2: 최종 커버리지 측정 및 목표 달성 검증**
  - 구문, 분기, MC/DC 커버리지가 설정한 목표치(예: 100%)에 도달했는지 확인.
- [ ] **Task 5.3: 결과 리포트(Summary) 생성**
  - 작성된 테스트 케이스 수, 발견된 잠재적 버그, 커버리지 결과 등을 요약하여 출력.

#### Phase 6: 복수 속성 확인 기능(`getTypeFlags()`) 추가 및 통합 테스트
- **목표**: 기존 `classify()` 메서드는 삼각형의 대표 유형 하나만 반환하므로, 삼각형의 복수 속성을 함께 확인할 수 있도록 `getTypeFlags()` 기능을 추가합니다.
  - (3, 3, 3) : equilateral
  - (3, 3, 4) : isosceles
  - (3, 4, 5) : right-angled, scalene
  - (4, 5, 6) : scalene
- [x] **Task 6.1: `getTypeFlags()`에 대한 Unit Testing 추가**
  - Mocking을 포함하여 작성.
  - 가능한 경우의 삼각형과 경계를 확인하는 테스트 케이스 필수 포함.
- [x] **Task 6.2: Integration Testing을 위한 Test Case 작성**
  - 단위시험의 테스트 케이스 포함.
  - 통합시험을 위한 추가 테스트 케이스 반드시 추가.
- [x] **Task 6.3: Git Repository의 삼각형 판별 프로그램에 통합**
  - `getTypeFlags()` 기능을 `Triangle` 클래스에 구현 및 통합.
- [x] **Task 6.4: Integration Testing 수행 및 통과 확인**
  - 테스트 수행을 통한 Build Success 확인.
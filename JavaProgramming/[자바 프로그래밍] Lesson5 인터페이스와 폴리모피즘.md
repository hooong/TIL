# [자바 프로그래밍] Lesson5 인터페이스와 폴리모피즘

### 모든 과목에 대한 보고서

 앞서 `RosterRepoter`를 통해 과목을 신청한 학생들에 대한 보고서를 만들었다면 이번에는 모든 과목에 대한 보고서를 만들어본다. 우선, `CourseReportTest`를 만들어 보고서의 뼈대를 만들어준다.

```java
public class CourseReportTest extends TestCase {
    public void testReport() {
        final Date date = new Date();
        CourseReport report = new CourseReport();
        report.add(CourseSession.create("ENGL", "101", date));
        report.add(CourseSession.create("CZEC", "200", date));
        report.add(CourseSession.create("ITAL", "410", date));

        assertEquals(
                "ENGL 101" + NEWLINE +             
			          "CZEC 200" + NEWLINE +
          			"ITAL 410" + NEWLINE,
            report.text());
    }
}
```

> 여기서 NEWLINE은 `import static sis.report.RosterReporter.NEWLINE;`로 스태틱 변수를 import하면 위와 같이 사용할 수 있다.

  이제 위의 테스트를 통과시키기 위해 `CourseReport`를 만들어야한다. `RosterReporter`와 구조가 거의 비슷하다.

```java
public class CourseReport {
    private ArrayList<CourseSession> sessions = new ArrayList<>();

    public void add(CourseSession session) {
        sessions.add(session);
    }

    public String text() {
        StringBuilder stringBuilder = new StringBuilder();
        for (CourseSession session: sessions) {
            stringBuilder.append(
              	session.getDepartment() + " " +
              	session.getNumber() + NEWLINE);
        }	
        return stringBuilder.toString();
    }
}
```

> 아마 `getDepartment()`와 `getNumber()`가 public으로 지정이 되어있지 않을 것이다. 이를 public으로 지정해주어야 컴파일 에러를 피할 수 있다.

<br>

### 정렬

 과목의 이름을 알파벳 순으로 정렬하여 보고서에 넣으면 보기 좋을 것이다. 아래와 같이 테스트를 변경해보자.

```java
public class CourseReportTest extends TestCase {
    public void testReport() {
        final Date date = new Date();
        CourseReport report = new CourseReport();
        report.add(CourseSession.create("ENGL", "101", date));
        report.add(CourseSession.create("CZEC", "200", date));
        report.add(CourseSession.create("ITAL", "410", date));

        assertEquals(
                "CZEC 200" + NEWLINE +
                "ENGL 101" + NEWLINE +
                "ITAL 410" + NEWLINE,
            report.text());
    }
}
```

"CZEC"와 "ENGL"을 바꿔 사전순으로 정렬한 결과가 나오는지 확인을 해보면 테스트는 실패를 할 것이다. 이를 해결하기 위해서는 아래와 같이 `CourseReport`를 변경해야 한다. 

```java
public class CourseReport {
    private ArrayList<CourseSession> sessions = new ArrayList<>();

    public void add(CourseSession session) {
        sessions.add(session);
    }

    public String text() {
        Collections.sort(sessions);
        StringBuilder stringBuilder = new StringBuilder();
        for (CourseSession session: sessions) {
            stringBuilder.append(
                    session.getDepartment() + " " +
                            session.getNumber() + NEWLINE);
        }
        return stringBuilder.toString();
    }
}
```

 해당 코드에서 `Collections.sort(sessions);` 와 같이 정렬하는 코드를 넣어준다. 그러나 이는 컴파일 에러가 난다. 여기서의 문제는 `session`이라는 `CourseSession`이라는 타입을 비교할 수 없기때문이다. 즉, java.lang.Comparable형(다른 객체와 비교가 가능한 형)으로 지정이 안 되어있기 때문이다. 이를 해결하기 위해서는 인터페이스를 활용하는 방법이 있다. 

<br>

- #### Collections.sort()

  - Merge sort 알고리즘을 사용하여 정렬
  - 아래와 같이 String타입의 객체들이 담긴 Collection인 ArrayList를 정렬할 수 있다.

  ```java
  public void testSortStringsInPlace() {
      ArrayList<String> list = new ArrayList<>();
      list.add("Heller");
      list.add("Kafka");
      list.add("Camus");
      list.add("Boyle");
      
      Collections.sort(list);
      assertEquals("Boyle", list.get(0));
      assertEquals("Camus", list.get(1));
      assertEquals("Heller", list.get(2));
      assertEquals("Kafka", list.get(3));
  }
  ```

<br>

### 인터페이스 (interface)

 위에서 언급한 `Comparable`는 클래스가 아닌 인터페이스이다. 인터페이스는 여러 개의 메소드 선언을 포함한다. 이 메소드 선언은 중괄호를 사용하지 않으며 메소드 내부에 코드를 지정하지 않는다. 예로 Comparable형을 살펴보자.

```java
public interface Comparable<T> {
  public int compareTo(T o);
}
```

 class와 다르게 interface 키워드로 선언을 하며 내부에 `comparTo()`와 같이 구현내용을 포함하지 않고 선언을 한다. `Comparable`에서 `compareTo()`메소드의 목적은 어떤 객체가 다른 객체의 이전에 위치할지 이후에 위치할지를 확인하는 것이다. 예를들어 "A"와 "B" 중 "A"가 먼저 나와야 한다는 것을 의미하는 값(양수인지 음수인지를 확인) 을 반환한다. 

 또한 클래스는 인터페이스를 `implements`하도록 정의될 수 있다. 이 선언을 하면 클래스는 인터페이스에 정의된 각 메소드를 구현하고 있다는 것을 보장한다. 즉, 인터페에스를 구현하는 것은 클래스가 하나 이상의 형으로 동작할 수 있게 한다.

위에서 Collections.sort()의 예에서 String의 경우는 컴파일 에러 없이 잘 동작하는 것을 확인할 수 있다. 이것은 String 객체가 Comparalbe 인터페이스를 implements를 하였기때문이다.

```java
public final class String
    implements java.io.Serializable, Comparable<String>, CharSequence {
 		// ... 
}
```

<br>

 인터페이스는 자바의 강력하면서도 중요한 기능이다. 자바에 충실한 디자인을 하는 가장 중요한 요소 중 한가지로는 '언제 인터페이스를 사용할지(혹은 사용하지 말아야 할지)를 아는 것'이다. 인터페이스는 더 높은 단계의 추상화를 제공한다. 이러한 추상화는 중복을 줄이는 한가지의 방법이 될 수 있다. 즉, 인터페이스를 시스템에서 추상화된 계층을 만들고 중복을 없애기 위해서 사용하는 것이 좋다.

<br>

### Comparable 구현하기

 `compareTo()` 메소드는 반환 값이 0이면 같음, 음수인 경우 수신자(compareTo 메시지를 받는 객체)의 순서가 앞, 양수인 경우 인수의 순서가 앞이다. 즉, 코드로 표현하면 다음과 같다.

```java
public void testStringCompareTo() {
  assertTrue("A".compareTo("B") < 0);
  assertEquals(0, "A".compareTo("A"));
  assertTrue("B".compareTo("A") > 0);
}
```

 그럼 드디어 아까 session을 정렬하지 못하던 문제를 해결하기 위해 Comparable인터페이스를 구현해보자. 구현하기에 앞서 테스트를 먼저 작성하는 것을 잊지말자.

```java
public void testComparable() {
    final Date date = new Date();
    CourseSession sessionA = CourseSession.create("CMSC", "101", date);
    CourseSession sessionB = CourseSession.create("ENGL", "101", date);
    assertTrue(sessionA.compareTo(sessionB) < 0);
    assertTrue(sessionB.compareTo(sessionA) > 0);

    CourseSession sessionC = CourseSession.create("CMSC", "101", date);
    assertEquals(0, sessionA.compareTo(sessionC));
}
```

 `compareTo()`를 검증하기 위한 테스트 코드를 작성한 후 이를 성공시키기 위해 `CourseSession`이 `Comparable`을 구현한 것을 선언해주어야 한다.

```java
public class CourseSession implements Comparable<CourseSession>{
		// ...
    @Override
    public int compareTo(CourseSession that) {
        return this.getDepartment().compareTo(that.getDepartment());
    }
  	// ...
```

 `implements`키워드로 `Comparable`을 구현한다고 선언하고 `compareTo()`메소드를 오버라이드해주면 위에서 발생하던 컴파일 에러가 해결이 됩니다. 물론 바로 위의 테스트도 성공을 합니다.

<br>

### 학과와 번호로 정렬하기

 여기까지 학과의 이름으로만 정렬을 했다면 이번에는 학과가 같은 경우 번호를 사용해 정렬을 하는 것까지 구현하고자한다. 그럼 테스트부터 추가해보자.

```java
public void testComparable() {
    final Date date = new Date();
    CourseSession sessionA = CourseSession.create("CMSC", "101", date);
    CourseSession sessionB = CourseSession.create("ENGL", "101", date);
    assertTrue(sessionA.compareTo(sessionB) < 0);
    assertTrue(sessionB.compareTo(sessionA) > 0);

    CourseSession sessionC = CourseSession.create("CMSC", "101", date);
    assertEquals(0, sessionA.compareTo(sessionC));

    CourseSession sessionD = CourseSession.create("CMSC", "210", date);
    assertTrue(sessionC.compareTo(sessionD) < 0);
    assertTrue(sessionD.compareTo(sessionC) > 0);
}
```

 이 테스트를 성공시키기 위해서는 앞서 구현한 `CourseSession`의 `compareTo()`만 변경해주면 된다.

```java
@Override
public int compareTo(CourseSession that) {
    int compare = this.getDepartment().compareTo(that.getDepartment());

    if (compare == 0) {
      compare = this.getNumber().compareTo(that.getNumber());
    }
    return compare;
}
```

 만약 학과로 비교한 것의 반환 값이 0이 나오면 다시 한번 번호로 비교를 해준 뒤 그 값을 반환해 주면 된다.

<br>

### 학점 정하기

 이제 모든 학생의 리포트 카드도 생성을 하려한다. 그 전에 학생의 평균 학점 (GPA, grade point average)를 계산하고자 한다. 평균을 계산하기 때문에 이 값은 플로팅포인트(floating-point) 숫자로 표현해야 한다.

<br>

- #### floating-point

  - float : 32비트 단일 정확도 이진 플로팅 포인트 형식
    - 숫자 뒤에 'f' 또는 'F'를 붙여야한다.
  - double : float의 2배 정확도를 가지는 64비트 플로팅 포인트 형식
  - 플로팅 포인트 숫자는 이진수를 이용해 만든 실제 값의 근사치이다.
    - `3 * 0.3 = 0.8999999999999999`
    - 실수 값은 무한한 값을 가지지만 32혹은 64비트로 한정되어 있기 때문
    - 이런 부정확성을 없애기 위해서는 반올림을 사용하는 방법이 있다.

<br>

 학생의 평균 학점을 계산하는 테스트를 아래와 같이 작성해보자.

```java
public class StudentTest extends TestCase {
    private static final double GRADE_TOLERANCE = 0.05;
  
		// ...
  
    public void testCalculateGpa(){
        Student student = new Student("a");
        assertEquals(0.0, student.getGpa(), GRADE_TOLERANCE);
        student.addGrade("A");
        assertEquals(4.0, student.getGpa(), GRADE_TOLERANCE);
        student.addGrade("B");
        assertEquals(3.5, student.getGpa(), GRADE_TOLERANCE);
        student.addGrade("C");
        assertEquals(3.0, student.getGpa(), GRADE_TOLERANCE);
        student.addGrade("D");
        assertEquals(2.5, student.getGpa(), GRADE_TOLERANCE);
        student.addGrade("F");
        assertEquals(2.0, student.getGpa(), GRADE_TOLERANCE);
    }
}
```

 테스트하는 것은 학점을 추가할 때마다 평균 학점이 제대로 바뀌는지를 확인한다. 여기서 `assertEquals()`에 세번째 인자가 들어간 것을 확인할 수 있다. 이는 허용오차를 나타낸다. 즉, JUnit에서 테스트 실패를 알리려면 두 플로팅 포인트 변수의 차이가 얼마 이상이어야 하는지를 지정하는 것이다. 만약 평균 학점을 소수 첫째짜리까지 정확하게 표현하고자 정한다면 관용적인 규칙으로 가장 작은 정확도의 반만큼의 차이를 허용하는 것으로 0.05가 적절하다. 따라서 위에 정적 상수로 `GRADE_TOLERANCE`를 선언하여 사용할 수 있다.

 그럼 이제 위의 테스트를 통과시키기 위해 `Student`클래스에 `getGpa()`와 `addGrade()`를 추가해보자.

```java
public class Student {
    // ...
    private ArrayList<String> grades = new ArrayList<>();
    // ...
    void addGrade(String grade) {
        grades.add(grade);
    }
    
    double getGpa() {
        if (grades.isEmpty())
            return 0.0;
        
        double total = 0.0;
        for (String grade: grades) {
            if (grade.equals("A"))
                total += 4;
            else if (grade.equals("B"))
                total += 3;
            else if (grade.equals("C"))
                total += 2;
            else if (grade.equals("D"))
                total += 1;
        }
        return total / grades.size();
    }
}
```

 `addGrade()`는 학점을 인수로 받아 학생이 가지는 grades 리스트에 추가한다. `getGpa()`는 현재 가지고 있는 학점들의 평균을 계산하여 반환한다. 이 메소드에서 처음 나오는 if문(grades가 비었는지 확인하는)은 보호조건(guard clause)라고 알려진 특별한 조건에서 메소드를 종료시킨다. 즉, 해당 메소드는 학점의 개수로 나누어 계산하는데 처음 if문을 통해 0으로 나누어질 가능성을 없애준다.

<br>

### 재구성하기

 느꼈는지 모르겠지만 `getGpa()`메소드는 너무 길고 따라가기 힘들어보인다. 그래서 문자 학점을 숫자로 변환하는 메소드를 따로 빼주면 좋을 것 같다.

```java
double getGpa() {
    if (grades.isEmpty())
      return 0.0;

    double total = 0.0;
    for (String grade: grades) {
      total += gradePintsFor(grade);
    }
    return total / grades.size();
}

double gradePintsFor(String grade) {
    if (grade.equals("A")) return 4;
    if (grade.equals("B")) return 3;
    if (grade.equals("C")) return 2;
    if (grade.equals("D")) return 1;
    return 0;
}
```

 코드가 한결 깔끔하고 역할 분담도 잘 되어있어보인다. 테스트에서 assert부분도 아래와 같이 변경하면 코드를 읽기가 편할 수 있다.

```java
public void testCalculateGpa(){
    Student student = new Student("a");
    assertGpa(student, 0.0);
    student.addGrade("A");
    assertGpa(student, 4.0);
    student.addGrade("B");
    assertGpa(student, 3.5);
    student.addGrade("C");
    assertGpa(student, 3.0);
    student.addGrade("D");
    assertGpa(student, 2.5);
    student.addGrade("F");
    assertGpa(student, 2.0);
}

private void assertGpa(Student student, double expectedGpa) {
    assertEquals(expectedGpa, student.getGpa(), GRADE_TOLERANCE);
}
```

<br>

- #### enum

   현재 grade는 String 타입으로 입력을 받고 있다. 그러나 문자열을 타이핑하다보면 오타가 있을 수 있다. 그러면 클래스 상수를 사용하면 되지 않을까?  하지만 클래스 상수를 사용해도 인자로 문자열 값을 입력한다면 코드는 문제 없이 돌아간다. 여기서 이 문제를 막을 수 있는 방법은 `enum`을 사용하는 것이다. 아래와 같이 `Student`클래스에 문자열로 된 학점을 `Grade`라는 이름으로 새로운 변수형을 만들 수 있다.

  ```java
  public class Student {
      enum Grade { A, B, C, D, F};
      // ...
  }
  ```

  - 각 enum 값은 메모리상의 유일한 인스턴스를 나타낸다.
  - `==` 연산자를 사용할 수 있다. => 두 참조가 메모리 상의 같은지를 비교할 수 있다.
  - `Student` 내에서 enum으로 선언한 인스턴스들이 존재하는 유일한 인스턴스이며 다른 코드에서 Grade의 새 인스턴스 생성 불가

  그럼 이제 앞에서 문자열로 받던 코드들을 모두 enum을 사용하는 코드로 변경해야한다.

  ```java
  // Student.class
  public class Student {
      enum Grade { A, B, C, D, F};
  		// ...
      private ArrayList<Grade> grades = new ArrayList<>();
  		// ...
      void addGrade(Grade grade) {
          grades.add(grade);
      }
  
      double getGpa() {
          if (grades.isEmpty())
              return 0.0;
  
          double total = 0.0;
          for (Grade grade: grades) {
              total += gradePintsFor(grade);
          }
          return total / grades.size();
      }
  
      double gradePintsFor(Grade grade) {
          if (grade == Grade.A) return 4;
          if (grade == Grade.B) return 3;
          if (grade == Grade.C) return 2;
          if (grade == Grade.D) return 1;
          return 0;
      }
  }
  
  // StudentTest.class
  public void testCalculateGpa(){
      Student student = new Student("a");
      assertGpa(student, 0.0);
      student.addGrade(Student.Grade.A);
      assertGpa(student, 4.0);
      student.addGrade(Student.Grade.B);
      assertGpa(student, 3.5);
      student.addGrade(Student.Grade.C);
      assertGpa(student, 3.0);
      student.addGrade(Student.Grade.D);
      assertGpa(student, 2.5);
      student.addGrade(Student.Grade.F);
      assertGpa(student, 2.0);
  }
  ```

  <br>

### 폴리모피즘 (polymorphism)

 위에서 구현한 학점 계산에서 더 나아가 일반 학생과 전공 학생의 경우 학점 계산을 다르게 해야한다면 위에서 코드를 어떻게 변화시킬 수 있을까? 물론 if문을 사용하여 이들을 구분하고 계산을 해줄 수 있다. 그러나 이렇게 코드를 구성한다면 빠르게 복잡해지고 이해하기 힘들어진다. 또한 학점 체계가 자주 변동이 된다면 Student 클래스의 코드를 계속해서 바꿔주어야 한다. 바꾸는 과정에서 클래스가 잘못될 수도 있고 다른 클래스들과 의존적인 관계가 생길 수 있다. 이러한 점은 폴리모피즘 즉, 다형성이라고 알려진 개념을 사용하여 if문의 사용을 최소화하는 방법으로 해결해 나갈 수 있다. 

아래의 UML을 살펴보자.

<img width="500" alt="Screen Shot 2020-09-03 at 11 33 52 AM" src="https://user-images.githubusercontent.com/37801041/92064874-6c5de380-edd9-11ea-989b-a02024911cf1.png">

 위의 UML을 보면 `GradingStrategy`라는 인터페이스로 학점체계라는 추상적인 개념을 표현한다. 이렇게 하고 각기 다른 계산 방법을 가지는 체계들을 `GradingStrategy`를 구현함으로써 폴리모피즘을 실현시킬 수 있다. 이렇게 코드를 구성한다면 새로운 학점체계가 생긴다고 해도 새로운 클래스를 만들어 `GradingStrategy`를 구현하여 `Student`의 수정이 없이 확장을 할 수 있다. 참고로 여기서 보면 `Student`는 학점 정책에 대한 변화에 닫혀있다 할 수 있다.

 그럼 위의 UML과 같이 우리의 코드를 변경해보고자 한다. 우선 `GradingStrategy` 인터페이스를 생성하고 내부가 정의되어있지 않는 `getGradePointsFor()`메소드를 선언해준다.

```java
package sis.studentinfo;

public interface GradingStrategy {
    int getGradePointsFor(Student.Grade grade);
}
```

> 인터페이스는 구현하는 클래스의 공개적인 접근 방법이 되는 메소드를 정의해야한다. 따라서 모든 인터페이스 메소드는 의미상 public이어야 한다. 그렇기때문에 메소드 선언에 public 키워드를 사용할 필요가 없다.

 이제 각 학점 체계를 구현하는 `RegularGradingStrategy`와 `HonorsGradingStrategy`를 만들어주자. 전공 학생의 경우 기존 학점에 1을 더한 값을 반환해주는 코드로 구현하였다.

```java
// RegularGradingStrategy.class
package sis.studentinfo;

public class RegularGradingStratey implements GradingStrategy {
    @Override
    public int getGradePointsFor(Student.Grade grade) {
        if (grade == Student.Grade.A) return 4;
        if (grade == Student.Grade.B) return 3;
        if (grade == Student.Grade.C) return 2;
        if (grade == Student.Grade.D) return 1;
        return 0;
    }
}

// HonorsGradingStrategy.class
package sis.studentinfo;

public class HonorGradingStrategy implements GradingStrategy {
    @Override
    public int getGradePointsFor(Student.Grade grade) {
        int points = basicGradePointsFor(grade);
        if (points > 0)
            points += 1;
        return points;
    }

    private int basicGradePointsFor(Student.Grade grade) {
        if (grade == Student.Grade.A) return 4;
        if (grade == Student.Grade.B) return 3;
        if (grade == Student.Grade.C) return 2;
        if (grade == Student.Grade.D) return 1;
        return 0;
    }
}
```

 그럼 이제 `Student`에서 `GradingStrategy`를 사용하게끔 변경을 해야한다.

```java
public class Student {

    private GradingStrategy gradingStrategy = new RegularGradingStratey();
		// ...
    public void setGradingStrategy(GradingStrategy gradingStrategy) {
        this.gradingStrategy = gradingStrategy;
    }
		// ...
    double getGpa() {
        if (grades.isEmpty())
            return 0.0;

        double total = 0.0;
        for (Grade grade: grades) {
            total += gradingStrategy.getGradePointsFor(grade);
        }
        return total / grades.size();
    }
}
```

 기본적으로는 학점 정책으로 `RegularGradingStrategy`를 가지지만 setter를 사용해 학점 정책을 바꿔줄 수 있도록 만들어주고 `getGpa()`메소드에서 해당 인스턴스가 가지는 학점정책의 `getGradePointsFor()`메소드를 사용해 학점을 반환 받도록 만들어준다. 

 또 여기서 살펴볼 것이 있다. 위에서 보면 `RegularGradingStrategy`가 `GradingStrategy` 인스턴스 변수에 할당이 되었다. 자바는 이것을 허용한다. 다시말해, 인터페이스를 구현한 클래스는 그 인터페이스 형식이 될 수 있다. (하지만  `GradingStrategy` 참조를 사용하는 코드에서는 이 참조를 통해서 `GradingStrategy`에  관련된 메시지만을 보낼 수 있다.)

 인스턴스는 "추상화의 경계"이다. 따라서 변화가 가능한 객체에 대해서 추상화된 상호 작용 방법을 정의할 수 있다. 그렇기때문에 인터페이스형을 사용하는 것은 좋은 객체지향 디자인의 초석이 된다. 이것이 중요한 이유는 다음과 같다.

<img width="402" alt="Screen Shot 2020-09-03 at 11 55 00 AM" src="https://user-images.githubusercontent.com/37801041/92066203-4c7bef00-eddc-11ea-810e-701fb50c1185.png">

위의 구조에서는 클래스 X가 변화할 때마다 X에 의존적인 모든 클래스 A, B를 다시 컴파일하고 테스트해야한다.

<img width="337" alt="Screen Shot 2020-09-03 at 11 57 58 AM" src="https://user-images.githubusercontent.com/37801041/92066396-b7c5c100-eddc-11ea-9c51-846db62b592b.png">

그러나 위처럼 인터페이스로 추상화를 한다면 클래스들이 추상화된 개념에 의존적이게 된다. 다시 말해, A와 B는 실제적인 변화되는 클래스에 더 이상 의존적이지 않다. A와 B는 추상화에만 의족적이다. XImplementation 클래스 역시 이 추상화에 의존적이다. 이런 의존성 배치를 위해 노력해야한다. 실제 형식에 의존을 할수록 시스템을 바꾸기가 힘들어지기 때문이다. 추상적 형식(인터페이스와 같은)에 의존할 수록 시스템은 바꾸기가 쉬워진다.
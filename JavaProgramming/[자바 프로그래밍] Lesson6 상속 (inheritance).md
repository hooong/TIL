# [자바 프로그래밍] Lesson6 상속 (inheritance)

### switch문

 앞서 작성하였던 `HonorGradingStrategy`의 `basicGradePointsFor` 메서드는 switch문을 사용하여 아래와 같이 변경할 수 있다.

```java
private int basicGradePointsFor(Student.Grade grade) {
        switch (grade) {
            case A: return 4;
            case B: return 3;
            case C: return 2;
            case D: return 1;
            default: return 0;
        }   
    }
```

- switch문에 인수로 주어지는 `grade`가 비교할 대상이 된다.

- 임의 개수의 case 레이블을 지정할 수 있다.

  - 각 case와 일치할 경우 바로 뒤의 명령문을 실행하고 그렇지 않으면 생략된다.

  - 일치하는 case가 없다면 default로 이동한다. (default 레이블은 선택적으로 사용 가능)

  - 각 레이블에 `break` 가 없다면 아래코드가 생략되지 않을 수 있다.

    ```java
    int tmp = 0;
    int x = 2;
    
    switch (x) {
      case 1:
        tmp += 1;
      case 2:
        tmp += 2;
      case 3:
        tmp += 3;
    }
    ```

    위의 코드가 동작하고 tmp가 2가 나올 것을 의도하고 코드를 작성하였다면 tmp는 실행결과 5가 나오므로 잘못되었다. 아래와 같이 `break`로 흐름제어를 해준다면 원하는 2가 나올 수 있다.

    ```java
    int tmp = 0;
    int x = 2;
    
    switch (x) {
      case 1:
        tmp += 1;
        break;
      case 2:
        tmp += 2;
        break;
      case 3:
        tmp += 3;
        break;
    }
    ```

- 같은 목적을 위해서 여러 개의 if문 또는 많은 switch문을 코드에 넣는 것은 좋지않다. 따라서 폴리모피즘을 통해 해결하는 것이 좋다.
- switch문을 폴리모피즘으로 바꾸는 기준은 `중복`, `반복의 정도`, `유지의 용이성`이다.

<br>

### 맵 (Map)

위에서 사용한 switch문을 맵으로도 대채할 수 있다. 맵(map)은 특정한 키(key)에 관련된 값을 빠르게 추가하고 가져오기 위해 제공되는 컬렉션이다. 

여기서는 각 학생에 대해서 리포트 카드에 학점에 따라 적절한 메시지를 넣기를 원한다고 할때 `ReportCard`를 만들어본다. `sis.report`에 `ReportCardTest`를 생성하고 테스트 코드를 먼저 작성해보자.

```java
public class ReportCardTest extends TestCase {
    public void testMessage() {
        ReportCard card = new ReportCard();
        assertEquals(ReportCard.A_MESSAGE,
                card.getMessage(Student.Grade.A));
        assertEquals(ReportCard.B_MESSAGE,
                card.getMessage(Student.Grade.B));
        assertEquals(ReportCard.C_MESSAGE,
                card.getMessage(Student.Grade.C));
        assertEquals(ReportCard.D_MESSAGE,
                card.getMessage(Student.Grade.D));
        assertEquals(ReportCard.F_MESSAGE,
                card.getMessage(Student.Grade.F));
    }
}
```

해당 테스트를 통과시키기 위해 `ReportCard` 클래스를 작성해보자.

```java
public class ReportCard {
    static final String A_MESSAGE = "Excellent";
    static final String B_MESSAGE = "Very good";
    static final String C_MESSAGE = "Hmmm...";
    static final String D_MESSAGE = "Yore not trying";
    static final String F_MESSAGE = "Loser";

    private Map<Student.Grade, String> messages = null;

    public String getMessage(Student.Grade grade) {
        return getMessages().get(grade);
    }

    public Map<Student.Grade, String> getMessages() {
        if (messages == null)
            loadMessages();
        return messages;
    }

    public void loadMessages() {
        messages =
                new EnumMap<Student.Grade, String>(Student.Grade.class);
        messages.put(Student.Grade.A, A_MESSAGE);
        messages.put(Student.Grade.B, B_MESSAGE);
        messages.put(Student.Grade.C, C_MESSAGE);
        messages.put(Student.Grade.D, D_MESSAGE);
        messages.put(Student.Grade.F, F_MESSAGE);
    }
}
```

 여기서는 `EnumMap` 을 사용하여 각 Grade에 지정된 메시지를 `put()` 메서드를 사용해 저장하였다. 그리고 `get()` 메서드를 사용해 해당 키값에 해당하는 메시지를 반환받는다.

-  늦은 초기화 (lazy initalization)
  - 늦은 초기화란, 필드를 선얺나 곳에서나 생성자 내부에서 초기화하는 것이 아닌 실제로 필드를 사용할 필요가 있을 때까지 기다렸다가 초기화하는 방법
  - 이를 사용하는 가장 큰 이유는 필요할 때가지 부담이 될 수 있는 연산을 미루는 것
  - 위의 예제에서는 messages를 초기에 `null`로 초기화하고 메시지를 불러오려고 할때 messages가 `null`이면 그제서야 `loadMessages`메서드를 통해 초기화를 진행

<br>

### 상속 (inheritance)

`RegularGradingStrategy`와 `HonorsGradingStrategy`에서 기본 학점을 가져오는 switch문은 코드가 양쪽 클래스에서 중복이 되고 있다. 이 중복은 상속을 이용하여 없앨 수 있다. 우선 `BasicGradingStrategy`라는 클래스를 만들고 두 개의 클래스에서 상속을 받게끔 만들어주어 보자.

```java
// BasicGradingStrategy.class
public class BasicGradingStrategy {
}

// RegularGradingStrategy.class
public class RegularGradingStratey
        extends BasicGradingStrategy
        implements GradingStrategy {
    @Override
    public int getGradePointsFor(Student.Grade grade) {
        switch (grade) {
            case A: return 4;
            case B: return 3;
            case C: return 2;
            case D: return 1;
            default: return 0;
        }   
    }
}

// HonorsGradingStrategy.class
public class HonorGradingStrategy
        extends BasicGradingStrategy
        implements GradingStrategy {
    @Override
    public int getGradePointsFor(Student.Grade grade) {
        int points = basicGradePointsFor(grade);
        if (points > 0)
            points += 1;
        return points;
    }
  
  	public int basicGradePointsFor(Student.Grade grade) {
        switch (grade) {
            case A: return 4;
            case B: return 3;
            case C: return 2;
            case D: return 1;
            default: return 0;
        }   
    }
}
```

- `extends` 키워드는 `implements` 앞에 사용해야한다.

그럼 이제 중복되는 부분인 switch 문을 `BasicGradingStrategy`로 옮겨주자.

```java
// BasicGradingStrategy.class
public class BasicGradingStrategy {
    public int basicGradePointsFor(Student.Grade grade) {
        switch (grade) {
            case A: return 4;
            case B: return 3;
            case C: return 2;
            case D: return 1;
            default: return 0;
        }   
    }
}

// RegularGradingStratey.class
public class RegularGradingStratey
        extends BasicGradingStrategy
        implements GradingStrategy {
    @Override
    public int getGradePointsFor(Student.Grade grade) {
        return basicGradePointsFor(grade);
    }
}

// HonorGradingStrategy.class
public class HonorGradingStrategy
        extends BasicGradingStrategy
        implements GradingStrategy {
    @Override
    public int getGradePointsFor(Student.Grade grade) {
        int points = basicGradePointsFor(grade);
        if (points > 0)
            points += 1;
        return points;
    }
}
```

<img width="614" alt="Screen Shot 2020-10-04 at 1 36 53 AM" src="https://user-images.githubusercontent.com/37801041/94996873-249dc800-05e2-11eb-9528-fe66681ea8aa.png">

상속을 통해 구현한 `HonorsGradingStrategy`의 UML은 위와 같게 된다.

<br>

### 추상 클래스 (abstract class)

여기서 조금 더 나아가 `BasicGradingStrategy`를 추상 클래스로 만들어 `implements`를 하는 부분의 중복을 좀더 줄일 수 있다. 

- 메소드의 동작을 구현할 수 없고 구현하지 않겠다는 의미로 `abstract`로 선언
- 클래스가 최소 하나의 `abstract` 메소드를 포함하면 그 클래스 자체도 `abstract`로 선언되어야만 함
- 인터페이스를 직접 인스턴스화할 수 없는 것처럼, 추상 클래스의 인스턴스도 생성 불가

```java
abstract public class BasicGradingStrategy implements GradingStrategy{
    abstract public int getGradePointsFor(Student.Grade grade);

    public int basicGradePointsFor(Student.Grade grade) {
        switch (grade) {
            case A: return 4;
            case B: return 3;
            case C: return 2;
            case D: return 1;
            default: return 0;
        }   
    }
}
```

위의 코드와 같이 `getGradePointsFor()` 을 `abstract`를 붙여 추상메소드로 만들고 해당 클래스를 상속받는 클래스에서 추상 메소드를 구현해주어야 한다. 그리고 해당 클래스는 이제 최소 하나의 추상 메소드를 가지게되므로 클래스도 `abstract`로 선언을 해주어야한다. 이렇게 추상 클래스를 만들어줌으로써 `RegularGradingStratey`, `HonorGradingStrategy`에서는 더이상`GradingStrategy`을 `implements`한다는 선언을 하지 않아도 된다.

```java
// RegularGradingStratey.class
public class RegularGradingStratey
        extends BasicGradingStrategy {
    @Override
    public int getGradePointsFor(Student.Grade grade) {
        return basicGradePointsFor(grade);
    }
}

// HonorGradingStrategy.class
public class HonorGradingStrategy
        extends BasicGradingStrategy {
    @Override
    public int getGradePointsFor(Student.Grade grade) {
        int points = basicGradePointsFor(grade);
        if (points > 0)
            points += 1;
        return points;
    }
}
```

<br>

### 메소드 확장하기

중복을 없애는 또 다른 방법이 있다. 현재 코드를 보면 `HonorGradingStrategy`의 `getGradePointsFor()`는 `RegularGradingStratey`의 것을 확장한 것이다. (기본 메소드가 하는 것을 모두 하고, 약간의 추가적인 일을 한다.) 이것에 대한 중복을 최소화할 수 있다. 즉, `RegularGradingStratey`를 없애고 `BasicGradingStrategy`에 `getGradePointsFor()`을 정의해놓는 것이다.

```java
// BasicGradingStrategy.class
public class BasicGradingStrategy implements GradingStrategy {
    public int getGradePointsFor(Student.Grade grade) {
        return basicGradePointsFor(grade);
    }

    public int basicGradePointsFor(Student.Grade grade) {
        switch (grade) {
            case A: return 4;
            case B: return 3;
            case C: return 2;
            case D: return 1;
            default: return 0;
        }   
    }
}

// HonorGradingStrategy.class
public class HonorGradingStrategy
        extends BasicGradingStrategy {
    @Override
    public int getGradePointsFor(Student.Grade grade) {
        int points = super.getGradePointsFor(grade);
        if (points > 0)
            points += 1;
        return points;
    }
}

// RegularGradingStratey.class
public class RegularGradingStratey
        extends BasicGradingStrategy {
}
```

 자바는 원래의 메소드와 관계없는 전혀 새로운 메소드를 정의하는 것을 허용하는 메소드 오버라이드(override)를 제공한다. 컴파일러는 오버라이딩과 확장을 구별하지 못한다. 즉, 컴파일러 입장에서는 확장도 오버라이딩인 것이다. 하지만 우리는 상위 클래스의 같은 이름의 메소드를 호출하는 것을 보고 확장인 것을 알아차릴 수 있다. 어떤 의미에서 하위 클래스는 상위 클래스의 메소드의 동작을 완전히 변경하지 않고 특화시킬 필요가 있다. 이럴때는 위의 `HonorGradingStrategy`에서와 같이 명시적으로 `super`키워드를 사용해 호출해주는 것이 좋다.

<br>

### 재구성

- `BasicGradingStrategy`의 `basicGradePointsFor()`는 메소드의 내용을 `getGradePointsFor()` 로 옮겨줌으로써 필요가 없어진다.

  ```java
  public class BasicGradingStrategy implements GradingStrategy {
      public int getGradePointsFor(Student.Grade grade) {
          switch (grade) {
              case A: return 4;
              case B: return 3;
              case C: return 2;
              case D: return 1;
              default: return 0;
          }
      }
  }
  ```

- `RegularGradingStratey`에서 하위 클래스로 분리할 필요가 없어진다. `Student`에서 gradeStrategy를 기본적으로 `BasicGradingStrategy`를 사용하도록 변경해주어 `RegularGradingStratey`를 제거해준다.

  ```java
  public class Student {
      // ...
    
      private GradingStrategy gradingStrategy = 
        new BasicGradingStrategy();
  
  		// ...
  }
  
  ```

- 일반적인 규칙으로 하나의 결과 클래스에 적어도 하나의 테스트 클래스를 만들어 주어야한다. 따라서 `BasicGradingStrategy`와 `HonorGradingStrategy`의 테스트를 작성해준다.

  ```java
  // BasicGradingStrategyTest.class
  public class BasicGradingStrategyTest extends TestCase {
      public void testGetGradePoints() {
          BasicGradingStrategy strategy = new BasicGradingStrategy();
          assertEquals(4, strategy.getGradePointsFor(Student.Grade.A));
          assertEquals(3, strategy.getGradePointsFor(Student.Grade.B));
          assertEquals(2, strategy.getGradePointsFor(Student.Grade.C));
          assertEquals(1, strategy.getGradePointsFor(Student.Grade.D));
          assertEquals(0, strategy.getGradePointsFor(Student.Grade.F));
      }
  }
  
  // HonorGradingStrategyTest.class
  public class HonorGradingStrategyTest extends TestCase {
      public void testGetGradePoints() {
          BasicGradingStrategy strategy = new HonorGradingStrategy();
          assertEquals(5, strategy.getGradePointsFor(Student.Grade.A));
          assertEquals(4, strategy.getGradePointsFor(Student.Grade.B));
          assertEquals(3, strategy.getGradePointsFor(Student.Grade.C));
          assertEquals(2, strategy.getGradePointsFor(Student.Grade.D));
          assertEquals(0, strategy.getGradePointsFor(Student.Grade.F));
      }
  }
  ```

<br>

### Grade enum 확장

enum은 정의를 확장해서 다른 클래스 형식처럼 인스턴스 값, 생성자, 메소드를 포함하도록 할 수 있다. 제약사항으로는 enum에서 다른 enum을 확장할 수 없다는 것이다.

`Student`에서 Grade enum의 정의를 아래와 같이 수정한다.

```java
public class Student {
    public enum Grade { 
        A(4), 
        B(3), 
        C(2), 
        D(1), 
        F(0);
        
        private int points;
        
        Grade(int points) {
            this.points = points;
        }
        
        int getPoints() {
            return points;
        }
    }
  
  	// ... 
}
```

각 enum 인스턴스에 관련된 인수는 Grade 생성자로 전달되어 points라는 필드에 저장이 된다. 그럼 이제 `BasicGradingStrategy`에서의 switch문이 필요가 없어진다.

```java
public class BasicGradingStrategy implements GradingStrategy {
    public int getGradePointsFor(Student.Grade grade) {
        return grade.getPoints();
    }
}
```

<br>

### 여름학기 과목

봄, 가을 학기가 아닌 여름학기의 과목들은 수업 종료일, 수업 수, 학점등 많은 부분에서 다르다. 따라서 `SummerCourseSession`이라는 새로운 클래스를 만들어줄 것이다. 이는 `CourseSession`을 확장함으로써 단순하게 접근할 수 있다.

우선 여름학기의 종료일을 정확히 계산하는지부터 확인해보면서 클래스를 만들어보자. (`sis.summer`라는 패키지 생성)

```java
package sis.summer;

public class SummerCourseSessionTest extends TestCase {
    public void testEndDate() {
        Date startDate = DateUtil.createDate(2003, 6, 9);
        CourseSession session =
                SummerCourseSession.create("ENGL", "200", startDate);
        Date eightWeekwOut = DateUtil.createDate(2003, 8, 1);
        assertEquals(eightWeekwOut, session.getEndDate());
    }
}
```

여름학기는 봄과 가을과 다르게 8주동안 진행된다는 가정하에 구현을 한다.

```java
package sis.summer;

public class SummerCourseSession extends CourseSession {
    public static SummerCourseSession create(
            String department,
            String number,
            Date startDate) {
        return new SummerCourseSession(department, number, startDate);
    }

    private SummerCourseSession(
            String department,
            String number,
            Date startDate) {
        super(department, number, startDate);
    }

    public Date getEndDate() {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(getStartDate());
        int sessionLength = 8;
        int daysInWeek = 7;
        int daysFromFridayToMonday = 3;
        int numberOfDays = sessionLength * daysInWeek - daysFromFridayToMonday;
        calendar.add(Calendar.DAY_OF_YEAR, numberOfDays);
        return calendar.getTime();
    }
}
```

`CourseSession`을 `extends` 키워드를 사용해 상속을 받는다. 상속을 받으며 주의할 사항들이 아래 있다.

- 상위 클래스가 메소드를 `public`으로 선언했다면 하위 클래스에서 오버라이드된 메소드도 `public`으로 선언해야한다.
- 상위 클래스에서 `private`로 선언되어 있으면 하위 클래스에서 접근이 불가능하기때문에 `protected`로 선언해준다.
  - `protected`는 다른 패키지에 포함되어도 하위 클래스라면 모든 것에 접근할 수 있다.

<br>

### 재구성

위의 여름학기에서 `getEndDate()`는 `sessionLength`말고는 모두 같다. 이러한 중복은 `getSessionLength()`라는 메소드를 만들고 `SummerCourseSession`에서 오버라이딩을 통해 다른 값을 반환해주는 방법으로 피할 수 있다.

```java
// CourseSession.class
public class CourseSession implements Comparable<CourseSession>{
		// ...
  
    public Date getEndDate() {
        GregorianCalendar calendar = new GregorianCalendar();
        final int daysInWeek = 7;
        final int daysFromFridayToMonday = 3;
        calendar.setTime(getStartDate());
        int numberOfDays = getSessionLength() * daysInWeek - daysFromFridayToMonday;
        calendar.add(Calendar.DAY_OF_YEAR, numberOfDays);
        return calendar.getTime();
    }

    protected int getSessionLength() {
        return 16;
    }
  
  	// ...
}

// SummerCourseSession.class
public class SummerCourseSession extends CourseSession {
    public static SummerCourseSession create(
            String department,
            String number,
            Date startDate) {
        return new SummerCourseSession(department, number, startDate);
    }

    private SummerCourseSession(
            String department,
            String number,
            Date startDate) {
        super(department, number, startDate);
    }

    @Override
    protected int getSessionLength() {
        return 8;
    }
}
```

- `@Override` 는 IDE가 해당 주석을 해석하여 무언가 잘못되었을 때 알려줄 수 있는 용도로 사용된다. 오바라이딩의 경우 명시해주는 것이 좋다.

- 해당 예제에서는 `CourseSession`의 `getEndDate()`메소드는 템플릿(template)로 사용되고 있다. 어떠한 알고리즘은 끼워넣을 수 있다. 이러한 알고리즘이 하위 클래스에서 상세 부분을 조금 다르게하여 사용할 수 있다. 상위 클래스에 이러한 알고리즘을 메서드로 만들어 놓은 것을 템플릿이라할 수 있고 이를 `템플릿 메소드`라는 디자인 패턴의 예이다.

<br>

### 하청의 법칙

일반적으로 하위 클래스는 그대로 혹은 좀더 강화된 조건을 만족해야한다. 즉, 하위 클래스의 테스트는 상위 클래스의 테스트는 물론 조건이 추가된 하위 클래스만의 테스트 모두 성공해야한다는 것이다. 해당 예제에서 `CourseSession`과 `SummerCourseSession`은 같은 계층 단계일 것이다. 따라서 한 계층 위인 `Session`을 만들어 더욱 관계를 명확히 할 수 있다.

<img width="392" alt="Screen Shot 2020-10-04 at 2 09 00 PM" src="https://user-images.githubusercontent.com/37801041/95007453-2780d300-064b-11eb-9ae3-a50064d12079.png">

그럼 `SessionTest`에 하위 클래스에 대한 기본적인 단위 테스트들과 추상적인 팩토리 메소드(createSession)을 만들어준다.

```java
// SessionTest.class
abstract public class SessionTest extends TestCase {
    private Session session;
    private Date startDate;
    private static final int CREDITS = 3;

    public void setUp() {
        startDate = DateUtil.createDate(2003,1,6);
        session = createSession("ENGL", "101", startDate);
        session.setNumberOfCredits(CREDITS);
    }

    abstract protected Session createSession(
            String department, String number, Date startDate);

    public void testCreate() {
        assertEquals("ENGL", session.getDepartment());
        assertEquals("101", session.getNumber());
        assertEquals(0, session.getNumberOfStudents());
        assertEquals(startDate, session.getStartDate());
    }

    public void testEnrollStudents() {
        Student student1 = new Student("Cain DiVoe");
        session.enroll(student1);
        assertEquals(CREDITS, student1.getCredits());
        assertEquals(1, session.getNumberOfStudents());
        assertEquals(student1, session.get(0));

        Student student2 = new Student("Coralee DeVaughn");
        session.enroll(student2);
        assertEquals(CREDITS, student2.getCredits());
        assertEquals(2, session.getNumberOfStudents());
        assertEquals(student1, session.get(0));
        assertEquals(student2, session.get(1));
    }

    public void testComparable() {
        final Date date = new Date();
        Session sessionA = createSession("CMSC","101",date);
        Session sessionB = createSession("ENGL","101",date);
        assertTrue(sessionA.compareTo(sessionB) < 0);
        assertTrue(sessionB.compareTo(sessionA) > 0);

        Session sessionC = createSession("CMSC", "101", date);
        assertEquals(0, sessionA.compareTo(sessionC));

        Session sessionD = createSession("CMSC", "210", date);
        assertTrue(sessionC.compareTo(sessionD) < 0);
        assertTrue(sessionD.compareTo(sessionC) > 0);
    }
  
  	public void testSessionLength() {
        Session session = createSession("", "", new Date());
        assertTrue(session.getSessionLength() > 0);
    }
}

// CourseSessionTest.class
public class CourseSessionTest extends SessionTest {
    public void testCourseDates() {
        Date startDate = DateUtil.createDate(2003, 1, 6);
        Session session = createSession("ENGL", "200", startDate);
        Date sixteenWeeksOut = DateUtil.createDate(2003,4,25);
        assertEquals(sixteenWeeksOut, session.getEndDate());
    }

    public void testCount() {
        CourseSession.resetCount();
        createSession("","",new Date());
        assertEquals(1, CourseSession.getCount());
        createSession("", "", new Date());
        assertEquals(2, CourseSession.getCount());
    }

    @Override
    protected Session createSession(String department, String number, Date startDate) {
        return CourseSession.create(department, number, startDate);
    }
}

// SummerCourseSessionTest.class
public class SummerCourseSessionTest extends SessionTest {
    public void testEndDate() {
        Date startDate = DateUtil.createDate(2003, 6, 9);
        Session session = createSession("ENGL", "200", startDate);
        Date eightWeekOut = DateUtil.createDate(2003, 8, 1);
        assertEquals(eightWeekOut, session.getEndDate());
    }

    @Override
    protected Session createSession(String department, String number, Date startDate) {
        return SummerCourseSession.create(department, number, startDate);
    }
}
```

위와 같이 `SessionTest` 를 상속받아 하위 클래스인 `CourseSessionTest`, `SummerCourseSessionTest`들이 상위 클래스의 테스트도 모두 하도록 하게끔 만들 수 있다. 그럼 이제 테스트를 성공시키기 위해 클래스들을 수정해보자.

```java
// Session.class
abstract public class Session implements Comparable<Session> {
    private String department;
    private String number;
    private List<Student> students = new ArrayList<Student>();
    private Date startDate;
    private static int count;
    private int numberOfCredits;

    protected Session(String department, String number, Date startDate) {
        this.department = department;
        this.number = number;
        this.startDate = startDate;
    }

    public String getDepartment() {
        return department;
    }

    public String getNumber() {
        return number;
    }

    int getNumberOfStudents() {
        return students.size();
    }

    @Override
    public int compareTo(Session that) {
        int compare = this.getDepartment().compareTo(that.getDepartment());

        if (compare == 0) {
            compare = this.getNumber().compareTo(that.getNumber());
        }
        return compare;
    }

    void setNumberOfCredits(int numberOfCredits) {
        this.numberOfCredits = numberOfCredits;
    }

    public void enroll(Student student) {
        student.addCredits(numberOfCredits);
        students.add(student);
    }

    public List<Student> getAllStudents() {
        return students;
    }

    public Student get(int index) {
        return students.get(index);
    }

    public Date getEndDate() {
        GregorianCalendar calendar = new GregorianCalendar();
        final int daysInWeek = 7;
        final int daysFromFridayToMonday = 3;
        calendar.setTime(getStartDate());
        int numberOfDays = getSessionLength() * daysInWeek - daysFromFridayToMonday;
        calendar.add(Calendar.DAY_OF_YEAR, numberOfDays);
        return calendar.getTime();
    }

    protected Date getStartDate() {
        return startDate;
    }

    abstract protected int getSessionLength();
}

// CourseSession.class
public class CourseSession extends Session{
    private static int count;

    protected CourseSession(
            String department, String number, Date startDate) {
        super(department, number, startDate);
        CourseSession.incrementCount();
    }

    public static CourseSession create(
            String department,
            String number,
            Date startDate) {
        return new CourseSession(department, number, startDate);
    }

    static int getCount() {
        return count;
    }

    static void resetCount() {
        count = 0;
    }

    static private void incrementCount() {
        ++count;
    }

    protected int getSessionLength() {
        return 16;
    }
}

// SummerCourseSession.class
public class SummerCourseSession extends Session {
    public static SummerCourseSession create(
            String department,
            String number,
            Date startDate) {
        return new SummerCourseSession(department, number, startDate);
    }

    private SummerCourseSession(
            String department,
            String number,
            Date startDate) {
        super(department, number, startDate);
    }

    @Override
    protected int getSessionLength() {
        return 8;
    }
}
```


# [자바 프로그래밍] Lesson4 클래스 메소드와 필드

### 클래스 메소드

 객체는 동작(메소드)과 속성(필드)으로 이루어지며 객체가 유지되는 동안 속성은 유지된다. 또한 객체는 항상 인스턴스 변수의 산태로 표현 가능한 특정 상태를 가진다. 객체는 메소드는 크게 동작 메소드(객체의 상태를 변경), 질의 메소드(객체 상태의 일부를 반환)로 나뉠 수 있으며 이와 같이 메소드는 한가지의 일만 하도록 디자인하는 것이 좋다.

 간혹 인수를 받아서 그 인수에 대해서만 연산을 하고 값을 반환하는 메소드가 필요한 경우가 있다. 이러한 메소드를 *유틸리티 메소드*라고 하며, 객체의 상태에는 영향을 주지 않는다. 다른 언어에서는 함수(function)이라고 불리며 어떤 클라이언트에서든지 호출이 가능하다.

 예로들면 앞서 `DateUtil`을 들 수 있다. 원래 코드와 아래 코드를 비교해보자.

```java
public void testRosterReport(){
    CourseSession session =
      new CourseSession("ENGL", "101",
                        DateUtil.createDate(2003, 1, 6));

    session.enroll(new Student("A"));
    session.enroll(new Student("B"));

    String rosterReport = new RosterReporter(session).getReport();
    System.out.println(rosterReport);
    assertEquals(
      RosterReporter.ROSTER_REPORT_HEADER +
      "A" + RosterReporter.NEWLINE +
      "B" + RosterReporter.NEWLINE +
      RosterReporter.ROSTER_REPORT_FOOTER +
      "2" + RosterReporter.NEWLINE, rosterReport);
}
```

 기존에는 DateUtil 인스턴스를 new 키워드를 통해 생성하고 createDate를 사용하였지만 위의 코드에서는 인스턴스 생성없이 바로 사용하고 있다. 이렇게 하기 위해서는 `DateUtil`클래스에서 `createDate`메소드를 클래스 메소드로 만들어주어야 한다. 방법은 간단하다. 메소드에 `static` 키워드를 붙여주는 것이다.

```java
public class DateUtil {
    private DateUtil() {}

    public static Date createDate(int year, int month, int date) {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month-1);
        calendar.set(Calendar.DAY_OF_MONTH, date);
        return calendar.getTime();
    }
}
```

 그리고 추가적으로 DateUtil의 생성자를 private로 만들어주어 외부에서는 인스턴스를 생성할 수 없게하여 사용하는 측에서 의미 없고 쓸모 없는 것을 할 수 없도록 방지하는 것이 좋다.

<br>

### 클래스 변수

 인스턴스 변수와 상대적인 개념인 클래스 변수에 대하여 알아보자.

 클래스 변수는 앞서 살펴본 클래스 메소드와 비슷하게 클래스를 읽어들인 후부터 응용프로그램이 종료될 때까지 클래스가 존재한다면 사용할 수 있는 정적인 범위(static scope)를 가진다. 따라서 사용법은 클래스 메소드와 유사하다. 아래는 전체 강의 수를 조회하기 위한 테스트를 추가한 것이다.

```java
public void testCount() {
    CourseSession.count = 0;
    createCourseSession();
    assertEquals(1, CourseSession.count);
    createCourseSession();
    assertEquals(2, CourseSession.count);
}

private CourseSession createCourseSession() {
    return new CourseSession("ENGL", "101", startDate);
}
```

 해당 테스트를 통과하기 위해서는 `CourseSession`에 아래와 같이 코드를 추가해야 한다.

```java
public class CourseSession {

    private String department;
    private String number;
    private ArrayList<Student> students = new ArrayList<Student>();
    private Date startDate;
    static int count;

    public CourseSession(String department, String number, Date startDate) {
        this.department = department;
        this.number = number;
        this.startDate = startDate;
        CourseSession.count += 1;
    }
		//...
}
```

 `static`지시어가 붙은 `count`를 선언해줌으로써 클래스가 존재하는 한 어디에서든 접근을 할 수 있다. 또한 어디에서 변경을 하면 앞으로 그 변경된 값이 불려와진다. 테스트 통과를 위해 생성자 부분에 새로운 인스턴스가 생길때마다 count를 증가시켜주는 코드를 작성해준다.

 이미 언급한 것처럼 객체가 유지되는 동안만 유효하는 인스턴스 변수와는 다른 유효기간을 가지는 것이 클래스 변수이다.

<br>

### 클래스 변수와 클래스 메소드에 대한 연산

 위에서 클래스 변수에 직접 접근하여 값을 변경하고 가져오고 했다. 그러나 클래스 변수 역시 외부에 공개하는 것은 좋지 않다. 따라서 클래스 메소드를 사용하여 클래스 변수를 변경하고 가져오는 방식으로 코드를 짜는 것이 좋다.

 그럼 클래스 변수 count를 숨기고 클래스 메소드를 통하여 변경하고 가져오는 코드로 바꿔보겠다. 

```java
public void testCount() {
    CourseSession.resetCount();
    createCourseSession();
    assertEquals(1, CourseSession.getCount());
    createCourseSession();
    assertEquals(2, CourseSession.getCount());
}
```

 우선 테스트에서 count에 직접 접근하던 부분을 모두 클래스 메소드로 호출을 하였다.

```java
public class CourseSession {
    //...
    private static int count;

    public CourseSession(String department, String number, Date startDate) {
        this.department = department;
        this.number = number;
        this.startDate = startDate;
        CourseSession.incrementCount();
    }
  	//...
    static int getCount() {
        return count;
    }

    static void resetCount() {
        count = 0;
    }

    static void incrementCount() {
        count += 1;
    }
}

```

 또한 생성자에서도 count를 직접 접근하지 않고 클래스 메소드를 통하여 증가를 시킨다. 이는 클래스 변수에 대한 제어를 위한 일종의 보호이다. 여기서 incrementCount() 메서드는 같은 클래스에 있어 클래스명을 명시하지 않아도 사용할 수 있다. 하지만 코드를 읽을 때 불필요한 혼동을 주는 좋지 않은 습관이다. 따라서 클래스 메소드를 사용할 때에는 클래스명을 명시해주는 것이 좋다. 이렇게 해주면 count는 외부에서 접근할 필요가 없기에 private 지시어를 사용해 내부로 감출 수 있게된다.

 여기서 주의할 점은 클래스 메소드에서는 클래스 변수에 직접 접근이 가능하다는 것이다. 따라서 접근할 때 클래스 이름을 명시해서는 안된다.

<br>

### 팩토리 메소드(Factory Methods)

 스태틱 팩토리 메소드를 사용해 CourseSession 객체를 생성할 수도 있다. 이렇게 재구성을 하면 새 인스턴스가 생성될 때의 동작을 좀 더 제어할 수 있으며 다수의 생성자를 생성 메소드로 대체할 수 있다. 또 가장 큰 장점은 생성 메소드에 알기 쉬운 이름을 붙일 수 있다는 것이다. 기존 생성자는 클래스의 이름과 같기때문에 충분한 정보를 줄 수 없기때문이다.

 그럼 `CourseSession`의 팩토리 메소드를 만들어보자.

```java
private CourseSession(String department, String number, Date startDate) {
  //...
}
```

 우선 생성자에 private를 붙여 CourseSession을 생성하고 있는 모든 곳을 찾아낸다. (rebuild를 통해 오류나는 곳을 확인) 모두 찾았다면 아래와 같이 팩토리 메소드를 사용하여 생성하는 것으로 코드를 변경해준다.

```java
private CourseSession createCourseSession() {
  	return CourseSession.create("ENGL", "101", startDate);
}
```

 그럼 마지막으로 팩토리 메소드를 작성한다.

```java
private CourseSession(String department, String number, Date startDate) {
    this.department = department;
    this.number = number;
    this.startDate = startDate;
}

public static CourseSession create(String department, String number, Date startDate) {
    incrementCount();
    return new CourseSession(department, number, startDate);
}
```

 이렇게 스태틱 팩토리 메소드를 만들어주면 클래스 메소드인 incrementCount()도 한결 자연스럽게 사용이 가능해진다.

<br>

### 단순한 디자인

 디자인을 하는 도중 정당한 이유 없이 스태틱 생성 메소드를 추가하는 것처럼 지나친 시스템 디자인을 할 수 있다. 또한 중요한 부분을 놓치는 경우가 많다. 디자인에서 가장 좋은 전력은 항상 코드를 가장 깨끗하게 유지하는 것이다. 이것을 실천하기 위해 "단순한 디자인"이라고 불리는 규칙이 있다.

- 테스트를 통과하고, 테스트에서 항상 100% 녹색으로 동작하는 것을 확인한다.
- 중복을 없앤다.
- 코드가 깨끗하고 분명한가를 확인한다.
- 클래스와 메소드의 수를 최소화한다.

<br>

### 정적 사용의 위험성

 스태틱 메소드나 변수는 부적절하게 사용하면 해결하기 어려운 심각한 문제를 일으킬 수 있다. 전형적인 실수로는 속성을 클래스 변수로 선언하는 것이다.

한 예로들자면, Student의 name이라는 속성은 인스턴스 변수로 선언해야한다. 그러나 클래스 변수로 선언을 한다면 모든 Student 객체가 같은 name변수를 사용하게 된다.

 책의 저자는 "정적 코드가 필요해질 때까지 정적 코드를 사용하지 않는다."라는 제프(Jeff)의 정적 코드 규칙을 말해준다. 정적 코드에 대하여 충분한 지식이 없다면 분별 없이 사용하게 될 수 있고, 이렇게 유지가 되면 이런 코드는 곧 객체 지향적이지 않고 점차 순차적 프로그램과 같아지고 전역 함수들이 늘어난다라고 한다. 또한 부적절하고 부주의한 정적 코드는 디자인의 제한, 찾기 힘들고 복잡한 버그, 메모리 누출과 같은 온갖 종류의 문제를 일으킬 수 있다고 한다. 따라서 왜 정적인 코드를 사용하는지를 알 때까지는 사용하지 않는 것이 좋다라고 한다.

<br>

### Boolean

 이제는 학생의 수업료에 관련되어 학생이 듣는 학점이 12점이 넘는지의 여부를 가지고 풀타임인지 파트타임인지를 구분하려고 한다. 학생이 풀타임인지 아닌지를 확인하기 위해서는 boolean형 변수를 사용할 수 있다. 

- boolean
  - true, false 두 가지 값을 가질 수 있음.
  - int형과 마찬가지로 기본형 => boolean으로 메시지를 보낼 수 없음
- assertFalse(), asserTrue()
  - 인수가 true인지 false인지 확인해주는 메소드

 그럼 `Students` 에 credits을 추가하고 풀타임인지 확인하는 테스트를 짜보고 통과시켜보자.

```java
public void testStudentStatus() {
    Student student = new Student("a");
    assertEquals(0, student.getCredits());
    assertFalse(student.isFullTime());

    student.addCredits(3);
    assertEquals(3, student.getCredits());
    assertFalse(student.isFullTime());

    student.addCredits(4);
    assertEquals(7, student.getCredits());
    assertFalse(student.isFullTime());

    student.addCredits(5);
    assertEquals(12, student.getCredits());
    assertTrue(student.isFullTime());
}
```

 위의 테스트를 통과하기 위해 `Students`를 변경해보자.

```java
package sis.studentinfo;

public class Student {
    private String name;
    private int credits;
    static final int CREDITS_REQUIRED_FOR_FULL_TIME = 12;

		//...

    boolean isFullTime() {
        return credits >= CREDITS_REQUIRED_FOR_FULL_TIME;
    }

    int getCredits() {
        return credits;
    }

    void addCredits(int credits) {
        this.credits += credits;
    }
}

```

 12학점을 기준으로 풀타임인지 아닌지를 확인한다. 그러나 이 값은 언제든지 변경이 될 수도 있다. 그렇기에 스태틱 상수로 선언을 해두는 것이 좋다. 따라서 credits와 CREDITS_REQUIRED_FOR_FULL_TIME를 비교하여 12 이상이라면 true를 반환해주게 된다. 

 Student를 모두 수정했으니 이제는 CourseSession도 수정해야 할 차례이다. 각 session별로 배정된 학점이 있고 그 수업에 수강신청을 하면 해당 학점을 학생에게 추가를 해주는 것이다. 테스트부터 만들어 보자.

```java
package sis.studentinfo;

import junit.framework.TestCase;

import java.util.Date;

public class CourseSessionTest extends TestCase {
    // ...
    private static final int CREDITS = 3;

    public void setUp() {
        startDate = DateUtil.createDate(2003,1,6);
        session = createCourseSession();
    }
		// ...
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
		// ...
    private CourseSession createCourseSession() {
        CourseSession session =
                CourseSession.create("ENGL", "101", startDate);
        session.setNumberOfCredits(CourseSessionTest.CREDITS);
        return session;
    }
}
```

 테스트에 변경과 추가 사항은 다음과 같다.

- 강의에 지정할 학점 정보를 가지는 상수 `CREDITS` 추가
- `SetUp`에서 `createCourseSession()` 메소드를 통해 생성
- `createCourseSession()`에서 해당 강의에 `CREDITS`로 `credits` 설정
- `testEnrollStudents()`에서 학생이 추가될때마다 해당 학생의 `credits`을 확인

 `CourseSession` 클래스를 수정하여 테스트를 통과시켜보자.

```java
package sis.studentinfo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class CourseSession {
		// ...
    private int numberOfCredits;
		// ...
    public void enroll(Student student) {
        student.addCredits(numberOfCredits);
        students.add(student);
    }
		// ...
    void setNumberOfCredits(int numberOfCredits) {
        this.numberOfCredits = numberOfCredits;
    }
  	// ...
}

```

해당 강의의 학점을 가지는 `numberOfCredits`을 선언하고 이를 외부에서 설정할 수 있게 Setter를 만들어준다. 또 `enroll()` 즉, 수강신청을 할 때마다 학생의 수강 학점도 증가를 시킨다.

<br>

### 초기화

 이 얘기를 하기위해 학생 정보에 사는 지역을 설정하고 학교의 위치인 콜로라도("CO")에 속하는지를 확인해 보려한다. 우선 테스트 코드를 먼저 작성해보자.

```java
public void testInState() {
    Student student = new Student("a");
    assertFalse(student.isInState());
    student.setState(Student.IN_STATE);
    assertTrue(student.isInState());
    student.setState("MD");
    assertFalse(student.isInState());
}
```

 해당 테스트는 `Student`를 생성하자마자 "CO"에 속하지 않는 것을 확인하고나서 상수로 선언할 IN_STATE("CO")를 설정한 뒤 속한다는 것을 확인하고 "MD"로 설정하고 속하지 않다는 것을 확인한다. 테스트를 성공시키기 위해 `Student`클래스를 수정해보자.

```java
public class Student {
		// ...
    static final String IN_STATE = "CO";
		// ...
    private String state = "";
		// ...
    void setState(String state) {
        this.state = state;
    }
		// ...
    public boolean isInState() {
        return state.equals(Student.IN_STATE);
    }
}

```

 여기서 왜 초기화 얘기가 나오나 할 수 있다.

```java
 private String state = "";
```

`Student`가 새로 생성이 되면 위에 코드와 같이 state가 ""로 빈 문자열이 저장된다. 만약 초기화 없이 아래와 같다면?

```java
 private String state;
```

 테스트를 돌려보면 NullPointerException이 발생하는 것을 확인할 수 있을 것이다. 이는 참조형 필드들을 초기화해주지 않으면 null로 초기화가 되기때문이다. 앞에서 int형이나 boolean형은 기본형으로 각각 0과 false로 초기화가 된다. 그러나 참조형 필드들은 다르다. 

 또한 기본형의 경우에도 자동으로 0과 false로 초기화되는 것이 유용하지만 필드에 특별한 의미가 있다면 명시적으로 필드를 초기화해주어야한다. 이것은 코드의 개발자로서 의도를 명확하게 해야하기 때문이다.


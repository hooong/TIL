# [자바 프로그래밍] Lesson3 문자열과 패키지

### 문자와 문자열

- #### 문자

  - char 프리미티브(primitive)형
  - 유니코드(Unicode 4.0) 표준에 기반
  - 2Byte = 2^16 = 65,536개의 문자 표현 가능

  - 본질적으로는 숫자
    - `assertEquals('\u0041', 'A');`  => 'A'를 '\u'와 16진수 '0041'(10진수로 65)로 표현
    - `assertEquals('\101', 'A');`  => 'A'를 '\\'와 8진수로 표현

- #### 특수 문자

  - 역슬래시(\\)뒤에 문자를 넣은 이스케이프 시퀀스(escape sequence)로 특수 문자 표현 

  ```
  '\r' : 캐리지 리턴 (Carriage return)
  '\n' : 라인 피드 (Line Feed)						 
  '\t' : 탭 (Tab)												
  '\f' : 폼 피드 (Form feed)
  '\b' : 백스페이스 (Backspace)
  '\'' : 작은 따옴표 (Single quote)
  '\\' : 역슬래시 (Backslash)
  '\"' : 큰 따옴표 (Double quote)
  ```

  <br>

- #### 문자열 

  - 문자열 객체는 고정된 길이의 char 문자의 나열

  - JVM은 String 객체를 보이지 않는 곳에서 생성

    1. `String a = "abc";`

    2. `String b = new String("abc");` => 두 개의 String 객체를 생성

    > 두 번째 방법에서는 "abc"라는 String 객체를 만들고 "abc"를 생성자로 전달하여 새로운 String 객체를 만들어 불필요한 생성자의 사용으로 성능을 떨어뜨릴 수 있다.

  - 불변성 (immutable)

    - Sun은 가장 최적화된 방법으로 실행하기 위하여 String이 변경될 수 없도록 함.
    - 따라서 문자열을 바꾸기 위한 어떤 시도도 새로운 문자열을 생성

- #### 문자열 연결하기

  `assertEquals("abcd", "ab".concat("cd"));`

  `assertEquals("abcdef", "abc" + "def");`

  > 오버라이드 된 '+' 연산자를 통해 concat() 대신 문자열을 연결할 수 있다.
  >
  > 따라서 아래와 같이 정수가 문자열로 표현되어있을때에는 연결이 된다.
  >
  > `assertEquals("123", "1" + "2" + "3");`

<br>

### StringBuilder

- 동적으로 문자열을 만들 수 있음
- 새로 생성된 StringBuilder 객체는 빈 문자열 혹은 빈 문자의 콜렉션
- append() : 콜렉션에 문자 또는 문자열을 추가할 수 있음.
- toString() : StringBuilder의 String 객체를 얻어 올 수 있음.
- StringBuffer보다 좀더 좋은 성능을 가짐.

<br>

 `CourseSessionTest`에 다음과 같이 테스트를 추가해보자.

```java
public void testRosterReport(){
    session.enroll(new Student("A"));
    session.enroll(new Student("B"));

    String rosterReport = session.getRosterReport();
    assertEquals(
      CourseSession.ROSTER_REPORT_HEADER +
      "A" + CourseSession.NEWLINE +
      "B" + CourseSession.NEWLINE +
      CourseSession.ROSTER_REPORT_FOOTER +
      "2" + CourseSession.NEWLINE, rosterReport);
}
```

위의 코드는 session에 대한 보고서를 반환받아 시작부분과 끝부분에 학생과 수를 보여주는지 확인하는 테스트이다. 따라서 해당 테스트를 성공하기 위해서는 `CourseSession` 클래스를 다음과 같이 변경해야 한다.

```java
public class CourseSession {
  	// 스태틱 변수
    static final String NEWLINE =
            System.getProperty("line.separator");
    static final String ROSTER_REPORT_HEADER =
            "Student" + NEWLINE +
            "-------" + NEWLINE;
    static final String ROSTER_REPORT_FOOTER =
            NEWLINE + "# students = ";

    ...

    public String getRosterReport() {
        StringBuilder buffer = new StringBuilder();

        buffer.append(ROSTER_REPORT_HEADER);

        Student student = students.get(0);
        buffer.append(student.getName());
        buffer.append(NEWLINE);

        student = students.get(1);
        buffer.append(student.getName());
        buffer.append(NEWLINE);

        buffer.append(ROSTER_REPORT_FOOTER + students.size() + NEWLINE);

        return buffer.toString();
    }
}
```

> 스태틱 변수를 선언한 부분을 보면 `System.getProperty()`라는 메서드를 볼 수 있다. `java.lang.System`은 유닉스나 윈도우와 같은 플랫폼이 달라 생길 수 있는 문제를 해결할 수 있게 도와주는 클래스이다. 예를 들면, 특성 중 `line.separator`의 경우 유닉스에서는 `\n`이지만 윈도우에서는 `\r\n`이다. 따라서 이러한 시스템 특성 값이 다른 문제를 해결할 수 있는 메서드가 바로 `System.gerProperty()`이다. 이는 시스템 특성 키를 인수로 받아서 키와 연관된 시스템 특성 값을 반환해준다.

 그러나 앞서 작성한  `CourseSession`클래스에서는 첫 번째와 두 번째 학생만 리포트에 추가된다. session에 등록된 모든 학생을 추가하기 위해서는 다음과 같이 for-each 루프를 사용하여 변경할 수 있다.

```java
public String getRosterReport() {
    StringBuilder buffer = new StringBuilder();

    buffer.append(ROSTER_REPORT_HEADER);
		
  	// for-each
    for (Student student: students){
      buffer.append(student.getName());
      buffer.append(NEWLINE);
    }

    buffer.append(ROSTER_REPORT_FOOTER + students.size() + NEWLINE);

    return buffer.toString();
}
```

> for-each loop : 위에서의 예로 설명하자면 콜렉션 students의 각 객체를 Student형의 student라는 이름의 레퍼런스에 할당하고, 이 상태에서 for loop의 내용을 실행하는 것이다.

<br>

### 단일 역할의 원칙

 객체 지향 프로그래밍에서 가장 기본적인 디자인 원칙은 클래스는 한가지 일만을 잘해야 한다는 것이다. 즉, 한가지 일만을 잘 하기 위해 클래스를 바꾸는 이유는 한 가지 뿐이어야한다. 이것이 바로 단일 역할의 원칙이다.

 그러나 `CourseSession`이 할 일 한가지는 강의에 대한 모든 정보를 추적하는 것이다. 보고서를 생성하는 것은 할 일이 아니다. 따라서 `RosterRepoter`라는 클래스로 떼어내야 한다.

`RosterRepoterTest`를 만들어 테스트케이스를 먼저 작성하자.

```java
public class RosterReporterTest extends TestCase {
    public void testRosterReport(){
        CourseSession session =
                new CourseSession("ENGL", "101", createDate(2003, 1, 6));

        session.enroll(new Student("A"));
        session.enroll(new Student("B"));

        String rosterReport = new RosterReporter(session).getReport();
        assertEquals(
                RosterReporter.ROSTER_REPORT_HEADER +
                        "A" + RosterReporter.NEWLINE +
                        "B" + RosterReporter.NEWLINE +
                        RosterReporter.ROSTER_REPORT_FOOTER +
                        "2" + RosterReporter.NEWLINE, rosterReport);
    }

    Date createDate(int year, int month, int date) {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month-1);
        calendar.set(Calendar.DAY_OF_MONTH, date);
        return calendar.getTime();
    }
}
```

 `CourseSessionTest`에서의 Repoter 관련 코드를 가져와 약간의 수정만 하면 된다. 그리고 `createDate()`메서드가 중복으로 사용되기 때문에 복사를 해온다. 따라서 주요 변경사항은 아래와 같다.

- CourseSession 객체를 인수로 하여 RosterRepoter의 인스턴스를 만든다.
- CourseSession 대신 RosterRepoter에 선언도니 클래스 상수를 사용한다.
- testRosterReprot는 자신의 CourseSession 객체를 만든다.

 테스트를 분리해냈으니 `AllTests`에도 해당 테스트를 등록해주는 것이 좋을 것 같다.

```java
public class AllTests {
    public static TestSuite suite() {
        TestSuite suite = new TestSuite();
        suite.addTestSuite(StudentTest.class);
        suite.addTestSuite(CourseSessionTest.class);
        suite.addTestSuite(RosterReporterTest.class);
        return suite;
    }
}
```

 <br>

 그럼 이제 위의 테스트를 통과시키기 위해 `RosterReport` 클래스를 만들어야한다.

```java
public class RosterReporter {
    static final String NEWLINE =
            System.getProperty("line.separator");
    static final String ROSTER_REPORT_HEADER =
            "Student" + NEWLINE +
                    "-------" + NEWLINE;
    static final String ROSTER_REPORT_FOOTER =
            NEWLINE + "# students = ";

    private CourseSession session;

    public RosterReporter(CourseSession session) {
        this.session = session;
    }

    public String getReport() {
        StringBuilder buffer = new StringBuilder();

        buffer.append(ROSTER_REPORT_HEADER);

        for (Student student: session.getAllStudents()){
            buffer.append(student.getName());
            buffer.append(NEWLINE);
        }

        buffer.append(ROSTER_REPORT_FOOTER + session.getAllStudents().size() + NEWLINE);

        return buffer.toString();
    }
}

```

 `CourseSession`에서 Report와 관련된 코드들을 가져오고 CourseSession의 인스턴스를 session이라는 이름으로 필드로 가지게 된다. 또한 for-each문에서는 모든 학생의 정보를 session에서 `getAllStudents()`메서드를 통해 가져온다.

![Screen Shot 2020-08-29 at 1 45 39 PM](https://user-images.githubusercontent.com/37801041/91628755-eec35d80-e9fd-11ea-969d-74242c76f083.png)

여기까지 코드를 작성하면 위와 같은 클래스 다이어그램을 가질 수 있다. 

<br>

### 재구성하기

 `RosterReporterTest`를 만들면서 `createDate()`메서드가 중복이 됐다. 작은 중복은 그냥 넘어갈 수 있겠지만, 금세 시스템에서 지나치기 어려운 중복이 생기기 시작할 수 있다. 따라서 중복은 최대한 없애야한다.

`createDate()`의 중복을 없애기 위해서 `DateUtil`이라는 클래스를 만들어본다.

```java
// DateUtilTest.java
public class DateUtilTest extends TestCase {
    public void testCreateDate() {
        Date date = new DateUtil().createDate(2000, 1, 1);
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        assertEquals(2000, calendar.get(Calendar.YEAR));
        assertEquals(Calendar.JANUARY, calendar.get(Calendar.MONTH));
        assertEquals(1, calendar.get(Calendar.DAY_OF_MONTH));
    }
}

// DateUtil.java
public class DateUtil {
    Date createDate(int year, int month, int date) {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month-1);
        calendar.set(Calendar.DAY_OF_MONTH, date);
        return calendar.getTime();
    }
}

// AllTests.java
public class AllTests {
    public static TestSuite suite() {
        TestSuite suite = new TestSuite();
        suite.addTestSuite(StudentTest.class);
        suite.addTestSuite(CourseSessionTest.class);
        suite.addTestSuite(RosterReporterTest.class);
        suite.addTestSuite(DateUtilTest.class);
        return suite;
    }
}
```

> 한 클래스의 코드를 새로운 클래스로 옮길 때, 항상 대응되는 새 테스트 클래스로 모든 테스트를 옮겨야한다. 만약, 없다면 새로 만들어야한다. 그래야 시스템을 유지할 수 있을 것이다.

 이렇게하면 이제 `createDate()`메서드를 사용하던 `courseSessionTest`와 `RosterRepoterTest`클래스에서는 다음과 같이 `createDate()`메서드를 사용할 수 있다.

```java
// CourseSessionTest.java
public class CourseSessionTest extends TestCase {
    private CourseSession session;
    private Date startDate;

    public void setUp() {
        startDate = new DateUtil().createDate(2003,1,6);
        session = new CourseSession("ENGL", "101", startDate);
    }

  	...

    public void testCourseDates() {
        Date sixteenWeeksOut = new DateUtil().createDate(2003,4,25);
        assertEquals(sixteenWeeksOut, session.getEndDate());
    }
}

// RosterReporterTest.java
public class RosterReporterTest extends TestCase {
    public void testRosterReport(){
        CourseSession session =
                new CourseSession("ENGL", "101",
                        new DateUtil().createDate(2003, 1, 6));
				...
    }
}
```

<br>

- #### System.out

  > out이 stdout 혹은 콘솔(console)로 불리는 표준 출력 스트림을 표시하는 PringStream형의 스태틱 변수이다. 따라서 `System.out`이라는 스태틱 변수 레퍼런스를 통해 콘솔 객체에 직접 엑세스할 수 있다.
  >
  > 
  >
  > System.out을 사용하는 가장 큰 이유는 프로그램의 실수를 찾기 위해 콘솔에 메시지를 출력하는 것이다. 하지만 TDD를 사용한다면 디버깅하거나 트레이스 문을 넣을 필요가 전혀 없어야한다. 테스트와 실제 코드를 조금씩 늘려서 시스템을 완성하면서 문제가 생기면 늘린 부분을 버리고 더 작은 단계를 밟아서 다시 시작하면 된다.

<br>

 `RosterReporter`에서 `getReport()`를 해당 메서드 안에서 보고서를 만들고 반환을 한다. 여기서 보고서를 작성하는 부분을 작은 메서드로 분리하면 더욱 좋을 것이다. 따라서 아래와 같이 `writeHeader`, `writeBody`, `writeFooter`의 세 단계로 보고서를 작성하는 부분의 메서드로 나누어 보자.

```java
public String getReport() {
    StringBuilder buffer = new StringBuilder();
    writeHeader(buffer);
    writeBody(buffer);
    writeFooter(buffer);

    return buffer.toString();
}

void writeHeader(StringBuilder buffer) {
  	buffer.append(ROSTER_REPORT_HEADER);
}

void writeBody(StringBuilder buffer) {
    for (Student student: session.getAllStudents()){
      buffer.append(student.getName());
      buffer.append(NEWLINE);
    }
}

void writeFooter(StringBuilder buffer) {
    buffer.append(
      ROSTER_REPORT_FOOTER + session.getAllStudents().size() + NEWLINE);
}
```

 또한 패키지의 경우에도 Report는 사용자에게 출력을 제공하기 때문에 사용자 인터페이스의 일부로 생각할 수 있으므로 아래와 같이 패키지 구조를 나누는 것이 좋겠다.

```
└── src
    └── sis
        ├── report
        │   ├── RosterReporter.java
        │   └── RosterReporterTest.java
        └── studentinfo
            ├── AllTests.java
            ├── CourseSession.java
            ├── CourseSessionTest.java
            ├── DateUtil.java
            ├── DateUtilTest.java
            ├── Student.java
            └── StudentTest.java
```

 IDE를 사용하지 않고 폴더를 통해 옮긴다면 패키지가 옮겨지는 클래스의 경우 상단의 `package`를 꼭 바꾸어 주고 재 컴파일을 해야할 것이다. 하지만 필자는 IntelliJ의 리팩토링 기능을 사용하여 패키지를 옮겨 이러한 수고를 덜 수 있다.

 만약 메서드에 접근 제어자가 붙어있지 않다면 패키지를 옮기고나서 에러가 나는 부분이 존재할 것이다. 아무 접근제어가자 붙지않는다면 기본 접근 권한으로 다른 패키지에서 접근이 불가능하다. 따라서 다른 클래스에서 접근한다면 `public`으로 지정을 해주어야한다.

 그러나 모든 클래스, 메서드에 public을 붙이면 좋지 않다. 코드는 가능한 보호하고 필요한 경우에만 접근 제한을 풀어준다. 너무 많이 내보이면 사용한는 코드가 시스템을 구성한 방식에 불필요하게 의존하게 될 것이기 때문이다.

 또한 테스트의 경우 패키지별로 `AllTests`를 만들어 주는 것이 일반적으로 패키지의 모든 클래스들이 테스트되는 것을 보장할 수 있다. 따라서 `sis.report`에도 `AllTests` 클래스를 만들어준다.

```java
public class AllTests {
    public static TestSuite suite() {
        TestSuite suite = new TestSuite();
        suite.addTestSuite(RosterReporterTest.class);
        return suite;
    }
}
```

 이제 `sis`에서 `sis.report`와 `sis.studentinfo`에 있는 모든 테스트를 한번에 진행할 수 있는 `AllTests`를 만들 수 있다.

```java
public class AllTests {
    public static TestSuite suite() {
        TestSuite suite = new TestSuite();
        suite.addTest(sis.report.AllTests.suite());
        suite.addTest(sis.studentinfo.AllTests.suite());
        return suite;
    }
}
```

 여기서는 `addTestSuite`이 아닌 `addTest`로 `suite()` 메시지를 보내서 얻은 결과를 전달한다. 객체 대신 클래스에 메시지를 보내면 스태틱 메서드(static method)가 호출된다.


# [자바 프로그래밍] Lesson2 자바의 기초

### CourseSession

> 강의의 기본 과목 정보와 수강정보를 관리하기 위해 CourseSession 클래스를 정의할 것이다.
>
> 단과대학, 수강번호, 학점 등의 기본적인 과목 정보는 일반적으로 학기마다 같다.
>
> 강의는 과목의 한가지 부분이며 다른 여러 정보와 강의 날짜와 강의하는 선생님의 정보, 강의에 수강신청한 학생의 목록을 가진다.

<br>

 CourseSession 클래스를 만들기 전에 테스트 케이스를 먼저 작성한다.

```java
public class CourseSessionTest extends TestCase {
    public void testCreate() {
        CourseSession session = new CourseSession("ENGL", "101");
        assertEquals("ENGL", session.getDepartment());
        assertEquals("101", session.getNumber());
    }
}
```

`testCreate()`는 생성 테스트로 어떤 객체가 생성된 직후에 어떻게 보이는지를 살펴보기 위한 좋은 위치이다. 그럼 이 테스트를 통과하기 위하여 CourseSession클래스를 작성해보자.

```java
public class CourseSession {
    private String department;
    private String number;

    CourseSession (String department, String number) {
        this.department = department;
        this.number = number;
    }

    String getDepartment() {
        return department;
    }

    String getNumber() {
        return number;
    }
}
```

 `Student`클래스와 `CourseSession`클래스는 모두 '가져가기' 메서드를 제공했다. 하지만 객체 지향 시스템에서 데이터를 저장하고 가져오는 것만을 다룬다면 작성한 시스템은 쓸모가 없을 것이다. 물론 객체 지향이라고 할 수도 없을 것이다. 객체 지향 시스템은 *특정 동작을 모델화하는 것이라는 것*을 기억해야한다. 동작은 객체에 데이터를 요구하는 것이 아니라 어떤 일을 하도록 하기 위해 해당 객체에 메시지를 보내서 수행된다. 하지만 일을 시작하기 위해서, 그리고 객체의 상태가 어떤지를 알 수 없다면 테스트 내에서 확인을 할 수가 없기에 '가져가기' 메서드가 필요한 면이 있다.

<br>

### 수강신청하기

 `CourseSession`이라는 클래스는 수강신청한 학생들을 관리하기 위해 학생의 집합이라는 새로운 속성을 저장할 필요가 있다. 만약, 방금 새로운 과목이 생성되었다면 신청한 학생은 없을 것이다. 이 상황을 테스트 케이스 코드에 녹여보겠다.

```java
public void testCreate() {
    CourseSession session = new CourseSession("ENGL", "101");
    assertEquals("ENGL", session.getDepartment());
    assertEquals("101", session.getNumber());
    assertEquals(0, session.getNumberOfStudents());
}
```

 `session`에서 학생의 수를 가져와 현재 0명이라는 것을 확인한다. 이 테스트를 위해서는 `CourseSession`클래스에 다음과 같이 `getNumberOfStudents()`라는 메서드를 추가해야한다.

```java
public class CourseSession {
    private String department;
    private String number;

    CourseSession (String department, String number) {
        this.department = department;
        this.number = number;
    }

    String getDepartment() {
        return department;
    }

    String getNumber() {
        return number;
    }

    int getNumberOfStudents() {
        return 0;
    }
}
```

 이렇게 `0`이라는 int형 변수를 반환해주면 위의 테스트는 통과할 수 있게된다.

- ##### int형 변수

  > -2,147,483,648에서 2,147,483,647까지의 정수 값을 표현하고 저장할 수 있는 변수형. int형 변수는 기본형(primitive type)이다.

<br>

그럼 이제 진짜로 학생들이 수강 신청을 하는 것을 테스트 케이스에 녹여보면 아래와 같은 테스트 코드를 작성할 수 있다.

```java
public void testEnrollStudents() {
    CourseSession session = new CourseSession("ENGL", "101");

    Student student1 = new Student("Cain DiVoe");
    session.enroll(student1);
    assertEquals(1, session.getNumberOfStudents());

    Student student2 = new Student("Coralee DeVaughn");
    session.enroll(student2);
    assertEquals(2, session.getNumberOfStudents());
}
```

 "ENGL"이라는 한 강의에 "Cain DiVoe"와 "Coralee DeVaughn"이라는 두 학생이 차례대로 수강 신청을 하고 한 명이 증가할때마다 `getNumberOfStudents()`메서드의 반환 값도 증가하게 된다. 이를 구현하기 위해서 아래와 같이 `CourseSession`클래스를 변경해준다.

```java
public class CourseSession {
   	...
    private int numberOfStudents = 0;
		...
    int getNumberOfStudents() {
        return numberOfStudents;
    }

    void enroll(Student student) {
        numberOfStudents = numberOfStudents + 1;
    }
}
```

 `numberOfStudents`라는 필드를 정의하고 초기 값(initial value)으로 `0`을 할당해준다. (초기 값은 객체가 인스턴스화외었을 때 생성자 내의 코드가 실행되기 전에 초기화된다.) 이렇게 하면 `enroll`을 할 때마다 `numberOfStuendts` 값은 1만큼 증가를 할 것이다. 따라서 테스트에도 성공을 할 것이다.

 그런데 여기서 "enroll"이라는 이름의 메서드가 필요하고 인자로는 Student가 필요하다는 것을 어떻게 알 수 있을까? 이는 개발자가 클래스를 사용하기 위한 공용 인터페이스 (public interface)를 디자인하는 과정이다. 즉, session으로 보내야할 메시지를 디자인하는 것이라고 볼 수 있다.

 지금까지 구현한 것을 클래스 다이어그램으로 그려보면 아래와 같다.

<img width="422" alt="Screen Shot 2020-08-16 at 11 40 31 PM" src="https://user-images.githubusercontent.com/37801041/90336911-e04a6e80-e019-11ea-9466-50c2f1ebfc82.png">

 enroll 메서드가 Student 객체를 인수로 받기 때문에 CourseSession은 Student에 의존적이다. 다시말해 Student 클래스가 없으면 CourseSession 클래스를 컴파일 할 수 없게된다.

<br>

### 초기화 (Initialization)

 위의 코드에서 `CourseSession`클래스의  `numberOfStudents`필드를 선언과 동시에 0으로 초기화하였다. 그러나 int형의 필드는 기본적으로(아무 값이 할당되지 않으면) 0으로 초기화된다. 따라서 0으로 초기화하는 것이 필요하지않을 수 있다. 그러나 이렇게 명시적으로 필드를 초기화하는 것은 코드의 목적을 명확하게 하는데에는 도움이 될 수 있다. 

 또한 필드를 초기화하는 방법은 두 가지가 있다.

1. 필드 선언 부분에서 초기화

2. 생성자 안에서 초기화

 어디에서 초기화를 해야하는지 절대적인 규칙은 없지만, 이 책의 저자는 다음과 같은 이유로 필드 선언 부분에서 초기화하는 것을 더 좋다고 판단한다. 

- 초기화와 선언을 같은 자리에서 하는 쪽이 코드를 따라가기 쉽다.
- 생성자가 여러 개일 경우 한번에 초기화하여 중복을 줄여준다.

  물론 필드 선언시에 초기화 코드를 넣을 수 없는 상황이 있을수도 있는데 이러한 상황에서는 생성자에서 초기화하는 것이 유일한 방법일 것이다.

<br>

### 기본 생성자

```java
public class StudentTest extends TestCase {
  	// StudentTest() {
  	//
    // }  
  	...
}
```

 StudentTest, CourseSessionTest를 보면 어떤 테스트에도 생성자가 없는 데 실행이 잘 되는 것을 확인했을 것이다. 어떻게 된 것일까? 이는 클래스에서 생성자를 정의 하지 않으면 자바 컴파일러가 기본적으로 인수를 가지지 않는 생성자를 자동으로 생성해주기때문에 가능하다. 즉, 기본 생성자를 생성하는 것은 자바가 생성자를 클래스의 필수 요소로 여긴다는 것을 의미하기도 한다. 또한 자바 컴파일러는 하나의 클래스 안에서 여러 개의 생성자를 정의하는 것을 허용하는데 이 부분은 뒷 부분에서 다룰 예정이다.

<br>

### 스위트(Suites)

 JUnit은 스위트(suite)라는 테스트의 집합을 만드는 기능을 제공한다. 즉, 여러 테스트를 한번에 모아서 실행해주는 것이다. `AllTests`라는 클래스를 만들고 아래 코드를 작성해보자.

```java
import junit.framework.TestSuite;

public class AllTests {
    public static TestSuite suite() {
        TestSuite suite = new TestSuite();
        suite.addTestSuite(StudentTest.class);
        suite.addTestSuite(CourseSessionTest.class);
        return suite;
    }
}
```

 `addTestSuite()`메서드를 사용해 테스트를 추가할 수 있는데 여기 들어가는 인자는 클래스 값(literal)이다. 이 값은 클래스의 이름이 `.class`를 붙여서 표시한다. 이는 클래스를 유일하게 표시하고 클래스 정의 자체를 다른 모든 객체와 비슷하게 다룰 수 있도록 해준다.

<br>

### ArrayList

 지금까지 구현한 코드에서는 수강 신청을 학생의 수만 저장을 하였다. 그러나 이는 부족하다. 학생 수 외에도 학생의 정보도 저장을 해야한다. 따라서 아래와 같이 테스트 코드를 작성해보자.

```java
public void testEnrollStudents() {
    CourseSession session = new CourseSession("ENGL", "101");

    Student student1 = new Student("Cain DiVoe");
    session.enroll(student1);
    assertEquals(1, session.getNumberOfStudents());
    ArrayList<Student> allStudents = session.getAllStudents();
    assertEquals(1, allStudents.size());
    assertEquals(student1, allStudents.get(0));

    Student student2 = new Student("Coralee DeVaughn");
    session.enroll(student2);
    assertEquals(2, session.getNumberOfStudents());
    assertEquals(2, allStudents.size());
    assertEquals(student1, allStudents.get(0));
    assertEquals(student2, allStudents.get(1));
}
```

 ArrayList<Student>형을 사용해 객체를 담을 수 있다. 여기서 괄호(<>)안에 인수형을 넣은 형실을 인수화된 형식(parameterized type)라고 부른다. 괄호안에 Student 인수를 넣어줌으로써 ArrayList에 들어갈 수 있는 형식은 Student로 한정(bound)이 된다는 것을 의미한다. 

 ArrayList의  `size()`라는 메서드는 현재 List에 들어있는 객체의 수를 반환해주며, `get()`이라는 메서드는 임의의 위치의 항목을 반환하지만 인자 값으로 index값을 넣어주면 해당 index의 항목을 돌려준다.

<br>

### 객체 추가하기

 위에 구현한 테스트를 통과하기 위해서는 아래와 같이 `CourseSession`클래스를 변경해야한다.

```java
import java.util.ArrayList;

public class CourseSession {
    ...
    private ArrayList<Student> students = new ArrayList<Student>();
    ...
    void enroll(Student student) {
        numberOfStudents = numberOfStudents + 1;
        students.add(student);
    }

    public ArrayList<Student> getAllStudents() {
        return students;
    }
}
```

 `students`라는 ArrayList를 만들어 수강 신청한 학생 객체들을 저장할 것이며 `getAllStudents()`라는 메서드를 정의하고 students를 반환해준다. 또한 `enroll()`메서드에서 인자로 받은 student를 students에 `add()   `메서드를 사용해 ArrayList에 추가해줄 수 있다.

> 자바는 "모든 것이 객체인 '순수한' 객체 지향 언어"라는 말이 있다. java.lang.Object클래스는 모든 클래스의 부모 클래스이다. 그렇기에 ArrayList의 add는 Object 타입으로 인자를 받을 수 있다. (현재는 제네릭 타입 E로 구현이 되어있다.) 하지만 ArrayList<Student>와 같이 괄호안에 bound를 둠으로써 부주의로 인한 다른 형식의 객체가 리스트에 추가되는 것을 방지할 수 있다. 이는 자바가 강한 형식(strongly typed)언어이기때문이다.

<br>

<img width="408" alt="Screen Shot 2020-08-17 at 12 30 33 AM" src="https://user-images.githubusercontent.com/37801041/90337932-dd9f4780-e020-11ea-8129-a6aced2fc2af.png">

 위의 다이어그램을 보면 CourseSession과 Student 의존 관계에 '*'이 있는 것을 볼 수 있다. 이는 하나의 CourseSession에 여러 Student가 의존할 수 있다는 뜻으로 1대 다 관계임을 보여준다.

<br>

### 점진적인 재구성

 `CourseSession`의 코드에서 `numberOfStudents`는 `students.size()`로 대체가 가능하다. 따라서 `numberOfStudents`필드는 불필요한 필드라고 볼 수 있으므로 제거를 하고 `getNumberOfStudents()`메서드를 수정해주고 `enroll()`메서드에서 `numberOfStudents` 관련 코드를 지워주는 것이 좋을 것이다.

```java
import java.util.ArrayList;

public class CourseSession {
    private String department;
    private String number;
    private ArrayList<Student> students = new ArrayList<Student>();
 		...
    int getNumberOfStudents() {
        return students.size();
    }

    void enroll(Student student) {
        students.add(student);
    }

    public ArrayList<Student> getAllStudents() {
        return students;
    }
}
```

<br>

### 패키지와 import 명령어

 패키지는 개발자들이 관련된 클래스를 모을 수 있는 방법을 제공한다. 예로들면 `java.util.ArrayList`에서 패키지 이름은 `java.util`이고 `ArrayList`는 클래스의 이름이다. 즉, ArrayList는 `java.util`패키지에 정의되어있는 클래스인 것이다.

 패키지를 사용하는 이유는 다음과 같다.

- 클래스들을 모아 두면 개발자들이 수백, 수천 개의 클래스를 뒤지는 수고를 덜 수 있다.
- 배포를 위하여 또는 코드의 모듈이나 부분 시스템을 재사용하기 편하게 하기 위하여 클래스를 모아두기 위해
- 네임스페이스를 제공하기 때문에.
- 좀 더 유일한 클래스 이름을 정하는 방법이 되어 이름이 겹칠 가능성을 최소화한다.

 네임스페이스로 사용하기 위하여 `import`라는 키워드를 사용할 수 있다. 위의 코드에서 ArrayList를 그냥 쓸 수 있었던 것은 코드 맨 윗부분에 `import java.util.ArrayList`가 있었기 때문이다.

 또한 import보다 윗 부분에는 해당 클래스가 패키지에 포함되도록 package 명령문을 추가해야한다. IDE에서 새로운 패키지 `studentinfo`를 생성하고 지금까지 만들어 놓은 5개의 class 파일들을 패키지 폴더 안으로 옮기게 되면 자동으로 리팩토링을 해주고 클래스 파일을 열어보면 `package studentinfo;`와 같은 문장이 코드 맨 윗부분에 추가되어있는 것을 확인할 수 있을 것이다. package 명령문은 코드의 맨 처음 부분에 나와야하기 때문이다. 또한 패키지 이름을 작성하는데에도 조건이 있다.

##### 패키지의 이름에 대한 조건

- 관습적으로 소문자를 사용
- 신중한 약어를 사용하여 패키지 이름이 복잡해지는 것을 방지한다.
- java 또는 javax로 시작해서는 안된다. (Sun에서 독점적으로 사용)

<br>

### java.lang 패키지

 여기서 드는 의문점이 있을 수 있다. String이라는 클래스는 왜 아무 import도 없이 사용이 가능할까? String은 `java.lang.String`으로 java.lang 패키지에 포함되어 있다. 이 패키지는 너무나 기본적인 것이라서 import를 명시하지 않아도 사용할 수 있게 모든 자바 소스파일에 내재가 되어있다. 또한 모든 클래스에는 아래와 같이 extends가 내재되어있다.

`class ClassName extends java.lang.Object`

<br>

### setUp 메서드

 `CourseSessionTest` 코드를 보면 두 개의 테스트에서 모두 `CourseSession session = new CourseSession("ENGL", "101");`가 중복되고 있는 것을 확인할 수 있다. 이러한 중복을 피하기위해서 JUnit3는 `setUp()`이라는 메서드를 제공한다. 이는 각각의 테스트 메서드를 실행하기 전에 실행되게 된다. 따라서 테스트 초기화를 위한 공통 코드를 포함시켜야한다. 여기서 만든 테스트에는 아래와 같이 바꿀 수 있다.

```java
public class CourseSessionTest extends TestCase {
    private CourseSession session;

    public void setUp() {
        session = new CourseSession("ENGL", "101");
    }

    public void testCreate() {
        assertEquals("ENGL", session.getDepartment());
        ...
    }

    public void testEnrollStudents() {
        Student student1 = new Student("Cain DiVoe");
        ...
    }

}
```

 여기서 주의할 점은 setUp메서드 안에서 session을 지역변수로 선언하면 안된다는 것이다.

<br>

### 추가적인 재구성

 `testEnrollStudents()`를 재구성 해보자.

```java
public void testEnrollStudents() {
    Student student1 = new Student("Cain DiVoe");
    session.enroll(student1);
    assertEquals(1, session.getNumberOfStudents());
    assertEquals(student1, session.get(0));

    Student student2 = new Student("Coralee DeVaughn");
    session.enroll(student2);
    assertEquals(2, session.getNumberOfStudents());
    assertEquals(student1, session.get(0));
    assertEquals(student2, session.get(1));
}
```

 위의 코드를 보면 기존의 코드와 비교해서 `allStudents`라는 지역변수를 삭제하였다. 그리고 session에서 바로 get()을 호출하였다. 이렇게 하기위해서는 아래와 같이 `CourseSession`을 get()메서드를 추가해주어야 한다.

```java
public Student get(int index) {
 	 return students.get(index);
}
```

 이렇게 재구성을 하면 이점은 무엇일까? 테스트 코드에서의 중복을 피할 수 있다. 또 다른 이점으로는 바로 불필요하게 노출된 세부사항을 숨겨 캡슐화를 하였다는 것이다. 기존의 코드는 학생을 저장하는 방식이 ArrayList라는 것이 노출되어 변경에 취약해질 수 있다. 또 다른 클래스가 `CourseSession`클래스가 변화를 알지 못하는 상태에서 객체를 새로 추가하거나 제거하는 것과 같이 콜렉션의 조작을 허용하므로 CourseSession 객체의 완결성을 망가트릴 수 있다. 위의 재구성은 캡슐화를 통해 방금 언급한 것과 같은 문제점을 방지해준다.

<br>

### 클래스 상수(class constants)

- ##### final 키워드

  > "이 변수의 정해진 값을 변경하는 것을 바라지 않는다"는 것을 다른 개발자에게 알린다. final 키워드는 다른 값을 할당하는 것을 막을 수 있다. 이 책의 저자는 필드를 final로 선언하는 것은 보호를 위해서가 아니고 가독성을 위해서라고 한다.

 또한 일반적인 값들은 중복이 될 가능성이 크다. 따라서 클래스 상수로 선언하여 중복을 최소화할 수 있다.

- ##### 클래스 상수

  > static과 final 키워드로 선언된 필드이다. final 키워드에 의해 다른 값을 참조하지 못하며 static 키워드에 의해 필드를 필드가 포함되는 클래스의 인스터스를 생성하지 않고도 사용할 수 있게 된다.

 다음은 클래스 상수를 사용하는데에 있어 조건이라고 할 수 있다.

- 관례적으로 대문자를 사용
- 모두 대문자이므로 단어들을 분리하기 위하여 언더바(_)를 사용
- 사용할때에는 `클래스이름.상수의 이름`과 같은 형식으로 사용한다.

<br>

### 강의의 시작과 끝

> 강의는 16주라고 계산하며 이 책에서는 총 일수를 16*7-3으로 계산을 하였다.

강의의 시작일이 주어지면 끝나는 일자를 반환해주는 메서드에 대한 테스트를 작성해보면 아래와 같다.

```java
public void testCourseDates() {
    int year = 103;
    int month = 0;
    int date = 6;
    Date startDate = new Date(year, month, date);

    CourseSession session =
      new CourseSession("ABCD", "200", startDate);

    year = 103;
    month = 3;
    date = 25;
    Date sixteenWeeksOut = new Date(year, month, date);
    assertEquals(sixteenWeeksOut, session.getEndDate());
}
```

 Date 클래스를 사용하면 year(년에 1900을 뺀 값, 여기선 2003년), month(0~11, 여기선 1월), date(1~31, 여기선 6일)로 시작하여 끝나는 날을 실제로 계산한 날짜인 "2003년 4월 25일"를 넣어 검증을 한다. 이 테스트를 보면 중간에 session을 새로 인스턴스를 저장한다. 이렇게 하기위해서는 `CourseSession`에서 생성자를 오버로딩해야한다. 

- 오버로딩

  > 오버로딩은 같은 이름의 메서드이지만 정해진 인자에 따라서 수행하는 코드가 달라질 수 있다는 것이다. 위에서는 `CourseSession`에 과목명과 번호 두 가지를 인자로 받는 생성자와 과목명, 번호, 시작일 세 가지를 받는 생성자가 동시에 존재하여 두 가지가 오면 앞의 생성자가 실행되고 세 가지가 오면 뒤의 생성자가 실행되는 것을 의미한다.

  <br>

그러나 강의의 시작일은 어차피 가지고 있어야하는 필드로 생성자를 변경하고 setUp()의 코드도 변경하는 것이 좋을 것 같다. 그렇게한다면 테스트 코드도 아래와 같이 변경이 될 것이다.

```java
package studentinfo;

import junit.framework.TestCase;

import java.util.Date;

public class CourseSessionTest extends TestCase {
    private CourseSession session;
    private Date startDate;

    public void setUp() {
        int year = 103;
        int month = 0;
        int date = 6;
        startDate = new Date(year, month, date);
        session = new CourseSession("ENGL", "101", startDate);
    }

    public void testCreate() {
        assertEquals("ENGL", session.getDepartment());
        assertEquals("101", session.getNumber());
        assertEquals(0, session.getNumberOfStudents());
        assertEquals(startDate, session.getStartDate());
    }
  	...
    public void testCourseDates() {
        int year = 103;
        int month = 3;
        int date = 25;
        Date sixteenWeeksOut = new Date(year, month, date);
        assertEquals(sixteenWeeksOut, session.getEndDate());
    }

}
```

 startDate를 setUp()에서 정하고 할당을 해준 뒤 testCreate()에서 검증을 해준 뒤 정말로 16주 뒤인지 testCourseDates()에서 검증을 한다. 이 테스트를 통과시키기 위해서는 아래와 같이 `CourseSession`을 변경해주어야 한다.

```java
package studentinfo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class CourseSession {
    private String department;
    private String number;
    private ArrayList<Student> students = new ArrayList<Student>();
    private Date startDate;

    CourseSession(String department, String number, Date startDate) {
        this.department = department;
        this.number = number;
        this.startDate = startDate;
    }
		...
    public Date getEndDate() {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(startDate);
        int numberOfDays = 16 * 7 - 3;
        calendar.add(Calendar.DAY_OF_YEAR, numberOfDays);
        Date endDate = calendar.getTime();
        return endDate;
    }

    public Date getStartDate() {
        return startDate;
    }
}

```

- 필드에 `startDate` 추가
- 생성자에 startDate 추가
- getEndDate() 구현
- getStartDate() 추가

<br>

### 재구성하기

첫 번째로 재구성할 코드는 `getEndDate()`메서드이다. 여기서는 불필요한 변수 endDate를 제거하고 단순하게 반환을 할 수 있다.

``` java
public Date getEndDate() {
    ...
    //        Date endDate = calendar.getTime();
		//        return endDate;
    return calendar.getTime();
}
```

두 번째는 import 재구성이다. 

```java
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
```

위처럼 같은 패키지의 클래스들이 많이 import되어 코드가 지저분해 보일 수 있다. 이럴때에는 와일드카드(*)를 사용할 수 있다. *은 java.util 패키지의 모든 클래스들을 가르키게 된다.

```java
import java.util.*;
```

 대부분의 메서드는 한 줄에서 여섯줄이 적당하다. 그 이유는 메서드를 빠르게 이해하고 관리하기 위해서이며 메서드가 충분히 짧지 않다면 의미있고 간결한 이름을 지을 수 없게된다. 만약 이름이 짓기 어렵다면 메서드를 작게 분리하는 것을 고려해보아야한다. `CourseSessionTest`에서 Date를 생성할때 년도는 1900을 뺀 값, 월은 0부터 시작하므로 혼동이 올 수 있다. 따라서 이를 아래와 같이 별도의 메서드를 만들어 생성할 수 있다.

```java
Date createDate(int year, int month, int date) {
  	return new Date(year-1900, month-1, date);
}
```

 이와 같은 메서드를 `팩토리 메서드`라고 한다. 즉, 객체를 생성하고 반환하는 역할을 하는 메서드이다. 위의 메서드를 추가하면 테스트 코드도 더 짧고 가독성이 좋게 변경이 될 수 있다.

```java
package studentinfo;

import junit.framework.TestCase;

import java.util.Date;

public class CourseSessionTest extends TestCase {
    private CourseSession session;
    private Date startDate;

    public void setUp() {
        startDate = createDate(2003,1,6);
        session = new CourseSession("ENGL", "101", startDate);
    }
		...
    public void testCourseDates() {
        Date sixteenWeeksOut = createDate(2003,4,25);
        assertEquals(sixteenWeeksOut, session.getEndDate());
    }

    Date createDate(int year, int month, int date) {
        return new Date(year-1900, month-1, date);
    }
}
```

 재구성을 통해 새로 만든 변수들을 다시 지우는 일을 자주하게 된다. 이는 형틀로 찍어낸 것을 조금 더 좋은 형태로 조각을 하는 과정이라고 생각하면 좋다. 이러한 것은 지금 당장 코드의 문제를 고치는 것이 시스템의 나머지 부분과 지나치게 얽힐 때까지 기다리는 것보다 훨씬 효율적이라는 것을 배우게 해줄 것이다.
# [자바 프로그래밍] Lesson1 시작하기

### 테스트하기

- #### 테스트 위주 개발

  > 작성하는 모든 코드에 대하여 테스트를 만드는 것, 또한 작성 이전에 먼저 테스트를 작성하는 것이다.
  >
  > 이러한 테스트는 해야하는 작업을 명확히 하기 위한 도구로 사용된다.

  <img width="420" alt="Screen Shot 2020-08-16 at 5 52 47 PM" src="https://user-images.githubusercontent.com/37801041/90330637-5682ad00-dfe9-11ea-88a0-5e7799fc4d01.png">

   각 클래스는 대응되는 테스트 클래스가 있다. 위 그림에서 `StudentTest` 는 결과 클래스 `Student`를 위한 테스트 클래스이다. 따라서 `StudentTest`는 `Student` 클래스 형식의 객체를 생성하고 생성한 객체로 메시지를 보내서 기대되는 동작을 수행하는지 확인한다. 위 UML의 화살표를 보면 알 수 있듯이 `StudentTest` 는 `Student` 에 종속적이다. 반대로 `Student` 는 `StudentTest` 에 종속적이지 않다. 따라서 작성하는 결과 클래스는 그 클래스를 위해 작성한 테스트에 영향을 받아서는 안된다.

- #### JUnit

  > 이 책은 2005년 발행 된 책으로 javaSE 5.0과 JUnit 3.8.2를 사용한다. 필자는 자바는 11을 사용하고 Junit은 책과 같은 3.8.2를 사용하고 있다.

   IntelliJ에서 자바 프로젝트를 새로 만들고 `Student.class` 파일을 만들고 `Cmd + Shift + T` 를 눌러 테스트 클래스 파일을 `JUnit3` 를 사용하여 만들었다. 그럼 라이브러리를 추가해야한다는 메시지 창이 뜨고 확인을 누르면 자동으로 추가를 해주어 Junit3를 사용할 수 있게끔 해준다. 

   ```Java
  import junit.framework.TestCase;
  
  public class StudentTest extends TestCase {
      public void testCreate() {
      }
  }
   ```

   위의 코드를 작성하고 실행을 해보면 녹색 글씨와 함께 성공했다는 것을 확인할 수 있다. JUnit3의 경우 Test 메서드에 대한 조건이 있는데 아래와 같다.

  - 메서드는 public으로 선언해야 한다.

  - 메서드는 값을 반환하지 않는다.

  - 메소드의 이름은 소문자 `test`로 시작해야 한다.

  - 인수를 받지 않는다.

    <br>

  ```java
  public class Student {
      Student(String name){ 
      }
  }
```
  
  ```java
  import junit.framework.TestCase;
  
  public class StudentTest extends TestCase {
      public void testCreate() {
          new Student("Hooong");
      }
  }
```
  
 그럼 이제 `Student` 에 생성자를 만들어주고 테스트클래스에서 Student 인스턴스를 생성해본다. 물론 테스트에 성공을 한다.
  
<br>
  
- #### 생성자 
  
  > 생성자는 흔히 다른 객체가 인수로 생성자에 전달하는 값을 이용하여 객체를 초기화하기 위해 사용되며 항상 클래스의 이름과 같아야하며 반환값을 지정할 수 없는 것이 특징이다.
  
- #### new 연산자
  
  > new 연산자는 클래스를 기반으로 객체 인스턴스를 메모리에 할당하고 메모리 내에서 객체의 위치에 대한 레퍼런스(reference)를 반환한다.
    
  <br>
  
  ```java
  import junit.framework.TestCase;
  
  public class StudentTest extends TestCase {
      public void testCreate() {
          Student student = new Student("Hong");
          String studentName = student.getName();
      }
  }
  ```
  
   이제 테스트 케이스에서 학생에 대한 이름을 불러오기를 원한다고 가정할 때 위와 같이 `.getName()`으로 이름을 요청할 수 있다. 물론 `Student`클래스에 코드 작성을 하지 않은 현재 상황에서 테스트를 돌리면 실패를 할 것이다. 그럼 성공하기 위해서 아래와 같이 `Student`클래스에 `getName()`메서드를 추가해보겠다.
  
  ```java
  public class Student {
      Student(String name){
      }
  
      String getName() {
          return "";
      }
  }
  ```
  
   이렇게 작성해주면 `StudentTest`가 'Student'에게 `getName`이라는 메시지를 보내고 그를 `Student`가 `getName()`메서드를 통해 응답을 해줄 수 있게 된다.
  
- #### 확인하기

  ```java
  import junit.framework.TestCase;
  
  public class StudentTest extends TestCase {
      public void testCreate() {
          Student student = new Student("Hooong");
          String studentName = student.getName();
          assertEquals("Hooong", studentName);
      }
  }
  ```

   JUnit3가 제공하는  `assertEquals()`를 사용하면 두 개의 값이 같은지 확인할 수 있다. 따라서 위에서는 'Hooong'와 student객체에서 얻어온 이름이 같은지를 비교하는 코드가 된다. 그러나 현재 우리는 빈 문자열을 반환해주었으므로 당연히 실패할 것이다.

  ```java
  String getName() {
  	return "Hooong";
  }
  ```

   따라서 위처럼 빈 문자열이 아닌 "Hooong"를 반환해준다면 테스트에 성공하는데는 무리가 없을 것이다.

  그러나 여기서는 문제가 있다. 아래의 코드를 살펴보자.

  ```java
  import junit.framework.TestCase;
  
  public class StudentTest extends TestCase {
      public void testCreate() {
          Student student = new Student("Hooong");
          String studentName = student.getName();
          assertEquals("Hooong", studentName);
  
          Student secondStudent = new Student("Steve");
          String secondStudentName = secondStudent.getName();
          assertEquals("Steve", secondStudentName);
      }
  }
  ```

   위의 코드처럼 테스트를 구성하게 되면 첫 번째 assertEquals의 경우 통과를 하겠지만, 두 번째의 경우에는 "Steve" 와 "Hooong"은 같지 않으므로 실패를 하게 된다. 따라서 각 객체마다 속성을 가지게 하여 이 문제를 해결해야 하는데 속성을 지정하는 가장 직접적인 방법은 `필드(field) 혹은 인스턴스 변수(instance variable)`로 정의를 하는 것이다. 

  ```java
  public class Student {
      String myName;
      
      Student(String name){
          myName = name;
      }
  
      String getName() {
          return myName;
      }
  }
  ```

   즉, `Student`클래스에 `myName`이라는 필드를 정의하여 생성자에서 초기화를 시켜주고 `getName()` 메서드에서 필드를 반환해주면 각 객체별로 다른 속성을 가질 수 있겠된다.

- #### 재구성하기

  개발을 하는데 작성한 코드를 관리하는데에는 많은 노력을 기울여야한다. 그러기 위해 아래와 같은 노력을 해야한다.

  - 시스템 내에 같은 코드가 없도록 한다. (중복을 제거한다.)
  - 코드의 목적을 명확하게 하여 코드를 깨끗하고 명확하게 한다.

  ```java
  import junit.framework.TestCase;
  
  public class StudentTest extends TestCase {
      public void testCreate() {
          Student student = new Student("Hooong");
          String studentName = student.getName();
          assertEquals("Hooong", studentName);
  
          Student secondStudent = new Student("Steve");
          String secondStudentName = secondStudent.getName();
          assertEquals("Steve", secondStudentName);
      }
  }
  ```

   위의 코드는 여태까지 작성한 `Student`클래스에 대한 테스트 클래스이다. 그러나 여기에는 두 가지 정도의 문제점이 있다.

   첫째, 불필요한 지역 변수 `studentName`, `secondStudentName`이 있다는 것이다. 물론 테스트의 목적이 달라진다면 필요한 지역 변수가 될 수도 있겠지만 이 예에서는 assertEquals로 생성될때 넣어준 이름과 생성된 후 반환받은 이름이 같은지를 확인하는 코드이므로 따로 변수에 저장하지 않고 검증을 할 때 `.`을 사용해 바로 받아오는 것이다. 아래의 코드처럼 말이다.

  ```java
  public void testCreate() {
    Student student = new Student("Hooong");
    assertEquals("Hooong", student.getName());
  
    Student secondStudent = new Student("Steve");
    assertEquals("Steve", secondStudent.getName());
  }
  ```

    두번째로, 문자열 값을 코드 않에 넣는 것은 좋지 않은 프로그래밍으로 알려져 있는데, 이는 각 문자열 값이 의미하는 것이 무엇인지를 명확히 추적하는 것이 어렵기 때문이다. 우리의 코드도 그러하다.

  ```java
  public void testCreate() {
    final String firstStudentName = "Hooong";
    Student student = new Student(firstStudentName);
    assertEquals(firstStudentName, student.getName());
  
    final String secondStudentName = "Steve";
    Student secondStudent = new Student(secondStudentName);
    assertEquals(secondStudentName, secondStudent.getName());
  }
  ```

    따라서 위의 코드와 같이 문자열 값을 문자열 상수로 교체를 하여 코드를 작성한다면 더욱 명확한 코드가 될 수 있을 것이다.

   또한 `Student` 클래스에도 문제가 있다. 일반적으로 필드명에 get을 붙여 메소드를 만드는 경우가 많은데 우리가 작성한 코드에서 보면 필드명은 `myName`인데 메서드명은 `getName()`으로 맞지가 않는다. 이로인해 불푤요한 중복이 생길 수도 있다. 따라서 이를 `this`를 사용해 이름을 맞춰줄 수 있다.

  ```java
  public class Student {
      String name;
  
      Student(String name){
          this.name = name;
      }
  
      String getName() {
          return name;
      }
  }
  ```

   여기에는 한 가지 더 문제가 있다. 

  ```java
  public void testCreate() {
    final String firstStudentName = "Hooong";
    Student student = new Student(firstStudentName);
    student.name = "Steve";
    assertEquals(firstStudentName, student.getName());
  
    ... 생략
  }
  ```

   위의 코드에서처럼 테스트 클래스에서 student의 name을 마음대로 변경할 수 있다. 이는 객체지향의 캡슐화를 위반하여 객체간의 결합도를 높여 코드에 좋지 않다. 따라서 name 필드를 `private`로 명시하여 외부 클래스에서는 접근할 수 없게 만들고 불가피하게 변경을 해야한다면 `setName()`과 같은 메서드를 만들어 접근할 수 있게하는 것이 좋은 방법이다.

  ```java
  public class Student {
      private String name;
  
      Student(String name){
          this.name = name;
      }
  
      String getName() {
          return name;
      }
  }
  ```

- #### 개발의 흐름

  지금까지 해온 과정을 흐름 순서대로 작성하면 아래와 같다.

  1. 기능의 일부분을 확인하는 작은 테스트를 작성
  2. 테스트가 실패하는 것을 확인
  3. 테스트를 통과할 수 있는 작은 부분의 코드를 작성
  4. 테스트와 코드를 모두 재구성하여 중복을 제거하고 의미를 명확히 한다.

  이렇게 TDD를 진행하면 깨끗한 코드를 작성하고 유지하는데 큰 도움이 될 것이다.
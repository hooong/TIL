# [자바 프로그래밍] Lesson7 전통적인 요소

- ### 학생 이름 나누기

 학생들의 이름을 firstName, middleName, lastName으로 나누어 저장하고자 한다. 우선 테스트 코드를 먼저 작성해보자. `StudentTest`의 `testCreat()`에 코드를 보충하면 된다.

```java
public void testCreate() {
        final String firstStudentName = "Jane Doe";
        Student student = new Student(firstStudentName);
        assertEquals(firstStudentName, student.getName());
        assertEquals("Jane", student.getFirstName());
        assertEquals("Doe", student.getLastName());
        assertEquals("", student.getMiddleName());

        final String secondStudentName = "Blow";
        Student secondStudent = new Student(secondStudentName);
        assertEquals(secondStudentName, secondStudent.getName());
        assertEquals("", secondStudent.getFirstName());
        assertEquals("Blow", secondStudent.getLastName());
        assertEquals("", secondStudent.getMiddleName());

        final String thirdStudentName = "Raymond Douglas Davies";
        Student thirdStudent = new Student(thirdStudentName);
        assertEquals(thirdStudentName, thirdStudent.getName());
        assertEquals("Raymond", thirdStudent.getFirstName());
        assertEquals("Davies", thirdStudent.getLastName());
        assertEquals("Douglas", thirdStudent.getMiddleName());
    }
```

위의 테스트를 통과시키기 위해 `Student`에 필요한 필드를 추가하고 메소드들을 추가해준다.

```java
public class Student {
		// ...
  
    private String firstName = "";
    private String middleName = "";
    private String lastName;
  
		// ...
  
    public Student(String fullName){
        this.name = fullName;
        credits = 0;
        List<String> nameParts = split(fullName);
        setName(nameParts);
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    private void setName(List<String> nameParts) {
        if (nameParts.size() == 1)
            this.lastName = nameParts.get(0);
        else if (nameParts.size() == 2) {
            this.firstName = nameParts.get(0);
            this.lastName = nameParts.get(1);
        }
        else if (nameParts.size() == 3) {
            this.firstName = nameParts.get(0);
            this.middleName = nameParts.get(1);
            this.lastName = nameParts.get(2);
        }
    }
		
  // ...
}
```

`Student`의 생성자를 살펴보면 `split(fullName)`이라고 작성해두었다. 이러한 프로그래밍 방법은 `의도를 통한 프로그래밍`으로 실제로 어떻게 코딩을 할 것인지를 알기 전에 무엇을 해야 할지를 명확하게 하는 기법이다. 이 방법은 하나의 문제를 여러 작은 목표로 나누는데 사용하면 유용하다. 

`split`은 아래에서 `tokenize()`라는 메소드를 통해 구현하고자 한다.

```java
private List<String> tokenize(String string) {
    List<String> results = new ArrayList<>();

    StringBuffer word = new StringBuffer();
    int index = 0;
    while (index < string.length()) {
      char ch = string.charAt(index);
      if (ch != ' ')
        word.append(ch);
      else
        if (word.length() > 0) {
          results.add(word.toString());
          word = new StringBuffer();
        }
      index++;
    }
    if (word.length() > 0)
      results.add(word.toString());
    return results;
}
```

`tokenzie()`에서는 while 루프가 사용된다. while 루프는 조건을 만족하는 동안 코드 블록을 계속해서 실행하기 위해 사용된다.

<br>

- ### 반복문

  - while

    - 조건을 만족하는 동안 코드 블록을 계속해서 실행하기 위해 사용

    - 무한루프

      ```java
      while (true) {
        
      }
      ```

  - for

    - 코드 본체를 정해진 수만큼 실행하는 것

    - for (초기화; 조건문; 갱신 계산시)으로 이루어짐

    - 다중 초기화와 갱신식이 가능

      ```java
      for (int forward = 0, backward = string.length() - 1;
           forward < string.length();
           forward++, backward--) {
        // ...
      }
      ```

    - 무한루프

      ```java
      for (;;) {
        
      }
      ```

  - do

    - 조건문이 루프 본체의 뒤에 나오는 것을 제외하면 while 루프와 동일
    - 루프 본체를 최소 한번은 실행되도록하는 경우에 사용

앞에서 살펴본 세 가지의 루프 방법을 비교해보면 다음과 같다.

- 루프가 카운트 변수를 다루는데 집중이 된다면 for문이 가장 어울림
- 카운터나 다른 증가 변수를 유지할 필요가 없다면 while로 충분
- 최소한 한번 루프를 실행하는 것은 일반적인 상황이 아니므로 do 루프는 사용 빈도가 적음

<br>

- ### 루프 제어명령

  - break

    - 루프의 실행을 중간에 멈추고 해당 루프 본체 이후의 명령으로 옮겨짐

  - continue

    - 제어가 루프의 조건문으로 옮겨짐

  - 레이블 된 break, continue

    - 레이블이 표시된 곳으로 제어가 옮겨짐.
    - 사용할 상황은 많지 않지만 레이블을 사용해서 이해가 좀더 쉬워지는 경우만 사용함

    ```java
    private boolean found(List<List<String>> table, String string) {
      boolean found = false;
      search:
      for (List<String> row: table) {
        for (String value: row) {
          if (value.equals(target)) {
            found = true;
            break search;
          }
        }
      }
      return found;
    }
    ```

<br>

- ### 재구성

앞서 구현하였던 `Student`의 `setName()`은 중복이 많이 있다. 재구성을 해보자.

```java
private void setName(List<String> nameParts) {
    this.lastName = removeLast(nameParts);
    String name = removeLast(nameParts);
    if (nameParts.isEmpty())
      this.lastName = name;
    else {
      this.middleName = name;
      this.lastName = removeLast(nameParts);
    }
}

private String removeLast(List<String> list) {
    if (list.isEmpty())
      return "";
    return list.remove(list.size() - 1);
}
```

위의 코드와 같이 리팩토링을 진행하면 중복을 제거함과 동시에 퍼져있던 고정된 인덱스를 없앴다. 또한, 이후 유용하게 사용할 수 있는 일반적인 메소드 `removeLast()`도 만들었다.

<br>

- ### 삼중(ternary) 연산자

  - if-else 문을 하나의 문장으로 줄여준다.

  ```java
  // 일반적인 형식
  conditional ? true-value : false-value
    
  // 사용 예
  String message = "the course has " + (sessions == 1 ? "one" : "many") + " sessions";
  ```

  - 삼중 연산자를 너무 많이 사용하면 관리하기 어려운 암호 같은 코드가 되므로 남용하지 말자.

<br>

- ### 이터레이터 (iterator)

이터레이터 객체는 콜렉션 내의 항목에 대한 내부적인 포인터를 유지한다. 그렇기에 다음 항목을 반환하도록 요청할 수 있으며 다음 요소를 반환한 후에는 다음의 가능한 항목을 참조하도록 내부 포인터를 갱신한다. 또한, 콜렉션에 더 이상의 항목이 있는지를 확인할 수도 있다.

앞서 살펴본  for-each 루프는 이터레이터 생성자를 이용하여 만들어진다.

```java
public void testIterate() {
    enrollStudents(session);

    List<Student> results = new ArrayList<>();
    for (Student student: session) {
      results.add(student);
    }

    assertEquals(session.getAllStudents(), results);
}

private void enrollStudents(Session session) {
    session.enroll(new Student("1"));
    session.enroll(new Student("2"));
    session.enroll(new Student("3"));
}
```

`sessionTest`에 위의 테스트 코드를 작성해보고 for-each가 이터레이터를 사용해 만들어지는 것을 확인해보자. 우선 `Student`는 `Iterable`인터페이스를 지원하지 않으므로 `Session` 클래스가 `Iterable` 인터페이스를 구현하도록 하고 `Iterator()`메소드를 구현함으로써 위의 테스트를 통과할 수 있다.

```java
abstract public class Session implements Comparable<Session>, Iterable<Student> {
		// ...
  
    public Iterator<Student> iterator() {
        return students.iterator();
    }
}
```

이렇게 for-each가 이터레이터 생성자를 이용해 코드가 돌아가는 것을 확인해보았다.




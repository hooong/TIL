# JAVA
## JVM
- JAVA가 OS에 상관없이 동작할 수 있도록 중계자 역할을 한다.
- GC등의 기능을 제공
- 일반적인 윈도우 프로그램은 Compile과 Linking을 거쳐 exe파일이 생성되고 OS가 exe파일로부터 Load와 Fetch를 하고 하드웨어가 Decode를 하고 Execute를 하는 과정을 거친다. -> 즉, OS의 영향을 받는다.
java의 프로그램 실행
- Java의 경우 Java Compiler로 javac 명령어를 사용해 컴파일을 하면 Byte Code(.class)가 생성됨.
- JVM의 Class Loader가 .class파일을 Load함.
- Execution Engine이 Loading된 클래스의 Bytecode를 해석하여 Binarycode로 변경함.
- Runtime Data Area : JVM이 OS로부터 할당받은 메모리 (.class에서 loading되어진 클래스들이 배치됨.)
- Method Area : 클래스 정보, 변수정보, Method정보, static변수정보, 상수 정보등이 저장되는 영역, 모든 Thread가 공유
- Heap Area : new 명령어로 생성된 인스턴스와 객체가 저장되는 구역, GC 이슈는 이영역에서 일어남. 모든 Thread가 공유
java 프로그램의 특징
- compile 후 생성된 파일이 interpret과 link없이 바로 JVM에 적재
- JVM이 OS로부터 메모리를 할당받아 스스로 메모리를 관리 (대표적인것이 GC)
- JVM이 OS에 맞추어 알아서 Bytecode를 Binarycode로 interpret하므로 OS에 독립적
- Byte코드가 클래스 단위로 생성되므로, 프로그램의 수정이 일어나도 전체를 다시 컴파일 할 필요가 없다.

## List 컬렉션
#### ArrayList
- 동기화 처리되지 않음
- 인덱스가 있어 랜덤액세스에 유리
- 대용량 데이터를 한 번에 가져아서 여러번 참조해 사용할 때 최상의 성능을 냄
- 물론 synchronized~를 사용하면 멀티쓰레드환경에서 동기화를 제공
#### LinkedList
- 데이터를 삭제할때 인접한 곳의 링크만을 변경하면 되기때문에 중간에 데이터를 추가/삭제할 때 유리
- 추가/삭제가 빈번히 일어나는 대용량 데이터 처리가 필요할때 사용 (스택, 큐, 덱)
- 인덱스가 따로 존재하지 않으므로 iterator를 사용해야 함.
#### Vector
- ArrayList와 동일한 구조, synchronization를 제공하여 멀티스레드에서 사용가능

## Set 컬렉션
순서의 개념이 없고 객체를 중복해서 저장이 불가능한 구조
#### HashSet
- 빠른 접근속도, 순서를 알 수 없음
#### LinkedHashSet
- 추가된 순서대로 접근 가능

## Map
- 키와 값(모두 객체)으로 구성된 Map.Entry를 저장하는 구조
- 키는 중복 불가능
- 동등성을 체크하기위해서는 hashCode(), equals() 모두 오버라이딩하는 것이 좋음. (왜더라? 자바프로그래밍에 이유있음.)
#### HashMap
- python의 딕셔너리랑 같음, 순서X, 중복X, null 허용
- 내부적으로는 배열로 이루어져있어 랜덤엑세스에 유리
#### HashTable
- 동기화 지원(->HashMap보다 느림), null 불가
#### Properties
- 키와 값을 String타입으로 제한한 Map 컬렉션
- `~.properties`파일을 읽어들일 때 주로 사용
- 변경이 잦은 문자열을 저장해서 유지 보수를 편리하게 만들어 줌

#### TreeSet, TreeMap
- 내부적으로 이진탐색트리로 구현 -> 검색속도가 빠름
- 추가/삭제 시 성능 좋지 못함.

## Wrapper Class
- primitive Type을 Object를 상속하는 참조형으로 나타낸 것.

----

## private 생성자

생성자를 private로 선언하게 되면 인스턴스 생성이 불가능하고, 더 나아가 서브클래스를 만들 수 없다.

- 사용하는 이유
  - 굳이 인스턴스를 생성할 필요없이 static으로 선언된 메소드를 사용할 수 있게한다.
  - 예로는 Arrays, Math가 있다.

----

## finally에서의 반환

try블롱안에 return 문을 넣어도 실행되는가? -> Yes

- finally가 실행되지 않는 경우
  - try/catch 블록 수행 중 가상 머신이 종료
  - try/catch를 수행하던 스레드가 죽음

----

## final, finally, finalize()

### final

변수나 메서드 또는 클래스가 '변경 불가능'하도록 만듬

- primitive 변수에 적용 시 : 해당 변수 값은 변경 불가능
- reference 변수에 적용 시 : 참조 변수가 힙 내의 다른 객체를 가리키도록 변경 불가능
- 메소드에 적용 시 : 오버라이드 불가능
- 클래스에 적용 시 : 하위 클래스 작성 불가능 (상속 불가능)

### finally

try/catch 블록이 종료될 때 항상 실행될 코드 블록 정의

### finalize()

Garbage Collector가 더 이상 참조가 존재하지 않는 객체를 메모리에서 삭제하겠다고 결정하는 순간 호출되는 메소드.

----

## Reflection

자바 클래스와 객체에 대한 정보를 프로그램 내에서 동적으로 알아낼 수 있도록 하는 기능

- 런타임 동안에 클래스 내부의 메소드와 필드에 대한 정보를 얻을 수 있다.
- 클래스의 객체를 생성할 수 있다.
- 객체 필드의 접근 제어자(access modifier)에 관계없이, 그 필드에 대한 참조를 얻어내어 값을 가져오거나(getting) 설정(setting)할 수 있음.

```java
// 인자 (parameters)
Object[] doubleArgs = new Object[] { 4.2, 3.9 };

// 클래스 가져오기
Class rectangleDefinition = Class.forName("MyProj.Rectangle");

// Rectangle rectangle = new Rectangle(4.2, 3.9);와 같은 코드
Class[] doubleArgsClass = new Class[] {double.class, double.class};
Constructor doubleArgsConstructor = 
  rectangleDefinition.getConstructor(doubleArgsClass);
Rectangle rectangle = (Rectangle) doubleArgsConstructor.newInstance(doubleArgs);

// Double area = rectangle.area();와 같은 코드
Method m = rectangleDefinition.getDeclaredMethod("area");
double area = (Double) m.invoke(rectangle);
```

```java
Rectangle rectangle = new Rectangle(4.2, 3.9);
Double area = rectangle.area();
```

> 위 두 코드는 정확히 같은 일을 하는 코드이다.

유용하게 쓰이는 용도

- 프로그램이 어떻게 동작하는지 실행 시간에 관측하고 조정할 수 있도록 해줌.
- 메서드나 생성자, 필드에 직접 접근할 수 있어 프로그램을 디버깅하거나 테스트할 때 유용
- 호출할 메서드를 미리 알고 있지 않더라도 그 이름을 사용해서 호출할 수 있음
  - Ex) 클래스 이름, 생성자에 전달할 인자, 메서드 이름을 주면 그 정보를 사용해 객체를 생성하고 메서드를 호출할 수 있음,

----

## 스레드

### Runnable 인터페이스 구현

- Runnable을 implements하고 run()을 오버라이딩한다.
- Runnable 객체를 인자로 주어 Thread 객체를 만들고 start()로 스레드를 시작한다.

### Thread 클래스 상송

- Thread의 run() 메소드를 오버라이딩하여 클래스를 만든다.

### Runnable vs Thread

- 자바는 다중 상속을 지원하지 않음 -> Thread를 상속하게 되면 하위 클래스는 다른 클래스를 상속할 수 없게 된다. 하지만 Runnable을 구현한다면 다른 클래스를 상속받을 수 있다.
- Thread 클래스의 모든 것을 상속받는 것이 부담이 될 경우가 있다. -> Runnable을 구현하는 편이 나을 수 있다.

이렇게 스레드를 생성할 때 Thread를 상속받는 것보다 Runnable 인터페이스를 구현하는 것이 더 선호된다.

static 메소드
OOP
설계 (노션 오브젝트 책 정리본)

## Servlet
자바로 구현된 CGI이며, 클라이언트의 요청에 대하여 동적으로 동작하는 웹 애플리케이션 컴포넌트
특징
- HTML을 사용하여 응답
- Thread 이용하여 동작
- MVC 패턴에서 Controller로 사용됨
동작 순서
- 요청을 받고 HttpServletRequest, HttpServletResponse 객체를 생성
- web.xml에 있는 DD(Deployment Descriptor)를 참조하여 어느 서블릿에 대한 요청인지 파악
- service()호출
- doGet() 또는 doPost() 호출
- HttpServletResponse 객체에 응답 후 HttpServletRequest, HttpServletResponse 소멸
생명주기
- servlet 객체 생성 -> init() -> service() -> destory()

## Servlet Container
- Sevlet을 관리해주는 컨테이너
- 클라이언트의 요청을 받고, 그에 대한 응답을 할 수 있게 웹서버와 소켓을 만들어 통신
- Tomcat이 대표적인 예
역할
- 웹서버와의 통신 지원 (소켓을 만들고 통신하는 것을 API로 제공)
- 서블릿 생명주기 관리
- 멀티쓰레드 지원 및 관리
- 선언적인 보안관리(xml배포 서술자에게 보안관리를 기록하여 보안에 대한 수정이 생겨도 자바 소스코드를 재컴파일 하지 않아도 보안관리 가능)

#CS
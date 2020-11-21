# [JAVA] java, JVM, JDK, JRE란?

처음 자바를 접할때 JDK, JRE가 뭐가 다른지부터 헛갈렸던 기억이 있다. 이번 글에서 이러한 용어에 대한 차이를 알아보고자 한다.

<br>

## Java

 자바는 프로그래밍 언어로 JDK에 들어있는 자바 컴파일러를 통해 `.class`(바이트 코드)파일로 컴파일 할 수 있다. 또한, JVM을 통해 Wirte Once Run Anywhere로 Java 언어 자체는 플랫폼에 독립적이다.

- java 바이트 코드

  ```shell
  $ javap -c Hello.class
  
  Compiled from "Hello.java"
  public class Hello {
    public Hello();
      Code:
         0: aload_0
         1: invokespecial #1                  // Method java/lang/Object."<init>":()V
         4: return
  
    public static void main(java.lang.String[]);
      Code:
         0: getstatic     #2                  // Field java/lang/System.out:Ljava/io/PrintStream;
         3: ldc           #3                  // String Hello Java
         5: invokevirtual #4                  // Method java/io/PrintStream.println:(Ljava/lang/String;)V
         8: return
  }
  ```

> 오라클에서 만든 Oracle JDK 11버전을 상용으로 사용할때만 유료라고 한다. 즉, OpenJDK 11을 사용한다면 무료인 것이다.

<br>

![스크린샷 2020-11-24 오전 2 40 39](https://user-images.githubusercontent.com/37801041/100131615-9f3ee180-2ec7-11eb-8712-c1f4d8a323d1.png)

## JVM 

- Java Virtual Machine
- 바이트 코드를 OS에 특화된 코드로 변환하고 실행해준다.
  - 즉, 특정 플랫폼에 종속적이다.
- `$ java Hello`와 같이 자바 프로그램을 실행할 수 있음.



## JRE

- Java Runtime Environment

- 자바 애플리케이션을 실행할 수 있도록 구성된 배포판이다.

  => `javac`가 포함되어 있지 않기때문에 바이트코드로 컴파일 불가능.

- JVM + Library

  - 즉, JVM과 핵심 라이브러리 및 런타임 환경에서 사용하는 프로퍼티 세팅 또는 리소스 파일을 가지고 있다.



## JDK

- Java Development Kit
- JRE + 개발 툴
- 오라클은 자바 11부터 JRE를 따로 제공하지 않고 오로지 JDK만 배포.



## JVM 언어

- JVM 기반으로 동작하는 프로그래밍 언어
- Kotlin과 같이 컴파일을 통해 `.class` 파일이 생성될 수 있다면 JVM을 통해 실행이 가능하다.

```kotlin
// Hello.kt
fun main(args: Array<String>){
  println("Hello Kotlin")
}
```

해당 코드를 `$ kotilnc Hello.kt -include-runtime -d hello.jar` jar파일로 컴파일을 한 뒤 `$ java -jar hello.jar` 명령어를 통해 JVM으로 실행이 가능하다.

<br>

 이렇게 각 용어들에 대하여 알아보았다. 간단하게 정리해보자면 가장 큰 개념인 JDK가 있고, JDK는 개발툴과 JRE를 포함하며, JRE는 JVM과 라이브러리를 포함하고 있는 개념이다. 자바 11 부터는 JRE가 배포되지 않고 있어 앞으로 JDK와 JRE를 헛갈일 일은 없어질 수도 있을 것 같다.
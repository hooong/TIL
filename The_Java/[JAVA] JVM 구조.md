# [JAVA] JVM 구조



JVM의 전체적인 구조를 그려보면 해당 그림과 같다.

<img width="499" alt="스크린샷 2020-11-26 오전 1 26 19" src="https://user-images.githubusercontent.com/37801041/100255039-63b71c80-2f86-11eb-9bd9-31e809825dfb.png">

<br>

## 클래스 로더

클래스 로더는 바이트 코드(`.class`)를 읽고 메모리의 적절한 위치에 저장한다. 클래스 로더의 구조를 좀 더 자세히 그려보면 다음과 같다.

<img width="519" alt="스크린샷 2020-11-26 오전 2 14 19" src="https://user-images.githubusercontent.com/37801041/100260726-1b4f2d00-2f8d-11eb-8a19-cf62663dfc6a.png">

클래스 로더는 크게 로딩, 링크, 초기화 순으로 진행이 된다. 각 과정들에 대하여 간단히 정리해보았다.

- 로딩

  - `.class` 파일을 읽고 적절한 바이너리 데이터를 만들고 메소드 영역에 저장을 한다.
  - 이때 메소드 영역에 저장하는 데이터는 다음과 같다.
    - FQCN (Fully Qualified Class Name) : 패키지명까지 포함되어 있는 식별자이다.
    - Class, Interface, Enum의 종류
    - 해당 클래스의 메소드와 변수
  - 이러한 로딩 과정이 끝나면 해당 클래스 타입의 Class 객체(`Class<App>` 의 타입)를 생성하여 힙 영역에 저장하게 된다.

  - 또한, 로딩은 크게 Bootstrap, Extension, Application로 계층 구조를 이루고 있다.

    - 부트스트랩 클래스 로더 : JAVA_HOME/lib에 있는 코어 자바 API를 제공하며, 최상위 우선순위를 가진 클래스 로더이다.

    - 플랫폼 클래스 로더(Extension) : JAVA_HOME/lib/ext 폴더 또는 java.ext.dirs 시스템 변수에 해당하는 위치에 있는 클래스를 읽는 로더이다.

    - 애플리케이션 클래스 로더 : 애플리케이션 클래스 패스 (애플리케이션 실행할 때 주는 -classpath 옵션 또는 java.class.path 환경 변수의 값에 해당하는 위치)에서 클래스를 읽는 로더이다.

    - 위의 세 클래스 로더를 확인해보면 다음과 같다.

      ```java
      public class App 
      {
        public static void main( String[] args )
        {
          ClassLoader classLoader = App.class.getClassLoader();
          System.out.println(classLoader);
          System.out.println(classLoader.getParent());
          System.out.println(classLoader.getParent().getParent());
        }
      }
      ```

      ```shell
      jdk.internal.loader.ClassLoaders$AppClassLoader@512ddf17
      jdk.internal.loader.ClassLoaders$PlatformClassLoader@3cb5cdba
      null
      ```

      - 애플리케이션 클래스 로더의 부모가 플랫폼 클래스로더인 것을 확인할 수 있지만 플랫폼 클래스 로더의 부모는 null로 나온다. 이는 부트스트랩 클래스 로더가 네이티브로 짜여 있기때문이다.

      ```java
      static {
        // -Xbootclasspath/a or -javaagent with Boot-Class-Path attribute
        String append = VM.getSavedProperty("jdk.boot.class.path.append");
        BOOT_LOADER =
          new BootClassLoader((append != null && append.length() > 0)
                              ? new URLClassPath(append, true)
                              : null);
        PLATFORM_LOADER = new PlatformClassLoader(BOOT_LOADER);
      
        // A class path is required when no initial module is specified.
        // In this case the class path defaults to "", meaning the current
        // working directory.  When an initial module is specified, on the
        // contrary, we drop this historic interpretation of the empty
        // string and instead treat it as unspecified.
        String cp = System.getProperty("java.class.path");
        if (cp == null || cp.length() == 0) {
          String initialModuleName = System.getProperty("jdk.module.main");
          cp = (initialModuleName == null) ? "" : null;
        }
        URLClassPath ucp = new URLClassPath(cp, false);
        APP_LOADER = new AppClassLoader(PLATFORM_LOADER, ucp);
      }
      ```

      - 따라서 `ClassLoader` 클래스 파일을 확인해보면 위의 코드와 같이 `BOOT_LOADER`, `PLATFORM_LOADER`, `APP_LOADER` 를 확인해 볼 수 있다.

- 링크

  - Verify : `.class` 파일 형식이 유효한지를 체크한다.
  - Preparation : 클래스 변수(static 변수)와 기본값에 필요한 메모리를 준비한다.
  - Resolve : 심볼릭 메모리 레퍼런스를 메소드 영역에 있는 실제 레퍼런스로 교체한다. (해당 과정은 Optional하다.)

- 초기화

  - Static 변수의 값을 할당한다. (이때 static{} 블럭이 있다면 실행이 된다.)

<br>

## 메모리

메모리는 쓰레드들이 공유하는지에 대한 여부로 크게 두 분류로 나뉠 수 있다. 스택, PC 레지스터, 네이티브 메소드 스택은 각 쓰레드별로 하나씩 소유하게 되고, 힙과 메소드 영역의 경우 모든 쓰레드들이 공유하는 영역이다.

- 메소드 영역 : 클래스의 정보 (클래스 이름, 부모 클래스 이름, 메소드, 변수) 저장
- 힙 영역 : 동적할당이 되는 공간으로 생성되는 객체들이 저장된다.
- 스택 영역 : 각 쓰레드별로 런타임 스택을 만들고, 그 안에 메소드 호출을 스택 프레임이라 부르는 블럭으로 쌓는다. (에러 발생 시 쌓여있는 스택 프레임을 확인할 수 있다.)
- PC(Program Counter) 레지스터 : 쓰레드 별 현재 실행할 instruction의 위치를 가리키는 포인터를 가지고 있는다.
- 네이티브 메소드 스택 : 쓰레드가 네이티브 메소드를 호출할 때 쌓이게 되는 스택이다.

<br>

## 실행 엔진

- 인터프리터 : 바이트 코드를 한줄 씩 실행하는 역할을 수행
- JIT 컴파일러 : 인터프리터 효율을 높이기 위해 반복되는 코드를 발견하면 JIT 컴파일로로 해당 코드를 네이티브 코드로 바꿔두고 다음번부터는 컴파일 된 코드를 바로 사용한다.
- GC (Garbage Collector) : 참조되지 않고 있는 객체를 정리하는 역할을 한다. GC는 성능과 관련있게 중요하므로 깊게 알아둘 필요가 있음. 추후 글로 정리할 예정.

<br>

## JNI (Java Native Interface)

우선 native란, java가 아닌 C, C++, 어셈블리어로 작성된 함수를 사용할 수 있는 방법을 제공하는 것으로, native 키워드를 붙여 사용할 수 있다. 위의 그림에서 볼 수 있듯이, 네이티브 메소드 라이브러리에 메소드들이 저장되어 있고 이를 사용하기 위해서 제공되는 인터페이스인 JNI를 사용해야한다.

- Ex) `Thread.currentThread();` 는 네이티브 메소드이다.

  ```java
  @HotSpotIntrinsicCandidate
  public static native Thread currentThread();
  ```

  
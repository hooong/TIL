package com.hong.java8to11;

public interface Foointerface {
    // 기본메소드
    // 인터페이스에 메소드 선언이 아니라 구현체를 제공하는 방법
    // 해당 인터페이스를 구현한 클래스를 깨트리지 않고 새 기능을 추가할 수 있다.
    // ex) WebMvcConfigurer

    void printName();

    // 기본 메소드는 구현체가 모르게 추가된 기능으로 그만큼 리스크가 있다. => 컴파일 에러가 아닌 런타임 에러가 발생할 수 있음.
    // 따라서 아래 @implSpec 태그를 사용해 반드시 문서화 하는 것이 좋다.
    /**
     * @implSpec
     * 이 구현체는 getName()으로 가져온 문자열을 대문자로 출력한다.
     */
    default void printNameUpperCase() {
        System.out.println(getName().toUpperCase());
    }

    // Objects에서 제공하는 equals(), toString()과 같은 메소드는 default로 재정의 불가하다.

    static void printAnything() {
        System.out.println("FOO");
    }

    String getName();
}

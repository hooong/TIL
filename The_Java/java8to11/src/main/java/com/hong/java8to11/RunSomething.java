package com.hong.java8to11;

// 해당 애노테이션으로 만들 수도 있다.
// 이를 위반하면 컴파일 시 에러가 발생할 것이다.
@FunctionalInterface
public interface RunSomething {

    // 추상메서드가 단 하나인 인터페이스가 곧 함수형 인터페이스
//    void doIt();

    // java8부터 static을 붙일 수 있음.
//    static void printName() {
//        System.out.println("Hooong");
//    }

//    default  void printAge() {
//        System.out.println("40");
//    }

    int doIt(int number);

}

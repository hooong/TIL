package com.hong.java8to11;

import java.util.function.*;

public class Foo {
    public static void main(String[] args) {
        // 아래와 똑같은 코드 (lambda)
//        RunSomething runSomething = () -> {
//            System.out.println("Hello");
//        };

        // 익명 함수
//        RunSomething runSomething = new RunSomething() {
//            @Override
//            public void doIt() {
//                System.out.println("Hello");
//            }
//        };

        // 순수함수
        RunSomething runSomething = (number) -> {
            return number + 10;
        };


        // 순수 함수라면 아래와 같이 같은 값을 넣으면 무조건 같은 결과값이 나온다. => 사이드 이펙트가 없다.
        System.out.println(runSomething.doIt(1));
        System.out.println(runSomething.doIt(1));
        System.out.println(runSomething.doIt(1));

        System.out.println(runSomething.doIt(2));
        System.out.println(runSomething.doIt(2));
        System.out.println(runSomething.doIt(2));

        RunSomething runSomething2 = new RunSomething() {
            // 상태를 가지게 되면서 변경의 여지가 생김 => 순수함수 X
            // 함수형 프로그래밍을 하려면 외부의 값이 변경되지 않아야한다는 것을 주의해야함.
            int baseNumber = 10;

            @Override
            public int doIt(int number) {
                baseNumber++;
                return number + baseNumber;
            }
        };

        Plus10 plus10 = new Plus10();
        System.out.println(plus10.apply(1));

        Function<Integer, Integer> plus10_1 = (i) -> i+10;
        Function<Integer, Integer> multiply2 = (i) -> i * 2;
        System.out.println(plus10_1.apply(1));
        System.out.println(multiply2.apply(1));

        // 두 함수를 조합하는 방법
        // compose : multiply2를 먼저 실행하고 그 결과값을 puls10_1의 입력값으로 사용
        Function<Integer, Integer> multiply2AndPlus10 = plus10_1.compose(multiply2);
        System.out.println(multiply2AndPlus10.apply(2));

        // andThen : compose와 반대
        System.out.println(plus10_1.andThen(multiply2).apply(2));

        // Consumer<T> : 반환값이 없고 입력값만 있는 함수 인터페이스
        Consumer<Integer> printT = (i) -> System.out.println(i);
        printT.accept(10);

        // Supplier<T> 입력값 없이 T타입의 값을 반환하는 함수 인터페이스
        Supplier<Integer> get10 = () -> 10;
        System.out.println(get10.get());

        // Predicate<T> : T타입을 받아서 boolean을 리턴하는 함수 인터페이스
        Predicate<String> startsWithHooong = (s) -> s.startsWith("Hooong");
        Predicate<Integer> isEven = (i) -> i%2 == 0;
        // 함수 조합용 메서드 (연산가능)
        // isEven.and();
        // isEven.or();
        // isEven.negate();

        // UnaryOperator<T> : 입력타입과 반환타입이 같은 Function의 특수 형태
        UnaryOperator<Integer> plus10_2 = (i) -> i+10;
    }
}

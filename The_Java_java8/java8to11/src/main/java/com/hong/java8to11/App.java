package com.hong.java8to11;

import java.util.*;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public class App {
    public static void main(String[] args) {
        // 메서드 레퍼런스
        UnaryOperator<String> hi = Greeting::hi;    // static한 메서드를 참조하는 방법
        System.out.println(hi.apply("hooong"));

        Greeting greeting = new Greeting();
        UnaryOperator<String> hello = greeting::hello;    // 인스턴스 메서드를 참조하는 방법
        System.out.println(hello.apply("hooong"));

        Supplier<Greeting> newGreeting = Greeting::new;     // 기본 생성자를 레퍼런스 하는 법
        Greeting greeting1 = newGreeting.get();

        Function<String, Greeting> hooongGreeting
                = Greeting::new;                            // 매개변수가 있는 생성자를 레퍼런스 하는 법
        Greeting greeting2 = hooongGreeting.apply("hooong");
        System.out.println(greeting2.getName());

        String[] names = {"aaa", "ccc", "bbb"};
        Arrays.sort(names, String::compareToIgnoreCase);     // 임의의 객체의 인스턴스 메소드 참조하는 법
                                                             // 다시말해 "aaa", "ccc"와 같은 각각의 String객체의 인스턴스 메소드를 참조
        System.out.println(Arrays.toString(names));

        // default Method
        Foointerface foo = new DefaultFoo("hooong");
        foo.printName();
        foo.printNameUpperCase();

        Foointerface.printAnything();

        // java8에서 추가된 기본 메소드와 스태틱 메소드
        List<String> name = new ArrayList<>();
        name.add("hooong");
        name.add("seokjun");
        name.add("hong");
        name.add("foo");

        // forEach
        name.forEach(System.out::println);  // 메소드 레퍼런스를 사용

        // Spliterator
        Spliterator<String> spliterator = name.spliterator();
        Spliterator<String> spliterator1 = spliterator.trySplit();  // 반으로 쪼갤 수 있다.
        while(spliterator.tryAdvance(System.out::println));     // 기존 iterator와 비슷
        System.out.println("=============");
        while(spliterator1.tryAdvance(System.out::println));    // 병행으로 작업할 때 유용할 수 있다.

        // removeIf
        System.out.println("-----------");
        name.removeIf(s -> s.startsWith("h"));
        name.forEach(System.out::println);


    }
}

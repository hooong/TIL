package com.hong.java8to11;

import java.util.Arrays;
import java.util.Comparator;
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
    }
}

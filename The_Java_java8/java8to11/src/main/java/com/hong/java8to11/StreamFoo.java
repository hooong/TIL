package com.hong.java8to11;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamFoo {
    public static void main(String[] args) {
        List<String> names = new ArrayList<>();
        names.add("hooong");
        names.add("seokjun");
        names.add("hong");
        names.add("foo");
        //Strem은 콜렉션을 소스로 사용하여 처리하는 API?

        // 중계 오퍼레이터
        // => 스트림을 리턴한다.
        // 종료 오퍼레이터 => 스트림이 아닌 다른 타입을 리턴한다.

        // 스트림 파이프라인
        // 종료형 오퍼레이터가 없으면 중계형 오퍼레이터는 실행되지 않는다 => 중계형 오퍼레이터는 기본적으로 lazy하다.
//        Stream<String> stringStream = names.stream().map(s -> {
//            System.out.println(s);
//            return s.toUpperCase();
//        });

        List<String> collect = names.stream().map(s -> {
            System.out.println(s);
            return s.toUpperCase();
        }).collect(Collectors.toList());
        collect.forEach(System.out::println);

        System.out.println("===============");

        // stream을 처리해도 원본은 변경되지 않음.
        names.forEach(System.out::println);

        System.out.println("---------------");

        // 아래와 같이 구현을 할 수 있지만 병렬적으로 처리하기 어려움.
//        for(String name : names) {
//            if (name.startsWith("k")){
//                System.out.println(name.toUpperCase());
//            }
//        }

        // parallelStream을 사용해 병렬처리 가능 (spliterator를 사용)
        // 병렬은 데이터가 방대한 경우에 유용.
        // 컨텍스트 스위칭 같은 처리가 더 많을수도 있기때문에 무조건 좋다고 할 수 없다.
        // 따라서 사용해야한다면 직접 다 성능 측정을 해봐야한다.
        List<String> collect1 = names.parallelStream().map(s -> {
            System.out.println(s + " " + Thread.currentThread().getName());
            return s.toUpperCase();
        }).collect(Collectors.toList());
        collect1.forEach(System.out::println);


    }
}

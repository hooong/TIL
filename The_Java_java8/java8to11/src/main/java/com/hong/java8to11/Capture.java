package com.hong.java8to11;

import java.util.function.Consumer;
import java.util.function.IntConsumer;

public class Capture {

    public static void main(String[] args) {
        Capture foo = new Capture();
        foo.run();
    }

    private void run() {
        int baseNumber = 10;  // 해당 변수가 사실상 코드에서 변경되지 않는 변수(effective final)라면 final을 생략해도 됨.

        // 로컬 클래스
        class LocalClass {
            void printBaseNumber() {
                int baseNumber = 11;
                System.out.println(baseNumber); // 11, 위에서의 baseNumber가 가려짐
            }
        }

        // 익명 클래스
        Consumer<Integer> integerConsumer = new Consumer<Integer>() {
            @Override
            public void accept(Integer baseNumber) {
                System.out.println(baseNumber);     // baseNumber를 매개변수로 받으면서 위의 baseNumber를 가림.
            }
        };

        // 람다
        // 위의 baseNumber와 같은 스콥이라 람다식에서 baseNumber라는 변수명을 새로 만들어 사용할 수 없음.
        IntConsumer printInt = (i) -> {
            System.out.println(i + baseNumber);
        };

        printInt.accept(10);
        // baseNumber++;   // 이펙티브 파이널일때 코드상에서 변화가 있다면 위의 람다식에서 컴파일 에러가 난다.


        // 로컬클래스와 익명클래스는 쉐도잉(위에서 baseNumber가 가려지는 것과 같은...) 효과가 있지만, 하지만 람다는 없다..
    }
}

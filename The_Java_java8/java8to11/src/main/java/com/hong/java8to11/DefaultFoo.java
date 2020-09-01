package com.hong.java8to11;

public class DefaultFoo implements Foointerface {
    // 두 개의 인터페이스를 구현할때에 default 메소드가 같은 것이 있으면 다이아몬드 problem이 발생.

    String name;

    public DefaultFoo(String name) {
        this.name = name;
    }

    @Override
    public void printNameUpperCase() {
        System.out.println(this.name.toUpperCase());
    }

    @Override
    public void printName() {
        System.out.println(this.name);
    }

    @Override
    public String getName() {
        return this.name;
    }
}

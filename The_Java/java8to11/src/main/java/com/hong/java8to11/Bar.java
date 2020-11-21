package com.hong.java8to11;

public interface Bar extends Foointerface {

    // 상속을 받은 인터페이스에서 default 메서드를 사용하기 싫으면 추상메소드로 다시 정의
    void printNameUpperCase();
}

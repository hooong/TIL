package com.hooong;

public class Book {
    public static String A = "A";

    private String B = "B";

    public Book() {
    }

    public Book(String b) {
        B = b;
    }

    private void c() {
        System.out.println("C");
    }

    public int sum(int l, int r){
        return l + r;
    }
}

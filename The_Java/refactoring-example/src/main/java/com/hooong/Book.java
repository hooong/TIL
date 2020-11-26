package com.hooong;

@MyAnnotation("hong")
public class Book {

    private static String b = "BOOK";

    private static final String c = "BOOK";

    @MyAnnotation
    private String a = "a";

    public String d = "d";

    protected String e = "e";

    public Book() {
    }

    @MyAnnotation
    public Book(String a, String d, String e) {
        this.a = a;
        this.d = d;
        this.e = e;
    }

    private void f() {
        System.out.println("F");
    }

    @MyAnnotation
    public void g() {
        System.out.println("g");
    }

    public int h() {
        return 100;
    }



}

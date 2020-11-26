package com.hooong;


import java.lang.reflect.*;
import java.util.Arrays;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchFieldException {
        Class<?> bookClass = Class.forName("com.hooong.Book");
        Constructor<?> constructor = bookClass.getConstructor(String.class);
        Book book = (Book) constructor.newInstance("myBook");
        System.out.println(book);

//        Field a = Book.class.getDeclaredField("A");
        // static 필드의 경우 해당 인스턴스가 없어 null로 가져올 수 있다.
//        System.out.println(a.get(null));
//        a.set(null, "AAAAAA");
//        System.out.println(a.get(null));

//        Field b = Book.class.getDeclaredField("B");
//        // 해당 필드가 private이기 때문에 아래의 코드로 접근 가능하게 할 수 있음.
//        b.setAccessible(true);
//        // 위에서 만들어놓은 book 인스턴스의 것을 가져오는 것.
//        System.out.println(b.get(book));
//        b.set(book, "BBBBBB");
//        System.out.println(b.get(book));

        Method c = Book.class.getDeclaredMethod("c");
        c.setAccessible(true);
        // invoke()를 통해 해당 메소드를 실행할 수 있음.
        c.invoke(book);

        // 파라미터에 대한 class를 지정해주어야함.
        Method sum = Book.class.getDeclaredMethod("sum", int.class, int.class);
        int invoke = (int) sum.invoke(book, 1, 2);
        System.out.println(invoke);
    }
}

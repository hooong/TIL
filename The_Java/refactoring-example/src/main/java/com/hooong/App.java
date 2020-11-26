package com.hooong;


import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws ClassNotFoundException {
        Class<Book> bookClass = Book.class;
        Book book = new Book();
//        Book book = new Book();
//        Class<? extends Book> aClass = book.getClass();
//
//        Class<?> aClass1 = Class.forName("com.hooong.Book");

//        Arrays.stream(bookClass.getDeclaredFields()).forEach(f -> {
//            try {
//                f.setAccessible(true);
//                System.out.printf("%s %s\n", f, f.get(book));
//            } catch (IllegalAccessException e) {
//                e.printStackTrace();
//            }
//        });

//        Arrays.stream(bookClass.getMethods()).forEach(System.out::println);
//
//        Arrays.stream(bookClass.getConstructors()).forEach(System.out::println);

//        Class<? super MyBook> superclass = MyBook.class.getSuperclass();
//        System.out.println(superclass);

//        Arrays.stream(MyBook.class.getInterfaces()).forEach(System.out::println);

//        Arrays.stream(Book.class.getDeclaredFields()).forEach(f -> {
//            int modifiers = f.getModifiers();
//            System.out.println(f);
//            System.out.println(Modifier.isPrivate(modifiers));
//            System.out.println(Modifier.isStatic(modifiers));
//        });

        Arrays.stream(Book.class.getMethods()).forEach(m -> {
            int modifiers = m.getModifiers();
            System.out.println(m);
            System.out.println(m.getReturnType());
        });
    }
}

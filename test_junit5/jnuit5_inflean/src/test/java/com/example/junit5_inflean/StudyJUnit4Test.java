package com.example.junit5_inflean;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class StudyJUnit4Test {

    @Before
    public void before() {
        System.out.println("before");
    }

    @Test
    public void createTest() {
        System.out.println("test");
    }

    @Test
    public void createTest2() {
        System.out.println("test2");
    }

    @Test
    public void createTest3() {
        System.out.println("test3");
    }

    @Test
    public void createTest4() {
        System.out.println("test4");
    }

}
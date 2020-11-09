package sis.studentinfo;

public class Performance {
    private int[] tests = {};
    public void setNumberOfTests(int size) {
        tests = new int[size];
    }

    public void set(int index, int score) {
        tests[index] = score;
    }


    public int get(int i) {
        return tests[i];
    }

    public double average() {
        if (tests.length == 0)
            return 0.0;
        int total = 0;
        for (int test: tests)
            total += test;
        return (double)total / tests.length;
    }

    public void setScore(int... tests) {
        this.tests = tests;
    }
}

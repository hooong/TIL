package sis.studentinfo;

public class Performance {
    private int[] tests;
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
        double total = 0.0;
        for (int test: tests)
            total += test;
        return total / tests.length;
    }

    public void setScore(int... tests) {
        this.tests = tests;
    }
}

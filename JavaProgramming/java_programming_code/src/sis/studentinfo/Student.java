package sis.studentinfo;

import java.util.ArrayList;

public class Student {
    enum Grade { A, B, C, D, F};

    static final int CREDITS_REQUIRED_FOR_FULL_TIME = 12;
    static final String IN_STATE = "CO";

    private String name;
    private String state = "";
    private int credits;
    private ArrayList<Grade> grades = new ArrayList<>();
    private GradingStrategy gradingStrategy = new RegularGradingStratey();

    public Student(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    void setState(String state) {
        this.state = state;
    }

    public void setGradingStrategy(GradingStrategy gradingStrategy) {
        this.gradingStrategy = gradingStrategy;
    }

    int getCredits() {
        return credits;
    }

    void addCredits(int credits) {
        this.credits += credits;
    }

    boolean isFullTime() {
        return credits >= CREDITS_REQUIRED_FOR_FULL_TIME;
    }

    public boolean isInState() {
        return state.equals(Student.IN_STATE);
    }

    void addGrade(Grade grade) {
        grades.add(grade);
    }

    double getGpa() {
        if (grades.isEmpty())
            return 0.0;

        double total = 0.0;
        for (Grade grade: grades) {
            total += gradingStrategy.getGradePointsFor(grade);
        }
        return total / grades.size();
    }
}

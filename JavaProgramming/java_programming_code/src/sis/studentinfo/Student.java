package sis.studentinfo;

public class Student {
    static final int CREDITS_REQUIRED_FOR_FULL_TIME = 12;
    static final String IN_STATE = "CO";

    private String name;
    private String state = "";
    private int credits;

    public Student(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    void setState(String state) {
        this.state = state;
    }

    boolean isFullTime() {
        return credits >= CREDITS_REQUIRED_FOR_FULL_TIME;
    }

    int getCredits() {
        return credits;
    }

    void addCredits(int credits) {
        this.credits += credits;
    }

    public boolean isInState() {
        return state.equals(Student.IN_STATE);
    }
}

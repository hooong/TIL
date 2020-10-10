package sis.studentinfo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
import java.util.logging.Logger;

public class Student {
    public enum Grade {
        A(4),
        B(3),
        C(2),
        D(1),
        F(0);


        private int points;
        Grade(int points) {
            this.points = points;
        }

        int getPoints() {
            return points;
        }


    }
    static final int CREDITS_REQUIRED_FOR_FULL_TIME = 12;
    public static final int MAX_NAME_PARTS = 3;
    static final String TOO_MANY_NAME_PARTS_MSG =
            "Student name '%s' contains more than %d parts";
    final static Logger logger = Logger.getLogger(Student.class.getName());

    static final String IN_STATE = "CO";
    private String name;

    private String firstName = "";
    private String middleName = "";
    private String lastName;
    private String state = "";

    private int credits;
    private ArrayList<Grade> grades = new ArrayList<>();
    private GradingStrategy gradingStrategy = new BasicGradingStrategy();

    public Student(String fullName){
        this.name = fullName;
        credits = 0;
        List<String> nameParts = split(fullName);
        if (nameParts.size() > Student.MAX_NAME_PARTS){
            String message = String.format(
                    Student.TOO_MANY_NAME_PARTS_MSG, fullName, Student.MAX_NAME_PARTS);
            Student.logger.info(message);
            throw new StudentNameFormatException(message);
        }
        setName(nameParts);
    }

    private List<String> split(String string) {
        List<String> results = new ArrayList<>();

//        StringBuffer word = new StringBuffer();
//        int index = 0;
//        while (index < string.length()) {
//            char ch = string.charAt(index);
//            if (ch != ' ')
//                word.append(ch);
//            else
//                if (word.length() > 0) {
//                    results.add(word.toString());
//                    word = new StringBuffer();
//                }
//            index++;
//        }
//        if (word.length() > 0)
//            results.add(word.toString());

//        StringTokenizer tokenizer = new StringTokenizer(string, " ");
//        while (tokenizer.hasMoreTokens())
//            results.add(tokenizer.nextToken());

        for (String name: string.split(" "))
            results.add(name);
        return results;
    }

    public String getName() {
        return name;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    private void setName(List<String> nameParts) {
        this.lastName = removeLast(nameParts);
        String name = removeLast(nameParts);
        if (nameParts.isEmpty())
            this.firstName = name;
        else {
            this.middleName = name;
            this.firstName = removeLast(nameParts);
        }
    }

    private String removeLast(List<String> list) {
        if (list.isEmpty())
            return "";
        return list.remove(list.size() - 1);
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
        Student.logger.fine("begin getGpa " + System.currentTimeMillis());
        if (grades.isEmpty())
            return 0.0;

        double total = 0.0;
        for (Grade grade: grades) {
            total += gradingStrategy.getGradePointsFor(grade);
        }
        double result = total / grades.size();
        Student.logger.fine("end getGpa " + System.currentTimeMillis());
        return result;
    }
}

package sis.studentinfo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class CourseSession {

    private String department;
    private String number;
    private ArrayList<Student> students = new ArrayList<Student>();
    private Date startDate;
    private static int count;
    private int numberOfCredits;

    private CourseSession(String department, String number, Date startDate) {
        this.department = department;
        this.number = number;
        this.startDate = startDate;
    }

    public static CourseSession create(String department, String number, Date startDate) {
        incrementCount();
        return new CourseSession(department, number, startDate);
    }

    String getDepartment() {
        return department;
    }

    String getNumber() {
        return number;
    }

    int getNumberOfStudents() {
        return students.size();
    }

    public void enroll(Student student) {
        student.addCredits(numberOfCredits);
        students.add(student);
    }

    void setNumberOfCredits(int numberOfCredits) {
        this.numberOfCredits = numberOfCredits;
    }

    public ArrayList<Student> getAllStudents() {
        return students;
    }

    public Student get(int index) {
        return students.get(index);
    }

    public Date getEndDate() {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(startDate);
        int numberOfDays = 16 * 7 - 3;
        calendar.add(Calendar.DAY_OF_YEAR, numberOfDays);
        return calendar.getTime();
    }

    public Date getStartDate() {
        return startDate;
    }

    static int getCount() {
        return count;
    }

    static void resetCount() {
        count = 0;
    }

    static void incrementCount() {
        count += 1;
    }
}

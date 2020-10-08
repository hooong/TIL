package sis.studentinfo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class CourseSession extends Session {
    private static int count;

    protected CourseSession(
            String department, String number, Date startDate) {
        super(department, number, startDate);
        CourseSession.incrementCount();
    }

    public static CourseSession create(
            String department,
            String number,
            Date startDate) {
        return new CourseSession(department, number, startDate);
    }

    static int getCount() {
        return count;
    }

    static void resetCount() {
        count = 0;
    }

    static private void incrementCount() {
        ++count;
    }

    protected int getSessionLength() {
        return 16;
    }
}

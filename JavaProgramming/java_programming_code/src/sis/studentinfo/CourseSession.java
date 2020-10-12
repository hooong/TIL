package sis.studentinfo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class CourseSession extends Session {
    private static int count;

    protected CourseSession(Course course, Date startDate) {
        super(course, startDate);
        CourseSession.incrementCount();
    }

    public static Session create(Course course, Date startDate) {
        incrementCount();
        return new CourseSession(course, startDate);
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

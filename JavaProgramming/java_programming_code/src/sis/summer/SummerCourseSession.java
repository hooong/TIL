package sis.summer;

import sis.studentinfo.CourseSession;
import sis.studentinfo.Session;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class SummerCourseSession extends Session {
    public static SummerCourseSession create(
            String department,
            String number,
            Date startDate) {
        return new SummerCourseSession(department, number, startDate);
    }

    private SummerCourseSession(
            String department,
            String number,
            Date startDate) {
        super(department, number, startDate);
    }

    @Override
    protected int getSessionLength() {
        return 8;
    }
}

package sis.summer;

import junit.framework.TestCase;
import sis.studentinfo.*;

import java.util.Date;

public class SummerCourseSessionTest extends SessionTest {
    public void testEndDate() {
        Date startDate = DateUtil.createDate(2003, 6, 9);
        Session session = createSession(new Course("ENGL", "200"), startDate);
        Date eightWeekOut = DateUtil.createDate(2003, 8, 1);
        assertEquals(eightWeekOut, session.getEndDate());
    }

    @Override
    protected Session createSession(Course course, Date startDate) {
        return SummerCourseSession.create(course, startDate);
    }
}
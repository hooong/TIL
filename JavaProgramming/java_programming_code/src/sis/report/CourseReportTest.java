package sis.report;

import junit.framework.TestCase;
import sis.studentinfo.CourseSession;

import java.util.Date;

import static sis.report.RosterReporter.NEWLINE;

public class CourseReportTest extends TestCase {
    public void testReport() {
        final Date date = new Date();
        CourseReport report = new CourseReport();
        report.add(CourseSession.create("ENGL", "101", date));
        report.add(CourseSession.create("CZEC", "200", date));
        report.add(CourseSession.create("ITAL", "410", date));

        assertEquals(
                "CZEC 200" + NEWLINE +
                        "ENGL 101" + NEWLINE +
                        "ITAL 410" + NEWLINE,
            report.text());
    }

    public void testComparable() {
        final Date date = new Date();
        CourseSession sessionA = CourseSession.create("CMSC", "101", date);
        CourseSession sessionB = CourseSession.create("ENGL", "101", date);
        assertTrue(sessionA.compareTo(sessionB) < 0);
        assertTrue(sessionB.compareTo(sessionA) > 0);

        CourseSession sessionC = CourseSession.create("CMSC", "101", date);
        assertEquals(0, sessionA.compareTo(sessionC));

        CourseSession sessionD = CourseSession.create("CMSC", "210", date);
        assertTrue(sessionC.compareTo(sessionD) < 0);
        assertTrue(sessionD.compareTo(sessionC) > 0);
    }
}

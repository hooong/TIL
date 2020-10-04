package sis.studentinfo;

import junit.framework.TestSuite;
import sis.report.RosterReporterTest;

public class AllTests {
    public static TestSuite suite() {
        TestSuite suite = new TestSuite();
        suite.addTestSuite(StudentTest.class);
        suite.addTestSuite(CourseSessionTest.class);
        suite.addTestSuite(DateUtilTest.class);
        suite.addTestSuite(BasicGradingStrategyTest.class);
        suite.addTestSuite(HonorGradingStrategyTest.class);
        return suite;
    }
}

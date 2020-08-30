package sis.studentinfo;

import junit.framework.TestCase;

public class StudentTest extends TestCase {
    public void testCreate() {
        final String firstStudentName = "Hooong";
        Student student = new Student(firstStudentName);
        assertEquals(firstStudentName, student.getName());

        final String secondStudentName = "Steve";
        Student secondStudent = new Student(secondStudentName);
        assertEquals(secondStudentName, secondStudent.getName());
    }

    public void testStudentStatus() {
        Student student = new Student("a");
        assertEquals(0, student.getCredits());
        assertFalse(student.isFullTime());

        student.addCredits(3);
        assertEquals(3, student.getCredits());
        assertFalse(student.isFullTime());

        student.addCredits(4);
        assertEquals(7, student.getCredits());
        assertFalse(student.isFullTime());

        student.addCredits(5);
        assertEquals(student.CREDITS_REQUIRED_FOR_FULL_TIME, student.getCredits());
        assertTrue(student.isFullTime());
    }

    public void testInState() {
        Student student = new Student("a");
        assertFalse(student.isInState());
        student.setState(Student.IN_STATE);
        assertTrue(student.isInState());
        student.setState("MD");
        assertFalse(student.isInState());
    }
}
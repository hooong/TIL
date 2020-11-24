package sis.studentinfo;

import junit.framework.TestCase;

public class StudentDirectoryTest extends TestCase {
    private StudentDirectory dir;

    protected void setUp() {
        dir = new StudentDirectory();
    }

    public void testStoreAndRetrive() {
        final int numberOfStudents = 10;

        for (int i = 0; i < numberOfStudents; i++)
            addStudent(dir, i);

        for (int i = 0; i < numberOfStudents; i++)
            verifyStudentLookup(dir,i);
    }

    private void addStudent(StudentDirectory dir, int i) {
        String id = "" + i;
        Student student = new Student(id);
//        student.setId(id);
        student.addCredits(i);
//        directory.add(student);
    }

    private void verifyStudentLookup(StudentDirectory directory, int i) {
        String id = "" + i;
        Student student = dir.findById(id);
        assertEquals(id, student.getLastName());
//        assertEquals(id, student.getId());
        assertEquals(i, student.getCredits());
    }

}

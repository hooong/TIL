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
}
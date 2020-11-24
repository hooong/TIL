package sis.report;

import junit.framework.TestCase;
import sis.studentinfo.Student;

import java.util.*;

public class ReportCardTest extends TestCase {
    private ReportCard card;

    protected void setUp() {
        card = new ReportCard();
    }

    public void testMessage() {
        assertEquals(ReportCard.A_MESSAGE,
                card.getMessage(Student.Grade.A));
        assertEquals(ReportCard.B_MESSAGE,
                card.getMessage(Student.Grade.B));
        assertEquals(ReportCard.C_MESSAGE,
                card.getMessage(Student.Grade.C));
        assertEquals(ReportCard.D_MESSAGE,
                card.getMessage(Student.Grade.D));
        assertEquals(ReportCard.F_MESSAGE,
                card.getMessage(Student.Grade.F));
    }

    public void testKeys() {
        Set<Student.Grade> expectedGrades = new HashSet<Student.Grade>();
        expectedGrades.add(Student.Grade.A);
        expectedGrades.add(Student.Grade.B);
        expectedGrades.add(Student.Grade.C);
        expectedGrades.add(Student.Grade.D);
        expectedGrades.add(Student.Grade.F);

        Set<Student.Grade> grades = new HashSet<Student.Grade>();
        for (Student.Grade grade: card.getMessages().keySet())
            grades.add(grade);
        assertEquals(expectedGrades, grades);
    }

    public void testValues() {
        List<String> expectedMessages = new ArrayList<String>();
        expectedMessages.add(ReportCard.A_MESSAGE);
        expectedMessages.add(ReportCard.B_MESSAGE);
        expectedMessages.add(ReportCard.C_MESSAGE);
        expectedMessages.add(ReportCard.D_MESSAGE);
        expectedMessages.add(ReportCard.F_MESSAGE);

        Collection<String> messages = card.getMessages().values();
        for (String message: messages)
            assertTrue(expectedMessages.contains(message));
        assertEquals(expectedMessages.size(), messages.size());
    }

    public void testEntries() {
        Set<Entry> entries = new HashSet<Entry>();

        for (Map.Entry<Student.Grade, String> entry:
                card.getMessages().entrySet())
            entries.add(new Entry(entry.getKey(), entry.getValue()));

        Set<Entry> expectedEntries = new HashSet<Entry>();
        expectedEntries.add(new Entry(Student.Grade.A, ReportCard.A_MESSAGE));
        expectedEntries.add(new Entry(Student.Grade.B, ReportCard.B_MESSAGE));
        expectedEntries.add(new Entry(Student.Grade.C, ReportCard.C_MESSAGE));
        expectedEntries.add(new Entry(Student.Grade.D, ReportCard.D_MESSAGE));
        expectedEntries.add(new Entry(Student.Grade.F, ReportCard.F_MESSAGE));

        assertEquals(expectedEntries, entries);
    }
}

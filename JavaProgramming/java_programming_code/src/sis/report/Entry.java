package sis.report;

import sis.studentinfo.Student;

public class Entry {
    private Student.Grade grade;
    private String message;
    Entry(Student.Grade grade, String message) {
        this.grade = grade;
        this.message = message;
    }

    @Override
    public int hashCode() {
        final int hashMultiplier = 41;
        int result = 7;
        result = result * hashMultiplier + grade.hashCode();
        result  = result * hashMultiplier + message.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj.getClass() != this.getClass())
            return false;
        Entry that = (Entry)obj;
        return
            this.grade == that.grade &&
            this.message.equals(that.message);
    }
}
